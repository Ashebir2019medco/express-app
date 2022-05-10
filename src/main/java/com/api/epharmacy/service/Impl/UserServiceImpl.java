package com.api.epharmacy.service.Impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.api.epharmacy.exception.AppException;
import com.api.epharmacy.io.entity.CompanyEntity;
import com.api.epharmacy.io.entity.RoleEntity;
import com.api.epharmacy.io.entity.UserEntity;
import com.api.epharmacy.io.repositories.CompanyRepository;
import com.api.epharmacy.io.repositories.RoleRepository;
import com.api.epharmacy.io.repositories.UserRepository;
import com.api.epharmacy.service.UserService;
import com.api.epharmacy.shared.GenerateRandomString;
import com.api.epharmacy.shared.SendMail;
import com.api.epharmacy.shared.dto.UserDto;
import com.api.epharmacy.ui.model.request.ResetPasswordRequestModel;
import com.api.epharmacy.ui.model.request.SearchRequestModel;
import com.api.epharmacy.ui.model.request.SendEmailRequestModel;
import com.api.epharmacy.ui.model.request.UploadProfileRequestModel;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	GenerateRandomString generateRandomString;
	
	@Autowired
	PasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	CompanyRepository companyRepository;
	
	@Autowired
    RoleRepository roleRepository;
	
	@Autowired
	SendMail sendMailComponent;
	
	@Value("${file.upload-dir}")
    private String uploadDirectory;
	
	@Value("${app.HostDomain}")
    private String applicationHostDomain;
		
	@Override
	public UserDto createUser(UserDto user) throws AddressException, MessagingException, IOException {
		
		//UserEntity checkEmailEntity = userRepository.findByEmail(user.getEmail());
		if(userRepository.findByEmail(user.getEmail()) != null) throw new AppException("Record already exists with this Email.");
		long companyId = user.getCompanyId();
		
		long newCompanyId = 0;
		if (companyId == -1) {
			CompanyEntity companyEntity = new CompanyEntity();
			BeanUtils.copyProperties(user, companyEntity);
			companyEntity.setAccountId(generateRandomString.generateAccountId(6));
			
			CompanyEntity storedCompanyDetail = companyRepository.save(companyEntity);
			newCompanyId = storedCompanyDetail.getCompanyId();
		}
		
		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(user, userEntity);
		
		long returnCompanyId;
		if(companyId == -1 || companyId == 0) {
			userEntity.setCompanyId(newCompanyId);
			returnCompanyId = newCompanyId;
		}else {
			userEntity.setCompanyId(companyId);
			returnCompanyId = companyId; 
		}
		
		//		String userType = "Customer";
		//		String Role = "CUSTOMER";
		//		if(companyId == 0) {
		//			userType = "Admin"; 
		//			Role = "ADMIN";
		//		   }
		 
		String defaultUserStatus = "NotVerified";
		String emailVerificationToken = generateRandomString.generateUserId(45);
		
		userEntity.setEmailVerificationToken(emailVerificationToken);
		userEntity.setUserStatus(defaultUserStatus);
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userEntity.setUserId(generateRandomString.generateUserId(30));
		
		RoleEntity userRole = roleRepository.findByRoleName(user.getUserType());
		
		if(userRole == null) 
			throw new AppException("User RoleEntity not set.");
		
		userEntity.setRoles(Collections.singleton(userRole));
		//		userEntity.setUserType(userType);
		UserEntity storedUserDetailsEntity = userRepository.save(userEntity);
		
		String mailSubject = "ePharmacy account Verification";
		String mailBody = "<b>Verify your ePharmacy Account</b><br><br> Follow this link --> <a href='" + applicationHostDomain + "/verifyaccount?verificationToken=" + emailVerificationToken + "'><b><i>Click me to Verify</i></b></a><br><br> <span style='color:red; font-size:12px;'> Don't reply to this email !</span>";
		SendEmailRequestModel sendMail = new SendEmailRequestModel();
		sendMail.setToAddress(storedUserDetailsEntity.getEmail());
		sendMail.setSubject(mailSubject);
		sendMail.setBody(mailBody);
		sendMailComponent.sendMail(sendMail);
		
		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(storedUserDetailsEntity, returnValue);
		returnValue.setCompanyId(returnCompanyId);
		
		return returnValue;
		
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByEmail(email);
		if(userEntity == null) throw new UsernameNotFoundException(email);
		
		return new User(userEntity.getEmail(),userEntity.getEncryptedPassword(), new ArrayList<>());
	}

	@Override
	public UserDto getuser(String email) {
		
		UserEntity userEntity = userRepository.findByEmail(email);
		if(userEntity == null) throw new AppException("User not found");
		
		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(userEntity, returnValue); 
		return returnValue;
	}

	@Override
	public UserDto getUserByUserId(String UserId) {
			
		UserDto returnValue = new UserDto();
		UserEntity userEntity = userRepository.findByUserId(UserId);
		
		if(userEntity == null) throw new AppException("User not found");
		
		BeanUtils.copyProperties(userEntity, returnValue); 
		return returnValue;
	}

	@Override
	public UserDto updateUser(String userId, UserDto userDto) {
		
		UserDto returnValue = new UserDto();
		UserEntity userEntity = userRepository.findByUserId(userId);
		
		if(userEntity == null) 
			throw new RuntimeException("User not found.");
		
		userEntity.setFirstName(userDto.getFirstName());
		userEntity.setLastName(userDto.getLastName());
		userEntity.setGrandFatherName(userDto.getGrandFatherName());
		userEntity.setEmail(userDto.getEmail());
		userEntity.setMobilePhone(userDto.getMobilePhone());
		userEntity.setUserType(userDto.getUserType());
		
		//			BeanUtils.copyProperties(userDto, userEntity);
		
		UserEntity updatesUserDetailsEntity = userRepository.save(userEntity);
		
		BeanUtils.copyProperties(updatesUserDetailsEntity, returnValue); 
		return returnValue;
	}
	
	@Override
	public UserDto updateUserStatus(String userId, UserDto userDto) {
		UserDto returnValue = new UserDto();
		UserEntity userEntity = userRepository.findByUserId(userId);
		
		if(userEntity == null) 
			throw new RuntimeException("User not found.");
		
		userEntity.setUserStatus(userDto.getUserStatus());		
		UserEntity updatesUserDetailsEntity = userRepository.save(userEntity);
		
		BeanUtils.copyProperties(updatesUserDetailsEntity, returnValue); 
		return returnValue;
	}
	
	@Override
	public void deleteUser(String userId) {
		UserEntity userEntity = userRepository.findByUserId(userId);
		
		if(userEntity == null) 
			throw new AppException("User not found");
		
		userRepository.delete(userEntity);
	}

	@Override
	public List<UserDto> getUsers(int page, int limit) {
		 
	    List<UserDto> returnValue = new ArrayList<>();
	    
	    if(page > 0) page = page - 1; 
	   
	    Pageable pageableRequest = PageRequest.of(page, limit, Sort.by("id").descending());
	    
	    Page<UserEntity> usersPage = userRepository.findByUserTypeNot("Customer",pageableRequest);
	    int totalPages = usersPage.getTotalPages();
	    List<UserEntity> users = usersPage.getContent();
	    for(UserEntity userEntity : users) {
	    	UserDto userDto = new UserDto(); 
	    	BeanUtils.copyProperties(userEntity, userDto);
	    	if(returnValue.size() == 0) {
	    		userDto.setTotalPages(totalPages);
	    	}
	    	returnValue.add(userDto);
	    }
	    
		return returnValue;
	}

	@Override
	public String checkEmail(String email) {
		if(userRepository.findByEmail(email) == null) {
			return "Email doesn't exist";
		}else {
			return "Email exists";
		}
	}

	@Override
	public List<UserDto> searchUsers(SearchRequestModel searchkeyDetail, int page, int limit) {
		
		String searchKey = searchkeyDetail.getSearchKey();
		List<UserDto> returnValue = new ArrayList<>();
	    
	    if(page > 0) page = page - 1; 
	    Pageable pageableRequest = PageRequest.of(page, limit);
	    int countSpaces = StringUtils.countOccurrencesOf(searchKey, " ");
	    Page<UserEntity> usersPage = null;
	    
	    String[] searchKeyArray = searchKey.split(" ");
	    if(countSpaces == 0) {
	    	usersPage = userRepository.findByFirstNameContainingOrLastNameContainingOrGrandFatherNameContainingOrMobilePhoneContainingOrEmailContainingOrUserStatusContaining(searchKey,searchKey,searchKey,searchKey,searchKey,searchKey,pageableRequest);
	    }
	    else if(countSpaces == 1){
	    	String firstName = searchKeyArray[0];
	    	String lastName = searchKeyArray[1];
	    	usersPage = userRepository.findByFirstNameContainingAndLastNameContaining(firstName,lastName,pageableRequest);
	    }
	    else if(countSpaces == 2) {
	    	String firstName = searchKeyArray[0];
	    	String lastName = searchKeyArray[1];
	    	String grandFatherName = searchKeyArray[2];
	    	usersPage = userRepository.findByFirstNameContainingAndLastNameContainingAndGrandFatherNameContaining(firstName,lastName,grandFatherName,pageableRequest);
	    }
	    //Page<UserEntity> usersPage = userRepository.findAll(pageableRequest);
	    int totalPages = usersPage.getTotalPages();
	    List<UserEntity> users = usersPage.getContent();
	    for(UserEntity userEntity : users) {
	    	UserDto userDto = new UserDto(); 
	    	BeanUtils.copyProperties(userEntity, userDto);
	    	if(returnValue.size() == 0) {
	    		userDto.setTotalPages(totalPages);
	    	}
	    	returnValue.add(userDto);
	    }
	    
		return returnValue;
	}

	@Override
	public String uploadProfilePicture(UploadProfileRequestModel requestDetail) throws IOException {
		
//		String rootDirectory = new File("").getAbsolutePath();

		String rootDirectory = "/var/www/html/images";
		
		String uploadDir = rootDirectory + "/profiles/";
		File directory = new File(uploadDir);
	    if (!directory.exists()){
	        directory.mkdirs();
	    }
		
		String returnValue = "Image not Saved";
		byte[] bytes = requestDetail.getProfilePicture().getBytes();
		
		String userId = requestDetail.getUserId();
		String fileName = requestDetail.getProfilePicture().getOriginalFilename();
		String extention = fileName.substring(fileName.lastIndexOf(".") + 1);
		String newFileName = userId + "." +  extention;
		Path path = Paths.get(uploadDir + newFileName);
	    Files.write(path, bytes);
	    
	    UserEntity userEntity = userRepository.findByUserId(userId);
		
		if(userEntity == null) 
			throw new AppException("User not found.");
		
		userEntity.setProfilePicture(newFileName);	
		UserEntity updatesUserDetailsEntity = userRepository.save(userEntity);
		if(updatesUserDetailsEntity.getProfilePicture() != null) {
			returnValue = "Profile picture Saved";
		}
		
		return returnValue;
		
	}

	@Override
	public String resetPassword(ResetPasswordRequestModel resetPasswordDetail) {
		
		String returnValue = "Password not changed";
		UserEntity userEntity = userRepository.findByEmailAndPasswordResetCode(resetPasswordDetail.getEmail(),resetPasswordDetail.getResetCode());
		if(userEntity == null) throw new AppException("Password Can't be changed");
		
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(resetPasswordDetail.getNewPassword()));
		userEntity.setPasswordResetCode(generateRandomString.generateAccountId(6));
		UserEntity passworUpdated = userRepository.save(userEntity);
		if(passworUpdated != null) {
			returnValue = "Password changed successfully";
		}
		return returnValue;
	}

	@Override
	public String changeAccountPassword(ResetPasswordRequestModel changePassRequest) {
		
		UserEntity userEntity = userRepository.findByEmail(changePassRequest.getEmail());
		
		if(userEntity == null) 
			throw new RuntimeException("User not found.");
		
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(changePassRequest.getNewPassword()));
		userRepository.save(userEntity);
		
		return "Password changed successfully";
	}

	@Override
	public List<UserDto> getCustomers(int page, int limit) {
		
		List<UserDto> returnValue = new ArrayList<>();
	    
	    if(page > 0) page = page - 1; 
	   
	    Pageable pageableRequest = PageRequest.of(page, limit, Sort.by("id").descending());
	    
	    Page<UserEntity> usersPage = userRepository.findByUserType("Customer",pageableRequest);
	    int totalPages = usersPage.getTotalPages();
	    List<UserEntity> users = usersPage.getContent();
	    for(UserEntity userEntity : users) {
	    	UserDto userDto = new UserDto(); 
	    	BeanUtils.copyProperties(userEntity, userDto);
	    	if(returnValue.size() == 0) {
	    		userDto.setTotalPages(totalPages);
	    	}
	    	returnValue.add(userDto);
	    }
	    
		return returnValue;
	}

}

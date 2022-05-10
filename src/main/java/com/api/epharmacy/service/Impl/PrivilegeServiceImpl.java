package com.api.epharmacy.service.Impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.epharmacy.exception.AppException;
import com.api.epharmacy.io.entity.PrivilegeEntity;
import com.api.epharmacy.io.entity.RolePrivilegeEntity;
import com.api.epharmacy.io.entity.UserEntity;
import com.api.epharmacy.io.repositories.PrivilegeRepository;
import com.api.epharmacy.io.repositories.RolePrivilegeRepository;
import com.api.epharmacy.io.repositories.UserRepository;
import com.api.epharmacy.service.PrivilegeService;
import com.api.epharmacy.ui.model.request.PrivilegeRequestModel;
import com.api.epharmacy.ui.model.request.RolePrivilegeRequestModel;
import com.api.epharmacy.ui.model.response.PrivilegeResponseModel;
import com.api.epharmacy.ui.model.response.RolePrivilegesResponseModel;

@Service
@Transactional
public class PrivilegeServiceImpl implements PrivilegeService {

	@Autowired
	PrivilegeRepository privilegeRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RolePrivilegeRepository rolePrivilegeRepository;

	@Override
	public PrivilegeResponseModel savePrivilege(PrivilegeRequestModel privilegeDetail) {
		// TODO Auto-generated method stub
		List<PrivilegeEntity> privilegeEnti = null;
		PrivilegeEntity savedPrivilegeEntity = null;
		PrivilegeResponseModel returnValue = new PrivilegeResponseModel();
		privilegeEnti = privilegeRepository.findByPrivilegeUrlAndMethodAndIsDeleted(privilegeDetail.getPrivilegeUrl(),
				privilegeDetail.getMethod(), false);

		if (privilegeEnti.size() > 0)
			throw new AppException("Privilege already exists with this Method.");
		privilegeEnti = privilegeRepository.findByPrivilegeName(privilegeDetail.getPrivilegeName());
		if (privilegeEnti.size() > 0) {
			PrivilegeEntity privilegeEntity1 = privilegeEnti.get(privilegeEnti.size() - 1);
			if (privilegeEntity1.isDeleted() == true) {

				privilegeEntity1.setDeleted(false);
				savedPrivilegeEntity = privilegeRepository.save(privilegeEntity1);
			} else {
				throw new AppException("Privilege Already Exists With This Name.");
			}
		} else {
			PrivilegeEntity privilegeEntity = new PrivilegeEntity();
			BeanUtils.copyProperties(privilegeDetail, privilegeEntity);
			savedPrivilegeEntity = privilegeRepository.save(privilegeEntity);
		}
		BeanUtils.copyProperties(savedPrivilegeEntity, returnValue);
		return returnValue;
	}

	@Override
	public PrivilegeResponseModel getPrivilege(Integer privilegeId) {
		// TODO Auto-generated method stub
		PrivilegeResponseModel returnValue = new PrivilegeResponseModel();
		PrivilegeEntity privilegeEntity = privilegeRepository.findByPrivilegeIdAndIsDeleted(privilegeId, false);
		if (privilegeEntity == null)
			throw new AppException("No Privilege with this Id");
		BeanUtils.copyProperties(privilegeEntity, returnValue);
		return returnValue;
	}

	@Override
	public PrivilegeResponseModel updatePrivilege(PrivilegeRequestModel privilegeDetail, Integer privilegeId) {
		PrivilegeResponseModel returnValue = new PrivilegeResponseModel();
		PrivilegeEntity privilegeEntity = privilegeRepository.findByPrivilegeIdAndIsDeleted(privilegeId, false);
		if (privilegeEntity == null)
			throw new AppException("No Privilege with this Id");
		BeanUtils.copyProperties(privilegeDetail, privilegeEntity);
		PrivilegeEntity savedPrivilegeEntity = privilegeRepository.save(privilegeEntity);
		BeanUtils.copyProperties(savedPrivilegeEntity, returnValue);
		return returnValue;
	}

	@Override
	public List<PrivilegeResponseModel> getAllPrivileges(int page, int limit, String searchKey, Long roleId) {
		List<PrivilegeResponseModel> returnValue = new ArrayList<>();

		if (page > 0)
			page = page - 1;

//		Pageable pageableRequest = PageRequest.of(page, limit);
		List<PrivilegeEntity> agentPage = null;

		if ("".equals(searchKey))
			agentPage = privilegeRepository.findByIsDeleted(false);// .findAll(pageableRequest);
		else
			agentPage = privilegeRepository
					.findByPrivilegeNameContainingOrPrivilegeUrlContainingOrPrivilegeDescriptionContainingAndIsDeleted(
							searchKey, searchKey, searchKey, false);// .findAll(pageableRequest);

//		List<PrivilegeEntity> privileges = agentPage.getContent();
//
//		int totalPages = agentPage.getTotalPages();
		for (PrivilegeEntity privilegeEntity : agentPage) {

			PrivilegeResponseModel privilegeResponseModel = new PrivilegeResponseModel();
			BeanUtils.copyProperties(privilegeEntity, privilegeResponseModel);
			List<RolePrivilegeEntity> rolePrivilegeEntity = rolePrivilegeRepository
					.findByPrivilegeIdAndRoleIdAndIsDeleted(privilegeEntity.getPrivilegeId(), roleId, false);
			if(rolePrivilegeEntity.size()>0) {
			for (RolePrivilegeEntity rolePrivilegeEntity2 : rolePrivilegeEntity) {
				if (rolePrivilegeEntity2 != null) {
					if (rolePrivilegeEntity2.isPrivilaged() == true)
						privilegeResponseModel.setPrivileged(true);
					else if(rolePrivilegeEntity2.isPrivilaged()==false)
						privilegeResponseModel.setPrivileged(false);
					

				}
			}
			}
//			else {
//				privilegeResponseModel.setPrivileged(false);
//			}

			UserEntity userEntity = userRepository.findByUserId(privilegeEntity.getCreatedBy());
			if (userEntity != null)
				privilegeResponseModel.setCreatedBy(userEntity.getFirstName() + " " + userEntity.getLastName());
			else
				privilegeResponseModel.setCreatedBy("");

			returnValue.add(privilegeResponseModel);
		}
		return returnValue;
	}

	@Override
	public String deletePrivilege(Integer privilegeId) {
		// TODO Auto-generated method stub
		PrivilegeEntity privilegeEntity = privilegeRepository.findByPrivilegeIdAndIsDeleted(privilegeId, false);
		if (privilegeEntity == null)
			throw new AppException("No Privilege with this Id");
		privilegeEntity.setDeleted(true);
		privilegeRepository.save(privilegeEntity);
//		return "Privilege Deleted!";
		return "Privilege Deleted!";
	}

	@Override
	public String saveRolePrivilegeData(RolePrivilegeRequestModel rolePrivilegeDetail) {
		// TODO Auto-generated method stub

		String returnValue;
		RolePrivilegeEntity rolePrivilegeEntity1 = rolePrivilegeRepository.findByRoleIdAndPrivilegeIdAndIsDeleted(
				rolePrivilegeDetail.getRoleId(), rolePrivilegeDetail.getPrivilegeId(), false);
		if (rolePrivilegeEntity1 == null) {
			RolePrivilegeEntity rolePrivilegeEntity = new RolePrivilegeEntity();
			BeanUtils.copyProperties(rolePrivilegeDetail, rolePrivilegeEntity);
			if (rolePrivilegeDetail.getIsPrivileged().equals("true"))
				rolePrivilegeEntity.setPrivilaged(true);
			else if (rolePrivilegeDetail.getIsPrivileged().equals("false"))
				rolePrivilegeEntity.setPrivilaged(false);

			rolePrivilegeRepository.save(rolePrivilegeEntity);
			returnValue = "Data Added!";
		}

		else {
			BeanUtils.copyProperties(rolePrivilegeDetail, rolePrivilegeEntity1);
			if (rolePrivilegeDetail.getIsPrivileged().equals("true"))
				rolePrivilegeEntity1.setPrivilaged(true);
			else if (rolePrivilegeDetail.getIsPrivileged().equals("false"))
				rolePrivilegeEntity1.setPrivilaged(false);
			rolePrivilegeRepository.save(rolePrivilegeEntity1);
			returnValue = "Data Updated!";
//			if(rolePrivilegeEntity1.isPrivilaged()==true) {
//			rolePrivilegeEntity1.setPrivilaged(false);
//			rolePrivilegeRepository.save(rolePrivilegeEntity1);
//			}
//			else {
//				rolePrivilegeEntity1.setPrivilaged(true);
//				rolePrivilegeRepository.save(rolePrivilegeEntity1);
//			}

		}

		return returnValue;
	}

	@Override
	public String updateRolePrivilegeData(RolePrivilegeRequestModel rolePrivilegeDetail, Long rolePrivilegeId) {
		// TODO Auto-generated method stub
		RolePrivilegeEntity rolePrivilegeEntity = rolePrivilegeRepository
				.findByRolePrivilegeIdAndIsDeleted(rolePrivilegeId, false);
		BeanUtils.copyProperties(rolePrivilegeDetail, rolePrivilegeEntity);
		rolePrivilegeRepository.save(rolePrivilegeEntity);
		return "Data Updated!";
	}

	@Override
	public String deleteRolePrivilageData(Long rolePrivilegeId) {
		// TODO Auto-generated method stub

		RolePrivilegeEntity rolePrivilegeEntity = rolePrivilegeRepository
				.findByRolePrivilegeIdAndIsDeleted(rolePrivilegeId, false);
		if (rolePrivilegeEntity == null)
			throw new AppException("role privilage relation with this id not exists");

		rolePrivilegeEntity.setDeleted(true);
		rolePrivilegeRepository.save(rolePrivilegeEntity);
		return "Data deleted";

	}

	@Override
	public List<RolePrivilegesResponseModel> getRolePrivileges(Long roleId) {

//		List<RolePrivilegesResponseModel> returnValue = new ArrayList<>();
//
//		if (page > 0)
//			page = page - 1;
//
//		Pageable pageableRequest = PageRequest.of(page, limit);
//		Page<PrivilegeEntity> privilegePage = null;
//
//		if ("".equals(searchKey))
//			privilegePage = privilegeRepository.findByIsDeleted(false,pageableRequest);// .findAll(pageableRequest);
//		else
//			privilegePage = privilegeRepository.findByPrivilegeNameContainingOrPrivilegeUrlContainingAndIsDeleted(searchKey,
//					searchKey,false,pageableRequest);// .findAll(pageableRequest);
//
//		List<PrivilegeEntity> privileges = privilegePage.getContent();
//
//		int totalPages = privilegePage.getTotalPages();
//		for (PrivilegeEntity privilegeEntity : privileges) {
//
//			RolePrivilegesResponseModel rolePrivilegesResponseModel = new RolePrivilegesResponseModel();
//			List<RolePrivilegeEntity> rolePrivilegeEntities = rolePrivilegeRepository.findByRoleIdAndIsDeleted(roleId, false);
//			if(rolePrivilegeEntities.stream().anyMatch(item -> item.getPrivilegeId() == privilegeEntity.getPrivilegeId()))
//				rolePrivilegesResponseModel.setAllowed(true);
//			else
//				rolePrivilegesResponseModel.setAllowed(false);
//			
//			BeanUtils.copyProperties(privilegeEntity, rolePrivilegesResponseModel);
//				
//			
//			UserEntity userEntity = userRepository.findByUserId(privilegeEntity.getCreatedBy());
//			
//			if (returnValue.size() == 0) {
//				rolePrivilegesResponseModel.setTotalPage(totalPages);
//			}
//
//			returnValue.add(rolePrivilegesResponseModel);
//		}
//		
//	  return returnValue;

		List<RolePrivilegesResponseModel> returnValue = new ArrayList<>();

		List<RolePrivilegeEntity> rolePrivilegePage = null;

		rolePrivilegePage = rolePrivilegeRepository.findByRoleIdAndIsDeleted(roleId, false);// .findAll(pageableRequest);

		for (RolePrivilegeEntity rolePrivilegeEntity : rolePrivilegePage) {

			RolePrivilegesResponseModel rolePrivilegesResponseModel = new RolePrivilegesResponseModel();
//			List<RolePrivilegeEntity> rolePrivilegeEntities = rolePrivilegeRepository.findByRoleIdAndIsDeleted(roleId, false);
//			if(rolePrivilegeEntities.stream().anyMatch(item -> item.getPrivilegeId() == privilegeEntity.getPrivilegeId()))
			if (rolePrivilegeEntity.isPrivilaged() == true)
				rolePrivilegesResponseModel.setAllowed(true);
			else
				rolePrivilegesResponseModel.setAllowed(false);
			PrivilegeEntity privilegeEntity = privilegeRepository
					.findByPrivilegeIdAndIsDeleted(rolePrivilegeEntity.getPrivilegeId(), false);
			if (privilegeEntity != null) {
				rolePrivilegesResponseModel.setPrivilegeName(privilegeEntity.getPrivilegeName());
				rolePrivilegesResponseModel.setPrivilegeDescription(privilegeEntity.getPrivilegeDescription());
				rolePrivilegesResponseModel.setMethod(privilegeEntity.getMethod());
				rolePrivilegesResponseModel.setPrivilegeUrl(privilegeEntity.getPrivilegeUrl());
				rolePrivilegesResponseModel.setPrivilegeId(privilegeEntity.getPrivilegeId());
			}

			BeanUtils.copyProperties(rolePrivilegeEntity, rolePrivilegesResponseModel);

			UserEntity userEntity = userRepository.findByUserId(rolePrivilegeEntity.getCreatedBy());
			if (userEntity != null)
				rolePrivilegesResponseModel.setCreatedBy(userEntity.getFirstName() + " " + userEntity.getLastName());
			else
				rolePrivilegesResponseModel.setCreatedBy("");

			returnValue.add(rolePrivilegesResponseModel);
		}

		return returnValue;

	}

}

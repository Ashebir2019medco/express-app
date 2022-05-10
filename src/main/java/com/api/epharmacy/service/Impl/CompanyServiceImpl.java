package com.api.epharmacy.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.api.epharmacy.io.entity.CompanyEntity;
import com.api.epharmacy.io.entity.RegionEntity;
import com.api.epharmacy.io.repositories.CompanyRepository;
import com.api.epharmacy.io.repositories.RegionRepository;
import com.api.epharmacy.service.CompanyService;
import com.api.epharmacy.shared.dto.UserDto;
import com.api.epharmacy.ui.model.request.SearchRequestModel;

@Service
public class CompanyServiceImpl implements CompanyService {
	
	@Autowired
	CompanyRepository companyRepository;
	
	@Autowired
	RegionRepository regionRepository;
	
	@Override
	public UserDto getCompanyById(long companyId) {
		
		UserDto returnValue = new UserDto();
		CompanyEntity companyEntity = companyRepository.findByCompanyId(companyId);
		
		if(companyEntity == null) throw new RuntimeException("Company not found.");
		
		RegionEntity regionEntity = regionRepository.findByRegionIdAndIsDeleted(companyEntity.getRegionId(), false);
		if (regionEntity != null)
			returnValue.setRegion(regionEntity.getName());
		
		BeanUtils.copyProperties(companyEntity, returnValue); 
		return returnValue;
	}

	@Override
	public List<UserDto> getCompanies(int page, int limit) {
		
		List<UserDto> returnValue = new ArrayList<>();
	    
	    if(page > 0) page = page - 1;
	   
	    Pageable pageableRequest = PageRequest.of(page, limit,Sort.by("companyId").descending());
	    
	    Page<CompanyEntity> companiesPage = companyRepository.findAll(pageableRequest);
	    int totalPages = companiesPage.getTotalPages();
	    List<CompanyEntity> companies = companiesPage.getContent();
	    for(CompanyEntity companyEntity : companies) {
			UserDto userDto = new UserDto();
			
			RegionEntity regionEntity = regionRepository.findByRegionIdAndIsDeleted(companyEntity.getRegionId(), false);
			if (regionEntity != null)
				userDto.setRegion(regionEntity.getName());
			
	    	BeanUtils.copyProperties(companyEntity, userDto);
	    	if(returnValue.size() == 0) {
	    		userDto.setTotalPages(totalPages);
	    	}
	    	returnValue.add(userDto);
	    }
	    
		return returnValue;
	}

	@Override
	public UserDto updateCompany(long companyId, UserDto userDto) {
		UserDto returnValue = new UserDto();
		CompanyEntity companyEntity = companyRepository.findByCompanyId(companyId);
		
		if(companyEntity == null) 
			throw new RuntimeException("Company not found.");
		
		companyEntity.setCompanyName(userDto.getCompanyName());
		companyEntity.setLicenceNumber(userDto.getLicenceNumber());
		companyEntity.setTinNumber(userDto.getTinNumber());
		
		CompanyEntity updatesCompanyEntity = companyRepository.save(companyEntity);
		
		BeanUtils.copyProperties(updatesCompanyEntity, returnValue); 
		return returnValue;
	}

	@Override
	public List<UserDto> searchCompanies(SearchRequestModel searchkeyDetails, int page, int limit) {
		
		String searchKey = searchkeyDetails.getSearchKey();
		List<UserDto> returnValue = new ArrayList<>();
	    
	    if(page > 0) page = page - 1;
	   
	    Pageable pageableRequest = PageRequest.of(page, limit);
	    
	    Page<CompanyEntity> companiesPage = companyRepository.findByCompanyNameContainingOrLicenceNumberContainingOrTinNumberContainingOrAccountIdContainingOrCompanyStatusContaining(searchKey,searchKey,searchKey,searchKey,searchKey,pageableRequest);;
	    int totalPages = companiesPage.getTotalPages();
	    List<CompanyEntity> companies = companiesPage.getContent();
	    for(CompanyEntity companyEntity : companies) {
	    	UserDto userDto = new UserDto(); 
			
			RegionEntity regionEntity = regionRepository.findByRegionIdAndIsDeleted(companyEntity.getRegionId(), false);
			if (regionEntity != null)
				userDto.setRegion(regionEntity.getName());
			
	    	BeanUtils.copyProperties(companyEntity, userDto);
	    	if(returnValue.size() == 0) {
	    		userDto.setTotalPages(totalPages);
	    	}
	    	returnValue.add(userDto);
	    }
	    
		return returnValue;
	}

}

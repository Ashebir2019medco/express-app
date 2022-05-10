package com.api.epharmacy.service.Impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.api.epharmacy.exception.AppException;
import com.api.epharmacy.io.entity.CountryEntity;
import com.api.epharmacy.io.entity.RegionEntity;
import com.api.epharmacy.io.repositories.CountryRepository;
import com.api.epharmacy.io.repositories.RegionRepository;
import com.api.epharmacy.service.RegionService;
import com.api.epharmacy.ui.model.request.RegionRequestModel;
import com.api.epharmacy.ui.model.response.RegionResponseModel;

@Service
@Transactional
public class RegionServiceImpl implements RegionService {
	
	@Autowired
	RegionRepository regionRepository;
	
	@Autowired
	CountryRepository countryRepository;

	@Override
	public RegionResponseModel saveRegion(RegionRequestModel regionDetail) {
		RegionResponseModel returnValue = new RegionResponseModel();
		RegionEntity regionEntity = new RegionEntity();
		BeanUtils.copyProperties(regionDetail, regionEntity);
		
		 List<RegionEntity> regions = regionRepository.findByCountryIdAndNameAndIsDeleted(regionDetail.getCountryId(), regionDetail.getName(),false);
			
			if(regions.size()>0)
		    	throw new AppException("Region already exists");
		CountryEntity countryEntity = countryRepository.findByCountryIdAndIsDeleted(regionDetail.getCountryId(), false);

		if(countryEntity==null)
			throw new AppException("the Country is not availlable");
		RegionEntity savedRegionEntity = regionRepository.save(regionEntity);
		BeanUtils.copyProperties(savedRegionEntity, returnValue);
		return returnValue;
	}

	@Override
	public List<RegionResponseModel> getAllRegions(String searchKey, int page, int limit) {
		List<RegionResponseModel> returnValue = new ArrayList<>();
	    
	    if(page > 0) page = page - 1; 
	   
	    Pageable pageableRequest = PageRequest.of(page, limit,Sort.by("name").ascending());	    Page<RegionEntity> regionPage = null;
	    
	    if("".equals(searchKey))
	    	regionPage = regionRepository.findByIsDeleted(false, pageableRequest);//.findAll(pageableRequest);
	    else
	    	regionPage = regionRepository.findByIsDeletedAndNameContaining(false,searchKey, pageableRequest);//.findAll(pageableRequest);
	    
	    List<RegionEntity> regions = regionPage.getContent();
	    
	    
	    
	    int totalPages = regionPage.getTotalPages();	    
	    for(RegionEntity regionEntity : regions) {
	    	
	    	RegionResponseModel regionResponseModel = new RegionResponseModel(); 
	    	BeanUtils.copyProperties(regionEntity, regionResponseModel);
	    	
//	    	UserEntity userEntity= userRepository.findByUserId(regionEntity.getCreatedBy());
//			if(userEntity!=null)
//				regionResponseModel.setCreatedBy(userEntity.getFirstName()+" "+userEntity.getLastName());
//	    	else
//	    		regionResponseModel.setCreatedBy("");
			
	    	if(returnValue.size() == 0) {
	    		regionResponseModel.setTotalPage(totalPages);
	    	}
	    	CountryEntity countryEntity = countryRepository.findByCountryIdAndIsDeleted(regionEntity.getCountryId(), false);
	    	if(countryEntity!=null)
	    		regionResponseModel.setCountryName(countryEntity.getName());
	    	returnValue.add(regionResponseModel);
	    }
	return returnValue;
	}

	@Override
	public RegionResponseModel getRegion(Integer regionId) {
		RegionResponseModel returnValue = new RegionResponseModel();
		RegionEntity regionEntity = regionRepository.findByRegionIdAndIsDeleted(regionId,false);
		if(regionEntity == null)
			throw new AppException("No region with this id");
		CountryEntity countryEntity = countryRepository.findByCountryIdAndIsDeleted(regionEntity.getCountryId(), false);
		BeanUtils.copyProperties(regionEntity, returnValue);
		if(countryEntity!=null)
			returnValue.setCountryName(countryEntity.getName());
		return returnValue;
	}

	@Override
	public RegionResponseModel updateRegion(Integer regionId, RegionRequestModel regionDetail) {
		RegionResponseModel returnValue = new RegionResponseModel();
		RegionEntity regionEntity = regionRepository.findByRegionIdAndIsDeleted(regionId, false);
		
		CountryEntity countryEntity = countryRepository.findByCountryIdAndIsDeleted(regionDetail.getCountryId(), false);
		if(countryEntity==null)
			throw new AppException("the Country is not availlable");
		BeanUtils.copyProperties(regionDetail, regionEntity);
		RegionEntity savedRegion = regionRepository.save(regionEntity);
		BeanUtils.copyProperties(savedRegion, returnValue);
		return returnValue;
	}

	@Override
	public String deleteRegion(Integer regionId) {
		RegionEntity regionEntity = regionRepository.findByRegionIdAndIsDeleted(regionId,false);
		if(regionEntity == null)
			throw new AppException("Invalid region");
		regionEntity.setDeleted(true);
		regionRepository.save(regionEntity);
		return "Region Deleted";
	}
}

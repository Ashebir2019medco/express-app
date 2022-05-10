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
import com.api.epharmacy.io.entity.ZoneEntity;
import com.api.epharmacy.io.repositories.CountryRepository;
import com.api.epharmacy.io.repositories.RegionRepository;
import com.api.epharmacy.io.repositories.ZoneRepository;
import com.api.epharmacy.service.ZoneService;
import com.api.epharmacy.ui.model.request.ZoneRequestModel;
import com.api.epharmacy.ui.model.response.ZoneResponseModel;

@Service
@Transactional
public class ZoneServiceImpl implements ZoneService {

	@Autowired
	RegionRepository regionRepository;
	
	@Autowired
	CountryRepository countryRepository;
	
	@Autowired
	ZoneRepository zoneRepository;
	@Override
	public ZoneResponseModel saveZone(ZoneRequestModel zoneDetail) {
		ZoneResponseModel returnValue = new ZoneResponseModel();
		ZoneEntity zoneEntity = new ZoneEntity();
		BeanUtils.copyProperties(zoneDetail, zoneEntity);
		
		  List<ZoneEntity> zones = zoneRepository.findByRegionIdAndNameAndIsDeleted(zoneDetail.getRegionId(), zoneDetail.getName(),false);
			
			if(zones.size()>0)
		    	throw new AppException("Zone already exists");
		
		CountryEntity countryEntity = countryRepository.findByCountryIdAndIsDeleted(zoneDetail.getCountryId(), false);
		RegionEntity regionEntity = regionRepository.findByRegionIdAndIsDeleted(zoneDetail.getRegionId(), false);
	
		if(countryEntity==null)
			throw new AppException("the Country is not availlable");
		if(regionEntity==null)
			throw new AppException("the Region is not availlable");
		ZoneEntity savedZoneEntity = zoneRepository.save(zoneEntity);
		BeanUtils.copyProperties(savedZoneEntity, returnValue);
		return returnValue;
	}

	@Override
	public List<ZoneResponseModel> getAllZones(String searchKey, int page, int limit) {
        List<ZoneResponseModel> returnValue = new ArrayList<>();
	    
	    if(page > 0) page = page - 1; 
	   
	    Pageable pageableRequest = PageRequest.of(page, limit,Sort.by("zoneId").descending());
	    Page<ZoneEntity> zonePage = null;
	    
	    if("".equals(searchKey))
	    	zonePage = zoneRepository.findByIsDeleted(false, pageableRequest);//.findAll(pageableRequest);
	    else
	    	zonePage = zoneRepository.findByIsDeletedAndNameContaining(false,searchKey, pageableRequest);//.findAll(pageableRequest);
	    
	    List<ZoneEntity> zones = zonePage.getContent();
	    
	    
	    
	    int totalPages = zonePage.getTotalPages();	    
	    for(ZoneEntity zoneEntity : zones) {
	    	
	    	ZoneResponseModel zoneResponseModel = new ZoneResponseModel(); 
	    	BeanUtils.copyProperties(zoneEntity, zoneResponseModel);
	    	
//	    	UserEntity userEntity= userRepository.findByUserId(zoneEntity.getCreatedBy());
//			if(userEntity!=null)
//				zoneResponseModel.setCreatedBy(userEntity.getFirstName()+" "+userEntity.getLastName());
//	    	else
//	    		zoneResponseModel.setCreatedBy("");
			
	    	if(returnValue.size() == 0) {
	    		zoneResponseModel.setTotalPage(totalPages);
	    	}
	    	CountryEntity countryEntity = countryRepository.findByCountryIdAndIsDeleted(zoneEntity.getCountryId(), false);
	    	RegionEntity regionEntity = regionRepository.findByRegionIdAndIsDeleted(zoneEntity.getRegionId(), false);
	    	if(countryEntity!=null)
	    		zoneResponseModel.setCountryName(countryEntity.getName());
	    	if(regionEntity!=null)
	    		zoneResponseModel.setRegionName(regionEntity.getName());
	    	returnValue.add(zoneResponseModel);
	    }
	  return returnValue;
	}

	@Override
	public ZoneResponseModel getZone(Integer zoneId) {
		ZoneResponseModel returnValue = new ZoneResponseModel();
		ZoneEntity zoneEntity = zoneRepository.findByZoneIdAndIsDeleted(zoneId,false);
		if(zoneEntity == null)
			throw new AppException("No region with this id");
		CountryEntity countryEntity = countryRepository.findByCountryIdAndIsDeleted(zoneEntity.getCountryId(), false);
		RegionEntity regionEntity = regionRepository.findByRegionIdAndIsDeleted(zoneEntity.getRegionId(), false);
		BeanUtils.copyProperties(zoneEntity, returnValue);
		if(countryEntity!=null)
			returnValue.setCountryName(countryEntity.getName());
		if(regionEntity!=null)
			returnValue.setRegionName(regionEntity.getName());
		return returnValue;
	}

	@Override
	public ZoneResponseModel updateZone(Integer zoneId, ZoneRequestModel zoneDetail) {
		ZoneResponseModel returnValue = new ZoneResponseModel();
		ZoneEntity zoneEntity = zoneRepository.findByZoneIdAndIsDeleted(zoneId, false);
		BeanUtils.copyProperties(zoneDetail, zoneEntity);
		ZoneEntity savedZone = zoneRepository.save(zoneEntity);
		BeanUtils.copyProperties(savedZone, returnValue);
		return returnValue;
	}

	@Override
	public String deleteZone(Integer zoneId) {
		ZoneEntity zoneEntity = zoneRepository.findByZoneIdAndIsDeleted(zoneId,false);
		if(zoneEntity == null)
			throw new AppException("Invalid zone");
		zoneEntity.setDeleted(true);
		zoneRepository.save(zoneEntity);
		return "zone Deleted";
	}

}

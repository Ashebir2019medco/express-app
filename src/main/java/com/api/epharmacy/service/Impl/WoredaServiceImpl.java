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
import com.api.epharmacy.io.entity.WoredaEntity;
import com.api.epharmacy.io.entity.ZoneEntity;
import com.api.epharmacy.io.repositories.CountryRepository;
import com.api.epharmacy.io.repositories.RegionRepository;
import com.api.epharmacy.io.repositories.WoredaRepository;
import com.api.epharmacy.io.repositories.ZoneRepository;
import com.api.epharmacy.service.WoredaService;
import com.api.epharmacy.ui.model.request.WoredaRequestModel;
import com.api.epharmacy.ui.model.response.WoredaResponseModel;

@Service
@Transactional
public class WoredaServiceImpl implements WoredaService {

	@Autowired
	RegionRepository regionRepository;
	
	
	@Autowired
	CountryRepository countryRepository;
	
	@Autowired
	ZoneRepository zoneRepository;
	
	@Autowired
	WoredaRepository woredaRepository;
	
	@Override
	public WoredaResponseModel saveWoreda(WoredaRequestModel woredaDetail) {
		WoredaResponseModel returnValue = new WoredaResponseModel();
		WoredaEntity woredaEntity = new WoredaEntity();
     
		BeanUtils.copyProperties(woredaDetail, woredaEntity);
		
		  List<WoredaEntity> woredas = woredaRepository.findByZoneIdAndNameAndIsDeleted(woredaDetail.getZoneId(), woredaDetail.getName(),false);
			
			if(woredas.size()>0)
				throw new AppException("woreda already exist");
		CountryEntity countryEntity = countryRepository.findByCountryIdAndIsDeleted(woredaDetail.getCountryId(), false);
		RegionEntity regionEntity = regionRepository.findByRegionIdAndIsDeleted(woredaDetail.getRegionId(), false);
		ZoneEntity zoneEntity = zoneRepository.findByZoneIdAndIsDeleted(woredaDetail.getZoneId(), false);
//		if(checkWoreda!=null)
//	    	throw new AppException("Woreda already exists");
		if(countryEntity==null)
			throw new AppException("the Country is not availlable");
		if(regionEntity==null)
			throw new AppException("the Region is not availlable");
		if(zoneEntity==null)
			throw new AppException("the Zone is not availlable");
		WoredaEntity savedWoredaEntity = woredaRepository.save(woredaEntity);
		BeanUtils.copyProperties(savedWoredaEntity, returnValue);
		return returnValue;
	}

	@Override
	public List<WoredaResponseModel> getAllWoreda(String searchKey, int page, int limit) {
		 List<WoredaResponseModel> returnValue = new ArrayList<>();
		    
		    if(page > 0) page = page - 1; 
		   
		    Pageable pageableRequest = PageRequest.of(page, limit,Sort.by("woredaId").descending());
		    Page<WoredaEntity> woredaPage = null;
		    
		    if("".equals(searchKey))
		    	woredaPage = woredaRepository.findByIsDeleted(false, pageableRequest);//.findAll(pageableRequest);
		    else
		    	woredaPage = woredaRepository.findByIsDeletedAndNameContaining(false,searchKey, pageableRequest);//.findAll(pageableRequest);
		    
		    List<WoredaEntity> woredas = woredaPage.getContent();
		    
		    
		    
		    int totalPages = woredaPage.getTotalPages();	    
		    for(WoredaEntity woredaEntity : woredas) {
		    	
		    	WoredaResponseModel woredaResponseModel = new WoredaResponseModel(); 
		    	BeanUtils.copyProperties(woredaEntity, woredaResponseModel);
		    	
//		    	UserEntity userEntity= userRepository.findByUserId(woredaEntity.getCreatedBy());
//				if(userEntity!=null)
//					woredaResponseModel.setCreatedBy(userEntity.getFirstName()+" "+userEntity.getLastName());
//		    	else
//		    		woredaResponseModel.setCreatedBy("");
				
		    	if(returnValue.size() == 0) {
		    		woredaResponseModel.setTotalPage(totalPages);
		    	}
		    	CountryEntity countryEntity = countryRepository.findByCountryIdAndIsDeleted(woredaEntity.getCountryId(), false);
		    	RegionEntity regionEntity = regionRepository.findByRegionIdAndIsDeleted(woredaEntity.getRegionId(), false);
		    	ZoneEntity zoneEntity = zoneRepository.findByZoneIdAndIsDeleted(woredaEntity.getZoneId(), false);
		    	if(countryEntity!=null)
		    		woredaResponseModel.setCountryName(countryEntity.getName());
		    	if(regionEntity!=null)
		    		woredaResponseModel.setRegionName(regionEntity.getName());
		    	if(zoneEntity!=null)
		    		woredaResponseModel.setZoneName(zoneEntity.getName());
		    	returnValue.add(woredaResponseModel);
		    }
		  return returnValue;
	}

	@Override
	public String deleteWoreda(Integer woredaId) {
		WoredaEntity woredaEntity = woredaRepository.findByWoredaIdAndIsDeleted(woredaId,false);
		if(woredaEntity == null)
			throw new AppException("Invalid woreda");
		woredaEntity.setDeleted(true);
		woredaRepository.save(woredaEntity);
		return "woreda Deleted";
	}

	@Override
	public WoredaResponseModel updateWoreda(Integer woredaId, WoredaRequestModel woredaDetail) {
		WoredaResponseModel returnValue = new WoredaResponseModel();
		WoredaEntity woredaEntity = woredaRepository.findByWoredaIdAndIsDeleted(woredaId, false);
		
		CountryEntity countryEntity = countryRepository.findByCountryIdAndIsDeleted(woredaDetail.getCountryId(), false);
		if(countryEntity==null)
			throw new AppException("the Country is not availlable");
		RegionEntity regionEntity = regionRepository.findByRegionIdAndIsDeleted(woredaDetail.getRegionId(), false);
		if(regionEntity==null)
			throw new AppException("the region is not availlable");
		ZoneEntity zoneEntity = zoneRepository.findByZoneIdAndIsDeleted(woredaDetail.getZoneId(), false);
		if(zoneEntity==null)
			throw new AppException("the zone is not availlable");
		BeanUtils.copyProperties(woredaDetail, woredaEntity);
		WoredaEntity savedWoreda = woredaRepository.save(woredaEntity);
		BeanUtils.copyProperties(savedWoreda, returnValue);
		return returnValue;
	}

	@Override
	public WoredaResponseModel getWoreda(Integer woredaId) {
		WoredaResponseModel returnValue = new WoredaResponseModel();
		WoredaEntity woredaEntity = woredaRepository.findByWoredaIdAndIsDeleted(woredaId,false);
		if(woredaEntity == null)
			throw new AppException("No woreda with this id");
		CountryEntity countryEntity = countryRepository.findByCountryIdAndIsDeleted(woredaEntity.getCountryId(), false);
		RegionEntity regionEntity = regionRepository.findByRegionIdAndIsDeleted(woredaEntity.getRegionId(), false);
		ZoneEntity zoneEntity = zoneRepository.findByZoneIdAndIsDeleted(woredaEntity.getZoneId(), false);
		BeanUtils.copyProperties(woredaEntity, returnValue);
		if(countryEntity!=null)
			returnValue.setCountryName(countryEntity.getName());
		if(regionEntity!=null)
			returnValue.setRegionName(regionEntity.getName());
		if(zoneEntity!=null)
			returnValue.setZoneName(zoneEntity.getName());
		return returnValue;
	}

}

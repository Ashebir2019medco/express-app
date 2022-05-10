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
import com.api.epharmacy.io.repositories.CountryRepository;
import com.api.epharmacy.service.CountryService;
import com.api.epharmacy.ui.model.request.CountryRequestModel;
import com.api.epharmacy.ui.model.response.CountryResponseModel;

@Service
@Transactional
public class CountryServiceImpl implements CountryService {
	
	@Autowired
	CountryRepository countryRepository;

	@Override
	public CountryResponseModel saveCountry(CountryRequestModel countryDetail) {
		
		CountryResponseModel returnValue = new CountryResponseModel();
		CountryEntity countryEntity = new CountryEntity();
		
		BeanUtils.copyProperties(countryDetail, countryEntity);
		
		CountryEntity checkCountry= countryRepository.findByNameAndIsDeleted(countryDetail.getName(),false);
		if(checkCountry!=null)
		    	throw new AppException("Country already exists");
//		countryEntity.setName(countryDetail.getName());
		CountryEntity savedCountryEntity = countryRepository.save(countryEntity);
		
		
		BeanUtils.copyProperties(savedCountryEntity, returnValue);
		return returnValue;
	}

	@Override
	public List<CountryResponseModel> getAllCountries(String searchKey, int page, int limit) {
		
		 List<CountryResponseModel> returnValue = new ArrayList<>();
		    
		    if(page > 0) page = page - 1; 
		   
		    Pageable pageableRequest = PageRequest.of(page, limit,Sort.by("nationality").ascending());
		    Page<CountryEntity> countryPage = null;
		    
		    if("".equals(searchKey))
		    	countryPage = countryRepository.findByIsDeleted(false, pageableRequest);//.findAll(pageableRequest);
		    else
		    	countryPage = countryRepository.findByIsDeletedAndNameContaining(false,searchKey, pageableRequest);//.findAll(pageableRequest);
		    
		    List<CountryEntity> countries = countryPage.getContent();
		    
		    
		    
		    int totalPages = countryPage.getTotalPages();	    
		    for(CountryEntity countryEntity : countries) {
		    	
		    	CountryResponseModel countryResponseModel = new CountryResponseModel(); 
		    	BeanUtils.copyProperties(countryEntity, countryResponseModel);
		    	
//		    	UserEntity userEntity= userRepository.findByUserId(countryEntity.getCreatedBy());
//				if(userEntity!=null)
//					countryResponseModel.setCreatedBy(userEntity.getFirstName()+" "+userEntity.getLastName());
//		    	else
//		    		countryResponseModel.setCreatedBy("");
				
		    	if(returnValue.size() == 0) {
		    		countryResponseModel.setTotalPage(totalPages);
		    	}
		    	
		    	returnValue.add(countryResponseModel);
		    }
		return returnValue;
	}

	@Override
	public CountryResponseModel getCountry(Integer countryId) {		
		CountryResponseModel returnValue = new CountryResponseModel();
		CountryEntity countryEntity = countryRepository.findByCountryIdAndIsDeleted(countryId,false);
		if(countryEntity == null)
			throw new AppException("No country with this id");
		BeanUtils.copyProperties(countryEntity, returnValue);
		return returnValue;
	}

	@Override
	public CountryResponseModel updateCountry(Integer countryId, CountryRequestModel countryDetail) {
		CountryResponseModel returnValue = new CountryResponseModel();
		CountryEntity countryEntity = countryRepository.findByCountryIdAndIsDeleted(countryId, false);
		
		BeanUtils.copyProperties(countryDetail, countryEntity);
		CountryEntity savedCountry = countryRepository.save(countryEntity);
		BeanUtils.copyProperties(savedCountry, returnValue);
		return returnValue;
	}

	@Override
	public String deleteCountry(Integer countryId) {
		CountryEntity countryEntity = countryRepository.findByCountryIdAndIsDeleted(countryId,false);
		if(countryEntity == null)
			throw new AppException("Invalid countryId");
		countryEntity.setDeleted(true);
		countryRepository.save(countryEntity);
		return "Country Deleted";
	}
	
}

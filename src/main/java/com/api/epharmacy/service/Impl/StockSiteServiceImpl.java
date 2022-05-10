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

import com.api.epharmacy.exception.AppException;
import com.api.epharmacy.io.entity.StockSiteEntity;
import com.api.epharmacy.io.repositories.StockSiteRepository;
import com.api.epharmacy.service.StockSiteService;
import com.api.epharmacy.ui.model.request.StockSiteRequestModel;
import com.api.epharmacy.ui.model.response.StockSiteResponseModel;

@Service
public class StockSiteServiceImpl implements StockSiteService {
	
	@Autowired
	StockSiteRepository stockSiteRepository;
	
	@Override
	public StockSiteResponseModel saveStockSite(StockSiteRequestModel requestDetail) {
		StockSiteResponseModel returnValue = new StockSiteResponseModel();
		StockSiteEntity stockSiteEntity = new StockSiteEntity();
		
		BeanUtils.copyProperties(requestDetail, stockSiteEntity);
		StockSiteEntity checkSite = stockSiteRepository.findBySiteNameAndIsDeleted(requestDetail.getSiteName(), false);
		if (checkSite != null)
			throw new AppException("Site already exists");
		StockSiteEntity savedDetail = stockSiteRepository.save(stockSiteEntity);
		
		BeanUtils.copyProperties(savedDetail, returnValue);
		return returnValue;
	}
	
	@Override
	public StockSiteResponseModel getStockSite(Integer siteId) {
		StockSiteResponseModel returnValue = new StockSiteResponseModel();
		StockSiteEntity stockSiteEntity = stockSiteRepository.findBySiteIdAndIsDeleted(siteId, false);
		if (stockSiteEntity == null)
			throw new AppException("No site with this id");
		BeanUtils.copyProperties(stockSiteEntity, returnValue);
		return returnValue;
	}
	
	@Override
	public List<StockSiteResponseModel> getStockSites(int page, int limit, String searchKey) {
		List<StockSiteResponseModel> returnValue = new ArrayList<>();
		
		if (page > 0)
			page = page - 1;
		
		Pageable pageableRequest = PageRequest.of(page, limit, Sort.by("siteId").descending());
		Page<StockSiteEntity> sietPage = null;
		
		if ("".equals(searchKey))
			sietPage = stockSiteRepository.findByIsDeleted(false, pageableRequest);
		else
			sietPage = stockSiteRepository.findByIsDeletedAndSiteNameContaining(false, searchKey, pageableRequest);
		List<StockSiteEntity> stockSiteEntities = sietPage.getContent();
		
		int totalPages = sietPage.getTotalPages();
		for (StockSiteEntity stockSiteEntity : stockSiteEntities) {
			
			StockSiteResponseModel stockSiteResponseModel = new StockSiteResponseModel();
			BeanUtils.copyProperties(stockSiteEntity, stockSiteResponseModel);
			if (returnValue.size() == 0) {
				stockSiteResponseModel.setTotalPage(totalPages);
			}
			
			returnValue.add(stockSiteResponseModel);
		}
		return returnValue;
	}
	
	@Override
	public StockSiteResponseModel updateStockSite(Integer siteId, StockSiteRequestModel requestDetail) {
		
		StockSiteResponseModel returnValue = new StockSiteResponseModel();
		StockSiteEntity siteEntity = stockSiteRepository.findBySiteIdAndIsDeleted(siteId, false);
		
		if (siteEntity == null)
			throw new AppException("No site with this id");
		
		BeanUtils.copyProperties(requestDetail, siteEntity);
		StockSiteEntity savedDetail = stockSiteRepository.save(siteEntity);
		BeanUtils.copyProperties(savedDetail, returnValue);
		return returnValue;
	}
	
	@Override
	public String deleteStockSite(Integer siteId) {
		StockSiteEntity siteEntity = stockSiteRepository.findBySiteIdAndIsDeleted(siteId, false);
		
		if (siteEntity == null)
			throw new RuntimeException("No site with this id");
		
		siteEntity.setDeleted(true);
		stockSiteRepository.save(siteEntity);
		
		return "Site Deleted";
	}

	

}

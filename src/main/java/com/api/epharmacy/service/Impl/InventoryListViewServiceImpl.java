package com.api.epharmacy.service.Impl;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.epharmacy.io.entity.InventoryEntity;
import com.api.epharmacy.io.entity.InventorySellPriceListEntity;
import com.api.epharmacy.io.repositories.InventoryCostPriceListRepository;
import com.api.epharmacy.io.repositories.InventoryRepository;
import com.api.epharmacy.io.repositories.InventorySellPriceListRepository;
import com.api.epharmacy.service.InventoryListViewService;
import com.api.epharmacy.ui.model.request.InventoryListViewSearchRequestModel;
import com.api.epharmacy.ui.model.response.InventoryListViewResponse;

@Service
public class InventoryListViewServiceImpl implements InventoryListViewService {

	@Autowired
	InventoryRepository inventoryRepository;

	@Autowired
	InventoryCostPriceListRepository inventoryCostPriceListRepository;

	@Autowired
	InventorySellPriceListRepository inventorySellPriceListRepository;
	
	@Override
	public List<InventoryListViewResponse> getInventeryListView(int page, int limit) {
		
		List<InventoryListViewResponse> returnValue = new ArrayList<>();
	    
	    if(page > 0) page = page - 1;
	   
	    Pageable pageableRequest = PageRequest.of(page, limit);
	    //Page<InventoryEntity> inventoryPage = inventoryRepository.findByAvailableQuantityNot(notZero,pageableRequest);
	    Page<InventoryEntity> inventoryPage = inventoryRepository.findByIsDeleted(false,pageableRequest);
	    int totalPages = inventoryPage.getTotalPages();
	    List<InventoryEntity> inventoryItems = inventoryPage.getContent();
	    for(InventoryEntity inventoryEntity : inventoryItems) {
			InventoryListViewResponse inventoryListViewResponse = new InventoryListViewResponse();
	    	BeanUtils.copyProperties(inventoryEntity, inventoryListViewResponse); 
	    	if(returnValue.size() == 0) {
	    		inventoryListViewResponse.setTotalPages(totalPages);
	    	}
	    	InventorySellPriceListEntity price = inventorySellPriceListRepository.findTopByInventoryIdAndEffectiveDateTimeLessThanEqualAndIsDeletedOrderByEffectiveDateTimeDesc(inventoryEntity.getInventoryId(), Instant.now(), false);						
	    	if(price != null) {
	    		inventoryListViewResponse.setSellPrice(price.getSellPrice());
		    	inventoryListViewResponse.setDiscountAmount(price.getDiscountAmount());
		    	returnValue.add(inventoryListViewResponse);
	    	}
	    }
	    
		return returnValue;
	}

	@Override
	public InventoryListViewResponse getInventoryByInventoryId(long inventoryId) {

		InventoryListViewResponse returnvalue = new InventoryListViewResponse();
		InventoryEntity inventoryEntity = inventoryRepository.findByInventoryId(inventoryId);
		if(inventoryEntity == null) throw new RuntimeException("Inventory Item not found.");
		
		InventorySellPriceListEntity price = inventorySellPriceListRepository
		        .findTopByInventoryIdAndEffectiveDateTimeLessThanEqualAndIsDeletedOrderByEffectiveDateTimeDesc(
		            inventoryEntity.getInventoryId(), Instant.now(), false);
		if (price != null) {
			returnvalue.setSellPrice(price.getSellPrice());
			returnvalue.setDiscountAmount(price.getDiscountAmount());
		}
		
		BeanUtils.copyProperties(inventoryEntity, returnvalue);
		
		return returnvalue;
	}

	@Override
	public List<InventoryListViewResponse> searchInventoryListView(InventoryListViewSearchRequestModel searchDetails,
			int page, int limit) {
		
		List<InventoryListViewResponse> returnValue = new ArrayList<>();
	    
	    if(page > 0) page = page - 1;
	    
	    Pageable pageableRequest = PageRequest.of(page, limit);
	    double notZero = 0.0;
	    String searchKey = searchDetails.getInventoryName();
	    String inventoryType = searchDetails.getInventoryType();
	   
	    Page<InventoryEntity> searchPage = inventoryRepository.findByInventoryGenericNameContainingOrInventoryBrandNameContainingAndAvailableQuantityNot(searchKey,searchKey,notZero,pageableRequest);
	    int totalPages = searchPage.getTotalPages();
	    List<InventoryEntity> searchItems = searchPage.getContent();
	    for(InventoryEntity searchItem : searchItems) {
	    	InventoryListViewResponse inventoryListViewResponse = new InventoryListViewResponse(); 
	    	BeanUtils.copyProperties(searchItem, inventoryListViewResponse);
	    	if(returnValue.size() == 0) {
	    		inventoryListViewResponse.setTotalPages(totalPages);
	    	}
				    	InventorySellPriceListEntity priceList = inventorySellPriceListRepository.findFirstByInventoryIdOrderBySellPriceDesc(searchItem.getInventoryId());
				    	if(priceList != null) {
					    	inventoryListViewResponse.setSellPrice(priceList.getSellPrice());
					    	inventoryListViewResponse.setDiscountAmount(priceList.getDiscountAmount());
					    	if(inventoryType == null || inventoryType == "") {
					    		returnValue.add(inventoryListViewResponse);
					    	}else{
					    		if(inventoryType.equals(searchItem.getInventoryType())) {
					    			returnValue.add(inventoryListViewResponse);
					    		}
					    	}
				    	}
	    }
	    
		return returnValue;
	}

}

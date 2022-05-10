package com.api.epharmacy.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.epharmacy.io.entity.InventoryCostPriceListEntity;
import com.api.epharmacy.io.entity.InventorySellPriceListEntity;
import com.api.epharmacy.io.repositories.InventoryCostPriceListRepository;
import com.api.epharmacy.io.repositories.InventorySellPriceListRepository;
import com.api.epharmacy.service.InventoryPriceListService;
import com.api.epharmacy.shared.dto.InventoryItemsDto;
import com.api.epharmacy.ui.model.response.InventoryPriceListResponse;

@Service
public class InventoryPriceListServiceImpl implements InventoryPriceListService {
	
	@Autowired
	InventoryCostPriceListRepository inventoryCostPriceListRepository;
	
	@Autowired
	InventorySellPriceListRepository inventorySellPriceListRepository;
	
	@Override
	public List<InventoryPriceListResponse> getInventoryItemPriceList(long inventoryId) {
		List<InventoryPriceListResponse> returnValue= new ArrayList<>();
		
		List<InventoryItemsDto> priceListsDto = new ArrayList<>();
		List<InventoryCostPriceListEntity> priceLists = inventoryCostPriceListRepository.findByInventoryId(inventoryId);
		
		for (InventoryCostPriceListEntity priceList : priceLists) {
			InventoryItemsDto inventoryItemsDto = new InventoryItemsDto(); 
	    	BeanUtils.copyProperties(priceList, inventoryItemsDto); 
	    	priceListsDto.add(inventoryItemsDto);
	    }
				
		for(InventoryItemsDto priceList : priceListsDto) {
			InventoryPriceListResponse inventoryPriceListResponse = new InventoryPriceListResponse(); 
	    	BeanUtils.copyProperties(priceList, inventoryPriceListResponse); 
	    	
			InventorySellPriceListEntity inventorySellPriceListEntity = inventorySellPriceListRepository.findTopByInventoryIdAndIsDeletedOrderByInventorySellPriceListIdDesc(priceList.getInventoryId(), false);
	    	if(inventorySellPriceListEntity!=null) {
	    		inventoryPriceListResponse.setSellPrice(inventorySellPriceListEntity.getSellPrice());
	    		inventoryPriceListResponse.setDiscountAmount(inventorySellPriceListEntity.getDiscountAmount());
	    	}
			returnValue.add(inventoryPriceListResponse);
	    }
		return returnValue;
	}

}

package com.api.epharmacy.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.epharmacy.io.entity.InventorySellPriceListEntity;
import com.api.epharmacy.io.repositories.InventorySellPriceListRepository;
import com.api.epharmacy.service.InventoryPriceListService;
import com.api.epharmacy.shared.dto.InventoryItemsDto;
import com.api.epharmacy.ui.model.response.InventoryPriceListResponse;

@RestController
@RequestMapping("/pricelist")
public class InventoryPriceListController {
	
	@Autowired
	InventoryPriceListService inventoryPriceListService;
	
	@Autowired
	InventorySellPriceListRepository inventorySellPriceListRepository;
	@GetMapping(path="/{InventoryId}")
	public List<InventoryPriceListResponse> getInventoryPriceList(@PathVariable long InventoryId) {
		/*
		List<InventoryPriceListResponse> returnValue= new ArrayList<>();
		List<InventoryItemsDto> priceLists = inventoryPriceListService.getInventoryItemPriceList(InventoryId);
		
		for(InventoryItemsDto priceList : priceLists) {
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
		*/
		return inventoryPriceListService.getInventoryItemPriceList(InventoryId);
	}
}

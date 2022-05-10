package com.api.epharmacy.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.epharmacy.service.InventoryTransactionService;
import com.api.epharmacy.shared.dto.InventoryItemsDto;
import com.api.epharmacy.ui.model.request.InventoryItemsRequestModel;
import com.api.epharmacy.ui.model.request.InventorySellPriceRequestModel;
import com.api.epharmacy.ui.model.request.InventoryTransactionUpdateRequestModel;
import com.api.epharmacy.ui.model.response.TransactionHistoryResponse;

@RestController
@RequestMapping("/inventorytransaction")
public class InventoryTransactionController {

	@Autowired
	InventoryTransactionService inventoryTransactionService;
	
	@PostMapping
	public String inserttransaction(@RequestBody List<InventoryItemsRequestModel> inventoryItems) {
		
		/*
		List<InventoryItemsRequestModel> inventoryItems = new ArrayList<>();
>>>>>>> branch 'main' of https://gitlab.com/epharmacy1/epharmacy-backend.git
		inventoryItems.addAll(inventoryList.getInventeryItems());
		
		List<InventoryItemsDto> inventoryItemsDtos = new ArrayList<>();
		
		for(InventoryItemsRequestModel inventoryItem : inventoryItems) {
			InventoryItemsDto inventoryModel = new InventoryItemsDto();
			BeanUtils.copyProperties(inventoryItem, inventoryModel);
			inventoryItemsDtos.add(inventoryModel);
		}*/
		String returnvalue = inventoryTransactionService.InsertInventoryItems(inventoryItems);
		
		return returnvalue;
	}
	
	@GetMapping(path="/{inventoryTransactionDetailId}")
	public TransactionHistoryResponse getInventoryByInventoryId(@PathVariable long inventoryTransactionDetailId) {

		TransactionHistoryResponse returnValue = inventoryTransactionService.getTransactionByInventoryTransactionDetailId(inventoryTransactionDetailId);
		return returnValue;
	}
	
	@GetMapping
	public List<TransactionHistoryResponse> getTransactionHistory(@RequestParam(value="page", defaultValue = "1") int page,
								   @RequestParam(value="limit", defaultValue = "25") int limit){
		
		List<TransactionHistoryResponse> returnValue = new ArrayList<>();
		
		List<InventoryItemsDto> inventoryItemsDtos= inventoryTransactionService.getTransactionHistory(page,limit);
		
		for(InventoryItemsDto  inventoryItemsDto: inventoryItemsDtos) {
			TransactionHistoryResponse transactionHistory = new TransactionHistoryResponse();
			BeanUtils.copyProperties(inventoryItemsDto, transactionHistory);
			returnValue.add(transactionHistory);
		}
		
		return returnValue;
	}
	
	@GetMapping(path="/search/{inventoryId}")
	public List<TransactionHistoryResponse> searchTransactionHistory(@PathVariable long inventoryId, @RequestParam(value="page", defaultValue = "1") int page,
								   @RequestParam(value="limit", defaultValue = "25") int limit){
		
		List<TransactionHistoryResponse> returnValue = new ArrayList<>();
		
		List<InventoryItemsDto> inventoryItemsDtos= inventoryTransactionService.searchTransactionHistory(inventoryId,page,limit);
		
		for(InventoryItemsDto  inventoryItemsDto: inventoryItemsDtos) {
			TransactionHistoryResponse transactionHistory = new TransactionHistoryResponse();
			BeanUtils.copyProperties(inventoryItemsDto, transactionHistory);
			returnValue.add(transactionHistory);
		}
		
		return returnValue;
	}
	
	@PutMapping(path="/{inventoryTransactionDetailId}")
	public InventoryTransactionUpdateRequestModel updateInventoryItem(@PathVariable long inventoryTransactionDetailId, @RequestBody InventoryTransactionUpdateRequestModel newTransactionDetail) {
		
		InventoryTransactionUpdateRequestModel returnValue = inventoryTransactionService.updateInventoryTransactionHistory(inventoryTransactionDetailId, newTransactionDetail);
		return returnValue;
	}
	
	@PostMapping(path = "/sell-price")
	public InventorySellPriceRequestModel insertInventorySellPrice(
	        @RequestBody InventorySellPriceRequestModel requestDetail) {
		
		InventorySellPriceRequestModel returnValue = inventoryTransactionService.insertInventorySellPrice(requestDetail);
		return returnValue;
	}
}

package com.api.epharmacy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.epharmacy.service.InventoryListViewService;
import com.api.epharmacy.ui.model.request.InventoryListViewSearchRequestModel;
import com.api.epharmacy.ui.model.request.UserDetailRequestModel;
import com.api.epharmacy.ui.model.response.InventoryListViewResponse;

@RestController
@RequestMapping("/inventorylistview")
public class InventoryListViewController {

	@Autowired
	InventoryListViewService inventoryListViewService;
	
	@GetMapping(path="/{inventoryId}")
	public InventoryListViewResponse getInventoryByInventoryId(@PathVariable long inventoryId) {

		InventoryListViewResponse requestedInventory = inventoryListViewService.getInventoryByInventoryId(inventoryId);
		return requestedInventory;
	}
	
	@GetMapping
	public List<InventoryListViewResponse> getInventeryListView(@RequestParam(value="page", defaultValue = "1") int page,
								   @RequestParam(value="limit", defaultValue = "25") int limit){
		
		List<InventoryListViewResponse> inventoryItems= inventoryListViewService.getInventeryListView(page,limit);
				
		return inventoryItems;
	}
	
	@PostMapping(path="/search")
	public List<InventoryListViewResponse> searchInventoryListView(@RequestBody InventoryListViewSearchRequestModel searchDetails,@RequestParam(value="page", defaultValue = "1") int page,
								   @RequestParam(value="limit", defaultValue = "25") int limit){
		
		List<InventoryListViewResponse> searchList= inventoryListViewService.searchInventoryListView(searchDetails,page,limit);
				
		return searchList;
	}
}

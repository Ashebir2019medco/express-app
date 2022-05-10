package com.api.epharmacy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.epharmacy.service.StockSiteService;
import com.api.epharmacy.ui.model.request.StockSiteRequestModel;
import com.api.epharmacy.ui.model.response.StockSiteResponseModel;

@RestController
@RequestMapping("/stock-site")
public class StockSiteController {
	
	@Autowired
	StockSiteService stockSiteService;
	
	@PostMapping
	public StockSiteResponseModel saveStockSite(@RequestBody StockSiteRequestModel requestDetail) {
		StockSiteResponseModel returnValue = stockSiteService.saveStockSite(requestDetail);
		return returnValue;
		
	}
	
	@GetMapping(path = "/{siteId}")
	public StockSiteResponseModel getStockSite(@PathVariable Integer siteId) {
		
		StockSiteResponseModel returnValue = stockSiteService.getStockSite(siteId);
		return returnValue;
	}
	
	@GetMapping
	public List<StockSiteResponseModel> getStockSites(@RequestParam(value = "page", defaultValue = "1") int page,
	        @RequestParam(value = "limit", defaultValue = "25") int limit,
	        @RequestParam(value = "searchKey", defaultValue = "") String searchKey) {
		
		List<StockSiteResponseModel> returnValue = stockSiteService.getStockSites(page, limit, searchKey);
		return returnValue;
	}
		
	@PutMapping(path = "/{siteId}")
	public StockSiteResponseModel updateStockSite(@PathVariable Integer siteId,
	        @RequestBody StockSiteRequestModel requestDetail) {
		
		StockSiteResponseModel returnValue = stockSiteService.updateStockSite(siteId, requestDetail);
		
		return returnValue;
	}
	
	@DeleteMapping(path = "/{siteId}")
	public String deleteStockSite(@PathVariable Integer siteId) {
		
		String returnValue = stockSiteService.deleteStockSite(siteId);
		
		return returnValue;
	}
	
}

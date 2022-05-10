package com.api.epharmacy.service;

import java.util.List;

import com.api.epharmacy.ui.model.request.StockSiteRequestModel;
import com.api.epharmacy.ui.model.response.StockSiteResponseModel;

public interface StockSiteService {
	
	StockSiteResponseModel getStockSite(Integer siteId);
	
	List<StockSiteResponseModel> getStockSites(int page, int limit, String searchKey);
	
	StockSiteResponseModel updateStockSite(Integer siteId, StockSiteRequestModel requestDetail);
	
	String deleteStockSite(Integer siteId);
	
	StockSiteResponseModel saveStockSite(StockSiteRequestModel requestDetail);
	
	
}

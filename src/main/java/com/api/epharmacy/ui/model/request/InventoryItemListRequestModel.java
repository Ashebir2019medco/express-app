package com.api.epharmacy.ui.model.request;

import java.util.List;

public class InventoryItemListRequestModel {

	List<InventoryItemsRequestModel> inventeryItems;

	public List<InventoryItemsRequestModel> getInventeryItems() {
		return inventeryItems;
	}

	public void setInventeryItems(
			List<InventoryItemsRequestModel> inventoryItemsRequestModel) {
		inventeryItems = inventoryItemsRequestModel;
	}
	
}

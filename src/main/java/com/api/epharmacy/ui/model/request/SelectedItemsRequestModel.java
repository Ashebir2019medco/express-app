
package com.api.epharmacy.ui.model.request;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import com.api.epharmacy.ui.model.response.OrderDocumentsResponseModel;
import com.api.epharmacy.ui.model.response.OrderItemResponseModel;
import com.api.epharmacy.ui.model.response.PreOrderResponseModel;

public class SelectedItemsRequestModel {

	private long[] inventoryIds;

	public long[] getInventoryIds() {
		return inventoryIds;
	}

	public void setInventoryIds(long[] inventoryIds) {
		this.inventoryIds = inventoryIds;
	}
	
}
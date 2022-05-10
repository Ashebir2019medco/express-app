package com.api.epharmacy.service;

import java.util.List;

import com.api.epharmacy.shared.dto.InventoryItemsDto;
import com.api.epharmacy.ui.model.response.InventoryPriceListResponse;

public interface InventoryPriceListService {

	List<InventoryPriceListResponse> getInventoryItemPriceList(long inventoryId);

}

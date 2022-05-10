package com.api.epharmacy.service;

import java.util.List;

import com.api.epharmacy.shared.dto.InventoryItemsDto;
import com.api.epharmacy.ui.model.request.InventoryItemsRequestModel;
import com.api.epharmacy.ui.model.request.InventorySellPriceRequestModel;
import com.api.epharmacy.ui.model.request.InventoryTransactionUpdateRequestModel;
import com.api.epharmacy.ui.model.response.TransactionHistoryResponse;

public interface InventoryTransactionService {

	String InsertInventoryItems(List<InventoryItemsRequestModel> inventoryItemsDtos);

	List<InventoryItemsDto> getTransactionHistory(int page, int limit);

	InventoryTransactionUpdateRequestModel updateInventoryTransactionHistory(long inventoryTransactionDetailId, InventoryTransactionUpdateRequestModel newTransactionDetail);

	List<InventoryItemsDto> searchTransactionHistory(long inventoryId, int page, int limit);

	TransactionHistoryResponse getTransactionByInventoryTransactionDetailId(long inventoryTransactionDetailId);

	public void orderPreOrderManagementJob();

	InventorySellPriceRequestModel insertInventorySellPrice(InventorySellPriceRequestModel requestDetail);
	
}

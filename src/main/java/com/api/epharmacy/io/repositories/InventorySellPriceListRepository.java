package com.api.epharmacy.io.repositories;

import java.time.Instant;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.api.epharmacy.io.entity.InventorySellPriceListEntity;

public interface InventorySellPriceListRepository extends PagingAndSortingRepository<InventorySellPriceListEntity, Long>{

	List<InventorySellPriceListEntity> findByInventoryIdAndIsDeleted(long inventoryId, boolean b);

	InventorySellPriceListEntity findTopByInventoryIdAndEffectiveDateTimeLessThanEqualAndIsDeletedOrderByEffectiveDateTimeDesc(
			long inventoryId, Instant now, boolean b);

	InventorySellPriceListEntity findTopByInventoryIdAndIsDeletedOrderByInventorySellPriceListIdDesc(long inventoryId, boolean b);

	InventorySellPriceListEntity findFirstByInventoryIdOrderBySellPriceDesc(long inventoryId);

}



package com.api.epharmacy.io.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.api.epharmacy.io.entity.InventoryTransactionDetailEntity;

public interface InventoryTransactionDetailRepository extends PagingAndSortingRepository<InventoryTransactionDetailEntity, Long>{

	InventoryTransactionDetailEntity findByInventoryTransactionDetailId(long inventoryTransactionDetailId);

	Page<InventoryTransactionDetailEntity> findByInventoryId(long inventoryId, Pageable pageableRequest);

}

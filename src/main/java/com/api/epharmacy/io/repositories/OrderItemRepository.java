package com.api.epharmacy.io.repositories;

import java.util.List;

import org.springframework.data.domain.Sort.Order;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.util.Streamable;

import com.api.epharmacy.io.entity.OrderItemEntity;

public interface OrderItemRepository extends PagingAndSortingRepository<OrderItemEntity, Long>{

	List<OrderItemEntity> findByOrderId(long orderId);

	OrderItemEntity findTopByInventoryId(long inventoryId);

	List<OrderItemEntity> findByInventoryIdAndIsDeleted(long inventoryId, boolean b);

	List<OrderItemEntity> findByInventoryIdAndPreOrderQuantityGreaterThanAndIsDeleted(long inventoryId, double i,
			boolean b);

	List<OrderItemEntity> findByInventoryIdAndPreOrderQuantityGreaterThanAndIsDeletedOrderByCreatedAt(long inventoryId,
			double i, boolean b);

	List<OrderItemEntity> findByInventoryIdAndPreOrderQuantityGreaterThanAndIsDeletedOrderByCreatedAtDesc(
			long inventoryId, double d, boolean b);

	List<OrderItemEntity> findByOrderIdAndIsDeleted(long orderId, boolean b);

	OrderItemEntity findByOrderItemIdAndIsDeleted(long orderItemId, boolean b);

	List<OrderItemEntity> findByInventoryIdAndOrderQuantityGreaterThanAndIsDeletedOrderByCreatedAt(long inventoryId, int i,
			boolean b);
	
	List<OrderItemEntity> findByInventoryIdInAndOrderQuantityGreaterThanAndIsDeletedOrderByCreatedAt(Long[] inventoryIds, int i, boolean b);
	
}

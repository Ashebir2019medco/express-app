package com.api.epharmacy.io.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Repository;

import com.api.epharmacy.io.entity.OrderItemEntity;
import com.api.epharmacy.io.entity.OrderItemStatusEntity;
import com.api.epharmacy.io.entity.OrderStatusTypeEntity;

@Repository
public interface OrderItemStatusRepository extends PagingAndSortingRepository<OrderItemStatusEntity, Long> {

	List<OrderItemStatusEntity> findByOrderItemIdAndOrderStatusTypeId(Long orderItemId,
			List<Integer> orderStatusTypeIds);

	List<OrderItemStatusEntity> findByOrderItemIdInAndOrderStatusTypeId(List<Long> orderItemIds,
			Integer orderStatusTypeId);

	List<OrderItemStatusEntity> findByOrderItemIdAndOrderStatusTypeIdInAndIsDeleted(long orderItemId,
			Integer[] orderStatusTypeIds, boolean b);

	List<OrderItemStatusEntity> findByOrderItemIdAndIsDeleted(long orderItemId, boolean b);

	OrderItemStatusEntity findTopByOrderItemIdAndOrderStatusTypeIdAndIsDeleted(long orderItemId, Integer orderStatusTypeId, boolean b);

	List<OrderItemStatusEntity> findByOrderItemIdAndOrderStatusTypeIdInAndQuantityGreaterThanAndIsDeleted(
			long orderItemId, Integer[] orderStatusTypeIds, double d, boolean b);
	
}

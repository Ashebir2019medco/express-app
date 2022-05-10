package com.api.epharmacy.io.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Repository;

import com.api.epharmacy.io.entity.OrderItemStatusEntity;
import com.api.epharmacy.io.entity.OrderStatusTypeEntity;

@Repository
public interface OrderStatusTypeRepository extends PagingAndSortingRepository<OrderStatusTypeEntity, Integer> {

	OrderStatusTypeEntity findByOrderStatusTypeAndIsDeleted(String orderStatusType, boolean b);

	OrderStatusTypeEntity findByOrderStatusTypeIdAndIsDeleted(Integer l, boolean b);
	
	Page<OrderStatusTypeEntity> findByIsDeleted(boolean b, Pageable pageableRequest);
	
	Page<OrderStatusTypeEntity> findByIsDeletedAndOrderStatusTypeContaining(boolean b, String searchKey,
	        Pageable pageableRequest);
	
}

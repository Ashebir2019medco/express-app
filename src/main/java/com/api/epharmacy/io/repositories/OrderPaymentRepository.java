package com.api.epharmacy.io.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.api.epharmacy.io.entity.OrderPaymentEntity;

public interface OrderPaymentRepository extends PagingAndSortingRepository<OrderPaymentEntity, Long> {
	
	List<OrderPaymentEntity> findByOrderIdAndIsDeleted(long orderId, boolean b);

	OrderPaymentEntity findTopByOrderIdAndIsDeleted(long orderId, boolean b);
	
	OrderPaymentEntity findByOrderPaymentIdAndIsDeleted(long orderPaymnetId, boolean b);
	
}

package com.api.epharmacy.io.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.api.epharmacy.io.entity.OrderPaymentVerificationEntity;

public interface OrderPaymentVerificationRepository extends PagingAndSortingRepository<OrderPaymentVerificationEntity, Long> {
	
	OrderPaymentVerificationEntity findByOrderPaymentVerificationIdAndIsDeleted(long orderPaymentVerificationId, boolean b);
	
	List<OrderPaymentVerificationEntity> findByOrderIdAndIsDeleted(long orderId, boolean b);
	
}

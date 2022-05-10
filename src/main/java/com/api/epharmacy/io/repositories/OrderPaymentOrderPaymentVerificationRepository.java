package com.api.epharmacy.io.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.api.epharmacy.io.entity.OrderPaymentOrderPaymentVerificationEntity;

@Repository
public interface OrderPaymentOrderPaymentVerificationRepository extends PagingAndSortingRepository<OrderPaymentOrderPaymentVerificationEntity, Long> {
	
	List<OrderPaymentOrderPaymentVerificationEntity> findByOrderPaymentVerificationIdAndIsDeleted(
	        long orderPaymentVerificationId, boolean b);
	
}

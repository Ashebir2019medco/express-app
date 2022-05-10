package com.api.epharmacy.io.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.api.epharmacy.io.entity.OrderPaymentTransactionEntity;

public interface OrderPaymentTransactionRepository extends PagingAndSortingRepository<OrderPaymentTransactionEntity, Long> {
	
	List<OrderPaymentTransactionEntity> findByOrderPaymentVerificationIdAndIsDeleted(long orderPaymentVerificationId,
	        boolean b);
	
}

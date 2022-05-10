package com.api.epharmacy.io.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.api.epharmacy.io.entity.OrderPaymentDocumentsEntity;

@Repository
public interface OrderDocumentsRepository extends PagingAndSortingRepository<OrderPaymentDocumentsEntity, Long>{


	List<OrderPaymentDocumentsEntity> findByOrderPaymentVerificationIdAndIsDeleted(long orderPaymentVerificationId,
	        boolean b);
	
	OrderPaymentDocumentsEntity findByOrderDocumentsIdAndIsDeleted(Long orderDocumentsId, boolean b);
	
}

package com.api.epharmacy.io.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.api.epharmacy.io.entity.RejectedPaymentReasonEntity;

@Repository
public interface RejectedPaymentRepository extends PagingAndSortingRepository<RejectedPaymentReasonEntity, Integer> {
	
	List<RejectedPaymentReasonEntity> findByOrderPaymentVerificationIdAndIsDeleted(long orderPaymentVerificationId,
	        boolean b);
	
}

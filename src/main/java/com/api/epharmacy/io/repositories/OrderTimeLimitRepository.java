package com.api.epharmacy.io.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.api.epharmacy.io.entity.OrderTimeLimitEntity;

@Repository
public interface OrderTimeLimitRepository extends PagingAndSortingRepository<OrderTimeLimitEntity, Integer> {
	
	
	OrderTimeLimitEntity findTopByIsDeletedOrderByOrderTimeLimitIdDesc(boolean b);
	
	OrderTimeLimitEntity findByOrderTimeLimitIdAndIsDeleted(Integer orderTimeLimitId, boolean b);

	OrderTimeLimitEntity findTopByIsDeleted(boolean b);
	

}

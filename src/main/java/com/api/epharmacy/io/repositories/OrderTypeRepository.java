package com.api.epharmacy.io.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.api.epharmacy.io.entity.OrderTypeEntity;

@Repository
public interface OrderTypeRepository extends PagingAndSortingRepository<OrderTypeEntity, Integer> {
	
	OrderTypeEntity findByOrderTypeAndIsDeleted(String orderType, boolean b);
	
	OrderTypeEntity findByOrderTypeIdAndIsDeleted(Integer orderTypeId, boolean b);
	
	Page<OrderTypeEntity> findByIsDeleted(boolean b, Pageable pageableRequest);
	List<OrderTypeEntity> findByIsDeleted(boolean b);
	
	Page<OrderTypeEntity> findByIsDeletedAndOrderTypeContaining(boolean b, String searchKey, Pageable pageableRequest);

	List<OrderTypeEntity> findByOrderTypeIdInAndIsDeleted(Integer[] orderTypeListIds, boolean b);
	
}

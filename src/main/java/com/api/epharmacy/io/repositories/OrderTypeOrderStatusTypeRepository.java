package com.api.epharmacy.io.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.api.epharmacy.io.entity.OrderTypeOrderStatusTypeEntity;

@Repository
public interface OrderTypeOrderStatusTypeRepository extends PagingAndSortingRepository<OrderTypeOrderStatusTypeEntity, Integer> {
	
	Page<OrderTypeOrderStatusTypeEntity> findByIsDeleted(boolean b, Pageable pageableRequest);
	
	Page<OrderTypeOrderStatusTypeEntity> findByOrderTypeIdAndIsDeleted(int orderTypeId, boolean b, Pageable pageableRequest);
	
	Page<OrderTypeOrderStatusTypeEntity> findByOrderStatusTypeIdAndIsDeleted(int orderStatusTypeId, boolean b,
	        Pageable pageableRequest);
	
	Page<OrderTypeOrderStatusTypeEntity> findByOrderStatusTypeIdAndOrderTypeIdAndIsDeleted(int orderStatusTypeId,
	        int orderTypeId, boolean b, Pageable pageableRequest);
	
	OrderTypeOrderStatusTypeEntity findByOrderTypeOrderStatusTypeIdAndIsDeleted(Integer orderTypeOrderStatusTypeId,
	        boolean b);
	
	OrderTypeOrderStatusTypeEntity findByOrderTypeIdAndOrderStatusTypeIdAndIsDeleted(Integer orderTypeId,
	        Integer orderStatusTypeId, boolean b);

	List<OrderTypeOrderStatusTypeEntity> findByOrderTypeIdInAndIsDeleted(Integer[] orderTypeIds, boolean b);
	
	List<OrderTypeOrderStatusTypeEntity> findByIsDeletedAndOrderStatusTypeId(boolean b, Integer orderStatusTypeId);

	OrderTypeOrderStatusTypeEntity findTopByOrderTypeIdAndIsDeletedOrderByWeight(Integer orderTypeId, boolean b);

	List<OrderTypeOrderStatusTypeEntity> findByOrderStatusTypeIdAndIsDeleted(Integer orderStatusTypeId, boolean b);

	List<OrderTypeOrderStatusTypeEntity> findByOrderTypeIdAndIsDeletedOrderByWeight(Integer orderTypeId, boolean b);

	List<OrderTypeOrderStatusTypeEntity> findByOrderTypeIdAndWeightLessThanEqualAndIsDeletedOrderByWeight(
			Integer orderTypeId, Integer weight, boolean b);

	OrderTypeOrderStatusTypeEntity findTopByOrderTypeIdAndOrderStatusTypeIdAndIsDeleted(Integer orderTypeId,
			Integer orderStatusTypeId, boolean b);
	
	List<OrderTypeOrderStatusTypeEntity> findByIsDeletedAndOrderTypeId(boolean b, Integer orderTypeId);
	
}

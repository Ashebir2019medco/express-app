package com.api.epharmacy.io.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.api.epharmacy.io.entity.StockSiteEntity;

@Repository
public interface StockSiteRepository extends PagingAndSortingRepository<StockSiteEntity, Integer> {
	
	StockSiteEntity findBySiteIdAndIsDeleted(Integer siteId, boolean b);
	
	Page<StockSiteEntity> findByIsDeleted(boolean b, Pageable pageableRequest);
	
	Page<StockSiteEntity> findByIsDeletedAndSiteNameContaining(boolean b, String searchKey, Pageable pageableRequest);
	
	StockSiteEntity findBySiteNameAndIsDeleted(String siteName, boolean b);
	
}

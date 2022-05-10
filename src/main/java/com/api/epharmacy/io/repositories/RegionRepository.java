package com.api.epharmacy.io.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.api.epharmacy.io.entity.RegionEntity;


public interface RegionRepository extends PagingAndSortingRepository<RegionEntity, Integer> {

	RegionEntity findByName(String name);

	Page<RegionEntity> findByIsDeleted(boolean b, Pageable pageableRequest);
    RegionEntity findByRegionIdAndIsDeleted(Integer regionId, boolean b);

	List<RegionEntity> findByCountryIdAndNameAndIsDeleted(Integer countryId, String name, boolean b);

	Page<RegionEntity> findByIsDeletedAndNameContaining(boolean b, String searchKey, Pageable pageableRequest);

}

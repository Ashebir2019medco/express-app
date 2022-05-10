package com.api.epharmacy.io.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.api.epharmacy.io.entity.ZoneEntity;

public interface ZoneRepository extends PagingAndSortingRepository<ZoneEntity, Integer> {

	ZoneEntity findByName(String name);

	Page<ZoneEntity> findByIsDeleted(boolean b, Pageable pageableRequest);

	ZoneEntity findByZoneIdAndIsDeleted(Integer zoneId, boolean b);

	List<ZoneEntity> findByRegionIdAndNameAndIsDeleted(Integer regionId, String name, boolean b);

	Page<ZoneEntity> findByIsDeletedAndNameContaining(boolean b, String searchKey, Pageable pageableRequest);


}

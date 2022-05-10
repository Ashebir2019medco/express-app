package com.api.epharmacy.io.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.api.epharmacy.io.entity.WoredaEntity;


public interface WoredaRepository extends PagingAndSortingRepository<WoredaEntity, Integer> {

	Page<WoredaEntity> findByIsDeleted(boolean b, Pageable pageableRequest);

	WoredaEntity findByWoredaIdAndIsDeleted(Integer woredaId, boolean b);

	List<WoredaEntity> findByZoneIdAndNameAndIsDeleted(Integer zoneId, String name, boolean b);

	Page<WoredaEntity> findByIsDeletedAndNameContaining(boolean b, String searchKey, Pageable pageableRequest);


}

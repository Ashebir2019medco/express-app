package com.api.epharmacy.io.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.api.epharmacy.io.entity.CountryEntity;

public interface CountryRepository extends PagingAndSortingRepository<CountryEntity, Integer> {

	Page<CountryEntity> findByIsDeleted(boolean b, Pageable pageableRequest);

	CountryEntity findByCountryIdAndIsDeleted(Integer countryId, boolean b);

	Page<CountryEntity> findByIsDeletedAndNameContaining(boolean b, String searchKey, Pageable pageableRequest);

	CountryEntity findByNameAndIsDeleted(String name, boolean b);

}

package com.api.epharmacy.io.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.api.epharmacy.io.entity.CompanyEntity;

@Repository
public interface CompanyRepository extends PagingAndSortingRepository<CompanyEntity, Long>{
	
	CompanyEntity findByCompanyId(long companyId);

	Page<CompanyEntity> findByCompanyNameContainingOrLicenceNumberContainingOrTinNumberContainingOrAccountIdContainingOrCompanyStatusContaining(
			String searchKey, String searchKey2, String searchKey3, String searchKey4, String searchKey5,
			Pageable pageableRequest);
}

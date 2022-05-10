package com.api.epharmacy.io.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.api.epharmacy.io.entity.PrivilegeEntity;

public interface PrivilegeRepository extends PagingAndSortingRepository<PrivilegeEntity, Integer> {


	PrivilegeEntity findByPrivilegeIdAndIsDeleted(Integer privilegeId, boolean b);

	Page<PrivilegeEntity> findByIsDeleted(boolean b, Pageable pageableRequest);

	List<PrivilegeEntity> findByIsDeleted(boolean b);



	List<PrivilegeEntity> findByPrivilegeUrlAndMethodAndIsDeleted(String privilegeUrl, String method, boolean b);



	

	List<PrivilegeEntity> findByPrivilegeName(String privilegeName);

	List<PrivilegeEntity> findByPrivilegeNameContainingOrPrivilegeUrlContainingOrPrivilegeDescriptionContainingAndIsDeleted(
			String searchKey, String searchKey2, String searchKey3, boolean b);



}

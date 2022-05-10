package com.api.epharmacy.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.epharmacy.exception.AppException;
import com.api.epharmacy.io.entity.RoleEntity;
import com.api.epharmacy.io.repositories.RoleRepository;
import com.api.epharmacy.service.RolesService;
import com.api.epharmacy.ui.model.request.AddRoleRequestModel;
import com.api.epharmacy.ui.model.response.RoleResponseModel;

@Service
public class RolesServiceImpl implements RolesService {

	@Autowired
    RoleRepository roleRepository;
	
	@Override
	public String addRole(AddRoleRequestModel roleDetails) {

		String returnValue = "";
		RoleEntity roleEntity = new RoleEntity();
		BeanUtils.copyProperties(roleDetails, roleEntity);
		roleEntity.setRoleFullName("ROLE_" + roleEntity.getRoleName());
		RoleEntity savedRole = roleRepository.save(roleEntity);
		if(savedRole != null) {
			returnValue="Role Added";
		}
		return returnValue;
	}

	@Override
	public List<RoleResponseModel> getRoles() {
		
		List<RoleResponseModel> returnValue = new ArrayList<>();
		
		//List<InventoryPriceListEntity> priceLists = inventoryPriceListRepository.findByInventoryId(inventoryId);
		List<RoleEntity> roles= (List<RoleEntity>) roleRepository.findAll();
		
		for(RoleEntity role : roles) {
			RoleResponseModel roleResponseModel = new RoleResponseModel(); 
	    	BeanUtils.copyProperties(role, roleResponseModel);	    	
	    	returnValue.add(roleResponseModel);
	    }
		
		return returnValue;
	}

	@Override
	public String updateRole(long roleId, AddRoleRequestModel roleDetails) {
		
		String returnValue = "";
		/*
		 * RoleEntity roleEntity = roleRepository.findAllById(roleId); if(roleEntity ==
		 * null) throw new AppException("User RoleEntity not found.");
		 * 
		 * BeanUtils.copyProperties(roleDetails, roleEntity);
		 * roleEntity.setRoleFullName("ROLE_" + roleDetails.getRoleName()); RoleEntity
		 * savedRole = roleRepository.save(roleEntity); if(savedRole != null) {
		 * returnValue="Role Updated"; }
		 */
		
		return returnValue;
	}

}

package com.api.epharmacy.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.epharmacy.service.RolesService;
import com.api.epharmacy.ui.model.request.AddRoleRequestModel;
import com.api.epharmacy.ui.model.response.RoleResponseModel;

@RestController
@RequestMapping("/roles")
public class RolesController {
	
	@Autowired
	RolesService rolesService;
	
	@GetMapping
	public List<RoleResponseModel> getRoles() {
		
		List<RoleResponseModel> returnValue = rolesService.getRoles();
		
		return returnValue;
	}

	@PostMapping(path="/addroles")
	public String addRole(@RequestBody AddRoleRequestModel roleDetails) {
		
		String returnValue = rolesService.addRole(roleDetails);
		
		return returnValue;
	}
	
	@PutMapping(path="/{roleId}")
	public String updateRole(@PathVariable long roleId,@RequestBody AddRoleRequestModel roleDetails) {
		
		String returnValue = rolesService.updateRole(roleId,roleDetails);
		
		return returnValue;
	}
}

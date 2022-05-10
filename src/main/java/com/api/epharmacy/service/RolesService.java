package com.api.epharmacy.service;

import java.util.List;

import com.api.epharmacy.ui.model.request.AddRoleRequestModel;
import com.api.epharmacy.ui.model.response.RoleResponseModel;

public interface RolesService {

	String addRole(AddRoleRequestModel roleDetails);

	List<RoleResponseModel> getRoles();

	String updateRole(long roleId, AddRoleRequestModel roleDetails);

}

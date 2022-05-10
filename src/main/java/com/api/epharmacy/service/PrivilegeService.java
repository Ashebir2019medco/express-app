package com.api.epharmacy.service;

import java.util.List;

import com.api.epharmacy.ui.model.request.PrivilegeRequestModel;
import com.api.epharmacy.ui.model.request.RolePrivilegeRequestModel;
import com.api.epharmacy.ui.model.response.PrivilegeResponseModel;
import com.api.epharmacy.ui.model.response.RolePrivilegesResponseModel;

public interface PrivilegeService {

	PrivilegeResponseModel savePrivilege(PrivilegeRequestModel privilegeDetail);

	PrivilegeResponseModel getPrivilege(Integer privilegeId);

	PrivilegeResponseModel updatePrivilege(PrivilegeRequestModel privilegeDetail, Integer privilegeId);

	List<PrivilegeResponseModel> getAllPrivileges(int page, int limit, String searchKey,Long roleId);

	String deletePrivilege(Integer privilegeId);

	String saveRolePrivilegeData(RolePrivilegeRequestModel rolePrivilegeDetail);

	String updateRolePrivilegeData(RolePrivilegeRequestModel rolePrivilegeDetail, Long rolePrivilegeId);

	String deleteRolePrivilageData(Long rolePrivilegeId);

	List<RolePrivilegesResponseModel> getRolePrivileges(Long roleId);
	

}

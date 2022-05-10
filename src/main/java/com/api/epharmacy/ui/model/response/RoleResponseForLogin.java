package com.api.epharmacy.ui.model.response;

import java.util.List;

public class RoleResponseForLogin {

	private Long id;
	private String roleName;
	private String roleFullName;
	List<PrivilegeResponseModel> privileges;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleFullName() {
		return roleFullName;
	}

	public void setRoleFullName(String roleFullName) {
		this.roleFullName = roleFullName;
	}

	public List<PrivilegeResponseModel> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(List<PrivilegeResponseModel> privileges) {
		this.privileges = privileges;
	}

}

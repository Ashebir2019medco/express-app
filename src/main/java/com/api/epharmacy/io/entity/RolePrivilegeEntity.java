package com.api.epharmacy.io.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.api.epharmacy.model.audit.Audit;

@Entity(name = "role_privilege")
@Table(uniqueConstraints= @UniqueConstraint(columnNames = {"roleId", "privilegeId"}) )
public class RolePrivilegeEntity extends Audit implements Serializable {

	private static final long serialVersionUID = -3171415245066163480L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long rolePrivilegeId;

	@Column(nullable = false)
	private Long roleId;

	@Column
	private boolean isPrivilaged = false;

	@Column
	private boolean isDeleted = false;

	@Column(nullable = false, length = 50)
	private Integer privilegeId;

	public Long getRolePrivilegeId() {
		return rolePrivilegeId;
	}

	public void setRolePrivilegeId(Long rolePrivilegeId) {
		this.rolePrivilegeId = rolePrivilegeId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Integer getPrivilegeId() {
		return privilegeId;
	}

	public void setPrivilegeId(Integer privilegeId) {
		this.privilegeId = privilegeId;
	}

	public boolean isPrivilaged() {
		return isPrivilaged;
	}

	public void setPrivilaged(boolean isPrivilaged) {
		this.isPrivilaged = isPrivilaged;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	@Override
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

}

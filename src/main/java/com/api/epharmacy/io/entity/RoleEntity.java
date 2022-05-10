package com.api.epharmacy.io.entity;


import javax.persistence.*;

@Entity
@Table(name = "roles")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String roleName;
    
    @Column(nullable = false, unique = true)
    private String roleFullName;
    
   
    public RoleEntity(){
    	
    }

	public String getRoleFullName() {
		return roleFullName;
	}

	public void setRoleFullName(String roleFullName) {
		this.roleFullName = roleFullName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    

}

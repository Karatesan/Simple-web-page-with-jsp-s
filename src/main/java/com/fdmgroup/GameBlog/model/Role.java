package com.fdmgroup.GameBlog.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Role implements GrantedAuthority {

	@Id
	@GeneratedValue
	private Integer roleId;
	
	private String roleName;
	
	public Role() {
		
	}

	public Role(String roleName) {
		super();
		this.roleName = roleName;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return "ROLE_" + roleName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(roleName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		return Objects.equals(roleName, other.roleName);
	}

	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", roleName=" + roleName + "]";
	}
	
	
	
}

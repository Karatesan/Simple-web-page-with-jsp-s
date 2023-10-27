package com.fdmgroup.GameBlog.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.GameBlog.model.Role;
import com.fdmgroup.GameBlog.repository.RoleRepository;

@Service
public class RoleService {
	
	private RoleRepository roleRepository;

	@Autowired
	public RoleService(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}
	
	public Role findByRoleName(String roleName) {
		Optional<Role> optionalRole = roleRepository.findByRoleName(roleName);
		
		return optionalRole.orElse(new Role("default role")); 
	}

}

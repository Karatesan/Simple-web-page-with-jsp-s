package com.fdmgroup.GameBlog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.GameBlog.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{

	Optional<Role> findByRoleName(String roleName);
	
}

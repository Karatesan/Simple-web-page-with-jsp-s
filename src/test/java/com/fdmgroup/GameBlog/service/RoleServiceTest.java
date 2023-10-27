package com.fdmgroup.GameBlog.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fdmgroup.GameBlog.repository.RoleRepository;
import com.fdmgroup.GameBlog.service.RoleService;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class RoleServiceTest {

	@InjectMocks
	RoleService roleService;
	
	@MockBean
	RoleRepository mockRepository;
	
	@Test
	public void test_findByRoleName_callsFindByRoleNameMethodOfRoleRepository() {
		roleService.findByRoleName("testRole");

		verify(mockRepository, times(1)).findByRoleName("testRole");
	}
	
	
}

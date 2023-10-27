package com.fdmgroup.GameBlog.security;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fdmgroup.GameBlog.model.User;
import com.fdmgroup.GameBlog.repository.UserRepository;
import com.fdmgroup.GameBlog.security.DefaultUserDetailService;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class DefaultUserServiceTest {

	@InjectMocks
	DefaultUserDetailService service;
	
	@MockBean
	UserRepository mockRepository;
	
	@Mock
	User mockUser;
	
	@Test
	public void test_findByUsername_callsFindByUsernameMethodOfUserRepository() {
		service.findByUsername("testUser");
		
		verify(mockRepository, times(1)).findByUsername("testUser");
		
	}
	
	@Test
	public void test_saveUser_callsSaveMethodOfUserRepository() {
		service.saveUser(mockUser);
		
		verify(mockRepository, times(1)).save(mockUser);
		
	}
	
	@Test
	public void test_loadUserByUsername_callsFindByUsername_andReturns_userPrincipal() {
	
		service.loadUserByUsername("testUser");
		
		verify(mockRepository, times(1)).findByUsername("testUser");	
	}
	
	@Test
	public void test_listAllUsers_callsFindAllMethodOfUserRepository() {
	
		service.listAllUsers();
		
		verify(mockRepository, times(1)).findAll();	
	}
	
}

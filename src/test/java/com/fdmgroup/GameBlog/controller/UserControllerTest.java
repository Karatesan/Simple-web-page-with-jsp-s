package com.fdmgroup.GameBlog.controller;
//package com.fdmgroup.Group4ProjectShazar.controller;
//
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//
//import com.fdmgroup.Group4ProjectShazar.Group4ProjectShazarApplication;
//import com.fdmgroup.Group4ProjectShazar.model.Address;
//import com.fdmgroup.Group4ProjectShazar.model.Role;
//import com.fdmgroup.Group4ProjectShazar.model.User;
//import com.fdmgroup.Group4ProjectShazar.security.DefaultUserDetailService;
//import com.fdmgroup.Group4ProjectShazar.service.AddressService;
//import com.fdmgroup.Group4ProjectShazar.service.CartService;
//import com.fdmgroup.Group4ProjectShazar.service.RoleService;
//
//@SpringBootTest(classes = {UserController.class})
//@ExtendWith(SpringExtension.class)
//@ExtendWith(MockitoExtension.class)
//@AutoConfigureMockMvc(addFilters = false)
//@ContextConfiguration(classes = Group4ProjectShazarApplication.class)
//public class UserControllerTest {
//
//	@MockBean
//	private DefaultUserDetailService mockDefaultUserService;
//	
//	@MockBean
//	private RoleService mockRoleService;
//	
//	@MockBean
//	private MainController mockMainController;
//	
//
//	
//	@MockBean
//	private PasswordEncoder mockPasswordEncoder;
//	
//	@Autowired
//	private MockMvc mockMvc;
//	
//	@Mock
//	private User mockUser;
//
//	@Mock
//	private Role mockRole;
//	
//
//	@Test
//	@WithMockUser
//	public void test_goToLogin_statusOk() throws Exception {
//			
//		mockMvc.perform(get("/login/")).andExpect(status().isOk()).andExpect(view().name("login"));
//	}
//	
//	@Test
//	@WithMockUser
//	public void test_loginError_addAttributeErrorMessage_statusOk() throws Exception {
//			
//		mockMvc.perform(get("/login-error")).andExpect(status().isOk()).andExpect(model().attribute("errorMessage", "Invalid username or password")).andExpect(view().name("login"));
//	}
//	
//	@Test
//	@WithMockUser
//	public void test_goToRegisterPage_statusOk() throws Exception {
//			
//		mockMvc.perform(get("/goRegisterPage")).andExpect(status().isOk()).andExpect(view().name("register"));
//	}
//	
//	// TESTS FOR REGISTER
//	@Test
//	@WithMockUser
//	public void test_registerUser_whenUsernameAlreadyInDatabase_returnRegister_AddAttributeErrorMessage() throws Exception {
//	
//		Role role = new Role("Customer");
//		Address address = new Address("teststreet", "1a", 12345, "testcity", "testcountry");
//		User user = new User("testUser", "pass", "firstname", "lastname", "123456", "test@email.com", "city", 4.6, 5, address, role);
//				
//		System.out.println("TEST " + user);
//	
//		when(mockDefaultUserService.findByUsername("testUser")).thenReturn(user);
//		
//		mockMvc.perform(post("/register/").param("username", "testUser").param("street", "teststreet").param("houseNumber", "1").param("postalCode", "12345").param("city", "testCity").param("country", "testCountry").param("confirmPassword", "pass")).andExpect(status().isOk()).andExpect(model().attribute("errorMessage", "This user name already exists")).andExpect(view().name("register"));
//		
//	
//	}
//	
//	@Test
//	@WithMockUser
//	public void test_registerUser_whenUsernameEqualsAnonymousUser_returnRegister_AddAttributeErrorMessage() throws Exception {
//	
//		Role role = new Role("Customer");
//		Address address = new Address("teststreet", "1a", 12345, "testcity", "testcountry");
//		User user = new User("testUser", "pass", "firstname", "lastname", "123456", "test@email.com", "city", 4.6, 5, address, role);
//				
//		System.out.println("TEST " + user);
//	
//		when(mockDefaultUserService.findByUsername("anonymousUser")).thenReturn(new User("default user"));
//		
//		mockMvc.perform(post("/register/").param("username", "anonymousUser").param("street", "teststreet").param("houseNumber", "1").param("postalCode", "12345").param("city", "testCity").param("country", "testCountry").param("confirmPassword", "pass")).andExpect(status().isOk()).andExpect(model().attribute("errorMessage", "This user name already exists")).andExpect(view().name("register"));
//			
//	}
//	
//	@Test
//	@WithMockUser
//	public void test_registerUser_whenUsernameEqualsDefault_Username_returnRegister_AddAttributeErrorMessage() throws Exception {
//	
//		Role role = new Role("Customer");
//		Address address = new Address("teststreet", "1a", 12345, "testcity", "testcountry");
//		User user = new User("testUser", "pass", "firstname", "lastname", "123456", "test@email.com", "city", 4.6, 5, address, role);
//				
//		System.out.println("TEST " + user);
//	
//		when(mockDefaultUserService.findByUsername("default username")).thenReturn(new User("default user"));
//		
//		mockMvc.perform(post("/register/").param("username", "default username").param("street", "teststreet").param("houseNumber", "1").param("postalCode", "12345").param("city", "testCity").param("country", "testCountry").param("confirmPassword", "pass")).andExpect(status().isOk()).andExpect(model().attribute("errorMessage", "This user name already exists")).andExpect(view().name("register"));
//			
//	}
//	
//	@Test
//	@WithMockUser
//	public void test_registerUser_whenPasswordAndConfirmPasswordNotTheSame_returnRegister_AddAttributeErrorMessage() throws Exception {
//	
//		Role role = new Role("Customer");
//		Address address = new Address("teststreet", "1a", 12345, "testcity", "testcountry");
//		User user = new User("testUser", "pass", "firstname", "lastname", "123456", "test@email.com", "city", 4.6, 5, address, role);
//				
//		System.out.println("TEST " + user);
//	
//		when(mockDefaultUserService.findByUsername("testUser")).thenReturn(new User("default user"));
//		
//		mockMvc.perform(post("/register/").param("username", "testUser").param("password", "pass").param("street", "teststreet").param("houseNumber", "1").param("postalCode", "12345").param("city", "testCity").param("country", "testCountry").param("confirmPassword", "notPass")).andExpect(status().isOk()).andExpect(model().attribute("errorMessage", "Entries in Password and Confirm Password are not the same")).andExpect(view().name("register"));
//			
//	}
//	
//	@Test
//	@WithMockUser
//	public void test_registerUser_whenUsernameNotInDatabase_returnIndex_saveUserInDatabase() throws Exception {
//		
//		Role role = new Role("Customer");
//		Address address = new Address("teststreet", "1a", 12345, "testcity", "testcountry");
//		User user = new User("testUser", "pass", "firstname", "lastname", "123456", "test@email.com", "city", 4.6, 5, address, role);
//				
//		System.out.println("TEST " + user);
//		
//		when(mockDefaultUserService.findByUsername("testUser")).thenReturn(new User("default user"));
//		
//		mockMvc.perform(post("/register/").param("username", "testUser").param("password", "pass").param("street", "teststreet").param("houseNumber", "1").param("postalCode", "12345").param("city", "testCity").param("country", "testCountry").param("confirmPassword", "pass")).andExpect(status().isOk()).andExpect(view().name("index"));
//	
//	}
//	
//	// TESTS FOR CHANGE PASSWORD
//	@Test
//	@WithMockUser
//	public void test_goChangePassword_statusOk() throws Exception {
//			
//		mockMvc.perform(get("/goChangePasswordPage")).andExpect(status().isOk()).andExpect(view().name("changePassword"));
//	}
//	
//	@Test
//	@WithMockUser
//	public void test_changePassword_currentPasswordWrong_addAttributeErrorMessage_returnViewChangePassword() throws Exception {
//		
//		Role role = new Role("Customer");
//		Address address = new Address("teststreet", "1a", 12345, "testcity", "testcountry");
//		User user = new User("testUser", "pass", "firstname", "lastname", "123456", "test@email.com", "city", 4.6, 5, address, role);
//		
//		when(mockDefaultUserService.findByUsername("testUser")).thenReturn(user);
//		
//		mockMvc.perform(post("/changePassword/").param("username", "testUser").param("currentPassword", "abc").param("newPassword", "abcd").param("confirmNewPassword", "abcd")).andExpect(status().isOk()).andExpect(model().attribute("errorMessage", "Wrong current password")).andExpect(view().name("changePassword"));
//	}
//	
//	@Test
//	@WithMockUser
//	public void test_changePassword_newPasswordAndConfirmPasswordNotTheSame_addAttributeErrorMessage_returnViewChangePassword() throws Exception {
//		
//		Role role = new Role("Customer");
//		Address address = new Address("teststreet", "1a", 12345, "testcity", "testcountry");
//		User user = new User("testUser", "password", "firstname", "lastname", "123456", "test@email.com", "city", 4.6, 5, address, role);
//		
//		when(mockDefaultUserService.findByUsername("testUser")).thenReturn(user);
//		
//		mockMvc.perform(post("/changePassword/").param("username", "testUser").param("currentPassword", "password").param("newPassword", "abc").param("confirmNewPassword", "abcd")).andExpect(status().isOk()).andExpect(model().attribute("errorMessage", "Different entries in 'New Password' and 'Confirm New Password'. Please try again")).andExpect(view().name("changePassword"));
//	}
//	
//	@Test
//	@WithMockUser
//	public void test_changePassword_newPasswordCorrect_addAttributeMessage_returnViewShowProfile() throws Exception {
//		
//		Role role = new Role("Customer");
//		Address address = new Address("teststreet", "1a", 12345, "testcity", "testcountry");
//		User user = new User("testUser", "password", "firstname", "lastname", "123456", "test@email.com", "city", 4.6, 5, address, role);
//		
//		when(mockDefaultUserService.findByUsername("testUser")).thenReturn(user);
//		when(mockPasswordEncoder.matches("password", user.getPassword())).thenReturn(true);
//		
//		mockMvc.perform(post("/changePassword/").param("username", "testUser").param("currentPassword", "password").param("newPassword", "abc").param("confirmNewPassword", "abc")).andExpect(model().attribute("message", "Password changed successfully")).andExpect(view().name("showProfile"));
//	}
//	
//	// Test For Reset Password
//	@Test
//	@WithMockUser
//	public void test_goQuestionPassword_statusOk() throws Exception {
//			
//		mockMvc.perform(get("/goQuestionPassword")).andExpect(status().isOk()).andExpect(view().name("questionPassword"));
//	}
//	
//	@Test
//	@WithMockUser
//	public void test_questionPassword_usernameNotInDatabase_addAttributeErrorMessage_returnViewQuestionPassword() throws Exception {
//		
//		when(mockDefaultUserService.findByUsername("testUser")).thenReturn(new User("default username"));
//			
//		mockMvc.perform(post("/questionPassword/").param("username", "testUser").param("answer", "abcd")).andExpect(model().attribute("errorMessage", "No User with this username")).andExpect(view().name("questionPassword"));
//	}
//	
//	
//	@Test
//	@WithMockUser
//	public void test_questionPassword_wrongAnswer_addAttributeErrorMessage_returnViewQuestionPassword() throws Exception {
//		Role role = new Role("Customer");
//		Address address = new Address("teststreet", "1a", 12345, "testcity", "testcountry");
//		User user = new User("testUser", "password", "firstname", "lastname", "123456", "test@email.com", "favouritecity", 4.6, 5, address, role);
//		
//		when(mockDefaultUserService.findByUsername("testUser")).thenReturn(user);
//			
//		mockMvc.perform(post("/questionPassword/").param("username", "testUser").param("answer", "wrongcity")).andExpect(model().attribute("errorMessage", "Something went wrong")).andExpect(view().name("questionPassword"));
//	}
//	
//	@Test
//	@WithMockUser
//	public void test_questionPassword_rightAnswer_addAttributeUser_returnViewReturnPassword() throws Exception {
//		Role role = new Role("Customer");
//		Address address = new Address("teststreet", "1a", 12345, "testcity", "testcountry");
//		User user = new User("testUser", "password", "firstname", "lastname", "123456", "test@email.com", "favouritecity", 4.6, 5, address, role);
//		
//		when(mockDefaultUserService.findByUsername("testUser")).thenReturn(user);
//			
//		mockMvc.perform(post("/questionPassword/").param("username", "testUser").param("answer", "favouritecity")).andExpect(model().attribute("user", user)).andExpect(view().name("resetPassword"));
//	}
//	
//	@Test
//	@WithMockUser
//	public void test_questionPassword_rightAnswerInDifferentCase_addAttributeUser_returnViewReturnPassword() throws Exception {
//		Role role = new Role("Customer");
//		Address address = new Address("teststreet", "1a", 12345, "testcity", "testcountry");
//		User user = new User("testUser", "password", "firstname", "lastname", "123456", "test@email.com", "favouritecity", 4.6, 5, address, role);
//		
//		when(mockDefaultUserService.findByUsername("testUser")).thenReturn(user);
//			
//		mockMvc.perform(post("/questionPassword/").param("username", "testUser").param("answer", "FaVoUriTeCitY")).andExpect(model().attribute("user", user)).andExpect(view().name("resetPassword"));
//	}
//	
//	
//	@Test
//	@WithMockUser
//	public void test_resetPassword_newPasswordAndConfirmNewPasswordWrong_addAttributeErrorMessage_returnViewChangePassword() throws Exception {
//		
//		mockMvc.perform(post("/resetPassword/").param("newPassword", "abcd").param("confirmNewPassword", "abc")).andExpect(model().attribute("errorMessage", "Different entries in 'New Password' and 'Confirm New Password'. Please try again")).andExpect(view().name("changePassword"));
//	}
//	
//	@Test
//	@WithMockUser
//	public void test_resetPassword_newPasswordAndConfirmNewPasswordTheSame_addAttributeMessage_returnViewIndex() throws Exception {
//		Role role = new Role("Customer");
//		Address address = new Address("teststreet", "1a", 12345, "testcity", "testcountry");
//		User user = new User("testUser", "password", "firstname", "lastname", "123456", "test@email.com", "city", 4.6, 5, address, role);
//		
//		when(mockDefaultUserService.findByUsername("testUser")).thenReturn(user);
//		
//		mockMvc.perform(post("/resetPassword/").param("username", "testUser").param("newPassword", "abcd").param("confirmNewPassword", "abcd")).andExpect(model().attribute("message", "Password changed successfully")).andExpect(view().name("index"));
//	}
//	
//}

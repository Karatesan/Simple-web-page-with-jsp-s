package com.fdmgroup.GameBlog.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fdmgroup.GameBlog.model.User;
import com.fdmgroup.GameBlog.security.DefaultUserDetailService;
import com.fdmgroup.GameBlog.service.RoleService;

@ExtendWith(MockitoExtension.class)
public class UseControllerTest {


    @Mock
    private DefaultUserDetailService defaultUserService;
    
    @Mock
    private PasswordEncoder passwordEncoder;
    
    @Mock
    private RoleService roleService;

    @Mock
    private MainController mainController;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void goToLogin() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("loginPage"));
    }
    
    @Test
    void loginError() throws Exception {
        mockMvc.perform(get("/login-error"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("errorMessage", "Invalid username or password"))
                .andExpect(view().name("loginPage"));
    }

    @Test
    void goToRegisterPage() throws Exception {
        mockMvc.perform(get("/goRegisterPage"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"));
    }
    
    @Test
    public void registerUserTest() throws Exception {
        mockMvc.perform(post("/register")
                        .param("username", "testuser")
                        .param("password", "password")
                        .param("email", "test@email.com")
                        .param("confirmPassword", "password"))
               .andExpect(status().isOk())
               .andExpect(view().name("index"));
    }
    
    @Test
    public void testRegisterUser_existingUsername() throws Exception {
        mockMvc.perform(post("/register")
                .param("username", "existingUsername")
                .param("password", "password")
                .param("email", "email@example.com")
                .param("confirmPassword", "password"))
            .andExpect(status().isOk())
            .andExpect(view().name("register"));
    }
    
    @Test
    public void testGoQuestionPassword() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/goQuestionPassword");
        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.view().name("questionPassword"));
    }

    @Test
    public void testQuestionPassword_validUsernameAndAnswer() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/questionPassword")
            .param("username", "validUsername")
            .param("answer", "validAnswer");

        User userFromDatabase = new User();
        userFromDatabase.setUsername("validUsername");
        userFromDatabase.setAnswerQuestion("validAnswer");

        when(defaultUserService.findByUsername("validUsername")).thenReturn(userFromDatabase);

        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.view().name("resetPassword"))
            .andExpect(MockMvcResultMatchers.model().attributeExists("user"));
    }
    

	@Test
	public void testQuestionPassword_invalidUsername() throws Exception {
	    RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/questionPassword")
	        .param("username", "invalidUsername")
	        .param("answer", "validAnswer");
	
	    when(defaultUserService.findByUsername("invalidUsername")).thenReturn(null);
	
	    mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk())
	        .andExpect(MockMvcResultMatchers.view().name("questionPassword"))
	        .andExpect(MockMvcResultMatchers.model().attributeExists("errorMessage"));
	}
	
	@Test
	public void testQuestionPassword_invalidAnswer() throws Exception {
	    RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/questionPassword")
	        .param("username", "validUsername")
	        .param("answer", "invalidAnswer");
	
	    User userFromDatabase = new User();
	    userFromDatabase.setUsername("validUsername");
	    userFromDatabase.setAnswerQuestion("validAnswer");
	
	    when(defaultUserService.findByUsername("validUsername")).thenReturn(userFromDatabase);
	
	    mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk())
	        .andExpect(MockMvcResultMatchers.view().name("questionPassword"))
	        .andExpect(MockMvcResultMatchers.model().attributeExists("errorMessage"));
	}
}

package com.fdmgroup.GameBlog.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fdmgroup.GameBlog.Blog2manProjectApplication;
import com.fdmgroup.GameBlog.controller.MainController;
import com.fdmgroup.GameBlog.security.DefaultUserDetailService;

@SpringBootTest(classes = { MainController.class })
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(classes = Blog2manProjectApplication.class)
public class MainControllerTest {

	@MockBean
	private DefaultUserDetailService defaultUserService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	@WithMockUser
	public void test_goToIndex_statusOk() throws Exception {
		mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("index"));
	}

	@Test
	@WithMockUser
	public void test_goToFAQ_statusOk() throws Exception {

		mockMvc.perform(get("/goToFAQ")).andExpect(status().isOk()).andExpect(view().name("FAQ"));
	}

	@Test
	@WithMockUser
	public void test_toPrivacyPolicy_statusOk() throws Exception {

		mockMvc.perform(get("/toPrivacyPolicy")).andExpect(status().isOk()).andExpect(view().name("privacy"));
	}

	@Test
	@WithMockUser
	public void test_toSearchingPage_statusOk() throws Exception {

		mockMvc.perform(get("/toTermsAndConditions")).andExpect(status().isOk())
				.andExpect(view().name("termsCondition"));
	}
	
	@Test
	@WithMockUser
	public void test_sendMessageToAdmin_statusOk() throws Exception {

		mockMvc.perform(post("/sendMessageToAdmin").param("username", "testUser").param("email", "test@email.com").param("question", "test question")).andExpect(status().isOk()).andExpect(model().attribute("message", "Your message has been sent to support team"))
				.andExpect(view().name("FAQ"));
	}

}

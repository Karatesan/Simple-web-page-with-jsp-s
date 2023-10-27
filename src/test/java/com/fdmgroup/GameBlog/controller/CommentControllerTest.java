package com.fdmgroup.GameBlog.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.ModelMap;

import com.fdmgroup.GameBlog.Blog2manProjectApplication;
import com.fdmgroup.GameBlog.model.BlogPost;
import com.fdmgroup.GameBlog.model.Comment;
import com.fdmgroup.GameBlog.model.Role;
import com.fdmgroup.GameBlog.model.User;
import com.fdmgroup.GameBlog.security.DefaultUserDetailService;
import com.fdmgroup.GameBlog.service.BlogPostService;
import com.fdmgroup.GameBlog.service.CommentService;



@SpringBootTest(classes = { CommentController.class })
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(classes = Blog2manProjectApplication.class)


public class CommentControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CommentService commentService;
	
	@MockBean
	private MainController mainController;
	
	@MockBean
	private DefaultUserDetailService userService;
	
	@MockBean
	private BlogPostService blogPostService;
	
	@Mock
    private Authentication auth;

	@Test
	public void addCommentTest() throws Exception {
		Role role = new Role("Author");
		User commenter = new User("testuser", "testpassword", "test@email.com", "User", role);
		Optional<BlogPost> article = Optional.of(new BlogPost(commenter, "test", "test", 0, LocalDateTime.now()));
		article.get().setLikesList(new ArrayList<>());
		article.get().setComments(new ArrayList<>());
		Comment comment = new Comment(article.get(), "testcontent", commenter, LocalDateTime.now());
		
		when(userService.findByUsername("testuser")).thenReturn(commenter);
		when(blogPostService.getPostById(1)).thenReturn(article);
		
		mockMvc.perform(post("/addComment")
				.param("username", "testuser")
				.param("articleId", "1")
				.param("content", "testcontent"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("blogPost"))
				.andExpect(model().attributeExists("confirmation"))
				.andExpect(view().name("post"));
	}
    
    @Test
    void removeComment_ForAdmin_ShouldReturnCommentsOfUserView() throws Exception {
    	Role role = new Role("Author");
    	String username = "testuser";
		User commenter = new User("testuser", "testpassword", "test@email.com", "User", role);
        User LoggedUser = commenter;
        BlogPost post = new BlogPost(LoggedUser, "title", "content", 0, LocalDateTime.now());
        Comment toDelete = new Comment(post, "content", commenter, LocalDateTime.now());
        List<Comment> commentsFromUser = Arrays.asList(toDelete);
        

        SecurityContextHolder.getContext().setAuthentication(auth);

        when(SecurityContextHolder.getContext().getAuthentication().getName()).thenReturn(username);
        when(userService.findByUsername(username)).thenReturn(LoggedUser);
        when(commentService.findById(1)).thenReturn(toDelete);
        when(commentService.findAllCommentsFromUser(commenter)).thenReturn(commentsFromUser);

        mockMvc.perform(post("/removeComment")
                .param("commentId", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("post"));
               // .andExpect(model().attribute("comments", commentsFromUser));
        
        verify(commentService).deleteComment(toDelete);
        verify(mainController).populateLoggedUserModel(any(ModelMap.class));
    }
}

package com.fdmgroup.GameBlog.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fdmgroup.GameBlog.Blog2manProjectApplication;
import com.fdmgroup.GameBlog.model.BlogPost;
import com.fdmgroup.GameBlog.model.Comment;
import com.fdmgroup.GameBlog.model.LikeDislike;
import com.fdmgroup.GameBlog.model.User;
import com.fdmgroup.GameBlog.security.DefaultUserDetailService;
import com.fdmgroup.GameBlog.service.BlogPostService;
import com.fdmgroup.GameBlog.service.CommentService;
import com.fdmgroup.GameBlog.service.LikeDislikeService;

@SpringBootTest(classes = { LikesController.class })
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(classes = Blog2manProjectApplication.class)
public class LikesControllerTest {
	
	
	 @Autowired
	    private MockMvc mockMvc;


	    @MockBean
	    private BlogPostService blogPostService;

	    @MockBean
	    private MainController mainController;

	    @MockBean
	    private DefaultUserDetailService userService;

	    @MockBean
	    private LikeDislikeService likeService;

	    @Test
	    public void testAddLike() throws Exception {
	        int blogPostId = 1;
	        String rating = LikeDislike.LIKE;
	        String username = "testuser";

	        BlogPost blogPost = new BlogPost();
	        blogPost.setBlogPostId(blogPostId);
	        blogPost.setLikes(0);
	        blogPost.setLikesList(new ArrayList<>());
	        Optional<BlogPost> blogPostOptional = Optional.of(blogPost);

	        User ratingUser = new User();
	        ratingUser.setUsername(username);
	        Optional<LikeDislike> opt = Optional.empty();

	        LikeDislike like = new LikeDislike(ratingUser, blogPost, rating);

	        when(blogPostService.getPostById(blogPostId)).thenReturn(blogPostOptional);
	        when(userService.findByUsername(username)).thenReturn(ratingUser);
	        when(likeService.findByUserAndBlog(ratingUser, blogPost)).thenReturn(opt);
	        doNothing().when(likeService).remove(opt.orElse(null));
	        when(blogPostService.save(blogPost)).thenReturn(blogPost);

	        mockMvc.perform(post("/likes/{blogPostId}", blogPostId)
	                .param("rating", rating)
	                .param("username", username))
	                .andExpect(status().isOk())
	                .andExpect(model().attribute("blogPost", blogPost))
	                .andExpect(view().name("post"));

	        verify(blogPostService, times(1)).getPostById(blogPostId);
	        verify(userService, times(1)).findByUsername(username);
	        verify(likeService, times(1)).findByUserAndBlog(ratingUser, blogPost);
	        verify(likeService, times(0)).remove(opt.orElse(null));
	        verify(blogPostService, times(1)).save(blogPost);
	    }
	

	    @Test
	    public void addLike_FirstRating_ShouldUpdateRatingAndSaveBlogPost() throws Exception {
	        BlogPost blogPost = new BlogPost();
	        blogPost.setLikes(0);
	        blogPost.setLikesList(new ArrayList<>());

	        User ratingUser = new User();
	        ratingUser.setUsername("test-user");

	        when(blogPostService.getPostById(1)).thenReturn(Optional.of(blogPost));
	        when(userService.findByUsername("test-user")).thenReturn(ratingUser);
	        when(likeService.findByUserAndBlog(ratingUser, blogPost)).thenReturn(Optional.empty());

	        mockMvc.perform(post("/likes/1")
	                .param("rating", LikeDislike.LIKE)
	                .param("username", "test-user"))
	                .andExpect(model().attribute("blogPost", blogPost))
	                .andExpect(view().name("post"));

	        verify(blogPostService).save(blogPost);
	       // verify(likeService).save(any(LikeDislike.class));
	        //verify(mainController).populateLoggedUserModel(any(ModelMap.class));

	        assertEquals(1, blogPost.getLikes());
	    }

//	    @Test
//	    public void addLike_ChangeRating_ShouldUpdateRatingAndRemoveOldLikeDislike() throws Exception {
//	        BlogPost blogPost = new BlogPost();
//	        blogPost.setLikes(0);
//
//	        User ratingUser = new User();
//	        ratingUser.setUsername("test-user");
//
//	        LikeDislike oldLike = new LikeDislike(ratingUser, blogPost, LikeDislike.DISLIKE);
//
//	        when(blogPostService.getPostById(1)).thenReturn(Optional.of(blogPost));
//	        when(userService.findByUsername("test-user")).thenReturn(ratingUser);
//	        when(likeService.findByUserAndBlog(ratingUser, blogPost)).thenReturn(Optional.of(oldLike));
//
//	        mockMvc.perform(post("/likes/1")
//	                .param("rating", LikeDislike.LIKE)
//	                .param("username", "test-user"))
//	                .andExpect(model().attribute("blogPost", blogPost))
//	                .andExpect(view().name("post"));
//
//	        verify(blogPostService).save(blogPost);
//	        verify(likeService

}

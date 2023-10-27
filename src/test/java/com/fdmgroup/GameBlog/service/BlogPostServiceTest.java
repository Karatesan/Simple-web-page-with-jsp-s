package com.fdmgroup.GameBlog.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.GameBlog.model.BlogPost;
import com.fdmgroup.GameBlog.repository.BlogPostRepository;
import com.fdmgroup.GameBlog.repository.CommentRepository;

@ExtendWith(MockitoExtension.class)
public class BlogPostServiceTest {

	@Mock
	private BlogPostRepository blogPostRepository;
	
	@Mock
	private CommentRepository commentRepository;
	
	@Mock
	private LikeDislikeService likesService;
	
	@InjectMocks
	private BlogPostService blogPostService;
	
	@Test
	public void testSavePost() {
		BlogPost blogPost = new BlogPost();
		blogPost.setTitle("Test Blog Post");
		
		Mockito.when(blogPostRepository.save(blogPost)).thenReturn(blogPost);
		
		BlogPost savedBlogPost = blogPostService.savePost(blogPost);
		
		assertNotNull(savedBlogPost);
		assertEquals("Test Blog Post", savedBlogPost.getTitle());
		
		Mockito.verify(blogPostRepository).save(blogPost);
	}
	
	@Test
	public void testGetPostById() {
		Integer id = 1;
		BlogPost blogPost = new BlogPost();
		blogPost.setTitle("Test Blog Post");
		
		Mockito.when(blogPostRepository.findById(id)).thenReturn(Optional.of(blogPost));
		
		Optional<BlogPost> returnedBlogPost = blogPostService.getPostById(id);
		
		assertNotNull(returnedBlogPost);
		assertEquals("Test Blog Post", returnedBlogPost.get().getTitle());
		
		Mockito.verify(blogPostRepository).findById(id);
	}
}
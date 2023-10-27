package com.fdmgroup.GameBlog.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fdmgroup.GameBlog.Blog2manProjectApplication;
import com.fdmgroup.GameBlog.model.BlogPost;
import com.fdmgroup.GameBlog.model.LikeDislike;
import com.fdmgroup.GameBlog.model.User;
import com.fdmgroup.GameBlog.repository.LikeDislikeRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)

public class LikesDislikeServiceTest {
	
	@MockBean
	private LikeDislikeRepository repo;

	@InjectMocks
	private LikeDislikeService service;
	@Mock
	private User user;
	@Mock
	private BlogPost post;
	@Mock
	private LikeDislike rating;

//	@BeforeEach
//	public void setup() {
//		//MockitoAnnotations.initMocks(this);
//		user = new User();
//		post = new BlogPost();
//		rating = new LikeDislike();
//		rating.setRatedPost(post);
//		rating.setRatingUser(user);
//	}

	@Test
	public void testSave() {
		service.save(rating);
		verify(repo, times(1)).save(rating);
	}

	@Test
	public void testFindLike() {
		when(repo.findById(1)).thenReturn(Optional.of(rating));
		Optional<LikeDislike> result = service.findLike(1);
		assertTrue(result.isPresent());
		assertEquals(rating, result.get());
	}

	@Test
	public void testFindByUserAndBlog() {
		List<LikeDislike> ratings = new ArrayList<>();
		ratings.add(rating);
		when(repo.findByRatingUserAndRatedPost(user, post)).thenReturn(ratings);
		Optional<LikeDislike> result = service.findByUserAndBlog(user, post);
		assertTrue(result.isPresent());
		assertEquals(rating, result.get());
	}

//	@Test
//	public void testRemove() {
//		rating.setRatedPost(post);
//		service.remove(rating);
//		verify(repo, times(1)).delete(rating);
//		assertFalse(post.getLikesList().contains(rating));
//	}

	@Test
	public void testFindByRatingUser() {
		List<LikeDislike> ratings = new ArrayList<>();
		ratings.add(rating);
		when(repo.findByRatingUser(user)).thenReturn(ratings);
		List<LikeDislike> result = service.findByRatingUser(user);
		assertEquals(ratings, result);
	}

}

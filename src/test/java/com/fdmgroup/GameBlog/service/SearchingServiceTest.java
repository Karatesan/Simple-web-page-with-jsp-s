package com.fdmgroup.GameBlog.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fdmgroup.GameBlog.model.BlogPost;
import com.fdmgroup.GameBlog.repository.BlogPostRepository;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class SearchingServiceTest {

  @TestConfiguration
  static class SearchingServiceTestContextConfiguration {
    @Bean
    public SearchingService searchingService() {
      return new SearchingService();
    }
  }

  @Autowired
  private SearchingService searchingService;

  @MockBean
  private BlogPostRepository blogPostRepository;

  @BeforeEach
  public void setUp() {
    BlogPost post1 = new BlogPost(null, "title1", "content1", 0, null);
    BlogPost post2 = new BlogPost(null, "title2", "content2", 0, null);
    BlogPost post3 = new BlogPost(null, "another title", "content3", 0, null);
    List<BlogPost> posts = new ArrayList<>();
    posts.add(post1);
    posts.add(post2);
    posts.add(post3);

    Mockito.when(blogPostRepository.findByTitleIgnoreCaseStartingWith("title"))
        .thenReturn(List.of(post1, post2));
    Mockito.when(blogPostRepository.findByTitleIgnoreCaseEndingWith("title"))
        .thenReturn(List.of(post1, post2));
    Mockito.when(blogPostRepository.findByTitleIgnoreCaseContaining("title"))
        .thenReturn(List.of(post1, post2, post3));
  }

  @Test
  public void whenValidTitle_thenBlogPostsShouldBeFound() {
    String title = "title";
    List<BlogPost> found = searchingService.findPostsByTitle(title);
    assertThat(found).isNotEmpty().hasSize(2).extracting(BlogPost::getTitle)
        .containsOnly("title1", "title2");
  }

}
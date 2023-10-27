package com.fdmgroup.GameBlog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.fdmgroup.GameBlog.model.BlogPost;
import com.fdmgroup.GameBlog.model.User;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Integer> {
	
	List<BlogPost> findByTitleIgnoreCaseContaining(String title);
	List<BlogPost> findByTitleIgnoreCaseStartingWith(String title);
	List<BlogPost> findByTitleIgnoreCaseEndingWith(String title);
	List<BlogPost> findByAuthor(User author);
	
	@Query("SELECT b FROM BlogPost b ORDER BY likes DESC")
	List<BlogPost> findTopProducts(); 

}

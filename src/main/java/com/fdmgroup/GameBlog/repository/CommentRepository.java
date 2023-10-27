package com.fdmgroup.GameBlog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.GameBlog.model.BlogPost;
import com.fdmgroup.GameBlog.model.Comment;
import com.fdmgroup.GameBlog.model.User;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer>{
	
	List<Comment> findByblogPostOrderByDateAsc(BlogPost post);

	List<Comment> findByCommenter(User user);

}

package com.fdmgroup.GameBlog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.GameBlog.model.BlogPost;
import com.fdmgroup.GameBlog.model.LikeDislike;
import com.fdmgroup.GameBlog.model.User;


@Repository
public interface LikeDislikeRepository extends JpaRepository<LikeDislike, Integer> {
	

	List<LikeDislike> findByRatingUserAndRatedPost(User ratingUser, BlogPost ratedPost);
	List<LikeDislike> findByRatingUser(User ratingUser);
}

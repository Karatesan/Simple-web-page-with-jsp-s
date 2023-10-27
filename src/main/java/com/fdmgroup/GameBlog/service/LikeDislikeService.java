package com.fdmgroup.GameBlog.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.GameBlog.model.BlogPost;
import com.fdmgroup.GameBlog.model.LikeDislike;
import com.fdmgroup.GameBlog.model.User;
import com.fdmgroup.GameBlog.repository.LikeDislikeRepository;

@Service
public class LikeDislikeService {
	
	@Autowired
	private LikeDislikeRepository repo;
	
	public void save(LikeDislike rating) {
		repo.save(rating);	
	}
	
	public Optional<LikeDislike> findLike(Integer LikeId) {
		return repo.findById(LikeId);
	}
	
//	public boolean checkIfUserRated(User user, BlogPost post) {
//		List<LikeDislike> list = repo.findByRatingUserAndRatedPost(user, post);
//		return (list.size()==0)?false:true;
//	}

	public Optional<LikeDislike> findByUserAndBlog(User user, BlogPost post) {
		
		List<LikeDislike> list = repo.findByRatingUserAndRatedPost(user, post);
		if(list.size()==0) return Optional.ofNullable(null);
		 return Optional.ofNullable(list.get(0));
	}
	
	public void remove(LikeDislike rating) {
		rating.getRatedPost().removeRate(rating);
		repo.delete(rating);
	}

	public List<LikeDislike> findByRatingUser(User username) {
		
		return repo.findByRatingUser(username);
	}

}

package com.fdmgroup.GameBlog.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.fdmgroup.GameBlog.model.BlogPost;
import com.fdmgroup.GameBlog.model.Comment;
import com.fdmgroup.GameBlog.model.User;
import com.fdmgroup.GameBlog.repository.CommentRepository;

@Service
public class CommentService {
	
	@Autowired
	private CommentRepository commentRepo;
	
	@Autowired
	private BlogPostService blogService;
	
	public void save(Comment comment) {
		commentRepo.save(comment);
	}
	
	public Optional<Comment> getCommentById(Integer id){
		return commentRepo.findById(id);
	}
	
	public List<Comment> findAllCommentsFromPost(BlogPost post){
		
		return commentRepo.findByblogPostOrderByDateAsc(post);		
	}
	
	public List<Comment>findAllCommentsFromUser(User user){
		return commentRepo.findByCommenter(user);
	}
	
	public void deleteById(Integer id) {
		commentRepo.deleteById(id);
	}
	public Comment findById(Integer id) {
		
		Comment c = commentRepo.getById(id);
		
		return commentRepo.getById(id);
	}
	
	public void deleteComment(Comment c) {

		commentRepo.delete(c);
	}
}

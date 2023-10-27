package com.fdmgroup.GameBlog.controller;

import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.GameBlog.model.BlogPost;
import com.fdmgroup.GameBlog.model.Comment;
import com.fdmgroup.GameBlog.model.User;
import com.fdmgroup.GameBlog.security.DefaultUserDetailService;
import com.fdmgroup.GameBlog.service.BlogPostService;
import com.fdmgroup.GameBlog.service.CommentService;

@Controller
public class CommentController {
	
	@Autowired
	private CommentService service;
	
	@Autowired
	private MainController mainController;

	@Autowired
	private DefaultUserDetailService userService;
	
	@Autowired
	private BlogPostService blogService;
	
	@PostMapping("/addComment")
	public String addComment(ModelMap model, @RequestParam String username, @RequestParam int articleId, @RequestParam String content) {
		
		User commenter = userService.findByUsername(username);
		
		LocalDateTime date = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE_TIME;
		date= date.truncatedTo(ChronoUnit.MINUTES);
		
		Optional<BlogPost> article = blogService.getPostById(articleId);
		
		Comment comment = new Comment(article.get(), content, commenter, date);
		article.get().addComment(comment);
		service.save(comment);
		
		mainController.populateLoggedUserModel(model);
		model.addAttribute("blogPost", article.get());
		model.addAttribute("confirmation", "Your commend has been added!!");
		return "post";
	}
	
	@PostMapping("/removeComment")
	public String removeComment(ModelMap model, @RequestParam Integer commentId) {

		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User LoggedUser = userService.findByUsername(username);

		Comment toDelete = service.findById(commentId);
		User commenter = toDelete.getCommenter();
		BlogPost post = blogService.getPostById(toDelete.getBlogPost().getBlogPostId()).orElse(null);

		if (LoggedUser.equals(commenter)) 
		{	
			service.deleteComment(toDelete);
		    model.addAttribute("blogPost", post);
			mainController.populateLoggedUserModel(model);
			return "post";
		}
		 else if (LoggedUser.getRole().getRoleName().equals("Admin")) {
			service.deleteComment(toDelete);
			model.addAttribute("comments", service.findAllCommentsFromUser(commenter));
			mainController.populateLoggedUserModel(model);
			return "commentsOfUser";
		}
		
		model.addAttribute("errorDeleting", "You are not allowed to do that");
		List<Comment> comments = service.findAllCommentsFromPost(post);
		model.addAttribute("comments", comments);
	    model.addAttribute("post", post);
		mainController.populateLoggedUserModel(model);
		return "post";
	}
		
//	 @PostMapping("/showAllComments")
//	    public String showComments(@PathVariable int postId, ModelMap model) {
//		 
//	        BlogPost post = blogService.getPostById(postId).orElse(null);
//	        if (post == null) {
//	            model.addAttribute("showCommentsError", "Error, post doesn't exist.");
//	            return "index";
//	        }
//
//	        List<Comment> comments = service.findAllCommentsFromPost(post);
//
//	        model.addAttribute("comments", comments);
//	        model.addAttribute("post", post);
//	        mainController.populateLoggedUserModel(model);
//	        
//	        return "fragments/comments :: commentList";
//	    }

}

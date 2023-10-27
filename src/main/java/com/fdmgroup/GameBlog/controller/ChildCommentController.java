//package com.fdmgroup.GameBlog.controller;
//
//import java.time.LocalDateTime;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import com.fdmgroup.GameBlog.model.ChildComment;
//import com.fdmgroup.GameBlog.model.Comment;
//import com.fdmgroup.GameBlog.model.User;
//import com.fdmgroup.GameBlog.security.DefaultUserDetailService;
//import com.fdmgroup.GameBlog.service.ChildCommentService;
//import com.fdmgroup.GameBlog.service.CommentService;
//
//@Controller
//public class ChildCommentController {
//	
//	@Autowired
//	private ChildCommentService childService;
//	
//	@Autowired
//	private MainController mainController;
//
//	@Autowired
//	private DefaultUserDetailService userService;
//	
//	@Autowired
//	private CommentService parentService;
//	
//	@PostMapping("/addChildComment")
//	public String addChildComment(ModelMap model, @RequestParam String username, @RequestParam Integer commentId, @RequestParam String content) {
//		User childCommenter = userService.findByUsername(username);
//		LocalDateTime date = LocalDateTime.now();
//		Optional<Comment> parentComment = parentService.getCommentById(commentId);
//		ChildComment childComment = new ChildComment(parentComment.get(), content, childCommenter, date);
//		childService.save(childComment);
//		mainController.populateLoggedUserModel(model);
//		return null;
//	}
//	
//	@PostMapping("/removeChildComment")
//	public String removeComment(ModelMap model, @RequestParam Integer childCommentId) {
//		return null;
//	}
//	
////	  @PostMapping("/comments/{commentId}/replies")
////	  public Comment createReply(ModelMap model, @PathVariable Integer commentId, @RequestBody Comment content, @RequestParam String username) {
////		User commenter = userService.findByUsername(username);
////		LocalDateTime date = LocalDateTime.now();
////	    content.setCommenter(commenter);
////	    content.setParentCommentId(commentId);
////		Optional<Comment> parentComment = service.getCommentById(commentId);
////		Comment childComment = new Comment(parentComment.get(), content, commenter, date);
////	    service.save(reply);
////	    mainController.populateLoggedUserModel(model);
////	    return null;
////	  }
//	
//}

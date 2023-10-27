package com.fdmgroup.GameBlog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.GameBlog.model.Comment;
import com.fdmgroup.GameBlog.model.Role;
import com.fdmgroup.GameBlog.model.User;
import com.fdmgroup.GameBlog.security.DefaultUserDetailService;
import com.fdmgroup.GameBlog.service.BlogPostService;
import com.fdmgroup.GameBlog.service.CommentService;
import com.fdmgroup.GameBlog.service.RoleService;

@Controller
public class AdminController {
	
	@Autowired
	BlogPostService blogService;
	
	@Autowired
	CommentService commentService;
	
	@Autowired
	DefaultUserDetailService userService;
	
	@Autowired
	MainController mainController;
	
	@Autowired
	RoleService roleService;
	
	
	@GetMapping("/admin/listUsers")
	public String gotToListOfUsers(ModelMap model) {
		
		List<User> allUsers = userService.listAllUsers();
		model.addAttribute("Users", allUsers);
		mainController.populateLoggedUserModel(model);
		return "usersList";
	}
	
	@GetMapping("/admin/showOtherUserProfile/{username}")
	public String goToShowUserProfile(ModelMap model, @PathVariable String username) {
		
		User user = userService.findByUsername(username);
		
		model.addAttribute("viewUser", user);
		mainController.populateLoggedUserModel(model);
		return "showOtherUserProfile";
	}
	
	@GetMapping("/admin/confirmAccountDeletion/{username}")
	public String confirmDeleteAcc(ModelMap model, @PathVariable String username)
	{
		System.out.println("w delecie" + "=============\n=================\n=============");
		User user = userService.findByUsername(username);
		model.addAttribute("viewUser", user);
		mainController.populateLoggedUserModel(model);
		
		return "confirmDeletion";
	}
	
	@PostMapping("/admin/deleteAccount/{username}")
	public String deleteUser(ModelMap model, @PathVariable String username) {
		
		
		User user = userService.findByUsername(username);
		userService.removeUser(user);
		
		List<User> allUsers = userService.listAllUsers();
		model.addAttribute("Users", allUsers);
		model.addAttribute("confirmation", "User Deleted");
		mainController.populateLoggedUserModel(model);
		return "usersList";
	}
	
	@PostMapping("/admin/changeAccStatus")
	public String changeAccountStatus(ModelMap model, @RequestParam String role, @RequestParam String username) {
		
		Role newRole = roleService.findByRoleName(role);
		User user = userService.findByUsername(username);
		user.setRole(newRole);
		
		userService.saveUser(user);
		model.addAttribute("viewUser", user);
		mainController.populateLoggedUserModel(model);
		
		return "showOtherUserProfile";
	}
	
	
	@GetMapping("/admin/showComments/{username}")
	public String gotToShowUserComments(ModelMap model, @PathVariable String username) {
		
		User user = userService.findByUsername(username);
		List<Comment> comments = commentService.findAllCommentsFromUser(user);
		
		model.addAttribute("comments", comments);
		model.addAttribute("username", username);
		mainController.populateLoggedUserModel(model);
		return "commentsOfUser";
	}
	
	
	
	
	
	

}

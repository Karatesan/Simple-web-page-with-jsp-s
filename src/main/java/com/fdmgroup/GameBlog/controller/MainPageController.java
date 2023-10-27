package com.fdmgroup.GameBlog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fdmgroup.GameBlog.model.BlogPost;
import com.fdmgroup.GameBlog.service.BlogPostService;

public class MainPageController {

	@Autowired
	private BlogPostService blogPostService;
	
	@GetMapping("/") //Listing all(some) blog posts on the main page
	public String home(Model model) {
		List<BlogPost> listOfPosts = blogPostService.getAllPosts();
		model.addAttribute("listOfPosts", listOfPosts);
		return "index"; //variable "index" defining to our template index.jsp/html which is main page
	}
	
}

package com.fdmgroup.GameBlog.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.GameBlog.model.BlogPost;
import com.fdmgroup.GameBlog.service.BlogPostService;
import com.fdmgroup.GameBlog.service.SearchingService;



@Controller
public class SearchingController {
	
	@Autowired
	MainController mainController;
	
	@Autowired
	BlogPostService blogService;
	
	@Autowired
	SearchingService service;
	
	@GetMapping("/goToSearchingPage")
	public String toSearchingPage(ModelMap model, @RequestParam String title) {

		
		//error checks
		if (title.isEmpty()) {
			model.addAttribute("errorNothingToSearch", "Please type something to search");
			List<BlogPost>allPosts =  blogService.getAllPosts();
			model.addAttribute("allPosts", allPosts);
			mainController.populateLoggedUserModel(model);
			return "index";
		}
			
		List<BlogPost> results = service.findPostsByTitle(title);
		model.addAttribute("foundPosts", results);
		mainController.populateLoggedUserModel(model);

		return "searching-page";

}
}

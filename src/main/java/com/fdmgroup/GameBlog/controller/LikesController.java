package com.fdmgroup.GameBlog.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.GameBlog.model.BlogPost;
import com.fdmgroup.GameBlog.model.LikeDislike;
import com.fdmgroup.GameBlog.model.User;
import com.fdmgroup.GameBlog.security.DefaultUserDetailService;
import com.fdmgroup.GameBlog.service.BlogPostService;
import com.fdmgroup.GameBlog.service.LikeDislikeService;

@Controller
public class LikesController {

	@Autowired
	private BlogPostService blogPostService;

	@Autowired
	private MainController mainController;

	@Autowired
	private DefaultUserDetailService userService;

	@Autowired
	private LikeDislikeService likeService;

	@PostMapping("/likes/{blogPostId}")
	public String addLike(ModelMap model, @PathVariable int blogPostId, @RequestParam String rating,
			@RequestParam String username) {

		Optional<BlogPost> blogPost = blogPostService.getPostById(blogPostId);
		User ratingUser = userService.findByUsername(username);
		Optional<LikeDislike> opt = likeService.findByUserAndBlog(ratingUser, blogPost.get());

		if (opt.isEmpty())
		// first rating of an user on this post
		{
			LikeDislike like = new LikeDislike(ratingUser, blogPost.get(), rating);
			updateRating(blogPost.get(), rating, like, 1);
		} else if (!opt.get().getRating().equals(rating)) {
			likeService.remove(opt.get());
			LikeDislike like = new LikeDislike(ratingUser, blogPost.get(), rating);
			updateRating(blogPost.get(), rating, like, 2);

		} else if (opt.get().getRating().equals(rating)) {
			likeService.remove(opt.get());
			int updatedLikes = (rating.equals(LikeDislike.LIKE)) ? blogPost.get().getLikes() - 1 : blogPost.get().getLikes() + 1;
			blogPost.get().setLikes(updatedLikes);
			blogPostService.save(blogPost.get());
		}
		model.addAttribute("blogPost", blogPost.get());
		mainController.populateLoggedUserModel(model);
		return "post";
	}

	public void updateRating(BlogPost post, String rating, LikeDislike like, int increment) {
		int updatedLikes = (rating.equals(LikeDislike.LIKE)) ? post.getLikes() + increment
				: post.getLikes() - increment;
		post.setLikes(updatedLikes);
		if (like != null)
			post.ratePost(like);
		blogPostService.save(post);
	}

}

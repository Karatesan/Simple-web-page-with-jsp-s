package com.fdmgroup.GameBlog.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fdmgroup.GameBlog.model.BlogPost;
import com.fdmgroup.GameBlog.model.Comment;
import com.fdmgroup.GameBlog.model.LikeDislike;
import com.fdmgroup.GameBlog.model.User;
import com.fdmgroup.GameBlog.security.DefaultUserDetailService;
import com.fdmgroup.GameBlog.service.BlogPostService;
import com.fdmgroup.GameBlog.util.BlogPostPictureUtil;

@Controller
public class BlogPostController {
	
	@Autowired
	private BlogPostService blogPostService;
	
	@Autowired
	DefaultUserDetailService defaultUserDetailService;
	
	@Autowired
	MainController mainController;

	
	@GetMapping("/posts/new")
	public String createNewPost(ModelMap model) {
		mainController.populateLoggedUserModel(model);
		return "post_new";
	}		

	@PostMapping("/posts/new")
	public String saveNewPost (ModelMap model, @RequestParam String title, @RequestParam String content, 
								@RequestParam String authorUsername, @RequestParam("picture") MultipartFile mainMultipartFile) throws IOException {
		User author = defaultUserDetailService.findByUsername(authorUsername);
		LocalDateTime time = LocalDateTime.now();
		BlogPost newPost = new BlogPost(author, title, content, 0, time);
		String picName = StringUtils.cleanPath(mainMultipartFile.getOriginalFilename());
		newPost.setPicture(picName);
		blogPostService.savePost(newPost);
		String uploadDir = "./blogPost-pictures/" + newPost.getBlogPostId();
		BlogPostPictureUtil.savePicture(uploadDir, picName, mainMultipartFile);
		List<BlogPost>allPosts =  blogPostService.getAllPosts();
		model.addAttribute("allPosts", allPosts);
		mainController.populateLoggedUserModel(model);
		return "index";
	}
	
	@GetMapping("/posts/{id}")
	public String getPost(@PathVariable Integer id, ModelMap model) {
		Optional<BlogPost> optionalBlogPost = blogPostService.getPostById(id); //find post by id
		// if post exists, push it into model
		if (optionalBlogPost.isPresent()) {
			BlogPost blogPost = optionalBlogPost.get();
			model.addAttribute("blogPost", blogPost);
			mainController.populateLoggedUserModel(model);
			return "post";
		}
		else {
			return "404";
		}
	}
	
    @GetMapping("/posts/{id}/edit")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_AUTHOR')")
    public String getPostForEdit(@PathVariable Integer id, ModelMap model) {
    	   	// find post by id
	        Optional<BlogPost> optionalBlogPost = blogPostService.getPostById(id);
	        // if post exist put it in model
	        if (optionalBlogPost.isPresent()) {
	           BlogPost blogPost = optionalBlogPost.get();
	           blogPost.getPicture();
	            model.addAttribute("blogPost", blogPost);
	            return "post_edit";
	        } else {
	            return "404";
        }
    }
    
    @PostMapping("/posts/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_AUTHOR')")
    public String updatePost(@PathVariable Integer id, BlogPost post, ModelMap model) {
    	
    	// checking if logged user, who wants edit post is the author of this post
    	String username = SecurityContextHolder.getContext().getAuthentication().getName();
    	User loggedUser = defaultUserDetailService.findByUsername(username);
    	Optional<BlogPost> postToEdit = blogPostService.getPostById(id);
		User author = postToEdit.get().getAuthor();
		if (loggedUser.equals(author) || loggedUser.getRole().getRoleName().equals("Admin")) {
        Optional<BlogPost> optionalBlogPost = blogPostService.getPostById(id);
        if (optionalBlogPost.isPresent()) {
            BlogPost existingPost = optionalBlogPost.get();
 //           System.out.println(post.getTitle()+"++++++++++++++++ /n +++++++++++++++++");
            existingPost.setTitle(post.getTitle());
 //           System.out.println(post.getContent()+"++++++++++++++++ /n +++++++++++++++++");
            existingPost.setContent(post.getContent());
            existingPost.setPicture(post.getPicture());
            model.addAttribute("existingPost", existingPost);
            blogPostService.save(existingPost);
        }
		}

        return "redirect:/posts/"+id;
    }

    @GetMapping("/posts/{id}/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_AUTHOR')")
    public String deletePost(@PathVariable Integer id) {
        // find post by id
        Optional<BlogPost> optionalBlogPost = blogPostService.getPostById(id);
        if (optionalBlogPost.isPresent()) {
            BlogPost blogPost = optionalBlogPost.get();
            blogPostService.delete(blogPost);
            return "redirect:/";
        } else {
            return "404";
        }
    }
    
    @GetMapping("/goToLikedPosts/{username}")
    public String goToLikedPosts(ModelMap model, @PathVariable String username) {
        // find post by id
    		User loggedUser = defaultUserDetailService.findByUsername(username);
            model.addAttribute("likedPosts", blogPostService.listLikedPost(loggedUser));
			mainController.populateLoggedUserModel(model);
			model.addAttribute("Title", "Posts liked by " + username);

		return "likedPosts";
        }
    
    @GetMapping("/goToUserPosts/{username}")
    public String goToUserPosts(ModelMap model, @PathVariable String username) {
        // find post by id
    		User loggedUser = defaultUserDetailService.findByUsername(username);
            model.addAttribute("likedPosts", blogPostService.findAllPostsOfUser(loggedUser));
			mainController.populateLoggedUserModel(model);
			model.addAttribute("Title", "Posted by " + username);

		return "likedPosts";
        }
}

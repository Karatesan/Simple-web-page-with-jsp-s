package com.fdmgroup.GameBlog.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.GameBlog.model.BlogPost;
import com.fdmgroup.GameBlog.model.Comment;
import com.fdmgroup.GameBlog.model.LikeDislike;
import com.fdmgroup.GameBlog.model.User;
import com.fdmgroup.GameBlog.repository.BlogPostRepository;
import com.fdmgroup.GameBlog.repository.CommentRepository;

@Service
public class BlogPostService implements IBlogPostService {
	
	@Autowired
	private BlogPostRepository blogPostRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private LikeDislikeService likesService;
	
	public BlogPost savePost (BlogPost blogPost) {
		if(blogPost.getBlogPostId() == null) {
			blogPost.setPostedAt(LocalDateTime.now());
		}
		return blogPostRepository.save(blogPost);
	}
	
	public Optional<BlogPost> getPostById(Integer id){
		return blogPostRepository.findById(id);
	}
	
	public List<BlogPost> findAllPostsOfUser(User user){
		return blogPostRepository.findByAuthor(user);
	}

	public List<BlogPost> getAllPosts(){
		return blogPostRepository.findAll();
	}
	

    public BlogPost save(BlogPost blogPost) {
        if (blogPost.getBlogPostId() == null) {
        	blogPost.setPostedAt(LocalDateTime.now());
        }
        blogPost.setUpdatedAt(LocalDateTime.now());
        return blogPostRepository.save(blogPost);
    }

    public void delete(BlogPost blogPost) {
    	blogPostRepository.delete(blogPost);
    }
    
    public void deleteComment(Comment comment)
    {
    	commentRepository.delete(comment);
    	
    }
    
	public List<BlogPost> listTopthreePosts() {
		
		return blogPostRepository.findTopProducts();
	}
	
	public List<BlogPost> listLikedPost(User username){
		
		List<LikeDislike> likesList =  likesService.findByRatingUser(username);
		return likesList.stream().map(l -> l.getRatedPost()).collect(Collectors.toList());
	}
	
}

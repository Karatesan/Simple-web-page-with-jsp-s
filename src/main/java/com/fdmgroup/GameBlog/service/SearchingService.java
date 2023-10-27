package com.fdmgroup.GameBlog.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.GameBlog.model.BlogPost;
import com.fdmgroup.GameBlog.repository.BlogPostRepository;




@Service
public class SearchingService {
	
	@Autowired
	BlogPostRepository blogrepo;
	
	public List<BlogPost> findPostsByTitle(String title) {
		
	List<BlogPost> foundPosts = blogrepo.findByTitleIgnoreCaseStartingWith(title);
	List<BlogPost> foundPosts2 = blogrepo.findByTitleIgnoreCaseEndingWith(title);
	List<BlogPost> foundPosts3 = blogrepo.findByTitleIgnoreCaseContaining(" " + title+ " ");
	foundPosts.addAll(foundPosts2);
	foundPosts.addAll(foundPosts3);

	return foundPosts.stream().distinct().collect(Collectors.toList());
	}

}

package com.fdmgroup.GameBlog.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.data.annotation.Transient;

@Entity
public class BlogPost {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer blogPostId;
	@ManyToOne(cascade = CascadeType.PERSIST)
	private User author;
	private String title;
	@Column(columnDefinition = "TEXT")
	private String content;
	private int likes;
	private LocalDateTime postedAt;
	private LocalDateTime updatedAt;
	@OneToMany ( mappedBy = "blogPost")
	private List<Comment>comments;
	@OneToMany(cascade = CascadeType.ALL)
	private List<LikeDislike> likesList;
	@Column(name = "picture", length = 64)
	private String picture;

	
	public BlogPost() {
	}
	
	public BlogPost(User author, String title, String content, int likes, LocalDateTime postedAt) {
		super();
		this.author = author;
		this.title = title;
		this.content = content;
		this.likes = likes;
		this.postedAt = postedAt;
	}
	
	public BlogPost(User author, String title, String content, int likes, LocalDateTime postedAt,
					String picture) {
		super();
		this.author = author;
		this.title = title;
		this.content = content;
		this.likes = likes;
		this.picture = picture;
	}
	
	

	public void addComment(Comment comment) {
		
		comments.add(comment);
	}
	
	public void removeComment(Comment comment) {
		
		comments.remove(comment);
	}
	
	public void ratePost(LikeDislike rate) {
		likesList.add(rate);
	}
	
	public void removeRate(LikeDislike rate) {
		likesList.remove(rate);
	}
	
	public Integer getBlogPostId() {
		return blogPostId;
	}

	public void setBlogPostId(Integer blogPostId) {
		this.blogPostId = blogPostId;
	}

	public User getAuthor() {
		return author;
	}	
	public void setAuthor(User author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public LocalDateTime getPostedAt() {
		return postedAt;
	}
	public void setPostedAt(LocalDateTime postedAt) {
		this.postedAt = postedAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	



	public List<LikeDislike> getLikesList() {
		return likesList;
	}

	public void setLikesList(List<LikeDislike> likesList) {
		this.likesList = likesList;
	}

	@Override
	public int hashCode() {
		return Objects.hash(author, blogPostId, comments, content, likes, likesList, postedAt, title, updatedAt);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BlogPost other = (BlogPost) obj;
		return Objects.equals(author, other.author) && Objects.equals(blogPostId, other.blogPostId)
				&& Objects.equals(comments, other.comments) && Objects.equals(content, other.content)
				&& likes == other.likes && Objects.equals(likesList, other.likesList)
				&& Objects.equals(postedAt, other.postedAt) && Objects.equals(title, other.title)
				&& Objects.equals(updatedAt, other.updatedAt);
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	@Override
	public String toString() {
		return "BlogPost [blogPostId=" + blogPostId + ", author=" + author + ", title=" + title + ", content=" + content
				+ ", likes=" + likes + ", postedAt=" + postedAt + ", picture=" + picture + "]";
	}
	
	@Transient
    public String getPicturePath() {
        if (picture == null) return null;
         
        return "/blogPost-pictures/" + blogPostId + "/" + picture;
    }
}
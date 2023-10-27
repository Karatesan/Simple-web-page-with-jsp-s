package com.fdmgroup.GameBlog.model;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Comment {
	@Id
	@GeneratedValue
	private Integer commentId;
	//private Integer parentCommentId;
	@ManyToOne
	private BlogPost blogPost;
//	@OneToMany
//	private List<Comment> childComments;
	private String content;
	@ManyToOne
	private User commenter;
	//private String commenterEmail;
	private LocalDateTime date;
	
	public Comment() {}
	
	

	public Comment( BlogPost post, String content, User commenter,  LocalDateTime date) {
		super();
		this.blogPost = post;
		this.content = content;
		this.commenter = commenter;
		this.date = date;
	}
	
	public Integer getCommentId() {
		return commentId;
	}
	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}
	public BlogPost getBlogPost() {
		return blogPost;
	}
	public void setBlogPost(BlogPost post) {
		this.blogPost = post;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public User getCommenter() {
		return commenter;
	}
	public void setCommenter(User commenter) {
		this.commenter = commenter;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}



	@Override
	public int hashCode() {
		return Objects.hash(blogPost, commentId, commenter, content, date);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comment other = (Comment) obj;
		return Objects.equals(blogPost, other.blogPost) && commentId == other.commentId
				&& Objects.equals(commenter, other.commenter) && Objects.equals(content, other.content)
				&& Objects.equals(date, other.date);
	}

	@Override
	public String toString() {
		return "Comment [commentId=" + commentId  + ", content=" + content + ", commenter="
				+ commenter + ", date=" + date + "]";
	}
	
}

package com.fdmgroup.GameBlog.model;


import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class LikeDislike {
	
	public static final String LIKE = "like";
	static final String DISLIKE = "dislike";
	
	@Id
	@GeneratedValue
	private Integer likeId;
	@ManyToOne
	private User ratingUser;
	@ManyToOne
	private BlogPost ratedPost;
	private String rating;
	
	
	public LikeDislike() {}

	public LikeDislike(User ratingUser,BlogPost ratedPost, String rating) {
		super();
		this.ratingUser = ratingUser;
		this.ratedPost = ratedPost;
		this.rating = rating;
	}

	public Integer getLikeId() {
		return likeId;
	}

	public void setLikeId(Integer likeId) {
		this.likeId = likeId;
	}

	public User getRatingUser() {
		return ratingUser;
	}

	public void setRatingUser(User ratingUser) {
		this.ratingUser = ratingUser;
	}

	public BlogPost getRatedPost() {
		return ratedPost;
	}

	public void setRatedPost(BlogPost ratedPost) {
		this.ratedPost = ratedPost;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	@Override
	public int hashCode() {
		return Objects.hash(likeId, rating, ratingUser);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LikeDislike other = (LikeDislike) obj;
		return Objects.equals(likeId, other.likeId) 
				&& Objects.equals(rating, other.rating) && Objects.equals(ratingUser, other.ratingUser);
	}

	@Override
	public String toString() {
		return "LikeDislike [likeId=" + likeId + ", ratingUser=" + ratingUser + ", rating="
				+ rating + "]";
	}
	
	
	
	
	
	

}

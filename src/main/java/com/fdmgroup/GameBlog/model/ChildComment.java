//package com.fdmgroup.GameBlog.model;
//
//import java.time.LocalDateTime;
//import java.util.Objects;
//
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.ManyToOne;
//
//public class ChildComment {
//	
//	@Id
//	@GeneratedValue
//	private Integer childCommentId;
////	@ManyToOne
//	private Comment comment;
//	private String childContent;
//	@ManyToOne
//	private User childCommenter;
//	private LocalDateTime date;
//	
//	public ChildComment() {}
//
//	public ChildComment(Comment comment, String childContent, User childCommenter, LocalDateTime date) {
//		super();
//		this.comment = comment;
//		this.childContent = childContent;
//		this.childCommenter = childCommenter;
//		this.date = date;
//	}
//
//	public Integer getChildCommentId() {
//		return childCommentId;
//	}
//
//	public void setChildCommentId(Integer childCommentId) {
//		this.childCommentId = childCommentId;
//	}
//
//	public Comment getComment() {
//		return comment;
//	}
//
//	public void setComment(Comment comment) {
//		this.comment = comment;
//	}
//
//	public String getChildContent() {
//		return childContent;
//	}
//
//	public void setChildContent(String childContent) {
//		this.childContent = childContent;
//	}
//
//	public User getChildCommenter() {
//		return childCommenter;
//	}
//
//	public void setChildCommenter(User childCommenter) {
//		this.childCommenter = childCommenter;
//	}
//
//	public LocalDateTime getDate() {
//		return date;
//	}
//
//	public void setDate(LocalDateTime date) {
//		this.date = date;
//	}
//
//	@Override
//	public int hashCode() {
//		return Objects.hash(childCommentId, childCommenter, childContent, comment, date);
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		ChildComment other = (ChildComment) obj;
//		return Objects.equals(childCommentId, other.childCommentId)
//				&& Objects.equals(childCommenter, other.childCommenter)
//				&& Objects.equals(childContent, other.childContent) && Objects.equals(comment, other.comment)
//				&& Objects.equals(date, other.date);
//	}
//
//	@Override
//	public String toString() {
//		return "ChildComment [childCommentId=" + childCommentId + ", comment=" + comment + ", childContent="
//				+ childContent + ", childCommenter=" + childCommenter + ", date=" + date + "]";
//	}
//}

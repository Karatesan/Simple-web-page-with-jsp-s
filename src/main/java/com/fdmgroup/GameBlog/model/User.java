package com.fdmgroup.GameBlog.model;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "users_table")
public class User {

	@Id
	@GeneratedValue
	private Integer userId;
	private String username;
	private String password;
	private String email;
	private String answerQuestion;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Role role;

	public User() {

	}

	public User(String username) {
		this.username = username;
	}

	public User(String username, String password, String email,
			String answerQuestion, Role role) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.answerQuestion = answerQuestion;
		this.role = role;
	}
	
	

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAnswerQuestion() {
		return answerQuestion;
	}

	public void setAnswerQuestion(String answerQuestion) {
		this.answerQuestion = answerQuestion;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		return Objects.hash(answerQuestion, email, password, role, userId, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(answerQuestion, other.answerQuestion) && Objects.equals(email, other.email)
				&& Objects.equals(password, other.password) && Objects.equals(role, other.role)
				&& Objects.equals(userId, other.userId) && Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", answerQuestion=" + answerQuestion + ", role=" + role + "]";
	}
	
	

	
}

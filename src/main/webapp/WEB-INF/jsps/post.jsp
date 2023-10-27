<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:url value="/css/style3.css" var="jstlCss" />
<link href="${jstlCss}" rel="stylesheet">
</head>
<body>
	<jsp:include page="header.jsp" /><br>

	<br>
	
	<h2 class="big-title-font">${ blogPost.title }</h2>
	<div class="blog-post-data">Author: ${blogPost.author.username }  Posted: ${blogPost.postedAt }</div>

	<div class="main-blog-container">
		<div class="blog-picture-container"> <img src=${blogPost.picturePath } /></div>
		<div class="blog-content-container"> ${blogPost.content }</div>
		Number of likes : ${blogPost.likes} <br><br>
		<form action="/likes/${blogPost.blogPostId}" method="post">
				<input type="hidden" name = "username" value="${user.username }">	
				<input type="submit" name = "rating" value="like">
				<input type ="submit" name = "rating" value="dislike"><br><br>
		</form>
		<c:choose>
			<c:when test="${user.role.roleName.equals('Admin') || user.role.roleName.equals('Author') }">
            <form action="/posts/${blogPost.blogPostId}/edit">
				<input type="submit" value="Edit post"><br><br>
			</form>
            </c:when>
            </c:choose>
				
		<div class=comments-container> 
		<span style="color: red">${confirmation}</span><br>
			<form action="/addComment" method="post">
				<input type="hidden" name="username" value="${user.username}">
				<input type="hidden" name="articleId" value="${blogPost.blogPostId}">
				<input class="comment-box" type="text" name="content" placeholder="Comment" required>
				<input type="submit" value="Comment">
			</form>


			<c:forEach items="${blogPost.comments}" var="comment"> 
			<div class="comment-box">
					Commenter: ${comment.commenter.username } date: ${comment.date }<br>
					
					<c:choose>
					<c:when test="${loggedIn == true && user.username.equals(comment.commenter.username)}">
					<form action="/removeComment" method="post">
						<input type="hidden" name="commentId" value="${comment.commentId}">
						<input type="submit" name="delete" value="delete comment">
					</form>
					</c:when>
					</c:choose>
					Comment text: ${comment.content }
				</div> </c:forEach>

		</div>
	</div>

	<jsp:include page="footer.jsp" />
</body>
</html>
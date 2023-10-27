<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:url value="/css/style.css" var="jstlCss" />
<link href="${jstlCss}" rel="stylesheet">
</head>
<body>
	<jsp:include page="header.jsp" /><br>
	<div class=comments-container> 
	<div class="username">Comments of ${username}</div>
	<br><br>
		<c:forEach items="${comments}" var="comment">
	
		<div class="comment-box">
		<div class ="info-box">
	 <div class="date"> date: ${comment.date } </div>
	 <div class="date"> <a href="/posts/${comment.blogPost.blogPostId }">Blog: ${comment.blogPost.title}</a></div>
		</div>
		<div class ="info-box">
		Comment text:<br> ${comment.content }
		<form action="/removeComment" method="post">
			<input type="hidden" name="commentId" value="${comment.commentId}">
			<input type="submit" name="delete" value="Delete comment">
		</form>
		</div>
		</div>
	
	</c:forEach>
	</div>
	
	

</body>
</html>
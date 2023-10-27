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
	<div class="form-container">
	<form action="/posts/${blogPost.blogPostId}" method="post">
		<label class="name-box">Edit the post</label><br><br>
		<input type="hidden" name="authorUsername" value="${ user.username }" /><br>
		<div class="blog-picture-container"> <img src=${blogPost.picturePath } /></div><br>
		<input class="form-field submit-button2" type="file" id="myFile" name="filename"><br><br>
		<label class="name-box">Title</label><br><br>
		<input class="form-field" type="text" name="title" placeholder="Title" value="${ blogPost.title }"/><br>
		<label class="name-box">Body of the post</label><br><br>
		<input class="form-field" type="text" name="content" placeholder="Content" value="${ blogPost.content }"/><br>
		<br>
		<input class="form-field submit-button2" type="submit" value="Edit post">
		<br>
		<br>
		</form>
		<form action="/posts/${blogPost.blogPostId}/delete" method="get">
				<input type="submit" value="Delete post">
		</form>
	</div>

	<jsp:include page="footer.jsp" />
</body>
</html>
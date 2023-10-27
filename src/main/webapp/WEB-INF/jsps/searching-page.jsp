<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Header</title>
<c:url value="/css/style.css" var="jstlCss" />
<link href="${jstlCss}" rel="stylesheet">
</head>
<body>
<jsp:include page="header.jsp" /><br>
<div class="searching-page-container">
	<div class="blog-container">
		<c:forEach items="${foundPosts}" var="blogPost">
			<a href="/posts/${blogPost.blogPostId }">
				<div class="blog-entry b1">
					<div class="blog-image"></div>
					<div class="blog-text-box">
						<div class="title">

							<h1 class="big-title-font">${blogPost.title}</h1>

							<h2 class="subtitle subtitle-font">${blogPost.title}</h2>
						</div>
						<div class="contents">
							<p>${blogPost.content}</p>
						</div>
					</div>
				</div>
			</a>
		</c:forEach>


		<div class="load-older">
			<a class="money-link">Show Previous</a>
		</div>
	</div>
	</div>






</body>
</html>
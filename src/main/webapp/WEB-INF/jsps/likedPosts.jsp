<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
<c:url value="/css/style.css" var="jstlCss" />
<link href="${jstlCss}" rel="stylesheet">
<c:url value="/pictures/Gamer2.png" var="Gamer2" />
</head>
<body>
	<div class="font">

		<jsp:include page="header.jsp" />

		<br></br>
		<hr>

		<!---Main images and title------------------------------------------------------------------>
		<div class="main-photo-box"></div>
		<div class="author-photo-box">
			<img class="author" src="${Gamer2}">
		</div>
		<div class="name-box">by Maciej Gomulec</div>

		<!--Main blog----<img class="name" src="../images/nazwa2.png">--------------------------------------------------------------->

		
			<div class="blog-container-list">
			<h1 class="big-title-font">${Title}</h1>
				<c:forEach items="${likedPosts}" var="likedPost">
					<a href="/posts/${likedPost.blogPostId }">
						<div class="blog-entry b1">
							<div class="blog-image"><img src=${likedPost.picturePath } /></div>
							<div class="blog-text-box">
								<div class="title">

									<h1 class="big-title-font">${likedPost.title}</h1>

									<h2 class="subtitle subtitle-font">${likedPost.title}</h2>
								</div>
								<div class="blog-picture-container">
									
								</div>
								<div class="contents">
									<p>${likedPost.content}</p>
								</div>
							</div>
						</div>
					</a>
				</c:forEach>
			</div>
		

		<!--Footer------------------------------------------------------------------->


		<jsp:include page="footer.jsp" />
	</div>


</body>
</html>
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

		<div class="main-container">
			<div class="blog-container">
				<c:forEach items="${allPosts}" var="blogPost">
					<a href="/posts/${blogPost.blogPostId }">
						<div class="blog-entry b1">
							<div class="blog-image"><img src=${blogPost.picturePath } /></div>
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
			<div class="side-menu-container">
				<div class="title s-title">
					<a>Highest Rated</a>
					<c:forEach items="${topPosts}" var="topPost">
						<a href="/posts/${topPost.blogPostId}">
							<div class="side-box">
								<div class="side-title">
									<h2 class="sidemenu-subtitle-font">${topPost.title }</h2>
								</div>
								<div class="side-image"><img src=${topPost.picturePath } /></div>
							</div>
						</a>
					</c:forEach>
				</div>

			</div>
		</div>



		<div class="support-text">
			<p>IF YOU LIKE IT SUPPORT MY WORK!!</p>
		</div>
		<div class="support-box">
			<a class="money-link"><img class="money"
				src="../images/money2.png"></a>
		</div>

		<!--Footer------------------------------------------------------------------->


		<jsp:include page="footer.jsp" />
	</div>


</body>
</html>
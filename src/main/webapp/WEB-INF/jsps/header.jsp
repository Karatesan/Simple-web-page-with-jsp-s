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
	<header>

				<c:choose>
					<c:when test="${loggedIn == true}"> <!-- aAND CHECK FOR user.role -->
						    <div class='navigation-bar'>
        <div class="logins">
        	<div class="username big-title-font"> ${user.username}</div>
            <a href="/logout" class='button'>Logout</a>
            <a href="/goToLikedPosts/${user.username}" class='button'>Show Liked Posts</a>
            <c:choose>
					<c:when test="${user.role.roleName.equals('Admin') }">
             <a href="/admin/listUsers" class='button'>Show all users</a>
              </c:when>
              </c:choose>
              <c:choose>
					<c:when test="${user.role.roleName.equals('Admin') || user.role.roleName.equals('Author') }">
              <a href="/posts/new" class='button'>Add New Post</a>
              <a href="/goToUserPosts/${user.username}" class='button'>Show Your Posts</a>
              </c:when>
              </c:choose>
        </div>
							<div class="blog-menu">
							
							<!--    SEARCH BAR ------------------------------  -->
								<div class="navbar-searchBar">
									<form class="form-class" action="/goToSearchingPage" method="get">
										<input class="search-bar" type="search" id="search-bar" name="title" placeholder="Search"> 
										<input class="submit-button" type="submit" hidden >
									</form>

								</div>
								<a href="/" class='button'>Home</a> <a href="/about"
									class='button'>About</a> <a
									href="mailto:maciej.gomulec@fdmgroup.com" class='button'>Contact</a>
								<a class='button support'>Patreon</a>
							</div>
						</div>
						
						<!-------------- SEE PROFILE------------------------- -->
						<a href="/showProfile"></a>
					</c:when>
					<c:otherwise>
    <div class='navigation-bar'>
        <div class="logins">
            <a href="/login" class='button'>Login</a>
            <a href="/goRegisterPage" class='button'>Sign Up</a>
        </div>
        <div class="blog-menu">
        		<div class="navbar-searchBar">
									<form action="/goToSearchingPage" method="get">
										<input class="search-bar" type="search" id="search-bar" name="title" placeholder="Search"> 
										<input class="submit-button" type="submit" hidden >
									</form>

								</div>
            <a href="/" class='button'>Home</a>
            <a href="/about" class='button'>About</a>
            <a href="mailto:maciej.gomulec@fdmgroup.com" class='button'>Contact</a>
            <a class='button support'>Patreon</a>
        </div>
    </div>
					</c:otherwise>
				</c:choose>
		
	</header>
</body>
</html>
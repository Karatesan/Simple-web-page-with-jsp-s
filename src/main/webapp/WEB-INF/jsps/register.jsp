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
	
	<div class="author-photo-box2">
			<img class="author2" src="pictures/Gamer2.png">
		</div>
<div class="in-center">
	<h3>Register here</h3>
	
		
	<span style="color: red">${errorMessage}</span>
	<br>
	<div class="form-container">
	<form action="/register" method="post">
		<input class="form-field" type="text" name="username" placeholder="Username" required /><br>
		<input class="form-field" type="password" name="password" placeholder="Password" required /><br>
		<input class="form-field" type="password" name="confirmPassword" placeholder="ConfirmPassword" /><br>
		<input class="form-field" type="email" name="email" placeholder="Email" required /><br>  
		<p>Security question:</p>
		<br> 
		<input class="form-field" type="text" name="answerQuestion" placeholder="FavouriteCity" required /><br> 
		<input class="form-field submit-button2" type="submit" value="Register">
	</form>
	</div>
	<br>
	<br>
</div>

	<jsp:include page="footer.jsp" />


</body>
</html>
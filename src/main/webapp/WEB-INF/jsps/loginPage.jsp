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

	<h3>Login</h3>
	
		
<div class="form-container">
		<form action="/login" method="post">
			<div>
				<input class="form-field" type="text" name="username" placeholder="Username" /><br>
				<input class="form-field" type="password" name="password" placeholder="Password" /><br> 
				<input class="form-field submit-button2" type="submit" value="Login"><br>
				<br>
			</div>
			<div >
				<a class="link" href="/goQuestionPassword">Reset password</a> <br>
				<a class="link" href="/goRegisterPage">	Register</a> <br>
			</div>
		</form>
		</div>
	<jsp:include page="footer.jsp" />

</body>
</html>
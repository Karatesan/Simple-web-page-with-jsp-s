<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Reset Password</title>
<c:url value="/css/style3.css" var="jstlCss3" />
<link href="${jstlCss3}" rel="stylesheet">
</head>
<body>

<jsp:include page="header.jsp" /><br><br>

	<div class="in-center">
	<h3>Enter new password</h3>
	
	<img src="pictures/user.png" class="user-pic"/>
	<br>


	<span style="color:red">${errorMessage}</span>
	<form action="/resetPassword" method="post">
		<input type="hidden" name="username" value="${user.username}"/>	<br>
		<input class="form-field" type="password" name="newPassword" placeholder="New Password..."/><br>
		<input class="form-field"  type="password" name="confirmNewPassword" placeholder="Confirm New Password..."/><br>
		<input class="form-field submit-button2" type="submit" value="Reset password"/>
	</form>
	
	<br><br><br>
</div>

	<jsp:include page="footer.jsp" />
	
</body>
</html>
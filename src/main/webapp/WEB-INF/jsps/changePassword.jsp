<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Change Password</title>
<c:url value="/css/style3.css" var="jstlCss3" />
<link href="${jstlCss3}" rel="stylesheet">

</head>
<body>
	<jsp:include page="header.jsp" /><br><br>
<div class="in-center">

	<h2>Change Password</h2>
	<span style="color:red">${errorMessage}</span><br>
	<form action="changePassword" method="post">
		<input type="hidden" name="username" value="${user.username}"/>	
		<input type="password" name="currentPassword" placeholder="Current Password"/>
		<input type="password" name="newPassword" placeholder="New Password"/>
		<input type="password" name="confirmNewPassword" placeholder="Confirm New Password"/>
		<input type="submit" value="change password"/>
	</form>
	<br><br><a class="link" href="/">Go to Homepage</a>
</div>
<br><br>
	<jsp:include page="footer.jsp" />
</body>
</html>
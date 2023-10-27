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
<div class="users">
		<div class="div1">
			
			<b>Username:</b> ${viewUser.username}<br><br>
			<b>Account Type:</b> ${viewUser.role.roleName} <br><br>
			<b>Email:</b> ${viewUser.email}<br><br>
		</div>	
			<div class="div1">
			<a class="button" href="/admin/confirmAccountDeletion/${viewUser.username}">Delete Profile</a> <br> <br> <br> 
			<!--  <a class="button" href="/goChangePasswordPage">Block Profile</a> <br><br> <br> -->
			<a class="button" href="/admin/showComments/${viewUser.username}">Show all comments of user</a><br><br><br>  
			
			<form action="/admin/changeAccStatus" method="post">
				<input type="hidden" name="username" value="${viewUser.username}">
				<select name="role">
					<option value="Author">Author</option>
					<option value="Admin">Admin</option>
					<option value="Reader">Reader</option>
				</select>
				<input type="submit" value="Change Role" />
			</form>  
		</div>
		
		</div>
</body>
</html>
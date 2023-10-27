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
 	<h2>Are you sure you want to delete ${viewUser.username} account? </h2>
					
			<form action="/admin/deleteAccount/${viewUser.username}" method="post">
					<input class="violetButton" type="submit" name="Yes" value="Yes" /> <br><br>
			</form>  
		<form action="/admin/showOtherUserProfile/${viewUser.username}" method="get">
					<input class="violetButton" type="submit" name="No" value="No" /> <br><br>
			</form>  
		
		</div>
</body>
</html>
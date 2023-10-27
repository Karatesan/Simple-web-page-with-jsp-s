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

	<div class="in-center">
		<div class="username">All Users</div>
		<span style="color: red">${confirmation}</span><br>

		<c:forEach items="${Users}" var="user">
			<a href="/admin/showOtherUserProfile/${user.username}" class="button">
				<div class="user-card">
					<div class="user-header">
						<b><p>${user.username}</p></b>
					</div>
				</div>
			</a>
		</c:forEach>

		<a href="/">Go to Homepage</a>
	</div>

	<jsp:include page="footer.jsp" />

</body>
</html>
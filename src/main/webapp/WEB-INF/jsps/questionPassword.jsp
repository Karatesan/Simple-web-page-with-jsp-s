<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Question Password</title>
<c:url value="/css/style3.css" var="jstlCss3" />
<link href="${jstlCss3}" rel="stylesheet">
</head>
<body>
	<jsp:include page="header.jsp" /><br>
	
	<div style="text-align: right;">
		<a href="${pageContext.request.contextPath}/goQuestionPassword?lang=en">English</a>
		<a href="${pageContext.request.contextPath}/goQuestionPassword?lang=de">German</a>
		<a href="${pageContext.request.contextPath}/goQuestionPassword?lang=pl">Polish</a>		
	</div>
	
	<spring:message code="label.username" var="labelUsername" />
	<spring:message code="label.questionFavouriteCity" var="labelQuestionFavouriteCity" />
	<spring:message code="label.resetPassword" var="labelResetPassword" />
	
<div class="in-center">
	<br>
	<h3>${labelResetPassword}</h3>
	
	<img src="pictures/user.png" class="user-pic"/>
	<br>
	
	<span style="color: red">${errorMessage}</span>
	<br>

	<form action="/questionPassword" method="post">
		<input class="form-field" type="text" name="username" placeholder="${labelUsername}" required /><br> 
		<input class="form-field" type="text" name="answer" placeholder="${labelQuestionFavouriteCity}" required /><br> 
		<input class="form-field submit-button2" type="submit" value="${labelResetPassword}"  />
	</form>

	<br><br><br>
</div>
	
	<jsp:include page="footer.jsp" />

</body>
</html>
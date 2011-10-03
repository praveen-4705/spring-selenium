<%@ include file="/WEB-INF/templates/include/web/header.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Login</title>
	</head>
	<body>
		<h1>Enter User and Password</h1>
		<form:form method="post" commandName="userLogin">
			<form:errors path="user_password_error" cssClass="error"/>
			<br />
			<table width="" bgcolor="f8f8ff" border="0" cellspacing="0" cellpadding="">				
				<tr>
					<td width="">Username</td>
					<td width=""> <form:input path="userName"/> </td>
					<td width="60%"> <form:errors path="userName" cssClass="error"/> </td>
				</tr>
				<tr>
					<td width="">Password</td>
					<td width=""> <form:password path="password"/> </td>
					<td width="60%"> <form:errors path="password" cssClass="error"/> </td>						
				</tr>
			</table>
			<br>
			<input type="submit" align="center" value="Login">
		</form:form>
		<br />
		<a href="<c:url value="/home/home"/>">Home</a>
	</body>
</html>
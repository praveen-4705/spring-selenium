<%@ include file="/WEB-INF/templates/include/web/header.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create New Product</title>
</head>
<body>
	<h1>Creating New Product</h1>
	<br />
	<form:form method="post" commandName="product">
		<td width="60%"> <form:errors path="description_price_error" cssClass="error"/> </td>
		<table width="" bgcolor="f8f8ff" border="0" cellspacing="0" cellpadding="">			
					
			<tr>
				<td width="">Description</td>
				<td width=""> <form:input path="description"/> </td>
				<td width="60%"> <form:errors path="description" cssClass="error"/> </td>
			</tr>
			<tr>
				<td>Price</td>
				<td width=""> <form:input path="price"/> </td>
				<td width="60%"> <form:errors path="price" cssClass="error"/> </td>
			</tr>
		</table>
		<br>
		<input type="submit" align="center" value="New Product">
	</form:form>
	<br />
	<a href="<c:url value="/inventory/home"/>">Products</a>

</body>
</html>
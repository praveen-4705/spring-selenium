<%@ include file="/WEB-INF/templates/include/web/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to BuyEasyToday.com</title>
</head>
<body>
	<%@ include file="/WEB-INF/templates/include/web/homeHeader.jsp" %>
	<h3>Products</h3>
    <table>
    	<tbody>
    		<tr>
    			<th>Description</th>
    			<th>Price</th>
    		</tr>
    	</tbody>
		<c:forEach items="${model.products}" var="prod">
			<tr>
				<th><c:out value="${prod.description}"/></th>
				<th><i>$<c:out value="${prod.price}"/></i></th>    		
			</tr>
	    </c:forEach>
    </table>
    <%@ include file="/WEB-INF/templates/include/web/homeUnder.jsp" %>
</body>
</html>
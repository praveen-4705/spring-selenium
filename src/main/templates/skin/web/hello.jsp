<%@ include file="/WEB-INF/templates/include/web/header.jsp" %>
<html>
  <head><title><fmt:message key="title"/></title></head>
  <body>
    <h1><fmt:message key="heading"/></h1>
    <p><fmt:message key="greeting"/> <c:out value="${model.now}"/></p>
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
    
    <br>
    <a href="<c:url value="priceincrease.htm"/>">Increase Prices</a>
    <br />
    <a href="<c:url value="newProduct.htm"/>">Create New Product</a>
    
  </body>
</html>
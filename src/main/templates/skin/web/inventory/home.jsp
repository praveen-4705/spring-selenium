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
    			<th></th>
    		</tr>
    	</tbody>
		<c:forEach items="${model.products}" var="prod">
			<tr id="product_${prod.id}">
				<td><c:out value="${prod.description}"/></th>
				<td><i>$<c:out value="${prod.price}"/></i></th>
				<td>
					<a href="<c:url value="view?id=${prod.id}" /> ">View</a>
					<a href="<c:url value="edit?id=${prod.id}" /> ">Edit</a>
					<a href="<c:url value="delete?id=${prod.id}" /> ">Delete</a>
				</td>    		
			</tr>
	    </c:forEach>
    </table>
    
    <br>
    <a href="<c:url value="priceIncrease"/>">Increase Prices</a>
    <br />
    <a href="<c:url value="newProduct"/>">Create New Product</a>
    <br />
    <a href="<c:url value="/home/home"/>">Home</a>
    
  </body>
</html>
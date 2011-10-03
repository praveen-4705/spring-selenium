<br />
<h3>Products</h3>
<table>
	<tbody>
		<tr>
			<th>Description</th>
			<th>Price</th>
			<!-- with permission -->
			<th></th>
			<!-- end with permission -->
		</tr>
	</tbody>
	<c:forEach items="${model.products}" var="prod">
		<tr id="product_${prod.id}">
			<td><c:out value="${prod.description}"/></th>
			<td><i>$<c:out value="${prod.price}"/></i></th>
			<!-- with permission -->
			<td>
				<a href="<c:url value="view?id=${prod.id}" /> ">View</a>
				<a href="<c:url value="edit?id=${prod.id}" /> ">Edit</a>
				<a href="<c:url value="destroy?id=${prod.id}" /> ">Delete</a>
			</td>
			<!-- end with permission -->
		</tr>
	</c:forEach>
</table>
<br />
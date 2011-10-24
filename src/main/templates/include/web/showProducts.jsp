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
			<td><c:out value="${prod.description}"/></td>
			<td><i>$<c:out value="${prod.price}"/></i></td>
			<c:if test="${model.hasPriv=='true'}">
				<td>
					<a href="<c:url value="view?id=${prod.id}" /> ">View</a>
					<a href="<c:url value="edit?id=${prod.id}" /> ">Edit</a>
					<a href="<c:url value="destroy?id=${prod.id}" /> ">Delete</a>
				</td>
			</c:if>
		</tr>
	</c:forEach>
</table>
<br />
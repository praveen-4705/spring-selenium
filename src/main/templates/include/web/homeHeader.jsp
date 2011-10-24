<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="homeHeader">
	<ul>		
		<li>
			<c:choose>
				<c:when test="${model.hasPriv}">
					<a href="<c:url value="/home/logOut"/>">LogOut</a>
				</c:when>
				<c:otherwise>		
					<a href="<c:url value="/login/login"/>">Login</a>
				</c:otherwise>
			</c:choose>		
		</li>
		<c:if test="${model.hasPriv}">
			<li>
				<a href="<c:url value="/inventory/home" />">Inventory</a>
			</li>			
		</c:if>
	</ul>

</div>
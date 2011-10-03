<%@ include file="/WEB-INF/templates/include/web/header.jsp" %>
<html>
  <head><title><fmt:message key="title"/></title></head>
  <body>
    <h1><fmt:message key="heading"/></h1>
    <p><fmt:message key="greeting"/> <c:out value="${model.now}"/></p>
    <%@ include file="/WEB-INF/templates/include/web/showProducts.jsp" %>    
    <a href="<c:url value="priceIncrease"/>">Increase Prices</a>
    <br />
    <a href="<c:url value="newProduct"/>">Create New Product</a>
    <br />
    <a href="<c:url value="/home/home"/>">Home</a>
    
  </body>
</html>
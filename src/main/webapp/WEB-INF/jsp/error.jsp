<!DOCTYPE html>
<html>
<head>
  <title>Error</title>
</head>
<body>
<c:url var="logoutUrl" value="/logout"/>
<form action="${logoutUrl}" method="post">
  <input type="submit" value="Log out" />
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
<h2>Error page</h2>
<c:choose>
  <c:when test="${empty message}">
    <p>Something went wrong.</p>
  </c:when>
  <c:otherwise>
    <p>${message}</p>
  </c:otherwise>
</c:choose>
<a href="<c:url value="/photo" />">Return to Photo Blog</a>
</body>
</html>

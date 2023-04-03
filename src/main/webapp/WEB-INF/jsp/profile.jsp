<!DOCTYPE html>
<html>
<head>
    <title>Customer Support</title>
</head>
<body>
<c:url var="logoutUrl" value="/logout"/>
<form action="${logoutUrl}" method="post">
    <input type="submit" value="Log out" />
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>

<h2>User ${username}</h2>
<br/><br/>
<i>email - <c:out value="${appUser.email}"/></i><br/><br/>
<i>description - <c:out value="${appUser.description}"/></i><br/><br/>
<c:forEach items="${appUser.photos}" var="photo" varStatus="status">
    <c:if test="${!status.first}">, </c:if>
    ${photo.photo}
    <br />
</c:forEach>
<a href="<c:url value="/user" />">Return to User list</a><br/><br/>
<a href="<c:url value="/photo" />">Return to Photo Blog</a>
</body>
</html>

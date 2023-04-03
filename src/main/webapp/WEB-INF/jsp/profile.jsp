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
<i>Post Record:</i><br/><br/>
<c:choose>
<c:when test="${fn:length(appUser.photos) == 0}">
    <i>There are no post in the system.</i>
</c:when>
<c:otherwise>
<c:forEach items="${appUser.photos}" var="photo" varStatus="status">
    Photo <a href="<c:url value="/photo/view/${photos.id}" />">
    <c:out value="${photos.id}"/></a>:
    <c:out value="${photo.subject}"/>
    (Poster: <c:out value="${photo.username}"/>)
    <br />
</c:forEach>
</c:otherwise>
</c:choose>
<a href="<c:url value="/user" />">Return to User list</a><br/><br/>
<a href="<c:url value="/photo" />">Return to Photo Blog</a>
</body>
</html>

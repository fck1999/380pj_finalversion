<!DOCTYPE html>
<html>
<head>
  <title>Photo Blog</title>
</head>
<body>
<c:url var="logoutUrl" value="/logout"/>
<form action="${logoutUrl}" method="post">
  <input type="submit" value="Log out" />
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>

<h2>Posts</h2>
<security:authorize access="hasRole('ADMIN')">
  <a href="<c:url value="/user" />">Manage User Accounts</a><br /><br />
</security:authorize>
<a href="<c:url value="/photo/create" />">Create a Post</a><br/><br/>
<c:choose>
  <c:when test="${fn:length(photoDatabase) == 0}">
    <i>There are no photo in the system.</i>
  </c:when>
  <c:otherwise>
    <c:forEach items="${photoDatabase}" var="entry">
      Photo ${entry.id}:
      <a href="<c:url value="/photo/view/${entry.id}" />">
      (Poster: <c:out value="${entry.username}"/>)</a>

      <security:authorize access="hasRole('ADMIN')">
        [<a href="<c:url value="/photo/delete/${entry.id}"/>">Delete</a>]
      </security:authorize>
      <br />
    </c:forEach>
  </c:otherwise>
</c:choose>
</body>
</html>

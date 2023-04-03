<!DOCTYPE html>
<html>
<head><title>Customer Support User Management</title></head>
<body>
<c:url var="logoutUrl" value="/logout"/>
<form action="${logoutUrl}" method="post">
  <input type="submit" value="Log out"/>
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>

<br/><br/>

<a href="<c:url value="/photo" />">Return to Photo Blog</a>

<h2>Users</h2>

<a href="<c:url value="/user/create" />">Create a User</a><br/><br/>

<c:choose>
  <c:when test="${fn:length(commentDB) == 0}">
    <i>There are no photo in the system.</i>
  </c:when>
  <c:otherwise>
    Comment For ${user.username}:
    <c:forEach items="${user.comments}" var="comment" varStatus="status">
      <c:if test="${!status.first}">, </c:if>
      ${comment.comment}
      <br />
    </c:forEach>
  </c:otherwise>
</c:choose>
</body>
</html>
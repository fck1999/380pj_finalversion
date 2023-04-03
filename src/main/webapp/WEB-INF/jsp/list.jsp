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
        <c:out value="${entry.subject}"/></a>
      (customer: <c:out value="${entry.customerName}"/>)
      <security:authorize access="hasRole('ADMIN') or
                                principal.username=='${entry.customerName}'">
        [<a href="<c:url value="/photo/edit/${entry.id}" />">Edit</a>]
      </security:authorize>
      <security:authorize access="hasRole('ADMIN')">
        [<a href="<c:url value="/photo/delete/${entry.id}" />">Delete</a>]
      </security:authorize>
      <br />
    </c:forEach>
  </c:otherwise>
</c:choose>
</body>
</html>

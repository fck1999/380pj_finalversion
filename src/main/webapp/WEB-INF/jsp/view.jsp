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

<h2>Post #${photoId}: <c:out value="${photo.subject}"/></h2>
<security:authorize access="hasRole('ADMIN') or
                          principal.username=='${photo.customerName}'">
    [<a href="<c:url value="/photo/edit/${photo.id}" />">Edit</a>]
</security:authorize>
<security:authorize access="hasRole('ADMIN') or
                    principal.username=='${photo.customerName}'">
    [<a href="<c:url value="/photo/delete/${photo.id}" />">Delete</a>]
</security:authorize>
<br/><br/>
<i>User Name - <c:out value="${photo.customerName}"/></i><br/><br/>
<c:out value="${photo.body}"/><br/><br/>
<c:if test="${!empty photo.attachments}">
    Attachments:
    <c:forEach items="${photo.attachments}" var="attachment" varStatus="status">
        <c:if test="${!status.first}">, </c:if>
        <a href="<c:url value="/photo/${photoId}/attachment/${attachment.id}" />">
            <c:out value="${attachment.name}"/></a>
        [<a href="<c:url value="/photo/${photoId}/delete/${attachment.id}" />">Delete</a>]
    </c:forEach><br/><br/>
</c:if>
<a href="<c:url value="/photo" />">Return to Photo Blog</a>
</body>
</html>

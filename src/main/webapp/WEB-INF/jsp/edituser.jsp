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

<h2>Edit User #${User.id}</h2>
<form:form method="POST" enctype="multipart/form-data" modelAttribute="photoForm">
    <form:label path="email">email</form:label><br/>
    <form:input type="text" path="email" /><br/><br/>
    <form:label path="roles">Roles</form:label><br/>
    <form:checkbox path="roles" value="ROLE_USER"/>ROLE_USER
    <form:checkbox path="roles" value="ROLE_ADMIN"/>ROLE_ADMIN
</form:form>
<a href="<c:url value="/photo" />">Return to Photo Blog</a>
</body>
</html>
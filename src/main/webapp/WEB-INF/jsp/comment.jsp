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

<h2>Comment for </h2>
<br/><br/>
<form:form method="POST" modelAttribute="commentForm">
    <form:label path="comment">Comment</form:label><br/>
    <form:input type="text" path="comment"/><br/><br/>
    <input type="submit" value="Submit"/>
</form:form>
</body>
</html>

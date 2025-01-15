<%--<%@ taglib uri="http://xmlns.jcp.org/jsf/core" prefix="f" %>--%>
<%--<%@ taglib uri="http://xmlns.jcp.org/jsf/html" prefix="h" %>--%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Login</title>
</head>
<body>
<h1>Login</h1>
<form action="j_security_check" method="post">
    <label for="username">Username:</label>
    <input type="text" id="username" name="j_username" />

    <label for="password">Password:</label>
    <input type="password" id="password" name="j_password" />

    <input type="submit" value="Login" />
</form>

</body>
</html>

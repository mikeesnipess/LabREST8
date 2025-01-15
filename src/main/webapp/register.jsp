<%--<%@ taglib uri="http://xmlns.jcp.org/jsf/core" prefix="f" %>--%>
<%--<%@ taglib uri="http://xmlns.jcp.org/jsf/html" prefix="h" %>--%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <title>Register</title>
</head>
<body>
<h1>Register</h1>
<form action="register" method="post">
  <table>
    <tr>
      <td><label for="username">Username:</label></td>
      <td><input type="text" id="username" name="username" value="${authBean.username}" /></td>
    </tr>
    <tr>
      <td><label for="password">Password:</label></td>
      <td><input type="password" id="password" name="password" value="${authBean.password}" /></td>
    </tr>
    <tr>
      <td><label for="role">Role:</label></td>
      <td>
        <select id="role" name="role">
          <option value="Student" ${authBean.role == 'Student' ? 'selected' : ''}>Student</option>
          <option value="Teacher" ${authBean.role == 'Teacher' ? 'selected' : ''}>Teacher</option>
          <option value="Admin" ${authBean.role == 'Admin' ? 'selected' : ''}>Admin</option>
        </select>
      </td>
    </tr>
  </table>
  <input type="submit" value="Register" />
</form>
</body>
</html>

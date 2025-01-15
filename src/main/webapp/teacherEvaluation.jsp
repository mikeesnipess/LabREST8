<%--<%@ taglib uri="http://xmlns.jcp.org/jsf/core" prefix="f" %>--%>
<%--<%@ taglib uri="http://xmlns.jcp.org/jsf/html" prefix="h" %>--%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <title>Teacher Evaluations</title>
</head>
<body>
<h2>Evaluations for Teacher: ${teacherEvaluationBean.loggedInTeacher.username}</h2>

<table border="1">
  <thead>
  <tr>
    <th>Activity</th>
    <th>Activity Type</th>
    <th>Grade</th>
    <th>Comment</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach var="evaluation" items="${teacherEvaluationBean.teacherEvaluations}">
    <tr>
      <td>${evaluation.activity}</td>
      <td>${evaluation.activityType}</td>
      <td>${evaluation.grade}</td>
      <td>${evaluation.comment}</td>
    </tr>
  </c:forEach>
  </tbody>
</table>

<form action="login.jsp" method="get">
  <input type="submit" value="Log out" />
</form>
</body>
</html>

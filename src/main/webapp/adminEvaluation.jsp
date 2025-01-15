<%--<%@ taglib uri="http://xmlns.jcp.org/jsf/core" prefix="f" %>--%>
<%--<%@ taglib uri="http://xmlns.jcp.org/jsf/html" prefix="h" %>--%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Admin Panel: Manage Evaluations</title>
</head>
<body>
<h2>Admin Panel: Manage Evaluations</h2>

<table border="1">
    <thead>
    <tr>
        <th>ID Evaluations</th>
        <th>Student</th>
        <th>Teacher</th>
        <th>Activity</th>
        <th>Activity Type</th>
        <th>Grade</th>
        <th>Comment</th>
        <th>TimeStamp</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="evaluation" items="${adminEvaluationBean.allEvaluations}">
        <tr>
            <td>${evaluation.id}</td>
            <td>${evaluation.student.username} - ${evaluation.student.id}</td>
            <td>${evaluation.teacher.username} - ${evaluation.teacher.id}</td>
            <td>${evaluation.activity}</td>
            <td>${evaluation.activityType}</td>
            <td>${evaluation.grade}</td>
            <td>${evaluation.comment}</td>
            <td>${evaluation.timestamp}</td>
            <td>
                <form action="deleteEvaluation" method="post">
                    <input type="hidden" name="evaluationId" value="${evaluation.id}" />
                    <input type="submit" value="Delete" />
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>

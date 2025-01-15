<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Submit Evaluation</title>
</head>
<body>
<!-- Form for submitting the evaluation -->
<form action="submitEvaluation.jsp" method="post">
    <!-- Input field for activity -->
    <label for="activity">Activity:</label>
    <input type="text" id="activity" name="activity" placeholder="Activity" value="${evaluationBean.activity}" />

    <!-- Input field for activity type -->
    <label for="activityType">Activity Type:</label>
    <input type="text" id="activityType" name="activityType" placeholder="Activity Type" value="${evaluationBean.activityType}" />

    <!-- Input field for grade -->
    <label for="grade">Grade:</label>
    <input type="text" id="grade" name="grade" placeholder="Grade" value="${evaluationBean.grade}" />

    <!-- Input field for comment -->
    <label for="comment">Comment:</label>
    <textarea id="comment" name="comment" placeholder="Comment">${evaluationBean.comment}</textarea>

    <!-- Dropdown for selecting a teacher -->
    <label for="teacher">Teacher:</label>
    <select id="teacher" name="teacher">
        <c:forEach var="teacher" items="${evaluationBean.teachers}">
            <option value="${teacher.id}" ${teacher.id == evaluationBean.teacher.id ? 'selected' : ''}>
                    ${teacher.username}
            </option>
        </c:forEach>
    </select>

    <!-- Submit button -->
    <input type="submit" value="Submit" />

    <!-- Success message display -->
    <c:if test="${not empty evaluationBean.successMessage}">
        <p>${evaluationBean.successMessage}</p>
    </c:if>
</form>

<!-- Separate form for log out -->
<form action="login.jsp" method="get">
    <input type="submit" value="Log out" />
</form>

</body>
</html>

<%--<%@ taglib uri="http://jakarta.apache.org/taglibs/standard" prefix="c" %>--%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Submit Evaluation</title>
    <script>
        // Fetch teachers from API and populate the dropdown
        window.onload = function() {
            fetch('api/evaluations/teachers') // API endpoint to fetch teachers
                .then(response => {
                    if (!response.ok) {
                        throw new Error(`HTTP error! Status: ${response.status}`);
                    }
                    return response.json(); // Parse the JSON response
                })
                .then(teachers => {
                    console.log('Teachers data:', teachers); // Log the parsed data for inspection

                    // Get the dropdown element
                    const teacherSelect = document.getElementById('teacher');

                    // Clear any existing options
                    teacherSelect.innerHTML = '';

                    // Add an initial placeholder option
                    const placeholderOption = document.createElement('option');
                    placeholderOption.value = '';
                    placeholderOption.textContent = 'Select a Teacher';
                    placeholderOption.disabled = true;
                    placeholderOption.selected = true;
                    teacherSelect.appendChild(placeholderOption);

                    // Populate the dropdown with teachers
                    teachers.forEach(teacher => {
                        const option = document.createElement('option');
                        option.value = teacher.id;
                        option.textContent = teacher.username;
                        teacherSelect.appendChild(option);
                    });
                })
                .catch(error => {
                    console.error('Error fetching teachers:', error);
                });
        };
    </script>



</head>
<body>
<!-- Form for submitting the evaluation -->
<h1>Submit Evaluation</h1>
<form action="api/evaluations/submitEvaluation" method="post">
    <table>
        <!-- Input field for activity -->
        <tr>
            <td><label for="activity">Activity:</label></td>
            <td><input type="text" id="activity" name="activity" placeholder="Activity" /></td>
        </tr>

        <!-- Input field for activity type -->
        <tr>
            <td><label for="activityType">Activity Type:</label></td>
            <td><input type="text" id="activityType" name="activityType" placeholder="Activity Type" /></td>
        </tr>

        <!-- Input field for grade -->
        <tr>
            <td><label for="grade">Grade:</label></td>
            <td><input type="text" id="grade" name="grade" placeholder="Grade" /></td>
        </tr>

        <!-- Input field for comment -->
        <tr>
            <td><label for="comment">Comment:</label></td>
            <td><textarea id="comment" name="comment" placeholder="Comment"></textarea></td>
        </tr>

        <!-- Dropdown for selecting a teacher -->
        <tr>
            <td><label for="teacher">Teacher:</label></td>
            <td>
                <select name="teacher" id="teacher">
                    <!-- Teachers will be populated here via the API call -->
                </select>
            </td>
        </tr>

        <!-- Submit button -->
        <tr>
            <td colspan="2"><input type="submit" value="Submit" /></td>
        </tr>
    </table>

    <!-- Success message display -->
    <c:if test="${not empty successMessage}">
        <p>${successMessage}</p>
    </c:if>
</form>

<!-- Separate form for log out -->
<form action="index.jsp" method="get">
    <input type="submit" value="Log out" />
</form>

</body>
</html>

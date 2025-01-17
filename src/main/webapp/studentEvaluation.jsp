<%--<%@ taglib uri="http://jakarta.apache.org/taglibs/standard" prefix="c" %>--%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Submit Evaluation</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f7f6;
            color: #333;
            margin: 0;
        }


        h1 {
            text-align: center;
            color: #333;
            margin-bottom: 20px;
        }

        form {
            background-color: #fff;
            padding: 20px 30px;
            border-radius: 8px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            max-width: 500px;
            width: 100%;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }

        td {
            padding: 10px;
            vertical-align: top;
        }

        label {
            font-weight: bold;
            color: #444;
        }

        input[type="text"], textarea, select {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 14px;
            box-sizing: border-box;
        }

        input[type="submit"] {
            background-color: #007BFF;
            color: #fff;
            border: none;
            padding: 10px 20px;
            font-size: 16px;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s ease;
            width: 100%;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }

        textarea {
            resize: none;
            height: 80px;
        }

        select {
            background-color: #fff;
        }

        c\\ p {
            text-align: center;
            color: #28a745;
            font-weight: bold;
        }

        form + form {
            text-align: center;
            margin-top: 20px;
        }

        form + form input[type="submit"] {
            width: auto;
            background-color: #dc3545;
        }

        form + form input[type="submit"]:hover {
            background-color: #a71d2a;
        }

        /* Ensure the buttons inside the forms are styled correctly */
        form button, form input[type="submit"] {
            background-color: #007BFF;
            color: #fff;
            border: none;
            padding: 10px 20px;
            font-size: 16px;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s ease;
            width: 100%;
            text-align: center; /* Ensure the text is centered */
        }

        /* Button hover effect */
        form button:hover, form input[type="submit"]:hover {
            background-color: #0056b3;
        }

        /* Ensure the Dashboard and Logout buttons are styled */
        form input[type="submit"] {
            width: 100%;
            font-size: 1em;
        }

        form button {
            font-size: 1.25em; /* Increase font size for the dashboard button */
        }

    </style>
    <script>
        window.onload = function() {
            fetch('api/evaluations/teachers')
                .then(response => {
                    if (!response.ok) {
                        throw new Error(`HTTP error! Status: ${response.status}`);
                    }
                    return response.json();
                })
                .then(teachers => {
                    const teacherSelect = document.getElementById('teacher');
                    teacherSelect.innerHTML = '';

                    const placeholderOption = document.createElement('option');
                    placeholderOption.value = '';
                    placeholderOption.textContent = 'Select a Teacher';
                    placeholderOption.disabled = true;
                    placeholderOption.selected = true;
                    teacherSelect.appendChild(placeholderOption);

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
<form id="evaluationForm">
    <h1>Submit Evaluation</h1>
    <table>
        <tr>
            <td><label for="activity">Activity:</label></td>
            <td><input type="text" id="activity" name="activity" placeholder="Activity" /></td>
        </tr>
        <tr>
            <td><label for="activityType">Activity Type:</label></td>
            <td><input type="text" id="activityType" name="activityType" placeholder="Activity Type" /></td>
        </tr>
        <tr>
            <td><label for="grade">Grade:</label></td>
            <td><input type="text" id="grade" name="grade" placeholder="Grade (1-100)" /></td>
        </tr>
        <tr>
            <td><label for="comment">Comment:</label></td>
            <td><textarea id="comment" name="comment" placeholder="Comment"></textarea></td>
        </tr>
        <tr>
            <td><label for="teacher">Teacher:</label></td>
            <td>
                <select name="teacher" id="teacher"></select>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Submit" />
            </td>
        </tr>
    </table>
    <p id="message" style="color: green; font-weight: bold;"></p>
</form>
<form action="/LabREST8_war_exploded/dashboard.jsp">
    <button type="submit">Dashboard TOP</button>
</form>
<form action="/LabREST8_war_exploded/logout" method="get">
    <input type="submit" value="Log out" />
</form>

<script>
    // Fetch teachers from API and populate the dropdown
    window.onload = function() {
        fetch('api/evaluations/teachers')
            .then(response => {
                if (!response.ok) {
                    throw new Error(`HTTP error! Status: ${response.status}`);
                }
                return response.json();
            })
            .then(teachers => {
                const teacherSelect = document.getElementById('teacher');
                teacherSelect.innerHTML = '';

                const placeholderOption = document.createElement('option');
                placeholderOption.value = '';
                placeholderOption.textContent = 'Select a Teacher';
                placeholderOption.disabled = true;
                placeholderOption.selected = true;
                teacherSelect.appendChild(placeholderOption);

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

    // Handle form submission with AJAX
    document.getElementById('evaluationForm').onsubmit = function(event) {
        event.preventDefault(); // Prevent the default form submission

        // Collect form data
        const formData = new FormData(this);

        // Convert FormData to URL-encoded string
        const urlEncodedData = new URLSearchParams(formData).toString();

        fetch('api/evaluations/submitEvaluation', {
            method: 'POST',
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
            body: urlEncodedData
        })
            .then(response => response.text()) // Assuming the API returns a plain text message
            .then(message => {
                document.getElementById('message').textContent = message;
            })
            .catch(error => {
                console.error('Error submitting evaluation:', error);
                document.getElementById('message').textContent = 'An error occurred while submitting the evaluation.';
            });
    };

</script>

</body>
</html>

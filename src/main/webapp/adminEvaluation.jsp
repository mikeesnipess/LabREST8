<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Panel: Manage Evaluations</title>
    <style>
        /* General page styles */
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f7f6;
            color: #333;
            display: flex;
            justify-content: center;
            align-items: flex-start; /* Align items at the top instead of center */
            min-height: 100vh; /* Ensure full page height */
            margin: 0;
        }

        h2 {
            color: #4CAF50;
            font-size: 2.5em;
            margin-bottom: 30px;
            font-weight: 600;
        }

        /*.container {*/
        /*    background-color: white;*/
        /*    padding: 30px;*/
        /*    border-radius: 10px;*/
        /*    box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);*/
        /*    text-align: center;*/
        /*    width: 90%;*/
        /*    max-width: 1200px; !* Limit the maximum width for better responsiveness *!*/
        /*    overflow-x: auto;*/
        /*    overflow-y: auto; !* Allow vertical scrolling when content exceeds container height *!*/
        /*    max-height: calc(100vh - 60px); !* Ensure the container is never taller than the viewport height *!*/
        /*}*/

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #4CAF50;
            color: white;
        }

        td {
            background-color: #f9f9f9;
        }

        tr:nth-child(even) td {
            background-color: #f2f2f2;
        }

        .button {
            background-color: #d9534f;
            color: white;
            border: none;
            padding: 8px 16px;
            text-align: center;
            cursor: pointer;
            border-radius: 5px;
            font-size: 1em;
            transition: background-color 0.3s ease;
        }

        .button:hover {
            background-color: #c9302c;
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


        /* Style for the Dashboard button */
        .dashboard-btn {
            background-color: #007bff;
            color: white;
            border: none;
            padding: 12px 24px;
            font-size: 16px;
            font-weight: bold;
            border-radius: 8px;
            cursor: pointer;
            transition: background-color 0.3s ease, transform 0.2s ease;
            width: 100%; /* Make the button take the full width of the container */
        }

        .dashboard-btn:hover {
            background-color: #0056b3; /* Darker blue on hover */
            transform: scale(1.05); /* Slight scale effect on hover */
        }

        .dashboard-btn:focus {
            outline: none; /* Remove the outline on focus */
            box-shadow: 0 0 5px rgba(0, 123, 255, 0.5); /* Optional: add a glow effect on focus */
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
        window.onload = function () {
            // Fetch evaluations for the admin
            fetch('api/evaluations/getAll') // API endpoint to fetch all evaluations
                .then(response => {
                    if (!response.ok) {
                        throw new Error(`HTTP error! Status: ${response.status}`);
                    }
                    return response.json(); // Parse the JSON response
                })
                .then(evaluations => {
                    console.log('Admin Evaluations data:', evaluations); // Log the parsed data for inspection

                    // Get the table body element
                    const tableBody = document.getElementById('adminEvaluationsTableBody');

                    // Clear any existing rows
                    tableBody.innerHTML = '';

                    // Populate the table with evaluations
                    evaluations.forEach(evaluation => {
                        const row = document.createElement('tr');
                        row.id = 'evaluation-' + evaluation.id; // Add an ID for the row

                        // Create and append cells for each field
                        const idCell = document.createElement('td');
                        idCell.textContent = evaluation.id;
                        row.appendChild(idCell);

                        const studentCell = createEditableCell('studentName', evaluation.studentName);
                        row.appendChild(studentCell);

                        const teacherCell = createEditableCell('teacherName', evaluation.teacherName);
                        row.appendChild(teacherCell);

                        const activityCell = createEditableCell('activity', evaluation.activity);
                        row.appendChild(activityCell);

                        const activityTypeCell = createEditableCell('activityType', evaluation.activityType);
                        row.appendChild(activityTypeCell);

                        const gradeCell = createEditableCell('grade', evaluation.grade);
                        row.appendChild(gradeCell);

                        const commentCell = createEditableCell('comment', evaluation.comment);
                        row.appendChild(commentCell);

                        // const timestampCell = document.createElement('td');
                        // timestampCell.textContent = evaluation.timestamp;
                        // row.appendChild(timestampCell);

                        // Add the action buttons (Delete, Edit)
                        const actionCell = document.createElement('td');

                        // Edit button
                        const editButton = document.createElement('button');
                        editButton.classList.add('button');
                        editButton.textContent = 'Edit';
                        editButton.onclick = function () {
                            toggleEditable(row, evaluation.id);
                        };
                        actionCell.appendChild(editButton);

                        // Delete button
                        const deleteButton = document.createElement('button');
                        deleteButton.classList.add('button');
                        deleteButton.textContent = 'Delete';
                        deleteButton.onclick = function () {
                            // AJAX request to delete the evaluation
                            fetch('api/evaluations/delete/' + evaluation.id, {
                                method: 'DELETE',
                            })
                                .then(response => {
                                    if (!response.ok) {
                                        throw new Error('Failed to delete the evaluation');
                                    }
                                    // Remove the row from the table if deletion is successful
                                    row.remove();
                                })
                                .catch(error => {
                                    console.error('Error deleting evaluation:', error);
                                });
                        };
                        actionCell.appendChild(deleteButton);

                        row.appendChild(actionCell);

                        // Append the row to the table body
                        tableBody.appendChild(row);
                    });
                })
                .catch(error => {
                    console.error('Error fetching evaluations:', error);
                });
        };

        // Function to create editable cells with input fields
        function createEditableCell(fieldName, value) {
            const cell = document.createElement('td');
            const input = document.createElement('input');
            input.type = 'text';
            input.name = fieldName;
            input.value = value;
            input.disabled = true; // Initially disable editing
            cell.appendChild(input);
            return cell;
        }

        // Function to create uneditable cells for studentName and teacherName
        function createUneditableCell(fieldName, value) {
            const cell = document.createElement('td');
            const input = document.createElement('input');
            input.type = 'text';
            input.name = fieldName;
            input.value = value;
            input.disabled = true; // Make it uneditable
            input.style.backgroundColor = '#f0f0f0'; // Gray out the background
            cell.appendChild(input);
            return cell;
        }

        // Function to toggle between editable and non-editable states
        function toggleEditable(row, evaluationId) {
            const cells = row.querySelectorAll('td');
            const inputs = row.querySelectorAll('input');
            const isEditing = inputs[0].disabled === false; // Check if already editing

            // Toggle editing state for each input (except for studentName and teacherName)
            inputs.forEach(input => {
                if (input.name !== 'studentName' && input.name !== 'teacherName') {
                    input.disabled = !input.disabled; // Only toggle editable fields
                }
            });

            if (!isEditing) {
                // If switching to editable mode, change the button text to "Save"
                const saveButton = document.createElement('button');
                saveButton.classList.add('button');
                saveButton.textContent = 'Save';
                saveButton.onclick = function () {
                    saveChanges(row, evaluationId);
                };

                // Replace the Edit button with the Save button
                row.querySelector('.button').replaceWith(saveButton);
            } else {
                // If switching back to view mode, revert the button text to "Edit"
                const editButton = document.createElement('button');
                editButton.classList.add('button');
                editButton.textContent = 'Edit';
                editButton.onclick = function () {
                    toggleEditable(row, evaluationId);
                };

                // Replace the Save button with the Edit button
                row.querySelector('.button').replaceWith(editButton);
            }
        }


        // Function to save the changes and call the PUT API
        function saveChanges(row, evaluationId) {
            const inputs = row.querySelectorAll('input');
            const updatedEvaluation = {};

            // Collect only the fields that need to be updated
            inputs.forEach(input => {
                // Check if the field is one of the fields to update
                if (input.name === 'activity' || input.name === 'activityType' || input.name === 'comment' || input.name === 'grade') {
                    updatedEvaluation[input.name] = input.value;
                }
            });

            // Send PUT request to update the evaluation
            fetch(`api/evaluations/update/` + evaluationId, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(updatedEvaluation),
            })
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Failed to update the evaluation');
                    }

                    // Optionally, you can display a success message
                    alert('Evaluation updated successfully');

                    // Reload the page after successfully saving the changes
                    location.reload(); // This will refresh the page
                })
                .catch(error => {
                    console.error('Error updating evaluation:', error);
                });
        }


    </script>

</head>
<body>


<div class="container">
    <h2>Admin Panel: Manage Evaluations</h2>

    <table>
        <thead>
        <tr>
            <th>ID Evaluation</th>
            <th>Student</th>
            <th>Teacher</th>
            <th>Activity</th>
            <th>Activity Type</th>
            <th>Grade</th>
            <th>Comment</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody id="adminEvaluationsTableBody">
        <!-- Table rows will be populated dynamically via JavaScript -->
        </tbody>
    </table>
    <form action="/LabREST8_war_exploded/dashboard.jsp">
        <button type="submit">Dashboard TOP</button>
    </form>
    <form action="/LabREST8_war_exploded/logout" method="get">
        <input type="submit" value="Log out" />
    </form>
</div>

</body>
</html>

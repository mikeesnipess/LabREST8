<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Top Teachers Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 800px;
            margin: 50px auto;
            background: #fff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
            overflow: hidden;
        }

        .header {
            background-color: #4CAF50;
            color: white;
            text-align: center;
            padding: 20px 0;
            font-size: 24px;
        }

        .back-button {
            display: block;
            margin: 20px auto;
            padding: 10px 20px;
            background-color: #007BFF;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s ease;
            text-align: center;
        }

        .back-button:hover {
            background-color: #0056b3;
        }

        .table-container {
            padding: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 10px;
        }

        th, td {
            border: 1px solid #ddd;
            text-align: left;
            padding: 8px;
        }

        th {
            background-color: #4CAF50;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        .teacher-name {
            font-weight: bold;
        }

        .activity {
            font-style: italic;
            color: #555;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="header">
        Top Teachers Dashboard
    </div>

    <div class="table-container">
        <table>
            <thead>
            <tr>
                <th>TOP#</th>
                <th>Teacher Name</th>
                <th>Activity</th>
                <th>Total Grade</th>
            </tr>
            </thead>
            <tbody id="teachersTableBody">
            <!-- Data will be dynamically populated here -->
            </tbody>
        </table>
    </div>
</div>

<!-- Back Button -->
<button class="back-button" onclick="goBack()">Back</button>

<script>
    // Function to go back to the previous page
    function goBack() {
        window.history.back(); // Navigates to the previous page
    }

    window.onload = function () {
        // Fetch the evaluations data from the backend API
        fetch('http://localhost:8080/LabREST8_war_exploded/api/evaluations/dashboard')
            .then(response => {
                if (!response.ok) {
                    throw new Error('HTTP error! Status: ' + response.status);
                }
                return response.json(); // Parse the response as JSON
            })
            .then(data => {
                const tableBody = document.getElementById("teachersTableBody");

                // Clear any existing rows
                tableBody.innerHTML = "";

                // Populate the table with the fetched data
                data.forEach((teacher, index) => {
                    const row = document.createElement("tr");

                    // Add ranking number
                    const numberCell = document.createElement("td");
                    numberCell.textContent = index + 1;
                    row.appendChild(numberCell);

                    // Add teacher name
                    const teacherNameCell = document.createElement("td");
                    teacherNameCell.textContent = teacher.teacherName || "N/A";
                    row.appendChild(teacherNameCell);

                    // Add activity
                    const activityCell = document.createElement("td");
                    activityCell.textContent = teacher.activity || "N/A";
                    row.appendChild(activityCell);

                    // Add grade
                    const gradeCell = document.createElement("td");
                    gradeCell.textContent = teacher.grade || "N/A";
                    row.appendChild(gradeCell);

                    // Append the row to the table
                    tableBody.appendChild(row);
                });
            })
            .catch(error => {
                console.error('Error fetching data:', error);
                alert("Failed to load data: " + error.message);
            });
    };
</script>
</body>
</html>

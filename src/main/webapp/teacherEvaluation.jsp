<%--<%@ taglib uri="http://jakarta.apache.org/taglibs/standard" prefix="c" %>--%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <title>Teacher Evaluations</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 0;
      padding: 0;
      background-color: #f9f9f9;
      color: #333;
    }

    h1 {
      text-align: center;
      color: #444;
      margin-top: 20px;
    }

    table {
      width: 80%;
      margin: 20px auto;
      border-collapse: collapse;
      background-color: #fff;
      box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    }

    th, td {
      padding: 12px 15px;
      text-align: left;
      border: 1px solid #ddd;
    }

    th {
      background-color: #f4f4f4;
      font-weight: bold;
    }

    tr:nth-child(even) {
      background-color: #f9f9f9;
    }

    tr:hover {
      background-color: #f1f1f1;
    }

    form {
      text-align: center;
      margin-top: 20px;
    }

    input[type="submit"] {
      background-color: #007BFF;
      color: white;
      border: none;
      padding: 10px 20px;
      font-size: 16px;
      border-radius: 4px;
      cursor: pointer;
      transition: background-color 0.3s ease;
    }

    input[type="submit"]:hover {
      background-color: #0056b3;
    }
  </style>
  <script>
    // Fetch evaluations for the logged-in teacher
    window.onload = function () {
      fetch('api/evaluations/getTeacherEvaluations') // API endpoint to fetch evaluations
              .then(response => {
                if (!response.ok) {
                  throw new Error(`HTTP error! Status: ${response.status}`);
                }
                return response.json(); // Parse the JSON response
              })
              .then(evaluations => {
                console.log('Evaluations data:', evaluations); // Log the parsed data for inspection

                // Get the table body element
                const tableBody = document.getElementById('evaluationsTableBody');

                // Clear any existing rows
                tableBody.innerHTML = '';

                // Populate the table with evaluations
                evaluations.forEach(evaluation => {
                  const row = document.createElement('tr');

                  // Create and append cells for each field
                  const activityCell = document.createElement('td');
                  activityCell.textContent = evaluation.activity;
                  row.appendChild(activityCell);

                  const activityTypeCell = document.createElement('td');
                  activityTypeCell.textContent = evaluation.activityType;
                  row.appendChild(activityTypeCell);

                  const gradeCell = document.createElement('td');
                  gradeCell.textContent = evaluation.grade;
                  row.appendChild(gradeCell);

                  const commentCell = document.createElement('td');
                  commentCell.textContent = evaluation.comment;
                  row.appendChild(commentCell);

                  const regNumberCell = document.createElement('td');
                  regNumberCell.textContent = evaluation.registrationNumber;
                  row.appendChild(regNumberCell);

                  tableBody.appendChild(row);
                });
              })
              .catch(error => {
                console.error('Error fetching evaluations:', error);
              });
    };
  </script>
</head>
<body>
<h1>Evaluations for Teacher: ${teacherEvaluationBean.loggedInTeacher.username}</h1>

<!-- Table for displaying evaluations -->
<table>
  <thead>
  <tr>
    <th>Activity</th>
    <th>Activity Type</th>
    <th>Grade</th>
    <th>Comment</th>
    <th>Registration Number</th>
  </tr>
  </thead>
  <tbody id="evaluationsTableBody">
  <!-- Evaluations will be dynamically populated here -->
  </tbody>
</table>
<form action="/LabREST8_war_exploded/dashboard.jsp">
  <input type="submit" value="Dashboard" />
</form>
<!-- Form for log out -->
<form action="/LabREST8_war_exploded/logout" method="get">
  <input type="submit" value="Log out" />
</form>
</body>
</html>

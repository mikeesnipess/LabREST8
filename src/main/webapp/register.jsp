<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Register</title>
  <style>
    /* General page styles */
    body {
      font-family: 'Arial', sans-serif;
      background-color: #f4f7f6;
      color: #333;
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
      margin: 0;
    }

    h1 {
      color: #4CAF50;
      font-size: 2.5em;
      margin-bottom: 30px;
      font-weight: 600;
    }

    .container {
      background-color: white;
      padding: 40px;
      border-radius: 10px;
      box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
      text-align: center;
      width: 320px;
      border: 1px solid #e0e0e0;
    }

    .container h1 {
      font-size: 1.8em;
      font-weight: bold;
      margin-bottom: 15px;
    }

    .container label {
      font-size: 1.1em;
      color: #555;
      margin-bottom: 8px;
      text-align: left;
      display: block;
    }

    .container input,
    .container select {
      width: 100%;
      padding: 12px;
      margin-bottom: 15px;
      border: 1px solid #ccc;
      border-radius: 5px;
      font-size: 1em;
      box-sizing: border-box;
    }

    .container button {
      background-color: #4CAF50;
      color: white;
      border: none;
      padding: 12px 25px;
      text-align: center;
      text-decoration: none;
      display: inline-block;
      font-size: 1.2em;
      margin-top: 20px;
      cursor: pointer;
      border-radius: 5px;
      width: 100%;
      transition: background-color 0.3s ease, transform 0.3s ease;
    }

    .container button:hover {
      background-color: #45a049;
      transform: translateY(-2px);
    }

    .container form {
      margin: 0;
    }

    .container p {
      font-size: 1.1em;
      color: #555;
      margin-top: 20px;
    }

    .container .back-link {
      font-size: 1.1em;
      text-decoration: none;
      color: #4CAF50;
    }

    .container .back-link:hover {
      text-decoration: underline;
    }
  </style>
</head>
<body>
<div class="container">
  <h1>Register</h1>

  <!-- Registration Form -->
  <form action="${pageContext.request.contextPath}/api/auth/register" method="get">

    <!-- Username -->
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required />

    <!-- Password -->
    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required />

    <!-- Role -->
    <label for="role">Role:</label>
    <select id="role" name="role" required>
      <option value="Student">Student</option>
      <option value="Teacher">Teacher</option>
      <option value="Admin">Admin</option>
    </select>

    <!-- Submit Button -->
    <button type="submit">Register</button>
  </form>

  <!-- Link to Login -->
  <p>Already have an account? <a href="api/auth/login" class="back-link">Login here</a></p>
</div>
</body>
</html>

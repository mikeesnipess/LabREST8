<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>JSP - Select Action</title>
  <style>
    /* General page styles */
    body {
      font-family: Arial, sans-serif;
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
      font-size: 2em;
      margin-bottom: 20px;
    }

    .container {
      background-color: white;
      padding: 30px;
      border-radius: 10px;
      box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
      text-align: center;
      width: 300px;
    }

    .container button {
      background-color: #4CAF50;
      color: white;
      border: none;
      padding: 10px 20px;
      text-align: center;
      text-decoration: none;
      display: inline-block;
      font-size: 1.2em;
      margin-top: 20px;
      cursor: pointer;
      border-radius: 5px;
      transition: background-color 0.3s ease;
    }

    .container button:hover {
      background-color: #45a049;
    }

    .container form {
      margin: 0;
    }
  </style>
</head>
<body>
<div class="container">
  <h1>Welcome! Please choose an action</h1>

  <!-- Button to redirect to Login page -->
  <form action="api/auth/login">
    <button type="submit">Login</button>
  </form>

  <!-- Button to redirect to Register page -->
  <form action="register.jsp">
    <button type="submit">Register</button>
  </form>
</div>
</body>
</html>

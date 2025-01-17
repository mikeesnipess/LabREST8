<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>JSP - Select Action</title>
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

    .container button {
      background-color: #4CAF50;
      color: white;
      border: none;
      padding: 12px 25px;
      text-align: center;
      text-decoration: none;
      display: inline-block;
      font-size: 1.2em;
      margin-top: 25px;
      cursor: pointer;
      border-radius: 5px;
      transition: background-color 0.3s ease, transform 0.3s ease;
      width: 100%;
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
  </style>
</head>
<body>
<div class="container">
  <h1>Welcome to Teacher Rating!</h1>
  <p>Please choose an action:</p>

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

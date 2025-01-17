<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Login</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        h1 {
            text-align: center;
            color: #444;
        }

        .container .back-link {
            display: inline-block;
            font-size: 1.1em;
            color: white;
            background-color: #4CAF50;
            padding: 10px 20px;
            text-decoration: none;
            border-radius: 4px;
            text-align: center;
            transition: background-color 0.3s ease, transform 0.2s ease;
        }

        .container .back-link:hover {
            background-color: #45a049;
            transform: translateY(-2px);
        }

        .container .back-link:focus {
            outline: none;
            background-color: #3e8e41;
        }

        form {
            background-color: #fff;
            padding: 20px 30px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 300px;
            text-align: center;
            margin-bottom: 15px; /* Space between forms */
        }

        label {
            display: block;
            margin: 15px 0 5px;
            font-weight: bold;
            color: #333;
        }

        input[type="text"], input[type="password"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 16px;
            box-sizing: border-box;
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
            width: 100%;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }

        input:focus {
            outline: none;
            border-color: #007BFF;
            box-shadow: 0 0 5px rgba(0, 123, 255, 0.5);
        }

        .go-home-button {
            width: 100%;
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            font-size: 16px;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .go-home-button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
<form action="j_security_check" method="post">
    <h1>Login</h1>
    <label for="username">Username:</label>
    <input type="text" id="username" name="j_username" placeholder="Enter your username" />

    <label for="password">Password:</label>
    <input type="password" id="password" name="j_password" placeholder="Enter your password" />

    <input type="submit" value="Login" />
    <p>Go home! <a href="/LabREST8_war_exploded/index.jsp" class="back-link">Back!</a></p>
</form>
</body>
</html>

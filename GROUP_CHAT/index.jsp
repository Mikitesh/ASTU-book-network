<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Chat Application</title>
    <link rel="stylesheet" href="style.css">

   
</head>
<body>
    <div class="container">
        <h1>Welcome to the Group Chat</h1>
        <form action="startchat" method="POST">
            <label for="id">Enter ID:</label>
            <input type="text" name="id" id="id" required><br>
            <label for="username">Enter Username:</label>
            <input type="text" name="username" id="username" required><br>
            <label for="password">Enter Password:</label>
            <input type="password" name="password" id="password" required><br>
            <button type="submit" value="submit">Start Chatting</button>
        </form>
    </div>
</body>
</html>
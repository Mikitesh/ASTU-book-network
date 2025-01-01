<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Start Chatting</title>
    <link rel="stylesheet" href="style.css">
</head>
<body onload="startchatting()">
    <div class="chat-window">
        <div id="chat-messages" onload="startchatting()" >
            <!-- Messages will be loaded here -->
        </div>
        <input type="text" id="message" placeholder="Type a message">
        <button onclick="sendMessage()">Send</button>
        <a  href="logout.jsp">Logout</a>
    </div>
    
    <img id="loader" src="loader.gif" style="display:none;">
    
    <script>
        // Load messages on page load
        function startchatting() {
            fetch("/startchatting", {
                method: "POST", 
                body: new URLSearchParams('message=')
            }).then(response => response.text())
              .then(data => {
                  document.getElementById("chat-messages").innerHTML = data;
              });
        }

        // Send message to server and update chat window
        function sendMessage() {
            const message = document.getElementById("message").value;
            document.getElementById("loader").style.display = "inline";  // Show loader
            fetch("/startchatting", {
                method: "POST",
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded"
                },
                body: `message=${message}`
            }).then(response => response.text())
              .then(data => {
                  document.getElementById("chat-messages").innerHTML = data;  // Update the chat window
                  document.getElementById("message").value = "";  // Clear the input
                  document.getElementById("loader").style.display = "none";  // Hide loader
              });
        }
    </script>
</body>
</html>

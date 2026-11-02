<%--
  Created by IntelliJ IDEA.
  User: jub
  Date: 12/10/2026
  Time: 01:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    // Récupération de la session existante, sans en créer une nouvelle
    jakarta.servlet.http.HttpSession sessionUser =
            request.getSession(false);

    if (sessionUser == null ||
            sessionUser.getAttribute("login") == null) {

        response.sendRedirect("session-login.jsp");
        return;
    }

    String login = (String) sessionUser.getAttribute("login");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Session privée</title>
</head>
<body>

<h1>Welcome</h1>

<p><%= login %></p>

</body>
</html>

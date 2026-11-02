<%--
  Created by IntelliJ IDEA.
  User: jub
  Date: 12/10/2026
  Time: 01:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="jakarta.servlet.http.Cookie" %>
<%@ page import="java.util.Arrays" %>

<%
    Cookie[] cookies = request.getCookies();
    String loginCookie = null;

    if (cookies != null) {
        loginCookie = Arrays.stream(cookies)
                .filter(cookie -> "login".equals(cookie.getName()))
                .map(Cookie::getValue)
                .findFirst()
                .orElse(null);
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Connexion</title>
</head>
<body>

<h1>Session</h1>

<form method="post" action="/demo/jsp/session-authenticate">

    <label for="login">Login :</label>
    <input type="text"
           id="login"
           name="login"
           value="<%= loginCookie != null ? loginCookie : "" %>"
           required>

    <br><br>

    <label for="password">Mot de passe :</label>
    <input type="password"
           id="password"
           name="password"
           required>

    <br><br>

    <input type="submit" value="Se connecter">

</form>

</body>
</html>

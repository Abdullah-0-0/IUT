<%--
  Created by IntelliJ IDEA.
  User: chams
  Date: 08/11/2026
  Time: 16:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Formulaire</title>
</head>
<body>
<form action="formule" method="POST">

    <label for="login">Login :</label>
    <input type="text" id="log" name="login" required>

    <label for="password">Password :</label>
    <input type="password" id="pwd" name="password" required>

    <button type="submit">Envoyer</button>

</form>
</body>
</html>

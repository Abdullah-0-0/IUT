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
<form action="${pageContext.request.contextPath}/formule1" method="POST">
    <h1>PAGE LOGIN FORMULAIRE PERSISTENT </h1>
    <label for="login">Login :</label>
    <input type="text" id="log" name="login" value="${loginMemo}" required>

    <label for="password">Password :</label>
    <input type="password" id="pwd" name="password" required>

    <button type="submit">Envoyer</button>

</form>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: jub
  Date: 12/10/2026
  Time: 01:56
  To change this template use File | Settings | File Templates.
  Page JSP pour illustrer la notion de session
  et l'utilisation des cookies pour mémoriser le login.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- Importation de la classe Cookie pour manipuler les cookies HTTP --%>
<%@ page import="jakarta.servlet.http.Cookie" %>
<%-- Importation de Arrays pour rechercher un cookie dans le tableau --%>
<%@ page import="java.util.Arrays" %>

<%
    // Récupère tous les cookies envoyés par le navigateur dans la requête HTTP
    Cookie[] cookies = request.getCookies();
    // Variable destinée à stocker la valeur du cookie nommé "login"
    String loginCookie = null;

    // Vérifie qu'il existe au moins un cookie dans la requête
    if (cookies != null) {
        // Parcourt les cookies pour rechercher celui dont le nom est "login"
        loginCookie = Arrays.stream(cookies)
                // Conserve uniquement le cookie dont le nom correspond à "login"
                .filter(cookie -> "login".equals(cookie.getName()))
                // Récupère la valeur du cookie trouvé
                .map(Cookie::getValue)
                // Récupère la première valeur trouvée, ou null si aucun cookie ne correspond
                .findFirst()
                // Définit null si aucun cookie "login" n'existe
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
    <%--
       Si un cookie "login" existe, son contenu est utilisé
       pour préremplir le champ.
       Sinon, le champ reste vide.
   --%>
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

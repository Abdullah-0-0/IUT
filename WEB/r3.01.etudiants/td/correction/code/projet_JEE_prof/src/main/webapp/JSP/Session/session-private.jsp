<%--
  Created by IntelliJ IDEA.
  User: jub
  Date: 12/10/2026
  Time: 01:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>

<%
    // Récupère la session existante de l'utilisateur.
    // Le paramètre false empêche la création d'une nouvelle session
    // si aucune session n'existe déjà.
    HttpSession sessionUser =
            request.getSession(false);
    // Vérifie si la session existe et si l'attribut "login" est présent.
    // Si l'une de ces conditions n'est pas remplie,
    // l'utilisateur n'est pas considéré comme connecté.
    if (sessionUser == null ||
            sessionUser.getAttribute("login") == null) {
        // Redirige l'utilisateur vers la page de connexion
        // pour qu'il puisse s'authentifier.
        response.sendRedirect("session-login.jsp");
        // Arrête l'exécution de la page JSP après la redirection.
        return;
    }
    // Récupère le login enregistré dans la session
    // et le convertit en chaîne de caractères.
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
<%--
    Affiche le login récupéré depuis la session.
    L'utilisateur peut accéder à cette page uniquement
    si une session valide contenant l'attribut "login" existe.
--%>
<p><%= login %></p>

</body>
</html>

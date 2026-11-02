<%-- Un simple Hello World
 pour illustrer :
 les coommentaires
 la directive page <%@ page %>
 la déclaration <%! %>
 le Scriptlet <% %>
 l'expression <%= %>
 --%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Hello World JSP</title>
</head>
<%! String text = "Hello World!"; %>
<body>
<h1>
    <%
        out.println(text);
    %>
</h1>
<!-- Alternative plus courte via une expression JSP -->
<p><%= text %></p>
</body>
</html>

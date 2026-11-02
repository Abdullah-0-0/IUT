<%@ page import="iut.nantes.products.dao.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<Product> products = new ArrayList<>();

    products.add(new Product(1, "Ordinateur", 750.0, 10));
    products.add(new Product(2, "Clavier", 35.0, 25));
    products.add(new Product(3, "Souris", 20.0, 50));
    products.add(new Product(4, "Écran", 180.0, 8));
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Produits</title>
</head>
<body>

<h1>Liste des produits</h1>

<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Nom</th>
        <th>Prix (€)</th>
        <th>Quantité</th>
    </tr>
    </thead>
    <tbody>
    <% for (Product product : products) { %>
    <tr>
        <td><%= product.getId() %></td>
        <td><%= product.getName() %></td>
        <td><%= product.getPrice() %></td>
        <td><%= product.getQuantity() %></td>
    </tr>
    <% } %>
    </tbody>
</table>

</body>
</html>

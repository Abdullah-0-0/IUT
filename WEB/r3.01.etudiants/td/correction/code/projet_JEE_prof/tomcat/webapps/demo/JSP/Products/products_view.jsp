<%--
  Created by IntelliJ IDEA.
  User: jub
  Date: 12/10/2026
  Time: 01:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="iut.nantes.products.dao.Product" %>
<%@ page import="java.util.List" %>
<% List<Product> products =
        (List<Product>) request.getAttribute("products");%>

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
        <th> + </th>
        <th> - </th>
    </tr>
    </thead>
    <tbody>
    <% for (Product product : products) { %>
    <tr>
        <td><%= product.getId() %></td>
        <td><%= product.getName() %></td>
        <td><%= product.getPrice() %></td>
        <td><%= product.getQuantity() %></td>
        <td> <form method="post"
                   action="${pageContext.request.contextPath}/products/products_controller">
            <input type="hidden" name="id"
                   value="<%= product.getId() %>">
            <input type="hidden" name="action" value="increment">
            <button type="submit">+</button>
        </form> </td>
        <td>
            <form method="post"
                  action="${pageContext.request.contextPath}/products/products_controller">
                <input type="hidden" name="id"
                       value="<%= product.getId() %>">
                <input type="hidden" name="action" value="decrement">
                <button type="submit">−</button>
            </form>
        </td>
    </tr>
    <% } %>
    </tbody>
</table>

</body>
</html>


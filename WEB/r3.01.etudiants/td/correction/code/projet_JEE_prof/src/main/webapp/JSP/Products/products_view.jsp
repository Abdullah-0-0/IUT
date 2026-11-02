<%--
    Approche MVC : cette page correspond à la VUE.

    Son rôle est d'afficher les produits transmis par le contrôleur.
    Elle ne crée pas les produits et ne contient pas la logique
    métier de gestion des quantités.

    Les actions de modification sont envoyées au contrôleur
    via des formulaires HTML.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="iut.nantes.products.dao.Product" %>
<%@ page import="java.util.List" %>
<%
    // Récupère la liste des produits transmise par le contrôleur.
    // Le contrôleur a préalablement placé cette liste dans la requête
    // avec request.setAttribute("products", products).
    List<Product> products =
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
        <td>
            <%--
            Formulaire permettant d'augmenter la quantité.
            La méthode POST envoie les données au contrôleur.
            L'URL utilise contextPath pour fonctionner même
            si le nom du contexte de l'application change.
        --%>
            <form method="post"
                   action="${pageContext.request.contextPath}/products/products_controller">
            <input type="hidden" name="id"
                   value="<%= product.getId() %>">
            <input type="hidden" name="action" value="increment">
            <button type="submit">+</button>
        </form> </td>
        <td>
            <%--
            Formulaire permettant de diminuer la quantité.
            Il envoie également une requête POST au contrôleur.
        --%>
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


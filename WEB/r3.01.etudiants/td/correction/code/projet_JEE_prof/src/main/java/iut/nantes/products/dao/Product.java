package iut.nantes.products.dao;

import java.util.Objects;

/*
 * Classe Product représentant un produit dans l'application.
 *
 * Cette classe constitue le modèle de données d'un produit.
 * Chaque produit possède un identifiant, un nom, un prix
 * et une quantité en stock.
 */
public class Product {
    private Integer id;
    private String name;
    private Double price;

    private Integer quantity;

    public Product(Integer id, String name, Double price, Integer quantity) {
        if (Objects.isNull(id) || Objects.isNull(name) || Objects.isNull(price) || Objects.isNull(quantity)) {
            throw new IllegalArgumentException();
        }
        if (price <0 ||  quantity < 0) {
            throw new IllegalArgumentException("price and/or quantity cannot be negative");
        }
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        if (quantity>=0) {
            this.quantity = quantity;
        }
    }
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }

    /*
     * Redéfinit equals() pour comparer deux produits.
     *
     * Deux produits sont considérés comme égaux si leurs
     * attributs id, name, price et quantity sont égaux.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(name, product.name) && Objects.equals(price, product.price) && Objects.equals(quantity, product.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, quantity);
    }
}

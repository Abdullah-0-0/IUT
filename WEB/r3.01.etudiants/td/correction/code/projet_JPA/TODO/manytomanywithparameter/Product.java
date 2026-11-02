package iut.nantes.hibernate.entities.manytomanywithparameter;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "iut.nantes.hibernate.entities.manytomanywithparameter.Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "product_name", nullable = false, length = 150,unique=true)
    private String name;
    @Column(name = "product_price", nullable = false)
    private Double price;
    @Column(name = "product_quantity", nullable = false)
    private Integer quantity;
    @OneToMany(mappedBy = "product")
    private List<UserProduct> owners = new ArrayList<>();

    public Product() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Product(String name, Double price, Integer quantity) {
        if (price <0 ||  quantity < 0) {
            throw new IllegalArgumentException("price and/or quantity cannot be negative");
        }
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

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }

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


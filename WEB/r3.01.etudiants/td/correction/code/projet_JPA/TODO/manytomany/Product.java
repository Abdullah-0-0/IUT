package iut.nantes.hibernate.entities.manytomany;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "iut.nantes.hibernate.entities.manytomany.Product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "product_name", nullable = false, length = 150, unique = true)
    private String name;

    @Column(name = "product_price", nullable = false)
    private Double price;

    @Column(name = "product_quantity", nullable = false)
    private Integer quantity;


    public Product() {}

    public Product(String name, Double price, Integer quantity) {
        if (price < 0 || quantity < 0) {
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

    @ManyToMany
    @JoinTable(
            name = "product_user",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> users = new HashSet<>();

    public Set<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        users.add(user);
        user.getProducts().add(this);
    }

    public void removeUser(User user) {
        users.remove(user);
        user.getProducts().remove(this);
    }
}

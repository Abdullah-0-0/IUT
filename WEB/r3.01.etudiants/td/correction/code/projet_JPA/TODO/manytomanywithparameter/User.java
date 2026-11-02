package iut.nantes.hibernate.entities.manytomanywithparameter;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name="iut.nantes.hibernate.entities.manytomanywithparameter.User")
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<UserProduct> products = new ArrayList<>();

    public User() {}

    public User(String username) {
        this.username = username;
    }

    public void addProduct(Product product, int quantity) {
        UserProduct userProduct = new UserProduct(this, product, quantity);
        products.add(userProduct);
    }

    public Integer getId() {
        return id;
    }

    public List<UserProduct> getProducts() {
        return products;
    }
}

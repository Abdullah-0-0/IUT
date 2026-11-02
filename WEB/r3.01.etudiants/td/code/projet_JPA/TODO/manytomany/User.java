package iut.nantes.hibernate.entities.manytomany;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "iut.nantes.hibernate.entities.manytomany.User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String username;

    @ManyToMany(mappedBy = "users")
    private Set<Product> products = new HashSet<>();

    public User() {}

    public User(String username) {
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Set<Product> getProducts() {
        return products;
    }
}

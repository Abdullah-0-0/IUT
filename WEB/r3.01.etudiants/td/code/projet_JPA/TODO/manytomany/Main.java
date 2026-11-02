package iut.nantes.hibernate.entities.manytomany;

import iut.nantes.hibernate.entities.manytomany.Product;
import iut.nantes.hibernate.entities.manytomany.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("myPersistenceUnit");

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            // Création des utilisateurs
            User user1 = new User("Alice");
            User user2 = new User("Bob");

            // Création des produits
            Product product1 = new Product("Ordinateur", 899.99, 10);
            Product product2 = new Product("Clavier", 49.99, 25);
            Product product3 = new Product("Souris", 19.99, 50);

            // Association ManyToMany
            product1.addUser(user1);
            product1.addUser(user2);

            product2.addUser(user1);

            product3.addUser(user2);

            // Persistance
            em.persist(user1);
            em.persist(user2);

            em.persist(product1);
            em.persist(product2);
            em.persist(product3);

            em.getTransaction().commit();

            // Affichage des associations
            System.out.println("Produits de Alice :");

            User alice = em.find(User.class, user1.getId());

            for (Product product : alice.getProducts()) {
                System.out.println(
                        product.getName() + " - " + product.getPrice() + " €"
                );
            }

            System.out.println("\nUtilisateurs du produit Ordinateur :");

            Product ordinateur = em.find(Product.class, product1.getId());

            for (User user : ordinateur.getUsers()) {
                System.out.println(user.getUsername());
            }

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}


package iut.nantes.hibernate.entities.manytomanywithparameter;

import iut.nantes.hibernate.entities.manytomanywithparameter.User;
import iut.nantes.hibernate.entities.manytomanywithparameter.Product;
import iut.nantes.hibernate.entities.manytomanywithparameter.UserProduct;

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

            // Création des produits
            Product p1 = new Product("Ordinateur", 899.99, 10);
            Product p2 = new Product("Souris", 25.50, 50);
            Product p3 = new Product("Clavier", 49.99, 30);

            // Persistance des produits
            em.persist(p1);
            em.persist(p2);
            em.persist(p3);

            // Création des utilisateurs
            User user1 = new User("Alice");
            User user2 = new User("Bob");

            // Association des produits avec les utilisateurs
            user1.addProduct(p1, 2);
            user1.addProduct(p2, 3);

            user2.addProduct(p2, 1);
            user2.addProduct(p3, 2);

            // Persistance des utilisateurs
            // CascadeType.ALL enregistre aussi les UserProduct
            em.persist(user1);
            em.persist(user2);

            em.getTransaction().commit();

            System.out.println("Données enregistrées avec succès !");

            // Récupération d'un utilisateur
            User user = em.find(User.class, user1.getId());

            if (user != null) {
                System.out.println("\nUtilisateur ID : " + user.getId());

                for (UserProduct up : user.getProducts()) {
                    System.out.println(
                            "Produit : " + up.getProduct().getName()
                                    + " | Quantité : " + up.getQuantity()
                    );
                }
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

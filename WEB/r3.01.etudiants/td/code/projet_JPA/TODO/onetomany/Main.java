
package iut.nantes.hibernate;

import iut.nantes.hibernate.entities.onetomany.Product;
import iut.nantes.hibernate.entities.onetomany.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("myPersistenceUnit");

        EntityManager em = emf.createEntityManager();

        try {
            // 1. Création d'un utilisateur
            User user = new User("amine");

            // 2. Création des produits
            Product p1 = new Product("PC", 1200.0, 5);
            Product p2 = new Product("Clavier", 50.0, 10);
            Product p3 = new Product("Souris", 25.0, 15);

            // 3. Association des produits à l'utilisateur
            user.addProduct(p1);
            user.addProduct(p2);
            user.addProduct(p3);

            // 4. Enregistrement en base de données
            em.getTransaction().begin();

            em.persist(user);

            em.getTransaction().commit();

            System.out.println("Utilisateur et produits enregistrés !");

            // 5. Récupération de l'utilisateur
            em.clear();

            User foundUser = em.find(User.class, user.getId());

            System.out.println("\nUtilisateur : " + foundUser.getUsername());

            // 6. Affichage des produits
            System.out.println("Liste des produits :");

            for (Product product : foundUser.getProducts()) {
                System.out.println(product);
            }

            // 7. Suppression d'un produit
            em.getTransaction().begin();

            Product productToRemove = foundUser.getProducts().get(0);
            foundUser.removeProduct(productToRemove);

            em.getTransaction().commit();

            System.out.println("\nProduit supprimé !");

            // 8. Vérification
            System.out.println("Produits restants :");

            for (Product product : foundUser.getProducts()) {
                System.out.println(product);
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

package iut.nantes.hibernate;
import iut.nantes.hibernate.entities.Product;
import jakarta.persistence.*;

import java.io.IOException;
import java.util.List;

public class MainEntityManager {
    public static void main(String[] args) throws IOException {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("myPersistenceUnit");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            Product product1 = new Product("Test1",10.0,2);
            Product product2 = new Product("Test2",220.0,10);

            em.persist(product1);
            em.persist(product2);

            List<Product> products ;

            TypedQuery<Product> requete = em.createQuery("from Product",Product.class);

            products = requete.getResultList();
            System.out.println(products);

            product1.setPrice(10000.7);
            em.persist(product1);
            products = requete.getResultList();
            System.out.println("*********Modif*************");
            System.out.println(products);

            em.remove(product1);
            products = requete.getResultList();
            System.out.println("**********Suppression produit 1**********");
            System.out.println();


            em.getTransaction().commit();

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

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

            // Ne pas fournir d'ID : Hibernate le génère automatiquement
            Product product1 = new Product("Test1",10.0,2);
            Product product2 = new Product("Test2",220.0,20);
            //Persistance
            em.persist(product1);
            em.persist(product2);

            List<Product> products;
            //Requête
            TypedQuery<Product> query = em.createQuery("from Product", Product.class);
            products= query.getResultList();
            System.out.println(products);

            //Modification
            product1.setPrice(1000.5);
            em.persist(product1);
            products= query.getResultList();
            System.out.println(products);

            //Suppression
            em.remove(product1);
            products= query.getResultList();
            System.out.println(products);

            //TODO supprimer tous les produits en utilisant un createStatement et les afficher
          



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

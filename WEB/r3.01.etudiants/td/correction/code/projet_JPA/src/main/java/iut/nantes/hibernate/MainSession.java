package iut.nantes.hibernate;
import iut.nantes.hibernate.entities.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.List;

public class MainSession {
    public static void main(String[] args) throws IOException {

//Avec des sessions hibernate.cfg.xml
        Configuration config = new Configuration();
        config.addAnnotatedClass(iut.nantes.hibernate.entities.Product.class);
        config.configure();




        // local SessionFactory bean created
        SessionFactory sessionFactory = config.buildSessionFactory();
        Session session = sessionFactory.openSession();

        try {
            session.beginTransaction();

            Product product1 = new Product("Test1",10.0,2);
            Product product2 = new Product("Test2",220.0,20);
            session.persist(product1);
            session.persist(product2);
            session.getTransaction().commit();
            List<Product> products;
            products= session.createQuery("SELECT p FROM Product p", Product.class).getResultList();
            System.out.println(products);

            
        } finally{
            session.close();
            sessionFactory.close();
        }
    }
}

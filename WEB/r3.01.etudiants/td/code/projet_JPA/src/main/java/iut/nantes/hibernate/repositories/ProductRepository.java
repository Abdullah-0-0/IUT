package iut.nantes.hibernate.repositories;

import iut.nantes.hibernate.entities.Product;
import jakarta.persistence.*;
import org.hibernate.mapping.Any;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductRepository implements CrudRepository<Product, Integer> {

    private final EntityManager entityManager;

    public ProductRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        //this.requete = entityManager.createQuery("from Product",Product.class);
    }


    @Override
    public <S extends Product> S save(S entity) {
        if (entity == null){
            throw new IllegalArgumentException("");
        }

        try {
            entityManager.getTransaction().begin();
            if (entity.getId()== null){
                entityManager.persist(entity);
                entityManager.getTransaction().commit();
                return  entity;
            }
            else {
                S produitExistant = entityManager.merge(entity);
                entityManager.getTransaction().commit();
                return  produitExistant;
            }

        } catch (RuntimeException e) {
            if (entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();

            }
            e.printStackTrace();
            throw e;
        }

    }

    @Override
    public <S extends Product> Iterable<S> saveAll(Iterable<S> entities) {
        List<S> produits = new ArrayList<>();
        if (entities == null){
            throw new IllegalArgumentException();
        }
        try{
            for ( S i : entities){
                produits.add(save(i));
            }
        } catch (Exception e) {
            throw e;
        }

        return produits;
    }

    @Override
    public Optional<Product> findById(Integer integer) {
        if (integer == null){
            throw new IllegalArgumentException();
        }
        try {
            Product n = entityManager.find(Product.class,integer);
            if ( n != null){
                return Optional.of(n);
            }
            return Optional.empty();
        } catch (Exception e) {
            throw e ;
        }

    }

    @Override
    public boolean existsById(Integer integer) {
        return null != entityManager.find(Product.class, integer);
    }

    @Override
    public Iterable<Product> findAll() {
        TypedQuery<Product> requeteSelect = entityManager.createQuery("SELECT p FROM Product p",Product.class);
        return requeteSelect.getResultList();
    }

    @Override
    public Iterable<Product> findAllById(Iterable<Integer> integers) {
        if ( integers == null){
            throw new IllegalArgumentException();
        }
        integers.forEach(element -> {
            if (element == null){
                throw  new IllegalArgumentException();
            }
        });
        TypedQuery<Product>  requete = entityManager.createQuery(
                "SELECT p FROM Product p WHERE p.id IN :ids",
                Product.class);
        requete.setParameter("ids",integers);
        return requete.getResultList();
    }

    @Override
    public long count() {
        TypedQuery<Long>  requete = entityManager.createQuery("SELECT COUNT(p) FROM Product p",Long.class);

        return requete.getSingleResult();
    }

    @Override
    public void deleteById(Integer integer) {
        if (integer == null){
            throw  new IllegalArgumentException();
        }
        try {
            entityManager.getTransaction().begin();
            if (entityManager.find(Product.class,integer)!=null){
                entityManager.remove(findById(integer).get());
            }
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            if (entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }

            throw e;
        }
    }

    @Override
    public void delete(Product entity) {
        if (entity == null){
            throw new IllegalArgumentException();
        }
        try {
            entityManager.getTransaction().begin();

            if (entity.getId() != null){
                if (entityManager.find(Product.class,entity.getId()) != null){
                    entityManager.remove(entity);
                }
            }

            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {
        if (integers == null){
            throw  new IllegalArgumentException();
        }

        try {
            entityManager.getTransaction().begin();
            integers.forEach(element -> {
                if (element == null){
                    throw new IllegalArgumentException();
                }
                else {
                    delete(element);
                }
            });
        }
    }

    @Override
    public void deleteAll(Iterable<? extends Product> entities) {

    }

    @Override
    public void deleteAll() {

    }
}


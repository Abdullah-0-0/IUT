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
            if (entity.getId() !=null){
                S produitExistant =  entityManager.merge(entity);
                entityManager.getTransaction().commit(); 
                return produitExistant ;
            }else {
                entityManager.persist(entity);
                entityManager.getTransaction().commit();
                return entity;
            }


        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }

            e.printStackTrace();
        }
        return null;
    }

    @Override
    public <S extends Product> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Product> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return null != entityManager.find(Product.class, integer);
    }

    @Override
    public Iterable<Product> findAll() {
        return null;
    }

    @Override
    public Iterable<Product> findAllById(Iterable<Integer> integers) {
        return null;
    }

    @Override
    public long count() {

        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(Product entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends Product> entities) {

    }

    @Override
    public void deleteAll() {

    }
}


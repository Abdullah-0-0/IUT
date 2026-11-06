import iut.nantes.hibernate.entities.Product;
import iut.nantes.hibernate.repositories.ProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductRepositoryTest {

    private EntityManager entityManager;
    private EntityTransaction transaction;
    private ProductRepository repository;

    @BeforeEach
    void setUp() {
        entityManager = mock(EntityManager.class);
        transaction = mock(EntityTransaction.class);

        when(entityManager.getTransaction()).thenReturn(transaction);

        when(transaction.isActive()).thenReturn(false);

        repository = new ProductRepository(entityManager);
    }

    @Test
    void saveShouldPersistNewProduct() {
        Product product = mock(Product.class);
        when(product.getId()).thenReturn(null);

        Product result = repository.save(product);

        assertSame(product, result);

        verify(transaction).begin();
        verify(entityManager).persist(product);
        verify(transaction).commit();
    }

    @Test
    void saveShouldMergeExistingProduct() {
        Product product = mock(Product.class);
        Product mergedProduct = mock(Product.class);

        when(product.getId()).thenReturn(1);
        when(entityManager.merge(product)).thenReturn(mergedProduct);

        Product result = repository.save(product);

        assertSame(mergedProduct, result);

        verify(transaction).begin();
        verify(entityManager).merge(product);
        verify(transaction).commit();
    }

    @Test
    void saveShouldRejectNull() {
        assertThrows(
                IllegalArgumentException.class,
                () -> repository.save(null)
        );
    }

    @Test
    void saveShouldRollbackWhenExceptionOccurs() {
        Product product = mock(Product.class);
        when(product.getId()).thenReturn(null);
        when(transaction.isActive()).thenReturn(true);

        doThrow(new RuntimeException())
                .when(entityManager).persist(product);

        assertThrows(
                RuntimeException.class,
                () -> repository.save(product)
        );

        verify(transaction).rollback();
    }

    @Test
    void saveAllShouldSaveProducts() {
        Product p1 = mock(Product.class);
        Product p2 = mock(Product.class);

        when(p1.getId()).thenReturn(null);
        when(p2.getId()).thenReturn(null);

        List<Product> result = (List<Product>)
                repository.saveAll(List.of(p1, p2));

        assertEquals(2, result.size());
        verify(entityManager).persist(p1);
        verify(entityManager).persist(p2);
    }

    @Test
    void saveAllShouldRejectNullIterable() {
        assertThrows(
                IllegalArgumentException.class,
                () -> repository.saveAll(null)
        );
    }

    @Test
    void saveAllShouldRejectNullProduct() {
        List<Product> products = java.util.Arrays.asList(
                mock(Product.class), null
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> repository.saveAll(products)
        );
    }

    @Test
    void findByIdShouldReturnProduct() {
        Product product = mock(Product.class);

        when(entityManager.find(Product.class, 1))
                .thenReturn(product);

        Optional<Product> result = repository.findById(1);

        assertTrue(result.isPresent());
        assertSame(product, result.get());
    }

    @Test
    void findByIdShouldReturnEmptyWhenNotFound() {
        when(entityManager.find(Product.class, 1))
                .thenReturn(null);

        assertTrue(repository.findById(1).isEmpty());
    }

    @Test
    void findByIdShouldRejectNull() {
        assertThrows(
                IllegalArgumentException.class,
                () -> repository.findById(null)
        );
    }

    @Test
    void existsByIdShouldReturnTrue() {
        when(entityManager.find(Product.class, 1))
                .thenReturn(mock(Product.class));

        assertTrue(repository.existsById(1));
    }

    @Test
    void existsByIdShouldReturnFalse() {
        when(entityManager.find(Product.class, 1))
                .thenReturn(null);

        assertFalse(repository.existsById(1));
    }

    @Test
    void findAllShouldReturnProducts() {
        TypedQuery<Product> query = mock(TypedQuery.class);

        List<Product> products = List.of(
                mock(Product.class),
                mock(Product.class)
        );

        when(entityManager.createQuery(
                "SELECT p FROM Product p",
                Product.class
        )).thenReturn(query);

        when(query.getResultList()).thenReturn(products);

        assertEquals(products, repository.findAll());
    }

    @Test
    void findAllByIdShouldReturnProducts() {
        TypedQuery<Product> query = mock(TypedQuery.class);

        List<Product> products = List.of(mock(Product.class));

        when(entityManager.createQuery(
                "SELECT p FROM Product p WHERE p.id IN :ids",
                Product.class
        )).thenReturn(query);

        when(query.setParameter("ids", List.of(1, 2)))
                .thenReturn(query);

        when(query.getResultList()).thenReturn(products);

        assertEquals(
                products,
                repository.findAllById(List.of(1, 2))
        );
    }

    @Test
    void findAllByIdShouldReturnEmptyForEmptyIds() {
        assertTrue(
                ((List<Product>) repository.findAllById(List.of()))
                        .isEmpty()
        );

        verifyNoInteractions(entityManager);
    }

    @Test
    void findAllByIdShouldRejectNull() {
        assertThrows(
                IllegalArgumentException.class,
                () -> repository.findAllById(null)
        );
    }

    @Test
    void findAllByIdShouldRejectNullId() {
        assertThrows(
                IllegalArgumentException.class,
                () -> repository.findAllById(
                        java.util.Arrays.asList(1, null)
                )
        );
    }

    @Test
    void countShouldReturnNumberOfProducts() {
        TypedQuery<Long> query = mock(TypedQuery.class);

        when(entityManager.createQuery(
                "SELECT COUNT(p) FROM Product p",
                Long.class
        )).thenReturn(query);

        when(query.getSingleResult()).thenReturn(5L);

        assertEquals(5L, repository.count());
    }

    @Test
    void deleteByIdShouldRemoveProduct() {
        Product product = mock(Product.class);

        when(entityManager.find(Product.class, 1))
                .thenReturn(product);

        repository.deleteById(1);

        verify(transaction).begin();
        verify(entityManager).remove(product);
        verify(transaction).commit();
    }

    @Test
    void deleteByIdShouldDoNothingWhenProductNotFound() {
        when(entityManager.find(Product.class, 1))
                .thenReturn(null);

        repository.deleteById(1);

        verify(entityManager, never()).remove(any());
        verify(transaction).commit();
    }

    @Test
    void deleteByIdShouldRejectNull() {
        assertThrows(
                IllegalArgumentException.class,
                () -> repository.deleteById(null)
        );
    }

    @Test
    void deleteByIdShouldRollbackWhenExceptionOccurs() {
        Product product = mock(Product.class);

        when(entityManager.find(Product.class, 1))
                .thenReturn(product);

        when(transaction.isActive()).thenReturn(true);

        doThrow(new RuntimeException())
                .when(entityManager).remove(product);

        assertThrows(
                RuntimeException.class,
                () -> repository.deleteById(1)
        );

        verify(transaction).rollback();
    }

    @Test
    void deleteShouldRejectNull() {
        assertThrows(
                IllegalArgumentException.class,
                () -> repository.delete(null)
        );
    }

    @Test
    void deleteShouldDeleteProductById() {
        Product product = mock(Product.class);
        when(product.getId()).thenReturn(1);

        when(entityManager.find(Product.class, 1))
                .thenReturn(product);

        repository.delete(product);

        verify(entityManager).remove(product);
    }

    @Test
    void deleteAllByIdShouldDeleteAllProducts() {
        Product p1 = mock(Product.class);
        Product p2 = mock(Product.class);

        when(entityManager.find(Product.class, 1)).thenReturn(p1);
        when(entityManager.find(Product.class, 2)).thenReturn(p2);

        repository.deleteAllById(List.of(1, 2));

        verify(entityManager).remove(p1);
        verify(entityManager).remove(p2);
    }

    @Test
    void deleteAllByIdShouldRejectNullIterable() {
        assertThrows(
                IllegalArgumentException.class,
                () -> repository.deleteAllById(null)
        );
    }

    @Test
    void deleteAllByIdShouldRejectNullId() {
        assertThrows(
                IllegalArgumentException.class,
                () -> repository.deleteAllById(
                        java.util.Arrays.asList(1, null)
                )
        );
    }

    @Test
    void deleteAllShouldRejectNullIterable() {
        assertThrows(
                IllegalArgumentException.class,
                () -> repository.deleteAll((Iterable<Product>) null)
        );
    }

    @Test
    void deleteAllShouldRejectNullProduct() {
        List<Product> products = java.util.Arrays.asList(
                mock(Product.class), null
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> repository.deleteAll(products)
        );
    }

    @Test
    void deleteAllShouldDeleteProducts() {
        Product p1 = mock(Product.class);
        Product p2 = mock(Product.class);

        when(p1.getId()).thenReturn(1);
        when(p2.getId()).thenReturn(2);

        when(entityManager.find(Product.class, 1)).thenReturn(p1);
        when(entityManager.find(Product.class, 2)).thenReturn(p2);

        repository.deleteAll(List.of(p1, p2));

        verify(entityManager).remove(p1);
        verify(entityManager).remove(p2);
    }
}

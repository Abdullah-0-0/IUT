package dao.old;

import static org.junit.jupiter.api.Assertions.*;
import dao.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductMemoryDaoTest {

    private ProductMemoryDao dao;
    private Product product1;
    private Product product2;

    @BeforeEach
    void setUp() {
        dao = new ProductMemoryDao();

        product1 = new Product(0, "Clavier", 49.99, 10);
        product2 = new Product(1, "Souris", 29.99, 20);

        dao.save(product1);
        dao.save(product2);
    }

    @Test
    void shouldGetProductById() {
        Product result = dao.get(0);

        assertNotNull(result);
        assertEquals(product1, result);

    }

    @Test
    void canNotGetProductById() {
        assertThrows(IllegalArgumentException.class, () -> dao.get(3));

    }


    @Test
    void shouldGetAllProducts() {
        List<Product> products = dao.getAll();

        assertEquals(2, products.size());
        assertEquals(product1, products.get(0));
        assertEquals(product2, products.get(1));
    }

    @Test
    void shouldSaveProduct() {
        Product product3 = new Product(2, "Écran", 199.99, 5);

        dao.save(product3);

        List<Product> products = dao.getAll();

        assertEquals(3, products.size());
        assertEquals(product3, products.get(2));
    }

    @Test
    void canNotSaveNullProduct() {
        assertThrows(IllegalArgumentException.class, () -> dao.save(null));
    }

    @Test
    void shouldNotRejectDuplicateProductId() {
        Product duplicate = new Product(0, "Nouveau clavier", 59.99, 5);

        assertDoesNotThrow(

                () -> dao.save(duplicate)
        );
    }

    @Test
    void shouldUpdateDuplicateProductId() {
        Product duplicate = new Product(0, "Nouveau clavier", 59.99, 5);
        dao.save(duplicate);
        assertEquals(dao.get(0), duplicate);

    }
    @Test
    void shouldUpdateProduct() {
        String[] params = {
                "0",
                "Clavier mécanique",
                "89.99",
                "15"
        };

        dao.update(product1, params);

        Product updated = dao.get(0);

        assertEquals(0, updated.getId());
        assertEquals("Clavier mécanique", updated.getName());
        assertEquals(89.99, updated.getPrice());
        assertEquals(15, updated.getQuantity());
    }

    @Test
    void updateAvecIdInvalide1() {
        String[] params = {"abc", "Produit", "10.5", "2"};

        assertThrows(IllegalArgumentException.class, () -> {
            dao.update(null, params);
        });
    }
    @Test
    void updateAvecIdInvalide2() {
        String[] params = {"abc", "Nouveau produit", "20.0", "10"};

        assertThrows(IllegalArgumentException.class, () -> {
            dao.update(dao.get(1), params);
        });
    }

    @Test
    void updateAvecPrixInvalide() {
        String[] params = {"1", "Nouveau produit", "abc", "10"};

        assertThrows(IllegalArgumentException.class, () -> {
            dao.update(dao.get(1), params);
        });
    }

    @Test
    void updateAvecQuantiteInvalide() {
        String[] params = {"1", "Nouveau produit", "20.0", "abc"};

        assertThrows(IllegalArgumentException.class, () -> {
            dao.update(dao.get(1), params);
        });
    }

    @Test
    void updateAvecPasAssezDeParametres() {
        String[] params = {"1", "Nouveau produit"};

        assertThrows(IllegalArgumentException.class, () -> {
            dao.update(dao.get(1), params);
        });
    }

    @Test
    void updateAvecParamsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            dao.update(dao.get(1), null);
        });
    }

    @Test
    void shouldDeleteProduct() {
        dao.delete(product1);

        List<Product> products = dao.getAll();

        assertEquals(1, products.size());
        assertFalse(products.contains(product1));
        assertTrue(products.contains(product2));
    }

    @Test
    void shouldReturnIndependentListFromGetAll() {
        List<Product> products = dao.getAll();
        List<Product> independentList = dao.getAll();
        independentList.set(0, product2);
        assertEquals(products, dao.getAll());
    }

    @Test
    void shouldThrowExceptionWhenGettingUnknownId() {
        assertThrows(
                IllegalArgumentException.class,
                () -> dao.get(99)
        );
    }

    @Test
    void shouldUpdateWithInvalidPrice() {
        String[] params = {
                "0",
                "Clavier",
                "prix-invalide",
                "10"
        };

        assertThrows(
                IllegalArgumentException.class,
                () -> dao.update(product1, params)
        );
    }

    @Test
    void shouldUpdateWithInvalidQuantity() {
        String[] params = {
                "0",
                "Clavier",
                "49.99",
                "quantite-invalide"
        };

        assertThrows(
                IllegalArgumentException.class,
                () -> dao.update(product1, params)
        );
    }

    @Test
    void deleteProduitNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            dao.delete(null);
        });
    }

    @Test
    void deleteProduitInexistant() {
        Product product = new Product(999, "Produit inexistant", 10.0, 5);

        assertThrows(IllegalArgumentException.class, () -> {
            dao.delete(product);
        });
    }
}



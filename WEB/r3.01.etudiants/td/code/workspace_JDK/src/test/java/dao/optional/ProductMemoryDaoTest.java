package dao.optional;

import dao.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductMemoryDaoTest {
    private ProductMemoryDao dao;
    private Product keyboard;
    private Product mouse;

    @BeforeEach
    void setUp() {
        dao = new ProductMemoryDao();
        keyboard = new Product(0, "Clavier", 49.99, 10);
        mouse = new Product(1, "Souris", 29.99, 20);
        dao.save(keyboard);
        dao.save(mouse);
    }

    @Test
    void getReturnsOptionalForExistingAndMissingProducts() {
        assertEquals(keyboard, dao.get(0).orElseThrow());
        assertTrue(dao.get(99).isEmpty());
    }

    @Test
    void getAllReturnsCopyInInsertionOrder() {
        List<Product> products = dao.getAll();
        products.clear();

        assertEquals(List.of(keyboard, mouse), dao.getAll());
    }

    @Test
    void saveReplacesProductWithSameId() {
        Product replacement = new Product(0, "Clavier mécanique", 89.99, 5);

        dao.save(replacement);

        assertEquals(replacement, dao.get(0).orElseThrow());
        assertEquals(2, dao.getAll().size());
    }

    @Test
    void updateReplacesProductAndRejectsInvalidParameters() {
        dao.update(keyboard, new String[]{"0", "Clavier mécanique", "89.99", "15"});

        assertEquals(new Product(0, "Clavier mécanique", 89.99, 15), dao.get(0).orElseThrow());
        assertThrows(IllegalArgumentException.class,
                () -> dao.update(keyboard, new String[]{"0", "Clavier", "bad", "10"}));
    }

    @Test
    void deleteRemovesExistingProductAndRejectsMissingProduct() {
        dao.delete(keyboard);

        assertTrue(dao.get(0).isEmpty());
        assertEquals(List.of(mouse), dao.getAll());
        assertThrows(IllegalArgumentException.class, () -> dao.delete(keyboard));
    }
}

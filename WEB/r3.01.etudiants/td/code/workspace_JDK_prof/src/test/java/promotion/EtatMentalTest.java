
        package promotion;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class EtatMentalTest {

    @Test
    void testHeureux() {
        assertNotNull(EtatMental.Heureux);
        assertEquals("Heureux", EtatMental.Heureux.toString());
        assertEquals("Heureux", EtatMental.Heureux.getValeur());
    }

    @Test
    void testJoyeux() {
        assertNotNull(EtatMental.Joyeux);
        assertEquals("Joyeux", EtatMental.Joyeux.toString());
        assertEquals("Joyeux", EtatMental.Joyeux.getValeur());
    }

    @Test
    void testBien() {
        assertNotNull(EtatMental.Bien);
        assertEquals("Biens", EtatMental.Bien.toString());
        assertEquals("Biens", EtatMental.Bien.getValeur());
    }

    @Test
    void testNombreDeValeurs() {
        assertEquals(3, EtatMental.values().length);
    }
}

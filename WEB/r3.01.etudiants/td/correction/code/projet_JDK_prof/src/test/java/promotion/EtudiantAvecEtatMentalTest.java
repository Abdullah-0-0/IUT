package promotion;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;



class EtudiantAvecEtatMentalTest {

    @Test
    void testEtatMentalConstructeur() {
        EtudiantAvecEtatMental etudiant =
                new EtudiantAvecEtatMental("E01", 15.0f, EtatMental.Heureux);

        assertEquals(EtatMental.Heureux, etudiant.getEtatMental());
    }

    @Test
    void testGetEtatMental() {
        EtudiantAvecEtatMental etudiant =
                new EtudiantAvecEtatMental("E01", 15.0f, EtatMental.Joyeux);

        assertEquals(EtatMental.Joyeux, etudiant.getEtatMental());
    }

    @Test
    void testSetEtatMental() {
        EtudiantAvecEtatMental etudiant =
                new EtudiantAvecEtatMental("E01", 15.0f, EtatMental.Bien);

        etudiant.setEtatMental(EtatMental.Joyeux);

        assertEquals(EtatMental.Joyeux, etudiant.getEtatMental());
    }
}

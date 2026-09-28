package promotion;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EtudiantCompteTest {

    @Test
    void testConstructeurEtGetNo() {
        Etudiant etudiant = new Etudiant("E001", 15.5f);
        assertEquals("E001", etudiant.getNo());
    }

    @Test
    void testGetMoyenne() {
        Etudiant etudiant = new Etudiant("E001", 15.5f);
        assertEquals(15.5f, etudiant.getMoyenne());
    }

    @Test
    void testConstructeur() {
        Etudiant etudiant = new Etudiant("E123", 12.75f);
        assertEquals("E123", etudiant.getNo());
        assertEquals(12.75f, etudiant.getMoyenne());
    }

    @Test
    void testConstructeurSansMoyenne() {
        Etudiant etudiant = new Etudiant("E123");
        assertEquals("E123", etudiant.getNo());
        assertEquals(10.0f, etudiant.getMoyenne());
    }
    @Test
    void testToString() {
        Etudiant etudiant = new Etudiant("E001", 15.5f);
        assertEquals("Etudiant{no='E001', moyenne=15.5}", etudiant.toString());
    }

    @Test
    void testMoyenneZero() {
        Etudiant etudiant = new Etudiant("E001", 0.0f);
        assertEquals(0.0f, etudiant.getMoyenne());
    }

    @Test
    void testMoyenneVingt() {
        Etudiant etudiant = new Etudiant("E001", 20.0f);
        assertEquals(20.0f, etudiant.getMoyenne());
    }
}

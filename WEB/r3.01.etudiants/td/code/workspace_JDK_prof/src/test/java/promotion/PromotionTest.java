package promotion;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PromotionTest {

    @Test
    void testAjouterEtudiant() {
        Promotion promotion = new Promotion();

        Etudiant etudiant = new Etudiant("E001", 15.5f);

        promotion.ajouterEtudiant(etudiant);

        assertEquals(1, promotion.getNombreEtudiants());
        assertTrue(promotion.contientEtudiant("E001"));
    }

    @Test
    void testAjouterPlusieursEtudiants() {
        Promotion promotion = new Promotion();

        promotion.ajouterEtudiant(new Etudiant("E001", 15.5f));
        promotion.ajouterEtudiant(new Etudiant("E002", 12.0f));
        promotion.ajouterEtudiant(new Etudiant("E003", 17.0f));

        assertEquals(3, promotion.getNombreEtudiants());

        assertTrue(promotion.contientEtudiant("E001"));
        assertTrue(promotion.contientEtudiant("E002"));
        assertTrue(promotion.contientEtudiant("E003"));
    }

    @Test
    void testNumeroEtudiantUnique() {
        Promotion promotion = new Promotion();

        promotion.ajouterEtudiant(
                new Etudiant("E001", 15.5f)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> promotion.ajouterEtudiant(
                        new Etudiant("E001", 18.0f)
                )
        );

        assertEquals(1, promotion.getNombreEtudiants());
    }

    @Test
    void testContientEtudiant() {
        Promotion promotion = new Promotion();

        promotion.ajouterEtudiant(
                new Etudiant("E001", 15.5f)
        );

        assertTrue(promotion.contientEtudiant("E001"));
        assertFalse(promotion.contientEtudiant("E002"));
    }

    @Test
    void testSupprimerEtudiantExistant() {
        Promotion promotion = new Promotion();

        promotion.ajouterEtudiant(
                new Etudiant("E001", 15.5f)
        );

        boolean resultat = promotion.supprimerEtudiant("E001");

        assertTrue(resultat);
        assertFalse(promotion.contientEtudiant("E001"));
        assertEquals(0, promotion.getNombreEtudiants());
    }

    @Test
    void testSupprimerEtudiantInexistant() {
        Promotion promotion = new Promotion();

        promotion.ajouterEtudiant(
                new Etudiant("E001", 15.5f)
        );

        boolean resultat = promotion.supprimerEtudiant("E999");

        assertFalse(resultat);
        assertEquals(1, promotion.getNombreEtudiants());
    }

    @Test
    void testAjouterEtudiantNull() {
        Promotion promotion = new Promotion();
        assertThrows(IllegalArgumentException.class, () -> promotion.ajouterEtudiant(null));
        assertEquals(0, promotion.getNombreEtudiants());
    }

    @Test
    void testGetEtudiants() {
        Promotion promotion = new Promotion();
        Etudiant e1 = new Etudiant("E001", 15.5f);
        Etudiant e2 = new Etudiant("E002", 12.0f);
        promotion.ajouterEtudiant(e1);
        promotion.ajouterEtudiant(e2);
        assertEquals(2, promotion.getEtudiants().size());
        assertTrue(promotion.getEtudiants().contains(e1));
        assertTrue(promotion.getEtudiants().contains(e2));
    }

    @Test
    void moyenne_doitCalculerLaMoyenneDesEtudiants() {
        Promotion promotion = new Promotion();
        promotion.ajouterEtudiant(new Etudiant("E001", 10.0f));
        promotion.ajouterEtudiant(new Etudiant("E002", 14.0f));
        promotion.ajouterEtudiant(new Etudiant("E003", 16.0f));
        double moyenne = promotion.moyenne();
        assertEquals(13.333, moyenne, 0.001);
    }


@Test
void moyenne_doitCalculerLSansEtudiant() {
    Promotion promotion = new Promotion();
    double moyenne = promotion.moyenne();
    assertEquals(0.0, moyenne, 0.001); }
    }

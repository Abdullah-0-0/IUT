package promotion;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EtudiantCompteTest {

    @Test
    void nombreEtudiantCrees_doitAugmenterApresCreation() {
        // Arrange
        int nombreAvant = Etudiant.nombreEtudiantCrees();

        // Act
        new Etudiant("E001", 15.5f);

        // Assert
        assertEquals(nombreAvant + 1, Etudiant.nombreEtudiantCrees());
    }

    @Test
    void nombreEtudiantCrees_doitAugmenterAvecLeConstructeurSimple() {
        // Arrange
        int nombreAvant = Etudiant.nombreEtudiantCrees();

        // Act
        new Etudiant("E002");

        // Assert
        assertEquals(nombreAvant + 1, Etudiant.nombreEtudiantCrees());
    }

    @Test
    void nombreEtudiantCrees_doitAugmenterDeDeuxPourDeuxEtudiants() {
        // Arrange
        int nombreAvant = Etudiant.nombreEtudiantCrees();

        // Act
        new Etudiant("E003", 12.0f);
        new Etudiant("E004", 14.0f);

        // Assert
        assertEquals(nombreAvant + 2, Etudiant.nombreEtudiantCrees());
    }
}

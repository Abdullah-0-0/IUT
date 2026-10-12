package promotion;

import java.util.ArrayList;
import java.util.List;

public class Promotion {

    private final List<Etudiant> etudiants;

    public Promotion() {
        this.etudiants = new ArrayList<>();
    }

    public void ajouterEtudiant(Etudiant etudiant) {
        if (etudiant == null) {
            throw new IllegalArgumentException("L'étudiant ne peut pas être null");
        }

        if (contientEtudiant(etudiant.getNo())) {
            throw new IllegalArgumentException(
                    "Un étudiant avec le numéro " + etudiant.getNo() + " existe déjà"
            );
        }

        etudiants.add(etudiant);
    }

    public boolean contientEtudiant(String no) {
        return etudiants.stream()
                .anyMatch(etudiant -> etudiant.getNo().equals(no));
    }

    public List<Etudiant> getEtudiants() {
        return List.copyOf(etudiants);
    }

    public int getNombreEtudiants() {
        return etudiants.size();
    }

    public boolean supprimerEtudiant(String no) {
        return etudiants.removeIf(etudiant -> etudiant.getNo().equals(no));
    }

    public double moyenne() {
        return etudiants.stream().mapToDouble(Etudiant::getMoyenne).average().orElse(0.0);
    }
}

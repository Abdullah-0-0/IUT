import java.util.ArrayList;
import java.util.List;

public class Promotion {
    private List<Etudiant> etudiants = new ArrayList<>();

    public  void ajouterEtudiant(Etudiant etudiant){
        if (etudiants.contains(etudiant) ){
            throw new IllegalArgumentException("etudiant déjà present sur la promo");
        }
        etudiants.add(etudiant);
    }

    public Boolean supprimerEtudiant(String nom){
        for (int i=0; i< etudiants.size();i++ ){
            if (etudiants.get(i).getNo() == nom){
                etudiants.remove(i);
                return true;
            }
        }
        return false;
    }

    public List<Etudiant> getEtudiant(){
        return etudiants;
    }

    public int getNombreEtudiant(){
        return etudiants.size();
    }

    public Boolean contientEtudiant(String nom){
        for (int i =0; i<etudiants.size();i++){
            if (etudiants.get(i).getNo() == nom){
                return  true;
            }
        }
        return false;
    }

    public Float moyenne(){
        if (etudiants.size() == 0 ){
            return  null;
        }
        Float somme = 0.0f;
        for (int i=0; i<etudiants.size();i++){
            somme+=etudiants.get(i).getMoyenne();
        }
        return somme/getNombreEtudiant();
    }
}

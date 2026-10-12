package promotion;

public class EtudiantAvecEtatMental extends Etudiant {
    private  EtatMental etatMental;

    public  EtudiantAvecEtatMental(String no , Float moyenne , EtatMental etatMental ){
        super(no,moyenne);
        this.etatMental = etatMental;
    }

    public EtatMental getEtatMental(){
        return etatMental;
    }

    @Override
    public String toString() {
        return "Etudiant n°";
    }

    public void setEtatMental(EtatMental etat ){
        etatMental = etat;
    }

}

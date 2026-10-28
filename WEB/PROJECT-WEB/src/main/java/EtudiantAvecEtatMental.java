public class EtudiantAvecEtatMental extends Etudiant{
    EtatMental etat ;

    public EtudiantAvecEtatMental(String no , Float moyenne , EtatMental etat){
        super(no, moyenne);
        this.etat = etat;
    }

    public EtatMental getEtatMental() {
        return etat;
    }

    public void  setEtatMental(EtatMental nouveauEtat){
        etat = nouveauEtat;
    }

    @Override
    public String toString() {
        return "l'etudiant nommee " +
                this.getNo() +
                " avec une moyenne de "+
                this.getMoyenne()+
                " se sent "+
                getEtatMental().getValue();
    }
}

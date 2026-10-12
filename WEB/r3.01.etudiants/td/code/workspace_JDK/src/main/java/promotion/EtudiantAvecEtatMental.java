package promotion;

public class EtudiantAvecEtatMental extends Etudiant{
    private EtatMental etatMental;

    public EtudiantAvecEtatMental(String no, Float moyenne, EtatMental etatMental) {
        super(no, moyenne);
        this.etatMental = etatMental;
    }

    public EtatMental getEtatMental() {
        return etatMental;
    }

    public void setEtatMental(EtatMental etatMental) {
        this.etatMental = etatMental;
    }

    @Override
    public String toString() {
        return super.toString()+"EtudiantAvecEtatMental{" +
                "etatMental=" + etatMental +
                '}';
    }
}

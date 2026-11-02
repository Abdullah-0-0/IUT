package promotion;

public enum  EtatMental {
    Heureux("Heureux"),
    Joyeux("Joyeux"),
    Bien("Biens");

    private final String valeur;

    EtatMental(String valeur) {
        this.valeur = valeur;
    }

    public String getValeur() {
        return valeur;
    }

    @Override
    public String toString() {
        return valeur;
    }
}

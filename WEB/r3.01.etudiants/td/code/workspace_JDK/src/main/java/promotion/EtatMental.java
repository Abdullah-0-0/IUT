
package promotion;

public enum EtatMental {

    Bien("Bien"),
    Joyeux("Joyeux"),
    Heureux("Heureux");

    private final String valeur;

    // Constructeur
    EtatMental(String valeur) {
        this.valeur = valeur;
    }

    // Getter
    public String getValeur() {
        return valeur;
    }

    @Override
    public String toString() {
        return valeur;
    }
}

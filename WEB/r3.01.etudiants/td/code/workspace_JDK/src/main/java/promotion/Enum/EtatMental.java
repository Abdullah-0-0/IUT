package promotion.Enum;

public enum EtatMental {
    BIEN("il va bien"),
    JOYEUX("il est Joyeux"),
    HEUREUX("il est heureux");
    private  String valeur;
    EtatMental( String valeur){
        this.valeur = valeur;
    }

    @Override
    public String toString() {
        return "";
    }
    String getValeur(){
        return  valeur;
    }
    EtatMental valueOf(String n){
        return  EtatMental.BIEN;
    }
}

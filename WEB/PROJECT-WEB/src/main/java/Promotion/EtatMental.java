package Promotion;

public enum EtatMental {
    Bien("bien"),
    Joyeux("joyeux"),
    Heureux("heureux");
    private String value ;

    private EtatMental(String value){
        this.value = value;
    }

    public String getValeur(){
        return value;
    }


    @Override
    public String toString() {
        return "elle a pour valeur: " + value;
    }
}

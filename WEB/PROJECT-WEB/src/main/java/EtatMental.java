public enum EtatMental {
    BIEN("bien"),
    JOYEUX("joyeux"),
    HEUREUX("heureux");
    private String value ;

    private EtatMental(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }


    @Override
    public String toString() {
        return "elle a pour valeur: " + value;
    }
}

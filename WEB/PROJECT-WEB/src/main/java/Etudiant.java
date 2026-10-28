public class Etudiant {
    private String no ;
    private Float moyenne ;
    private static Integer nb = 0;
    public Etudiant(String no , Float moyenne){
        this.no = no;
        this.moyenne = moyenne;
        nombreEtudiantCrees();
    }
    public Etudiant(String no){
        this.moyenne = 10.0f;
        this.no = no;
        nombreEtudiantCrees();
    }

    public String getNo(){
        return  no;
    }
    public Float getMoyenne(){
        return moyenne;
    }
    private static void nombreEtudiantCrees(){
        nb++;
    }
    public Integer donneNonbreEtudiant(){
        return nb;
    }

    @Override
    public String toString() {
        return "l'etudiant nommée :" + no +" à pour moyenne " + moyenne;
    }


}

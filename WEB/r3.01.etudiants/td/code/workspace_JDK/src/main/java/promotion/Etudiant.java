package promotion;

public class Etudiant {
    private String nom ;
    private Float moyenne;

    Etudiant(String nom , Float moyenne){
        this.nom = nom;
        this.moyenne = moyenne;
    }

    Etudiant(String nom){
        this.nom = nom;
        this.moyenne = 10.0f;
    }
     String getNo(){
        return nom ;
    }
    Float getMoyenne(){
        return moyenne;
    }

    @Override
    public String toString() {
        return  nom + "a une moyenne de " + moyenne;
    }

    static public void main(String[] args){
        Etudiant alber =  new Etudiant("alber");
        Etudiant jean = new Etudiant("jean",18.0f);
        System.out.println(alber.toString());
        System.out.println(jean.toString());
    }
    public enum EtatMental{
        BIEN,
        JOYEUX,
        HEUREUX;
        String valeur ;
        EtatMental(String Valeur){
            this.valeur = Valeur;
        }
    }
}

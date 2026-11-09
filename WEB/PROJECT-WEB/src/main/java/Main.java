import Promotion.EtatMental;
import Promotion.Etudiant;
import Promotion.EtudiantAvecEtatMental;

public class Main {

    public static void main(String[] args){
        EtatMental etat = EtatMental.Bien;
        Etudiant adrien = new Etudiant("adrien");
        Etudiant flo  = new Etudiant("flo",16.8f);
        EtudiantAvecEtatMental moi = new EtudiantAvecEtatMental("abdullah",0.7f,etat);
        System.out.println(adrien);
        System.out.println(flo);
        System.out.println(etat.getValeur());
        System.out.println("*******************************");
        System.out.println(moi);
        System.out.println(adrien.donneNonbreEtudiant());
    }


}

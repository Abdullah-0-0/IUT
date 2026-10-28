public class Main {

    public static void main(String[] args){
        EtatMental etat = EtatMental.BIEN;
        Etudiant adrien = new Etudiant("adrien");
        Etudiant flo  = new Etudiant("flo",16.8f);
        EtudiantAvecEtatMental moi = new EtudiantAvecEtatMental("abdullah",0.7f,etat);
        System.out.println(adrien);
        System.out.println(flo);
        System.out.println(etat.getValue());
        System.out.println("*******************************");
        System.out.println(moi);
        System.out.println(adrien.donneNonbreEtudiant());
    }


}

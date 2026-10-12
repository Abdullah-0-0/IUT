
package promotion;

public class Etudiant {
    private Float moyenne;
    private String no ;

    private static int nb = 0;

    public Etudiant(String no , Float moyenne){
        this.no = no;
        this.moyenne = moyenne;
        nb++;
    }
    public Etudiant(String no ){
        this.no = no;
        this.moyenne = 10.0f;
        nb++;
    }

    public String getNo(){
        return no;
    }
    public Float getMoyenne(){
        return moyenne;
    }
    @Override
    public  String toString(){
        return "Etudiant{" +
                "no='" + no + '\'' +
                ", moyenne=" + moyenne +
                '}';
    }
    public static Integer nombreEtudiantCrees() {
        return nb;
    }
}

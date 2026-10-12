package promotion;

public class Etudiant {
    private String no;
    private Float moyenne;
    private static Integer nb =0;

    public Etudiant(String no, Float moyenne) {
        this.no = no;
        this.moyenne = moyenne;
        nb++;
    }

    public static Integer nombreEtudiantCrees() {
        return nb;
    }


    public Etudiant(String no) {
        this(no,10.0f);
    }
    public String getNo() {
        return no;
    }

    public Float getMoyenne() {
        return moyenne;
    }

    @Override
    public String toString() {
        return "Etudiant{" +
                "no='" + no + '\'' +
                ", moyenne=" + moyenne +
                '}';
    }
}

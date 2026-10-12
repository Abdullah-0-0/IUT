package tutoriel.question00;

public class IdentifyMyParts {
    public static int x = 7; //JFB class variables
    public int y = 3; //JFB instance variables

public static  void main(String[] args) {
    IdentifyMyParts a = new IdentifyMyParts();
    IdentifyMyParts b = new IdentifyMyParts();
    a.y = 5;
    b.y = 6;
    a.x = 1;
    b.x = 2;
    System.out.println("a.y = " + a.y); //JFB a.y = 5
    System.out.println("b.y = " + b.y); //JFB b.y = 6
    System.out.println("a.x = " + a.x); //JFB a.x = 2
    System.out.println("b.x = " + b.x); //JFB b.x = 2
    System.out.println("IdentifyMyParts.x = " + IdentifyMyParts.x);
    //JFB IdentifyMyParts.x = 2
}
}

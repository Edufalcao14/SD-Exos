public class Mor {
 String jessica = "Jessica";
 String eduardo = "Eduardo";
 String moises = "Moises";
 int a;
 int b;

 public String isTheFirstLetter(char letter){
     if (letter == 'j') return jessica;
     if (letter == 'e') return eduardo;
     if (letter == 'm') return moises;
     if (letter != 'j' || letter !='e' || letter != 'm') return "Letra invalida";
     return null;
 }
    public static void main(String[] args) {
    Mor mor = new Mor();

        System.out.println(mor.isTheFirstLetter('l'));
    }
}

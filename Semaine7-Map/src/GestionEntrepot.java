
import java.util.Arrays;

public class GestionEntrepot {
    //private static Scanner scanner = new Scanner(System.in);
    private static MonScanner scanner = new MonScanner("commandes.txt");
    private static Entrepot entrepot;

    public static void main(String[] args) {
        System.out.println("*********************");
        System.out.println("Gestion d'un entrepot");
        System.out.println("*********************");
        System.out.println();
        System.out.print("Entrez le nombre d'hangars : ");
        int nombreHangars = scanner.nextInt();
        entrepot = new Entrepot(nombreHangars);
        int choix = 0;
        do {
            System.out.println();
            System.out.println("1 -> attribuer un hangar");
            System.out.println("2 -> lister les hangars d'une societe");
            System.out.println("3 -> Liberer Hangar");
            System.out.println("5 -> Quitter");
            System.out.println();
            System.out.print("Votre choix : ");
            choix = scanner.nextInt();
            switch (choix) {
                case 1:
                    attribuerUnHangar();
                    break;
                case 2:
                    listerLesHangars();
                    break;
                case 3:
                    libererHangar();
                    break;
                case 5:
                    break;
            }

        } while (choix!=5);

        System.out.println("Fin");
    }

    private static void attribuerUnHangar() {
        if (entrepot.nombreHangarsLibres()==0) {
            System.out.println("Desole, tous les hangars sont occupes !");
        } else {
            System.out.print("Entrez le numero de la societe : ");
            int numeroSociete = scanner.nextInt();
            Societe societe = entrepot.getSociete(numeroSociete);
            String nomSociete;
            if(societe==null){
                System.out.print("Entrez le nom de la societe : ");
                nomSociete = scanner.next();
            }else{
                nomSociete = societe.getNom();
            }
            System.out.println();
            int numeroHangar = entrepot.attribuerHangar(numeroSociete,nomSociete);
            System.out.println("Le numero du hangar attribue : " + numeroHangar);
        }
    }

    private static void listerLesHangars() {
        int numeroSociete ;
        System.out.print("Entrez le numero de la societe que vous voulez lister les hangars : ");
        numeroSociete= scanner.nextInt();
        if (!entrepot.mapSociete.containsKey(numeroSociete)) {
            System.out.println("le numero de societe que vous avez informé n'existe pas , svp entrez le bon numero :");
            numeroSociete = scanner.nextInt();
        }
        System.out.println("Les Hangars de la societe " + numeroSociete + " : " + entrepot.mapSociete.get(numeroSociete).lesHangars());
    }
    private static void libererHangar() {
        int numeroHangar;
        System.out.print("Entrez le numero de la societe que vous voulez lister les hangars : ");
        numeroHangar = scanner.nextInt();
        if (entrepot.libererHangar(numeroHangar)) {
            System.out.println("l'hangar " + numeroHangar + " a été liberé ");
        }else {
            System.out.println("l'hangar " + numeroHangar + " n'a pas été liberé ");
        }

    }

}

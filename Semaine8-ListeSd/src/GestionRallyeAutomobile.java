/**
 *
 * @author Sampaio Falcao Eduardo Henrique
 *
 */
import java.util.Scanner;

public class GestionRallyeAutomobile {
    private static RallyeAutomobile rallyeAutomobile;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("" +
                "          ,-----------------.                    ,-----------------.\n" +
                "         /,--------.--------.\\                  /,--------.--------.\\\n" +
                "        /:       '---' ,--.  :\\                ::       '---'       ::\n" +
                "       (.'------------'----`-',)               ||__,-=-.____________||\n" +
                "     ,' \\`.        $        ,'/ `.           ,( \\.        &        ,/ ).\n" +
                "   ,'-.--. `.------^------.' .--,-`.       ,'-.-. `,------^------.' ,-,-`.\n" +
                "  ((__)) |,'    ___|___    `.| ((__))     ((__))|\\/    ___|___    \\/|((__))\n" +
                "  |`--' _|_________|_________|_ `--'|     |`--' |_________|_________| `--'|\n" +
                "  | __,',--.__|____|____|__,--.`.__ |     | __,',-.__|____|____|__,-.`.__ |\n" +
                "  ||__`_`--'._|____|____|__`--','__||     ||__`.`-'__|____|____|__`-','__||\n" +
                " (___          |SSt-101|          ___)   (___         |SSt-101|         ___)\n" +
                "    |````|``---'-------'---``|````|         |```|-----'-------'-----|```|\n" +
                "    '----'                   '----'         '---'                   '---'" + "\n");
        System.out.println("************************************************");
        System.out.println("Programme de gestion d'un Rallye Automobile");
        System.out.println("************************************************");

        System.out.println("Entrez le nombre de pilotes : ");
        int nbrPilote = scanner.nextInt();
        String[] tablePilote = new String[nbrPilote];
        String piloteNom = "Pilote";
        for (int i = 0; i < nbrPilote; i++) {
            System.out.println("Entrez le nom du pilote " + (i+1) + " : ");
            String pilote = scanner.next();
            tablePilote[i] = pilote;
        }
        rallyeAutomobile = new RallyeAutomobile(tablePilote);

        int option = 0;
        do {
            System.out.println("1 -> Afficher toute la course");
            System.out.println("2 -> Afficher le pilote en tête");
            System.out.println("3 -> Enregistrer un dépassement");
            System.out.println("4 -> Disqualifier un pilote");
            System.out.println("5 -> Donner la position d’un pilote");
            System.out.println("6 -> Faire franchir la ligne d’arrivée au pilote de tête");
            System.out.println("7 -> Remettre un pilote dans la course (après un autre pilote)");
            System.out.println("8 -> Afficher les pilotes hors course");
            System.out.println("9 -> Afficher le classement ");

            System.out.println("entrez votre choix: ");
            option = scanner.nextInt();
            switch (option) {

                case 1:
                    afficherLaCourse();
                    break;
                case 2:
                    afficherPiloteEnTete();
                    break;
                case 3:
                    enregistrerDepassement();
                    break;
                case 4:
                    disqualifierPilote();
                    break;
                case 5:
                    donnerPositionPilote();
                    break;
                case 6:
                    faireFranchirLaLigne();
                    break;
                case 7:
                    remettrePilotesHorsCourse();
                    break;
                case 8:
                    afficherLesPilotesHorsCourse();
                    break;
                case 9:
                    afficherClassement();
                    break;

            }
        } while (option >= 1 && option <= 9 && rallyeAutomobile.nombrePilotesEnCourse()!=0);

        System.out.println("La course est terminé!!");
        afficherClassement();

    }

    private static void afficherLaCourse() {
        System.out.println("Voici la course au moment actuel : ");
        System.out.println(rallyeAutomobile.afficherLaCourse());
    }

    private static void afficherPiloteEnTete() {
        System.out.println("Le pilote qui est en première position du Rallye :");
        System.out.println(rallyeAutomobile.afficherPiloteEnTete());
    }

    private static void enregistrerDepassement() {
        System.out.println("Entrez le nom du pilote qui va faire un depassement :");
        String pilote2 = scanner.next();
        try {
            rallyeAutomobile.enregistrerDepassement(pilote2);

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        afficherLaCourse();
    }

    private static void disqualifierPilote() {
        System.out.println("Entrez le pilote qui sera disqualifié :");
        String pilote = scanner.next();
        try {
            rallyeAutomobile.retirerPilote(pilote);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void donnerPositionPilote() {
        System.out.println("Entrez le pilote que vous voulez connaitre la position :");
        String pilote = scanner.next();
        try {
            rallyeAutomobile.donnerPositionPilote(pilote);

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        if (rallyeAutomobile.donnerPositionPilote(pilote)==0){
            System.out.println("Le pilote n'est pas dans la course");
        }else {
            System.out.println("La position du pilote est " + rallyeAutomobile.donnerPositionPilote(pilote));
        }
    }

    private static void faireFranchirLaLigne() {
        if (rallyeAutomobile.nombrePilotesEnCourse()!=0) {
            String pilote = rallyeAutomobile.afficherPiloteEnTete();
            try {
                rallyeAutomobile.fairePiloteFranchirLigne(pilote);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }else{
            System.out.println("Tout les pilotes ont deja franchi la ligne d'arrive");
        }
    }

    private static void afficherLesPilotesHorsCourse() {
        System.out.println("Voici les pilotes hors course :");
        System.out.println(rallyeAutomobile.afficherPilotesHorsCourse());
    }

    private static void remettrePilotesHorsCourse() {
        System.out.println("Entrez le nom du pilote que vous voulez remettre :");
        String pilote = scanner.next();
        System.out.println("Entrez le nom du pilote qui le pilote " + pilote + " sera place apres :");
        String pilote2 = scanner.next();
        try {
            rallyeAutomobile.remettrePilote(pilote, pilote2);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Le pilote " + pilote + " a été remis après le pilote " + pilote2);
    }

    private static void afficherClassement() {
        System.out.println("Le classement de la course :");
        System.out.println(rallyeAutomobile.classement());
    }
}

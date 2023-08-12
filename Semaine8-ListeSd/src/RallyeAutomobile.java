/**
 *
 * @author Sampaio Falcao Eduardo Henrique
 *
 */
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;

public class RallyeAutomobile {
    private ListeSDImpl<String> positionsRallye;
    private LinkedList<String> classement;
    private LinkedList<String> pilotesHorsCourse;

    RallyeAutomobile(String[] lesPilotes) {
        this.positionsRallye = new ListeSDImpl<>();
        classement = new LinkedList<String>();
        pilotesHorsCourse = new LinkedList<String>();
        String piloteN1 = lesPilotes[0];
        positionsRallye.insererEnTete(piloteN1);
        for (int i = 1; i < lesPilotes.length; i++) {
            positionsRallye.insererApres(lesPilotes[i - 1], lesPilotes[i]);
        }
    }

    public String afficherLaCourse() {
        return positionsRallye.toString();
    }


    public void enregistrerDepassement(String pilote) {
        if (pilote == null || pilote.equals("")) {
            throw new IllegalArgumentException("Parametres Invalides");
        }
        if (positionsRallye.contient(pilote)) {
            if (pilote.equals(positionsRallye.premier())) {
                System.out.println("Le pilote ne peut pas depasser personne car il est en premier !");
            } else {
                String piloteDepasse = positionsRallye.donnerPrecedent(pilote);
                positionsRallye.permuter(pilote, piloteDepasse);
                System.out.println("Le depassemenet a été enregistré !");
            }
        }else{
            System.out.println("Le pilote " + pilote + " n'est pas dans la course");
        }
    }

    public int nombrePilotesEnCourse() {
        return positionsRallye.taille();
    }

    public String afficherPiloteEnTete() {
        return positionsRallye.premier();
    }

    public int donnerPositionPilote(String pilote) {
        if (pilote.equals("") || pilote == null) throw new IllegalArgumentException("");
        if (afficherPiloteEnTete().equals(pilote)) return 1;
        int position = 0;
        if (positionsRallye.contient(pilote)) {
            for (String piloteTEMP : positionsRallye) {
                position++;
                if (piloteTEMP.equals(pilote)) {
                    break;
                }
            }
            return position;
        }
        return 0;
    }

    public void retirerPilote(String pilote) {
        if (pilote.equals("") || pilote == null) throw new IllegalArgumentException("");
        if (positionsRallye.contient(pilote)) {
            pilotesHorsCourse.add(pilote);
            positionsRallye.supprimer(pilote);

            System.out.println("Le pilote " + pilote + " a été retiré de la course");
        }
        System.out.println("Le pilote " + pilote + "N'est pas dans la course !");
    }

    public void fairePiloteFranchirLigne(String pilote) {
        if (pilote.equals("") || pilote == null) throw new IllegalArgumentException("");

        if (positionsRallye.contient(pilote)) {
            positionsRallye.supprimer(pilote);
            if (classement.isEmpty()) {
                classement.addFirst(pilote);
                System.out.println("Le pilote " + pilote + " a franchi la ligne d'arrive");
            } else {
                classement.add(pilote);
                System.out.println("Le pilote " + pilote + " a franchi la ligne d'arrive");
            }
        } else {
            System.out.println("Le pilote " + pilote + " n'est pas dans la course");
        }
    }

    public void remettrePilote(String piloteARemetre, String piloteDevant) {
        if (piloteARemetre == null || piloteARemetre.equals("") || piloteDevant == null || piloteDevant.equals("")) {
            throw new IllegalArgumentException("");
        }
        if (!positionsRallye.contient(piloteARemetre)) {
            positionsRallye.insererApres(piloteDevant, piloteARemetre);
        } else {
            System.out.println("Le pilote est deja dans la course!!");
        }
    }

    public String afficherPilotesHorsCourse() {
        if (pilotesHorsCourse.isEmpty()) return " ";

        return pilotesHorsCourse.toString();
    }

    public String classement() {
        if (classement.isEmpty()) return " ";

        return classement.toString();
    }
}

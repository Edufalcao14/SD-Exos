import java.util.*;

/**
 *
 * @author Sampaio Falcao Eduardo Henrique
 *
 */
public class Brocante {

    private int phase = 1;

    private Emplacement[] tableEmplacements;
    private HashMap<String, Integer> mapRiverains;
    private ArrayDeque<Emplacement> pileEmplacementsLibres;

    private HashMap<String , Exposant> mapExposants;
    private int nombrePlacesLibres;

    //private String tableRiverains[] 
    //inutile, regardez bien les schemas, cette table n'apparait pas !


    public int getPhase() {
        return phase;
    }

    /**
     * initialise une brocante avec nombre emplacements
     *
     * @param nombreEmplacements le nombre d'emplacements
     * @param tableRiverains     la table des riverains
     * @throws IllegalArgumentException si le nombre d'emplacements est negatif ou nul ou si la table des riverains est null
     */
    public Brocante(int nombreEmplacements, String[] tableRiverains) {
        if (tableRiverains == null) throw new IllegalArgumentException("La table de riverains est null");
        if (nombreEmplacements < 0 || nombreEmplacements == 0)
            throw new IllegalArgumentException("Le nombre D'emplacement est invalide");

        this.phase = 1;
        this.tableEmplacements = new Emplacement[nombreEmplacements];
        this.mapRiverains = new HashMap<String, Integer>();
        this.mapExposants = new HashMap<>();
        this.pileEmplacementsLibres = new ArrayDeque<>();
        this.nombrePlacesLibres = tableEmplacements.length;

        for (int i = 0; i < tableEmplacements.length; i++) {
            tableEmplacements[i] = new Emplacement(i);
        }

        for (String riverains : tableRiverains) {
            mapRiverains.put(riverains, 0);
        }
    }

    public int nombreEmplacementsRiverain(String nom) {
        return mapRiverains.get(nom);
    }

    public boolean estUnRiverain(String nom) {
        if (mapRiverains.containsKey(nom)) return true;
        return false;
    }

    public boolean estLibre(int numeroEmplacement) {
        if (tableEmplacements[numeroEmplacement].getExposant() == null) return true;

        return false;
    }

    public Exposant consulterExposantViaNom(String nom){
        return mapExposants.get(nom);
    }
    public LinkedList getListeExposant(String nom){
        return mapExposants.get(nom).getListeEmplacements();
    }
    public String tousLesExposants(){
        String string = "";
        for (Exposant exposant:mapExposants.values()) {
            string += exposant + "\n" + "--------------------------------" + "\n";
        }
        return string;
    }

    /**
     * reserve l'emplacement qui porte le numero passe en parametre au demandeur passe en parametre
     * La reservation reussit si
     * l'emplacement est libre
     * le demandeur est bien un riverain
     * le riverain n'a pas encore 3 emplacements
     *
     * @param demandeur         le riverain qui demande un emplacement
     * @param numeroEmplacement le numero de l'emplacement souhaite
     * @return true si la reservation a reussi, false sinon
     * @throws IllegalArgumentException si le numero de l'emplacement n'existe pas
     * @throws IllegalStateException    si on n'est pas en phase 1
     */
    public boolean reserver(Exposant demandeur, int numeroEmplacement) {
        if (getPhase() != 1) throw new IllegalStateException("La brocante n'est pas en Phase 1");
        System.out.println(phase);
        if (numeroEmplacement < 0) throw new IllegalArgumentException("Lee numero d'emplacement n'existe pas");

        if (!estLibre(numeroEmplacement)) return false;
        if (!estUnRiverain(demandeur.getNom())) return false;
        if (nombreEmplacementsRiverain(demandeur.getNom()) >= 3) return false;

        Integer nombreEmplacements = mapRiverains.get(demandeur.getNom());
        mapRiverains.put(demandeur.getNom(), ++nombreEmplacements);
        mapExposants.put(demandeur.getNom(),demandeur);
        tableEmplacements[numeroEmplacement].setExposant(demandeur);
        nombrePlacesLibres--;
        return true;
    }
    /**
     * attribue automatiquement un emmplacement libre au demandeur passe en parametre
     *
     * @param demandeur le demandeur d'un emplacement
     * @return le numero de l'emplacement attribue ou -1 si plus d'emplacement libre
     * @throws IllegalStateException si on n'est pas en phase 2
     */
    public int attribuerAutomatiquementEmplacement(Exposant demandeur) {
        if (phase != 2) {
            throw new IllegalStateException("Impossible d'attribuer un emplacement automatiquement en dehors de la phase 2.");
        }
        else
        if (pileEmplacementsLibres==null) return -1;
        int numeroEmplacement = pileEmplacementsLibres.getFirst().getNumero();

        tableEmplacements[pileEmplacementsLibres.getFirst().getNumero()].setExposant(demandeur);
        mapExposants.put(demandeur.getNom(),demandeur);
        pileEmplacementsLibres.pop();
        nombrePlacesLibres--;

        return numeroEmplacement;
    }

    /**
     * a comme effet de passer de la phase 1 a la phase 2
     * si deja en phase 2, rien ne doit etre fait
     */
    public void changerPhase() {
        if (this.phase == 2) {
            return;
        }
        setPhase(2);
        if (nombrePlacesLibres!=0) {
            for (Emplacement emplacement : this.tableEmplacements) {
                if (emplacement.getExposant() == null) {
                    this.pileEmplacementsLibres.push(emplacement);
                }
            }
        }
    }

    public void setPhase(int phase) {
        this.phase = phase;

    }

    @Override
    public String toString() {
        String aRenvoyer = "";
        for (int i = 0; i < tableEmplacements.length; i++) {
            if (tableEmplacements[i].getExposant() == null) {
                aRenvoyer += ("\n" + i + " : /");
            } else {
                aRenvoyer += ("\n" + i + " : " + tableEmplacements[i].getExposant().getNom());
            }
        }
        return aRenvoyer;
    }

    //Pour le debug
    public String donnerTableEmplacements() {
        if (tableEmplacements == null)
            return "null";
        return Arrays.toString(tableEmplacements);
    }

    //Pour le debug
    public String donnerMapRiverains() {
        if (mapRiverains == null)
            return "null";
        return mapRiverains.toString();
    }

    //Pour le debug
    public String donnerPileEmplacementsLibres() {
        if (pileEmplacementsLibres == null)
            return "null";
        return pileEmplacementsLibres.toString();
    }

}

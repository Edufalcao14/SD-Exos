import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class ParcAConteneurs {
    private HashSet<String>[] tablePeriodes; //table avec l'ensemble des voitures autorisees pour chaque periode
    private HashMap<String,Integer> mapVoitures; //voiture (plaque)  --> nombre de periodes reservees pour cette voiture
    private int maxPeriodes; //nombre max de periodes pour une meme voiture
    private int maxVoitures; //nombre max de voitures pour une meme periode

    /**
     * debute une semaine de reservation
     * @param nombrePeriodes le nombre de periodes de cette semaine
     * @param maxVoitures le nombre maximal de voitures autorisees sur une periode
     * @param maxPeriodes le nombre maximal de periodes qu'une meme voiture peut reserver sur cette semaine
     * @throws IllegalArgumentException
     *    s'il n'y a pas au moins une periode
     *    s'il n'y a pas au moins une voiture par periode
     *    s'il n'y a pas au moins une periode par voiture
     */
    public ParcAConteneurs(int nombrePeriodes, int maxVoitures, int maxPeriodes){
        this.maxVoitures = maxVoitures;
        this.maxPeriodes = maxPeriodes;
        this.tablePeriodes = new HashSet[nombrePeriodes];
        for (int i = 0; i < tablePeriodes.length; i++) {
            tablePeriodes[i] = new HashSet<>();
        }
        this.mapVoitures = new HashMap<String,Integer>();
    }

    /**
     * enregistre, si possible, la voiture pour la periode demandee
     * (La numerotation des periodes commence a 1)
     * la voiture ne peut pas depasser le nombre max de periodes autorisees par voiture
     * la voiture ne peut pas deja etre enregistree pour cette periode
     * le nombre de voitures max pour la periode demandee ne peut etre atteint
     * @param voiture la voiture a enregistrer
     * @param numeroPeriode la periode demandee
     * @return true si la voiture a ete enregistree pour la periode demandee, false sinon
     * @throws IllegalArgumentException si la voiture est null ou vide
     *                                  si la periode n'existe pas
     */
    public boolean enregistrerVoiture(String voiture, int numeroPeriode) {
        if (numeroPeriode<=0) throw new IllegalArgumentException("La periode n'existe pas");
        if (voiture.equals("")||voiture==null) throw new IllegalArgumentException("La voiture est null ou vide");
        if (tablePeriodes[numeroPeriode-1].contains(voiture)) return false;
        if (tablePeriodes[numeroPeriode-1].size()>=maxVoitures) return false;
        if (mapVoitures.get(voiture) != null && mapVoitures.get(voiture) == maxPeriodes) return false;


        tablePeriodes[numeroPeriode-1].add(voiture);
        mapVoitures.put(voiture,numeroPeriode);
         return true;
    }

    /**
     * verifie si la voiture a bien ete enregistree pour cette periode
     * (La numerotation des periodes commence a 1)
     * @param voiture la voiture verifiee
     * @param numeroPeriode la periode verifiee
     * @return true si la voiture est autorisee, false sinon
     * @throws IllegalArgumentException si la voiture est null ou vide
     *                                  si la periode n'existe pas
     */
    public boolean estAutorisee(String voiture, int numeroPeriode) {
        if (numeroPeriode<=0) throw new IllegalArgumentException("La periode n'existe pas");
        if (voiture.equals("")||voiture==null) throw new IllegalArgumentException("La voiture est null ou vide");
        if (tablePeriodes[numeroPeriode-1].contains(voiture)) return true;
        return false;
    }

    /**
     * construit une liste avec les numeros des periodes non pleines
     * cette liste est triee selon l'ordre croissant des periodes
     * @return la liste avec les numeros des periodes non pleines
     */
    public ArrayList<Integer> listePeriodesNonPleines(){
        ArrayList<Integer> listePeriodesNonPleines = new ArrayList<Integer>();
        for (int i = 0; i < tablePeriodes.length; i++) {
            if (tablePeriodes[i].size()!=maxVoitures){
                listePeriodesNonPleines.add(i+1);
            }
        }

        return listePeriodesNonPleines;
        //aucun tri n'est necessaire, reflechissez !
    }

    @Override
    public String toString() {
        //Vous pouvez modifier cette methode
        //MAIS
        //Cette methode ne sera pas evaluee
        //Ne perdez pas de temps
        return "tablePeriodes=" + Arrays.toString(tablePeriodes) +
                "\nmapVoitures=" + mapVoitures +
                "\nmaxVoitures=" + maxVoitures +
                "\nmaxPeriodes=" + maxPeriodes;
    }
}

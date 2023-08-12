import java.util.HashMap;

/**
 *
 * @author 													-------> SAMPAIO FALCAO EDUARDO HENRIQUE
 *
 *
 */

public class Entrepot {

	int nombrehangarsLibre;
	HashMap<Integer , Societe> mapSociete;
	Hangar [] tableHangars;


	/**
	 * construit un entrepot contenant nombreHangars
	 * @param nombreHangars le nombre d'hangars
	 * @throws IllegalArgumentException si le nombre d'hangars est negatif ou nul
	 */
	public Entrepot(int nombreHangars) {
		if(nombreHangars<=0)
			throw new IllegalArgumentException();
		this.nombrehangarsLibre=nombreHangars;
		mapSociete = new HashMap<>();
		tableHangars= new Hangar[nombreHangars];
		for (int i = 0; i < nombreHangars; i++) {
			tableHangars[i]=new Hangar(i);
		}
	}

	/**
	 * renvoie le nombre d'hangars libres
	 * @return le nombre d'hangars libres
	 */
	public int nombreHangarsLibres(){
		return nombrehangarsLibre;
	}


	/**
	 * renvoie le nombre de societes presentes
	 * @return le nombre de societes presentes
	 */
	public int nombreSocietesPresentes(){
		return mapSociete.size();

	}

	/**
	 * renvoie la societe dont le numero est passe en parametre
	 * @param numeroSociete le numero de la societe
	 * @return la societe recherchee ou null si aucune societe presente ne possede ce numero
	 */
	public Societe getSociete(int numeroSociete){
		return mapSociete.get(numeroSociete);
	}

	/**
	 * attribue un hangar a la societe passee en parametre
	 * Si l'attribution a pu se faire :
	 * la societe est enregistree comme presente (si pas encore presente)
	 * le hangar lui est ajoute
	 * @param numeroSociete le numero de la societe
	 * @param nomSociete le nom de la societe
	 * @return le numero du hangar attribue ou -1 s'il n'y en a plus de libre
	 */
	public int attribuerHangar(int numeroSociete, String nomSociete) {
		if (numeroSociete < 0) return -1;
		if (nombrehangarsLibre==0) return -1;

		int numeroHangar ;
		Societe societe = new Societe(numeroSociete, nomSociete);

		if (!mapSociete.containsKey(numeroSociete))
			mapSociete.put(numeroSociete,societe);

		if (numeroSociete>= tableHangars.length) numeroHangar =numeroSociete%tableHangars.length;
		else
			numeroHangar=numeroSociete;

			while (tableHangars[numeroHangar].getSociete() != null) {
				if (numeroHangar>=tableHangars.length-1){
					numeroHangar=-1;
				}
				numeroHangar++;
			}
			mapSociete.get(numeroSociete).ajouterHangar(numeroHangar);
			tableHangars[numeroHangar].setSociete(societe);
			nombrehangarsLibre--;
			return numeroHangar;
	}
	public boolean libererHangar(int numeroHangar){
		if (numeroHangar<0) return false;
		if (numeroHangar> tableHangars.length) return false;

		Societe societe = tableHangars[numeroHangar].getSociete();

		mapSociete.get(societe.getNumeroSociete()).retirerHangar(numeroHangar);
		tableHangars[numeroHangar].setSociete(null);
		nombrehangarsLibre++;
		return true;
	}
}

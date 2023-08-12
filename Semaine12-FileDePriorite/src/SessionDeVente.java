import java.util.*;

public class SessionDeVente {

    private PriorityQueue<ClientEnAttente> filePriorite;
    private HashSet<Client> ensembleClientsActuellementDansFile;
    private HashMap<Client, Commande> mapClientCommande;
    private ArrayList<Commande> listeCommandes;
    private CompareteurClient compareteurClient = new CompareteurClient();
    private int nombreCasiersRestants;
    private final static int MAX_CASIERS_CLIENT = 3;


    /**
     * debute une session de vente
     *
     * @param nombreCasiersMisEnVente le nombre de casiers mis en vente
     * @throws IllegalArgumentException s'il n'y a pas au moins un casier a vendre
     */
    public SessionDeVente(int nombreCasiersMisEnVente) {
        if (nombreCasiersMisEnVente <= 0)
            throw new IllegalArgumentException();
        this.nombreCasiersRestants = nombreCasiersMisEnVente;
        filePriorite = new PriorityQueue<ClientEnAttente>(compareteurClient);
        ensembleClientsActuellementDansFile = new HashSet<Client>();
        mapClientCommande = new HashMap<Client, Commande>();
        listeCommandes = new ArrayList<Commande>();
    }

    public int getNombreCasiersRestants() {
        return nombreCasiersRestants;
    }

    /**
     * ajoute, si possible, le client dans la file d'attente
     * le client ne peut pas deja y etre
     * si client a deja une commande lors de cette session de vente, le max de casiers autorise n'est pas deja atteint
     * s'il reste encore des casiers a vendre
     *
     * @param client le client a ajouter
     * @return true si l'ajout a pu se faire, false sinon
     * @throws IllegalArgumentException si le client est null ou vide
     */
    public boolean placerDansFileAttente(ClientEnAttente client) {
        if (client.equals("") || client == null) throw new IllegalArgumentException("");
        if (ensembleClientsActuellementDansFile.contains(client.getClient())) return false;
        if (mapClientCommande.containsKey(client.getClient()) && mapClientCommande.get(client.getClient()).getNombreCasiersDemandes() == 3)
            return false;
        if (nombreCasiersRestants == 0) return false;


        filePriorite.add(client);
        ensembleClientsActuellementDansFile.add(client.getClient());
        return true;

    }

    /**
     * retire de la file d'attente le client de tete
     *
     * @return le client de tete ou null si la file est vide
     */
    public ClientEnAttente selectionnerClientSuivant() {
        if (filePriorite.isEmpty()) return null;
        ClientEnAttente client = filePriorite.poll();
        ensembleClientsActuellementDansFile.remove(client.getClient());

        return client;
    }

    /**
     * ajoute, si possible, une nouvelle commande
     * le nombre de casiers restants doit etre suffisant pour satisfaire completement la commande
     * (il n'y a pas de commande partielle)
     * le nombre de casiers demandes ne peut depasser le max autorise
     *
     * @param client                le client qui fait la demande
     * @param nombreCasiersDemandes le nombre de casiers demandes
     * @return true si la commande a pu etre faite, false sinon
     * @throws IllegalArgumentException si le client est null ou vide
     *                                  ou si le nombre de casiers demandés est <=0
     * @throws IllegalStateException    si le client a deja fait une commande
     */
    public boolean passerNouvelleCommande(ClientEnAttente client, int nombreCasiersDemandes) {
        if (client.equals("") || client == null) throw new IllegalArgumentException("");
        if (nombreCasiersDemandes > nombreCasiersRestants) return false;
        if (mapClientCommande.containsKey(client) && mapClientCommande.get(client).getNombreCasiersDemandes() == 3)
            return false;
        if (nombreCasiersDemandes == 0) return false;

        Commande commande = new Commande(client.getClient(), nombreCasiersDemandes);
        mapClientCommande.put(client.getClient(), commande);
        listeCommandes.add(commande);
        nombreCasiersRestants = nombreCasiersRestants - nombreCasiersDemandes;
        client.getClient().setPriorite(client.getClient().getPriorite() - 1);
        return true;
    }


    /**
     * modifie, si possible, une commande existante
     * le nombre de casiers restants doit etre suffisant
     * (il n'y a pas de commande partielle)
     * le nombre total de casiers apres ajout de ce nombre de casiers supplementaires ne peut depasser le max autorise
     *
     * @param client                      le client qui veut modifier sa commande
     * @param nombreCasiersDemandesEnPlus le nombre de casiers a ajouter au nombre de casiers deja commande
     * @return true si la commande a pu etre modifiee, false sinon
     * @throws IllegalArgumentException si le client est null ou vide
     *                                  ou si le nombre de casiers demandes est <= 0
     * @throws IllegalStateException    si le client n'a pas encore fait de commande lors de cette session de commande
     */
    public boolean modifierCommande(Client client, int nombreCasiersDemandesEnPlus) {
        if (client.equals("") || client == null) throw new IllegalArgumentException("");
        if (!mapClientCommande.containsKey(client)) throw new IllegalStateException("");
        int nombreCasiersClient = mapClientCommande.get(client).getNombreCasiersDemandes();
        if (nombreCasiersClient + nombreCasiersDemandesEnPlus > nombreCasiersRestants) return false;
        if (+nombreCasiersDemandesEnPlus > 3) return false;

        mapClientCommande.get(client).setNombreCasiersDemandes(nombreCasiersClient + nombreCasiersDemandesEnPlus);
        return true;
    }


    public String toString() {
        // cette methode ne sera pas evaluee
        // elle peut-etre interessante a appeler en cas de bug
        // n'hesitez pas a la completer
        return "le nombre de casiers restants : " + nombreCasiersRestants
                + "\nla file d'attente : " + filePriorite.stream().toList() + "\nles commandes " + listeCommandes;
    }

}

		
	
	
	
	
	


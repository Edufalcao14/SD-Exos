import java.util.Objects;

public class ClientEnAttente {
    private Client client;
    private static int numArrivee = 0;

    public ClientEnAttente(Client client){
        if (client==null) throw new IllegalArgumentException("");
        this.client = client;
        numArrivee++;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public static int getNumArrivee() {
        return numArrivee;
    }

    public static void setNumArrivee(int numArrivee) {
        ClientEnAttente.numArrivee = numArrivee;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ClientEnAttente other = (ClientEnAttente) obj;
        if ((this.getClient().getNom() == null) ? (other.getClient().getNom() != null) : !this.getClient().getNom().equals(other.getClient().getNom())) {
            return false;
        }
        return this.getClient().getPriorite() == other.getClient().getPriorite();
    }


    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.getClient().getNom());
        hash = 71 * hash + this.getClient().getPriorite();
        return hash;
    }

    @Override
    public String toString() {
        return "[" +
                "client : " + client +
                ", numArrivee=" + numArrivee +
                ']';
    }
}

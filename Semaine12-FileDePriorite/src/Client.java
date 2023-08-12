import java.util.Objects;

public class Client {
    private String nom;
    private int priorite;


    public Client(String nom ){
        if (nom.equals("") || nom==null) throw new IllegalArgumentException("");
        this.nom =nom;
        this.priorite=3;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getPriorite() {
        return priorite;
    }

    public void setPriorite(int priorite) {
        this.priorite = priorite;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Client other = (Client) obj;
        if ((this.nom == null) ? (other.nom != null) : !this.nom.equals(other.nom)) {
            return false;
        }
        if (this.priorite != other.priorite) {
            return false;
        }
        return true;
    }


    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.nom);
        hash = 71 * hash + this.priorite;
        return hash;
    }

    @Override
    public String toString() {
        return "Nom : '" + nom + '\'' +
                ", Priorite=" + priorite;
    }
}


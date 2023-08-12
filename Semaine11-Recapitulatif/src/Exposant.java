
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Objects;
/**
 *
 * @author Sampaio Falcao Eduardo Henrique
 *
 */
public class Exposant {
    private String nom;
    private String email;
    private LinkedList<Emplacement> listeEmplacements;

    public Exposant(String nom, String email) {
        this.nom = nom;
        this.email = email;
        this.listeEmplacements = new LinkedList<>();
    }
    public LinkedList<Emplacement> getListeEmplacements() {
        return listeEmplacements;
    }
    public void ajouterEmplacement(Emplacement emp) {
        listeEmplacements.addFirst(emp);
    }
    public Iterator<Emplacement> tousLesEmplacements() {
        return listeEmplacements.iterator();
    }
    public String getNom() {
        return nom;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exposant exposant = (Exposant) o;
        return nom.equals(exposant.nom) && email.equals(exposant.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, email);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Exposant [nom=").append(nom).append("\n");
        sb.append("Emplacements :\n");
        for (Emplacement emplacement : listeEmplacements) {
            sb.append(" - ").append(emplacement).append("\n");
        }
        return sb.toString()+"]";
    }
}


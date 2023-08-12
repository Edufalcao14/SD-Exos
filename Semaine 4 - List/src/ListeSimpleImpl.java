import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * implementation de l'interface ListeSimple avec une liste simplement chainee
 *
 * @author Sampaio Falcao Eduardo Henrique
 */
public class ListeSimpleImpl<E> implements ListeSimple<E> {

    private Noeud tete;
    private int taille;


    public ListeSimpleImpl() {
        tete = null;
        taille = 0;
    }

    // A NE PAS MODIFIER --> POUR LES TESTS!!!
    public ListeSimpleImpl(Object[] table) {
        if (table == null)
            throw new IllegalArgumentException();
        this.taille = table.length;
        for (int i = table.length - 1; i >= 0; i--) {
            E element = (E) table[i];
            this.tete = new Noeud(element, tete);
        }
    }

    // A NE PAS MODIFIER --> POUR LES TESTS!!!
    public String toString() {
        String aRenvoyer = "";
        Noeud baladeur = tete;
        int cpt = 0;
        while (baladeur != null) {
            cpt++;
            if (cpt > taille) {
                aRenvoyer = "boucle infinie dans toString(), chainage a verifier";
                return aRenvoyer;
            }
            aRenvoyer += " " + baladeur.element;
            baladeur = baladeur.suivant;
        }
        return aRenvoyer;
    }


    public int taille() {
        return taille;
    }

    public boolean estVide() {
        return taille == 0;
    }

    // renvoie l element contenu dans le noeud de tete
    public E premier() throws ListeVideException {
        if (estVide()) throw new ListeVideException("Liste vide");
        return tete.element;

    }


    // insere un nouveau noeud en tete de liste avec l element
    public void insererEnTete(E element) {
        if (estVide()) {
            Noeud noeudAjout = new Noeud(element, null);
            tete = noeudAjout;
            taille++;
        } else {
            Noeud noeudAjout = new Noeud(element, tete);
            tete = noeudAjout;
            taille++;

        }
    }


    // verifie la presence d un noeud contenant l element passe en parametre
    public boolean contient(E element) {
        if (estVide()) {
            return false;
        }
        if (premier().equals(element)) return true;
        Noeud baladeur = tete;
        while (baladeur != null) {
            if (baladeur.element.equals(element)) {
                return true;
            }
            baladeur = baladeur.suivant;
        }
        return false;

    }


    // insere un nouveaud noeud apres le noeud contenant la premiere occurrence de l'element passe en parametre
    public boolean insererApres(E element, E elementAInserer) {
        if (estVide()) {
            insererEnTete(element);
            return true;
        }
        Noeud baladeur = tete;
        while (baladeur != null) {
            if (baladeur.element.equals(elementAInserer)) {
                Noeud ajout = new Noeud(element, baladeur.suivant);
                baladeur.suivant = ajout;
                taille++;
                return true;
            }
            baladeur = baladeur.suivant;
        }
        return false;
    }
    //supprime le noeud contenant la premiere occurrence de l'element passe en parametre
    public boolean supprimer(E element) {
        if (estVide()) return false;

        if (premier().equals(element)) {
            tete = tete.suivant;
            taille--;
            return true;
        }
        Noeud baladeur = tete;
        Noeud noeudPrecedent = tete;
        while (baladeur != null) {
            if (baladeur.element.equals(element)) {
                noeudPrecedent.suivant = baladeur.suivant;
                taille--;
                return true;
            }
            noeudPrecedent = baladeur;
            baladeur = baladeur.suivant;
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new IterateurImpl();
    }


    private class Noeud {
        private E element;
        private Noeud suivant;

        public Noeud(E element, Noeud suivant) {
            this.element = element;
            this.suivant = suivant;
        }
    }


    private class IterateurImpl implements Iterator {

        private Noeud noeudNext;


        // Au depart le noeud "next" est le noeud de tete
        private IterateurImpl() {
            noeudNext = tete;
        }

        @Override
        // verifie si le noeud "next" est null
        public boolean hasNext() {
            if (noeudNext == null) {
                return false;
            }
            return true;
        }


        @Override
        // renvoie l element qui se trouve dans le noeud "next"
        // le noeud "next" passe au noeud suivant
        public E next() {
            if (!hasNext()) throw new NoSuchElementException();
            E element = noeudNext.element;
            noeudNext = noeudNext.suivant;
            return element;
        }


        @Override
        // A NE PAS COMPLETER : Les suppressions sont interdites
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

}

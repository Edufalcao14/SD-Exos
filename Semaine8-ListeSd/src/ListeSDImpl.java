/**
 *
 * @author Sampaio Falcao Eduardo Henrique
 *
 */

import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListeSDImpl<E> implements ListeSD<E>, Iterable<E> {

    private Noeud tete, queue;
    private HashMap<E, Noeud> mapElementNoeud;

    public ListeSDImpl() {
        mapElementNoeud = new HashMap<E, Noeud>();
        tete = new Noeud();
        queue = new Noeud();
        tete.suivant = queue;
        queue.precedent = tete;

    }

    public int taille() {
        return mapElementNoeud.size();
    }

    public boolean estVide() {
        if (mapElementNoeud.size() == 0) return true;
        return false;

    }

    public boolean contient(E element) {
        if (mapElementNoeud.containsKey(element)) return true;
        return false;

    }

    public E premier() {
        return tete.suivant.element;

    }

    public E dernier() {
        return queue.precedent.element;
    }

    public E donnerPrecedent(E element) {
        if (element == null) return null;
        if (!mapElementNoeud.containsKey(element)) return null;
        if (tete == mapElementNoeud.get(element)) return null;
        return mapElementNoeud.get(element).precedent.element;
    }

    public E donnerSuivant(E element) {
        if (!mapElementNoeud.containsKey(element)) return null;
        if (queue == mapElementNoeud.get(element)) return null;
        return mapElementNoeud.get(element).suivant.element;

    }

    public boolean insererEnTete(E element) {
        if (mapElementNoeud.containsKey(element)) return false;

        Noeud noeudAInserer = new Noeud(element);
        Noeud noeudAvant = tete;
        Noeud noeudApres = tete.suivant;

        noeudAInserer.suivant = noeudApres;
        noeudAInserer.precedent = noeudAvant;
        noeudApres.precedent = noeudAInserer;
        noeudAvant.suivant = noeudAInserer;

        mapElementNoeud.put(element, noeudAInserer);
        return true;

    }

    public boolean insererEnQueue(E element) {
        if (mapElementNoeud.containsKey(element)) return false;

        Noeud noeudAInserer = new Noeud(element);

        Noeud noeudAvant = queue.precedent;
        Noeud noeudApres = queue;

        noeudAInserer.suivant = noeudApres;
        noeudAInserer.precedent = noeudAvant;
        noeudAvant.suivant = noeudAInserer;
        noeudApres.precedent = noeudAInserer;

        mapElementNoeud.put(element, noeudAInserer);

        return true;

    }

    public boolean insererApres(E element, E elementAInserer) {

        if (mapElementNoeud.containsKey(elementAInserer) || !mapElementNoeud.containsKey(element)) return false;

        Noeud noeudAvant = mapElementNoeud.get(element);
        Noeud noeudApres = mapElementNoeud.get(element).suivant;

        Noeud noeudAinserer = new Noeud(elementAInserer);

        noeudAinserer.suivant = noeudApres;
        noeudAinserer.precedent = noeudAvant;
        noeudApres.precedent = noeudAinserer;
        noeudAvant.suivant = noeudAinserer;

        mapElementNoeud.put(elementAInserer, noeudAinserer);


        return true;

    }

    public boolean insererAvant(E element, E elementAInserer) {
        if (mapElementNoeud.containsKey(elementAInserer) || !mapElementNoeud.containsKey(element)) return false;

        Noeud noeudAvant = mapElementNoeud.get(element).precedent;
        Noeud noeudApres = mapElementNoeud.get(element);

        Noeud noeudAinserer = new Noeud(elementAInserer);

        noeudAinserer.suivant = noeudApres;
        noeudAinserer.precedent = noeudAvant;
        noeudApres.precedent = noeudAinserer;
        noeudAvant.suivant = noeudAinserer;


        mapElementNoeud.put(elementAInserer, noeudAinserer);
        return true;

    }


    public boolean supprimer(E element) {
        Noeud noeud = mapElementNoeud.get(element);
        if (element == null || !mapElementNoeud.containsKey(element)) return false;

        Noeud noeudAvant = noeud.precedent;
        Noeud noeudApres = noeud.suivant;

        noeudAvant.suivant = noeudApres;
        noeudApres.precedent = noeudAvant;

        mapElementNoeud.remove(element);

        return true;

    }


    public boolean permuter(E element1, E element2) {
        if (!mapElementNoeud.containsKey(element1) || !mapElementNoeud.containsKey(element2)) return false;

        Noeud noeud1 = mapElementNoeud.get(element1);
        Noeud noeud2 = mapElementNoeud.get(element2);

        E elementTemp = noeud1.element;
        noeud1.element = noeud2.element;
        noeud2.element = elementTemp;
        mapElementNoeud.remove(element1);
        mapElementNoeud.remove(element2);

        mapElementNoeud.put(element2, noeud1);
        mapElementNoeud.put(element1, noeud2);
        return true;

        // REMARQUE : CE SONT LES VALEURS QUI SONT PERMUTEES, PAS LES NOEUDS!!!
        // Il est donc inutile de revoir le chainage
        // N'oubliez pas de modifier les noeuds associes dans le map

    }

    public Iterator<E> iterator() {


        return new IterateurImpl<E>();
        // il faut renvoyer un objet de type Iterator :
        //return new IterateurImpl<E>();
        // completez la classe interne IterateurImpl !
    }

    public String toString() {
        String aRenvoyer = "";
        int num = 1;
        Noeud baladeur = tete.suivant;
        while (baladeur != queue) {
            aRenvoyer += num + " - " + baladeur.element + "\n";
            baladeur = baladeur.suivant;
            num++;
        }
        return aRenvoyer;
    }


    // Classe interne Noeud
    private class Noeud {
        private E element;
        private Noeud suivant;
        private Noeud precedent;

        private Noeud() {
            this(null, null, null);
        }

        private Noeud(E element) {
            this(null, element, null);
        }

        private Noeud(Noeud precedent, E element, Noeud suivant) {
            this.element = element;
            this.suivant = suivant;
            this.precedent = precedent;
        }
    }


    // Classe interne IterateurImpl
    private class IterateurImpl<E> implements Iterator<E> {

        private Noeud noeudCourant;

        private IterateurImpl() {
            noeudCourant = tete.suivant;
        }

        public boolean hasNext() {

            if (noeudCourant.suivant!=queue) return true;
            return false;

        }

        // renvoie l element qui se trouve dans le noeud courant
        // le noeud courant passe au noeud suivant
        public E next() {
            E elementTemp = (E) noeudCourant.element;
            if (hasNext()){
                noeudCourant=noeudCourant.suivant;
            }

            return elementTemp;
        }

        // A NE PAS COMPLETER : Les suppressions sont interdites
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // pour les tests
    public ListeSDImpl(E[] tableACopier) {
        mapElementNoeud = new HashMap<E, Noeud>();
        tete = new Noeud();   // sentinelle de tete
        queue = new Noeud();  // sentinelle de queue
        Noeud prec = tete;
        for (int i = 0; i < tableACopier.length; i++) {
            Noeud nouveauNoeud = new Noeud(tableACopier[i]);
            mapElementNoeud.put(tableACopier[i], nouveauNoeud);
            nouveauNoeud.precedent = prec;
            prec.suivant = nouveauNoeud;
            prec = nouveauNoeud;
        }
        prec.suivant = queue;
        queue.precedent = prec;
    }

    // pour les tests
    public String teteQueue() {
        try {
            String aRenvoyer = "(";
            Noeud baladeur = tete.suivant;
            int cpt = 0;
            while (baladeur != queue) {
                if (cpt == 0)
                    aRenvoyer += baladeur.element;
                else
                    aRenvoyer += "," + baladeur.element;
                baladeur = baladeur.suivant;
                cpt++;
                if (cpt == 100) {
                    return "boucle infinie";
                }
            }
            return aRenvoyer + ")";
        } catch (NullPointerException e) {
            return "nullPointerException";
        }
    }

    // pour les tests
    public String queueTete() {
        try {
            String aRenvoyer = "(";
            Noeud baladeur = queue.precedent;
            int cpt = 0;
            while (baladeur != tete) {
                if (cpt == 0)
                    aRenvoyer += baladeur.element;
                else
                    aRenvoyer += "," + baladeur.element;
                baladeur = baladeur.precedent;
                cpt++;
                if (cpt == 100) {
                    return "boucle infinie";
                }
            }
            return aRenvoyer + ")";
        } catch (NullPointerException e) {
            return "nullPointerException";
        }
    }

}

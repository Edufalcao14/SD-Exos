public class DequeImplChaineeAS<E> implements Deque<E> {


    private Noeud tete;
    private Noeud queue;
    private int taille;

    public DequeImplChaineeAS() {
        tete = new Noeud(null);
        queue = new Noeud(null);
        tete.suivant = queue;
        queue.precedent = tete;
        taille = 0;
    }

    public int taille() {
        return this.taille;
    }

    public boolean estVide() {
        return (taille == 0);
    }

    public void ajouterEnPremier(E element) {
        Noeud nouveauNoeud = new Noeud(element, tete, tete.suivant);
        tete.suivant.precedent = nouveauNoeud;
        tete.suivant = nouveauNoeud;
        taille++;
    }

    public E retirerPremier() {

        if (estVide()) throw new DequeVideException("Deque est vide");

        E elementARetirer = tete.suivant.element;

        if (taille == 1) {
            tete.suivant = queue.precedent;
            tete.precedent= null;
            tete=tete.suivant;
        } else {
            tete = tete.suivant;
            tete.precedent = null;
        }

        taille--;

        return elementARetirer;
    }

    public void ajouterEnDernier(E element) {
        Noeud nouveauNoeud = new Noeud(element, tete, tete.suivant);
        queue.element = element;
        queue.suivant= new Noeud(null,queue,queue.suivant);
        queue = queue.suivant;

        taille++;
    }

    public E retirerDernier() throws DequeVideException {
        if (estVide()) throw new DequeVideException("Deque est vide");

        E elementARetirer = queue.precedent.element;

        if (taille == 1) {
            tete.suivant = queue.precedent;
            queue=queue.precedent;
            queue.suivant= null;
        } else {
            queue = queue.precedent;
            queue.suivant = null;
        }
        taille--;
        return elementARetirer;
    }

    public E premier() throws DequeVideException {
        if (estVide()) throw new DequeVideException("");

        return tete.suivant.element;
    }

    public E dernier() throws DequeVideException {

        return queue.precedent.element;
    }


    // A NE PAS MODIFIER --> POUR LES TESTS!!!
    // tete 'a' 'b' 'c' queue : ['a','b','c']
    public DequeImplChaineeAS(Object[] table) {
        if (table == null)
            throw new IllegalArgumentException();
        taille = 0;
        tete = new Noeud(null);
        queue = new Noeud(null);
        tete.suivant = queue;
        queue.precedent = tete;
        if (table.length == 0)
            return;
        for (int i = table.length - 1; i >= 0; i--) {
            this.ajouterTest((E) table[i]);
        }
    }

    // A NE PAS MODIFIER --> POUR LES TESTS!!!
    public String toString() {
        String aRenvoyer = "";
        Noeud baladeur = tete.suivant;
        int cpt = 0;
        while (baladeur != queue) {
            cpt++;
            if (cpt > taille) {
                aRenvoyer = "boucle infinie dans toString(), chainage a verifier";
            }
            aRenvoyer += baladeur.element;
            if (baladeur.suivant != queue)
                aRenvoyer += " ";
            baladeur = baladeur.suivant;
        }
        return aRenvoyer;
    }

    // A NE PAS MODIFIER --> POUR LES TESTS!!!
    public String parcoursInverse() {
        String aRenvoyer = "";
        Noeud baladeur = queue.precedent;
        int cpt = 0;
        while (baladeur != tete) {
            cpt++;
            if (cpt > taille) {
                aRenvoyer = "boucle infinie dans toString(), chainage a verifier";
            }
            aRenvoyer += baladeur.element;

            if (baladeur.precedent != tete)
                aRenvoyer += " ";
            baladeur = baladeur.precedent;
        }
        return aRenvoyer;
    }

    // A NE PAS MODIFIER --> POUR LES TESTS!!!
    public void ajouterTest(E element) {
        Noeud nouveauNoeud = new Noeud(element);
        nouveauNoeud.suivant = tete.suivant;
        nouveauNoeud.precedent = tete;
        tete.suivant.precedent = nouveauNoeud;
        tete.suivant = nouveauNoeud;
        taille++;
    }


    // classe interne
    private class Noeud {
        private E element;
        private Noeud precedent;
        private Noeud suivant;

        private Noeud(E element) {
            this.element = element;
            this.precedent = null;
            this.suivant = null;
        }

        private Noeud(E element, Noeud precedent, Noeud suivant) {
            this.element = element;
            this.precedent = precedent;
            this.suivant = suivant;
        }
    }
}

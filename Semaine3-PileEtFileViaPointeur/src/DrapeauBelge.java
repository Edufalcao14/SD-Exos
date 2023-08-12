
public class DrapeauBelge {

    private NoeudCouleur premierNoir;
    private NoeudCouleur dernierJaune;
    // NE PAS AJOUTER D'AUTRES ATTRIBUTS!!!

    /**
     * construit une chaine contenant 3 noeuds avec les caracteres 'n', 'j' et 'r' (dans cet ordre)
     */
    public DrapeauBelge() {
        premierNoir = new NoeudCouleur('n');
        dernierJaune = new NoeudCouleur('j');
        premierNoir.suivant = dernierJaune;
        dernierJaune.suivant = new NoeudCouleur('r');

    }

    /**
     * ajoute un noeud avec la couleur passee en parametre dans la chaine
     * La chaine doit respecter les couleurs du  drapeau belge : noir/jaune/rouge
     *
     * @param couleur un caractere representant une couleur du drapeau belge : 'n', 'j' ou 'r'
     * @throws IllegalArgumentException si le caractere ne correspond pas a un des 3 caracteres : 'n', 'j' ou 'r'
     */
    public void ajouter(char couleur) {
        if (!(couleur == 'n' || couleur == 'r' || couleur == 'j')) throw new IllegalArgumentException("");
        NoeudCouleur nouveauNoeud = new NoeudCouleur(couleur);
        if (couleur == 'n') {
            nouveauNoeud.suivant = premierNoir;
            premierNoir = nouveauNoeud;
        } else if (couleur == 'j') {
            nouveauNoeud.suivant = dernierJaune.suivant;
            dernierJaune.suivant = nouveauNoeud;
            dernierJaune = nouveauNoeud;
        } else {
            nouveauNoeud.suivant = dernierJaune.suivant;
            dernierJaune.suivant = nouveauNoeud;
        }
    }
    // A NE PAS MODIFIER. VA SERVIR POUR LES TESTS
    public String toString() {
        String drapeau = "";
        NoeudCouleur baladeur = premierNoir;
        int cpt = 0;
        while (baladeur != null) {
            cpt++;
            if (cpt == 100) {
                return "boucle infinie dans toString(), chainage a verifier";
            }
            drapeau += baladeur.couleur;
            baladeur = baladeur.suivant;
        }
        return drapeau;
    }

    private class NoeudCouleur {

        private char couleur;
        private NoeudCouleur suivant;

        private NoeudCouleur(char couleur) {
            this.couleur = couleur;
            this.suivant = null;
        }

        private NoeudCouleur(char couleur, NoeudCouleur suivant) {
            this.couleur = couleur;
            this.suivant = suivant;
        }

    }
}

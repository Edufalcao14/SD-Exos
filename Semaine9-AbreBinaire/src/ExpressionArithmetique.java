import java.util.HashMap;
import java.util.HashSet;
/**
 *
 * @author Sampaio Falcao Eduardo Henrique
 *
 */
public class ExpressionArithmetique extends ArbreDeCaracteres { 
	
	/**
	 * Cree une expression arithmetique a partir d'un arbre de caracteres
	 * @param a
	 */
	public ExpressionArithmetique (ArbreDeCaracteres a) {
		super(a);
	}

	public ExpressionArithmetique (char c) {
		super(c);
	}
	
	public ExpressionArithmetique (char c, ArbreDeCaracteres ag, ArbreDeCaracteres ad) {
		super(c, ag, ad);
	}
	
	
	/**
	 * calcule le nombre d'operations correspondant au type d'operateur passe en parametre que contient l'expression arithmetique
	 * Par ex : exp1 : + --> 1
	 *                 / --> 1
	 *                 ...
	 *          exp3 : + --> 4 
	 * @param operateur l'operateur verifie
	 * @return le nombre d'operations
	 * @throws IllegalArgumentException si le caractere passe en parametre n'est pas un operateur (+,-,*,/)
	 */
	public int nombreOperations(char operateur)  {
		if (operateur != '+' && operateur != '-' && operateur != '*' && operateur != '/') {
			throw new IllegalArgumentException("Le caractère passé en paramètre n'est pas un opérateur valide.");
		}

		return nombreOperations(racine,operateur);
	}
	private int nombreOperations(NoeudCaractere noeudCaractere ,char operateur){
		int count = 0;
		if (noeudCaractere.caractere == operateur) {
			count++;
		}

		if (noeudCaractere.droit != null) {
			count += nombreOperations(noeudCaractere.droit,operateur);
		}

		if (noeudCaractere.gauche != null) {
			count += nombreOperations(noeudCaractere.gauche,operateur);
		}

		return count;
	}
	
	/**
	 * verifie si l'arbre ne contient que des additions
	 * Par ex : exp3 ne contient que des +
	 * @return true si l'expression arithmetique contient uniquement des additions, false sinon
	 */
	public boolean uniquementDesAdditions(){
		return uniquementDesAdditions(racine);
	}
	private boolean uniquementDesAdditions(NoeudCaractere noeudCaractere){
		if (noeudCaractere==null) return true;
		if (noeudCaractere.caractere == '-' || noeudCaractere.caractere=='*' || noeudCaractere.caractere=='/') return false;

		return uniquementDesAdditions(noeudCaractere.gauche) && uniquementDesAdditions(noeudCaractere.droit);

	}
	
	/**
	 * calcule le nombre d'entiers differents contenus dans l'expression arithmetique
	 * Par ex : exp2 contient 3 entiers differents : 1, 4 et 8
	 * @return le nombre d'entiers differents
	 */
	public int nombreEntiersDifferents(){
		// piste de solution:
		// utilisez un ensemble (HashSet<Character>) dans lequel seront places les entiers contenus dans l'arbre
		// Grace a la caracteristique d'unicite d'un ensemble, ceux-ci n'y figureront qu'une fois
		// La taille de l'ensemble obtenu correspondra au nombre recherche
		// TODO
		HashSet<Character> ensemble = new HashSet<Character>();
		nombreEntiersDifferents(racine,ensemble);
		return ensemble.size();
	}
	private void nombreEntiersDifferents(NoeudCaractere node, HashSet<Character> entiers) {
		if (node == null) {
			return;
		}
		if (Character.isDigit(node.caractere)) {
			entiers.add(node.caractere);
		}

		nombreEntiersDifferents(node.gauche, entiers);
		nombreEntiersDifferents(node.droit, entiers);
	}
	/**
	 * calcule la valeur de l'expression stockee dans l'arbre
	 * Par ex : exp1 --> 13
	 * @return le resultat 
	 */
	public double resultat () {	
		// pour obtenir le chiffre : (int)element - '0'; 
		// car l'element est de type char
		// (int)'0' = 48  (int)'1' = 49  (int)'2' = 50 ...  (int)'9' = 57
		// Le cast (int) n'est pas obligatoire
		// TODO
		return resultat(racine);
	}

	private double resultat(NoeudCaractere node) {
		if (node == null) {
			return 0;
		}

		char c = node.caractere;

		if (Character.isDigit(c)) {
			return (int) c - '0';
		} else {
			double gauche = resultat(node.gauche);
			double droit = resultat(node.droit);

			switch (c) {
				case '+':
					return gauche + droit;
				case '-':
					return gauche - droit;
				case '*':
					return gauche * droit;
				case '/':
					return gauche / droit;
				default:
					throw new IllegalArgumentException("Opérateur invalide : " + c);
			}
		}
	}
	/**
	 * renvoie l'expression stockee dans l'arbre en notation infixe
	 * Par exp : exp1 --> ((3-2)+(4*(9/3)))
	 * @return la notation infixe
	 */
	public String notationInfixe () {
		// TODO
		return null;	
	}
	
}


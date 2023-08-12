
/**
 *
 * @author Sampaio Falcao Eduardo Henrique
 *
 */

public class TestPlaqueDeVoiture {
	// Attention, la methode hashCode() renvoie un entier
	// Cet entier pourrait etre negatif --> Math.abs()
	// Cet entier doit correspondre a une liste --> %NBRE_LISTES

	public final static int NBRE_LISTES =2000;

	public static void main (String args[]) {
		int [] table = new int[NBRE_LISTES];
		for (char a = 'A'; a < 'Z'; a++) {
			for (char b = 'A'; b < 'Z' ; b++) {
				for (char c = 'A'; c < 'Z' ; c++) {
					for (int d = 0; d <= 9; d++) {
						for (int e = 0; e <= 9 ; e++) {
							for (int f = 0; f <= 9; f++) {
								String plaque = "1" + a+b+c+d+e+f;

								Voiture voiture = new Voiture(plaque,"");
								int hash = Math.abs(voiture.hashCodeC()%NBRE_LISTES);
								table[hash]++;
								System.out.println(plaque);
							}
						}

					}

				}

			}
		}
		for (int i = 0; i < table.length; i++) {
			System.out.println(table[i]);
		}
	}
}
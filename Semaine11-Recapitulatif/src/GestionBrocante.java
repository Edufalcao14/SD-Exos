import java.util.Scanner;

public class GestionBrocante {

	//private static Scanner scanner = new Scanner(System.in);
	private static  MonScanner scanner = new MonScanner("lancement.txt");
	private static Brocante brocante;

	public static void main(String[] args) {
		System.out.println("**********************");
		System.out.println("gestion d'une brocante");
		System.out.println("**********************");
		System.out.println();

		// configuration de la brocante
		System.out.println("Configuration de la brocante");
		System.out.println("----------------------------");
		System.out.print("Entrez le nombre d'emplacements : ");
		int nombreEmplacements = scanner.nextInt();
		System.out.print("Entrez le nombre de riverains : ");
		int nombreRiverains = scanner.nextInt();
		scanner.nextLine();
		String[] tableRiverains = new String[nombreRiverains];
		for (int i = 0; i < tableRiverains.length; i++) {
			System.out.print("Entrez le nom du riverain " + (i + 1) + " : ");
			String nomRiv  = scanner.nextLine();
			tableRiverains[i] = nomRiv;
		}
		brocante = new Brocante(nombreEmplacements, tableRiverains);
		System.out.println();

		// Phase 1
		System.out.println("Phase 1");
		System.out.println("-------");
		int choix = 0;
		do {
			System.out.println();
			System.out.println("1 -> reserver un emplacement");
			System.out.println("2 -> afficher la brocante");
			System.out.println("3 -> Constuler exposant via nom");
			System.out.println("4 -> Lister tous les exposants");
			System.out.println("5 -> Passer a phase 2");
			System.out.println();
			System.out.print("Votre choix : ");
			choix = scanner.nextInt();
			scanner.nextLine();
			switch (choix) {
				case 1:
					reserverPhase1();
					break;
				case 2:
					afficherTout();
					break;
				case 3:
					getExposants();
					break;
				case 4:
					listerTousExposants();
					break;
			}

		} while (choix >= 1 && choix <= 4);

		brocante.changerPhase();
		System.out.println();
		System.out.println();

		// Phase 2
		System.out.println("Phase 2");
		System.out.println("-------");
		choix = 0;
		do {
			System.out.println();
			System.out.println("1 -> reserver un emplacement");
			System.out.println("2 -> afficher la brocante");
			System.out.println("3 -> afficher Pile Emplacements");
			System.out.println();
			System.out.print("Votre choix : ");
			choix = scanner.nextInt();
			scanner.nextLine();
			switch (choix) {
				case 1:
					reserverPhase2();
					break;
				case 2:
					afficherTout();
					break;
				case 3:
					pileEmplacements();
					break;
			}

		} while (choix >= 1 && choix <= 2);

		System.out.println("Fin de la brocante!");
	}

	private static void reserverPhase1() {
		System.out.print("Entrez le nom : ");
		String nom = scanner.nextLine();

		System.out.print("Entrez l'email : ");
		String email = scanner.nextLine();

		System.out.print("Entrez le numero de l'emplacement : ");
		int numero = scanner.nextInt();
		scanner.nextLine();
		Exposant riverain = new Exposant(nom,email);
		System.out.println("Numero : " + numero);
		try {
			brocante.reserver(riverain,numero);
		}catch (IllegalArgumentException e){
			e.getMessage();
		}
	}

	private static void reserverPhase2() {
		System.out.print("Entrez le nom : ");
		String nom = scanner.nextLine();

		System.out.print("Entrez l'email : ");
		String email = scanner.nextLine();
		Exposant demandeur = new Exposant(nom,email);

		try {
			brocante.changerPhase();
			brocante.attribuerAutomatiquementEmplacement(demandeur);
		}catch (IllegalArgumentException e){
			e.getMessage();
		}
	}
	private static void getExposants() {
		System.out.print("Entrez le nom : ");
		String nom = scanner.nextLine();
		System.out.println("LISTEEE " + brocante.getListeExposant(nom));
		try {
			System.out.println(brocante.consulterExposantViaNom(nom));
		}catch (IllegalArgumentException e){
			e.getMessage();
		}
	}

	private static void afficherTout() {
		System.out.println("Emplacements :") ;
		System.out.println("--------------") ;
		System.out.println() ;
		System.out.println(brocante) ;
		//System.out.println() ;
	}
	private static void tableEmplacements() {
		System.out.println("Table :") ;
		System.out.println("--------------") ;
		System.out.println(brocante.donnerTableEmplacements()) ;
	}
	private static void pileEmplacements() {
		System.out.println("Pile :") ;
		System.out.println("--------------") ;
		System.out.println(brocante.donnerPileEmplacementsLibres()) ;

	}
	private static void listerTousExposants() {
		System.out.println("Liste de tous les exposants :") ;
		System.out.println("--------------") ;
		System.out.println(brocante.tousLesExposants().toString()) ;
	}
}

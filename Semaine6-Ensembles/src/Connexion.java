public class Connexion {
   
	Ensemble<Login> ensembleDesConnectes;
 	// N'AJOUTEZ PAS D'AUTRES ATTRIBUTS!!!
	// N'OUBLIEZ PAS DE COMPLETER LA METHODE HASHCODE() DE LA CLASSE LOGIN
	
	public Connexion(){
		ensembleDesConnectes = new EnsembleTableBooleens<>(999);
	}

	public int nombreDeConnectes(){
		return ensembleDesConnectes.taille();
	}
	public boolean connecter(Login login){
		return ensembleDesConnectes.ajouter(login);
	}
	public boolean deconnecter(Login login){
		return ensembleDesConnectes.enlever(login);
	}
	public boolean estConnecte(Login login){

		return ensembleDesConnectes.contient(login);
	}
	
	

	
}


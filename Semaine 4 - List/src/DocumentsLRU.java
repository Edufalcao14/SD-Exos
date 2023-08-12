import java.util.LinkedList;
/**
 * implementation de l'interface ListeSimple avec une liste simplement chainee
 *
 * @author Sampaio Falcao Eduardo Henrique
 */
public class DocumentsLRU {

	private LinkedList<String> listeLRU;
	
	
	/**
	 * construit une liste de nombreDocuments documents : doc1 doc2 ...
	 * @param nombreDocuments
	 * @throws IllegalArgumentException il faut au moins 1 document
	 */
	public DocumentsLRU(int nombreDocuments){
		listeLRU = new LinkedList<>();
		if (nombreDocuments< 1 ) throw new IllegalArgumentException ("");
		for (int i = 0; i < nombreDocuments; i++) {
			listeLRU.add(i,"doc" + (i +1));
		}
	}

	/**
	 * place le document passe en parametre dans la liste selon le mecanisme LRU
	 * @param le document qui est ouvert
	 * @throws IllegalArgumentException si le document est null ou ""
	 */
	public void ouvrirDocument(String document){
		if (document==null|| document.equals("")) throw new IllegalArgumentException("");
		if (!listeLRU.remove(document)){
			listeLRU.removeLast();
			listeLRU.addFirst(document);
		}else{
			listeLRU.addFirst(document);
		}
	}
	
	
	public String toString(){
		return listeLRU.toString();
	}
	
}

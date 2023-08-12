import java.util.Comparator;

public class CompareteurClient implements Comparator<ClientEnAttente> {


    @Override
    public int compare(ClientEnAttente o1, ClientEnAttente o2) {
        if (o1 != o2) {
            return o2.getClient().getPriorite() - o1.getClient().getPriorite();
            //return Integer.compare(o1.getClient().getPriorite(), o2.getClient().getPriorite());
        } else {
            return o2.getNumArrivee() - o1.getNumArrivee();
        }
    }
}

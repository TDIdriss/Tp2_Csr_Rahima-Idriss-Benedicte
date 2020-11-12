
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

class SystemeEmprunt {

    /* Constantes */
    static final int NB_SITES = 5;
    static final int MAX_CLIENTS = 100;

    /* Attributs */
    private Site[] sites = new Site[NB_SITES];
    private Client[] clients = new Client[MAX_CLIENTS];
    private Camion camion = null;

    /* Constructeur du systeme d'emprunt */
    SystemeEmprunt() {

        /* Instanciation des sites */
        for (int i = 0; i < NB_SITES; i++) {
            sites[i] = new Site(i);
        }

        Random rand = new Random();
        /* Instanciation des clients */
        for (int i = 0; i < MAX_CLIENTS; i++) {
            int siteDepId = rand.nextInt(NB_SITES);
            int siteArrId = rand.nextInt(NB_SITES);
            clients[i] = new Client(sites[siteDepId], sites[siteArrId]);
        }

        /* Instanciation du camion */
        camion = new Camion(sites);
    }

    void fonctionner() {
        camion.start();
        for (int i = 0; i < MAX_CLIENTS; i++) {
            clients[i].start();
        }
        for (int i = 0; i < MAX_CLIENTS; i++) {
            try {
                clients[i].join();
            } catch (InterruptedException ex) {
            }
        }
        
    }

    /* Point d'entree du programme */
    public static void main(String[] args) {

        new SystemeEmprunt().fonctionner();
        System.out.println("FIN");

    }

} // class SystemeEmprunt


class Site {

    /* Constantes associees au site */
    static final int STOCK_INIT = 5;
    static final int STOCK_MAX = 10;
    static final int BORNE_SUP = 8;
    static final int BORNE_INF = 2;

    private int nbSite;
    private int nbVeloPresent = STOCK_INIT;

    public Site(int nbSite) {
        this.nbSite = nbSite;
    }

    synchronized void emprunter() {
        while (nbVeloPresent <= 0) {
            try {
                wait();
            } catch (InterruptedException ex) {
            }
        }
        nbVeloPresent--;
        notifyAll();
        System.out.println("Le client " + Thread.currentThread().getName()
                + " emprunte du site numero " + nbSite + ": " + nbVeloPresent + " velos.");

    }

    synchronized void retourner() {
        while (nbVeloPresent >= STOCK_MAX) {
            try {
                wait();
               // System.out.println("Je suis reveille pour retourner");
            } catch (InterruptedException ex) {
            }
        }
        nbVeloPresent++;
        notifyAll();
        System.out.println("Le client " + Thread.currentThread().getName()
                + " retourne le velo au site numero " + nbSite + ": " + nbVeloPresent + " velo.");
    }

    synchronized void  equilibrer(Camion camion) {
        
        if (nbVeloPresent > BORNE_SUP) {
            
            camion.setTaille(camion.getTaille() + (nbVeloPresent - STOCK_INIT));
            nbVeloPresent = STOCK_INIT;
            
        } else if (nbVeloPresent < BORNE_INF) {
            if (camion.getTaille() - (STOCK_INIT - nbVeloPresent) > 0) {
                camion.setTaille(camion.getTaille() - (STOCK_INIT - nbVeloPresent));
                nbVeloPresent = STOCK_INIT;
            } else {
                nbVeloPresent = nbVeloPresent + camion.getTaille();
                camion.setTaille(0);
            }
        }
        System.out.println("Le camion à equilibre le site " + nbSite + " velo = " + nbVeloPresent + " taille = " + camion.getTaille());
        notifyAll();
    }

    @Override
    public String toString() {
        return "Le site " + nbSite + " contient " + nbVeloPresent + " velo(s).";
    }
}

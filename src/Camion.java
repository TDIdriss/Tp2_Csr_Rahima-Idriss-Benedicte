
class Camion extends Thread {

    private Site sites[];
    private int taille;
    private boolean running;

    public Camion(Site[] sites) {
        this.sites = sites;
        taille = 60;
        this.setDaemon(true);
    }

    @Override
    public void run() {
        while (true) {
            for (int i = 0; i < sites.length; i++) {
                sites[i].equilibrer(this);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
            }
        }
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public int getTaille() {
        return taille;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

}

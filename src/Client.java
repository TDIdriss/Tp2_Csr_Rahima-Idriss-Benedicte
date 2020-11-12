class Client extends Thread {

    private Site A;
    private Site B;

    public Client(Site A, Site B) {
        this.A = A;
        this.B = B;
    }

    @Override
    public void run() {
        A.emprunter();
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
        }
        B.retourner();
    }
}

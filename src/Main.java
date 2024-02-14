abstract  class Poupee{
    private int taille;
    private boolean ouverte;
    abstract void ouvrir();
    abstract void fermer();
    public Poupee(int taille) {
        this.taille = taille;
        this.ouverte = false;
    }
    public int getTaille() { return taille;
    }

    public boolean estOuverte() {
        return ouverte;
    }

    protected void setOuverte(boolean ouverte) {
        this.ouverte = ouverte;
    }

    abstract void placerDans(Poupee p);

     abstract void sortirDe(Poupee p);
}
class PoupeeRusse extends  Poupee{
    public String couleur;
     private PoupeeRusse contenueDans;
    public PoupeeRusse(int taille) {
        super(taille);
        this.couleur=couleur;
        this.contenueDans = null;
    }

    @Override
    void ouvrir() {
        System.out.print("la poupee "+getTaille()+"est ouverte");
        setOuverte(true);
    }

    @Override
    void fermer() {

    }

    @Override
    void placerDans(Poupee p) {

    }

    @Override
    void sortirDe(Poupee p) {

    }
}
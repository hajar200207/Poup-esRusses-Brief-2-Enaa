class PoupeeRusse extends Poupee {
    private String couleur;
    private PoupeeRusse contenueDans;

    public PoupeeRusse(int taille,String couleur) {
        super(taille);
        this.couleur = couleur; // Couleur par défaut
        this.contenueDans = null;
    }

    @Override
    public void ouvrir() {
        System.out.println("La poupée russe de taille " + getTaille() + " est ouverte.");
        setOuverte(true);
    }
    public String colorer(String couleur){
        return couleur;
    }
    @Override
    public void fermer() {
        System.out.println("La poupée russe de taille " + getTaille() + " est fermée.");
        setOuverte(false);
    }

    @Override
    public void placerDans(Poupee p) {
        if (p instanceof PoupeeRusse) {
            PoupeeRusse poup = (PoupeeRusse) p;
            if (this.getTaille() < poup.getTaille()) {
                System.out.println("La poupée russe de taille " + this.getTaille() + " est placée dans une autre poupée de taille " + poup.getTaille());
                this.contenueDans = poup;
            } else {
                System.out.println("Impossible de placer la poupée russe de taille " + this.getTaille() + " dans une poupée de taille " + poup.getTaille() + ".");
            }
        } else {
            System.out.println("Impossible de placer la poupée russe dans une poupée de type différent.");
        }
    }

    @Override
    public void sortirDe(Poupee p) {
        if (p instanceof PoupeeRusse && this.contenueDans == p) {
            PoupeeRusse poup = (PoupeeRusse) p;
            System.out.println("La poupée russe de taille " + this.getTaille() + " est sortie de la poupée de taille " + poup.getTaille());
            this.contenueDans = null;
        } else {
            System.out.println("La poupée russe n'est pas contenue dans la poupée spécifiée.");
        }
    }
    public void afficher() {
        java.lang.String etat = estOuverte() ? "ouverte" : "fermée";
        String couleurAffichee = ("ouverte".equals(etat)) ? "\u001B[31mRED\u001B[0m" : "\u001B[33myellow\u001B[0m";
        System.out.println("Poupée russe de taille " + getTaille() + " et de couleur "+ colorer(couleur) + " après de  etat " + etat+ " sera  de couleur la poupee est " +  couleurAffichee  );
    }
}


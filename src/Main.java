
import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    private static ArrayList<Action> actions = new ArrayList<>();

    // Méthode pour ajouter une action à l'enregistrement
    private static void addAction(String action) {
        actions.add(new Action(action));
    }
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_BLUE = "\u001B[34m";


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(ANSI_WHITE +"*************************** application console Java simulant les poupées russes***************************");
        System.out.println("***************************************************************************************************************"+ANSI_RESET);
        System.out.println(ANSI_CYAN+" =*=*=*=*=Entrez le nombre de poupées russes à utiliser =*=*=*=*= "+ANSI_RESET);
        int nombrePoupees = scanner.nextInt();

        PoupeeRusse[] poupees = new PoupeeRusse[nombrePoupees];
        for (int i = 0; i < nombrePoupees; i++) {
            System.out.println(ANSI_GREEN+"---Entrez la taille de la poupée " + (i + 1) + " :--- "+ANSI_RESET);
            int taille = scanner.nextInt();
            System.out.println(ANSI_GREEN+"---Entrez la couleur de la poupée " + (i + 1) + " :--- "+ANSI_RESET);
            scanner = new Scanner(System.in);
            String couleur = scanner.nextLine();
            poupees[i] = new PoupeeRusse(taille,couleur);
        }

        int choix;
        do {
            System.out.println(ANSI_WHITE+"************************************************************************************");
            System.out.println("  |                               Menu:                                         |");
            System.out.println("  |                     1. Ouvrir une poupée                                    |");
            System.out.println("  |                     2. Fermer une poupée                                    |");
            System.out.println("  |                     3. Placer une poupée dans une autre poupée              |");
            System.out.println("  |                     4. Sortir une poupée d'une autre poupée                 |");
            System.out.println("  |                     5. Afficher l'état d'une poupée                         |");
            System.out.println("  |                     6. Afficher l'état de toutes les poupées                |");
            System.out.println("  |                     7. Afficher l'enregistrement des actions                |");
            System.out.println("  |                     0. Quitter                                              |");
            System.out.println("************************************************************************************"+ANSI_RESET);
            System.out.print("Choix: ");
            choix = scanner.nextInt();
            switch (choix) {
                case 1:
                    System.out.print("Entrez la taille de la poupée à ouvrir : ");
                    int tailleOuvrir = scanner.nextInt();
                    ouvrirPoupeeParTaille(tailleOuvrir, poupees);
                    break;
                case 2:
                    System.out.print("Entrez la taille de la poupée à fermer : ");
                    int tailleFermer = scanner.nextInt();
                    fermerPoupeeParTaille(tailleFermer, poupees);
                    break;
                case 3:
                    System.out.print("Entrez la taille de la poupée à placer : ");
                    int taillePlacer = scanner.nextInt();
                    System.out.print("Entrez la taille de la poupée dans laquelle placer : ");
                    int tailleConteneur = scanner.nextInt();
                    placerDansPoupeeParTaille(taillePlacer, tailleConteneur, poupees);
                    break;
                case 4:
                    System.out.print("Entrez la taille de la poupée à sortir : ");
                    int tailleSortir = scanner.nextInt();
                    System.out.print("Entrez la taille de la poupée de laquelle sortir : ");
                    int tailleContenant = scanner.nextInt();
                    sortirDePoupeeParTaille(tailleSortir, tailleContenant, poupees);
                    break;
                case 5:
                    System.out.print("Entrez la taille de la poupée à afficher : ");
                    int tailleAfficher = scanner.nextInt();
                    afficherEtatPoupee(tailleAfficher, poupees);
                    break;
                case 6:
                    afficherEtatToutesPoupees(poupees);
                    break;
                case 7:
                    afficherEnregistrementActions();
                    break;
                case 0:
                    System.out.println("Fin du programme.");
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
                    break;
            }

        } while (choix != 0);

        scanner.close();
    }

    public static void ouvrirPoupeeParTaille(int taillePoupee, PoupeeRusse[] poupees) {
        for (PoupeeRusse poup : poupees) {
            if (poup.getTaille() == taillePoupee) {
                poup.ouvrir();
                addAction("Ouverture de la poupée de taille " + taillePoupee);
                return;
            }
        }
        System.out.println("Poupée de taille " + taillePoupee + " non trouvée.");
    }

    public static void fermerPoupeeParTaille(int taillePoupee, PoupeeRusse[] poupees) {
        for (PoupeeRusse poup : poupees) {
            if (poup.getTaille() == taillePoupee) {
                poup.fermer();
                addAction("Fermeture de la poupée de taille " + taillePoupee);
                return;
            }
        }
        System.out.println("Poupée de taille " + taillePoupee + " non trouvée.");
    }

    public static void placerDansPoupeeParTaille(int taillePoupee, int tailleConteneur, PoupeeRusse[] poupees) {
        PoupeeRusse poupeeAPlacer = null;
        PoupeeRusse poupeeConteneur = null;

        for (PoupeeRusse poup : poupees) {
            if (poup.getTaille() == taillePoupee) {
                poupeeAPlacer = poup;
            } else if (poup.getTaille() == tailleConteneur) {
                poupeeConteneur = poup;
            }
        }

        if (poupeeAPlacer != null && poupeeConteneur != null) {
            if (poupeeConteneur.estOuverte()) {
                poupeeAPlacer.placerDans(poupeeConteneur);
            } else {
                System.out.println("Impossible de placer la poupée : assurez-vous que les poupées sont toutes ouvertes.");
            }
        } else {
            System.out.println("Une ou plusieurs poupées de taille non trouvées.");
        }
        addAction("Placement de la poupée de taille " + taillePoupee + " dans la poupée de taille " + tailleConteneur);
    }

    public static void sortirDePoupeeParTaille(int taillePoupee, int tailleContenant, PoupeeRusse[] poupees) {
        PoupeeRusse poupeeASortir = null;
        PoupeeRusse poupeeContenant = null;

        for (PoupeeRusse poup : poupees) {
            if (poup.getTaille() == taillePoupee) {
                poupeeASortir = poup;
            } else if (poup.getTaille() == tailleContenant) {
                poupeeContenant = poup;
            }
        }

        if (poupeeASortir != null && poupeeContenant != null) {
            if (poupeeASortir.estOuverte() && poupeeContenant.estOuverte()) {
                poupeeASortir.sortirDe(poupeeContenant);
            } else {
                System.out.println("Impossible de sortir la poupée : assurez-vous que les poupées sont toutes ouvertes.");
            }
        } else {
            System.out.println("Une ou plusieurs poupées de taille non trouvées.");
        }
        addAction("Sortie de la poupée de taille " + taillePoupee + " de la poupée de taille " + tailleContenant);
    }


    public static void afficherEtatPoupee(int taillePoupee, PoupeeRusse[] poupees) {
        for (PoupeeRusse poup : poupees) {
            if (poup.getTaille() == taillePoupee) {
                poup.afficher();
                return;
            }
        }
        System.out.println("Poupée de taille " + taillePoupee + " non trouvée.");
        addAction("Afficheurer l' etat de poupe  " + taillePoupee);

    }

    public static void afficherEtatToutesPoupees(PoupeeRusse[] poupees) {
        for (PoupeeRusse poup : poupees) {
            poup.afficher();
        }
        addAction("Afficheurer les etats des poupees  " );

    }


    public static void afficherEnregistrementActions() {
        System.out.println("Enregistrement des actions : ");
        for (Action action : actions) {
            System.out.println(action);
        }
    }
}

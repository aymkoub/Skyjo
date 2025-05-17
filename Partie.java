import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

public class Partie {
    // Attributs de la classe Partie
    private int __nbJoueurs;
    private ArrayList<Joueur> __joueurs;
    private Stack<Carte> __pioche;
    private Stack<Carte> __defausse;

    // Constructeur de la classe Partie
    public Partie(int nbJoueurs) {
        this.__nbJoueurs = nbJoueurs;
        this.__joueurs = new ArrayList<>();
        this.__pioche = new Stack<>();
        this.__defausse = new Stack<>();
        remplirPioche();
    }

    // Getter de la classe Partie
    public int getNbJoueurs() {
        return this.__nbJoueurs;
    }

    // Méthode de distribution des cartes aux joueurs en début de partie
    public void distribuer() {
        for (int lin = 0; lin < 3; lin++) {
            for (int coln = 0; coln < 4; coln++) {
                for (int j = 0; j < this.getNbJoueurs(); j++) {
                    this.__joueurs.get(j).getGrille().getColonne(coln).add(this.__pioche.pop());
                }
            }
        }
        Carte debutDefausse = this.__pioche.pop();
        debutDefausse.changerVisibilite();
        this.__defausse.push(debutDefausse);
    }

    // Méthode de remplissage de la pioche de carte
    public void remplirPioche() {
        // Ajout des -2
        for (int i = 0; i < 5; i++) {
            __pioche.push(new Carte(-2));
        }
        // Ajout des 0
        for (int i = 0; i < 15; i++) {
            __pioche.push(new Carte(0));
        }
        // Ajout des autres cartes de -1 à 12
        for (int i = 0; i < 10; i++) {
            for (int k = -1; k <= 12; k++) {
                // On ignore 0puisqu'on a déjà ajouté ces cartes
                if (k != 0) {
                    __pioche.push(new Carte(k));
                }
            }
        }
        Collections.shuffle(__pioche);
    }

    public void demarrer() {
        int nbJoueurs = this.getNbJoueurs();
        Scanner scanner = new Scanner(System.in);

        // Création des joueurs
        for (int i = 0; i < nbJoueurs; i++) {
            System.out.println("Joueur " + (i + 1) + " choisit un nom !");
            String nom = scanner.next();
            this.__joueurs.add(new Joueur(nom));
        }
        this.remplirPioche();
        this.distribuer();
        // Choix du premier joueur
        int sommeMax = -4; // Somme minimale possible avec 2 cartes
        int numJoueur = 1;
        int sommeJoueur = 0;
        for (int j = 0; j < nbJoueurs; j++) {
            System.out.println("Joueur " + (j + 1) + " retourne 2 cartes !");
            for (int n = 0; n < 2; n++) {
                System.out.print("Sélectionner la colonne de la carte à retourner (1-4): ");
                int coln = scanner.nextInt() - 1;
                System.out.print("Sélectionner la ligne de la carte à retourner (1-3): ");
                int lin = scanner.nextInt() - 1;

                this.__joueurs.get(j).getGrille().getColonne(coln).get(lin).changerVisibilite();
            }
            // Calcul de la somme des 2 cartes retournées
            sommeJoueur = this.__joueurs.get(j).getGrille().getSommePoints();
            if (sommeMax < sommeJoueur) {
                sommeMax = sommeJoueur;
                numJoueur = j;
            }
        }
        System.out.println("Joueur " + numJoueur + " commence la partie !");

        boolean finDePartie = false;
        while (!finDePartie) {
            for (int tourjoueur = numJoueur; tourjoueur < nbJoueurs; tourjoueur++) {
                // Déroulé du tour
                // Choisir de piocher ou non dans la défausse
                System.out.println("Voulez-vous piocher dans la défausse ? (d/*) : ");
                Carte carteJouee;
                if (scanner.next() == "d") {
                    carteJouee = this.__joueurs.get(tourjoueur).piocher(__defausse);
                    // Remplacer une carte puis la mettre dans la défausse
                    System.out.print("Sélectionner la colonne de la carte à remplacer (1-4): ");
                    int coln = scanner.nextInt() - 1;
                    System.out.print("Sélectionner la ligne de la carte à remplacer (1-3): ");
                    int lin = scanner.nextInt() - 1;

                    Carte carteRemplacee = this.__joueurs.get(tourjoueur).getGrille().getColonne(coln).get(lin);
                    if (!carteRemplacee.estVisible()) {
                        carteRemplacee.changerVisibilite();
                    }
                    this.__joueurs.get(tourjoueur).getGrille().remplacerCarte(carteJouee, coln, lin);
                    this.__defausse.push(carteRemplacee);
                }
                // Piocher une carte de la pioche
                else {
                    carteJouee = this.__joueurs.get(tourjoueur).piocher(__pioche);
                    // Choisir de garder cette carte ou non
                    System.out.println("Voulez-vous garder " + carteJouee.getValeur() + " ? (y/*) : ");
                    // Si oui la placer dans sa grille, remplacer une carte en la mettant dans la
                    // défausse
                    if (scanner.next() == "y") {
                        System.out.print("Sélectionner la colonne de la carte à remplacer (1-4): ");
                        int coln = scanner.nextInt() - 1;
                        System.out.print("Sélectionner la ligne de la carte à remplacer (1-3): ");
                        int lin = scanner.nextInt() - 1;

                        Carte carteRemplacee = this.__joueurs.get(tourjoueur).getGrille().getColonne(coln).get(lin);
                        if (!carteRemplacee.estVisible()) {
                            carteRemplacee.changerVisibilite();
                        }
                        this.__joueurs.get(tourjoueur).getGrille().remplacerCarte(carteJouee, coln, lin);
                        this.__defausse.push(carteRemplacee);
                    } else {
                        // sinon l'envoyer dans la défausse et révéler une carte cachée de la grille
                        System.out.print("Sélectionner la colonne de la carte à retourner (1-4): ");
                        int coln = scanner.nextInt() - 1;
                        System.out.print("Sélectionner la ligne de la carte à retourner (1-3): ");
                        int lin = scanner.nextInt() - 1;

                        boolean uneCarteAeteRetournee = false;
                        while (!uneCarteAeteRetournee) {
                            if (!this.__joueurs.get(tourjoueur).getGrille().getColonne(coln).get(lin).estVisible()) {
                                Carte carteAretourner = this.__joueurs.get(tourjoueur).getGrille().getColonne(coln)
                                        .get(lin);
                                this.__joueurs.get(tourjoueur).getGrille().retournerCarte(carteAretourner);
                                uneCarteAeteRetournee = true;
                            }
                        }
                    }
                }
                // vérifier si une colonne est à retirer ou non
            }
            for (int tourrestant = 0; tourrestant < numJoueur; tourrestant++) {
                // Déroulé du tour
                // Choisir de piocher ou non dans la défausse
                // Piocher une carte de la pioche
                // Choisir de garder cette carte ou non
                // Si oui la placer dans sa grille, remplacer une carte en la mettant dans la
                // défausse
                // sinon l'envoyer dans la défausse et révéler une carte cachée de la grille
                // vérifier si une colonne est à retirer ou non
            }
        }
        scanner.close();
    }
}

import java.util.ArrayList;
import java.util.Collections;
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
                    this.__joueurs.get(j).getGrille().getGrille().get(coln).add(this.__pioche.pop());
                }
            }
        }
        Carte debutDefausse = this.__pioche.pop();
        debutDefausse.retoruner();
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
}

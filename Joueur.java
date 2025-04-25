import java.util.Stack;

public class Joueur {
    // Attributs de la classe Joueur
    private String nom;
    private GrilleJoueur grille;

    // Constructeurs de la classe Joueur
    public Joueur() {
        this.nom = "joueur";
        grille = new GrilleJoueur();
    }

    public Joueur(String nom) {
        this.nom = nom;
        grille = new GrilleJoueur();
    }

    // Getters de la classe Joueur
    public String getNom() {
        return this.nom;
    }

    public GrilleJoueur getGrille() {
        return this.grille;
    }

    // Setter de la classe Joueur
    public void setNom(String nom) {
        this.nom = nom;
    }

    public Carte piocher(Stack<Carte> pioche) {
        Carte cartePiochee = pioche.pop();
        return cartePiochee;
    }
}

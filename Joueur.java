import java.util.Stack;

public class Joueur {
    private String nom;
    private GrilleJoueur grille;

    public Joueur(){
        this.nom = "joueur";
        grille = new GrilleJoueur();
    }

    public Joueur(String nom){
        this.nom = nom;
        grille = new GrilleJoueur();
    }

    public String getNom(){
        return this.nom;
    }

    public GrilleJoueur getGrille(){
        return this.grille;
    }

    public void setNom(String nom){
        this.nom = nom;
    }

    public Carte piocher(Stack<Carte> pioche){
        Carte cartePiochee = pioche.pop();
        return cartePiochee;
    }
}

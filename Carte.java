public class Carte {
    // Attributs de la classe Carte
    private int __valeur;
    private boolean __faceCachee;

    // Constructeur de la classe Carte
    public Carte(int val) {
        this.__valeur = val;
        this.__faceCachee = true;
    }

    // Getter de la classe Carte
    public int getValeur() {
        if (this.estVisible()) {
            return this.__valeur;
        } else {
            return 13; // Valeur inexistante dans le jeu
        }
    }

    public boolean estVisible() {
        return this.__faceCachee;
    }

    // Setter de la classe Carte
    public void setValeur(int val) {
        this.__valeur = val;
    }

    // Méthode pour retourner une carte, quand elle est révélée dans la grille ou
    // envoyée dans la défausse
    public void changerVisibilite() {
        this.__faceCachee = !this.__faceCachee;
    }
}

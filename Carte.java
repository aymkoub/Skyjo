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
        return this.__valeur;
    }

    // Setter de la classe Carte
    public void setValeur(int val) {
        this.__valeur = val;
    }

    // Méthode pour retourner une carte, quand elle est révélée dans la grille ou
    // envoyée dans la défausse
    public void retoruner() {
        this.__faceCachee = !this.__faceCachee;
    }
}

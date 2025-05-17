import java.util.ArrayList;

public class GrilleJoueur {
    private int sommePoints = 0;

    private ArrayList<Carte> colonne1 = new ArrayList<Carte>();
    private ArrayList<Carte> colonne2 = new ArrayList<Carte>();
    private ArrayList<Carte> colonne3 = new ArrayList<Carte>();
    private ArrayList<Carte> colonne4 = new ArrayList<Carte>();

    private ArrayList<ArrayList<Carte>> grille = new ArrayList<ArrayList<Carte>>();

    private ArrayList<GrilleObserver> observers = new ArrayList<GrilleObserver>();

    public GrilleJoueur() {
        grille.add(colonne1);
        grille.add(colonne2);
        grille.add(colonne3);
        grille.add(colonne4);
    }

    public int getSommePoints() {
        return this.sommePoints;
    }

    public ArrayList<ArrayList<Carte>> getGrille() {
        return this.grille;
    }

    public ArrayList<Carte> getColonne(int index) {
        return grille.get(index);
    }

    public void setSomme(int somme) {
        this.sommePoints = somme;
    }

    public void retirerColonne(int index) {
        grille.remove(index);
    }

    public void remplacerCarte(Carte nouvelleCarte, int colonne, int position) {
        this.getColonne(colonne).set(position, nouvelleCarte);
        notifyObservers();
    }

    public void retournerCarte(Carte carte) {
        carte.changerVisibilite();
        notifyObservers();
    }

    public void printGrille() {
        for (int lin = 0; lin < 3; lin++) {
            for (int coln = 0; coln < 4; coln++) {
                System.out.print(this.getColonne(coln).get(lin).getValeur() + " ");
            }
            System.out.println();
        }
    }

    public void calculSomme() {
        int somme = 0;
        for (int lin = 0; lin < 3; lin++) {
            for (int coln = 0; coln < 4; coln++) {
                if (this.getColonne(coln).get(lin).estVisible()) {
                    somme += this.getColonne(coln).get(lin).getValeur();
                }
            }
        }
        this.setSomme(somme);
    }

    public boolean estComplete() {
        for (int lin = 0; lin < 3; lin++) {
            for (int coln = 0; coln < 4; coln++) {
                if (!this.getColonne(coln).get(lin).estVisible()) {
                    return false;
                }
            }
        }
        return true;
    }

    public void addObserver(GrilleObserver obs) {
        observers.add(obs);
    }

    public void notifyObservers() {
        for (GrilleObserver obs : observers) {
            obs.update();
        }
    }
}

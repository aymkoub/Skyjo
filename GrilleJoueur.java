import java.util.ArrayList;

public class GrilleJoueur {
    private int sommePoints = 0;

    private Carte[] grille = new Carte[12];

    // private ArrayList<GrilleObserver> observers = new ArrayList<GrilleObserver>();

    public int getSommePoints(){
        return this.sommePoints;
    }

    public Carte[] getGrille(){
        return this.grille;
    }

    public void setGrille(Carte[] grille){
        this.grille = grille;
    }

    public ArrayList<Carte> getColonne(int index){
        ArrayList<Carte> colonne = new ArrayList<>();
        for(int i=index; i<grille.length; i+=(grille.length/3)){
            colonne.add(grille[i]);
        }
        return colonne;
    }
    
    public void retirerColonne(int index){
        Carte[] newGrille = new Carte[grille.length - 3];
        for(int j=0; j<grille.length; j++){
            int k=0;
            if (j%(grille.length/3) != index) {
                newGrille[k] = grille[j];
                k++;
            }
        }
        this.setGrille(newGrille);
    }
}

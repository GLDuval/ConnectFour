package ca.cours5b5.gabriellevesqueduval.donnees.partie;

import ca.cours5b5.gabriellevesqueduval.donnees.Donnees;
import ca.cours5b5.gabriellevesqueduval.enumerations.ECouleur;
import ca.cours5b5.gabriellevesqueduval.enumerations.ETailleGrille;
import ca.cours5b5.gabriellevesqueduval.global.GLog;

public abstract class DPartie extends Donnees {

    private DGrille grille;
    private ETailleGrille taille;
    private ECouleur couleur;

    public DPartie(){
        GLog.appel(this);

    }

    public DPartie(DGrille grille, ETailleGrille taille, ECouleur couleur){
        GLog.appel(this);
        this.grille = grille;
        this.taille = taille;
        this.couleur = couleur;
    }

    public DGrille getGrille() {
        GLog.appel(this);
        return grille;
    }

    public ECouleur getCouleur() {
        GLog.appel(this);
        return couleur;
    }

    public ETailleGrille getTaille() {
        GLog.appel(this);
        return taille;
    }

    public void setCouleur(ECouleur couleur) {
        GLog.appel(this);
        this.couleur = couleur;
    }

    public void setGrille(DGrille grille) {
        GLog.appel(this);
        this.grille = grille;
    }

    public void setTaille(ETailleGrille taille) {
        GLog.appel(this);
        this.taille = taille;
    }

}

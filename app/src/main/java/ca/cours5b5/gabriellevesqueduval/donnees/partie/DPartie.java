package ca.cours5b5.gabriellevesqueduval.donnees.partie;

import ca.cours5b5.gabriellevesqueduval.donnees.Donnees;
import ca.cours5b5.gabriellevesqueduval.enumerations.ECouleur;
import ca.cours5b5.gabriellevesqueduval.enumerations.ETailleGrille;
import ca.cours5b5.gabriellevesqueduval.global.GLog;

public abstract class DPartie extends Donnees {

    private DGrille grille;
    private ETailleGrille taille = ETailleGrille.grande;
    private ECouleur couleur = ECouleur.bleu;

    public DPartie(){
        GLog.appel(this);
        this.grille = new DGrille(taille.getHauteur(), taille.getLargeur());

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

    public void setCouleur(ECouleur couleurJoue) {
        GLog.appel(this);
        if(couleurJoue == ECouleur.bleu){
            this.couleur = ECouleur.rouge;
        }else{
            this.couleur = ECouleur.bleu;
        }
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

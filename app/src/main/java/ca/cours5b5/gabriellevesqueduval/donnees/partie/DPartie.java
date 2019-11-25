package ca.cours5b5.gabriellevesqueduval.donnees.partie;

import ca.cours5b5.gabriellevesqueduval.donnees.Donnees;
import ca.cours5b5.gabriellevesqueduval.enumerations.ECouleur;
import ca.cours5b5.gabriellevesqueduval.enumerations.ETailleGrille;
import ca.cours5b5.gabriellevesqueduval.global.GLog;

public abstract class DPartie extends Donnees<DPartie> {

    private DGrille grille;
    private ETailleGrille taille;
    private ECouleur couleur = ECouleur.bleu;

    public DPartie(){
        GLog.appel(this);
        this.grille = new DGrille();

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

    public void prochaineCouleur() {
        GLog.appel(this);
        if(this.couleur == ECouleur.bleu){
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
        grille.initialiserGrille(taille.getHauteur(), taille.getLargeur());
    }

    @Override
    public void copierDonnees(DPartie dPartie){
        GLog.appel(this);
        super.copierDonnees(dPartie);
        this.grille = dPartie.getGrille();
        this.couleur = dPartie.getCouleur();
        this.taille = dPartie.getTaille();
    }

}

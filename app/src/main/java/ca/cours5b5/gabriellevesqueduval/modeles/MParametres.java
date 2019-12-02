package ca.cours5b5.gabriellevesqueduval.modeles;

import ca.cours5b5.gabriellevesqueduval.commandes.CContinuerPartie;
import ca.cours5b5.gabriellevesqueduval.commandes.CTailleGrille;
import ca.cours5b5.gabriellevesqueduval.donnees.DParametres;
import ca.cours5b5.gabriellevesqueduval.enumerations.ETailleGrille;
import ca.cours5b5.gabriellevesqueduval.global.GLog;
import ca.cours5b5.gabriellevesqueduval.vues.pages.PParametres;

public class MParametres extends  Modele<DParametres, PParametres> {

    private ETailleGrille taille;
    private boolean continuer;

    public MParametres(DParametres donnees, PParametres page) {

        super(donnees, page);
        GLog.appel(this);
    }

    @Override
    protected void initialiserCommandes() {
        GLog.appel(this);
        taille = donnees.getTaille();
        CContinuerPartie.initialiser(this);
        CTailleGrille.initialiser(this);
    }

    @Override
    public void reagirChangement() {

    }

    public void changementTaille(ETailleGrille taille){

        GLog.appel(this);

        donnees.setTaille(taille);
        this.taille = taille;
        super.notifierModificationLocale();
        page.rafraichirAffichage(donnees);
    }

    public void changementContinuer(boolean continuer){
        GLog.appel(this);
        donnees.setContinuer(continuer);
        this.continuer = continuer;
        super.notifierModificationLocale();
        page.rafraichirAffichage(donnees);
    }

    public ETailleGrille getTaille(){
        GLog.appel(this);
        return taille;
    }

    public boolean isContinuer(){
        GLog.appel(this);
        return continuer;
    }
}

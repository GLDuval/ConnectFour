package ca.cours5b5.gabriellevesqueduval.modeles;

import ca.cours5b5.gabriellevesqueduval.donnees.DParametres;
import ca.cours5b5.gabriellevesqueduval.enumerations.ETailleGrille;
import ca.cours5b5.gabriellevesqueduval.global.GLog;
import ca.cours5b5.gabriellevesqueduval.vues.pages.PParametres;

public class MParametres extends  Modele<DParametres, PParametres> {

    public MParametres(DParametres donnees, PParametres page) {

        super(donnees, page);
        GLog.appel(this);
    }

    public void changementTaille(ETailleGrille taille){

        GLog.appel(this);

        donnees.setTaille(taille);
    }

    public void changementContinuer(boolean continuer){
        GLog.appel(this);
        donnees.setContinuer(continuer);
    }
}

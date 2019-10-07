package ca.cours5b5.gabriellevesqueduval.modeles;

import ca.cours5b5.gabriellevesqueduval.donnees.DParametres;
import ca.cours5b5.gabriellevesqueduval.enumerations.ETailleGrille;
import ca.cours5b5.gabriellevesqueduval.vues.pages.PParametres;

public class MParametres extends  Modele<DParametres, PParametres> {

    public MParametres(DParametres donnees, PParametres page) {
        super(donnees, page);
    }

    public void changementTaille(ETailleGrille taille){

        donnees.setTaille(taille);
    }

    public void changementContinuer(boolean continuer){
        donnees.setContinuer(continuer);
    }
}

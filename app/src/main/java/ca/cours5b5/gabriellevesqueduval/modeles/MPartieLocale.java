package ca.cours5b5.gabriellevesqueduval.modeles;

import ca.cours5b5.gabriellevesqueduval.donnees.partie.DPartie;
import ca.cours5b5.gabriellevesqueduval.global.GLog;
import ca.cours5b5.gabriellevesqueduval.vues.pages.PPartie;

public class MPartieLocale extends MPartie{

    public MPartieLocale(DPartie donnees, PPartie page) {

        super(donnees, page);
        GLog.appel(this);
    }


}

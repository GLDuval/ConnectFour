package ca.cours5b5.gabriellevesqueduval.modeles;

import ca.cours5b5.gabriellevesqueduval.donnees.Donnees;
import ca.cours5b5.gabriellevesqueduval.global.GLog;
import ca.cours5b5.gabriellevesqueduval.vues.pages.PageAvecModeles;

public abstract class Modele <D extends Donnees, P extends PageAvecModeles> {

    protected D donnees;
    protected P page;

    public Modele(D donnees, P page){
        GLog.appel(this);

        this.donnees = donnees;
        this.page = page;
        page.installerCapteurs(this);
    }
}

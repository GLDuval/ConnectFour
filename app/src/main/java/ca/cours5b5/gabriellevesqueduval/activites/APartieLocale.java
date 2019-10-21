package ca.cours5b5.gabriellevesqueduval.activites;

import ca.cours5b5.gabriellevesqueduval.R;
import ca.cours5b5.gabriellevesqueduval.donnees.partie.DPartieLocale;
import ca.cours5b5.gabriellevesqueduval.global.GLog;
import ca.cours5b5.gabriellevesqueduval.modeles.MPartieLocale;
import ca.cours5b5.gabriellevesqueduval.vues.pages.PPartieLocale;

public class APartieLocale extends ActiviteAvecModeles <DPartieLocale, MPartieLocale, PPartieLocale> {
    @Override
    protected int getLayoutId() {
        GLog.appel(this);
        return R.layout.page_partie_locale;
    }

    @Override
    protected int getIdPage() {
        return 0;
    }

    @Override
    protected Class<DPartieLocale> getClassDonnees() {
        return null;
    }

    @Override
    protected MPartieLocale creerModele(DPartieLocale donnees, PPartieLocale page) {
        return null;
    }

}

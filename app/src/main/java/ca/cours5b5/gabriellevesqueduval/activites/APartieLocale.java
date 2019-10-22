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
        GLog.appel(this);
        return R.id.pPartieLocale;
    }

    @Override
    protected Class<DPartieLocale> getClassDonnees() {
        GLog.appel(this);
        return DPartieLocale.class;
    }

    @Override
    protected MPartieLocale creerModele(DPartieLocale donnees, PPartieLocale page) {
        GLog.appel(this);
        return new MPartieLocale(donnees, page);
    }

}

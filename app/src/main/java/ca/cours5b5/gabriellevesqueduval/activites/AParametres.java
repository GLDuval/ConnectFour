package ca.cours5b5.gabriellevesqueduval.activites;


import ca.cours5b5.gabriellevesqueduval.R;
import ca.cours5b5.gabriellevesqueduval.donnees.DParametres;
import ca.cours5b5.gabriellevesqueduval.global.GLog;
import ca.cours5b5.gabriellevesqueduval.vues.pages.PParametres;

public class AParametres extends ActiviteAvecDonnees<DParametres, PParametres> {


    @Override
    protected int getLayoutId() {
        GLog.appel(this);
        return R.layout.page_parametres;
    }

    @Override
    protected int getIdPage() {
        GLog.appel(this);
        return R.id.pParametres;
    }

    @Override
    protected DParametres creerDonnees() {
        GLog.appel(this);
        DParametres parametres = new DParametres();
        return parametres;
    }
}

package ca.cours5b5.gabriellevesqueduval.activites;

import ca.cours5b5.gabriellevesqueduval.R;
import ca.cours5b5.gabriellevesqueduval.commandes.CQuitterActivite;
import ca.cours5b5.gabriellevesqueduval.donnees.partie.DPartieLocale;
import ca.cours5b5.gabriellevesqueduval.donnees.partie.DPartieReseau;
import ca.cours5b5.gabriellevesqueduval.global.GLog;
import ca.cours5b5.gabriellevesqueduval.modeles.MPartieLocale;
import ca.cours5b5.gabriellevesqueduval.modeles.MPartieReseau;
import ca.cours5b5.gabriellevesqueduval.vues.pages.PPartieLocale;
import ca.cours5b5.gabriellevesqueduval.vues.pages.PPartieReseau;

public class APartieReseau extends ActiviteAvecModeles <DPartieReseau, MPartieReseau, PPartieReseau> {
    @Override
    protected int getLayoutId() {
        GLog.appel(this);
        return R.layout.page_partie_reseau;
    }

    @Override
    protected int getIdPage() {
        GLog.appel(this);
        return R.id.pPartieReseau;
    }

    @Override
    protected Class<DPartieReseau> getClassDonnees() {
        GLog.appel(this);
        return DPartieReseau.class;
    }

    @Override
    protected MPartieReseau creerModele(DPartieReseau donnees, PPartieReseau page) {
        GLog.appel(this);
        CQuitterActivite.initialiser(this);
        return new MPartieReseau(donnees, page);
    }

}

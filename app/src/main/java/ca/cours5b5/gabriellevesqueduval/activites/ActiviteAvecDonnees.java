package ca.cours5b5.gabriellevesqueduval.activites;

import android.os.Bundle;

import ca.cours5b5.gabriellevesqueduval.donnees.DParametres;
import ca.cours5b5.gabriellevesqueduval.donnees.Donnees;
import ca.cours5b5.gabriellevesqueduval.global.GLog;
import ca.cours5b5.gabriellevesqueduval.vues.pages.PageAvecDonnees;

public abstract class ActiviteAvecDonnees<D extends Donnees, P extends PageAvecDonnees> extends Activite {

    private D donnees;
    private P page;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        GLog.appel(this);
        super.onCreate(savedInstanceState);
        initialiserDonneesPage(savedInstanceState);
    }

    private void initialiserDonneesPage(Bundle etat){
        GLog.appel(this);
        donnees = creerDonnees();
        initialiserPage(donnees);
    }

    private void initialiserPage(D donnees){

        GLog.appel(this);
        page = recupererPage();
        page.creerAffichage(donnees);

    }

    private P recupererPage(){

        GLog.appel(this);
        int pageID = getIdPage();
        page = findViewById(pageID);
        return page;
    }

    @Override
    protected void onResume(){
        GLog.appel(this);
        super.onResume();
        page.rafraichirAffichage(donnees);
    }

    protected abstract int getIdPage();

    protected abstract D creerDonnees();

}

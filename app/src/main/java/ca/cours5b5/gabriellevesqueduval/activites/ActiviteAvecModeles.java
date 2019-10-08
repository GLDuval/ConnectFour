package ca.cours5b5.gabriellevesqueduval.activites;

import android.os.Bundle;

import ca.cours5b5.gabriellevesqueduval.donnees.Donnees;
import ca.cours5b5.gabriellevesqueduval.donnees.EntrepotDeDonnees;
import ca.cours5b5.gabriellevesqueduval.global.GLog;
import ca.cours5b5.gabriellevesqueduval.modeles.Modele;
import ca.cours5b5.gabriellevesqueduval.vues.pages.PageAvecModeles;

public abstract class ActiviteAvecModeles<D extends Donnees, M extends Modele, P extends PageAvecModeles> extends Activite {

    private D donnees;
    private P page;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        GLog.appel(this);
        super.onCreate(savedInstanceState);
        initialiserDonneesPageModele(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        EntrepotDeDonnees.sauvegarderDonnees(donnees, outState);
    }


    private void initialiserDonneesPageModele(Bundle etat){
        GLog.appel(this);
        donnees = recupererDonnees(etat);
        initialiserPageModele(donnees);
    }

    private void initialiserPageModele(D donnees){

        GLog.appel(this);
        page = recupererPage();
        page.creerAffichage(donnees);
        creerModele(donnees, page);

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

    private D recupererDonnees(Bundle etat){
        return EntrepotDeDonnees.obtenirDonnees(getClassDonnees(), etat);
    }

    protected abstract int getIdPage();

    protected abstract Class<D> getClassDonnees();

    protected abstract void creerModele(D donnees, P page);

}

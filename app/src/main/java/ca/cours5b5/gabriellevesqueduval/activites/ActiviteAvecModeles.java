package ca.cours5b5.gabriellevesqueduval.activites;

import android.os.Bundle;

import java.io.File;

import ca.cours5b5.gabriellevesqueduval.donnees.Donnees;
import ca.cours5b5.gabriellevesqueduval.donnees.EntrepotDeDonnees;
import ca.cours5b5.gabriellevesqueduval.donnees.RetourDonnees;
import ca.cours5b5.gabriellevesqueduval.global.GLog;
import ca.cours5b5.gabriellevesqueduval.modeles.Modele;
import ca.cours5b5.gabriellevesqueduval.vues.pages.PageAvecModeles;

public abstract class ActiviteAvecModeles<D extends Donnees, M extends Modele, P extends PageAvecModeles> extends Activite {

    protected D donnees;
    protected P page;
    private M modele;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        GLog.appel(this);
        super.onCreate(savedInstanceState);
        page = recupererPage();
        obtenirDonneesPuisInitialiserModelePage();

    }

    private void creerAffichage(){
        GLog.appel(this);
        page.creerAffichage(donnees);
    }

    private void rafraichirAffichage(){
        GLog.appel(this);
        page.rafraichirAffichage(donnees);
    }

    private void initialiserPage(){
        GLog.appel(this);
        creerAffichage();
        rafraichirAffichage();
    }

    private void memoriserDonneesPuisInitialiserModelePage(D donneesObtenues){
        GLog.appel(this);

        donnees = donneesObtenues;

        initialiserPage();

        modele = creerModele(donnees, page);

    }

    private void obtenirDonneesPuisInitialiserModelePage(){
        GLog.appel(this);
        EntrepotDeDonnees.obtenirDonnees(getClassDonnees(), new RetourDonnees<D>() {
            @Override
            public void recevoirDonnees(D donnees) {
                GLog.appel(this);
                memoriserDonneesPuisInitialiserModelePage(donnees);
            }
        });
    }

    private P recupererPage(){
        GLog.appel(this);
        int pageID = getIdPage();
        page = findViewById(pageID);
        return page;
    }


    @Override
    protected void onPause(){
        GLog.appel(this);
        super.onPause();
        EntrepotDeDonnees.sauvegarderDonneesSurServeur(donnees);
    }


    protected abstract int getIdPage();

    protected abstract Class<D> getClassDonnees();

    protected abstract M creerModele(D donnees, P page);

}

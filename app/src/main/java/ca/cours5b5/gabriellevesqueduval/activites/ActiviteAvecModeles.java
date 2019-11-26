package ca.cours5b5.gabriellevesqueduval.activites;

import android.os.Bundle;

import ca.cours5b5.gabriellevesqueduval.donnees.Donnees;
import ca.cours5b5.gabriellevesqueduval.donnees.EntrepotDeDonnees;
import ca.cours5b5.gabriellevesqueduval.donnees.Observateur;
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
        observerDonneesEtGereModelePage();

    }

    private void creerAffichage(){
        GLog.appel(this);
        page.creerAffichage(donnees);
        page.creerCommandes();
        page.installerCapteurs();
    }

    private void rafraichirAffichage(){
        GLog.appel(this);
        page.raffraichirCommandes();
        page.rafraichirAffichage(donnees);

    }

    private void initialiserPage(){
        GLog.appel(this);
        creerAffichage();
        rafraichirAffichage();
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

    private void detruireAncienModele(){
        GLog.appel(this);
        if(modele != null){
            modele.detruire();
        }
    }

    private void  memoriserDonneesPuisGererModelePage(D donneesRecues){
        GLog.appel(this);
        detruireAncienModele();
        donnees = donneesRecues;
        modele = creerModele(donnees, page);
        initialiserPage();
    }

    private void reagirDonneesDuServeur(D donneesDuServeur){
        GLog.appel(this);
        donnees.copierDonnees(donneesDuServeur);
        rafraichirAffichage();
    }

    protected void observerDonneesEtGereModelePage(){
        GLog.appel(this);
        EntrepotDeDonnees.observerDonnees(getClassDonnees(), new Observateur<D>() {
            @Override
            protected void nouveau(D donnees) {
                memoriserDonneesPuisGererModelePage(donnees);
            }

            @Override
            protected void donneesDuServeur(D donnees) {
                reagirDonneesDuServeur(donnees);
            }
        });
    }



    protected abstract int getIdPage();

    protected abstract Class<D> getClassDonnees();

    protected abstract M creerModele(D donnees, P page);

}

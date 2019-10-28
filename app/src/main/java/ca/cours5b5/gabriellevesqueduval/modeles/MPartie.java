package ca.cours5b5.gabriellevesqueduval.modeles;

import android.os.Environment;

import java.util.ArrayList;

import ca.cours5b5.gabriellevesqueduval.donnees.DParametres;
import ca.cours5b5.gabriellevesqueduval.donnees.EntrepotDeDonnees;
import ca.cours5b5.gabriellevesqueduval.donnees.partie.DCase;
import ca.cours5b5.gabriellevesqueduval.donnees.partie.DPartie;
import ca.cours5b5.gabriellevesqueduval.enumerations.ECouleur;
import ca.cours5b5.gabriellevesqueduval.global.GLog;
import ca.cours5b5.gabriellevesqueduval.vues.pages.PPartie;

public abstract class MPartie extends Modele<DPartie, PPartie> {

    public MPartie(DPartie donnees, PPartie page) {

        super(donnees, page);
        GLog.appel(this);
    }

    public void jetonJoue(int indiceCol){
        GLog.appel(this);
        changerCouleurCase(indiceCol);
        page.rafraichirAffichage(donnees);

    }

    public void changerCouleurCase(int indiceCol){
        GLog.appel(this);

        ArrayList<DCase> cases = donnees.getGrille().getColonnes().get(indiceCol).getCases();

        for(int i=cases.size()-1; i>-1; i--){

            if(cases.get(i).getCouleur() == ECouleur.gris){
                cases.get(i).setCouleur(donnees.getCouleur());
                donnees.setCouleur(cases.get(i).getCouleur());
                break;
            }
        }

    }




}

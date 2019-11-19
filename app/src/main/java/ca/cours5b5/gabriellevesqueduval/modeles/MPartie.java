package ca.cours5b5.gabriellevesqueduval.modeles;


import java.util.ArrayList;
import java.util.Random;


import ca.cours5b5.gabriellevesqueduval.R;
import ca.cours5b5.gabriellevesqueduval.commandes.CCoupIci;
import ca.cours5b5.gabriellevesqueduval.commandes.CMessagePuisCommande;
import ca.cours5b5.gabriellevesqueduval.commandes.CQuitterActivite;
import ca.cours5b5.gabriellevesqueduval.donnees.partie.DCase;
import ca.cours5b5.gabriellevesqueduval.donnees.partie.DPartie;
import ca.cours5b5.gabriellevesqueduval.enumerations.ECouleur;
import ca.cours5b5.gabriellevesqueduval.global.GLog;
import ca.cours5b5.gabriellevesqueduval.vues.pages.PPartie;

public abstract class MPartie extends Modele<DPartie, PPartie> {

    int indiceCol;

    public MPartie(DPartie donnees, PPartie page) {

        super(donnees, page);
        GLog.appel(this);
    }

    public void jetonJoue(int indiceCol){
        GLog.appel(this);
        changerCouleurCase(indiceCol);
        page.rafraichirAffichage(donnees);
        this.indiceCol = indiceCol;
        jouerCoupIci(indiceCol);

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

    public boolean siColonnePleine(int indiceCol){
        GLog.appel(this);

        ArrayList<DCase> cases = donnees.getGrille().getColonnes().get(indiceCol).getCases();

        return cases.get(0).getCouleur() == ECouleur.gris;
    }

    protected void initialiserCommandes(){
        GLog.appel(this);
        CCoupIci.initialiser(this);
        CMessagePuisCommande.initialiser(page);
    }

    private void reagirPartieGagnee(){
        GLog.appel(this);
        CMessagePuisCommande cMessagePuisCommande = new CMessagePuisCommande(getIdMessageAuGagnant(), new CQuitterActivite());

        cMessagePuisCommande.executer();

    }

    private int getIdMessageAuGagnant(){
        GLog.appel(this);
        int idMessage;
        if(donnees.getCouleur() == ECouleur.bleu){
            idMessage = R.string.messageGagnantRouge;
        }else {
            idMessage = R.string.messageGagnantBleu;
        }

        return idMessage;
    }

    private boolean siPartieGagnee(){
        GLog.appel(this);
        int random = new Random().nextInt(4)+1;
        return random == 3;
    }

    private void testerSiPartieGagnee(){
        GLog.appel(this);
        if(siPartieGagnee()){
            reagirPartieGagnee();
        }
    }

    private void jouerCoupIci(int indiceCol){
        GLog.appel(this);
        testerSiPartieGagnee();
    }




}

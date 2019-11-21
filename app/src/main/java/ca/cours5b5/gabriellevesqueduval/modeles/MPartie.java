package ca.cours5b5.gabriellevesqueduval.modeles;


import java.util.ArrayList;
import java.util.Random;


import ca.cours5b5.gabriellevesqueduval.R;
import ca.cours5b5.gabriellevesqueduval.commandes.CCoupIci;
import ca.cours5b5.gabriellevesqueduval.commandes.CMessagePuisCommande;
import ca.cours5b5.gabriellevesqueduval.commandes.CQuitterActivite;
import ca.cours5b5.gabriellevesqueduval.donnees.partie.DCase;
import ca.cours5b5.gabriellevesqueduval.donnees.partie.DColonne;
import ca.cours5b5.gabriellevesqueduval.donnees.partie.DPartie;
import ca.cours5b5.gabriellevesqueduval.enumerations.ECouleur;
import ca.cours5b5.gabriellevesqueduval.global.GConstantes;
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
        int indiceCase = changerCouleurCase(indiceCol);
        page.rafraichirAffichage(donnees);
        this.indiceCol = indiceCol;
        testerSiPartieGagnee(indiceCol, indiceCase);

    }

    public int changerCouleurCase(int indiceCol){
        GLog.appel(this);

        ArrayList<DCase> cases = donnees.getGrille().getColonnes().get(indiceCol).getCases();

        for(int i=cases.size()-1; i>-1; i--){

            if(cases.get(i).getCouleur() == ECouleur.gris){
                cases.get(i).setCouleur(donnees.getCouleur());
                donnees.setCouleur(cases.get(i).getCouleur());
                return i;
            }
        }
        return 0;
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

    private boolean siPartieGagnee(int indiceCol, int indiceCase){
        GLog.appel(this);
        boolean gagnee = false;

        return suiteHorizontale(indiceCase);

    }

    private void testerSiPartieGagnee(int indiceCol, int indiceCase){
        GLog.appel(this);
        if(siPartieGagnee(indiceCol, indiceCase)){
            reagirPartieGagnee();
        }
    }

    private boolean suiteVerticale(){
        GLog.appel(this);
        ArrayList<DCase> cases = donnees.getGrille().getColonnes().get(indiceCol).getCases();
        ECouleur couleur = donnees.getCouleur() == ECouleur.bleu ? ECouleur.rouge : ECouleur.bleu;
        int compteur=0;
        for(int i=0; i<cases.size(); i++){
            if(cases.get(i).getCouleur() == couleur){
                compteur++;
            }else{
                compteur=0;
            }

            if(compteur>=GConstantes.SUITE_GAGNANTE){
                break;
            }
        }
        GLog.valeurs(compteur);
        return compteur >= GConstantes.SUITE_GAGNANTE;
    }

    private boolean suiteHorizontale(int indiceCase){
        GLog.appel(this);
        ECouleur couleur = donnees.getCouleur() == ECouleur.bleu ? ECouleur.rouge : ECouleur.bleu;
        int compteur=0;
        for(int i=0; i< donnees.getGrille().getColonnes().size(); i++){
            if(donnees.getGrille().getColonnes().get(i).getCases().get(indiceCase).getCouleur() == couleur){
                compteur++;
            }else{
                compteur=0;
            }

            if(compteur>=GConstantes.SUITE_GAGNANTE){
                break;
            }
            GLog.valeurs(compteur);
        }
        return compteur >= GConstantes.SUITE_GAGNANTE;
    }





}

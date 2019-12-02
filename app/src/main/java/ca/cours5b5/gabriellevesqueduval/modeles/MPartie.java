package ca.cours5b5.gabriellevesqueduval.modeles;


import java.util.ArrayList;


import ca.cours5b5.gabriellevesqueduval.R;
import ca.cours5b5.gabriellevesqueduval.commandes.CCoupIci;
import ca.cours5b5.gabriellevesqueduval.commandes.CMessagePuisCommande;
import ca.cours5b5.gabriellevesqueduval.commandes.CQuitterActivite;
import ca.cours5b5.gabriellevesqueduval.donnees.partie.DCase;
import ca.cours5b5.gabriellevesqueduval.donnees.partie.DPartie;
import ca.cours5b5.gabriellevesqueduval.enumerations.ECouleur;
import ca.cours5b5.gabriellevesqueduval.global.GConstantes;
import ca.cours5b5.gabriellevesqueduval.global.GLog;
import ca.cours5b5.gabriellevesqueduval.vues.pages.PPartie;

public abstract class MPartie extends Modele<DPartie, PPartie> {


    public MPartie(DPartie donnees, PPartie page) {

        super(donnees, page);
        GLog.appel(this);
    }

    public void jetonJoue(int indiceCol){
        GLog.appel(this);

        int indiceCaseJouee = changerCouleurCase(indiceCol);

        donnees.setIndiceCaseJoue(indiceCaseJouee);
        donnees.setIndiceColJoue(indiceCol);

        donnees.prochaineCouleur();
        super.notifierModificationLocale();

    }


    private int changerCouleurCase(int indiceCol){
        GLog.appel(this);

        ArrayList<DCase> cases = donnees.getGrille().getColonnes().get(indiceCol).getCases();

        for(int i=cases.size()-1; i>-1; i--){

            if(cases.get(i).getCouleur() == ECouleur.gris){
                cases.get(i).setCouleur(donnees.getCouleur());
                return i;
            }
        }
        return 0;
    }

    public boolean siExecutable(int indiceCol){
        GLog.appel(this);

        boolean siPlein = false;

        ArrayList<DCase> cases = donnees.getGrille().getColonnes().get(indiceCol).getCases();

        siPlein = !cases.isEmpty() && cases.get(0).getCouleur() == ECouleur.gris;


        return siPlein;
    }

    @Override
    protected void initialiserCommandes(){
        GLog.appel(this);
        CCoupIci.initialiser(this);
        CMessagePuisCommande.initialiser(page);
    }

    @Override
    public void reagirChangement(){
        GLog.appel(this);
        testerSiPartieGagnee();
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

        if(suiteVerticale(indiceCol) || suiteHorizontale(indiceCase) || suiteDiagonaleDroite(indiceCol, indiceCase) || suiteDiagonaleGauche(indiceCol, indiceCase)){
            gagnee =true;
        }

        return gagnee;

    }

    public void testerSiPartieGagnee(){
        GLog.appel(this);
        if(siPartieGagnee(donnees.getIndiceColJoue(), donnees.getIndiceCaseJoue())){
            reagirPartieGagnee();
        }
    }

    private boolean suiteVerticale(int indiceCol){
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

            if(compteur==GConstantes.SUITE_GAGNANTE){
                break;
            }
        }
        return compteur == GConstantes.SUITE_GAGNANTE;
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

            if(compteur == GConstantes.SUITE_GAGNANTE){
                break;
            }
        }
        return compteur == GConstantes.SUITE_GAGNANTE;
    }


    private boolean suiteDiagonaleGauche(int indiceCol, int indiceCase){
        GLog.appel(this);

        ECouleur couleur = donnees.getCouleur() == ECouleur.bleu ? ECouleur.rouge : ECouleur.bleu;
        int compteur=0;

        //Boucle pour trouver les coordonnées de départ (le plus en haut à gauche en diagonal)
        while (indiceCol > 0 && indiceCase > 0){
            indiceCase--;
            indiceCol--;
        }

        for(int i=indiceCol; i<donnees.getGrille().getColonnes().size(); i++){
            if(donnees.getGrille().getColonnes().get(i).getCases().get(indiceCase).getCouleur() == couleur){
                compteur++;
            }else{
                compteur=0;
            }

            if(compteur == GConstantes.SUITE_GAGNANTE){
                break;
            }else if(indiceCase<donnees.getGrille().getColonnes().get(i).getCases().size()-1){
                indiceCase++;
            }else{
                break;
            }

        }
        return compteur == GConstantes.SUITE_GAGNANTE;
    }

    private boolean suiteDiagonaleDroite(int indiceCol, int indiceCase){
        GLog.appel(this);

        ECouleur couleur = donnees.getCouleur() == ECouleur.bleu ? ECouleur.rouge : ECouleur.bleu;
        int compteur=0;

        //Boucle pour trouver les coordonnées de départ (le plus en haut à droite en diagonal)
        while (indiceCol < donnees.getGrille().getColonnes().size()-1 && indiceCase > 0){
            indiceCase--;
            indiceCol++;
        }

        for(int i=indiceCol; i>=0; i--){
            if(donnees.getGrille().getColonnes().get(i).getCases().get(indiceCase).getCouleur() == couleur){
                compteur++;
            }else{
                compteur=0;
            }

            if(compteur == GConstantes.SUITE_GAGNANTE){
                break;
            }else if(indiceCase < donnees.getGrille().getColonnes().get(i).getCases().size()-1){
                indiceCase++;
            }else{
                break;
            }

        }
        return compteur == GConstantes.SUITE_GAGNANTE;
    }





}

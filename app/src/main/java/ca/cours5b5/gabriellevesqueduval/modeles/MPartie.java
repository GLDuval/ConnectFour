package ca.cours5b5.gabriellevesqueduval.modeles;

import ca.cours5b5.gabriellevesqueduval.donnees.partie.DColonne;
import ca.cours5b5.gabriellevesqueduval.donnees.partie.DGrille;
import ca.cours5b5.gabriellevesqueduval.donnees.partie.DPartie;
import ca.cours5b5.gabriellevesqueduval.enumerations.ECouleur;
import ca.cours5b5.gabriellevesqueduval.global.GLog;
import ca.cours5b5.gabriellevesqueduval.vues.pages.PPartie;

public abstract class MPartie extends Modele<DPartie, PPartie> {

    public MPartie(DPartie donnees, PPartie page) {

        super(donnees, page);
        GLog.appel(this);
    }

    public void jetonJoue(ECouleur couleur, int indiceCol, int indiceCase){
        GLog.appel(this);
        donnees.setCouleur(couleur);
        ECouleur couleurCase = couleur.equals(ECouleur.bleu) ? ECouleur.rouge : ECouleur.bleu;
        changerCouleurCase(couleurCase, indiceCol, indiceCase);

    }

    public void changerCouleurCase(ECouleur couleurCase,int indiceCol, int indiceCase){
        donnees.getGrille().getColonnes().get(indiceCol).getCases().get(indiceCase).setCouleur(couleurCase);
    }

    public void initialiserGrille(int hauteur, int largeur){

        if(donnees.getGrille() == null){
            DGrille grille = new DGrille();

            for(int i=0; i<largeur; i++){
                DColonne colonne = new DColonne();
                colonne.creerColonne(hauteur);
                grille.getColonnes().add(colonne);
            }

            donnees.setGrille(grille);
        }






    }




}

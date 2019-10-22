package ca.cours5b5.gabriellevesqueduval.vues.pages;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import androidx.core.content.ContextCompat;


import java.util.ArrayList;

import ca.cours5b5.gabriellevesqueduval.R;
import ca.cours5b5.gabriellevesqueduval.donnees.partie.DCase;
import ca.cours5b5.gabriellevesqueduval.donnees.partie.DColonne;
import ca.cours5b5.gabriellevesqueduval.donnees.partie.DPartie;
import ca.cours5b5.gabriellevesqueduval.enumerations.ECouleur;
import ca.cours5b5.gabriellevesqueduval.enumerations.ETailleGrille;
import ca.cours5b5.gabriellevesqueduval.global.GLog;
import ca.cours5b5.gabriellevesqueduval.modeles.MPartie;
import ca.cours5b5.gabriellevesqueduval.vues.controles.VCase;
import ca.cours5b5.gabriellevesqueduval.vues.controles.VColonne;
import ca.cours5b5.gabriellevesqueduval.vues.controles.VEntete;
import ca.cours5b5.gabriellevesqueduval.vues.controles.VGrille;


public abstract class PPartie extends PageAvecModeles<DPartie, MPartie> {

    private VGrille grille;
    private TextView textViewNom1;
    private TextView textViewNom2;
    private int hauteur;
    private int largeur;
    public ECouleur couleur = ECouleur.bleu;

    public PPartie(Context context) {
        super(context);
        GLog.appel(this);
    }

    public PPartie(Context context, AttributeSet attrs) {
        super(context, attrs);
        GLog.appel(this);
    }

    public PPartie(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        GLog.appel(this);
    }

    @Override
    protected void recupererControles(){
        GLog.appel(this);

        grille = this.findViewById(R.id.grille);
        textViewNom1 = this.findViewById(R.id.textViewNom1);
        textViewNom2 = this.findViewById(R.id.textViewNom2);


        largeur = ETailleGrille.moyenne.getLargeur();
        hauteur = ETailleGrille.moyenne.getHauteur();

        grille.creerGrille(hauteur, largeur);


    }

    @Override
    public void creerAffichage(DPartie donnees) {
        GLog.appel(this);
        afficherLesDonnees(donnees);
    }

    @Override
    public void rafraichirAffichage(DPartie donnees) {
        GLog.appel(this);
        afficherLesDonnees(donnees);
    }

    @Override
    public void installerCapteurs(final MPartie modele) {
        GLog.appel(this);
        modele.initialiserGrille(hauteur, largeur);
        for (final VColonne colonne : grille.getListeColonnes()) {
            VEntete enTete = colonne.getEnTete();
            enTete.setOnClickListener(new OnClickListener() {
                int indiceCase = largeur-1;
                @Override
                public void onClick(View view) {
                    GLog.appel(this);

                    couleur = ajouterCouleur(colonne.getCases(), couleur);

                    modele.jetonJoue(couleur, colonne.getIndice(), indiceCase);
                    if(indiceCase>0){
                        indiceCase--;
                    }

                }
            });
        }

    }

    private ECouleur ajouterCouleur(VCase[] cases, ECouleur couleur){
        GLog.appel(this);
        for(int i=cases.length-1; i>-1; i--){
            ColorDrawable couleurDraw = (ColorDrawable)cases[i].getBackground();
            if(couleurDraw.getColor() == ContextCompat.getColor(getContext(), R.color.colorFond)){
                if(couleur == ECouleur.bleu){
                    cases[i].setBackgroundColor(ContextCompat.getColor(getContext(), R.color.bleu));
                    couleur = ECouleur.rouge;
                }else{
                    cases[i].setBackgroundColor(ContextCompat.getColor(getContext(), R.color.rouge));
                    couleur = ECouleur.bleu;
                }
                break;
            }
        }
        return couleur;

    }

    public void afficherLesDonnees(DPartie donnees){
        GLog.appel(this);
        if(donnees.getGrille() != null){
            ArrayList<DColonne> colonnes = donnees.getGrille().getColonnes();
            for (int i=0; i<colonnes.size(); i++) {
                ArrayList<DCase> cases= colonnes.get(i).getCases();
                for(int j=0; j<cases.size(); j++){
                    couleur = cases.get(j).getCouleur();
                    if(couleur == ECouleur.bleu){
                        grille.getListeColonnes().get(i).getCases()[j].setBackgroundColor(ContextCompat.getColor(getContext(), R.color.bleu));
                    }else if(couleur == ECouleur.rouge){
                        grille.getListeColonnes().get(i).getCases()[j].setBackgroundColor(ContextCompat.getColor(getContext(), R.color.rouge));
                    }else{
                        grille.getListeColonnes().get(i).getCases()[j].setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorFond));
                    }
                }
            }
        }


    }
}

package ca.cours5b5.gabriellevesqueduval.vues.pages;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import androidx.core.content.ContextCompat;


import java.util.ArrayList;

import ca.cours5b5.gabriellevesqueduval.R;
import ca.cours5b5.gabriellevesqueduval.donnees.DParametres;
import ca.cours5b5.gabriellevesqueduval.donnees.EntrepotDeDonnees;
import ca.cours5b5.gabriellevesqueduval.donnees.partie.DCase;
import ca.cours5b5.gabriellevesqueduval.donnees.partie.DColonne;
import ca.cours5b5.gabriellevesqueduval.donnees.partie.DPartie;
import ca.cours5b5.gabriellevesqueduval.enumerations.ECouleur;
import ca.cours5b5.gabriellevesqueduval.global.GLog;
import ca.cours5b5.gabriellevesqueduval.modeles.MPartie;
import ca.cours5b5.gabriellevesqueduval.vues.controles.VColonne;
import ca.cours5b5.gabriellevesqueduval.vues.controles.VEntete;
import ca.cours5b5.gabriellevesqueduval.vues.controles.VGrille;


public abstract class PPartie extends PageAvecModeles<DPartie, MPartie> {

    private VGrille grille;
    private TextView textViewNom1;
    private TextView textViewNom2;
    private Context context;

    public PPartie(Context context) {
        super(context);
        GLog.appel(this);
        this.context = context;
    }

    public PPartie(Context context, AttributeSet attrs) {
        super(context, attrs);
        GLog.appel(this);
        this.context = context;
    }

    public PPartie(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        GLog.appel(this);
        this.context = context;
    }

    @Override
    protected void recupererControles(){
        GLog.appel(this);

        grille = this.findViewById(R.id.grille);
        textViewNom1 = this.findViewById(R.id.textViewNom1);
        textViewNom2 = this.findViewById(R.id.textViewNom2);


    }

    @Override
    public void creerAffichage(DPartie donnees) {
        GLog.appel(this);

        DParametres dParametres = EntrepotDeDonnees.obtenirDonnees(DParametres.class, null, context.getFilesDir());

        donnees.setTaille(dParametres.getTaille());

        grille.creerGrille(donnees.getTaille().getHauteur(), donnees.getTaille().getLargeur());

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
        for (final VColonne colonne : grille.getListeColonnes()) {

            VEntete enTete = colonne.getEnTete();

            enTete.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    GLog.appel(this);

                    modele.jetonJoue(colonne.getIndice());

                }
            });
        }

    }

    private void afficherLesDonnees(DPartie donnees){
        GLog.appel(this);

        int backgroundColor;

        ArrayList<DColonne> colonnes = donnees.getGrille().getColonnes();


        for (int i=0; i<colonnes.size(); i++) {

            ArrayList<DCase> cases = colonnes.get(i).getCases();

            for(int j=0; j<cases.size(); j++){

                ECouleur couleur = cases.get(j).getCouleur();

                if(couleur == ECouleur.bleu){
                    backgroundColor = ContextCompat.getColor(getContext(), R.color.bleu);
                }else if(couleur == ECouleur.rouge){
                    backgroundColor = ContextCompat.getColor(getContext(), R.color.rouge);
                }else{
                    backgroundColor = ContextCompat.getColor(getContext(), R.color.colorFond);
                }

                grille.getListeColonnes().get(i).getCases()[j].setBackgroundColor(backgroundColor);

            }
        }



    }
}

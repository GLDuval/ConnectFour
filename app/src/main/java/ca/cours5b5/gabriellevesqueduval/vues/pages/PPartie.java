package ca.cours5b5.gabriellevesqueduval.vues.pages;

import android.content.Context;
import android.util.AttributeSet;
import java.util.Random;

import ca.cours5b5.gabriellevesqueduval.R;
import ca.cours5b5.gabriellevesqueduval.global.GLog;
import ca.cours5b5.gabriellevesqueduval.vues.controles.VGrille;


public abstract class PPartie extends Page {

    VGrille grille;

    public PPartie(Context context) {
        super(context);
    }

    public PPartie(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PPartie(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void recupererControles(){
        GLog.appel(this);
        //Random random = new Random();
        grille = this.findViewById(R.id.grille);
        int largeur = 7;
        int hauteur = 7;
        grille.creerGrille(hauteur, largeur);


    }
}

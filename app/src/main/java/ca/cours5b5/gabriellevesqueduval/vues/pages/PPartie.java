package ca.cours5b5.gabriellevesqueduval.vues.pages;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import java.util.Random;

import ca.cours5b5.gabriellevesqueduval.R;
import ca.cours5b5.gabriellevesqueduval.global.GLog;
import ca.cours5b5.gabriellevesqueduval.vues.controles.VGrille;


public abstract class PPartie extends Page {

    private VGrille grille;
    private TextView textViewNom1;
    private TextView textViewNom2;

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

        Random random = new Random();

        int largeur = random.nextInt(5) + 2;
        int hauteur = random.nextInt(4) + 2;

        grille.creerGrille(hauteur, largeur);


    }
}

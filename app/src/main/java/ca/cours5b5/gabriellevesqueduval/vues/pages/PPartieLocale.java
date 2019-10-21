package ca.cours5b5.gabriellevesqueduval.vues.pages;

import android.content.Context;
import android.util.AttributeSet;

import ca.cours5b5.gabriellevesqueduval.donnees.partie.DPartie;
import ca.cours5b5.gabriellevesqueduval.global.GLog;
import ca.cours5b5.gabriellevesqueduval.modeles.MPartie;

public class PPartieLocale extends PPartie {
    public PPartieLocale(Context context) {
        super(context);
        GLog.appel(this);
    }

    public PPartieLocale(Context context, AttributeSet attrs) {
        super(context, attrs);
        GLog.appel(this);
    }

    public PPartieLocale(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        GLog.appel(this);
    }

    @Override
    public void creerAffichage(DPartie donnees) {

    }

    @Override
    public void rafraichirAffichage(DPartie donnees) {

    }

    @Override
    public void installerCapteurs(MPartie modele) {

    }

}

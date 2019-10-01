package ca.cours5b5.gabriellevesqueduval.vues.pages;

import android.content.Context;
import android.util.AttributeSet;

import ca.cours5b5.gabriellevesqueduval.donnees.Donnees;
import ca.cours5b5.gabriellevesqueduval.modeles.Modele;

public abstract class PageAvecModeles<D extends Donnees, M extends Modele> extends Page {
    public PageAvecModeles(Context context) {
        super(context);
    }

    public PageAvecModeles(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PageAvecModeles(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public abstract void creerAffichage(D donnees);

    public abstract void rafraichirAffichage(D donnees);

    public abstract void installerCapteurs(M modele);
}

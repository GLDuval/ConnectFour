package ca.cours5b5.gabriellevesqueduval.vues.pages;

import android.content.Context;
import android.util.AttributeSet;

import ca.cours5b5.gabriellevesqueduval.donnees.Donnees;

public abstract class PageAvecDonnees<D extends Donnees> extends Page {
    public PageAvecDonnees(Context context) {
        super(context);
    }

    public PageAvecDonnees(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PageAvecDonnees(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public abstract void creerAffichage(D donnees);

    public abstract void rafraichirAffichage(D donnees);
}

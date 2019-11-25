package ca.cours5b5.gabriellevesqueduval.vues.pages;

import android.content.Context;
import android.util.AttributeSet;

import ca.cours5b5.gabriellevesqueduval.donnees.Donnees;
import ca.cours5b5.gabriellevesqueduval.global.GLog;
import ca.cours5b5.gabriellevesqueduval.modeles.Modele;

public abstract class PageAvecModeles<D extends Donnees, M extends Modele> extends Page {
    public PageAvecModeles(Context context) {
        super(context);
        GLog.appel(this);
    }

    public PageAvecModeles(Context context, AttributeSet attrs) {
        super(context, attrs);
        GLog.appel(this);
    }

    public PageAvecModeles(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        GLog.appel(this);
    }

    public abstract void creerAffichage(D donnees);

    public void rafraichirAffichage(D donnees){
        GLog.appel(this);
        raffraichirCommandes();
    }

    public abstract void detruireAffichage();

    public abstract void installerCapteurs();

    public abstract void creerCommandes();

    public abstract void raffraichirCommandes();
}

package ca.cours5b5.gabriellevesqueduval.vues.pages;

import android.content.Context;
import android.util.AttributeSet;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.snackbar.Snackbar;

import ca.cours5b5.gabriellevesqueduval.commandes.CQuitterActivite;
import ca.cours5b5.gabriellevesqueduval.commandes.Commande;
import ca.cours5b5.gabriellevesqueduval.global.GLog;

public abstract class Page extends ConstraintLayout {

    public Page(Context context) {
        super(context);
        GLog.appel(this);
    }

    public Page(Context context, AttributeSet attrs) {
        super(context, attrs);
        GLog.appel(this);
    }

    public Page(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        GLog.appel(this);
    }

    @Override
    protected void onFinishInflate() {
        GLog.appel(this);
        super.onFinishInflate();
        recupererControles();

    }

    protected  abstract void recupererControles();

    public void afficherMessagePuisExecuterCommande(int message, final Commande commande) {
        GLog.appel(this);
        Snackbar snackbar = Snackbar.make(this.getRootView(),getResources().getString(message), Snackbar.LENGTH_SHORT);
        snackbar.addCallback(new Snackbar.Callback(){
            @Override
            public void onDismissed(Snackbar snackbar1, int event){
                commande.executer();
            }
        });
        snackbar.show();
    }
}

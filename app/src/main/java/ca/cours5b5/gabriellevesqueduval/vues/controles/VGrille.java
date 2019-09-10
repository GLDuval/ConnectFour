package ca.cours5b5.gabriellevesqueduval.vues.controles;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import ca.cours5b5.gabriellevesqueduval.global.GLog;

public class VGrille extends LinearLayout {

    private ArrayList<VColonne> listeColonnes = new ArrayList<>();

    public VGrille(Context context) {
        super(context);
    }

    public VGrille(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public VGrille(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public VGrille(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        GLog.appel(this);


    }

    public void creerGrille(int hauteur, int largeur){
        GLog.appel(this);

        this.setOrientation(LinearLayout.HORIZONTAL);

        for(int col=0; col<largeur; col++){

            VColonne colonne = new VColonne(this.getContext());

            colonne.creerColonne(col, hauteur);

            LayoutParams layoutParams = new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1);

            this.addView(colonne, layoutParams);

            listeColonnes.add(colonne);
            }

    }
}

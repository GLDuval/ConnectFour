package ca.cours5b5.gabriellevesqueduval.vues.controles;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;


import javax.microedition.khronos.opengles.GL;

import ca.cours5b5.gabriellevesqueduval.global.GLog;

public class VColonne extends LinearLayout {

    private VEntete enTete;
    private VCase[] cases;
    private int indice;

    public VColonne(Context context) {
        super(context);
        GLog.appel(this);
    }

    public VColonne(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        GLog.appel(this);
    }

    public VColonne(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        GLog.appel(this);
    }

    public VColonne(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        GLog.appel(this);
    }

    public void creerColonne(int colCourante, int hauteur){
        GLog.appel(this);
        this.indice = colCourante;
        this.setOrientation(LinearLayout.VERTICAL);

        ajouterEnTete(colCourante);

        remplirCases(colCourante, hauteur);
    }

    private void remplirCases(int colCourante, int hauteur){
        GLog.appel(this);

        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 2);
        layoutParams.setMargins(10,10,10,10);

        cases = new VCase[hauteur];

        for(int i=0; i<cases.length; i++){

            cases[i] = new VCase(this.getContext(), colCourante, cases.length-1-i);

            this.addView(cases[i], layoutParams);
        }

    }

    private void ajouterEnTete(int colCourante){
        GLog.appel(this);

        enTete = new VEntete(this.getContext(), colCourante);

        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 3);
        layoutParams.setMargins(10,10,10,10);

        this.addView(enTete, layoutParams);
    }

    public VCase[] getCases() {
        GLog.appel(this);
        return cases;
    }

    public VEntete getEnTete() {
        GLog.appel(this);
        return enTete;
    }

    public int getIndice() {
        GLog.appel(this);
        return indice;
    }
}

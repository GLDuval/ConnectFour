package ca.cours5b5.gabriellevesqueduval.vues.controles;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import ca.cours5b5.gabriellevesqueduval.global.GLog;

public class VColonne extends LinearLayout {

    private VEntete enTete;
    private VCase[] cases;

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
        this.setOrientation(LinearLayout.VERTICAL);
        enTete = new VEntete(this.getContext());

        remplirCases(colCourante, hauteur);
    }

    private void remplirCases(int colCourante, int hauteur){
        GLog.appel(this);

        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(10,10,10,10);
        cases = new VCase[hauteur];
        for(int i=0; i<cases.length; i++){

            cases[i] = new VCase(this.getContext(), colCourante, i);

            this.addView(cases[i], layoutParams);
        }

    }


}

package ca.cours5b5.gabriellevesqueduval.vues.pages;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.TextView;

import ca.cours5b5.gabriellevesqueduval.R;
import ca.cours5b5.gabriellevesqueduval.global.GLog;

public class PParametres extends Page{

    private TextView textViewTaille;
    private CheckBox checkBoxPetite;
    private CheckBox checkBoxMoyenne;
    private CheckBox checkBoxGrande;
    private Switch switchReprendre;

    public PParametres(Context context) {
        super(context);
        GLog.appel(this);
    }

    public PParametres(Context context, AttributeSet attrs) {
        super(context, attrs);
        GLog.appel(this);
    }

    public PParametres(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        GLog.appel(this);
    }

    @Override
    protected void recupererControles() {

        GLog.appel(this);
        textViewTaille = this.findViewById(R.id.textViewTaille);
        checkBoxPetite = this.findViewById(R.id.checkBoxPetite);
        checkBoxMoyenne = this.findViewById(R.id.checkBoxMoyenne);
        checkBoxGrande = this.findViewById(R.id.checkBoxGrande);
        switchReprendre = this.findViewById(R.id.switchReprendre);
        GLog.valeurs(textViewTaille, checkBoxPetite, checkBoxMoyenne, checkBoxGrande, switchReprendre);
    }
}

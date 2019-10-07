package ca.cours5b5.gabriellevesqueduval.vues.pages;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.TextView;

import ca.cours5b5.gabriellevesqueduval.R;
import ca.cours5b5.gabriellevesqueduval.donnees.DParametres;
import ca.cours5b5.gabriellevesqueduval.enumerations.ETailleGrille;
import ca.cours5b5.gabriellevesqueduval.global.GLog;
import ca.cours5b5.gabriellevesqueduval.modeles.MParametres;

public class PParametres extends PageAvecModeles<DParametres, MParametres> {

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
    public void creerAffichage(DParametres donnees) {
        GLog.appel(this);
        afficherLesDonnees(donnees);
    }

    @Override
    public void rafraichirAffichage(DParametres donnees) {
        GLog.appel(this);
        afficherLesDonnees(donnees);
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

    @Override
    public void installerCapteurs(final MParametres modele) {

        checkBoxGrande.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                modele.changementTaille(ETailleGrille.grande);
                checkBoxPetite.setChecked(false);
                checkBoxMoyenne.setChecked(false);
            }
        });
        checkBoxMoyenne.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                modele.changementTaille(ETailleGrille.moyenne);
                checkBoxPetite.setChecked(false);
                checkBoxGrande.setChecked(false);
            }
        });
        checkBoxPetite.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                modele.changementTaille(ETailleGrille.petite);
                checkBoxMoyenne.setChecked(false);
                checkBoxGrande.setChecked(false);
            }
        });
        switchReprendre.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                modele.changementContinuer(switchReprendre.isChecked());
            }
        });

    }


    private void afficherLesDonnees(DParametres donnees){
        GLog.appel(this);
        donnees.setTaille(donnees.getTaille());
        donnees.setContinuer(donnees.isContinuer());

        switch (donnees.getTaille()){
            case petite:
                checkBoxPetite.setChecked(true);
                break;
            case moyenne:
                checkBoxMoyenne.setChecked(true);
                break;
            case grande:
                checkBoxGrande.setChecked(true);
                break;

        }

        switchReprendre.setChecked(donnees.isContinuer());
    }
}

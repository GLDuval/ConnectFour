package ca.cours5b5.gabriellevesqueduval.vues.pages;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.TextView;

import ca.cours5b5.gabriellevesqueduval.R;
import ca.cours5b5.gabriellevesqueduval.commandes.CContinuerPartie;
import ca.cours5b5.gabriellevesqueduval.commandes.CTailleGrille;
import ca.cours5b5.gabriellevesqueduval.donnees.DParametres;
import ca.cours5b5.gabriellevesqueduval.enumerations.ETailleGrille;
import ca.cours5b5.gabriellevesqueduval.global.GConstantes;
import ca.cours5b5.gabriellevesqueduval.global.GLog;
import ca.cours5b5.gabriellevesqueduval.modeles.MParametres;

public class PParametres extends PageAvecModeles<DParametres, MParametres> {

    private TextView textViewTaille;
    private CheckBox checkBoxPetite;
    private CheckBox checkBoxMoyenne;
    private CheckBox checkBoxGrande;
    private Switch switchReprendre;

    private CTailleGrille cTailleGrillePetite;
    private CTailleGrille cTailleGrilleMoyenne;
    private CTailleGrille cTailleGrilleGrande;
    private CContinuerPartie cContinuerPartieTrue;
    private CContinuerPartie cContinuerPartieFalse;


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
    }

    @Override
    public void rafraichirAffichage(DParametres donnees) {
        super.rafraichirAffichage(donnees);
        GLog.appel(this);

        afficherLesDonnees(donnees);
    }

    @Override
    public void detruireAffichage() {

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
    public void installerCapteurs() {

        GLog.appel(this);

        checkBoxGrande.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                GLog.appel(this);
                cTailleGrilleGrande.executer();
                checkBoxPetite.setChecked(false);
                checkBoxMoyenne.setChecked(false);
            }
        });
        checkBoxMoyenne.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                GLog.appel(this);
                cTailleGrilleMoyenne.executer();
                checkBoxPetite.setChecked(false);
                checkBoxGrande.setChecked(false);
            }
        });
        checkBoxPetite.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                GLog.appel(this);
                cTailleGrillePetite.executer();
                checkBoxMoyenne.setChecked(false);
                checkBoxGrande.setChecked(false);
            }
        });
        switchReprendre.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                GLog.appel(this);
                if(switchReprendre.isChecked()){
                    cContinuerPartieTrue.executer();
                }else{
                    cContinuerPartieFalse.executer();
                }
            }
        });

    }

    @Override
    public void creerCommandes() {
        GLog.appel(this);

        cTailleGrillePetite = new CTailleGrille(ETailleGrille.petite);
        cTailleGrilleMoyenne = new CTailleGrille(ETailleGrille.moyenne);
        cTailleGrilleGrande = new CTailleGrille(ETailleGrille.grande);

        cContinuerPartieTrue = new CContinuerPartie(true);
        cContinuerPartieFalse = new CContinuerPartie(false);
    }

    @Override
    public void raffraichirCommandes() {
        GLog.appel(this);

        if(!cTailleGrillePetite.siExecutable()){
            checkBoxPetite.setClickable(false);
            checkBoxMoyenne.setClickable(true);
            checkBoxGrande.setClickable(true);
        }else if(!cTailleGrilleMoyenne.siExecutable()){
            checkBoxPetite.setClickable(true);
            checkBoxMoyenne.setClickable(false);
            checkBoxGrande.setClickable(true);
        }else if(!cTailleGrilleGrande.siExecutable()){
            checkBoxPetite.setClickable(true);
            checkBoxMoyenne.setClickable(true);
            checkBoxGrande.setClickable(false);
        }

    }


    private void afficherLesDonnees(DParametres donnees){
        GLog.appel(this);

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

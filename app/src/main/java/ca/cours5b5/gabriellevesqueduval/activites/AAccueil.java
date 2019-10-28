package ca.cours5b5.gabriellevesqueduval.activites;


import android.content.Intent;
import android.view.View;
import android.widget.Button;

import java.io.File;

import ca.cours5b5.gabriellevesqueduval.R;
import ca.cours5b5.gabriellevesqueduval.donnees.DParametres;
import ca.cours5b5.gabriellevesqueduval.donnees.EntrepotDeDonnees;
import ca.cours5b5.gabriellevesqueduval.donnees.partie.DPartie;
import ca.cours5b5.gabriellevesqueduval.donnees.partie.DPartieLocale;
import ca.cours5b5.gabriellevesqueduval.global.GLog;

public class AAccueil extends ActiviteAvecControles {

    private Button buttonConnexion;
    private Button buttonJouer;
    private Button buttonEnLigne;
    private Button buttonParametres;

    @Override
    protected int getLayoutId(){
        GLog.appel(this);
        return R.layout.page_accueil;
    }

    @Override
    protected void recupererControles(){
        GLog.appel(this);
        buttonConnexion = findViewById(R.id.buttonConnexion);
        buttonEnLigne = findViewById(R.id.buttonEnLigne);
        buttonJouer = findViewById(R.id.buttonJouer);
        buttonParametres = findViewById(R.id.buttonParametres);
        GLog.valeurs(buttonConnexion, buttonEnLigne, buttonJouer, buttonParametres);

        buttonParametres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GLog.appel(this);
                Intent ouvertureParametres = new Intent(AAccueil.this, AParametres.class);
                AAccueil.this.startActivity(ouvertureParametres);
            }
        });

        buttonJouer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GLog.appel(this);
                Intent ouverturePartie = new Intent(AAccueil.this, APartieLocale.class);

                DParametres dParametres = EntrepotDeDonnees.obtenirDonnees(DParametres.class, null, AAccueil.this.getFilesDir());
                DPartie dPartie = EntrepotDeDonnees.obtenirDonnees(DPartieLocale.class, null, AAccueil.this.getFilesDir());

                if(!dParametres.isContinuer() || dPartie.getTaille() != dParametres.getTaille()){
                    EntrepotDeDonnees.effacerDonnees(DPartieLocale.class, AAccueil.this.getFilesDir());
                }

                AAccueil.this.startActivity(ouverturePartie);
            }
        });
    }


}

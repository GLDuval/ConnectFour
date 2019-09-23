package ca.cours5b5.gabriellevesqueduval.activites;


import android.content.Intent;
import android.view.View;
import android.widget.Button;

import ca.cours5b5.gabriellevesqueduval.R;
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
                Intent ouvertureParametres = new Intent(AAccueil.this, AParametres.class);
                AAccueil.this.startActivity(ouvertureParametres);
            }
        });

        buttonJouer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ouverturePartie = new Intent(AAccueil.this, APartieLocale.class);
                AAccueil.this.startActivity(ouverturePartie);
            }
        });
    }


}

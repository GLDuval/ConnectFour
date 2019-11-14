package ca.cours5b5.gabriellevesqueduval.activites;


import android.content.Intent;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import ca.cours5b5.gabriellevesqueduval.R;
import ca.cours5b5.gabriellevesqueduval.donnees.DParametres;
import ca.cours5b5.gabriellevesqueduval.donnees.EntrepotDeDonnees;
import ca.cours5b5.gabriellevesqueduval.donnees.partie.DPartie;
import ca.cours5b5.gabriellevesqueduval.donnees.partie.DPartieLocale;
import ca.cours5b5.gabriellevesqueduval.global.GLog;
import ca.cours5b5.gabriellevesqueduval.global.GUsagerCourant;

public class AAccueil extends ActiviteAvecControles {

    private Button buttonConnexion;
    private Button buttonJouer;
    private Button buttonEnLigne;
    private Button buttonParametres;

    private final int CODE_CONNEXION = 122;

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

        buttonConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GLog.appel(this);
                GLog.valeurs("Usager", GUsagerCourant.getId());
                if(GUsagerCourant.siConnecte()){
                    effectuerDeconnexion();
                }else{
                    effectuerConnexion();
                }

            }
        });
    }

    private void effectuerConnexion(){
        GLog.appel(this);
        List<AuthUI.IdpConfig> fournisseursDeConnexion = new ArrayList<>();
        fournisseursDeConnexion.add(new AuthUI.IdpConfig.GoogleBuilder().build());
        fournisseursDeConnexion.add(new AuthUI.IdpConfig.EmailBuilder().build());
        fournisseursDeConnexion.add(new AuthUI.IdpConfig.PhoneBuilder().build());

        Intent intentionConnexion = AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(fournisseursDeConnexion).build();

        this.startActivityForResult(intentionConnexion, CODE_CONNEXION);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        GLog.appel(this);
        if(requestCode == CODE_CONNEXION){
            if(resultCode == RESULT_OK){
                buttonConnexion.setText(R.string.boutonDeconnexion);
                activationJouerEnLigne(true);
                GLog.valeurs("Usager", GUsagerCourant.getId());
            }
        }

    }

    private void effectuerDeconnexion(){
        GLog.appel(this);
        AuthUI.getInstance().signOut(this).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                buttonConnexion.setText(R.string.boutonConnexion);
                activationJouerEnLigne(false);
                GLog.valeurs("Usager", GUsagerCourant.getId());
            }
        });
    }

    private void activationJouerEnLigne(boolean active){
        GLog.appel(this);
        buttonEnLigne.setEnabled(active);
    }


}

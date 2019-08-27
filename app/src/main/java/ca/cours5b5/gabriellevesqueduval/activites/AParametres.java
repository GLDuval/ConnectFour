package ca.cours5b5.gabriellevesqueduval.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import ca.cours5b5.gabriellevesqueduval.R;
import ca.cours5b5.gabriellevesqueduval.global.GLog;

public class AParametres extends Activite {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        GLog.appel(this);
        GLog.valeurs(this.getResources().getString(R.string.bonjour));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_parametres);
    }

    @Override
    protected int getContentViewId() {
        GLog.appel(this);
        return R.layout.page_parametres;
    }
}

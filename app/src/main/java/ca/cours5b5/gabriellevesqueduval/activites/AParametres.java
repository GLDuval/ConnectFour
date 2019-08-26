package ca.cours5b5.gabriellevesqueduval.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import ca.cours5b5.gabriellevesqueduval.R;

public class AParametres extends Activite {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_parametres);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.page_parametres;
    }
}

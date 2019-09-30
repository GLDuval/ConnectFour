package ca.cours5b5.gabriellevesqueduval.activites;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import ca.cours5b5.gabriellevesqueduval.global.GLog;

public abstract class Activite extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        GLog.appel(this);
        GLog.valeurs(savedInstanceState);
        super.onCreate(savedInstanceState);

        int contentViewId = getLayoutId();

        setContentView(contentViewId);

    }

    @Override
    protected void onResume() {

        GLog.appel(this);
        super.onResume();
    }

    @Override
    protected void onPause() {

        GLog.appel(this);
        super.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        GLog.appel(this);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {

        GLog.appel(this);
        super.onDestroy();
    }




    protected abstract int getLayoutId();
}

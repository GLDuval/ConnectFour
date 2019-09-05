package ca.cours5b5.gabriellevesqueduval.activites;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import ca.cours5b5.gabriellevesqueduval.global.GLog;

public abstract class Activite extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        GLog.appel(this);
        super.onCreate(savedInstanceState);

        int contentViewId = getLayoutId();

        setContentView(contentViewId);

    }

    protected abstract int getLayoutId();
}

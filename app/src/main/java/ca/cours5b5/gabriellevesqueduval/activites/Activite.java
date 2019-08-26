package ca.cours5b5.gabriellevesqueduval.activites;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public abstract class Activite extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int contentViewId = getContentViewId();

        setContentView(contentViewId);

    }

    protected abstract int getContentViewId();
}

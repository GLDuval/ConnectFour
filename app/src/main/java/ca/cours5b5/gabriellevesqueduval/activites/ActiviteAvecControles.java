package ca.cours5b5.gabriellevesqueduval.activites;

import android.os.Bundle;

import ca.cours5b5.gabriellevesqueduval.global.GLog;

public abstract class ActiviteAvecControles extends Activite {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        GLog.appel(this);
        super.onCreate(savedInstanceState);
        recupererControles();


    }

    protected abstract void recupererControles();
}

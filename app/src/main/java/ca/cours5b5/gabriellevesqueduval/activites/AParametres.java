package ca.cours5b5.gabriellevesqueduval.activites;

import android.os.Bundle;

import ca.cours5b5.gabriellevesqueduval.R;
import ca.cours5b5.gabriellevesqueduval.global.GLog;

public class AParametres extends Activite {


    @Override
    protected int getLayoutId() {
        GLog.appel(this);
        return R.layout.page_parametres;
    }
}

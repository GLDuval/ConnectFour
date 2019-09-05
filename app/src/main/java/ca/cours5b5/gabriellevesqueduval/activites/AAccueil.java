package ca.cours5b5.gabriellevesqueduval.activites;


import android.os.Bundle;

import ca.cours5b5.gabriellevesqueduval.R;
import ca.cours5b5.gabriellevesqueduval.global.GLog;

public class AAccueil extends ActiviteAvecControles {

    @Override
    protected int getContentViewId(){
        GLog.appel(this);
        return R.layout.page_accueil;
    }

    @Override
    protected void recupererControles(){
        GLog.appel(this);
    }
}

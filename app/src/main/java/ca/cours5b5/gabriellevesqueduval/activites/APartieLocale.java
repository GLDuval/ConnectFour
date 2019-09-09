package ca.cours5b5.gabriellevesqueduval.activites;

import ca.cours5b5.gabriellevesqueduval.R;
import ca.cours5b5.gabriellevesqueduval.global.GLog;

public class APartieLocale extends Activite {
    @Override
    protected int getLayoutId() {
        GLog.appel(this);
        return R.layout.page_partie_locale;
    }
}

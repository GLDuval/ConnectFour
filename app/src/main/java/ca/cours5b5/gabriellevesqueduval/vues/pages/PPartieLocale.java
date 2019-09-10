package ca.cours5b5.gabriellevesqueduval.vues.pages;

import android.content.Context;
import android.util.AttributeSet;

import ca.cours5b5.gabriellevesqueduval.global.GLog;

public class PPartieLocale extends PPartie {
    public PPartieLocale(Context context) {
        super(context);
        GLog.appel(this);
    }

    public PPartieLocale(Context context, AttributeSet attrs) {
        super(context, attrs);
        GLog.appel(this);
    }

    public PPartieLocale(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        GLog.appel(this);
    }

}

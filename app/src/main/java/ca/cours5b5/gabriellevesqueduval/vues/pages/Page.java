package ca.cours5b5.gabriellevesqueduval.vues.pages;

import android.content.Context;
import android.util.AttributeSet;

import androidx.constraintlayout.widget.ConstraintLayout;

import ca.cours5b5.gabriellevesqueduval.global.GLog;

public abstract class Page extends ConstraintLayout {

    public Page(Context context) {
        super(context);
        GLog.appel(this);
    }

    public Page(Context context, AttributeSet attrs) {
        super(context, attrs);
        GLog.appel(this);
    }

    public Page(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        GLog.appel(this);
    }

    @Override
    protected void onFinishInflate() {
        GLog.appel(this);
        super.onFinishInflate();
        recupererControles();

    }

    protected  abstract void recupererControles();
}

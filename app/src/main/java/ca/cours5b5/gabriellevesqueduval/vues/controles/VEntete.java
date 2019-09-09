package ca.cours5b5.gabriellevesqueduval.vues.controles;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatButton;

import ca.cours5b5.gabriellevesqueduval.global.GLog;


public class VEntete extends AppCompatButton {
    public VEntete(Context context) {
        super(context);
        GLog.appel(this);
    }

    public VEntete(Context context, AttributeSet attrs) {
        super(context, attrs);
        GLog.appel(this);
    }

    public VEntete(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        GLog.appel(this);
    }
}

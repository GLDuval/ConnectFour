package ca.cours5b5.gabriellevesqueduval.vues.controles;

import android.content.Context;
import android.util.AttributeSet;


import androidx.appcompat.widget.AppCompatButton;

import ca.cours5b5.gabriellevesqueduval.global.GLog;

public class VCase extends AppCompatButton {
    public VCase(Context context) {
        super(context);
        GLog.appel(this);
    }

    public VCase(Context context, AttributeSet attrs) {
        super(context, attrs);
        GLog.appel(this);
    }

    public VCase(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        GLog.appel(this);
    }

    public VCase(Context context, int indiceCol, int indiceLigne){
        super(context);
        GLog.appel(this);

        this.setText(indiceLigne + ", " + indiceCol);
    }
}

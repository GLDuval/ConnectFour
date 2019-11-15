package ca.cours5b5.gabriellevesqueduval.global;

import com.google.firebase.auth.FirebaseAuth;

public class GUsagerCourant {

    public static boolean siConnecte(){
        GLog.appel(GUsagerCourant.class);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        return auth.getCurrentUser() != null;
    }

    public static String getId(){
        GLog.appel(GUsagerCourant.class);
        String uid = FirebaseAuth.getInstance().getUid();
        if(uid == null){
            uid = GConstantes.USAGER_DEFAUT;
        }
        return uid;
    }
}

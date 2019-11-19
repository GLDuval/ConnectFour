package ca.cours5b5.gabriellevesqueduval.commandes;

import ca.cours5b5.gabriellevesqueduval.activites.Activite;
import ca.cours5b5.gabriellevesqueduval.global.GLog;

public class CQuitterActivite extends Commande{

    private static Activite activite;

    public static void initialiser(Activite activite){
        GLog.appel(CQuitterActivite.class);
        CQuitterActivite.activite = activite;
    }

    public CQuitterActivite(){
        GLog.appel(this);

    }

    @Override
    public void executer(){
        GLog.appel(this);
        activite.quitter();
    }
}

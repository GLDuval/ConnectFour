package ca.cours5b5.gabriellevesqueduval.commandes;

import ca.cours5b5.gabriellevesqueduval.global.GLog;
import ca.cours5b5.gabriellevesqueduval.modeles.MPartie;

public class CCoupIci extends Commande {

    private static MPartie modele;

    private int indiceCoup;

    public static void initialiser(MPartie modele){
        GLog.appel(CCoupIci.class);
        CCoupIci.modele = modele;
    }

    public CCoupIci(int indiceCoup){
        GLog.appel(this);
        this.indiceCoup = indiceCoup;
    }

    @Override
    public void executer() {
        GLog.appel(this);
        modele.jetonJoue(indiceCoup);
    }

    @Override
    public boolean siExecutable(){
        GLog.appel(this);

        return modele.siColonnePleine(indiceCoup);
    }
}

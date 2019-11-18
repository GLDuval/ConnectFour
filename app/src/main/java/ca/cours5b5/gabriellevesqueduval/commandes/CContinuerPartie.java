package ca.cours5b5.gabriellevesqueduval.commandes;

import ca.cours5b5.gabriellevesqueduval.global.GLog;
import ca.cours5b5.gabriellevesqueduval.modeles.MParametres;

public class CContinuerPartie extends Commande {

    private static MParametres modele;

    private boolean continuer;

    public static void initialiser(MParametres modele){
        GLog.appel(CContinuerPartie.class);
        CContinuerPartie.modele = modele;
    }

    public CContinuerPartie(boolean continuerPartie){
        GLog.appel(this);
        continuer = continuerPartie;
    }

    @Override
    public void executer() {
        GLog.appel(this);
        modele.changementContinuer(continuer);
        GLog.valeurs(continuer);
    }
}

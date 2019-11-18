package ca.cours5b5.gabriellevesqueduval.commandes;

import ca.cours5b5.gabriellevesqueduval.enumerations.ETailleGrille;
import ca.cours5b5.gabriellevesqueduval.global.GLog;
import ca.cours5b5.gabriellevesqueduval.modeles.MParametres;

public class CTailleGrille extends Commande{

    private static MParametres modele;

    private ETailleGrille taille;

    public static void initialiser(MParametres modele){
        GLog.appel(CTailleGrille.class);
        CTailleGrille.modele = modele;
    }

    public CTailleGrille(ETailleGrille tailleGrille){
        GLog.appel(this);
        taille = tailleGrille;
    }

    @Override
    public void executer(){
        GLog.appel(this);
        modele.changementTaille(taille);
    }

    @Override
    public boolean siExecutable(){
        GLog.appel(this);
        return modele.getTaille() != taille;
    }
}

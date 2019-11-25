package ca.cours5b5.gabriellevesqueduval.donnees;

import ca.cours5b5.gabriellevesqueduval.enumerations.ETailleGrille;
import ca.cours5b5.gabriellevesqueduval.global.GConstantes;
import ca.cours5b5.gabriellevesqueduval.global.GLog;

public class DParametres extends Donnees<DParametres>{

    private ETailleGrille taille = GConstantes.taille;
    private boolean continuer = GConstantes.continuer;

    public DParametres(){
        GLog.appel(this);

    }

    public void setTaille(ETailleGrille taille){
        GLog.appel(this);
        this.taille = taille;
    }

    public void setContinuer(boolean continuer){
        GLog.appel(this);
        this.continuer = continuer;
    }

    public ETailleGrille getTaille(){
        GLog.appel(this);
        return taille;
    }

    public boolean isContinuer(){
        GLog.appel(this);
        return continuer;
    }

    @Override
    public void copierDonnees(DParametres dParametres){
        GLog.appel(this);
        super.copierDonnees(dParametres);

        this.taille = dParametres.getTaille();
        this.continuer = dParametres.isContinuer();
    }
}

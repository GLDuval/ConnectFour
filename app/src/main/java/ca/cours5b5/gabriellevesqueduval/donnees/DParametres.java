package ca.cours5b5.gabriellevesqueduval.donnees;

import ca.cours5b5.gabriellevesqueduval.global.GLog;

public class DParametres extends Donnees{

    private String taille;
    private boolean continuer;

    public DParametres(){
        GLog.appel(this);

    }

    public void setTaille(String taille){
        GLog.appel(this);
        this.taille = taille;
    }

    public void setContinuer(boolean continuer){
        GLog.appel(this);
        this.continuer = continuer;
    }

    public String getTaille(){
        GLog.appel(this);
        return taille;
    }

    public boolean isContinuer(){
        GLog.appel(this);
        return continuer;
    }
}

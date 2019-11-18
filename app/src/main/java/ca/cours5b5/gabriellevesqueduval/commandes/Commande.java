package ca.cours5b5.gabriellevesqueduval.commandes;

import ca.cours5b5.gabriellevesqueduval.global.GLog;

public abstract class Commande {

    public abstract void executer();

    public boolean siExecutable(){
        GLog.appel(this);
        return true;
    }
}

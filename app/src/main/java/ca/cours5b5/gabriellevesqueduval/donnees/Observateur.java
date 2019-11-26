package ca.cours5b5.gabriellevesqueduval.donnees;

import ca.cours5b5.gabriellevesqueduval.global.GLog;

public abstract class Observateur<D extends Donnees> {

    private long versionCourante = 0;

    public void notifierNouvellesDonnees(D donnees){
        GLog.appel(this);
        versionCourante = donnees.getVersionCourante();
        nouveau(donnees);
    }

    public void notifierModificationReseau(D donnees){
        GLog.appel(this);
        if(versionCourante < donnees.getVersionCourante()){
            versionCourante = donnees.getVersionCourante();
            donneesDuServeur(donnees);
        }

    }

    protected abstract void nouveau(D donnees);
    protected abstract void donneesDuServeur(D donnees);
}

package ca.cours5b5.gabriellevesqueduval.donnees;


import ca.cours5b5.gabriellevesqueduval.global.GLog;

public abstract class Donnees<D extends Donnees> {

    private long versionCourante;

    public void notifierModificationLocale(){
        GLog.appel(this);
        versionCourante++;
    }

    public void copierDonnees(D donnees){
        GLog.appel(this);
        this.versionCourante = donnees.getVersionCourante();
    }

    public long getVersionCourante() {
        GLog.appel(this);
        return versionCourante;
    }
}

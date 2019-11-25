package ca.cours5b5.gabriellevesqueduval.donnees;

public abstract class Observateur<D extends Donnees> {

    private long versionCourante = 0;

    public void notifierNouvellesDonnees(D donnees){
        versionCourante = donnees.getVersionCourante();
        nouveau(donnees);
    }

    public void notifierModificationReseau(D donnees){
        if(versionCourante < donnees.getVersionCourante()){
            versionCourante = donnees.getVersionCourante();
            donneesDuServeur(donnees);
        }

    }

    protected abstract void nouveau(D donnees);
    protected abstract void donneesDuServeur(D donnees);
}

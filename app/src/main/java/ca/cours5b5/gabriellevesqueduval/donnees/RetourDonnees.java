package ca.cours5b5.gabriellevesqueduval.donnees;

public interface RetourDonnees<D extends Donnees> {

    void recevoirDonnees(D donnees);
}

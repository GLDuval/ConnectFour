package ca.cours5b5.gabriellevesqueduval.donnees.partie;

import ca.cours5b5.gabriellevesqueduval.donnees.Donnees;
import ca.cours5b5.gabriellevesqueduval.enumerations.ECouleur;
import ca.cours5b5.gabriellevesqueduval.global.GLog;

public class DCase extends Donnees {

    private ECouleur couleur = ECouleur.gris;

    public DCase(){
        GLog.appel(this);
    }

    public void setCouleur(ECouleur couleur){
        GLog.appel(this);
        this.couleur = couleur;
    }

    public ECouleur getCouleur(){
        GLog.appel(this);
        return couleur;
    }
}

package ca.cours5b5.gabriellevesqueduval.donnees.partie;

import java.util.ArrayList;

import ca.cours5b5.gabriellevesqueduval.donnees.Donnees;
import ca.cours5b5.gabriellevesqueduval.global.GLog;

public class DGrille extends Donnees {

    private ArrayList<DColonne> colonnes = new ArrayList<>();

    public DGrille(){
        GLog.appel(this);

    }

    public DGrille(ArrayList<DColonne> colonnes){
        GLog.appel(this);
        this.colonnes = colonnes;
    }

    public ArrayList<DColonne> getColonnes(){
        GLog.appel(this);
        return colonnes;
    }

    public void setColonnes(ArrayList<DColonne> colonnes) {
        GLog.appel(this);
        this.colonnes = colonnes;
    }
}

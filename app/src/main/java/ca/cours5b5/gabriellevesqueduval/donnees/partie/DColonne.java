package ca.cours5b5.gabriellevesqueduval.donnees.partie;

import java.util.ArrayList;
import ca.cours5b5.gabriellevesqueduval.donnees.Donnees;
import ca.cours5b5.gabriellevesqueduval.global.GLog;

public class DColonne extends Donnees {

    private ArrayList<DCase> cases = new ArrayList<>();

    public DColonne(){
        GLog.appel(this);
    }

    public DColonne(int hauteur){
        GLog.appel(this);
        for(int i=0; i<hauteur; i++){
            cases.add(new DCase());
        }
    }

    public ArrayList<DCase> getCases(){
        GLog.appel(this);
        return cases;
    }

    public void setCases(ArrayList<DCase> cases) {
        GLog.appel(this);
        this.cases = cases;
    }

}

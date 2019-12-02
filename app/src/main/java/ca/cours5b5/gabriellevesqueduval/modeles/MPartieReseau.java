package ca.cours5b5.gabriellevesqueduval.modeles;

import ca.cours5b5.gabriellevesqueduval.donnees.partie.DPartie;
import ca.cours5b5.gabriellevesqueduval.donnees.partie.DPartieReseau;
import ca.cours5b5.gabriellevesqueduval.global.GLog;
import ca.cours5b5.gabriellevesqueduval.global.GUsagerCourant;
import ca.cours5b5.gabriellevesqueduval.vues.pages.PPartie;
import ca.cours5b5.gabriellevesqueduval.vues.pages.PPartieReseau;

public class MPartieReseau extends MPartie {

    DPartieReseau dPartieReseau;

    public MPartieReseau(DPartie donnees, PPartieReseau page) {
        super(donnees, page);
        GLog.appel(this);
        dPartieReseau = (DPartieReseau) donnees;
    }

    @Override
    public boolean siExecutable(int indiceCol){
        GLog.appel(this);
        boolean executable = false;
        if(dPartieReseau.getIdProchain()!=null){
            executable = !dPartieReseau.getIdProchain().equals(GUsagerCourant.getId());
        }else{
            executable = true;
        }

        return executable && super.siExecutable(indiceCol);
    }

    @Override
    public void jetonJoue(int indiceCol){
        GLog.appel(this);
        dPartieReseau.setIdProchain(GUsagerCourant.getId());
        super.jetonJoue(indiceCol);
    }
}

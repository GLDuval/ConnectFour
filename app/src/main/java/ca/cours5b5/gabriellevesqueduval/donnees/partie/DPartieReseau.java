package ca.cours5b5.gabriellevesqueduval.donnees.partie;

import ca.cours5b5.gabriellevesqueduval.global.GLog;

public class DPartieReseau extends DPartie {

    private String idProchain;

    public String getIdProchain() {
        return idProchain;
    }

    public void setIdProchain(String idProchain) {
        GLog.appel(this);
        this.idProchain = idProchain;
    }

    @Override
    public void copierDonnees(DPartie dPartie){
        GLog.appel(this);
        super.copierDonnees(dPartie);
        DPartieReseau dPartieReseau = (DPartieReseau) dPartie;
        this.idProchain = dPartieReseau.getIdProchain();
    }

}

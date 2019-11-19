package ca.cours5b5.gabriellevesqueduval.commandes;



import ca.cours5b5.gabriellevesqueduval.global.GLog;
import ca.cours5b5.gabriellevesqueduval.vues.pages.Page;

public class CMessagePuisCommande extends Commande {

    private static Page page;

    private int message;

    private Commande commande;

    public static void initialiser(Page page){
        GLog.appel(CMessagePuisCommande.class);
        CMessagePuisCommande.page = page;
    }

    public CMessagePuisCommande(int message, Commande commande){
        GLog.appel(this);
        this.message = message;
        this.commande = commande;
    }


    @Override
    public void executer() {
        GLog.appel(this);
        page.afficherMessagePuisExecuterCommande(message, commande);
    }
}

package ca.cours5b5.gabriellevesqueduval.donnees;

import java.util.HashMap;
import java.util.Map;

import ca.cours5b5.gabriellevesqueduval.global.GLog;

public class EntrepotDeDonnees {

    public static Map <Class<? extends Donnees>, Donnees> donneesMap = new HashMap<>();

    public static <D extends Donnees> D obtenirDonnees(Class<D> classeDonnees){

        GLog.appel(EntrepotDeDonnees.class);

        if(!siDonneesSontDansEntrepot(classeDonnees)){
            try {
                entreposerDonnees(creerDonnees(classeDonnees));
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
        return donneesDansEntrepot(classeDonnees);

    }

    private static <D extends  Donnees> D creerDonnees(Class<D> classeDonnees) throws InstantiationException, IllegalAccessException {
        GLog.appel(EntrepotDeDonnees.class);

        return classeDonnees.newInstance();

    }
    private static <D extends  Donnees> D donneesDansEntrepot(Class<? extends Donnees> classeDonnees){
        GLog.appel(EntrepotDeDonnees.class);


        return (D)donneesMap.get(classeDonnees);
    }
    private static boolean siDonneesSontDansEntrepot(Class<? extends Donnees> classeDonnees){
        GLog.appel(EntrepotDeDonnees.class);

        return donneesMap.containsKey(classeDonnees);
    }
    private static <D extends  Donnees> void entreposerDonnees(D donnees){
        GLog.appel(EntrepotDeDonnees.class);

        donneesMap.put(donnees.getClass(), donnees);

    }
}

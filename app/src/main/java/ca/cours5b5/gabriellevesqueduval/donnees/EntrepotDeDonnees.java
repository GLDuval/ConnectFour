package ca.cours5b5.gabriellevesqueduval.donnees;

import java.util.Map;

public class EntrepotDeDonnees {

    public static Map <Class<? extends Donnees>, Donnees> donneesMap;

    public static <D extends Donnees> D obtenirDonnees(Class<D> classeDonnees){

        D donnees = null;

        if(!siDonneesSontDansEntrepot(classeDonnees)){
            try {
                entreposerDonnees(creerDonnees(classeDonnees));
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }else{
            donnees = donneesDansEntrepot(classeDonnees);
        }
        return donnees;

    }

    private static <D extends  Donnees> D creerDonnees(Class<D> classeDonnees) throws InstantiationException, IllegalAccessException {

        return classeDonnees.newInstance();

    }
    private static <D extends  Donnees> D donneesDansEntrepot(Class<? extends Donnees> classeDonnees){


        return (D)donneesMap.get(classeDonnees);
    }
    private static boolean siDonneesSontDansEntrepot(Class<? extends Donnees> classeDonnees){

        return donneesMap.containsKey(classeDonnees);
    }
    private static <D extends  Donnees> void entreposerDonnees(D donnees){

        donneesMap.put(donnees.getClass(), donnees);

    }
}

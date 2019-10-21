package ca.cours5b5.gabriellevesqueduval.donnees;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import ca.cours5b5.gabriellevesqueduval.global.GLog;

public class EntrepotDeDonnees {

    public static Map<Class<? extends Donnees>, Donnees> donneesMap = new HashMap<>();

    private static Gson gson = new GsonBuilder().create();

    public static <D extends Donnees> D obtenirDonnees(Class<D> classeDonnees, Bundle etat, File repertoireDonnees){

        GLog.appel(EntrepotDeDonnees.class);

        D donnees;

        if(!siDonneesSontDansEtat(classeDonnees, etat) ){
            if(!siDonneesSontSurDisque(classeDonnees, repertoireDonnees)){
                if(!siDonneesSontDansEntrepot(classeDonnees)){
                    donnees = creerDonnees(classeDonnees);
                    entreposerDonnees(donnees);
                }else{
                    donnees = donneesDansEntrepot(classeDonnees);
                }
            }else{
                donnees = donneesSurDisque(classeDonnees, repertoireDonnees);
            }



        }else {
            donnees = donneesDansEtat(classeDonnees, etat);
        }
        return donnees;

    }

    private static <D extends  Donnees> D creerDonnees(Class<D> classeDonnees) {
        GLog.appel(EntrepotDeDonnees.class);
        D donnees = null;
        try {
            donnees = classeDonnees.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return donnees;

    }
    private static <D extends  Donnees> D donneesDansEtat(Class<D> classeDonnees, Bundle etat){
        GLog.appel(EntrepotDeDonnees.class);

        String chaineJson = etat.getString(clePourClasseDonnees(classeDonnees));
        D donnees = gson.fromJson(chaineJson, classeDonnees);
        GLog.valeurs("Données chargées: ", classeDonnees, chaineJson );


        return donnees;
    }

    private static boolean siDonneesSontDansEtat(Class<? extends Donnees> classeDonnees, @Nullable Bundle etat){
        GLog.appel(EntrepotDeDonnees.class);

        boolean present = false;
        if(etat != null){
            if(etat.containsKey(clePourClasseDonnees(classeDonnees))){
                present = true;
            }
        }



        return present;
    }

    private static String clePourClasseDonnees(Class<? extends Donnees> classeDonnees){
        GLog.appel(EntrepotDeDonnees.class);

        return classeDonnees.getSimpleName();
    }

    public static <D extends  Donnees> void sauvegarderDonnees(D donnees, Bundle outState){
        GLog.appel(EntrepotDeDonnees.class);

        String chaineJson = gson.toJson(donnees);
        String cle = clePourClasseDonnees(donnees.getClass());
        outState.putString(cle, chaineJson);
        GLog.valeurs("Données sauvegardées: ", cle, chaineJson);

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

    private static String nomFichierPourClasseDonnees(Class<? extends Donnees> classeDonnees){
        GLog.appel(EntrepotDeDonnees.class);
        return classeDonnees.getSimpleName() + ".json";
    }

    private static File fichierDonnees(Class <? extends Donnees> classeDonnees, File repertoireDonnees){
        GLog.appel(EntrepotDeDonnees.class);
        File file = new File(repertoireDonnees.getPath() + File.separator + nomFichierPourClasseDonnees(classeDonnees));
        return file;
    }

    private static boolean siDonneesSontSurDisque(Class <? extends Donnees> classeDonnees, File repertoireDonnees){
        GLog.appel(EntrepotDeDonnees.class);
        boolean present = false;
        for (File file: repertoireDonnees.listFiles()) {
            if(file.getName().equals(nomFichierPourClasseDonnees(classeDonnees))){
                present = true;
                break;
            }
        }
        return present;
    }

    private static <D extends Donnees> D donneesSurDisque(Class<D> classeDonnees, File repertoireDonnees){
        GLog.appel(EntrepotDeDonnees.class);
        D donnees = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(fichierDonnees(classeDonnees, repertoireDonnees));

            BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));
            String chaineJson;
            while((chaineJson = reader.readLine()) != null){
                GLog.valeurs(chaineJson);
                donnees = gson.fromJson(chaineJson, classeDonnees);
            }
            reader.close();
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return donnees;
    }

    public static <D extends Donnees> void sauvegarderSurDisque(D donnees, File repertoireDonnees){
        GLog.appel(EntrepotDeDonnees.class);
        String chaineJson = gson.toJson(donnees);

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fichierDonnees(donnees.getClass(), repertoireDonnees));
            Writer writer = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
            writer.write(chaineJson);
            writer.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            GLog.valeurs(e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            GLog.valeurs(e.getMessage());
            e.printStackTrace();
        }

    }
}

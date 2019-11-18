package ca.cours5b5.gabriellevesqueduval.donnees;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import ca.cours5b5.gabriellevesqueduval.global.GLog;
import ca.cours5b5.gabriellevesqueduval.global.GUsagerCourant;

public class EntrepotDeDonnees {

    private static FirebaseFirestore firestore = FirebaseFirestore.getInstance();


    private static <D extends  Donnees> D creerDonnees(Class<D> classeDonnees) {
        GLog.appel(EntrepotDeDonnees.class);
        D donnees = null;
        try {
            donnees = classeDonnees.newInstance();
        } catch (IllegalAccessException e) {
            GLog.valeurs(e.getMessage());
            e.printStackTrace();
        } catch (InstantiationException e) {
            GLog.valeurs(e.getMessage());
            e.printStackTrace();
        }
        return donnees;

    }


    private static String nomCollection(Class<? extends Donnees> classeDonnees){
        GLog.appel(EntrepotDeDonnees.class);
        return classeDonnees.getSimpleName();
    }

    private static String idDocument(){
        GLog.appel(EntrepotDeDonnees.class);
        return GUsagerCourant.getId();
    }

    private static DocumentReference referenceDocument(Class<? extends Donnees> classeDonnees){
        GLog.appel(EntrepotDeDonnees.class);
        return firestore.collection(nomCollection(classeDonnees)).document(idDocument());
    }

    public static <D extends Donnees> void sauvegarderDonneesSurServeur(D donnees){
        GLog.appel(EntrepotDeDonnees.class);
        referenceDocument(donnees.getClass()).set(donnees);
    }

    private  static <D extends Donnees> void reagirDonneesChargees(RetourChargement<D> retourChargement, @Nullable D donnees){
        GLog.appel(EntrepotDeDonnees.class);
        if(donnees != null){
            retourChargement.chargementReussi(donnees);
        }else{
            retourChargement.chargementEchoue();
        }

    }

    private static <D extends Donnees> void reagirDocumentCharge(Class<D> classeDonnees, RetourChargement<D> retourChargement, DocumentSnapshot documentSnapshot){
        GLog.appel(EntrepotDeDonnees.class);
        if(documentSnapshot.exists()){
            D donnees = documentSnapshot.toObject(classeDonnees);
            reagirDonneesChargees(retourChargement, donnees);
        }else {
            retourChargement.chargementEchoue();
        }
    }

    private static <D extends Donnees> void installerCapteursServeur(final Class<D> classeDonnees, final RetourChargement<D> retourChargement, Task<DocumentSnapshot> promessesServeur){
        GLog.appel(EntrepotDeDonnees.class);
        promessesServeur.addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                GLog.appel(this);
                reagirDocumentCharge(classeDonnees, retourChargement, documentSnapshot);
            }
        });

        promessesServeur.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                GLog.appel(this);
                retourChargement.chargementEchoue();
            }
        });
    }

    private static <D extends Donnees> void chargerDonneesDuServeur(final Class<D> classeDonnees, final RetourChargement<D> retourChargement){
        GLog.appel(EntrepotDeDonnees.class);
        DocumentReference reference = referenceDocument(classeDonnees);

        installerCapteursServeur(classeDonnees, retourChargement, reference.get());


    }

    public static <D extends Donnees> void obtenirDonnees(final Class<D> classeDonnees, final RetourDonnees<D> retourDonnees){
        GLog.appel(EntrepotDeDonnees.class);
        RetourChargement<D> retour = new RetourChargement<D>() {
            @Override
            public void chargementReussi(D donnees) {
                GLog.appel(this);
                retourDonnees.recevoirDonnees(donnees);
            }

            @Override
            public void chargementEchoue() {
                GLog.appel(this);
                retourDonnees.recevoirDonnees(creerDonnees(classeDonnees));
            }
        };
        chargerDonneesDuServeur(classeDonnees, retour);

    }
}

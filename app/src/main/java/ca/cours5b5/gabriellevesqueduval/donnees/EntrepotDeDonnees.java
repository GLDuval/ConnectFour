package ca.cours5b5.gabriellevesqueduval.donnees;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

import ca.cours5b5.gabriellevesqueduval.global.GLog;
import ca.cours5b5.gabriellevesqueduval.global.GUsagerCourant;

public class EntrepotDeDonnees {

    private static FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    private static Map<Class<? extends Donnees>, ListenerRegistration> mapObservateur = new HashMap<>();

    private static <D extends Donnees> void memoriserObservateurServeur(Class<D> classeDonnees, ListenerRegistration listenerRegistration){
        mapObservateur.put(classeDonnees, listenerRegistration);
    }

    public static <D extends Donnees> void effacerObservateur(final Class<D> classeDonnees){
        if(mapObservateur.containsKey(classeDonnees)){
            mapObservateur.get(classeDonnees).remove();
        }
    }

    private static <D extends Donnees> void observerUnDocument(Class<D> classeDonnees, Observateur<D> observateurClient, DocumentChange documentChange){

        switch (documentChange.getType()){
            case ADDED:
                observateurClient.notifierNouvellesDonnees(documentChange.getDocument().toObject(classeDonnees));
                break;
            case MODIFIED:
                observateurClient.notifierModificationReseau(documentChange.getDocument().toObject(classeDonnees));
                break;
        }
    }

    private static <D extends Donnees> void observerDocumentsExistants(Class<D> classeDonnees, Observateur<D> observateurClient, QuerySnapshot queryDocumentSnapshots){
        for(DocumentChange documentChange : queryDocumentSnapshots.getDocumentChanges()){
            observerUnDocument(classeDonnees, observateurClient, documentChange);
        }
    }

    private static <D extends Donnees> void observerDocumentsOuCreerNouveau(Class<D> classeDonnees, Observateur<D> observateurClient, QuerySnapshot queryDocumentSnapshots){

        if(queryDocumentSnapshots.isEmpty()){
            observateurClient.notifierNouvellesDonnees(creerDonnees(classeDonnees));
        }else{
            observerDocumentsExistants(classeDonnees, observateurClient, queryDocumentSnapshots);
        }

    }

    private static <D extends Donnees> com.google.firebase.firestore.EventListener<QuerySnapshot> creerObservateurServeur(final Class<D> classeDonnees, final Observateur<D> observateurClient){
        EventListener<QuerySnapshot> listener = new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if(queryDocumentSnapshots != null){
                    observerDocumentsOuCreerNouveau(classeDonnees, observateurClient, queryDocumentSnapshots);
                }
            }
        };
        return listener;
    }

    private static <D extends Donnees> Query creerRequete(Class<D> classeDonnees){
        CollectionReference collection = firestore.collection(nomCollection(classeDonnees));

        return collection.whereEqualTo(FieldPath.documentId(), idDocument());
    }

    private static ListenerRegistration ajouterObservateurServeur(Query requete, com.google.firebase.firestore.EventListener<QuerySnapshot> observateurServeur){
        ListenerRegistration listenerRegistration = requete.addSnapshotListener(observateurServeur);
        return listenerRegistration;
    }

    public static <D extends Donnees> void observerDonnees(final Class<D> classeDonnees, final Observateur<D> observateurClient){
        effacerObservateur(classeDonnees);

        Query requete = creerRequete(classeDonnees);

        EventListener observateurServeur = creerObservateurServeur(classeDonnees, observateurClient);

        ListenerRegistration listenerRegistration = ajouterObservateurServeur(requete, observateurServeur);

        memoriserObservateurServeur(classeDonnees, listenerRegistration);
    }




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

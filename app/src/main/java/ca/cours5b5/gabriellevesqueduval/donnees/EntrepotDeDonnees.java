package ca.cours5b5.gabriellevesqueduval.donnees;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

import ca.cours5b5.gabriellevesqueduval.global.GConstantes;
import ca.cours5b5.gabriellevesqueduval.global.GLog;
import ca.cours5b5.gabriellevesqueduval.global.GUsagerCourant;

public class EntrepotDeDonnees {

    private static FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    private static Map<Class<? extends Donnees>, ListenerRegistration> mapObservateur = new HashMap<>();

    private static <D extends Donnees> void memoriserObservateurServeur(Class<D> classeDonnees, ListenerRegistration listenerRegistration){
        GLog.appel(EntrepotDeDonnees.class);
        mapObservateur.put(classeDonnees, listenerRegistration);
    }

    public static <D extends Donnees> void effacerObservateur(final Class<D> classeDonnees){
        GLog.appel(EntrepotDeDonnees.class);
        if(mapObservateur.containsKey(classeDonnees)){
            mapObservateur.get(classeDonnees).remove();
        }
    }

    private static <D extends Donnees> void observerUnDocument(Class<D> classeDonnees, Observateur<D> observateurClient, DocumentChange documentChange){
        GLog.appel(EntrepotDeDonnees.class);
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
        GLog.appel(EntrepotDeDonnees.class);
        for(DocumentChange documentChange : queryDocumentSnapshots.getDocumentChanges()){
            observerUnDocument(classeDonnees, observateurClient, documentChange);
        }
    }

    private static <D extends Donnees> void observerDocumentsOuCreerNouveau(Class<D> classeDonnees, Observateur<D> observateurClient, QuerySnapshot queryDocumentSnapshots){
        GLog.appel(EntrepotDeDonnees.class);
        if(queryDocumentSnapshots.isEmpty()){
            observateurClient.notifierNouvellesDonnees(creerDonnees(classeDonnees));
        }else{
            observerDocumentsExistants(classeDonnees, observateurClient, queryDocumentSnapshots);
        }

    }

    private static <D extends Donnees> com.google.firebase.firestore.EventListener<QuerySnapshot> creerObservateurServeur(final Class<D> classeDonnees, final Observateur<D> observateurClient){
        GLog.appel(EntrepotDeDonnees.class);
        EventListener<QuerySnapshot> listener = new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                GLog.appel(this);
                if(queryDocumentSnapshots != null){
                    observerDocumentsOuCreerNouveau(classeDonnees, observateurClient, queryDocumentSnapshots);
                }
            }
        };
        return listener;
    }

    private static <D extends Donnees> Query creerRequete(Class<D> classeDonnees){
        GLog.appel(EntrepotDeDonnees.class);
        CollectionReference collection = firestore.collection(nomCollection(classeDonnees));

        return collection.whereEqualTo(FieldPath.documentId(), idDocument(classeDonnees));
    }

    private static ListenerRegistration ajouterObservateurServeur(Query requete, com.google.firebase.firestore.EventListener<QuerySnapshot> observateurServeur){
        GLog.appel(EntrepotDeDonnees.class);
        ListenerRegistration listenerRegistration = requete.addSnapshotListener(observateurServeur);

        return listenerRegistration;
    }

    public static <D extends Donnees> void observerDonnees(final Class<D> classeDonnees, final Observateur<D> observateurClient){
        GLog.appel(EntrepotDeDonnees.class);

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

    private static String idDocument(Class<? extends Donnees> classeDonnees){
        GLog.appel(EntrepotDeDonnees.class);

        String id;
        if(nomCollection(classeDonnees).equals("DPartieReseau")){
            id = GConstantes.USAGER_DEFAUT;
        }else{
            id = GUsagerCourant.getId();
        }
        return id;
    }

    private static DocumentReference referenceDocument(Class<? extends Donnees> classeDonnees){
        GLog.appel(EntrepotDeDonnees.class);
        return firestore.collection(nomCollection(classeDonnees)).document(idDocument(classeDonnees));
    }

    public static <D extends Donnees> void sauvegarderDonneesSurServeur(D donnees){
        GLog.appel(EntrepotDeDonnees.class);
        referenceDocument(donnees.getClass()).set(donnees);
    }






}

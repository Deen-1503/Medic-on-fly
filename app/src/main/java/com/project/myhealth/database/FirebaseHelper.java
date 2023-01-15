package com.project.myhealth.database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.project.myhealth.utils.RVAdapter;

import java.util.ArrayList;
public class FirebaseHelper{


    private final static String TAG = "FirestoreHelper: ";

    private DocumentReference docRef;
    private boolean isLogIn;
    Profile profile = new Profile();

    private static FirebaseHelper firebaseHelper;

    public static FirebaseHelper getInstance() {
        if (firebaseHelper == null) {
            firebaseHelper = new FirebaseHelper();
        }
        return firebaseHelper;
    }

    public boolean isLogIn() {
        return isLogIn;
    }
    public void setLogIn(boolean logIn) {
        isLogIn = logIn;
    }

    private FirebaseHelper() {
        FirebaseFirestore fStore = FirebaseFirestore.getInstance();
        FirebaseAuth fAuth = FirebaseAuth.getInstance();
        FirebaseUser fUser = fAuth.getCurrentUser();
        setLogIn(false);

        if (fUser != null) {
            setLogIn(true);
            String userEmail = fUser.getEmail();
            CollectionReference FIREBASE_COL_REF_ROOT = fStore.collection("Users");
            docRef = FIREBASE_COL_REF_ROOT.document(userEmail);
            profile();
        }
    }

    public Profile getProfile() {
        return profile();
    }

    public Profile profile() {
        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException e) {
                if (value != null) {
                    FirebaseHelper.this.profile.setEmail(value.getId());
                    FirebaseHelper.this.profile = value.get("profile", Profile.class);
                }
            }
        });
    return this.profile;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void loadData(String collectionName, Context context, RecyclerView recyclerView, int layout) {
        ArrayList<DataSet> list = new ArrayList<>();
        RVAdapter adapter = new RVAdapter(recyclerView.getContext(), list, layout);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setHasFixedSize(true);

        CollectionReference colRef = docRef.collection(collectionName);
        colRef.addSnapshotListener((query, e) -> {
            if (e != null) {
                Log.e(TAG,e.getMessage());
                return;
            }
            if(query != null) {
                list.clear();
                for (DocumentChange documentChange : query.getDocumentChanges()) {
                    switch (documentChange.getType()){
                        case ADDED:
                            list.add(documentChange.getDocument().toObject(DataSet.class));
                            adapter.notifyDataSetChanged();
                            break;
                        case MODIFIED:
                            break;
                        case REMOVED:
                            break;
                        default:
                            throw new IllegalStateException("Unexpected value: " + documentChange.getType());
                    }
                }
            }

        });
    }
}

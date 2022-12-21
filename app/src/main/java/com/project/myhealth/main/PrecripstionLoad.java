package com.project.myhealth.main;

import android.util.Log;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.project.myhealth.utils.DataSet;
import com.project.myhealth.utils.RVAdapter;

import java.util.ArrayList;
import java.util.Objects;

public class PrecripstionLoad implements EventListener<QuerySnapshot>{
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    DocumentReference docRef;

    ArrayList<DataSet> list;
    RVAdapter adapter;
    String firebaseDocument;



    public PrecripstionLoad(RVAdapter adapter, ArrayList<DataSet> list, String firebaseDocument){
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        this.list = list;
        this.adapter = adapter;
        this.firebaseDocument = firebaseDocument;
    }

    public void loadData(){
        if (fAuth.getCurrentUser() != null){
            docRef = fStore.collection("Users")
                    .document(Objects.requireNonNull(fAuth.getCurrentUser().getEmail()));


            CollectionReference colRef = docRef
                    .collection(firebaseDocument);

            colRef.addSnapshotListener(this);
        }
    }
    @Override
    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
            if (error != null){
                Log.e("Firestore",error.getMessage());
                return;
            }

            for (DocumentChange dc : value.getDocumentChanges()){
                if (dc.getType() == DocumentChange.Type.ADDED) {
                    list.add(dc.getDocument().toObject(DataSet.class));
                }
                adapter.notifyDataSetChanged();
            }
    }
}

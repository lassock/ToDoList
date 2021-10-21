package com.example.todolist2;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class FireBaseUtil {
    public static final String TAG = "Tag";
    static CollectionReference mRcollecttion;
    public  static String Task_collection ="taches";


    public  static CollectionReference getReferenceFirestore(String collectionName){
        if (mRcollecttion==null){
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            mRcollecttion = db.collection(collectionName);
            return mRcollecttion;

        }
        return mRcollecttion;

    }

    public  static  void add(Tache tache){
        FireBaseUtil.getReferenceFirestore(Task_collection)
                .add(tache)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "onSuccess: ");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "onSuccess: ");
                    }
                });
    }
}

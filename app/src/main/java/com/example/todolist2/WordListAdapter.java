package com.example.todolist2;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {
    private final ArrayList<Tache> taches;
    LayoutInflater mInflater;
public  static String TAG = "tag_adapter";

    public WordListAdapter(ArrayList<Tache> taches) {
        this.taches = taches;

       // mInflater = LayoutInflater.from(context);
      //  taches = new ArrayList<>();


        FireBaseUtil
                .getReferenceFirestore(FireBaseUtil.Task_collection


                )
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot snapshots, @Nullable FirebaseFirestoreException e) {

                        if (e != null) {
                            Log.w(TAG, "listen:error", e);
                            return;
                        }

                        for (DocumentChange dc : snapshots.getDocumentChanges()) {
                            switch (dc.getType()) {
                                case ADDED:
                                    Log.d(TAG, "Insertion" + dc.getDocument().getData());
                                    Tache task = dc.getDocument().toObject(Tache.class);
                                    task.setId(dc.getDocument().getId());
                                    taches.add(0, task);
                                    WordListAdapter.this.notifyItemInserted(0);
                                    break;
                                case MODIFIED:
                                    Log.d(TAG, "Modified city: " + dc.getDocument().getData());
                                    Tache tache = dc.getDocument().toObject(Tache.class);
                                    tache.setId(dc.getDocument().getId());
                                    int j=getIndex(taches,tache);
                                    Log.d(TAG, "onEvent: "+ " ----  " + j + " ------ "+tache);

                                    if (j!=-1){
                                        taches.remove(j);
                                        taches.add(j,tache);
                                        WordListAdapter.this.notifyItemChanged(j);
                                    }

                                    break;
                                case REMOVED:
                                    Tache tache1 = dc.getDocument().toObject(Tache.class);
                                    tache1.setId(dc.getDocument().getId());
                                    int k =getIndex(taches,tache1) ;
                                    if (k !=-1){
                                        taches.remove(k);
                                        WordListAdapter.this.notify();
                                    }


                                    Log.d(TAG, "Removed city: " + dc.getDocument().getData());
                                    break;
                            }
                        }

                    }
                });
    }
    public  int getIndex(ArrayList<Tache> taches , Tache tache ){
        int i=0;
        int index = -1;
        while (index == -1 && i<taches.size()){
            if (taches.get(i).getId().equals(tache.getId())){
                index=i;
            }
            i++;
        }
        return index;
    }

    @NonNull
    @Override
    public WordListAdapter.WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item,
                parent, false);
        return new WordViewHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WordListAdapter.WordViewHolder holder, int position) {
        Tache mCurrent = taches.get(position);
        holder.checkBox.setText(mCurrent.getNon_tache());
        holder.checkBox.setChecked(mCurrent.isFini());

    }

    @Override
    public int getItemCount() {
        return taches.size();
    }
    public class WordViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;
        ImageButton spinner;
        ImageButton imageButton;
        TextView textView;
        public WordViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkBox_taches);
            spinner = itemView.findViewById(R.id.spiner);
            //imageButton = itemView.findViewById(R.id.menu);
            //textView = itemView.findViewById(R.id.menu);



        }
    }
}

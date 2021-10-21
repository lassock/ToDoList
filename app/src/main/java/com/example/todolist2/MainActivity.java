package com.example.todolist2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.todolist2.databinding.ActivityMainBinding;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private ArrayList<Tache> taches = new ArrayList<>();
    private RecyclerView mrecycleview ;
    private WordListAdapter madaptateur;
    private ImageButton imageButton;
    private TextView nom_tache;
    public ActivityMainBinding binding;
ConstraintLayout layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setClickListener();
        setContentView(R.layout.activity_main);



        imageButton = findViewById(R.id.spiner);


        layout = (ConstraintLayout) LayoutInflater.from(this).inflate(R.layout.layout_item_spiner,null,false);


        mrecycleview = findViewById(R.id.recyclerview);
        taches.add(new Tache("Recevoir les cour de poo de lundi ",false));
        taches.add(new Tache("acheter des fruits pour maman",false));
        /**


        taches.add(new Tache("Aller au marche pour acheter des fruits et legume ",false));
        taches.add(new Tache("Faire le sport au stade",false));
        taches.add(new Tache("Aller a l'ecole",false));
         */

        madaptateur = new WordListAdapter(taches);
        mrecycleview.setAdapter(madaptateur);
        mrecycleview.setLayoutManager(new LinearLayoutManager(this));
    }
    public void onClick1(View view){
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        alert.setView(layout)
                .setPositiveButton("ENREGISTER", (d, v) -> {
                    EditText editText = layout.findViewById(R.id.editText_note);
                    String string = editText.getText().toString();
                    Tache tache1 = new Tache(string,false);

                     Tache tache = new Tache(string,false);
                    taches.add(0,tache);
                    taches.add(0, tache1);

                    FireBaseUtil.getReferenceFirestore("taches");
                    FireBaseUtil.add(tache);
                })
                .setNegativeButton("ANNULER",(d,v) ->{
                    d.cancel();
                })
                .setOnDismissListener(d->{
                    ((ViewGroup)layout.getParent()).removeView(layout);
                })
                .show();
    }
     private void setClickListener(){
        binding.edCommentId.setOnClickListener(view ->  {

             view.setEnabled(false);
             view.setFocusable(false);

             BottomSheetTache bottomSheetDialog = new BottomSheetTache(this);
             bottomSheetDialog.show();

             EditText editResult = bottomSheetDialog.binding.editTextTextPersonName;
             Button btn_envoyer = bottomSheetDialog.binding.btnEnvoyer;


            bottomSheetDialog.setOnCancelListener(d -> {
                view.setEnabled(true);
                view.setFocusable(true);
                //isCurrent=false;
            });
        });








     }

}
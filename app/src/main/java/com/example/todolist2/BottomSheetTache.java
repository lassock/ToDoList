package com.example.todolist2;

import android.content.Context;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;

import com.example.todolist2.databinding.BottomsheppAddTacheBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class BottomSheetTache extends BottomSheetDialog {
   // public BottomsheetAddTacheBinding binding;
    public BottomsheppAddTacheBinding binding;

    public BottomSheetTache(@NonNull Context context) {
        super(context,R.style.bottomSheetDialog);
        binding = BottomsheppAddTacheBinding.bind(
                LayoutInflater.from(context).inflate(
                        R.layout.bottomshepp_add_tache, null, false));
        setCancelable(false);
        //setClickingListeners();
        setContentView(binding.getRoot());

    }
    //private void setClickingListeners() {
       // binding.closeBtn.setOnClickListener(v -> cancel());
   // }

    @Override
    public void onBackPressed() {
        if(isShowing())
            cancel();
    }


}

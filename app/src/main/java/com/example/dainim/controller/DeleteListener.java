package com.example.dainim.controller;

import android.content.DialogInterface;
import android.view.View;

import androidx.appcompat.app.AlertDialog;

import com.example.dainim.R;
import com.example.dainim.view.ProfileActivity;

public class DeleteListener implements View.OnClickListener {
    private ProfileActivity profilActivity;

    public DeleteListener(ProfileActivity profilActivity){
        this.profilActivity = profilActivity;
    }
    @Override
    public void onClick(View v) {
        new AlertDialog.Builder(profilActivity)
                .setMessage(R.string.popup_message_confirmation_delete_account)
                .setPositiveButton(R.string.popup_message_choice_yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        profilActivity.deleteUserFromFirebase();
                    }
                })
                .setNegativeButton(R.string.popup_message_choice_no, null)
                .show();
    }
}

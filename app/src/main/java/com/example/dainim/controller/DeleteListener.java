package com.example.dainim.controller;

import android.content.DialogInterface;
import android.view.View;
import androidx.appcompat.app.AlertDialog;
import com.example.dainim.R;
import com.example.dainim.view.ProfileActivity;

/**
 * Event for delete Account
 */
public class DeleteListener implements View.OnClickListener {
    private ProfileActivity profileActivity;

    /**
     * Constructor
     * @param profileActivity ProfileActivity
     */
    public DeleteListener(ProfileActivity profileActivity){
        this.profileActivity = profileActivity;
    }
    @Override
    public void onClick(View v) {
        new AlertDialog.Builder(profileActivity)
                .setMessage(R.string.popup_message_confirmation_delete_account)
                .setPositiveButton(R.string.popup_message_choice_yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        profileActivity.deleteUserFromFirebase();
                    }
                })
                .setNegativeButton(R.string.popup_message_choice_no, null)
                .show();
    }
}

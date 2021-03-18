package com.example.dainim.controller;

import android.view.View;

import com.example.dainim.view.ProfileActivity;

/**
 * Event Update in FireBase
 */
public class UpdateListener implements View.OnClickListener {
    /**
     * ProfileActivity
     */
    private ProfileActivity profileActivity;

    /**
     * Constructor
     * @param profileActivity ProfileActivity
     */
    public UpdateListener(ProfileActivity profileActivity){
        this.profileActivity = profileActivity;
    }
    @Override
    public void onClick(View v) {
        profileActivity.updateUsernameInFirebase();
    }
}

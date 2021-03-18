package com.example.dainim.controller;

import android.view.View;

import com.example.dainim.view.ProfileActivity;

/**
 * Event Sign out FireBase
 */
public class SignoutListener implements View.OnClickListener {
    /**
     * ProfileActivity
     */
    private ProfileActivity profileActivity;

    /**
     * Constructor
     * @param profileActivity ProfileActivity
     */
    public SignoutListener(ProfileActivity profileActivity){
        this.profileActivity = profileActivity;
    }
    @Override
    public void onClick(View v) {
        profileActivity.signOutUserFromFirebase();
    }
}

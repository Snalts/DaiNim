package com.example.dainim.controller;

import android.view.View;

import com.example.dainim.view.ProfileActivity;

public class UpdateListener implements View.OnClickListener {
    private ProfileActivity profilActivity;

    public UpdateListener(ProfileActivity profilActivity){
        this.profilActivity = profilActivity;
    }
    @Override
    public void onClick(View v) {
        profilActivity.updateUsernameInFirebase();
    }
}

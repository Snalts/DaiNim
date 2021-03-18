package com.example.dainim.controller;

import android.view.View;

import com.example.dainim.view.ProfilActivity;

public class UpdateListener implements View.OnClickListener {
    private ProfilActivity profilActivity;

    public UpdateListener(ProfilActivity profilActivity){
        this.profilActivity = profilActivity;
    }
    @Override
    public void onClick(View v) {
        profilActivity.updateUsernameInFirebase();
    }
}

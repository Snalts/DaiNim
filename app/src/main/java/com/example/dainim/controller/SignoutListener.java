package com.example.dainim.controller;

import android.view.View;

import com.example.dainim.view.ProfilActivity;

public class SignoutListener implements View.OnClickListener {
    private ProfilActivity profilActivity;

    public SignoutListener(ProfilActivity profilActivity){
        this.profilActivity = profilActivity;
    }
    @Override
    public void onClick(View v) {
        profilActivity.signOutUserFromFirebase();
    }
}

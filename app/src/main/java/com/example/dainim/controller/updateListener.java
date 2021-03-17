package com.example.dainim.controller;

import android.view.View;

import com.example.dainim.view.ProfilActivity;

public class updateListener implements View.OnClickListener {
    private ProfilActivity profilActivity;

    public updateListener(ProfilActivity profilActivity){
        this.profilActivity = profilActivity;
    }
    @Override
    public void onClick(View v) {
        profilActivity.updateUsernameInFirebase();
    }
}

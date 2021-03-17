package com.example.dainim.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.dainim.R;
import com.firebase.ui.auth.AuthUI;
import com.google.android.material.navigation.NavigationView;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;

import reactor.util.annotation.Nullable;

public abstract class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    private static final int RC_SIGN_IN = 123;

    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private Intent intent;
    private Intent intent_profil;




    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.intent = new Intent(getApplicationContext(), PlanningActivity.class);
        this.intent_profil = new Intent(getApplicationContext(), PlanningActivity.class);
    }

    @Override
    public void onBackPressed()
    {
        // 5 - Handle back click to close menu
        if(this.drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            this.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // 4 - Handle Navigation Item Click
        int id = item.getItemId();

        switch (id)
        {
            case R.id.activity_main_drawer_signup:

                break;
            case R.id.activity_main_drawer_login:
                if(isCurrentUserLogged()) {
                    startActivity(intent_profil);
                }
                else{
                    startSignInActivity();
                }
                break;
            case R.id.activity_main_drawer_planning:
                startActivity(intent);
                break;
            case R.id.activity_main_drawer_settings:
                break;
            default:
                break;
        }

        this.drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    // ---------------------
    // CONFIGURATION
    // ---------------------

    protected void configureAll()
    {
        this.configureToolBar();
        this.configureDrawerLayout();
        this.configureNavigationView();
    }

    private void configureToolBar()
    {
        this.toolbar = (Toolbar) findViewById(R.id.activity_main_toolbar);
        setSupportActionBar(toolbar);
    }

    private void configureNavigationView()
    {
        this.navigationView = (NavigationView) findViewById(R.id.activity_main_nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void configureDrawerLayout()
    {
        this.drawerLayout = (DrawerLayout) findViewById(R.id.activity_main_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, this.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    // --------------------
    // UTILS
    // --------------------

    protected  void startSignInActivity(){
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setTheme(R.style.LoginTheme)
                        .setAvailableProviders(
                                Arrays.asList(new AuthUI.IdpConfig.EmailBuilder().build()))
                        .setIsSmartLockEnabled(false, true)
                        .setLogo(R.drawable.ic_logo_auth)
                        .build(),
                RC_SIGN_IN);
    }

    protected FirebaseUser getCurrentUser(){ return FirebaseAuth.getInstance().getCurrentUser();}

    protected Boolean isCurrentUserLogged(){ return (getCurrentUser() != null); }
}

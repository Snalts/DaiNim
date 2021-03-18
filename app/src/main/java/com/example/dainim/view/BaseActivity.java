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
import com.example.dainim.controller.UserHelper;
import com.example.dainim.model.Anime;
import com.example.dainim.model.User;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Abtstract class implementing the base of all the application activities
 */
public abstract class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final int RC_SIGN_IN = 123;

    /**
     * The application toolbar
     */
    private Toolbar toolbar;

    /**
     * The navigation menu's navigation view
     */
    private NavigationView navigationView;

    /**
     * The main layout of all activities
     */
    private DrawerLayout drawerLayout;

    /**
     * The intent that start the main activity
     */
    private Intent intent_menu;

    /**
     * The intent that start the planning activity
     */
    private Intent intent_planning;

    /**
     * The intent that start the profile activity
     */
    private Intent intent_profile;

    /**
     * The current logged in user
     */
    protected User currentUser;

    /**
     * The user's favorite animes list
     */
    private List<Anime> arr;

    /*
     * Method called when BaseActivity is created
     * @param savedInstanceState The activity saved instance state
    */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent_menu = new Intent(getApplicationContext(), MainActivity.class);
        intent_planning = new Intent(getApplicationContext(), PlanningActivity.class);
        intent_profile = new Intent(getApplicationContext(), ProfileActivity.class);
    }

    @Override
    public void onBackPressed() {
        // 5 - Handle back click to close menu
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // 4 - Handle Navigation Item Click
        int id = item.getItemId();

        switch (id) {
            /*case R.id.activity_main_drawer_profil:
                startSignInActivity();
                break;*/
            // Profile page
            case R.id.activity_main_drawer_profil:
                if (isCurrentUserLogged()) {
                    this.getUserInFireBase();
                    startActivity(intent_profile);
                } else {
                    startSignInActivity();
                }
                break;
            // Main page
            case R.id.activity_main_drawer_menu:
                startActivity(intent_menu);
                break;
            // Planning page
            case R.id.activity_main_drawer_planning:
                startActivity(intent_planning);
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

    /**
     * Method that call all configuration methods
     */
    protected void configureAll() {
        this.configureToolBar();
        this.configureDrawerLayout();
        this.configureNavigationView();
    }

    /**
     * Method that sets up the activity toolbar
     */
    private void configureToolBar() {
        this.toolbar = (Toolbar) findViewById(R.id.activity_main_toolbar);
        setSupportActionBar(toolbar);
    }

    /**
     * Method that sets up the activity navigation view
     */
    private void configureNavigationView() {
        this.navigationView = (NavigationView) findViewById(R.id.activity_main_nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    /**
     * Method that sets up the activity drawer layout
     */
    private void configureDrawerLayout() {
        this.drawerLayout = (DrawerLayout) findViewById(R.id.activity_main_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, this.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    // --------------------
    // UTILS
    // --------------------

    protected void startSignInActivity() {
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        this.handleResponseAfterSignIn(requestCode, resultCode, data);
    }

    private void handleResponseAfterSignIn(int requestCode, int resultCode, Intent data) {

        IdpResponse response = IdpResponse.fromResultIntent(data);

        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) { // SUCCESS
                this.createUserInFirestore();
                this.getUserInFireBase();
            }
        }
    }

    private void createUserInFirestore() {
        if (this.getCurrentUser() != null) {
            ArrayList<Anime> arr_list = new ArrayList<Anime>();
            String username = this.getCurrentUser().getDisplayName();
            String uid = this.getCurrentUser().getUid();

            UserHelper.createUser(uid, username, arr_list).addOnFailureListener(onFailureListener());
        }
    }

    protected void updateAddArrayInFirebase(Anime a){
        UserHelper.getUser(this.getCurrentUser().getUid()).addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                currentUser = documentSnapshot.toObject(User.class);
                System.out.println(currentUser.getFav_list());
                arr = currentUser.getFav_list();
                arr.add(a);
                UserHelper.updateFavAnime(currentUser.getuId(),arr);
            }
        });
    }

    protected void updateDeleteArrayInFirebase(Anime a) {
        UserHelper.getUser(this.getCurrentUser().getUid()).addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                currentUser = documentSnapshot.toObject(User.class);
                System.out.println("Test" + currentUser.getFav_list());
                arr = currentUser.getFav_list();
                int id = arr.indexOf(a);
                System.out.println(id);
                if(id != -1) {
                    arr.remove(id);
                    System.out.println("Arr");
                    UserHelper.updateFavAnime(currentUser.getuId(), arr);
                }
            }
        });
    }

    protected void getUserInFireBase() {
        UserHelper.getUser(this.getCurrentUser().getUid()).addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                currentUser = documentSnapshot.toObject(User.class);

            }
        });
    }

    protected FirebaseUser getCurrentUser() {
        return FirebaseAuth.getInstance().getCurrentUser();
    }

    protected Boolean isCurrentUserLogged() {
        return (getCurrentUser() != null);
    }

    protected OnFailureListener onFailureListener() {
        return new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                System.out.print("ICI ICI ICI ICI");
                System.out.println(getString(R.string.error_unknown_error));
                System.err.println("Erreur : " + e.getMessage());
            }
        };
    }
}

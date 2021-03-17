package com.example.dainim.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.dainim.model.Anime;
import com.example.dainim.model.AnimeSeason;
import com.example.dainim.model.EnumSeason;
import com.firebase.ui.auth.AuthUI;
import com.google.android.material.navigation.NavigationView;

import com.example.dainim.R;
import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{

    //FOR DESIGN
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private AnimeView av;
    private Object obj;
    private TextView tv;
    private Anime a2;
    private Intent intent;
    private static final int RC_SIGN_IN = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = new Intent(getApplicationContext(),animeFrame.class);

        // 6 - Configure all views

        try
        {
            obj = new Object();
            Button b = (Button) findViewById(R.id.compte);

            /*Connection button*/
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    this.startSignInActivity();
                }
                // 2 - Launch Sign-In Activity
                public void startSignInActivity(){
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
            });


            //AnimeSeason a = AnimeSeason.getInstance(obj);
            // av = new AnimeView(1, tv, obj);

            /*Anime a = av.getAnime();
            String url = a.getImage();
            ImageButton iv = (ImageButton) findViewById(R.id.view);
            Picasso.get().load(url).into(iv);

            this.a2 = new Anime(40028, obj);
            String url2 = a2.getImage();
            ImageButton iv2 = (ImageButton) findViewById(R.id.view2);

            Picasso.get().load(url2).into(iv2);
            */
            /*InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");*/
            //iv.setImageDrawable(d);
            //iv.setImageResource(R.drawable.ic_openclassrooms);
           // System.out.println("Affichage dans le Main :" + a.getAnime(2021,EnumSeason.SPRING,0));
           // System.out.println("Affichage dans le Main :" + a.getAnime(2021,EnumSeason.WINTER,0));

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        this.configureToolBar();

        this.configureDrawerLayout();

        this.configureNavigationView();
    }


    public void clickNew(View v){
        intent.putExtra("anime",a2);
        /*intent.putExtra("title",a2.getTitle());
        intent.putExtra("url_image",a2.getImage());
        intent.putExtra("syno",a2.getSynopsis());
        */startActivity(intent);
    }
    /*
        //FOR DESIGN
        // 1 - Get Coordinator Layout
        @BindView(R.id.main_activity_coordinator_layout) CoordinatorLayout coordinatorLayout;


        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            // 4 - Handle SignIn Activity response on activity result
            this.handleResponseAfterSignIn(requestCode, resultCode, data);
        }


        // --------------------
        // UI
        // --------------------

        // 2 - Show Snack Bar with a message
        private void showSnackBar(CoordinatorLayout coordinatorLayout, String message){
            Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_SHORT).show();
        }

        // --------------------
        // UTILS
        // --------------------

        // 3 - Method that handles response after SignIn Activity close
        private void handleResponseAfterSignIn(int requestCode, int resultCode, Intent data){

            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (requestCode == RC_SIGN_IN) {
                if (resultCode == RESULT_OK) { // SUCCESS
                    showSnackBar(this.coordinatorLayout, getString(R.string.connection_succeed));
                } else { // ERRORS
                    if (response == null) {
                        showSnackBar(this.coordinatorLayout, getString(R.string.error_authentication_canceled));
                    } else if (response.getErrorCode() == ErrorCodes.NO_NETWORK) {
                        showSnackBar(this.coordinatorLayout, getString(R.string.error_no_internet));
                    } else if (response.getErrorCode() == ErrorCodes.UNKNOWN_ERROR) {
                        showSnackBar(this.coordinatorLayout, getString(R.string.error_unknown_error));
                    }
                }
            }
        }
    */
    /*--------------------------------------------------------------------------*/

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
            case R.id.activity_main_drawer_news :
                break;
            case R.id.activity_main_drawer_profile:
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

    // 1 - Configure Toolbar
    private void configureToolBar()
    {
        this.toolbar = (Toolbar) findViewById(R.id.activity_main_toolbar);
        setSupportActionBar(toolbar);
    }

    // 2 - Configure Drawer Layout
    private void configureDrawerLayout()
    {
        this.drawerLayout = (DrawerLayout) findViewById(R.id.activity_main_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    // 3 - Configure NavigationView
    private void configureNavigationView()
    {
        this.navigationView = (NavigationView) findViewById(R.id.activity_main_nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void configureFrameLayout()
    {
        AnimeSeason anime_season = AnimeSeason.getInstance(new Object());
            //anime_season.get
    }
}
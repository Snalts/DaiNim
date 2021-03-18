package com.example.dainim.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.dainim.controller.AnimeClickListener;
import com.example.dainim.controller.UserHelper;
import com.example.dainim.model.Anime;
import com.example.dainim.model.AnimeSeason;
import com.example.dainim.model.EnumSeason;

import com.firebase.ui.auth.AuthUI;

import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.material.navigation.NavigationView;

import com.example.dainim.R;
import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener
{
    //FOR DESIGN
    private AnimeView av;
    private Object obj;
    private TextView tv;
    private Anime anime;
    private Intent intent;
    private Anime a2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = new Intent(getApplicationContext(), AnimeActivity.class);
        obj = new Object();

        // 6 - Configure all views
        this.configureAll();
        this.configureFrameLayout();
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

    // ---------------------
    // CONFIGURATION
    // ---------------------

    // Configure FrameLayout
    private void configureFrameLayout()
    {
        try
        {
            AnimeSeason anime_season = AnimeSeason.getInstance(new Object());
            TableLayout table_layout = (TableLayout) findViewById(R.id.tablelayout);

            int c = Calendar.getInstance().get(Calendar.YEAR);
            EnumSeason e = EnumSeason.getThisSeason();

            for(int i = 0; i < anime_season.getAnimeList(c, e).size(); i += 3)
            {
                TableRow table_row_image = new TableRow(this);

                for(int j = 0; j < 3; j++)
                {
                    if(i + j < anime_season.getAnimeList(c, e).size())
                    {
                        displayAnime(anime_season, table_row_image, i + j);
                    }
                }

                table_layout.addView(table_row_image);
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }



    private void displayAnime(AnimeSeason anime_season, TableRow table_row_image, int i) throws JSONException
    {
        int c = Calendar.getInstance().get(Calendar.YEAR);
        EnumSeason e = EnumSeason.getThisSeason();

        Anime anime = anime_season.getAnime(c, e, i);
        String url = anime.getImage();
        ImageButton image_button = new ImageButton(this);

        //image_button.setMinimumHeight(500);
        //image_button.setMinimumWidth(370);
        image_button.setClickable(true);
        image_button.setOnClickListener(new AnimeClickListener(this, this.intent, anime));
        Picasso.get().load(url).into(image_button);
        table_row_image.addView(image_button);
    }

}
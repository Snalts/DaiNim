package com.example.dainim.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.dainim.controller.AnimeClickListener;
import com.example.dainim.model.Anime;
import com.example.dainim.model.AnimeSeason;
import com.example.dainim.model.EnumSeason;
import com.google.android.material.navigation.NavigationView;

import com.example.dainim.R;
import com.squareup.picasso.Picasso;

import org.json.JSONException;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{

    //FOR DESIGN
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private AnimeView av;
    private Object obj;
    private TextView tv;
    private Anime anime;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = new Intent(getApplicationContext(), AnimeActivity.class);

        // 6 - Configure all views

        this.configureToolBar();
        this.configureDrawerLayout();
        this.configureNavigationView();
        this.configureFrameLayout();
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
                break;
            case R.id.activity_main_drawer_planning:
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
        try
        {
            AnimeSeason anime_season = new AnimeSeason(2021, EnumSeason.WINTER, new Object());
            TableLayout table_layout = (TableLayout) findViewById(R.id.tablelayout);

            for(int i = 0; i < anime_season.getAnimeList().size(); i += 3)
            {
                TableRow table_row_image = new TableRow(this);

                for(int j = 0; j < 3; j++)
                {
                    if(i + j < anime_season.getAnimeList().size())
                    {
                        displayAnime(anime_season, table_row_image, i + j);
                    }
                }

                table_layout.addView(table_row_image);
            }
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    private void displayAnime(AnimeSeason anime_season, TableRow table_row_image, int i) throws JSONException
    {
        Anime anime = anime_season.getAnime(i);
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
package com.example.dainim.view;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.dainim.model.Anime;
import com.example.dainim.model.AnimeSeason;
import com.example.dainim.model.Season;
import com.google.android.material.navigation.NavigationView;

import com.example.dainim.R;
import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.io.InputStream;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{

    //FOR DESIGN
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private AnimeView av;
    private Object obj;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 6 - Configure all views

        try
        {
            obj = new Object();
            av = new AnimeView(1, tv, obj);

            Anime a = av.getAnime();
            String url = a.getImage();
            ImageView iv = (ImageView) findViewById(R.id.imageview);
            Picasso.get().load(url).into(iv);

            Anime a2 = new Anime(40028, obj);
            String url2 = a2.getImage();
            ImageView iv2 = (ImageView) findViewById(R.id.imageview2);
            Picasso.get().load(url2).into(iv2);

            /*InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");*/
            //iv.setImageDrawable(d);
            //iv.setImageResource(R.drawable.ic_openclassrooms);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        this.configureToolBar();

        this.configureDrawerLayout();

        this.configureNavigationView();
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
        try
        {
            AnimeSeason anime_season = new AnimeSeason(2021, Season.WINTER, new Object());

            //anime_season.get
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
}
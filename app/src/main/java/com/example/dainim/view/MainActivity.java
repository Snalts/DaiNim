package com.example.dainim.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.dainim.controller.AnimeClickListener;
import com.example.dainim.model.Anime;
import com.example.dainim.model.AnimeSeason;
import com.example.dainim.model.EnumSeason;

import com.google.android.material.navigation.NavigationView;

import com.example.dainim.R;
import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.util.Calendar;

/**
 * Class implementing the main page activity
 */
public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener
{
    //FOR DESIGN
    /**
     * Object that synchronize the thread used in UrlConnect
     */
    private Object obj;

    /**
     * Intent that create the anime activity when anime buttons are clicked
     */
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = new Intent(getApplicationContext(), AnimeActivity.class);
        this.obj = new Object();

        // Configure all views
        this.configureAll();
        this.configureFrameLayout();
    }

    // ---------------------
    // CONFIGURATION
    // ---------------------

    /**
     * Method that configure the activity frame layout
     */
    private void configureFrameLayout()
    {
        try
        {
            AnimeSeason anime_season = AnimeSeason.getInstance(new Object());
            TextView text_view = findViewById(R.id.text_view);
            int c = Calendar.getInstance().get(Calendar.YEAR);
            TableLayout table_layout = (TableLayout) findViewById(R.id.tablelayout);

            text_view.setText("Season Anime " + EnumSeason.getThisSeason().getMinimum().substring(0, 1).toUpperCase() + EnumSeason.getThisSeason().getMinimum().substring(1) + " " + c);

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

    /**
     * Method that displays all animes from the current season
     * @param anime_season The current season
     * @param table_row_image A row of animes' images
     * @param i The index of the anime to display in the anime list
     * @throws JSONException Catched when anime data don't exist
     */
    private void displayAnime(AnimeSeason anime_season, TableRow table_row_image, int i) throws JSONException
    {
        int c = Calendar.getInstance().get(Calendar.YEAR);
        EnumSeason e = EnumSeason.getThisSeason();
        Anime anime = anime_season.getAnime(c, e, i);
        String url = anime.getImage();
        ImageButton image_button = new ImageButton(this);
        image_button.setClickable(true);
        image_button.setOnClickListener(new AnimeClickListener(this, this.intent, anime));
        image_button.setBackgroundColor(getResources().getColor(R.color.dark_2));
        Picasso.get().load(url).into(image_button);
        table_row_image.addView(image_button);
    }
}
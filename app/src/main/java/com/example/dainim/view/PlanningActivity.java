package com.example.dainim.view;

import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dainim.R;
import com.example.dainim.controller.NextButtonListener;
import com.example.dainim.controller.PreviousButtonListener;
import com.example.dainim.model.Anime;
import com.example.dainim.model.AnimeDay;
import com.example.dainim.model.AnimeWeek;
import com.example.dainim.model.EnumSeason;
import com.example.dainim.model.EnumWeek;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

/**
 * Class implementing the planning page activity
 */
public class PlanningActivity extends BaseActivity
{
    /**
     * Object that synchronize the thread used in UrlConnect
     */
    private Object obj;

    /**
     * The activity's linear layout
     */
    private LinearLayout linear_layout;

    /**
     * Anime from the current week's list
     */
    private AnimeWeek anime_week;

    /**
     * Week enumeration
     */
    private EnumWeek enum_week;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planning);
        this.obj = new Object();

        this.configureAll();

        this.linear_layout = (LinearLayout) findViewById(R.id.linear_layout_2);
        this.anime_week = AnimeWeek.getInstance(this.obj);

        setButtonListeners(this.anime_week);
        displayPlanning(this.anime_week.getCurrentDay(), EnumWeek.getCurrent());
    }

    /**
     * Method that displays the anime airing on a day
     * @param anime_day The day anime list
     * @param enum_week Week enumeration
     */
    public void displayPlanning(ArrayList<Anime> anime_day, EnumWeek enum_week)
    {
        this.linear_layout.removeAllViews();

        this.enum_week = enum_week;

        Collections.sort(anime_day);

        // Data information
        TextView text_view_season = findViewById(R.id.text_view_season);
        TextView text_view_day = findViewById(R.id.text_view_day);
        int c = Calendar.getInstance().get(Calendar.YEAR);

        text_view_season.setText("Saison " + EnumSeason.getThisSeason().getMinimum().substring(0, 1).toUpperCase() + EnumSeason.getThisSeason().getMinimum().substring(1) + " " + c);
        text_view_day.setText(enum_week.getMinimum().substring(0, 1).toUpperCase() + enum_week.getMinimum().substring(1));

        for (int i = 0; i < anime_day.size(); i++)
        {
            // Time information
            TextView text_view_time = new TextView(this);
            text_view_time.setText(anime_day.get(i).getTime());
            text_view_time.setTextColor(Color.WHITE);
            text_view_time.setTextSize(TypedValue.COMPLEX_UNIT_PX, 50);
            linear_layout.addView(text_view_time);

            // Anime image
            ImageView image_view = new ImageView(this);
            String url = anime_day.get(i).getImage();
            Picasso.get().load(url).into(image_view);
            linear_layout.addView(image_view);
        }
    }

    /**
     * Method that sets the next and previous buttons listeners
     * @param anime_week The anime week
     */
    private void setButtonListeners(AnimeWeek anime_week)
    {
        ImageButton previous = (ImageButton) findViewById(R.id.image_button_1);
        ImageButton next = (ImageButton) findViewById(R.id.image_button_2);

        previous.setOnClickListener(new PreviousButtonListener(this));
        next.setOnClickListener(new NextButtonListener(this));
    }

    /**
     * Getter on the anime week
     * @return The anime week
     */
    public AnimeWeek getAnimeWeek()
    {
        return this.anime_week;
    }

    /**
     * Getter on the week enumeration
     * @return The week enumeration
     */
    public EnumWeek getEnumWeek()
    {
        return this.enum_week;
    }
}

package com.example.dainim.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dainim.R;
import com.example.dainim.model.Anime;
import com.squareup.picasso.Picasso;

import org.json.JSONException;

public class animeFrame extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime_frame);
        Intent intent = getIntent();
        Anime a = (Anime) intent.getSerializableExtra("anime");
        String url2 = null;

             url2 = a.getImage();
            //url2 = intent.getStringExtra("url_image");
            ImageView iv2 = (ImageView) findViewById(R.id.ImageAnime);
            Picasso.get().load(url2).into(iv2);
            TextView v = (TextView) findViewById(R.id.textTitle);
            v.setText(a.getTitle());
         //   v.setText(intent.getStringExtra("title"));
            TextView v1 = (TextView) findViewById(R.id.textSyno);
         //   v1.setText(intent.getStringExtra("syno"));
            v1.setText(a.getSynopsis());

    }
}
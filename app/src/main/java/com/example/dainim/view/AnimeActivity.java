package com.example.dainim.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.dainim.R;
import com.example.dainim.model.Anime;
import com.squareup.picasso.Picasso;

public class AnimeActivity extends BaseActivity {

    private Toolbar toolbar;
    private Switch follow;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime);
        Intent intent = getIntent();
        Anime a = (Anime) intent.getSerializableExtra("anime");
        String url2 = null;

        url2 = a.getImage();
        //url2 = intent.getStringExtra("url_image");
        ImageView iv2 = (ImageView) findViewById(R.id.ImageAnime);
        Picasso.get().load(url2).into(iv2);
        TextView v = (TextView) findViewById(R.id.textTitle);
        v.setText(a.getTitle());
        //v.setText(intent.getStringExtra("title"));
        TextView v1 = (TextView) findViewById(R.id.textSyno);
        //v1.setText(intent.getStringExtra("syno"));
        v1.setText(a.getSynopsis());

        this.configureAll();
        this.follow = findViewById(R.id.switchFollow);
        this.follow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    updateAddArrayInFirebase(a);
                }
                else{
                    updateDeleteArrayInFirebase(a);
                }

            }
        });
    }
}
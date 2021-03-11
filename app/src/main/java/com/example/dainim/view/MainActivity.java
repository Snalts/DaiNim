package com.example.dainim.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.TextView;

import com.example.dainim.R;
import com.example.dainim.modele.ApiCom;

public class MainActivity extends AppCompatActivity
{
    private ApiCom api;
    protected Object obj;
    protected TextView tap;
    protected AnimView a;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        obj = new Object();
        /*api = new ApiCom();
        Log.d("M","Ab");
        try{
            api.get_search_anim();
        }
        catch (IOException e){
            Log.d("ME",e.toString());
        }*/
        /*StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Anime a = new Anime(42897);
        a.start();

        try {
            a.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            System.out.println(a.getTitle());
            System.out.println(a.getTitleJapanese());
            TextView api = (TextView) findViewById(R.id.apitest);
            api.setText(a.getTitle());
            System.out.println(a.getSynopsis());
        }
        catch(Exception e){
                Log.d("Erreur","CHiant");
        }

         */
        //LongRunningTask lrt = new LongRunningTask(42897);



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tap = ((TextView) findViewById(R.id.apitest));
        tap.setText("TEST");


        try {
            a= new AnimView(42897,tap,obj);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        getWindow().getDecorView().setBackgroundColor(Color.parseColor("#32353b"));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationIcon(R.drawable.menu);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        return true;
    }

    private void initToolbar()
    {

    }
}
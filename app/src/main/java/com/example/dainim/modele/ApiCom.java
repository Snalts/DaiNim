package com.example.dainim.modele;

/*
import android.text.method.ScrollingMovementMethod
import android.view.View;
import android.widget.*;
*//*import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
*/


import java.io.BufferedInputStream;
/*import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import android.util.Log;

import org.json.JSONException;

import org.json.JSONObject;
*/
public class ApiCom
{


    public ApiCom(){
    }

 /*   private String readStream(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader r = new BufferedReader(new InputStreamReader(is),1000);
        for (String line = r.readLine(); line != null; line =r.readLine()){
            sb.append(line);
        }
        is.close();
        return sb.toString();
    }
*/

    public void get_search_anim() {
/*
        URL url = new URL("https://api.jikan.moe/v3/season/2021/winter");

        String result;
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            result = readStream(in);
        } finally {
            urlConnection.disconnect();
        }

        Log.d("Test ", result);
*/
        //URL url = new URL("https://api.jikan.moe/v3/season/2021/winter");
/*        URL url = new URL("https://api.jikan.moe/v3/anime/1");
        Log.d("START","coucou");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        //Getting the response code
        int responsecode = conn.getResponseCode();
        if (responsecode != 200) {
            Log.d("TimeOUt","oupsi");
            throw new RuntimeException("HttpResponseCode: " + responsecode);

        } else {

            String inline = "";
            Scanner scanner = new Scanner(url.openStream());

            //Write all the JSON data into a string using a scanner
            Log.d("inline","ecriture");
            while (scanner.hasNext()) {

                inline += scanner.nextLine();
            }
            Log.d("inline",inline);

            //Close the scanner
            scanner.close();

            //Using the JSON simple library parse the string into a json object
            JSONParser parse = new JSONParser();
            Log.d("Try","Je suis là");
            try {
                JSONObject data_obj = (JSONObject) parse.parse(inline);
                //Get the required object from the above created object
                JSONObject obj = (JSONObject) data_obj.get("title");
                Log.d("Result","titre" + (String)data_obj.get("title"));
                //Get the required data using its key
                //Log.d("Result",(obj.get("title")).toString());
            } catch (ParseException e) {
                Log.d("Erreur", "ParseException" +e.toString());
            } catch (JSONException jsonE){
                Log.d("Erreur", "JsonException" + jsonE.toString());
            }
*/
        }
    }

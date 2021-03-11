package com.example.dainim.modele;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class UrlConnect extends Thread {
    private Object obj;
    private String s_url;
    JSONObject data;
    public UrlConnect(String s_url,Object obj){
        this.s_url = s_url;
        this.obj = obj;
    }
    @Override
    public void run() {
        try {
            synchronized (obj) {
                URL url = new URL(this.s_url);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.connect();

                //Getting the response code
                int responsecode = conn.getResponseCode();
                if (responsecode != 200) {
                    throw new RuntimeException("HttpResponseCode: " + responsecode);
                } else {

                    String inline = "";
                    Scanner scanner = new Scanner(url.openStream());

                    //Write all the JSON data into a string using a scanner
                    while (scanner.hasNext()) {
                        inline += scanner.nextLine();
                    }

                    //Close the scanner
                    scanner.close();

                    this.data = new JSONObject(inline);
                }
            }
        }
        catch (Exception e) {
            Log.d("Exception","Exception" + e.toString());
        }
    }

    public JSONObject getData() {
        return data;
    }
}

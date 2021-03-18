package com.example.dainim.model;

import android.util.Log;
import org.json.JSONObject;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * UrlConnect class, realise the connection to the API, get result and convert to JSONObject, extends Thread
 */
public class UrlConnect extends Thread {
    /**
     * Needed to Synchronize all Threads
     */
    private Object obj;
    /**
     * url request for API
     */
    private String s_url;
    /**
     * Response data for API
     */
    JSONObject data;
    public UrlConnect(String s_url,Object obj){
        this.s_url = s_url;
        this.obj = obj;
    }

    @Override
    public void run() {
        try {
            //Need to get synchronize methode and get data
            synchronized (obj) {
                obj.wait(4000);
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

    /**
     * returns response from API
     * @return data
     */
    public JSONObject getData() {
        return data;
    }
}

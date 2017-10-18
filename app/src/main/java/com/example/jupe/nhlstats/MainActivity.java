package com.example.jupe.nhlstats;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class MainActivity extends Activity {


    //http://live.nhl.com/GameData/SeasonSchedule-20172018.json
    //https://api.mysportsfeeds.com/v1.1/sample/pull/nhl/2016-2017-regular/daily_player_stats.json?fordate=20161015&
    //https://www.mysportsfeeds.com/data-feeds/nhl/feedlist/

    Button button, button1, button2;

    private TextView gamesData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button button2 = (Button) findViewById(R.id.buttonHit);
        gamesData = (TextView) findViewById(R.id.textView1);



        button2.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           new JSONTask().execute("http://live.nhl.com/GameData/SeasonSchedule-20172018.json");
                                       }
                                   });


        addListenerOnButton();
    }

    public void addListenerOnButton() {
        final Context context = this;

        button = (Button) findViewById(R.id.buttonStats);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent myIntent = new Intent(MainActivity.this, StatsActivity.class);
                startActivity(myIntent);
            }
        });
        button1 = (Button) findViewById(R.id.buttonBets);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent myIntent1 = new Intent(MainActivity.this, betsActivity.class);
                startActivity(myIntent1);
            }
        });

    }

    public class JSONTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            final HttpURLConnection[] connection = {null};
            final BufferedReader[] reader = {null};

            try {
                URL url = new URL(params[0]);
                connection[0] = (HttpURLConnection) url.openConnection();
                connection[0].connect();

                InputStream stream = connection[0].getInputStream();

                reader[0] = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();

                String line = "";
                while ((line = reader[0].readLine()) != null) {
                    buffer.append(line);
                }
                String finalJson = buffer.toString();

                JSONArray json = new JSONArray(finalJson);

                StringBuffer finalBufferedData = new StringBuffer();

                for(int i=0; i<json.length(); i++) {
                    JSONObject finalObject = json.getJSONObject(i);


                    String homeTeam = finalObject.getString("h");
                    String awayTeam = finalObject.getString("a");

                    if(finalObject.getString("h").equalsIgnoreCase("ANA") || finalObject.getString("a").equalsIgnoreCase("ANA")) {
                        finalBufferedData.append(awayTeam + " - " + homeTeam + "\n");
                    }

                }
                return finalBufferedData.toString();



            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (connection[0] != null) {
                    connection[0].disconnect();
                }
                try {
                    if (reader[0] != null) {
                        reader[0].close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            gamesData.setText(result);
        }
    }
}

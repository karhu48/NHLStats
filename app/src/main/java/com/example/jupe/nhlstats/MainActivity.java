package com.example.jupe.nhlstats;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;


import com.example.jupe.nhlstats.JSONDownloader;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    String jsonURL="http://live.nhl.com/GameData/SeasonSchedule-20172018.json";
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.list);

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(this);

    }

    public  void onClick(View v) {
        new JSONDownloader(MainActivity.this,jsonURL,lv).execute();
    }
}

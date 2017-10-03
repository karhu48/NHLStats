package com.example.jupe.nhlstats;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    //http://live.nhl.com/GameData/SeasonSchedule-20172018.json
    //https://api.mysportsfeeds.com/v1.1/sample/pull/nhl/2016-2017-regular/daily_player_stats.json?fordate=20161015&
    //https://www.mysportsfeeds.com/data-feeds/nhl/feedlist/
    
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.buttonGames);

        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                Intent myIntent = new Intent(MainActivity.this, StatsActivity.class);
                startActivity(myIntent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}

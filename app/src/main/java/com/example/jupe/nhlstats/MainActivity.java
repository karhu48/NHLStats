package com.example.jupe.nhlstats;

import android.content.Context;
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
    Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addListenerOnButton();
    }
        public void addListenerOnButton() {
            final Context context = this;

            button = (Button) findViewById(R.id.buttonGames);
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
           /* @Override
            public boolean onCreateOptionsMenu (Menu menu){
                getMenuInflater().inflate(R.menu.activity_main, menu);
                return true;
            }*/
}

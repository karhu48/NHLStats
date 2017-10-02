package com.example.jupe.nhlstats;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Jupe on 2.10.2017.
 */

public class StatsActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from new_activity.xml
        setContentView(R.layout.stats_layout);
    }
}

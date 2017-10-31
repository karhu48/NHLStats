package com.example.jupe.nhlstats;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Omistaja on 30.10.2017.
 */

public class JSONParser extends AsyncTask<Void, Void, Boolean> {

    Context c;
    String jsonData;
    ListView lv;
    ProgressDialog pd;
    ArrayList<String> users=new ArrayList<>();
    public JSONParser(Context c, String jsonData, ListView lv) {
        this.c = c;
        this.jsonData = jsonData;
        this.lv = lv;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd=new ProgressDialog(c);
        pd.setTitle("Parse JSON");
        pd.setMessage("Parsing...Please wait");
        pd.show();
    }
    @Override
    protected Boolean doInBackground(Void... voids) {
        return this.parse();
    }
    @Override
    protected void onPostExecute(Boolean isParsed) {
        super.onPostExecute(isParsed);
        pd.dismiss();
        if(isParsed)
        {
            //BIND
            ArrayAdapter<String> adapter=new ArrayAdapter<String>(c,android.R.layout.simple_list_item_1,users);
            lv.setAdapter(adapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Toast.makeText(c, users.get(i), Toast.LENGTH_SHORT).show();
                }
            });
        }else
        {
            Toast.makeText(c, "Unable To Parse, check log for more info", Toast.LENGTH_SHORT).show();
        }
    }
    private Boolean parse()
    {
        try
        {
            JSONArray ja=new JSONArray(jsonData);
            JSONObject jo;
            users.clear();
            for (int i=0;i<ja.length();i++)
            {
                jo=ja.getJSONObject(i);
                String time=jo.getString("est");
                String hTeam=jo.getString("h");
                String aTeam=jo.getString("a");
                if(jo.getString("h").equalsIgnoreCase("ANA") || jo.getString("a").equalsIgnoreCase("ANA")) {
                    users.add(time + " : " + aTeam + "--" + hTeam);
                }
            }
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }
}

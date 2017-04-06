package com.example.pranee.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

public class ServiceActiviry extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_activiry);

        ListView listView = (ListView) findViewById(R.id.livName);
        try {
            GetUser getUser = new GetUser(ServiceActiviry.this);
            getUser.execute();

            String strJSON = getUser.get();
            Log.d("TestV3", "JSON ==>" + strJSON);

            JSONArray jsonArray = new JSONArray(strJSON);

            String[] nameStrings = new String[jsonArray.length()];
            for (int i=0;i<jsonArray.length();i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                nameStrings[i] = jsonObject.getString("Name");

            }   // for

            //Create ListView
            ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(ServiceActiviry.this,
                    android.R.layout.simple_list_item_1, nameStrings);
            listView.setAdapter(stringArrayAdapter);


        } catch (Exception e) {
            Log.d("TestV3", "e Main ==>" + e.toString());
        }

    }   //Main
}   //Main Class

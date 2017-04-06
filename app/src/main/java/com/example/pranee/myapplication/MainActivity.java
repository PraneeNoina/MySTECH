package com.example.pranee.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Explicit ประกาศตัวแปร
    private EditText userEditText, passwordEditText;
    private TextView textView;
    private Button button;
    private String userString, passwordString,truePasswordString;
    private boolean aBoolean = true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initial View  การผูกตัวแปรกับ View ที่อยู่ที่ Activity
        initialView();

        // Create Controller
        Controller();


    }   // Main Method นี่คือ Method

    private void Controller() {
        textView.setOnClickListener(MainActivity.this);
        button.setOnClickListener(MainActivity.this);
    }

    private void initialView() {
        userEditText = (EditText) findViewById(R.id.edtUser);
        passwordEditText = (EditText) findViewById(R.id.edtPassword);
        textView = (TextView) findViewById(R.id.txtRegister);
        button = (Button) findViewById(R.id.btnLogin);
    }

    @Override
    public void onClick(View v) {
        String tag = "SriwattanaV1";
        //For TextView
        if (v == textView) {
            Log.d(tag, "You Click TextView");

            // Create Intent
            Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
            startActivity(intent);
        }
        //For Button
        if (v == button) {
            Log.d(tag, "You Click Button");

            //Get Value From Edit Text
            userString = userEditText.getText().toString().trim();
            passwordString = passwordEditText.getText().toString().trim();

            //Check Space
            if (userString.equals("") || passwordString.equals("")) {
                //Have Space
                MyAlert myAlert = new MyAlert(MainActivity.this);
                myAlert.myDialog("Have Space", "Please Fill All");
            } else {
                //No Space
                checkUserAnPass();
            }

        }

    }   // onClick

    private void checkUserAnPass() {
        try {

            GetUser getUser = new GetUser(MainActivity.this);
            getUser.execute();

            String strJson = getUser.get();
            Log.d("TestV2", "e check ==>" +strJson);

            // Check User
            JSONArray jsonArray = new JSONArray(strJson);
            for (int i = 0; i < jsonArray.length(); i += 1) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if (userString.equals(jsonObject.getString("User"))) {
                    aBoolean = false;
                    truePasswordString = jsonObject.getString("Password");
                }

            }   // for
            if (aBoolean) {
                MyAlert myAlert = new MyAlert(MainActivity.this);
                myAlert.myDialog("User Fales","No This User on Database");
            } else if (!(passwordString.equals(truePasswordString))) {
                MyAlert myAlert = new MyAlert(MainActivity.this);
                myAlert.myDialog("Password Fales","Please Try agin");

            } else {
                Intent intent = new Intent(MainActivity.this,ServiceActiviry.class);
                startActivity(intent);
                finish();
            }

        } catch (Exception e) {
            Log.d("TestV2", "e Check ==>" + e.toString());

        }
    }

}   //Main Class นี่คือ Class หลัก

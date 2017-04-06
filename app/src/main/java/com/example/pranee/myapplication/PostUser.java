package com.example.pranee.myapplication;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

/**
 * Created by pranee on 4/6/2017.
 */

public class PostUser extends AsyncTask<String,Void,String>{

    private Context context;
    private static final String urlPHP = "http://swiftcodingthai.com/tech/addUserMaster.php";

    public PostUser(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {

        try {


            OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody requestBody = new FormEncodingBuilder()
                    .add("isAdd", "true")
                    .add("Name", params[0])
                    .add("Surname", params[1])
                    .add("Address", params[2])
                    .add("User", params[3])
                    .add("Password", params[4])
                    .build();
            Request.Builder builder = new Request.Builder();
            Request request = builder.url(urlPHP).post(requestBody).build();
            Response response = okHttpClient.newCall(request).execute();
            return response.body().string();

        } catch (Exception e) {
            Log.d("TestV1", "e doIn ==>" + e.toString());
            return null;
        }


    }
}   //Main Class

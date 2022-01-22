package com.aerium.listacyncproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayAdapter<ClipData.Item> items;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.ListView);
        new TaskAsyncTask().execute();
    }

    class TaskAsyncTask extends AsyncTask{
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.show();
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            String json = null;
            try {
                InputStream inputStream = getAssets().open("data.json");
                int size = inputStream.available();
                byte[] buffer = new byte[size];
                inputStream.read(buffer);
                inputStream.close();

                json = new String(buffer, "UTF-8");
                JSONObject jsonObject = new JSONObject(json);
                JSONArray jsonArray = jsonObject.getJSONArray("formulas") ;

                customAdapter = new CustomAdapter(MainActivity.this,jsonArray);
            } catch (IOException e){
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object object){
            super.onPostExecute(object);
            listView.setAdapter(customAdapter);
            if (progressDialog.isShowing())
                progressDialog.dismiss();
        }
    }
}
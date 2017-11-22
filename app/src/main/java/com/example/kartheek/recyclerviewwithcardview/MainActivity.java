package com.example.kartheek.recyclerviewwithcardview;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView  recyclerView;
    ArrayList moviename,datee,ratings,descrptions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView)findViewById(R.id.recycle);
        moviename=new ArrayList();
        datee=new ArrayList();
        ratings=new ArrayList();
        descrptions=new ArrayList();


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        CardViewAdapter adapter = new CardViewAdapter(this,moviename,datee,ratings,descrptions);
        recyclerView.setAdapter(adapter);
        new MyTask().execute();
    }
    public class MyTask extends AsyncTask<String,Void,String>
    {
        @Override
        protected void onPreExecute() {
            Toast.makeText(MainActivity.this, "Wait sometime", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected String doInBackground(String... params) {

            try{

                URL url=new URL("https://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=fd75d8c708d418f9ee6280f179e7f399");
                HttpURLConnection urlConnection=(HttpURLConnection)url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                String urldata=bufferedReader.readLine();

                JSONObject jsonObject=new JSONObject(urldata);
                JSONArray jsonArray=jsonObject.getJSONArray("results");
                for(int i=0;i<jsonArray.length();i++)
                {
                    JSONObject jsonObject1=jsonArray.getJSONObject(i);
                    String movie=jsonObject1.getString("title");
                    moviename.add(movie);
                    String dates=jsonObject1.getString("release_date");
                    datee.add(dates);
                    double rates=jsonObject1.getDouble("vote_average");
                    ratings.add(rates);
                    String content=jsonObject1.getString("overview");
                    descrptions.add(content);
                }

            }catch (Exception e)
            {
                e.printStackTrace();
            }
            return "data successfully loaded";
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(MainActivity.this, "Congracts", Toast.LENGTH_SHORT).show();
        }
    }
}

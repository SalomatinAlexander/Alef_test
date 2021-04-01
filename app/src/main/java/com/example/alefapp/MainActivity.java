package com.example.alefapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.app.UiModeManager;
import android.content.Context;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> list = new ArrayList<>();
    RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       recyclerView = findViewById(R.id.recycler_photo);
        new JsonTask().execute();
    }

        private class JsonTask extends AsyncTask<String, String, String> {

            protected void onPreExecute() {
                super.onPreExecute();

            }

            protected String doInBackground(String... params) {

                HttpURLConnection connection = null;
                BufferedReader reader = null;

                try {
                    URL url = new URL("http://dev-tasks.alef.im/task-m-001/list.php");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.connect();

                    InputStream stream = connection.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(stream));
                    StringBuffer buffer = new StringBuffer();
                    String line = "";

                    while ((line = reader.readLine()) != null) {
                        buffer.append(line + "\n");
                        Log.d("Response: ", "> " + line);
                    }

                    return buffer.toString();

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                    try {
                        if (reader != null) {
                            reader.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                 String in = result;
                 StringTokenizer st = new StringTokenizer(in, " \" ");
                 while(st.hasMoreTokens()) {
                        String val = st.nextToken();
                        if(val.length() == 1){

                        }else {
                            list.add(val);
                            System.out.println(val);
                        }

                }
                 list.remove(list.size()-1);
                Toast.makeText(MainActivity.this, "work", Toast.LENGTH_LONG).show();



                if (getResources().getConfiguration().smallestScreenWidthDp>= 600){
                    recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 3));

                } else {
                    recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
                }


                PhotoRecycler adapter = new PhotoRecycler(list);
                recyclerView.setAdapter(adapter);
            }
    }   }
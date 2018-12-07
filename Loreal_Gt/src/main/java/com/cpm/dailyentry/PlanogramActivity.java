package com.cpm.dailyentry;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.cpm.Constants.CommonString;
import com.cpm.capitalfoods.R;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by ashishc on 31-08-2016.
 */
public class PlanogramActivity extends AppCompatActivity {
    FloatingActionButton fab;
    ImageView imageview;
    Bitmap b;
    String str;

    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        setContentView(R.layout.planogramlayout);

        //str = getIntent().getStringExtra("Plalogram");
        str = getIntent().getExtras().getString("Plalogram");

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                finish();

                overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
            }
        });
       /* Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        imageview = (ImageView) findViewById(R.id.imageView3);

        information info = new information();
        info.execute("");

    }


    public class information extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... arg0) {

            try {
                URL url = new URL(str);
                InputStream is = new BufferedInputStream(url.openStream());
                b = BitmapFactory.decodeStream(is);

            } catch (Exception e) {
            }
            return null;
        }
        @Override
        protected void onPostExecute(String result) {
            imageview.setImageBitmap(b);
        }
    }


}

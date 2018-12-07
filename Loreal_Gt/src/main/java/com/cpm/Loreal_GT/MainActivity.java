package com.cpm.Loreal_GT;


import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.cpm.Constants.CommonString1;

import com.cpm.dailyentry.DailyEntryScreen;
import com.cpm.database.GSKDatabase;
import com.cpm.delegates.CoverageBean;


import com.cpm.download.CompleteDownloadActivity;
import com.cpm.upload.UploadDataActivity;

import com.cpm.xmlGetterSetter.JourneyPlanGetterSetter;
import com.cpm.capitalfoods.R;
import com.cpm.message.AlertMessage;

public class MainActivity extends Activity implements OnClickListener {

    Button  dailyEntry, exit, download, upload, performance;
    GSKDatabase database;
    ArrayList<JourneyPlanGetterSetter> jcplist;
    private SharedPreferences preferences;
    private String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainpage);

        upload = (Button) findViewById(R.id.upload);
        dailyEntry = (Button) findViewById(R.id.dash_dailyentry);
        download = (Button) findViewById(R.id.download);
        exit = (Button) findViewById(R.id.exit);
        performance = (Button) findViewById(R.id.performance);

        database = new GSKDatabase(this);
        database.open();

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        date = preferences.getString(CommonString1.KEY_DATE, null);

    }

    @Override

    protected void onStart() {
        super.onStart();
        upload.setOnClickListener(this);
        dailyEntry.setOnClickListener(this);
        download.setOnClickListener(this);
        exit.setOnClickListener(this);
        performance.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.download) {

            if (checkNetIsAvailable()) {

                Intent startDownload = new Intent(MainActivity.this, CompleteDownloadActivity.class);
                startActivity(startDownload);
                MainActivity.this.finish();
            } else {
                Toast.makeText(getApplicationContext(), "No Network Available", Toast.LENGTH_SHORT).show();
            }

        } else if (v.getId() == R.id.dash_dailyentry) {
            Intent startDownload = new Intent(MainActivity.this, DailyEntryScreen.class);
            startActivity(startDownload);

            overridePendingTransition(R.anim.activity_in, R.anim.activity_out);

            //finish();
        } else if (v.getId() == R.id.exit) {
            Intent startDownload = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(startDownload);
            MainActivity.this.finish();

        } else if (v.getId() == R.id.upload) {
            if (checkNetIsAvailable()) {

                jcplist = database.getJCPData(date);

                if (jcplist.size() == 0) {
                    Toast.makeText(getBaseContext(), "Please Download Data First",
                            Toast.LENGTH_LONG).show();
                } else {

                    if (preferences.getString(CommonString1.KEY_STOREVISITED_STATUS, "").equals("Yes")) {
                        Toast.makeText(getApplicationContext(), "First checkout of store", Toast.LENGTH_SHORT).show();
                    } else {
                        ArrayList<CoverageBean> cdata = database.getCoverageData(date);
                        if (cdata.size() == 0) {
                            Toast.makeText(getBaseContext(), AlertMessage.MESSAGE_NO_DATA, Toast.LENGTH_LONG).show();
                        } else {
                            Intent i = new Intent(getBaseContext(), UploadDataActivity.class);
                            i.putExtra("UploadAll", false);
                            startActivity(i);

                            finish();

                        }
                    }
                }

            } else {
                Toast.makeText(getApplicationContext(), "No Network Available", Toast.LENGTH_SHORT).show();
            }
        } else if (v.getId() == R.id.performance) {
			/*Intent startPerformance = 	new Intent(MainActivity.this,Performance.class);
			startActivity(startPerformance);*/
            overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
        }
		/*else if(v.getId()==R.id.teamattendence){
//			Intent startDownload = 	new Intent(MainActivity.this,NonWorkingActivity.class);
//			startActivity(startDownload);
		}*/
    }

    public boolean checkNetIsAvailable() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }
}

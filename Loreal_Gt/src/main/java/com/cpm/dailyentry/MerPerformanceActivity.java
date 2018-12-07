package com.cpm.dailyentry;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.cpm.Constants.CommonString1;
import com.cpm.capitalfoods.R;


public class MerPerformanceActivity extends AppCompatActivity {

    TextView merName, totalpresent_txt, absent_txt, leave_txt, weekoff_txt, holiday_txt, totalplannedcalls_txt, totalcallsmade_txt, totalexecution_txt;
    private SharedPreferences preferences;
    String username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mer_performance);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Merchandiser Performance");
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        merName = (TextView) findViewById(R.id.merName);
        totalpresent_txt = (TextView) findViewById(R.id.totalpresent_txt);
        absent_txt = (TextView) findViewById(R.id.absent_txt);
        leave_txt = (TextView) findViewById(R.id.leave_txt);
        weekoff_txt = (TextView) findViewById(R.id.weekoff_txt);
        holiday_txt = (TextView) findViewById(R.id.holiday_txt);
        totalplannedcalls_txt = (TextView) findViewById(R.id.totalplannedcalls_txt);
        totalcallsmade_txt = (TextView) findViewById(R.id.totalcallsmade_txt);
        totalexecution_txt = (TextView) findViewById(R.id.totalexecution_txt);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        username = preferences.getString(CommonString1.KEY_USERNAME, null);
        merName.setText(username);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {

            // NavUtils.navigateUpFromSameTask(this);
            finish();

            overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);

        }

        return super.onOptionsItemSelected(item);
    }
}

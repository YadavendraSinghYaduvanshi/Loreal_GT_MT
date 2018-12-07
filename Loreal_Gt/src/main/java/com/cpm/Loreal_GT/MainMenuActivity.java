package com.cpm.Loreal_GT;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cpm.Constants.CommonString1;
import com.cpm.capitalfoods.R;
import com.cpm.dailyentry.DailyEntryScreen;
import com.cpm.dailyentry.MerPerformanceActivity;
import com.cpm.dailyentry.PreviousStoreDataActivity;
import com.cpm.dailyentry.WindowListActivity;
import com.cpm.database.GSKDatabase;
import com.cpm.delegates.CoverageBean;
import com.cpm.download.CompleteDownloadActivity;
import com.cpm.fragment.HelpFragment;
import com.cpm.fragment.MainFragment;
import com.cpm.message.AlertMessage;
import com.cpm.upload.BulkImageUploadActivity;
import com.cpm.upload.CheckoutNUpload;
import com.cpm.upload.UploadDataActivityWithoutWait;
import com.cpm.upload.UploadImageWithRetrofit;
import com.cpm.xmlGetterSetter.JourneyPlanGetterSetter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainMenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    GSKDatabase database;
    ArrayList<JourneyPlanGetterSetter> jcplist;
    private SharedPreferences preferences = null;
    private String date, user_name, user_type, noticeBoard, quizUrl;
    TextView tv_username, tv_usertype;
    FrameLayout frameLayout;
    NavigationView navigationView;
    ArrayList<CoverageBean> cdata = new ArrayList<>();
    JourneyPlanGetterSetter storestatus = new JourneyPlanGetterSetter();
    Context context;
    FloatingActionButton fab;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        context = this;
        frameLayout = (FrameLayout) findViewById(R.id.frame_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        user_name = preferences.getString(CommonString1.KEY_USERNAME, null);
        user_type = preferences.getString(CommonString1.KEY_USER_TYPE, null);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle((Activity) context, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = LayoutInflater.from(this).inflate(R.layout.nav_header_main_menu2, navigationView, false);
        navigationView.addHeaderView(headerView);

        tv_username = (TextView) headerView.findViewById(R.id.nav_user_name);
        tv_usertype = (TextView) headerView.findViewById(R.id.nav_user_type);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        tv_username.setText(user_name);
        tv_usertype.setText(user_type);

        navigationView.setNavigationItemSelectedListener(this);
        database = new GSKDatabase(context);
        database.open();

        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        date = preferences.getString(CommonString1.KEY_DATE, null);
        noticeBoard = preferences.getString(CommonString1.KEY_NOTICE_BOARD, "");
        quizUrl = preferences.getString(CommonString1.KEY_QUIZ_URL, "");

        bundle = new Bundle();
        bundle.putString(CommonString1.KEY_NOTICE_BOARD, noticeBoard);
        bundle.putString(CommonString1.KEY_QUIZ_URL, quizUrl);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.empty_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_daily) {
            // Handle the camera action

            if (database.getPreviousStoreData(date).size() > 0) {
                AlertMessage.showToastMessage(context, "Please upload pending stores first");
            } else {
                Intent startDownload = new Intent(context, DailyEntryScreen.class);
                startActivity(startDownload);
                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
            }


//            if (database.getPreviousStoreData(null).size() > 0) {
//                AlertMessage.showToastMessage(context, "Please upload pending stores first");
//            } else {
//                Intent startDownload = new Intent(context, DailyEntryScreen.class);
//                startActivity(startDownload);
//                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
//            }

        } else if (id == R.id.nav_download) {
            if (checkNetIsAvailable()) {
                database.open();
                if (database.isCoverageDataFilled(date)) {
                    ArrayList<CoverageBean> coveragePrevious = database.getCoveragePreviousData(date);
                    if (coveragePrevious.size() > 0) {
                        for (int i = 0; i < coveragePrevious.size(); i++) {
                            if (coveragePrevious.get(i).getStatus().equalsIgnoreCase(CommonString1.KEY_CHECK_IN)
                                    || coveragePrevious.get(i).getStatus().equalsIgnoreCase(CommonString1.KEY_VALID)) {
                                database.deleteSpecificStoreData(coveragePrevious.get(i).getStoreId());
                            }
                        }
                    }
                    database.open();
                    if (database.isCoverageDataFilled(date)) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setTitle("Parinaam");
                        builder.setMessage("Please Upload Previous Data First")
                                .setCancelable(false)
                                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                })
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Intent startUpload = new Intent(context, CheckoutNUpload.class);
                                        startActivity(startUpload);
                                        finish();
                                    }
                                });

                        AlertDialog alert = builder.create();
                        alert.show();
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setTitle("Parinaam");
                        builder.setMessage("Do you want to download data?")
                                .setCancelable(false)
                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Intent startDownload = new Intent(context, CompleteDownloadActivity.class);
                                        startActivity(startDownload);
                                        finish();
                                    }
                                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Parinaam");
                    builder.setMessage("Do you want to download data?")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent startDownload = new Intent(context, CompleteDownloadActivity.class);
                                    startActivity(startDownload);
                                    finish();
                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            } else {
                Snackbar.make(frameLayout, "No Network Available", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
            }

        } else if (id == R.id.nav_upload) {

            if (checkNetIsAvailable()) {

                jcplist = database.getJCPData(date);

                if (jcplist.size() == 0) {
                    Snackbar.make(frameLayout, "Please Download Data First", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                } else {
                    if (preferences.getString(CommonString1.KEY_STOREVISITED_STATUS, "").equals("Yes")) {
                        Snackbar.make(frameLayout, "First checkout of store", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                    } else {
                        ArrayList<CoverageBean> cdata = new ArrayList<CoverageBean>();
                        cdata = database.getCoverageData(date);
                        if (cdata.size() == 0) {
                            Snackbar.make(frameLayout, AlertMessage.MESSAGE_NO_DATA, Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                        } else {
                            if ((validate_data())) {
                                //Intent i = new Intent(getBaseContext(), UploadDataActivity.class); OLD UPLOAD CLASS
                                Intent i = new Intent(getBaseContext(), UploadDataActivityWithoutWait.class);
                                startActivity(i);
                            } else {
                                Toast.makeText(getBaseContext(), AlertMessage.MESSAGE_NO_DATA, Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }
            } else {
                Snackbar.make(frameLayout, "No Network Available", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
            }

        } else if (id == R.id.nav_exit) {
            Intent startDownload = new Intent(context, LoginActivity.class);
            startActivity(startDownload);
            finish();
        } else if (id == R.id.nav_help) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            HelpFragment cartfrag = new HelpFragment();
            fragmentManager.beginTransaction().replace(R.id.frame_layout, cartfrag).commit();

        } else if (id == R.id.nav_export_database) {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
            builder1.setMessage("Are you sure you want to take the backup of your data?")
                    .setCancelable(false)
                    .setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                @SuppressWarnings("resource")
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    try {

                                        File sd = Environment.getExternalStorageDirectory();
                                        File data = Environment.getDataDirectory();
                                        if (sd.canWrite()) {
                                            long date = System.currentTimeMillis();

                                            SimpleDateFormat sdf = new SimpleDateFormat("MMM/dd/yy");
                                            String dateString = sdf.format(date);
                                            String currentDBPath = "//data//com.cpm.LorealGt//databases//" + GSKDatabase.DATABASE_NAME;
                                            String backupDBPath = "LorealGT_backup_" + user_name.replace(".", "") + "_"
                                                    + dateString.replace('/', '-') + "_" + getCurrentTime().replace(":", "") + ".db";

                                            String path = Environment.getExternalStorageDirectory().getPath();
                                            File currentDB = new File(data, currentDBPath);
                                            File backupDB = new File(path, backupDBPath);

                                            if (currentDB.exists()) {
                                                @SuppressWarnings("resource")
                                                FileChannel src = new FileInputStream(currentDB).getChannel();
                                                FileChannel dst = new FileOutputStream(backupDB).getChannel();
                                                dst.transferFrom(src, 0, src.size());
                                                src.close();
                                                dst.close();
                                            }

                                            if (new File(path + "/" + backupDBPath).exists()) {
                                                UploadImageWithRetrofit uploadRetro = new UploadImageWithRetrofit(context);
                                                uploadRetro.uploadedFiles = 0;
                                                uploadRetro.UploadBackup(backupDBPath, "DB_Backup", path + "/");
                                            }

                                        }
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                    }
                                }
                            })
                    .setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
            AlertDialog alert1 = builder1.create();
            alert1.show();

        } else if (id == R.id.nav_winPlanogram) {

            Intent startDownload = new Intent(this, WindowListActivity.class);
            startActivity(startDownload);
            overridePendingTransition(R.anim.activity_in, R.anim.activity_out);

        } else if (id == R.id.nav_performance) {

            Intent startDownload = new Intent(this, MerPerformanceActivity.class);
            startActivity(startDownload);
            overridePendingTransition(R.anim.activity_in, R.anim.activity_out);

        } else if (id == R.id.nav_bulk_upload) {

            if (checkNetIsAvailable()) {

                File f = new File(CommonString1.FILE_PATH_FOR_BULK_UPLOAD);
                File file[] = f.listFiles();

                if (file.length > 0) {

                    Intent startDownload = new Intent(this, BulkImageUploadActivity.class);
                    startActivity(startDownload);
                    overridePendingTransition(R.anim.activity_in, R.anim.activity_out);

                } else {
                    Snackbar.make(frameLayout, "No image found for upload", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                }
            } else {
                Snackbar.make(frameLayout, "No Network Available", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
            }

        } else if (id == R.id.prev_data) {

            if (database.getPreviousStoreData(null).size() > 0) {
                Intent in = new Intent(context, PreviousStoreDataActivity.class);
                startActivity(in);
            } else {
                AlertMessage.showToastMessage(context, "No Previous Data");
            }
        } else if (id == R.id.emp_salary) {

            if (database.getEmpSalaryData().size() > 0) {
                Intent in = new Intent(context, EmpSalaryActivity.class);
                startActivity(in);
            } else {
                AlertMessage.showToastMessage(context, "No Employee Salary Data");
            }
        } else if (id == R.id.future_data) {
            Intent startDownload = new Intent(context, FutureJCPActivity.class);
            startActivity(startDownload);
            overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public boolean validate_data() {
        boolean result = false;
        database.open();
        cdata = database.getCoverageData(date);
        for (int i = 0; i < cdata.size(); i++) {
            storestatus = database.getStoreStatus(cdata.get(i).getStoreId());
            if (!storestatus.getUploadStatus().get(0).equalsIgnoreCase(CommonString1.KEY_U)) {
                if ((storestatus.getCheckOutStatus().get(0).equalsIgnoreCase(CommonString1.KEY_C)
                        || storestatus.getUploadStatus().get(0).equalsIgnoreCase(CommonString1.KEY_P)
                        || storestatus.getUploadStatus().get(0).equalsIgnoreCase(CommonString1.KEY_D)
                        || storestatus.getUploadStatus().get(0).equalsIgnoreCase(CommonString1.STORE_STATUS_LEAVE))) {
                    result = true;
                    break;
                }
            }
        }

        return result;
    }

    public boolean checkNetIsAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (quizUrl == null || quizUrl.equalsIgnoreCase("")) {
            fab.setVisibility(View.INVISIBLE);
        } else {
            fab.setVisibility(View.VISIBLE);
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        MainFragment cartfrag = new MainFragment();
        cartfrag.setArguments(bundle);
        fragmentManager.beginTransaction().replace(R.id.frame_layout, cartfrag).commit();
    }

    public String getCurrentTime() {
        Calendar m_cal = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        String cdate = formatter.format(m_cal.getTime());
        return cdate;

    }


}

package com.cpm.dailyentry;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cpm.Constants.CommonString;
import com.cpm.Constants.CommonString1;
import com.cpm.GetterSetter.ServerTimeGetterSetter;
import com.cpm.capitalfoods.R;
import com.cpm.database.GSKDatabase;
import com.cpm.delegates.CoverageBean;
import com.cpm.geotag.LocationActivity;
import com.cpm.message.AlertMessage;
import com.cpm.utilities.CommonFunctions;
import com.cpm.xmlGetterSetter.JourneyPlanGetterSetter;
import com.cpm.xmlGetterSetter.WindowListGetterSetter;
import com.cpm.xmlGetterSetter.WindowSKUEntryGetterSetter;
import com.cpm.xmlHandler.XMLHandlers;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DailyEntryScreen extends AppCompatActivity implements OnItemClickListener, LocationListener {

    GSKDatabase database;
    ArrayList<JourneyPlanGetterSetter> jcplist;
    ArrayList<CoverageBean> coverage;
    private SharedPreferences preferences;
    private String date, username;
    ListView lv;
    String store_cd;
    private SharedPreferences.Editor editor = null;
    private Dialog dialog;
    public static String currLatitude = "0.0";
    public static String currLongitude = "0.0";
    CardView cardView;
    LinearLayout parent_linear, nodata_linear;
    private ArrayList<WindowListGetterSetter> windowdata = new ArrayList<>();
    List<WindowSKUEntryGetterSetter> WINDOWSIZE = new ArrayList<>();
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.storelistlayout);
        context = this;
        lv = (ListView) findViewById(R.id.list);
        nodata_linear = (LinearLayout) findViewById(R.id.no_data_lay);
        parent_linear = (LinearLayout) findViewById(R.id.parent_linear);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        database = new GSKDatabase(context);
        database.open();
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        date = preferences.getString(CommonString1.KEY_DATE, null);
        username = preferences.getString(CommonString1.KEY_USERNAME, null);
        editor = preferences.edit();
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Store List" + "-" + date);
    }


    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        jcplist = database.getJCPData(date);
        if (jcplist.size() > 0) {
            setCheckOutData();
            lv.setAdapter(new MyAdapter());
            lv.setOnItemClickListener(this);
        } else {
            lv.setVisibility(View.GONE);
            parent_linear.setBackgroundColor((getResources().getColor(R.color.grey_light)));
            nodata_linear.setVisibility(View.VISIBLE);
        }
        coverage = database.getCoverageData(date);
        lv.setAdapter(new MyAdapter());
        lv.setOnItemClickListener(this);
    }


    public void setCheckOutData() {
        boolean flag;
        boolean flag1 = true;
        boolean flag2 = true;
        for (int i = 0; i < jcplist.size(); i++) {
            String storeCd = jcplist.get(i).getStore_cd().get(0);
            String GEO_TAG = jcplist.get(i).getGEO_TAG().get(0);
            String state_cd = jcplist.get(i).getSTATE_CD().get(0);
            String store_type_cd = jcplist.get(i).getSTORETYPE_CD().get(0);

            windowdata = database.getWindowListData(state_cd, store_type_cd);
            WINDOWSIZE = database.getwindowdat(storeCd);

            if (database.getCoverageSpecificData(storeCd).size() > 0) {
                if (!jcplist.get(i).getCheckOutStatus().get(0).equals(CommonString1.KEY_C)
                        && !jcplist.get(i).getCheckOutStatus().get(0).equals(CommonString1.KEY_VALID)) {

                    if (jcplist.get(i).getCHANNEL_CD().get(0).equalsIgnoreCase("1")) {
                        if (database.getCOMPETITORData(storeCd).size() > 0 && database.getSFTData(storeCd).size() > 0 && database.getwindowdat(storeCd).size() > 0 && database.getStockPresentUpload(storeCd).size() > 0) {

                            if (GEO_TAG.equalsIgnoreCase(CommonString1.KEY_GEO_N)) {
                                flag = database.getGeotaggingData(storeCd).size() > 0;
                            } else {
                                flag = true;
                            }
                            flag1 = isEachWindowFilled();
                        } else {
                            flag = false;
                        }

                        if (database.getPOSMDATA(storeCd, store_type_cd).size() > 0) {
                            flag2 = database.getPOSM2data(storeCd).size() > 0;
                        }

                        if (flag && flag1 && flag2) {
                            database.updateStoreStatusOnCheckout(storeCd, date, CommonString1.KEY_VALID);
                            database.updateCoverage(CommonString1.KEY_VALID, storeCd);
                            jcplist = database.getJCPData(date);
                        }

                    } else {

                        boolean isfilled_flag = false;
                        if (database.getShareShelfHeaderData(storeCd).size() > 0 && database.getStockAvailabilityForChannel2Data(storeCd).size() > 0 && database.getFreeVisibilityData(storeCd).size() > 0) {

                            isfilled_flag = true;
                            if (GEO_TAG.equalsIgnoreCase(CommonString1.KEY_GEO_N)) {
                                isfilled_flag = database.getGeotaggingData(storeCd).size() > 0;
                            }
                            if (isfilled_flag) {
                                if (database.getMappingPaidVisibilityData(storeCd).size() > 0) {
                                    isfilled_flag = database.getPaidVisibilityData(storeCd).size() > 0;
                                }
                            }
                            if (isfilled_flag) {
                                if (database.getMapppingPromotionData(storeCd).size() > 0) {
                                    isfilled_flag = database.isPromotionDataFilled(storeCd);
                                }
                            }
                        }
                        if (isfilled_flag) {
                            database.updateStoreStatusOnCheckout(storeCd, date, CommonString1.KEY_VALID);
                            database.updateCoverage(CommonString1.KEY_VALID, storeCd);
                            jcplist = database.getJCPData(date);
                        }
                    }
                }
            }
        }
    }

    public boolean isEachWindowFilled() {
        boolean isFilled = true;
        for (int i = 0; i < windowdata.size(); i++) {
            if (!database.getwindowDataForEachWindow(store_cd, windowdata.get(i).getWindow_cd().get(0))) {
                isFilled = false;
                break;
            }
        }
        return isFilled;
    }


    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return jcplist.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(final int position, View convertView,
                            ViewGroup parent) {
            // TODO Auto-generated method stub
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.storelistrow, null);
                holder.storename = (TextView) convertView.findViewById(R.id.tvstorename);
                holder.city = (TextView) convertView.findViewById(R.id.tvcity);
                holder.keyaccount = (TextView) convertView.findViewById(R.id.tvkeyaccount);
                holder.img = (ImageView) convertView.findViewById(R.id.img);
                holder.checkout = (Button) convertView.findViewById(R.id.chkout);
                holder.checkinclose = (ImageView) convertView.findViewById(R.id.closechkin);
                holder.imgtag = (ImageView) convertView.findViewById(R.id.imgtag);
                holder.storenamelistview_layout = (RelativeLayout) convertView.findViewById(R.id.storenamelistview_layout);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }


            holder.imgtag.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    if (!jcplist.get(position).getLATTITUDE().get(0).equalsIgnoreCase("")
                            && !jcplist.get(position).getLONGITUDE().get(0).equalsIgnoreCase("")) {
                        Intent intent = new Intent(context, LocationActivity.class);
                        intent.putExtra(CommonString1.KEY_FROM_STORELIST, true);
                        intent.putExtra(CommonString1.KEY_STORE_CD, jcplist.get(position).getStore_cd().get(0));
                        startActivity(intent);
                    } else {
                        Snackbar.make(lv, "This store does not have Location", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                    }


                }
            });

            holder.checkout.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    if (CheckNetAvailability()) {
                        int totalHours = 0;
                        String intime = "00:00:00";
                        boolean islastStore = false;
                        if (isLastStore(jcplist.get(position).getStore_cd().get(0))) {
                            GetTimeFromServer run = new GetTimeFromServer();
                            Thread mainThread = Thread.currentThread();
                            Thread thread = new Thread(run);
                            thread.start();
                            try {
                                mainThread.sleep(800);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            totalHours = run.getValue();
                            intime = run.getIntime();
                            islastStore = true;
                        }
                        // getCoverageSpecificData(store_id);
                        //final String storeintime = database.getCoverageSpecificData(jcplist.get(position).getStore_cd().get(0)).get(0).getInTime();
                        final String storeintime = preferences.getString(CommonString1.KEY_CHECK_IN_TIME, "00:00:00");
                        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
                        try {
                            Date Date1 = format.parse(storeintime);
                            Date Date2 = format.parse(getCurrentTime());
                            long mills = Date2.getTime() - Date1.getTime();
                            long minutes = TimeUnit.MILLISECONDS.toMinutes(mills);

                            //PLEASE REMOVE
                            if (minutes < 15) {
                                AlertMessage.showToastMessage(context, "You have not completed 15 minutes of store minimum time. " + (15 - minutes) + " min Left.");
                            } else if (islastStore) {
                                if (totalHours == -1) {
                                    new AlertMessage((Activity) context, null, null, null).ShowAlert2(CommonString1.MESSAGE_SOCKETEXCEPTION);
                                } else if (totalHours == -2) {
                                    new AlertMessage((Activity) context, null, null, null).ShowAlert2(CommonString1.MESSAGE_EXCEPTION);
                                } else if (totalHours < 8) {
                                    final Runnable runnable = new Runnable() {
                                        @Override
                                        public void run() {
                                            AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                            builder.setMessage("Are you sure you want to Checkout")
                                                    .setCancelable(false)
                                                    .setPositiveButton("OK",
                                                            new DialogInterface.OnClickListener() {
                                                                public void onClick(
                                                                        DialogInterface dialog, int id) {
                                                                    if (CheckNetAvailability()) {
                                                                        editor = preferences.edit();
                                                                        editor.putString(CommonString1.KEY_STORE_CD, jcplist.get(position).getStore_cd().get(0));
                                                                        editor.putString(CommonString1.KEY_STORE_NAME, jcplist.get(position).getStore_name().get(0));
                                                                        String metadata = CommonFunctions.setMetadataAtImagesAtPref(jcplist.get(position), username);
                                                                        editor.putString(CommonString1.KEY_META_DATA, metadata);
                                                                        editor.commit();
                                                                        Intent i = new Intent(DailyEntryScreen.this, CheckOutStoreActivity.class);
                                                                        i.putExtra(CommonString1.KEY_STORE_CD, jcplist.get(position).getStore_cd().get(0));
                                                                        startActivity(i);
                                                                    } else {
                                                                        Snackbar.make(lv, "No Network", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                                                                    }
                                                                }
                                                            })
                                                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                                        public void onClick(
                                                                DialogInterface dialog, int id) {
                                                            dialog.cancel();
                                                        }
                                                    });
                                            AlertDialog alert = builder.create();
                                            alert.show();
                                        }
                                    };

                                    final Runnable runnable2 = new Runnable() {
                                        @Override
                                        public void run() {
                                            if (CheckNetAvailability()) {
                                                editor = preferences.edit();
                                                editor.putString(CommonString1.KEY_STORE_CD, jcplist.get(position).getStore_cd().get(0));
                                                editor.putString(CommonString1.KEY_STORE_NAME, jcplist.get(position).getStore_name().get(0));
                                                String metadata = CommonFunctions.setMetadataAtImagesAtPref(jcplist.get(position), username);
                                                editor.putString(CommonString1.KEY_META_DATA, metadata);
                                                editor.commit();
                                                Intent i = new Intent(context, CheckOutStoreActivity.class);
                                                i.putExtra(CommonString1.KEY_STORE_CD, jcplist.get(position).getStore_cd().get(0));
                                                startActivity(i);
                                            } else {
                                                Snackbar.make(lv, "No Network", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                                            }
                                        }
                                    };

                                    AlertMessage.eightHourCheckoutAlert((Activity) context, "You have not completed 8 hours of Work in the stores.\nYou will be marked half day today.\n\n First store Intime - " + intime, runnable2);
                                } else {
                                    if (CheckNetAvailability()) {

                                        editor = preferences.edit();
                                        editor.putString(CommonString1.KEY_STORE_CD, jcplist.get(position).getStore_cd().get(0));
                                        editor.putString(CommonString1.KEY_STORE_NAME, jcplist.get(position).getStore_name().get(0));
                                        String metadata = CommonFunctions.setMetadataAtImagesAtPref(jcplist.get(position), username);
                                        editor.putString(CommonString1.KEY_META_DATA, metadata);
                                        editor.commit();
                                        Intent i = new Intent(context, CheckOutStoreActivity.class);
                                        i.putExtra(CommonString1.KEY_STORE_CD, jcplist.get(position).getStore_cd().get(0));
                                        startActivity(i);
                                    } else {
                                        Snackbar.make(lv, "No Network", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                                    }
                                }
                            } else {

                                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                builder.setMessage("Are you sure you want to Checkout")
                                        .setCancelable(false)
                                        .setPositiveButton("OK",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(
                                                            DialogInterface dialog, int id) {
                                                        if (CheckNetAvailability()) {

                                                            editor = preferences.edit();
                                                            editor.putString(CommonString1.KEY_STORE_CD, jcplist.get(position).getStore_cd().get(0));
                                                            editor.putString(CommonString1.KEY_STORE_NAME, jcplist.get(position).getStore_name().get(0));
                                                            String metadata = CommonFunctions.setMetadataAtImagesAtPref(jcplist.get(position), username);
                                                            editor.putString(CommonString1.KEY_META_DATA, metadata);

                                                            editor.commit();

                                                            Intent i = new Intent(context, CheckOutStoreActivity.class);
                                                            i.putExtra(CommonString1.KEY_STORE_CD, jcplist.get(position).getStore_cd().get(0));
                                                            startActivity(i);
                                                        } else {
                                                            Snackbar.make(lv, "No Network", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                                                        }

                                                    }
                                                })
                                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                dialog.cancel();
                                            }
                                        });
                                AlertDialog alert = builder.create();
                                alert.show();
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                            Log.e("error", e.toString());
                        }
                    } else {
                        Snackbar.make(lv, "No Network", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                    }

                }
            });


            if (jcplist.get(position).getGEO_TAG().get(0).equalsIgnoreCase(CommonString1.KEY_GEO_Y)) {
                holder.imgtag.setVisibility(View.VISIBLE);
            } else {
                holder.imgtag.setVisibility(View.GONE);
            }

            if (jcplist.get(position).getSTORETYPE_CD().get(0).equalsIgnoreCase("1")) {
                holder.storenamelistview_layout.setBackgroundResource(R.color.color1);
            } else if (jcplist.get(position).getSTORETYPE_CD().get(0).equalsIgnoreCase("2")) {
                holder.storenamelistview_layout.setBackgroundResource(R.color.color2);
            } else if (jcplist.get(position).getSTORETYPE_CD().get(0).equalsIgnoreCase("3")) {
                holder.storenamelistview_layout.setBackgroundResource(R.color.color3);
            } else if (jcplist.get(position).getSTORETYPE_CD().get(0).equalsIgnoreCase("4")) {
                holder.storenamelistview_layout.setBackgroundResource(R.color.color4);
            } else if (jcplist.get(position).getSTORETYPE_CD().get(0).equalsIgnoreCase("5")) {
                holder.storenamelistview_layout.setBackgroundResource(R.color.color5);
            } else if (jcplist.get(position).getSTORETYPE_CD().get(0).equalsIgnoreCase("6")) {
                holder.storenamelistview_layout.setBackgroundResource(R.color.color6);
            } else {
                holder.storenamelistview_layout.setBackgroundResource(R.color.color7);
            }


            String storecd = jcplist.get(position).getStore_cd().get(0);
            ArrayList<CoverageBean> specificCoverageData = database.getCoverageSpecificData(storecd);

            if (jcplist.get(position).getUploadStatus().get(0).equals(CommonString1.KEY_D)) {

                holder.img.setVisibility(View.VISIBLE);
                holder.img.setBackgroundResource(R.drawable.tick_d);
                holder.checkout.setVisibility(View.INVISIBLE);
                holder.checkinclose.setVisibility(View.INVISIBLE);


            } else if (jcplist.get(position).getUploadStatus().get(0).equals(CommonString1.KEY_U)) {

                holder.img.setVisibility(View.VISIBLE);
                holder.img.setBackgroundResource(R.drawable.tick_u);
                holder.checkout.setVisibility(View.INVISIBLE);
                holder.checkinclose.setVisibility(View.INVISIBLE);


            } else if (specificCoverageData.size() > 0 && specificCoverageData.get(0).getStatus().equalsIgnoreCase(CommonString1.STORE_STATUS_LEAVE)) {
                holder.img.setBackgroundResource(R.drawable.leave_tick);
                holder.img.setVisibility(View.VISIBLE);
                holder.checkout.setVisibility(View.INVISIBLE);
                holder.checkinclose.setVisibility(View.INVISIBLE);

            } else if ((jcplist.get(position).getCheckOutStatus().get(0).equals(CommonString1.KEY_C))) {

                holder.img.setVisibility(View.INVISIBLE);
                holder.checkinclose.setBackgroundResource(R.drawable.tick_c);
                holder.checkinclose.setVisibility(View.VISIBLE);
                holder.checkout.setVisibility(View.INVISIBLE);

            } else if (isvalid(jcplist.get(position).getStore_cd().get(0), CommonString1.KEY_VALID)) {

                holder.checkout.setBackgroundResource(R.drawable.checkout);
                holder.checkout.setVisibility(View.VISIBLE);
                holder.checkout.setEnabled(true);
                holder.checkinclose.setVisibility(View.INVISIBLE);
                holder.img.setVisibility(View.INVISIBLE);
            } else if (isvalid(jcplist.get(position).getStore_cd().get(0), CommonString1.KEY_CHECK_IN)) {

                holder.img.setVisibility(View.INVISIBLE);
                holder.checkout.setEnabled(false);
                holder.checkout.setVisibility(View.INVISIBLE);
                holder.checkinclose.setBackgroundResource(R.drawable.checkin_ico);
                holder.checkinclose.setVisibility(View.VISIBLE);

            } else {
                holder.checkout.setEnabled(false);
                holder.checkout.setVisibility(View.INVISIBLE);
                holder.img.setVisibility(View.INVISIBLE);
                holder.img.setBackgroundResource(R.drawable.store);

                holder.checkinclose.setEnabled(false);
                holder.checkinclose.setVisibility(View.INVISIBLE);

            }

            holder.storename.setText(jcplist.get(position).getStore_name().get(0));
            holder.city.setText(jcplist.get(position).getCity().get(0));
            holder.keyaccount.setText(jcplist.get(position).getKey_account().get(0));

            return convertView;
        }

        private class ViewHolder {
            TextView storename, city, keyaccount;
            ImageView img, checkinclose, imgtag;
            RelativeLayout storenamelistview_layout;

            Button checkout;
        }

    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
    }

    boolean isvalid(String store_cd, String status) {
        boolean isvalid = false;
        if (coverage.size() > 0) {
            int i;
            for (i = 0; i < coverage.size(); i++) {
                if (store_cd.equals(coverage.get(i).getStoreId())) {
                    if (coverage.get(i).getStatus().equalsIgnoreCase(status)) {
                        isvalid = true;
                        break;
                    }
                }
            }
        }
        return isvalid;
    }


    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
        // TODO Auto-generated method stub

        store_cd = jcplist.get(position).getStore_cd().get(0);
        ArrayList<CoverageBean> specificCoverageData = database.getCoverageSpecificData(store_cd);


        final String upload_status = jcplist.get(position).getUploadStatus().get(0);
        final String checkoutstatus = jcplist.get(position).getCheckOutStatus().get(0);
        final String STORETYPE_CD = jcplist.get(position).getSTORETYPE_CD().get(0);
        final String STATE_CD = jcplist.get(position).getSTATE_CD().get(0);
        final String GeoTag = jcplist.get(position).getGEO_TAG().get(0);

        editor = preferences.edit();

        editor.putString(CommonString1.KEY_GEO_TAG, GeoTag);
        editor.putString(CommonString1.KEY_STORE_TYPE_CD, STORETYPE_CD);
        editor.putString(CommonString1.KEY_STATE_CD, STATE_CD);
        editor.commit();

        if (upload_status.equals(CommonString1.KEY_U)) {
            Snackbar.make(lv, "All Data and Images Uploaded", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
        } else if (upload_status.equals(CommonString1.KEY_D)) {
            Snackbar.make(lv, "All Data Uploaded", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
        } else if (((checkoutstatus.equals(CommonString1.KEY_C)))) {
            Snackbar.make(lv, "Store already checked out", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
        } else if (specificCoverageData.size() > 0 && specificCoverageData.get(0).getStatus().equalsIgnoreCase(CommonString1.STORE_STATUS_LEAVE)) {
            Snackbar.make(lv, "Store Already Closed", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
        } else {
            if (isStoreCheckedIN(store_cd)) {
                showMyDialog(jcplist.get(position), "Yes");
            } else {
                Snackbar.make(lv, "Please checkout from current store", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
            }
        }

    }

    public boolean isLastStore(String storeCd) {
        boolean isLastStore = true;
        if (storeCd != null && jcplist.size() > 0) {
            for (int i = 0; i < jcplist.size(); i++) {
                if ((!jcplist.get(i).getStore_cd().get(0).equalsIgnoreCase(storeCd))) {
                    if ((!jcplist.get(i).getCheckOutStatus().get(0).equalsIgnoreCase(CommonString1.KEY_C))
                            && (!jcplist.get(i).getUploadStatus().get(0).equalsIgnoreCase(CommonString1.STORE_STATUS_LEAVE))) {
                        isLastStore = false;
                        break;
                    }
                }

            }
        }
        return isLastStore;
    }

   /* public boolean isTotalHoursDone(String storeCd) {

    }*/

    public String getCurrentTime() {
        Calendar m_cal = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        String cdate = formatter.format(m_cal.getTime());
        return cdate;
    }

    public boolean CheckNetAvailability() {

        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED
                || connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            // we are connected to a network
            connected = true;
        }
        return connected;
    }

    void showMyDialog(final JourneyPlanGetterSetter jcpGetSet, final String status) {
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialogbox);
        // dialog.setTitle("About Android Dialog Box");
        RadioGroup radioGroup = (RadioGroup) dialog.findViewById(R.id.radiogrpvisit);

        radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                if (checkedId == R.id.yes) {
                    editor = preferences.edit();
                    editor.putString(CommonString1.KEY_STOREVISITED, jcpGetSet.getStore_cd().get(0));
                    editor.putString(CommonString1.KEY_STOREVISITED_STATUS, "Yes");
                    editor.putString(CommonString1.KEY_LATITUDE, currLatitude);
                    editor.putString(CommonString1.KEY_LONGITUDE, currLongitude);
                    editor.putString(CommonString1.KEY_STORE_NAME, jcpGetSet.getStore_name().get(0));
                    editor.putString(CommonString1.KEY_STORE_CD, jcpGetSet.getStore_cd().get(0));
                    editor.putString(CommonString1.KEY_CHANNEL_CD, jcpGetSet.getCHANNEL_CD().get(0));
                    String metadata = CommonFunctions.setMetadataAtImagesAtPref(jcpGetSet, username);
                    editor.putString(CommonString1.KEY_META_DATA, metadata);
                    if (jcpGetSet.getVISIT_DATE().get(0) != null && !jcpGetSet.getVISIT_DATE().get(0).equals("")) {
                        editor.putString(CommonString1.KEY_VISIT_DATE, jcpGetSet.getVISIT_DATE().get(0));
                    }
                    if (status.equals("Yes")) {
                        editor.putString(CommonString1.KEY_STOREVISITED_STATUS, "Yes");
                    }
                    editor.commit();
                    database.updateStoreStatusOnCheckout(jcpGetSet.getStore_cd().get(0), date, CommonString1.KEY_INVALID);
                    dialog.cancel();

                    boolean flag = true;

                    if (coverage.size() > 0) {
                        for (int i = 0; i < coverage.size(); i++) {
                            if (store_cd.equals(coverage.get(i).getStoreId())) {
                                flag = false;
                                break;
                            }
                        }
                    }
                    if (flag) {
                        //Intent in = new Intent(DailyEntryScreen.this, StoreimageActivity.class);
                        Intent in = new Intent(context, StoreProfileActivity.class);
                        in.putExtra(CommonString.TAG_OBJECT, jcpGetSet);
                        startActivity(in);
                        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                    } else {
                        if (jcpGetSet.getCHANNEL_CD().get(0).equalsIgnoreCase("1")) {

                            Intent in = new Intent(context, StoreProfileActivity.class);
                            //Intent in = new Intent(DailyEntryScreen.this, StoreEntry.class);
                            in.putExtra(CommonString.TAG_OBJECT, jcpGetSet);
                            in.putExtra(CommonString1.KEY_STORE_CD, jcpGetSet.getStore_cd().get(0));
                            startActivity(in);

                        } else {

                            Intent in = new Intent(context, StoreProfileActivity.class);
                            //Intent in = new Intent(DailyEntryScreen.this, StoreEntryForChannel2Activity.class);
                            in.putExtra(CommonString.TAG_OBJECT, jcpGetSet);
                            in.putExtra(CommonString1.KEY_STORE_CD, jcpGetSet.getStore_cd().get(0));
                            startActivity(in);

                        }


                    }
                } else if (checkedId == R.id.no) {
                    dialog.cancel();

                    if (jcpGetSet.getCheckOutStatus().get(0).equals(CommonString1.KEY_INVALID) || jcpGetSet.getCheckOutStatus().get(0).equals(CommonString1.KEY_VALID)) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(DailyEntryScreen.this);
                        builder.setMessage(CommonString1.DATA_DELETE_ALERT_MESSAGE)
                                .setCancelable(false)
                                .setPositiveButton("Yes",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog,
                                                                int id) {

                                                new Task().execute(jcpGetSet.getStore_cd().get(0));
                                                SharedPreferences.Editor editor = preferences.edit();
                                                editor.putString(CommonString1.KEY_STORE_CD, jcpGetSet.getStore_cd().get(0));
                                                editor.putString(CommonString1.KEY_STOREVISITED, "");
                                                editor.putString(CommonString1.KEY_STOREVISITED_STATUS, "");
                                                editor.putString(CommonString1.KEY_LATITUDE, "");
                                                editor.putString(CommonString1.KEY_LONGITUDE, "");
                                                String metadata = CommonFunctions.setMetadataAtImagesAtPref(jcpGetSet, username);
                                                editor.putString(CommonString1.KEY_META_DATA, metadata);
                                                editor.commit();

                                                Intent in = new Intent(context, NonWorkingReason.class);
                                                in.putExtra(CommonString.TAG_OBJECT, jcpGetSet);
                                                startActivity(in);

                                            }
                                        })
                                .setNegativeButton("No",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog,
                                                                int id) {
                                                dialog.cancel();
                                            }
                                        });
                        AlertDialog alert = builder.create();

                        alert.show();
                    } else {
                        new Task().execute(jcpGetSet.getStore_cd().get(0));

                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString(CommonString1.KEY_STORE_CD, jcpGetSet.getStore_cd().get(0));
                        editor.putString(CommonString1.KEY_STOREVISITED, "");
                        editor.putString(CommonString1.KEY_STOREVISITED_STATUS, "");
                        editor.putString(CommonString1.KEY_LATITUDE, "");
                        editor.putString(CommonString1.KEY_LONGITUDE, "");
                        String metadata = CommonFunctions.setMetadataAtImagesAtPref(jcpGetSet, username);
                        editor.putString(CommonString1.KEY_META_DATA, metadata);
                        editor.commit();

                        Intent in = new Intent(context, NonWorkingReason.class);
                        in.putExtra(CommonString.TAG_OBJECT, jcpGetSet);
                        startActivity(in);
                    }
                    //finish();
                }
            }

        });

        dialog.show();
    }

    class Task extends AsyncTask<String, String, String> {
        String storeCd;
        boolean isSuccess;
        String msg;

        @Override
        protected String doInBackground(String... params) {
            isSuccess = false;
            msg = "";
            try {
                SoapObject request = new SoapObject(CommonString1.NAMESPACE, CommonString1.METHOD_UPLOAD_COVERAGE_REMOVE);
                request.addProperty("STORE_CD", params[0]);
                request.addProperty("USER_ID", username);
                request.addProperty("VISIT_DATE", date);
                storeCd = params[0];
                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);
                HttpTransportSE androidHttpTransport = new HttpTransportSE(CommonString1.URL);
                androidHttpTransport.call(CommonString1.SOAP_ACTION + CommonString1.METHOD_UPLOAD_COVERAGE_REMOVE, envelope);

                Object result = (Object) envelope.getResponse();
                if (result.toString().contains(CommonString1.KEY_SUCCESS)) {
                    isSuccess = true;
                } else {
                    isSuccess = false;
                    msg = CommonString1.KEY_FAILURE;
                }
            } catch (MalformedURLException e) {
                isSuccess = false;
                msg = CommonString1.MESSAGE_EXCEPTION;
            } catch (IOException e) {
                isSuccess = false;
                msg = CommonString1.MESSAGE_SOCKETEXCEPTION;
            } catch (Exception e) {
                isSuccess = false;
                msg = CommonString1.MESSAGE_EXCEPTION;

            }
            if (isSuccess) {
                return CommonString1.KEY_SUCCESS;
            } else {
                return msg;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (result.contains(CommonString1.KEY_SUCCESS)) {
                database.open();
                if (database.isCoverageExist(storeCd, date)) {
                    UpdateData(storeCd);
                    Toast.makeText(getApplicationContext(), "Coverage deleted Successfully", Toast.LENGTH_SHORT).show();
                }
            } else if (!result.equals("")) {
                //ShowAlert2(msg);
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void ShowAlert2(String str) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Error");
        builder.setMessage(str).setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }


    @Override
    public void onLocationChanged(Location location) {
        // TODO Auto-generated method stub
        currLatitude = Double.toString(location.getLatitude());
        currLongitude = Double.toString(location.getLongitude());
    }

    @Override
    public void onProviderDisabled(String arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onProviderEnabled(String arg0) {
        // TODO Auto-generated method stub

    }


    @Override
    public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
        // TODO Auto-generated method stub

    }

    public void UpdateData(String storeCd) {
        database.open();
        database.deleteSpecificStoreData(storeCd);
        database.updateStoreStatusOnCheckout(storeCd, jcplist.get(0).getVISIT_DATE().get(0), "N");
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
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
            overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean isStoreCheckedIN(String store_cd) {
        boolean result = true;
        for (int i = 0; i < coverage.size(); i++) {
            if (coverage.get(i).getStatus().equalsIgnoreCase(CommonString1.KEY_CHECK_IN)
                    || coverage.get(i).getStatus().equalsIgnoreCase(CommonString1.KEY_VALID)) {
                if (!coverage.get(i).getStoreId().equalsIgnoreCase(store_cd)) {
                    result = false;
                }

                break;
            }
        }
        return result;
    }

    public class GetTimeFromServer implements Runnable {
        private volatile int totalTime;
        private volatile String intime;

        @Override
        public void run() {
            try {
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(true);
                XmlPullParser xpp = factory.newPullParser();
                SoapSerializationEnvelope envelope;
                HttpTransportSE androidHttpTransport;
                SoapObject request;
                // Brand Master data
                request = new SoapObject(CommonString1.NAMESPACE, CommonString1.METHOD_NAME_UNIVERSAL_DOWNLOAD);
                request.addProperty("UserName", username);
                request.addProperty("Type", "SERVER_TIME");
                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);
                androidHttpTransport = new HttpTransportSE(CommonString1.URL);
                androidHttpTransport.call(CommonString1.SOAP_ACTION_UNIVERSAL, envelope);

                Object resultPrevdata = (Object) envelope.getResponse();
                if (resultPrevdata.toString() != null) {

                    xpp.setInput(new StringReader(resultPrevdata.toString()));
                    xpp.next();
                    int eventType = xpp.getEventType();

                    ServerTimeGetterSetter serverTimeGetterSetter = XMLHandlers.ServerTimeXML(xpp, eventType);
                    String tot_hour = serverTimeGetterSetter.getTOT_HOUR();
                    String inttime = serverTimeGetterSetter.getFIRST_STORE_LOGIN();
                    //String tot_hour = "8";
                    totalTime = Integer.parseInt(tot_hour);
                    intime = (inttime);
                }
            } catch (IOException e) {
                e.printStackTrace();
                totalTime = -1;
            } catch (Exception e) {
                e.printStackTrace();
                totalTime = -2;
            }
        }

        public int getValue() {
            return totalTime;
        }

        public String getIntime() {
            return intime;
        }
    }


}

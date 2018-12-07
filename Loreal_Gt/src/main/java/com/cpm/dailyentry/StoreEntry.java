package com.cpm.dailyentry;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.cpm.Constants.AlertandMessages;
import com.cpm.Constants.CommonString1;
import com.cpm.GetterSetter.NavMenuItemGetterSetter;
import com.cpm.capitalfoods.R;
import com.cpm.database.GSKDatabase;
import com.cpm.geotag.LocationActivity;
import com.cpm.xmlGetterSetter.WindowListGetterSetter;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StoreEntry extends AppCompatActivity implements OnClickListener {

    GSKDatabase db;
    private SharedPreferences preferences;
    String store_cd, state_cd, store_type_cd;
    String user_type = "";
    String GEO_TAG = "";
    String username;
    ValueAdapter adapter;
    RecyclerView recyclerView;
    int uploadCount = 0;
    Context context;
    private ArrayList<WindowListGetterSetter> windowdata = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.menu_item_recycle_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db = new GSKDatabase(getApplicationContext());
        db.open();

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        username = preferences.getString(CommonString1.KEY_USERNAME, "");
        if (getIntent().getStringExtra(CommonString1.KEY_STORE_CD) != null) {
            store_cd = getIntent().getStringExtra(CommonString1.KEY_STORE_CD);
        } else {
            store_cd = preferences.getString(CommonString1.KEY_STORE_CD, null);
        }
        store_type_cd = preferences.getString(CommonString1.KEY_STORE_TYPE_CD, null);
        state_cd = preferences.getString(CommonString1.KEY_STATE_CD, null);
        GEO_TAG = preferences.getString(CommonString1.KEY_GEO_TAG, null);
        user_type = preferences.getString(CommonString1.KEY_USER_TYPE, null);
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        windowdata = db.getWindowListData(state_cd, store_type_cd);
        recyclerView = (RecyclerView) findViewById(R.id.drawer_layout_recycle);

        adapter = new ValueAdapter(getApplicationContext(), getdata());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));

    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
    }


    @Override
    public void onClick(View view) {
        // TODO Auto-generated method stub

        int id = view.getId();

        switch (id) {
            case R.id.performance:

                Intent startPerformance = new Intent(StoreEntry.this, Performance.class);
                startActivity(startPerformance);
                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
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
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
            overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
        }

        return super.onOptionsItemSelected(item);
    }

    public class ValueAdapter extends RecyclerView.Adapter<ValueAdapter.MyViewHolder> {

        private LayoutInflater inflator;

        List<NavMenuItemGetterSetter> data = Collections.emptyList();

        public ValueAdapter(Context context, List<NavMenuItemGetterSetter> data) {

            inflator = LayoutInflater.from(context);
            this.data = data;

        }

        @Override
        public ValueAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int i) {

            View view = inflator.inflate(R.layout.custom_row, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ValueAdapter.MyViewHolder viewHolder, final int position) {

            final NavMenuItemGetterSetter current = data.get(position);

            viewHolder.icon.setImageResource(current.getIconImg());
            viewHolder.icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (current.getIconImg() == R.drawable.stock1 || current.getIconImg() == R.drawable.stock_done1) {

                        if (!db.isClosingDataFilled(store_cd)) {

                            Intent in1 = new Intent(getApplicationContext(), StockAvailability2Activity.class);
                            startActivity(in1);
                            overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                        } else {
                            Snackbar.make(recyclerView, "Data cannot be changed", Snackbar.LENGTH_SHORT).show();
                        }
                    }
                    if (current.getIconImg() == R.drawable.midday_stock || current.getIconImg() == R.drawable.midday_stock_done) {

                        if (!db.isClosingDataFilled(store_cd)) {
                            Intent in3 = new Intent(getApplicationContext(), MidDayStock.class);
                            startActivity(in3);
                            overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                        } else {
                            Snackbar.make(recyclerView, "Data cannot be changed", Snackbar.LENGTH_SHORT).show();

                        }
                    }
                    if (current.getIconImg() == R.drawable.windows || current.getIconImg() == R.drawable.window_done) {
                        Intent in4 = new Intent(getApplicationContext(), SecondaryWindowActivity.class);
                        startActivity(in4);
                        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);

                    }
                    if (current.getIconImg() == R.drawable.posm1 || current.getIconImg() == R.drawable.posm_done1) {
                        if (db.getPOSMDATA(store_cd, store_type_cd).size() > 0) {
                            Intent in5 = new Intent(getApplicationContext(), POSM2Activity.class);
                            startActivity(in5);
                            overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                        } else {
                            Snackbar.make(recyclerView, "No Data", Snackbar.LENGTH_SHORT).show();
                        }
                    }

                    if (current.getIconImg() == R.drawable.geotag1 || current.getIconImg() == R.drawable.geotag_done1) {

                        if (GEO_TAG.equalsIgnoreCase(CommonString1.KEY_GEO_Y)) {
                            Toast.makeText(getApplicationContext(), "GoeTag Already Done", Toast.LENGTH_LONG).show();
                        } else if (db.getGeotaggingData(store_cd).size() > 0) {

                            if (db.getGeotaggingData(store_cd).get(0).getGEO_TAG().equalsIgnoreCase(CommonString1.KEY_GEO_Y)) {
                                Toast.makeText(getApplicationContext(), "GoeTag Already Done", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Intent in4 = new Intent(getApplicationContext(), LocationActivity.class);
                            in4.putExtra(CommonString1.KEY_FROM_STORELIST, false);
                            in4.putExtra(CommonString1.KEY_STORE_CD, store_cd);
                            startActivity(in4);
                            overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                        }


                    }


                    if (current.getIconImg() == R.drawable.closing_stock || current.getIconImg() == R.drawable.closing_stock_done) {
                        if (db.isOpeningDataFilled(store_cd)) {
                            if (db.isMiddayDataFilled(store_cd)) {
                                Intent in2 = new Intent(getApplicationContext(), ClosingStock.class);
                                startActivity(in2);
                                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                            } else {
                                Snackbar.make(recyclerView, "First fill Midday Stock Data", Snackbar.LENGTH_SHORT).show();
                            }
                        } else {
                            Snackbar.make(recyclerView, "First fill Opening Stock data", Snackbar.LENGTH_SHORT).show();
                        }
                    }

                    if (current.getIconImg() == R.drawable.storesignage1 || current.getIconImg() == R.drawable.storesignage_done1) {
                        Intent in5 = new Intent(getApplicationContext(), StoreSignAgeActivity.class);
                        startActivity(in5);
                        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                    }

                    if (current.getIconImg() == R.drawable.competition1 || current.getIconImg() == R.drawable.cpmpetition_done1) {
                        Intent in7 = new Intent(getApplicationContext(), COMPETITORActivity.class);
                        startActivity(in7);
                        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                    }

                }
            });

        }

        @Override
        public int getItemCount() {
            return data.size();
        }


        class MyViewHolder extends RecyclerView.ViewHolder {

            //TextView txt;
            ImageView icon;

            public MyViewHolder(View itemView) {
                super(itemView);
                //txt=(TextView) itemView.findViewById(R.id.list_txt);
                icon = (ImageView) itemView.findViewById(R.id.list_icon);
            }
        }

    }

    public List<NavMenuItemGetterSetter> getdata() {
        List<NavMenuItemGetterSetter> data = new ArrayList<>();

        int Stock, middayImg, closingImg, windows, assetImg, additionalImg, competitionImg, geotag;

        if (db.isClosingDataFilled(store_cd)) {
            closingImg = R.drawable.closing_stock_done;
        } else {
            closingImg = R.drawable.closing_stock;
        }

        if (db.isMiddayDataFilled(store_cd)) {
            middayImg = R.drawable.midday_stock_done;
        } else {
            middayImg = R.drawable.midday_stock;
        }

        if (db.isOpeningDataFilled(store_cd)) {
            Stock = R.drawable.stock_done1;
        } else {
            Stock = R.drawable.stock1;
        }

        if (db.isPOSMDataFilled(store_cd)) {
            assetImg = R.drawable.posm_done1;
        } else {
            assetImg = R.drawable.posm1;

        }

        if (isEachWindowFilled()) {
            windows = R.drawable.window_done;
            new UploadWindowTask(context).execute();
        } else {
            windows = R.drawable.windows;
        }


        if (db.getCOMPETITORData(store_cd).size() > 0) {
            competitionImg = R.drawable.cpmpetition_done1;
        } else {
            competitionImg = R.drawable.competition1;

        }

        if (db.getSFTData(store_cd).size() > 0) {
            additionalImg = R.drawable.storesignage_done1;
        } else {
            additionalImg = R.drawable.storesignage1;

        }

        if (db.getGeotaggingData(store_cd).size() > 0) {

            if (db.getGeotaggingData(store_cd).get(0).getGEO_TAG().equalsIgnoreCase(CommonString1.KEY_GEO_Y)) {

                geotag = R.drawable.geotag_done1;
            } else {

                geotag = R.drawable.geotag1;
            }
        } else {

            if (GEO_TAG.equalsIgnoreCase(CommonString1.KEY_GEO_Y)) {
                geotag = R.drawable.geotag_done1;
            } else {
                geotag = R.drawable.geotag1;
            }


        }

        if (user_type.equals("Promoter")) {
            //int img[]={Stock, middayImg, windows, assetImg, closingImg, additionalImg, competitionImg};
            int img[] = {middayImg, windows, Stock, closingImg, additionalImg, competitionImg};
            for (int i = 0; i < img.length; i++) {

                NavMenuItemGetterSetter recData = new NavMenuItemGetterSetter();
                recData.setIconImg(img[i]);
                //recData.setIconName(text[i]);

                data.add(recData);
            }
        } else if (user_type.equals("Merchandiser")) {
            //int img[]={Stock, windows, assetImg,, , };
            //int img[]={Stock, geotag,additionalImg,assetImg,competitionImg,windows};
            int img[] = {geotag, additionalImg, Stock, windows, competitionImg, assetImg};

            for (int i = 0; i < img.length; i++) {

                NavMenuItemGetterSetter recData = new NavMenuItemGetterSetter();
                recData.setIconImg(img[i]);
                //recData.setIconName(text[i]);

                data.add(recData);
            }
        }

        return data;
    }

    public boolean isEachWindowFilled() {
        boolean isFilled = true;
        for (int i = 0; i < windowdata.size(); i++) {
            if (!db.getwindowDataForEachWindow(store_cd, windowdata.get(i).getWindow_cd().get(0))) {
                isFilled = false;
                break;
            }
        }

        return isFilled;
    }

    private class UploadWindowTask extends AsyncTask<Void, Void, String> {
        private Context context;
        ProgressDialog dialog;
        boolean up_success_flag = true;

        UploadWindowTask(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            dialog = new ProgressDialog(context);
            dialog.setTitle("Uploading Window Images Data");
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected String doInBackground(Void... params) {
            // TODO Auto-generated method stub
            try {
                //------------------------------new----------------------
                //region window1 DATA
                File f = new File(CommonString1.FILE_PATH);
                File[] files = f.listFiles();
                String final_xml = "";
                if (files.length > 0) {
                    for (int i = 0; i < files.length; i++) {
                        String onXML = "";
                        onXML = "[IMAGE_WINDOW]"
                                + files[i].getName()
                                + "[/IMAGE_WINDOW]";
                        final_xml = final_xml + onXML;
                    }
                } else {
                    String onXML = "";
                    onXML = "[IMAGE_WINDOW]"
                            + "NO IMAGE IN FOLDER"
                            + "[/IMAGE_WINDOW]";
                    final_xml = final_xml + onXML;
                }
                final String sos_xml = "[DATA]" + final_xml + "[/DATA]";
                SoapObject request = new SoapObject(CommonString1.NAMESPACE, CommonString1.METHOD_UPLOAD_XML);
                request.addProperty("XMLDATA", sos_xml);
                request.addProperty("KEYS", "IMAGEDATA_WINDOW");
                request.addProperty("USERNAME", username);
                request.addProperty("MID", "0");
                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);
                HttpTransportSE androidHttpTransport = new HttpTransportSE(CommonString1.URL);
                androidHttpTransport.call(CommonString1.SOAP_ACTION + CommonString1.METHOD_UPLOAD_XML, envelope);

                Object result = (Object) envelope.getResponse();

                if (result.toString().equalsIgnoreCase(CommonString1.KEY_SUCCESS)) {
                    up_success_flag = true;
                } else {
                    return result.toString();
                }
                //endregion

            } catch (MalformedURLException e) {
                e.printStackTrace();
                up_success_flag = false;

            } catch (IOException e) {
                e.printStackTrace();
                up_success_flag = false;

            } catch (Exception e) {
                e.printStackTrace();
                up_success_flag = false;
            }
            if (up_success_flag == true) {
                return CommonString1.KEY_SUCCESS;
            } else {
                return CommonString1.KEY_FAILURE;
            }

        }

        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            dialog.dismiss();
            if (result.contains(CommonString1.KEY_SUCCESS)) {

            } else if (!result.contains(CommonString1.KEY_FAILURE)) {
                AlertandMessages.showToastMsg(context, "Error :" + result);
            } else {
                uploadCount++;
                if (uploadCount < 2) {
                    new UploadWindowTask(context).execute();
                }
            }

        }
    }
}

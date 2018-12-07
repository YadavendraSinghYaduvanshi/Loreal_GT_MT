package com.cpm.geotag;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cpm.Constants.CommonString;
import com.cpm.Constants.CommonString1;
import com.cpm.Constants.TMSCommonString;
import com.cpm.capitalfoods.R;
import com.cpm.dailyentry.StoreEntry;
import com.cpm.database.GSKDatabase;
import com.cpm.message.AlertMessage;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class UploadGeotaggingActivity extends Activity {


    private TextView licenceinfo;
    private Handler handler = new Handler();
    ProgressBar progressBar;
    int timerCount = 0;
    public static int uploadsuccess = 0;

    boolean isFailure = false;
    boolean up_success_flag = true;
    boolean isConnected = false;

    boolean isException = false;

    final Timer timer = new Timer();

    GSKDatabase mDataClassObj;

    static Editor e1;
    private String visit_date, username, STORE_ID;
    String empid;
    private SharedPreferences preferences = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);


        setContentView(R.layout.uploadscreen);

        mDataClassObj = new GSKDatabase(this);
        e1 = this.getSharedPreferences("emp", Context.MODE_WORLD_READABLE).edit();

        //	empid = this.getSharedPreferences("emp", Context.MODE_WORLD_WRITEABLE).getString("empid", "");
        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        empid = preferences.getString(TMSCommonString.KEY_EMP_ID, "");
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        visit_date = preferences.getString(CommonString1.KEY_DATE, null);
        username = preferences.getString(CommonString1.KEY_USERNAME, null);
        STORE_ID = preferences.getString(CommonString1.KEY_STORE_CD, null);
        licenceinfo = (TextView) findViewById(R.id.licencelabel1);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setEnabled(true);
        timer.schedule(new TimerTask() {
            public void run() {
                System.out.println("on create auth started");
                UploadData();

            }
        }, 1000);
        if (progressBar.isEnabled())

            handler.postDelayed(updateTimeTask, 1000);
    }

    private Runnable updateTimeTask = new Runnable() {
        public void run() {
            timerCount++;

            /**
             * Add code here for displaying message in different condition
             */
            // if (uploadsuccess == 1) {
            //
            // timerCount = 120;
            // }
            upDateInfo(timerCount);

            handler.postDelayed(this, 1000);

        }

    };

    private void upDateInfo(int timerCount) {
        switch (timerCount) {
            case 1:
                licenceinfo.setText("Checking .........");
                break;

            case 3:
                licenceinfo.setText("Uploading in progress.........");
                break;

            case 4:
                licenceinfo.setText("Uploading in progress.........");
                break;
            case 5:
                licenceinfo.setText("Uploading in progress.........");
                break;
            case 6:
                licenceinfo.setText("Uploading in progress.........");
                break;

        }

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Handle the back button
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Alert Message");
            builder.setMessage("Uploading Data")
                    .setCancelable(false)
                    .setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {

                                }
                            });
            AlertDialog alert = builder.create();
            alert.show();

            return true;
        }

        return super.onKeyDown(keyCode, event);
    }


    public void UploadData() {
        new UploadTask(this).execute();

    }

    public String getinTime() {

        Calendar m_cal;

        String _outime = "";

        m_cal = Calendar.getInstance();

        _outime = m_cal.get(Calendar.HOUR_OF_DAY) + ":"
                + m_cal.get(Calendar.MINUTE) + ":"
                + m_cal.get(Calendar.SECOND);

        return _outime;

    }

    private class UploadTask extends AsyncTask<Void, Data, String> {
        private Context context;

        UploadTask(Context context) {
            this.context = context;
        }

        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();


        }

        protected String doInBackground(Void... params) {
            // TODO Auto-generated method stub


            try {
                ArrayList<GeotaggingBeans> geodata = new ArrayList<GeotaggingBeans>();

                mDataClassObj.open();

                geodata = mDataClassObj.getGeotaggingData(STORE_ID);

                if (geodata.size() > 0) {

                    for (int i = 0; i < geodata.size(); i++) {


                        GeotaggingBeans gdata = new GeotaggingBeans();
                        gdata = geodata.get(i);
                        String final_xml = "";
                        String onXMLs = "";

                        String onXML = "[DATA][USER_DATA][STORE_ID]"
                                + Integer.parseInt(gdata.getStoreId())
                                + "[/STORE_ID]"
                                + "[USERNAME]"
                                + username
                                + "[/USERNAME]"

								/*+ "[GEO_TAG_Status]"
                                + gdata.getGEO_TAG()
								+ "[/GEO_TAG_Status]"*/

                                + "[Image1]"
                                + gdata.getUrl1()
                                + "[/Image1][Latitude]"
                                + Double.toString(gdata.getLatitude())
                                + "[/Latitude][Longitude]"
                                + Double.toString(gdata.getLongitude())
                                + "[/Longitude][/USER_DATA][/DATA]";


                        final_xml = onXML;


						/*final String sos_xml = "[DATA]" + final_xml
                                + "[/DATA]";*/

                        SoapObject request = new SoapObject(
                                CommonString.NAMESPACE,
                                CommonString1.METHOD_UPLOAD_XML);
                        request.addProperty("XMLDATA", final_xml);
                        request.addProperty("KEYS", "GeoXML");
                        request.addProperty("USERNAME", username);

                        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                                SoapEnvelope.VER11);
                        envelope.dotNet = true;
                        envelope.setOutputSoapObject(request);

                        HttpTransportSE androidHttpTransport = new HttpTransportSE(
                                CommonString1.URL);

                        androidHttpTransport.call(
                                CommonString.NAMESPACE + CommonString1.METHOD_UPLOAD_XML,
                                envelope);

                        Object result = (Object) envelope.getResponse();


                        if (result.toString().equalsIgnoreCase("Success")) {


                            if (!(gdata.getUrl1().equals(""))) {

                                ImageUploadActivity.UploadGeoTaggingImage(gdata.getUrl1());


                            }
                        } else if (result.toString().equalsIgnoreCase(
                                CommonString1.KEY_NO_DATA)) {
                            mDataClassObj.close();
                            return CommonString1.KEY_NO_DATA;

                        } else {
                            mDataClassObj.close();
                            return CommonString1.DATA_ALERT;

                        }


                    }


                }


            } catch (IOException e) {
                up_success_flag = false;
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (XmlPullParserException e) {

                up_success_flag = false;
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            if (up_success_flag == true) {
                return CommonString1.KEY_SUCCESS;
            } else {
                return CommonString1.KEY_FAILURE;
            }
        }

        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);


            if (result.contains(CommonString1.KEY_SUCCESS)) {

                //database.open();
                //database.deleteAllTables();
                //mDataClassObj.deletegeotaggingdata();
                //mDataClassObj.close();

					/*AlertMessage message = new AlertMessage(
							UploadGeotaggingActivity.this,
							AlertMessage.MESSAGE_UPLOAD_DATA, "success", null);
					message.showMessage();*/

                //mDataClassObj.deleteGeoTag();
                Toast.makeText(getApplicationContext(), "GeoTag Success", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(UploadGeotaggingActivity.this, StoreEntry.class);
                intent.putExtra(CommonString1.KEY_STORE_CD, STORE_ID);
                startActivity(intent);

                finish();


            } else {

                AlertMessage message = new AlertMessage(
                        UploadGeotaggingActivity.this, CommonString1.ERROR, "success", null);
                message.showMessage();
            }


        }

    }


    class Data {
        int value;
        String name;
    }


}

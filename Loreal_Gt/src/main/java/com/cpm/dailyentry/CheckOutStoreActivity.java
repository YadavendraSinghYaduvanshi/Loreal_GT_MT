package com.cpm.dailyentry;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cpm.Constants.CommonString1;
import com.cpm.capitalfoods.R;
import com.cpm.database.GSKDatabase;
import com.cpm.message.AlertMessage;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@SuppressWarnings("deprecation")
public class CheckOutStoreActivity extends Activity implements LocationListener {

    private Dialog dialog;
    private ProgressBar pb;
    private TextView percentage, message, tv_title;
    private String username, visit_date, store_id, store_intime;
    private Data data;
    private GSKDatabase db;
    private SharedPreferences preferences = null;
    private double latitude = 0.0, longitude = 0.0;
    public static String currLatitude = "0.0";
    public static String currLongitude = "0.0";
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.storename);
        context = this;
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        username = preferences.getString(CommonString1.KEY_USERNAME, "");

        if (getIntent().getStringExtra(CommonString1.KEY_STORE_CD) != null) {
            store_id = getIntent().getStringExtra(CommonString1.KEY_STORE_CD);
        }

        currLatitude = preferences.getString(CommonString1.KEY_LATITUDE, "0.0");
        currLongitude = preferences.getString(CommonString1.KEY_LONGITUDE, "0.0");
        db = new GSKDatabase(context);
        db.open();

        visit_date = db.getVisiteDateFromCoverage(store_id);
        store_intime = db.getCoverageSpecificData(store_id).get(0).getInTime();
        new BackgroundTask(context).execute();
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

        db.open();

    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        db.close();
    }

    private class BackgroundTask extends AsyncTask<Void, Data, String> {
        private Context context;

        BackgroundTask(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();

            dialog = new Dialog(context);
            dialog.setContentView(R.layout.custom);
            dialog.setTitle("Sending Checkout Data");
            dialog.setCancelable(false);
            dialog.show();
            tv_title = (TextView) dialog.findViewById(R.id.tv_title);
            pb = (ProgressBar) dialog.findViewById(R.id.progressBar1);
            percentage = (TextView) dialog.findViewById(R.id.percentage);
            message = (TextView) dialog.findViewById(R.id.message);
            tv_title.setText("Please wait");

        }

        @SuppressWarnings("deprecation")
        @Override
        protected String doInBackground(Void... params) {
            // TODO Auto-generated method stub

            try {

                //String result = "";
                data = new Data();
                data.value = 20;
                data.name = "Checked out Data Uploading";
                publishProgress(data);


                String onXML = "[STORE_CHECK_OUT_STATUS][USER_ID]"
                        + username
                        + "[/USER_ID]" + "[STORE_ID]"
                        + store_id
                        + "[/STORE_ID][LATITUDE]"
                        + latitude
                        + "[/LATITUDE][LOGITUDE]"
                        + longitude
                        + "[/LOGITUDE][CHECKOUT_DATE]"
                        + visit_date
                        + "[/CHECKOUT_DATE][CHECK_OUTTIME]"
                        + "00:00:00"
                        + "[/CHECK_OUTTIME][CHECK_INTIME]"
                        + "00:00:00"
                        + "[/CHECK_INTIME][CREATED_BY]"
                        + username
                        + "[/CREATED_BY][/STORE_CHECK_OUT_STATUS]";

                final String sos_xml = "[DATA]" + onXML + "[/DATA]";
                SoapObject request = new SoapObject(CommonString1.NAMESPACE, "Upload_Store_ChecOut_Status");
                request.addProperty("onXML", sos_xml);
                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);
                HttpTransportSE androidHttpTransport = new HttpTransportSE(CommonString1.URL);
                androidHttpTransport.call(CommonString1.SOAP_ACTION + "Upload_Store_ChecOut_Status", envelope);

                Object result = envelope.getResponse();
                if (result.toString().equalsIgnoreCase(CommonString1.KEY_NO_DATA)) {
                    return "Upload_Store_ChecOut_Status";
                }
                if (result.toString().equalsIgnoreCase(CommonString1.KEY_FAILURE)) {
                    return "Upload_Store_ChecOut_Status";
                }

                // for failure
                data.value = 100;
                data.name = "Checkout Done";
                publishProgress(data);

                if (result.toString().equalsIgnoreCase(CommonString1.KEY_SUCCESS)) {

                    if (db.updateCoverageStoreOutTime(store_id, visit_date, CommonString1.KEY_C) > 0) {
                        if (db.updateStoreStatusOnCheckout(store_id, visit_date, CommonString1.KEY_C) > 0) {
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString(CommonString1.KEY_STOREVISITED, "");
                            editor.putString(CommonString1.KEY_STOREVISITED_STATUS, "");
                            editor.putString(CommonString1.KEY_LATITUDE, "");
                            editor.putString(CommonString1.KEY_LONGITUDE, "");
                            editor.putString(CommonString1.KEY_CHECK_IN_TIME, "");
                            editor.commit();
                        }
                    }

                } else {
                    return CommonString1.KEY_FAILURE;
                }
                return CommonString1.KEY_SUCCESS;

            } catch (MalformedURLException e) {
                final AlertMessage message = new AlertMessage((Activity) context, AlertMessage.MESSAGE_EXCEPTION, "download", e);
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        message.showMessage();
                    }
                });

            } catch (IOException e) {
                final AlertMessage message = new AlertMessage((Activity) context, AlertMessage.MESSAGE_SOCKETEXCEPTION, "socket", e);
                // counter++;
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        message.showMessage();
                        // TODO Auto-generated method stub
                    }
                });
            } catch (Exception e) {
                final AlertMessage message = new AlertMessage((Activity) context, AlertMessage.MESSAGE_EXCEPTION, "download", e);
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        message.showMessage();
                    }
                });
            }

            return "";
        }

        @Override
        protected void onProgressUpdate(Data... values) {
            // TODO Auto-generated method stub
            pb.setProgress(values[0].value);
            percentage.setText(values[0].value + "%");
            message.setText(values[0].name);
        }

        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            dialog.dismiss();
            if (result.equals(CommonString1.KEY_SUCCESS)) {
                AlertMessage message = new AlertMessage((Activity) context, "Successfully Checked out", "checkout", null);
                message.showMessage();
                finish();
            } else if (!result.equals("")) {
                Toast.makeText(getApplicationContext(), "Error in Uploading Checkout Data", Toast.LENGTH_SHORT).show();
                finish();
            }
        }

    }

    class Data {
        int value;
        String name;
    }


    public String getCurrentTime() {

        Calendar m_cal = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        return formatter.format(m_cal.getTime());
    }

    @Override
    public void onLocationChanged(Location location) {
        // TODO Auto-generated method stub
        latitude = location.getLatitude();
        longitude = location.getLongitude();
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
}

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
import android.support.annotation.Nullable;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cpm.Constants.CommonString1;
import com.cpm.capitalfoods.R;
import com.cpm.database.GSKDatabase;
import com.cpm.message.AlertMessage;
import com.cpm.xmlGetterSetter.CheckoutBean;
import com.cpm.xmlGetterSetter.FailureGetterSetter;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by deepakp on 4/6/2017.
 */

public class UpdateIntimeCoverageActivity extends Activity implements LocationListener {

    private Dialog dialog;
    private ProgressBar pb;
    private TextView percentage, message;
    private String username, visit_date, store_id, store_intime;
    ;
    private Data data;
    int eventType;
    private GSKDatabase db;

    private SharedPreferences preferences = null;
    private CheckoutBean checkout = new CheckoutBean();
    private FailureGetterSetter failureGetterSetter = null;
    static int counter = 1;
    private double latitude = 0.0, longitude = 0.0;
    public static String currLatitude = "0.0";
    public static String currLongitude = "0.0";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.storename);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        username = preferences.getString(CommonString1.KEY_USERNAME, "");
        visit_date = preferences.getString(CommonString1.KEY_DATE, null);
        store_id = preferences.getString(CommonString1.KEY_STORE_CD, "");
        store_intime = preferences.getString(CommonString1.KEY_STORE_IN_TIME, "");
        currLatitude = preferences.getString(CommonString1.KEY_LATITUDE, "0.0");
        currLongitude = preferences.getString(CommonString1.KEY_LONGITUDE, "0.0");
        db = new GSKDatabase(this);
        db.open();
        new BackgroundTask(this).execute();
    }

    @Override
    protected void onResume() {
        super.onResume();
        db.open();
    }

    @Override
    protected void onStop() {
        super.onStop();
        db.close();
    }

    @Override
    public void onLocationChanged(Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

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
            pb = (ProgressBar) dialog.findViewById(R.id.progressBar1);
            percentage = (TextView) dialog.findViewById(R.id.percentage);
            message = (TextView) dialog.findViewById(R.id.message);

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
                        + getCurrentTime()
                        + "[/CHECK_OUTTIME][CHECK_INTIME]"
                        + store_intime
                        + "[/CHECK_INTIME][CREATED_BY]"
                        + username
                        + "[/CREATED_BY][/STORE_CHECK_OUT_STATUS]";


                final String sos_xml = "[DATA]" + onXML
                        + "[/DATA]";

                SoapObject request = new SoapObject(
                        CommonString1.NAMESPACE,
                        "Upload_Store_ChecOut_Status");
                request.addProperty("onXML", sos_xml);
                /*request.addProperty("KEYS", "CHECKOUT_STATUS");
				request.addProperty("USERNAME", username);*/
                //request.addProperty("MID", mid);

                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                        SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);

                HttpTransportSE androidHttpTransport = new HttpTransportSE(
                        CommonString1.URL);

                androidHttpTransport.call(
                        CommonString1.SOAP_ACTION + "Upload_Store_ChecOut_Status",
                        envelope);
                Object result = (Object) envelope.getResponse();


                if (result.toString().equalsIgnoreCase(
                        CommonString1.KEY_NO_DATA)) {
                    return "Upload_Store_ChecOut_Status";
                }

                if (result.toString().equalsIgnoreCase(
                        CommonString1.KEY_FAILURE)) {
                    return "Upload_Store_ChecOut_Status";
                }

                // for failure

                data.value = 100;
                data.name = "Checkout Done";
                publishProgress(data);

                if (result.toString()
                        .equalsIgnoreCase(CommonString1.KEY_SUCCESS_chkout)) {

                    db.updateCoverageStoreOutTime(store_id, visit_date, CommonString1.KEY_C);

                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString(CommonString1.KEY_STOREVISITED, "");
                    editor.putString(CommonString1.KEY_STOREVISITED_STATUS, "");
                    editor.putString(CommonString1.KEY_LATITUDE, "");
                    editor.putString(CommonString1.KEY_LONGITUDE, "");
                    editor.commit();

                    db.updateStoreStatusOnCheckout(store_id, visit_date,
                            CommonString1.KEY_C);

                } else {
                    if (result.toString().equalsIgnoreCase(
                            CommonString1.KEY_FALSE)) {
                        return CommonString1.METHOD_Checkout_StatusNew;
                    }

                    // for failure

                }
                return CommonString1.KEY_SUCCESS;

            } catch (MalformedURLException e) {

                final AlertMessage message = new AlertMessage(
                        UpdateIntimeCoverageActivity.this,
                        AlertMessage.MESSAGE_EXCEPTION, "download", e);
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub

                        message.showMessage();
                    }
                });

            } catch (IOException e) {
                final AlertMessage message = new AlertMessage(
                        UpdateIntimeCoverageActivity.this,
                        AlertMessage.MESSAGE_SOCKETEXCEPTION,
                        "socket", e);
                // counter++;
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        message.showMessage();
                        // TODO Auto-generated method stub
						/*
						 * if (counter < 10) { new
						 * BackgroundTask(CheckOutUploadActivity
						 * .this).execute(); } else { message.showMessage();
						 * counter =1; }
						 */
                    }
                });
            } catch (Exception e) {
                final AlertMessage message = new AlertMessage(
                        UpdateIntimeCoverageActivity.this,
                        AlertMessage.MESSAGE_EXCEPTION, "download", e);
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

                AlertMessage message = new AlertMessage(
                        UpdateIntimeCoverageActivity.this,
                        "Successfully updated", "intime update", null);
                message.showMessage();

                finish();

            } else if (!result.equals("")) {
                Toast.makeText(getApplicationContext(), "Network Error Try Again", Toast.LENGTH_SHORT).show();
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
        String cdate = formatter.format(m_cal.getTime());

       /* String intime = m_cal.get(Calendar.HOUR_OF_DAY) + ":"
                + m_cal.get(Calendar.MINUTE) + ":" + m_cal.get(Calendar.SECOND);*/

        return cdate;

    }

}

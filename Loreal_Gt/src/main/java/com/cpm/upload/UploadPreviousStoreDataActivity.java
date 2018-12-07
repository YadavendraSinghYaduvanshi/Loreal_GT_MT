package com.cpm.upload;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cpm.Constants.CommonString;
import com.cpm.Constants.CommonString1;
import com.cpm.capitalfoods.R;
import com.cpm.dailyentry.StoreEntry;
import com.cpm.dailyentry.StoreEntryForChannel2Activity;
import com.cpm.database.GSKDatabase;
import com.cpm.delegates.CoverageBean;
import com.cpm.message.AlertMessage;
import com.cpm.xmlGetterSetter.FailureGetterSetter;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class UploadPreviousStoreDataActivity extends AppCompatActivity {

    private Dialog dialog;
    private ProgressBar pb;
    private TextView percentage, message;
    private Data data;
    private GSKDatabase db;
    String app_ver, datacheck, validity, intentData, visit_date, channelcd;
    String[] words;
    ArrayList<CoverageBean> coverageBeanlist;
    private SharedPreferences preferences = null;
    private FailureGetterSetter failureGetterSetter = null;
    private double latitude = 0.0, longitude = 0.0;
    CoverageBean cdata;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_previous_store_data);
        context = this;
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        app_ver = preferences.getString(CommonString1.KEY_VERSION, "");
        visit_date = preferences.getString(CommonString1.KEY_DATE, null);
        intentData = getIntent().getStringExtra(CommonString.TAG_INTENT_DATA);
        if (intentData.equalsIgnoreCase("createCoverage")) {
            channelcd = getIntent().getStringExtra(CommonString.TAG_CHANNEL_CD);
            cdata = (CoverageBean) getIntent().getSerializableExtra(CommonString.TAG_OBJECT);
        } else {
            channelcd = "0";
        }

        db = new GSKDatabase(this);
        new BackgroundTask(this).execute();
    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        //db.close();
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
            dialog.setContentView(R.layout.custom_upload);
            dialog.setTitle("Sending Previous Store Data");
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

                if (intentData.equalsIgnoreCase("FromPrevStore")) {
                    data.value = 20;
                    data.name = "Previous Data Uploading";
                    db.open();
                    coverageBeanlist = db.getCoverageData("FromPrevStore");
                } else if (intentData.equalsIgnoreCase("createCoverage")) {
                    data.value = 20;
                    data.name = "Intime Uploading";
                    coverageBeanlist = new ArrayList<>();
                    coverageBeanlist.add(cdata);
                    //coverageBeanlist = db.getCoverageData(visit_date);
                }
                publishProgress(data);

                if (coverageBeanlist.size() > 0) {
                    for (int i = 0; i < coverageBeanlist.size(); i++) {

                        String onXML = "[DATA][USER_DATA][STORE_CD]"
                                + coverageBeanlist.get(i).getStoreId()
                                + "[/STORE_CD]" + "[VISIT_DATE]"
                                + coverageBeanlist.get(i).getVisitDate()
                                + "[/VISIT_DATE][LATITUDE]"
                                + coverageBeanlist.get(i).getLatitude()
                                + "[/LATITUDE][APP_VERSION]"
                                + app_ver
                                + "[/APP_VERSION][LONGITUDE]"
                                + coverageBeanlist.get(i).getLongitude()
                                + "[/LONGITUDE][IN_TIME]"
                                + "00:00:00"
                                + "[/IN_TIME][OUT_TIME]"
                                + "00:00:00"
                                + "[/OUT_TIME][UPLOAD_STATUS]"
                                + "N"
                                + "[/UPLOAD_STATUS][USER_ID]" + coverageBeanlist.get(i).getUserId()
                                + "[/USER_ID][IMAGE_URL]" + coverageBeanlist.get(i).getImage()
                                + "[/IMAGE_URL][REASON_ID]"
                                + coverageBeanlist.get(i).getReasonid()
                                + "[/REASON_ID]"
                                + "[CHANNEL_CD]"
                                + coverageBeanlist.get(i).getChannel_cd()
                                + "[/CHANNEL_CD]"
                                + "[SUBREASON_ID]"
                                + coverageBeanlist.get(i).getSub_reasonId()
                                + "[/SUBREASON_ID]"
                                + "[INFORM_TO]"
                                + coverageBeanlist.get(i).getInformto()
                                + "[/INFORM_TO]"
                                + "[REASON_REMARK]"
                                + coverageBeanlist.get(i).getRemark()
                                + "[/REASON_REMARK][/USER_DATA][/DATA]";


                        SoapObject request = new SoapObject(CommonString1.NAMESPACE, CommonString1.METHOD_UPLOAD_DR_STORE_COVERAGE1);
                        request.addProperty("onXML", onXML);
                        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                        envelope.dotNet = true;
                        envelope.setOutputSoapObject(request);
                        HttpTransportSE androidHttpTransport = new HttpTransportSE(CommonString1.URL);
                        androidHttpTransport.call(CommonString1.SOAP_ACTION + CommonString1.METHOD_UPLOAD_DR_STORE_COVERAGE1, envelope);

                        Object result = (Object) envelope.getResponse();
                        datacheck = result.toString();
                        datacheck = datacheck.replace("\"", "");
                        words = datacheck.split("\\;");
                        validity = (words[0]);

                        if (validity.equalsIgnoreCase(CommonString1.KEY_SUCCESS)) {
                            if (intentData.equalsIgnoreCase("FromPrevStore")) {
                                db.open();
                                db.deletePreviousData(coverageBeanlist.get(i).getStoreId());
                            }
                        } else {
                            if (!result.toString().equalsIgnoreCase(CommonString1.KEY_SUCCESS)) {
                                return CommonString1.METHOD_UPLOAD_DR_STORE_COVERAGE;
                            }
                            if (result.toString().equalsIgnoreCase(CommonString1.KEY_FALSE)) {
                                return CommonString1.METHOD_UPLOAD_DR_STORE_COVERAGE;
                            }
                            if (result.toString().equalsIgnoreCase(CommonString1.KEY_FAILURE)) {
                                return CommonString1.METHOD_UPLOAD_DR_STORE_COVERAGE;
                            }
                        }
                        data.value = 40 * i;
                        data.name = "Uploading";

                        publishProgress(data);
                    }
                    return CommonString1.KEY_SUCCESS;
                }
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

                if (intentData.equalsIgnoreCase("FromPrevStore")) {

                    Toast.makeText(getApplicationContext(), "Pending Stores Successfully Uploaded", Toast.LENGTH_SHORT).show();

                } else if (intentData.equalsIgnoreCase("createCoverage")) {
                    db.open();
                    long id2 = db.InsertCoverageData(cdata);
                    if (id2 > 0) {

                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString(CommonString1.KEY_STOREVISITED_STATUS, "");
                        editor.putString(CommonString1.KEY_LATITUDE, "");
                        editor.putString(CommonString1.KEY_LONGITUDE, "");
                        editor.putString(CommonString1.KEY_CHECK_IN_TIME, getCurrentTime());
                        editor.commit();

                        if (channelcd.equalsIgnoreCase("1")) {
                            Intent in = new Intent(context, StoreEntry.class);
                            in.putExtra(CommonString1.KEY_STORE_CD, cdata.getStoreId());
                            startActivity(in);
                        } else {
                            Intent in = new Intent(context, StoreEntryForChannel2Activity.class);
                            startActivity(in);
                        }
                    } else {
                        AlertMessage.showToastMessage(UploadPreviousStoreDataActivity.this, "Coverage Not Created");
                    }

                    Toast.makeText(getApplicationContext(), "Intime Successfully Uploaded", Toast.LENGTH_SHORT).show();
                }
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
        return cdate;

    }
}

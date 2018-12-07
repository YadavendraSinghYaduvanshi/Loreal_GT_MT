package com.cpm.upload;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cpm.Constants.CommonString1;
import com.cpm.GetterSetter.DisplayMasterGetterSetter;
import com.cpm.GetterSetter.FreeVisibilityGetterSetter;
import com.cpm.GetterSetter.MappingPromotion2GetterSetter;
import com.cpm.GetterSetter.ShareShelfInsertHeaderDataGetterSetter;
import com.cpm.GetterSetter.StoreProfileGetterSetter;
import com.cpm.capitalfoods.R;
import com.cpm.database.GSKDatabase;
import com.cpm.delegates.CoverageBean;
import com.cpm.message.AlertMessage;
import com.cpm.xmlGetterSetter.BrandGetterSetter;
import com.cpm.xmlGetterSetter.COMPETITORGetterSetter;
import com.cpm.xmlGetterSetter.ChecklistGetterSetter;
import com.cpm.xmlGetterSetter.FailureGetterSetter;
import com.cpm.xmlGetterSetter.JourneyPlanGetterSetter;
import com.cpm.xmlGetterSetter.MappingAssetGetterSetter;
import com.cpm.xmlGetterSetter.POSMDATAGetterSetter;
import com.cpm.xmlGetterSetter.StockNewGetterSetter;
import com.cpm.xmlGetterSetter.StoreSignAgeGetterSetter;
import com.cpm.xmlGetterSetter.WindowSKUEntryGetterSetter;
import com.cpm.xmlGetterSetter.windowsChildData;
import com.cpm.xmlHandler.FailureXMLHandler;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class CheckoutNUpload extends Activity {

    public static String currLatitude = "0.0";
    public static String currLongitude = "0.0";
    ArrayList<JourneyPlanGetterSetter> jcplist;
    GSKDatabase database;
    boolean allNotUploaded = false;
    ArrayList<CoverageBean> coverageBean;
    String app_ver;
    String datacheck = "";
    String[] words;
    String validity;
    String errormsg = "";
    boolean up_success_flag = true, upload_status = false;
    int mid, uploadImgCount = 0, totalImag = 0;
    String Path;
    ArrayList<MappingPromotion2GetterSetter> mappingPromotionList = new ArrayList<>();
    private SharedPreferences preferences;
    private String username;
    private String visit_date;
    private String store_id;
    private String store_intime;
    private String store_out_time;
    private String date;
    private String prev_date;
    private Dialog dialog;
    private ProgressBar pb;
    private TextView percentage, message;
    private Data data;
    private ArrayList<CoverageBean> coverageBeanlist = new ArrayList<>();
    private int factor;
    private ArrayList<StoreSignAgeGetterSetter> STOREDETAIL = new ArrayList<>();
    private FailureGetterSetter failureGetterSetter = null;
    private ArrayList<StockNewGetterSetter> stockData = new ArrayList<>();
    private ArrayList<windowsChildData> WindowsData = new ArrayList<>();
    private ArrayList<MappingAssetGetterSetter> WindowsDataChecklist = new ArrayList<>();
    private ArrayList<BrandGetterSetter> stockavail2List = new ArrayList<BrandGetterSetter>();
    private ArrayList<DisplayMasterGetterSetter> paidVisibilityList = new ArrayList<DisplayMasterGetterSetter>();
    private ArrayList<FreeVisibilityGetterSetter> freeVisibilityList = new ArrayList<FreeVisibilityGetterSetter>();
    private ArrayList<ShareShelfInsertHeaderDataGetterSetter> shareshelfheaderList = new ArrayList<ShareShelfInsertHeaderDataGetterSetter>();
    private ArrayList<StoreSignAgeGetterSetter> SignAgeData = new ArrayList<StoreSignAgeGetterSetter>();
    private ArrayList<WindowSKUEntryGetterSetter> window1 = new ArrayList<WindowSKUEntryGetterSetter>();
    private ArrayList<WindowSKUEntryGetterSetter> window2 = new ArrayList<WindowSKUEntryGetterSetter>();
    private ArrayList<ChecklistGetterSetter> check1 = new ArrayList<ChecklistGetterSetter>();
    private ArrayList<POSMDATAGetterSetter> POSMdata = new ArrayList<POSMDATAGetterSetter>();
    private ArrayList<COMPETITORGetterSetter> COMPETITORDATA = new ArrayList<COMPETITORGetterSetter>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        setContentView(R.layout.checkout_n_upload);
        database = new GSKDatabase(this);
        database.open();
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        username = preferences.getString(CommonString1.KEY_USERNAME, "");
        app_ver = preferences.getString(CommonString1.KEY_VERSION, "");
        visit_date = preferences.getString(CommonString1.KEY_DATE, null);
        currLatitude = preferences.getString(CommonString1.KEY_LATITUDE, "0.0");
        currLongitude = preferences.getString(CommonString1.KEY_LONGITUDE, "0.0");
        Path = CommonString1.FILE_PATH;

        if (!isCheckoutDataExist()) {
            new UploadTask(this).execute();
        }
    }

    public boolean isCheckoutDataExist() {

        boolean flag = false;

        jcplist = database.getAllJCPData();

        for (int i = 0; i < jcplist.size(); i++) {

            if (!jcplist.get(i).getVISIT_DATE().get(0).equals(visit_date)) {

                prev_date = jcplist.get(i).getVISIT_DATE().get(0);

                if (jcplist.get(i).getCheckOutStatus().get(0).equals(CommonString1.KEY_VALID)) {

                    store_id = jcplist.get(i).getStore_cd().get(0);

                    coverageBean = database.getCoverageSpecificData(store_id);

                    if (coverageBean.size() > 0) {
                        flag = true;
                        username = coverageBean.get(0).getUserId();
                        store_intime = coverageBean.get(0).getInTime();
                        store_out_time = coverageBean.get(0).getOutTime();
                        if (store_out_time == null || store_out_time.equals("")) {
                            store_out_time = getCurrentTime();
                        }
                        date = coverageBean.get(0).getVisitDate();

                        new BackgroundTask(this).execute();
                    }

                }

            }
        }

        return flag;
    }

    public String getCurrentTime() {

        Calendar m_cal = Calendar.getInstance();

        return m_cal.get(Calendar.HOUR_OF_DAY) + ":"
                + m_cal.get(Calendar.MINUTE) + ":" + m_cal.get(Calendar.SECOND);

    }

    public String UploadImage(String path, String folder_path) throws Exception {

        errormsg = "";
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(Path + path, o);

        // The new size we want to scale to
        final int REQUIRED_SIZE = 1024;

        // Find the correct scale value. It should be the power of 2.
        int width_tmp = o.outWidth, height_tmp = o.outHeight;
        int scale = 1;

        while (true) {
            if (width_tmp < REQUIRED_SIZE && height_tmp < REQUIRED_SIZE)
                break;
            width_tmp /= 2;
            height_tmp /= 2;
            scale *= 2;
        }

        // Decode with inSampleSize
        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;
        Bitmap bitmap = BitmapFactory.decodeFile(
                Path + path, o2);

        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, bao);
        byte[] ba = bao.toByteArray();
        String ba1 = Base64.encodeBytes(ba);

        SoapObject request = new SoapObject(CommonString1.NAMESPACE,
                CommonString1.METHOD_UPLOAD_IMAGE);

        String[] split = path.split("/");
        String path1 = split[split.length - 1];

        request.addProperty("img", ba1);
        request.addProperty("name", path1);
        request.addProperty("FolderName", folder_path);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);

        HttpTransportSE androidHttpTransport = new HttpTransportSE(
                CommonString1.URL);

        androidHttpTransport
                .call(CommonString1.SOAP_ACTION_UPLOAD_IMAGE,
                        envelope);
        Object result = envelope.getResponse();

        if (!result.toString().equalsIgnoreCase(CommonString1.KEY_SUCCESS)) {

            if (result.toString().equalsIgnoreCase(CommonString1.KEY_FALSE)) {
                return CommonString1.KEY_FALSE;
            }

            SAXParserFactory saxPF = SAXParserFactory.newInstance();
            SAXParser saxP = saxPF.newSAXParser();
            XMLReader xmlR = saxP.getXMLReader();

            // for failure
            FailureXMLHandler failureXMLHandler = new FailureXMLHandler();
            xmlR.setContentHandler(failureXMLHandler);

            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(result.toString()));
            xmlR.parse(is);

            failureGetterSetter = failureXMLHandler
                    .getFailureGetterSetter();

            if (failureGetterSetter.getStatus().equalsIgnoreCase(
                    CommonString1.KEY_FAILURE)) {
                errormsg = failureGetterSetter.getErrorMsg();
                return CommonString1.KEY_FAILURE;
            }
        } else {
            new File(Path + path).delete();
            SharedPreferences.Editor editor = preferences
                    .edit();
            editor.putString(CommonString1.KEY_STOREVISITED_STATUS, "");
            editor.commit();
        }

        return result.toString();
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
            dialog.setTitle("Uploading Checkout Data");
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
                data = new Data();

                data.value = 20;
                data.name = "Checked out Data Uploading";
                publishProgress(data);

                String onXML = "[STORE_CHECK_OUT_STATUS][USER_ID]"
                        + username
                        + "[/USER_ID]" + "[STORE_ID]"
                        + store_id
                        + "[/STORE_ID][LATITUDE]"
                        + currLatitude
                        + "[/LATITUDE][LOGITUDE]"
                        + currLongitude
                        + "[/LOGITUDE][CHECKOUT_DATE]"
                        + date
                        + "[/CHECKOUT_DATE][CHECK_OUTTIME]"
                        + store_out_time
                        + "[/CHECK_OUTTIME][CHECK_INTIME]"
                        + store_intime
                        + "[/CHECK_INTIME][CREATED_BY]"
                        + username
                        + "[/CREATED_BY][/STORE_CHECK_OUT_STATUS]";


                final String sos_xml = "[DATA]" + onXML
                        + "[/DATA]";

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

                database.updateCoverageStoreOutTime(store_id, date, CommonString1.KEY_C);

                if (result.toString().equalsIgnoreCase(CommonString1.KEY_SUCCESS_chkout)) {

                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString(CommonString1.KEY_STOREVISITED, "");
                    editor.putString(CommonString1.KEY_STOREVISITED_STATUS, "");
                    editor.putString(CommonString1.KEY_LATITUDE, "");
                    editor.putString(CommonString1.KEY_LONGITUDE, "");
                    editor.commit();

                    database.updateStoreStatusOnCheckout(store_id, visit_date, CommonString1.KEY_C);

                } else {
                    if (result.toString().equalsIgnoreCase(CommonString1.KEY_FALSE)) {
                        return CommonString1.METHOD_Checkout_StatusNew;
                    }
                    // for failure
                }
                return CommonString1.KEY_SUCCESS;

            } catch (MalformedURLException e) {

                final AlertMessage message = new AlertMessage(
                        CheckoutNUpload.this,
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
                        CheckoutNUpload.this,
                        AlertMessage.MESSAGE_SOCKETEXCEPTION,
                        "socket", e);
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        message.showMessage();
                        // TODO Auto-generated method stub
                    }
                });
            } catch (Exception e) {
                final AlertMessage message = new AlertMessage(
                        CheckoutNUpload.this,
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

                new UploadTask(CheckoutNUpload.this).execute();

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

    private class UploadTask extends AsyncTask<Void, Data, String> {
        private Context context;

        UploadTask(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();

            dialog = new Dialog(context);
            dialog.setContentView(R.layout.custom_upload);
            dialog.setTitle("Uploading Data");
            dialog.setCancelable(false);
            dialog.show();
            pb = (ProgressBar) dialog.findViewById(R.id.progressBar1);
            percentage = (TextView) dialog.findViewById(R.id.percentage);
            message = (TextView) dialog.findViewById(R.id.message);
        }

        @Override
        protected String doInBackground(Void... params) {
            // TODO Auto-generated method stub
            try {
                data = new Data();
                coverageBeanlist = database.getCoverageData(prev_date);

                if (coverageBeanlist.size() > 0) {

                    if (coverageBeanlist.size() == 1) {
                        factor = 50;
                    } else {
                        factor = 100 / (coverageBeanlist.size());
                    }
                }
                for (int i = 0; i < coverageBeanlist.size(); i++) {
                    if (!coverageBeanlist.get(i).getStatus().equalsIgnoreCase(CommonString1.KEY_D)
                            && !coverageBeanlist.get(i).getStatus().equalsIgnoreCase(CommonString1.KEY_CHECK_IN)) {

                        if (!coverageBeanlist.get(i).getStatus().equalsIgnoreCase(CommonString1.KEY_D)) {

                            String onXML = "[DATA][USER_DATA][STORE_CD]"
                                    + coverageBeanlist.get(i).getStoreId()
                                    + "[/STORE_CD]" + "[VISIT_DATE]"
                                    + coverageBeanlist.get(i).getVisitDate()
                                    + "[/VISIT_DATE][LATITUDE]"
                                    + "0.0"//coverageBeanlist.get(i).getLatitude()
                                    + "[/LATITUDE][APP_VERSION]"
                                    + app_ver
                                    + "[/APP_VERSION][LONGITUDE]"
                                    + "0.0"//coverageBeanlist.get(i).getLongitude()
                                    + "[/LONGITUDE][IN_TIME]"
                                    + coverageBeanlist.get(i).getInTime()
                                    + "[/IN_TIME][OUT_TIME]"
                                    + coverageBeanlist.get(i).getOutTime()
                                    + "[/OUT_TIME][UPLOAD_STATUS]"
                                    + "N"
                                    + "[/UPLOAD_STATUS]"
                                    + "[USER_ID]" + username
                                    + "[/USER_ID]"
                                    + "[CHANNEL_CD]" + coverageBeanlist.get(i).getChannel_cd()
                                    + "[/CHANNEL_CD]"
                                    + "[IMAGE_URL]" + coverageBeanlist.get(i).getImage()
                                    + "[/IMAGE_URL]"
                                    + "[REASON_ID]" + coverageBeanlist.get(i).getReasonid()
                                    + "[/REASON_ID]"
                                    + "[SUBREASON_ID]" + coverageBeanlist.get(i).getSub_reasonId()
                                    + "[/SUBREASON_ID]"
                                    + "[INFORM_TO]" + coverageBeanlist.get(i).getInformto()
                                    + "[/INFORM_TO]"
                                    + "[REASON_REMARK]" + coverageBeanlist.get(i).getRemark()
                                    + "[/REASON_REMARK]" +
                                    "[/USER_DATA][/DATA]";

                            SoapObject request = new SoapObject(CommonString1.NAMESPACE, CommonString1.METHOD_UPLOAD_DR_STORE_COVERAGE1);
                            request.addProperty("onXML", onXML);
                            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                            envelope.dotNet = true;
                            envelope.setOutputSoapObject(request);
                            HttpTransportSE androidHttpTransport = new HttpTransportSE(CommonString1.URL);
                            androidHttpTransport.call(CommonString1.SOAP_ACTION + CommonString1.METHOD_UPLOAD_DR_STORE_COVERAGE1, envelope);

                            Object result = envelope.getResponse();

                            datacheck = result.toString();
                            datacheck = datacheck.replace("\"", "");
                            words = datacheck.split("\\;");
                            validity = (words[0]);

                            if (validity.equalsIgnoreCase(CommonString1.KEY_SUCCESS)) {
                                database.updateCoverageStatus(coverageBeanlist.get(i).getMID(), CommonString1.KEY_P);
                                database.updateStoreStatusOnLeave(coverageBeanlist.get(i).getStoreId(), coverageBeanlist.get(i).getVisitDate(), CommonString1.KEY_P);
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

                            mid = Integer.parseInt((words[1]));
                            data.value = 30;
                            data.name = "Uploading";
                            publishProgress(data);
                            //region STORE_INFO_NEW
                            database.open();
                            StoreProfileGetterSetter storeInfoGetSet = database.getStoreProfileData(coverageBeanlist.get(i).getStoreId());
                            if (storeInfoGetSet != null) {
                                onXML = "[STORE_PROFILE][MID]"
                                        + mid
                                        + "[/MID]"
                                        + "[CREATED_BY]"
                                        + username
                                        + "[/CREATED_BY]"
                                        + "[STORE_CD]"
                                        + coverageBeanlist.get(i).getStoreId()
                                        + "[/STORE_CD]"
                                        + "[RETAILER_NAME]"
                                        + storeInfoGetSet.getRetailerName()
                                        + "[/RETAILER_NAME]"
                                        + "[CONTACT]"
                                        + storeInfoGetSet.getContactNumber()
                                        + "[/CONTACT]"
                                        + "[CONTACT2]"
                                        + storeInfoGetSet.getContactNumber2()
                                        + "[/CONTACT2]"
                                        + "[POSTAL_ADDRESS]"
                                        + storeInfoGetSet.getPostalAddress()
                                        + "[/POSTAL_ADDRESS]"
                                        + "[CITY_CD]"
                                        + storeInfoGetSet.getCity_cd()
                                        + "[/CITY_CD]"
                                        + "[STATE_CD]"
                                        + storeInfoGetSet.getState_cd()
                                        + "[/STATE_CD]"
                                        + "[STORE_PROFILE_IMG]"
                                        + storeInfoGetSet.getAddressImg()
                                        + "[/STORE_PROFILE_IMG]"
                                        + "[KYC_ID]"
                                        + storeInfoGetSet.getKycId()
                                        + "[/KYC_ID]"
                                        + "[GST_NO]"
                                        + storeInfoGetSet.getGstNo()
                                        + "[/GST_NO]"
                                        + "[GST_IMG]"
                                        + storeInfoGetSet.getGstImg()
                                        + "[/GST_IMG]"
                                        + "[/STORE_PROFILE]";

                                final String sos_xml = "[DATA]" + onXML + "[/DATA]";
                                request = new SoapObject(CommonString1.NAMESPACE, CommonString1.METHOD_UPLOAD_XML);
                                request.addProperty("XMLDATA", sos_xml);
                                request.addProperty("KEYS", "STORE_PROFILE_NEW");
                                request.addProperty("USERNAME", username);
                                request.addProperty("MID", mid);
                                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                                envelope.dotNet = true;
                                envelope.setOutputSoapObject(request);
                                androidHttpTransport = new HttpTransportSE(CommonString1.URL);
                                androidHttpTransport.call(CommonString1.SOAP_ACTION + CommonString1.METHOD_UPLOAD_XML, envelope);
                                result = (Object) envelope.getResponse();
                                if (!result.toString().equalsIgnoreCase(
                                        CommonString1.KEY_SUCCESS)) {
                                    // return "STORE_INFO_NEW";
                                }
                                data.value = 10;
                                data.name = "Uploading Store Info";
                                publishProgress(data);
                            }
                            //endregion


                            String final_xml = "";
                            //------------------------------new----------------------
                            // stock present data
                            final_xml = "";
                            onXML = "";
                            stockData = database.getStockPresentUpload(coverageBeanlist.get(i).getStoreId());

                            if (stockData.size() > 0) {

                                for (int j = 0; j < stockData.size(); j++) {

                                    onXML = "[STOCK_DATA][MID]"
                                            + mid
                                            + "[/MID]"
                                            + "[CREATED_BY]"
                                            + username
                                            + "[/CREATED_BY]"
                                            + "[SKU_CD]"
                                            + stockData.get(j).getSku_cd()
                                            + "[/SKU_CD]"
                                            + "[OPENING_STOCK_PRESENT]"
                                            + stockData.get(j).getSkuQuantity()
                                            + "[/OPENING_STOCK_PRESENT]"
                                            + "[/STOCK_DATA]";

                                    final_xml = final_xml + onXML;

                                }

                                final String sos_xml = "[DATA]" + final_xml
                                        + "[/DATA]";

                                request = new SoapObject(
                                        CommonString1.NAMESPACE,
                                        CommonString1.METHOD_UPLOAD_XML);
                                request.addProperty("XMLDATA", sos_xml);
                                request.addProperty("KEYS", "STOCK_DATA_PRESENT_NEW");
                                request.addProperty("USERNAME", username);
                                request.addProperty("MID", mid);

                                envelope = new SoapSerializationEnvelope(
                                        SoapEnvelope.VER11);
                                envelope.dotNet = true;
                                envelope.setOutputSoapObject(request);

                                androidHttpTransport = new HttpTransportSE(
                                        CommonString1.URL);

                                androidHttpTransport.call(
                                        CommonString1.SOAP_ACTION + CommonString1.METHOD_UPLOAD_XML,
                                        envelope);
                                result = (Object) envelope.getResponse();


                                if (!result.toString().equalsIgnoreCase(
                                        CommonString1.KEY_SUCCESS)) {

                                    return "STOCK_DATA_PRESENT";
                                }
                                if (result.toString().equalsIgnoreCase(
                                        CommonString1.KEY_NO_DATA)) {
                                    return CommonString1.METHOD_UPLOAD_XML;
                                }
                                if (result.toString().equalsIgnoreCase(
                                        CommonString1.KEY_FAILURE)) {
                                    return CommonString1.METHOD_UPLOAD_XML;
                                }
                            }
                            data.value = 20;
                            data.name = "STOCK_DATA_PRESENT";

                            publishProgress(data);


//							uploading SIGNAGE_DATA

                            final_xml = "";
                            onXML = "";
                            SignAgeData = database.getSFTData(coverageBeanlist.get(i).getStoreId());

                            if (SignAgeData.size() > 0) {

                                for (int j = 0; j < SignAgeData.size(); j++) {

                                    onXML = "[SIGNAGE_DATA][MID]"
                                            + mid
                                            + "[/MID]"

                                            + "[CREATED_BY]"
                                            + username
                                            + "[/CREATED_BY]"
                                            + "[SIGN_EXIST]"
                                            + SignAgeData.get(j).getExistSpinner_CD()
                                            + "[/SIGN_EXIST]"
                                            + "[WORKING]"
                                            + SignAgeData.get(j).getWorkingsppiner_CD()
                                            + "[/WORKING]"
                                            + "[IMAGE_URL]"
                                            + SignAgeData.get(j).getImage()
                                            + "[/IMAGE_URL]"
                                            + "[/SIGNAGE_DATA]";

                                    final_xml = final_xml + onXML;

                                }

                                final String sos_xml = "[DATA]" + final_xml
                                        + "[/DATA]";

                                request = new SoapObject(
                                        CommonString1.NAMESPACE,
                                        CommonString1.METHOD_UPLOAD_XML);
                                request.addProperty("XMLDATA", sos_xml);
                                request.addProperty("KEYS", "SIGNAGE_DATA");
                                request.addProperty("USERNAME", username);
                                request.addProperty("MID", mid);

                                envelope = new SoapSerializationEnvelope(
                                        SoapEnvelope.VER11);
                                envelope.dotNet = true;
                                envelope.setOutputSoapObject(request);

                                androidHttpTransport = new HttpTransportSE(
                                        CommonString1.URL);

                                androidHttpTransport.call(
                                        CommonString1.SOAP_ACTION + CommonString1.METHOD_UPLOAD_XML,
                                        envelope);
                                result = (Object) envelope.getResponse();


                                if (!result.toString().equalsIgnoreCase(
                                        CommonString1.KEY_SUCCESS)) {

                                    //return "SIGNAGE_DATA";
                                }

                            }


                            data.value = 30;
                            data.name = "SIGNAGE_DATA";

                            publishProgress(data);


//							uploading POSM_DATA data

                            final_xml = "";
                            onXML = "";
                            POSMdata = database.getPOSM2data(coverageBeanlist.get(i).getStoreId());

                            if (POSMdata.size() > 0) {

                                for (int j = 0; j < POSMdata.size(); j++) {
                                    String isExist = "0";
                                    if (POSMdata.get(j).isExist()) {
                                        isExist = "1";
                                    }

                                    String reason_cd = "";
                                    if (POSMdata.get(j).getReason_CD().equalsIgnoreCase("-1")) {
                                        reason_cd = "0";
                                    } else {
                                        reason_cd = POSMdata.get(j).getReason_CD();
                                    }

                                    onXML = "[POSM_DATA][MID]"
                                            + mid
                                            + "[/MID]"
                                            + "[CREATED_BY]"
                                            + username
                                            + "[/CREATED_BY]"
                                            + "[REASON_CD]"
                                            + reason_cd
                                            + "[/REASON_CD]"
                                            + "[POSM_CD]"
                                            + POSMdata.get(j).getPOSM_CD().get(0)
                                            + "[/POSM_CD]"
                                            + "[Image_URL]"
                                            + POSMdata.get(j).getImage_Url()
                                            + "[/Image_URL]"
                                            + "[ISEXIST]"
                                            + isExist
                                            + "[/ISEXIST]"
                                            + "[/POSM_DATA]";


                                    final_xml = final_xml + onXML;

                                }

                                final String sos_xml = "[DATA]" + final_xml
                                        + "[/DATA]";

                                request = new SoapObject(
                                        CommonString1.NAMESPACE,
                                        CommonString1.METHOD_UPLOAD_XML);
                                request.addProperty("XMLDATA", sos_xml);
                                request.addProperty("KEYS", "POSM_DATA_NEW");
                                request.addProperty("USERNAME", username);
                                request.addProperty("MID", mid);

                                envelope = new SoapSerializationEnvelope(
                                        SoapEnvelope.VER11);
                                envelope.dotNet = true;
                                envelope.setOutputSoapObject(request);

                                androidHttpTransport = new HttpTransportSE(
                                        CommonString1.URL);

                                androidHttpTransport.call(
                                        CommonString1.SOAP_ACTION + CommonString1.METHOD_UPLOAD_XML,
                                        envelope);
                                result = (Object) envelope.getResponse();

                                if (!result.toString().equalsIgnoreCase(
                                        CommonString1.KEY_SUCCESS)) {
                                    //return "POSM_DATA";
                                }


                            }

                            data.value = 45;
                            data.name = "POSM_DATA";
                            publishProgress(data);


                            ///// COMPETITOR DATA
                            final_xml = "";
                            onXML = "";
                            COMPETITORDATA = database.getCOMPETITORData(coverageBeanlist.get(i).getStoreId());

                            if (COMPETITORDATA.size() > 0) {

                                for (int j = 0; j < COMPETITORDATA.size(); j++) {

                                    onXML = "[COMPETITOR_DATA][MID]"
                                            + mid
                                            + "[/MID]"

                                            + "[CREATED_BY]"
                                            + username
                                            + "[/CREATED_BY]"
                                            + "[COMPANY_CD]"
                                            + COMPETITORDATA.get(j).getCOMPANY_CD()
                                            + "[/COMPANY_CD]"
                                            + "[REMARKS]"
                                            + COMPETITORDATA.get(j).getEdText()
                                            + "[/REMARKS]"
                                            + "[WINDOW]"
                                            + COMPETITORDATA.get(j).getSpinnerExists_CD()
                                            + "[/WINDOW]"
                                            + "[Image_URL]"
                                            + COMPETITORDATA.get(j).getImage()
                                            + "[/Image_URL]"


                                            + "[/COMPETITOR_DATA]";

                                    final_xml = final_xml + onXML;

                                }

                                final String sos_xml = "[DATA]" + final_xml
                                        + "[/DATA]";

                                request = new SoapObject(
                                        CommonString1.NAMESPACE,
                                        CommonString1.METHOD_UPLOAD_XML);
                                request.addProperty("XMLDATA", sos_xml);
                                request.addProperty("KEYS", "COMPETITOR_DATA");
                                request.addProperty("USERNAME", username);
                                request.addProperty("MID", mid);

                                envelope = new SoapSerializationEnvelope(
                                        SoapEnvelope.VER11);
                                envelope.dotNet = true;
                                envelope.setOutputSoapObject(request);

                                androidHttpTransport = new HttpTransportSE(
                                        CommonString1.URL);

                                androidHttpTransport.call(
                                        CommonString1.SOAP_ACTION + CommonString1.METHOD_UPLOAD_XML,
                                        envelope);
                                result = (Object) envelope.getResponse();


                                if (!result.toString().equalsIgnoreCase(
                                        CommonString1.KEY_SUCCESS)) {

                                    // return "COMPETITOR_DATA";
                                }

                            }


                            data.value = 50;
                            data.name = "COMPETITOR_DATA";

                            publishProgress(data);


                            ///////////////////////////////

///////window1 DATA
                            final_xml = "";
                            String final_xml_checklist = "", onXML_checklist = "";
                            onXML = "";
                            window1 = database.getwindowdat(coverageBeanlist.get(i).getStoreId());

                            if (window1.size() > 0) {

                                for (int j = 0; j < window1.size(); j++) {

                                    check1 = database.getchecklist(window1.get(j).getKeyID());

                                    for (int k = 0; k < check1.size(); k++) {

                                        onXML_checklist = "[CHECK_LIST][MID]"
                                                + mid
                                                + "[/MID]"
                                                + "[CREATED_BY]"
                                                + username
                                                + "[/CREATED_BY]"
                                                + "[WINDOW_CD]"
                                                + check1.get(k).getWINDOW_CD()
                                                + "[/WINDOW_CD]"
                                                + "[COMMON_ID]"
                                                + check1.get(k).getCOMMON_ID()
                                                + "[/COMMON_ID]"
                                                + "[CHECKLIST_CD]"
                                                + check1.get(k).getCHECKLIST_CD()
                                                + "[/CHECKLIST_CD]"
                                                + "[ANSWER_CD]"
                                                + check1.get(k).getANSWER_CD()
                                                + "[/ANSWER_CD]"
                                                + "[/CHECK_LIST]";

                                        final_xml_checklist = final_xml_checklist + onXML_checklist;
                                    }

                                    final String sos_xml_checklist = "[DATA]" + final_xml_checklist
                                            + "[/DATA]";

                                    onXML = "[WINDOW_DATA][MID]"
                                            + mid
                                            + "[/MID]"
                                            + "[CREATED_BY]"
                                            + username
                                            + "[/CREATED_BY]"
                                            + "[WINDOW_CD]"
                                            + window1.get(j).getWindow_cd()
                                            + "[/WINDOW_CD]"
                                            + "[KEY_ID]"
                                            + window1.get(j).getKeyID()
                                            + "[/KEY_ID]"
                                            + "[WINDOW_IMAGE]"
                                            + window1.get(j).getWindow_image()
                                            + "[/WINDOW_IMAGE]"
                                            + "[REASON_ID]"
                                            + window1.get(j).getReason_id()
                                            + "[/REASON_ID]"
                                            + "[EXISTORNOT]"
                                            + window1.get(j).getExistOrnot()
                                            + "[/EXISTORNOT]"
                                            + "[CHECKLIST_DATA]"
                                            + sos_xml_checklist
                                            + "[/CHECKLIST_DATA]"
                                            + "[/WINDOW_DATA]";

                                    final_xml = final_xml + onXML;

                                }

                                final String sos_xml = "[DATA]" + final_xml
                                        + "[/DATA]";

                                request = new SoapObject(
                                        CommonString1.NAMESPACE,
                                        CommonString1.METHOD_UPLOAD_XML);
                                request.addProperty("XMLDATA", sos_xml);
                                request.addProperty("KEYS", "WINDOW_AND_CHECKLIST_DATA_NEW");
                                request.addProperty("USERNAME", username);
                                request.addProperty("MID", mid);

                                envelope = new SoapSerializationEnvelope(
                                        SoapEnvelope.VER11);
                                envelope.dotNet = true;
                                envelope.setOutputSoapObject(request);

                                androidHttpTransport = new HttpTransportSE(
                                        CommonString1.URL);

                                androidHttpTransport.call(
                                        CommonString1.SOAP_ACTION + CommonString1.METHOD_UPLOAD_XML,
                                        envelope);
                                result = (Object) envelope.getResponse();


                                if (!result.toString().equalsIgnoreCase(
                                        CommonString1.KEY_SUCCESS)) {

                                    // return "WINDOW_DATA";
                                }

                            }


                            data.value = 80;
                            data.name = "WINDOW_DATA";

                            publishProgress(data);


/////// Share shelf Data

                            final_xml = "";
                            onXML = "";

                            shareshelfheaderList = database.getShareShelfHeaderData(coverageBeanlist.get(i).getStoreId());

                            if (shareshelfheaderList.size() > 0) {

                                for (int j = 0; j < shareshelfheaderList.size(); j++) {

                                    onXML = "[SHARESHELF_DATA][MID]"
                                            + mid
                                            + "[/MID]"
                                            + "[CREATED_BY]"
                                            + username
                                            + "[/CREATED_BY]"
                                            + "[CATEGORY_CD]"
                                            + shareshelfheaderList.get(j).getCategorycd()
                                            + "[/CATEGORY_CD]"
                                            + "[CATEGORY_QTY]"
                                            + shareshelfheaderList.get(j).getCategory_qty()
                                            + "[/CATEGORY_QTY]"
                                            + "[IMAGE]"
                                            + shareshelfheaderList.get(j).getImage_name()
                                            + "[/IMAGE]"
                                            + "[BRAND_CD]"
                                            + shareshelfheaderList.get(j).getBrand_cd()
                                            + "[/BRAND_CD]"
                                            + "[BRAND_QTY]"
                                            + shareshelfheaderList.get(j).getBrand_qty()
                                            + "[/BRAND_QTY]"
                                            + "[/SHARESHELF_DATA]";

                                    final_xml = final_xml + onXML;

                                }

                                final String sos_xml = "[DATA]" + final_xml
                                        + "[/DATA]";

                                request = new SoapObject(
                                        CommonString1.NAMESPACE,
                                        CommonString1.METHOD_UPLOAD_XML);
                                request.addProperty("XMLDATA", sos_xml);
                                request.addProperty("KEYS", "MT_SHARESHELF_DATA");
                                request.addProperty("USERNAME", username);
                                request.addProperty("MID", mid);

                                envelope = new SoapSerializationEnvelope(
                                        SoapEnvelope.VER11);
                                envelope.dotNet = true;
                                envelope.setOutputSoapObject(request);

                                androidHttpTransport = new HttpTransportSE(
                                        CommonString1.URL);

                                androidHttpTransport.call(
                                        CommonString1.SOAP_ACTION + CommonString1.METHOD_UPLOAD_XML,
                                        envelope);
                                result = (Object) envelope.getResponse();


                                if (!result.toString().equalsIgnoreCase(
                                        CommonString1.KEY_SUCCESS)) {
                                    // return "SHARESHELF_DATA";
                                }

                            }


                            data.value = 80;
                            data.name = "SHARESHELF_DATA";

                            publishProgress(data);


/////// STOCK AVAILABILITY FOR CHANNEL 2 Data

                            final_xml = "";
                            onXML = "";

                            stockavail2List = database.getStockAvailabilityForChannel2Data(coverageBeanlist.get(i).getStoreId());

                            if (stockavail2List.size() > 0) {

                                for (int j = 0; j < stockavail2List.size(); j++) {

                                    onXML = "[STOCKAVAILABILITYFORCHANNEL2_DATA][MID]"
                                            + mid
                                            + "[/MID]"
                                            + "[CREATED_BY]"
                                            + username
                                            + "[/CREATED_BY]"
                                            + "[BRAND_CD]"
                                            + stockavail2List.get(j).getBrand_cd().get(0)
                                            + "[/BRAND_CD]"
                                            + "[SKU_CD]"
                                            + stockavail2List.get(j).getSku_cd()
                                            + "[/SKU_CD]"
                                            + "[SKU]"
                                            + stockavail2List.get(j).getQuantity()
                                            + "[/SKU]"
                                            + "[/STOCKAVAILABILITYFORCHANNEL2_DATA]";

                                    final_xml = final_xml + onXML;

                                }

                                final String sos_xml = "[DATA]" + final_xml
                                        + "[/DATA]";

                                request = new SoapObject(
                                        CommonString1.NAMESPACE,
                                        CommonString1.METHOD_UPLOAD_XML);
                                request.addProperty("XMLDATA", sos_xml);
                                request.addProperty("KEYS", "MT_STOCKAVAILABILITYFORCHANNEL2_DATA");
                                request.addProperty("USERNAME", username);
                                request.addProperty("MID", mid);

                                envelope = new SoapSerializationEnvelope(
                                        SoapEnvelope.VER11);
                                envelope.dotNet = true;
                                envelope.setOutputSoapObject(request);

                                androidHttpTransport = new HttpTransportSE(
                                        CommonString1.URL);

                                androidHttpTransport.call(
                                        CommonString1.SOAP_ACTION + CommonString1.METHOD_UPLOAD_XML,
                                        envelope);
                                result = (Object) envelope.getResponse();


                                if (!result.toString().equalsIgnoreCase(
                                        CommonString1.KEY_SUCCESS)) {

                                    // return "STOCKAVAILABILITYFORCHANNEL2_DATA";
                                }
                            }


                            data.value = 80;
                            data.name = "STOCKAVAILABILITYFORCHANNEL2_DATA";

                            publishProgress(data);


/////// PAID_VISIBILITY Data

                            final_xml = "";
                            onXML = "";

                            paidVisibilityList = database.getPaidVisibilityData(coverageBeanlist.get(i).getStoreId());

                            if (paidVisibilityList.size() > 0) {

                                for (int j = 0; j < paidVisibilityList.size(); j++) {

                                    int k = 0;

                                    if (paidVisibilityList.get(j).getExist()) {
                                        k = 1;
                                    }


                                    onXML = "[PAID_VISIBILITY_DATA][MID]"
                                            + mid
                                            + "[/MID]"
                                            + "[CREATED_BY]"
                                            + username
                                            + "[/CREATED_BY]"
                                            + "[DISPLAY_CD]"
                                            + paidVisibilityList.get(j).getDISPLAY_CD().get(0)
                                            + "[/DISPLAY_CD]"
                                            + "[BRAND_CD]"
                                            + paidVisibilityList.get(j).getBrand_cd()
                                            + "[/BRAND_CD]"
                                            + "[IS_EXIST]"
                                            + k
                                            + "[/IS_EXIST]"
                                            + "[IMAGE]"
                                            + paidVisibilityList.get(j).getImage()
                                            + "[/IMAGE]"
                                            + "[REMARK]"
                                            + paidVisibilityList.get(j).getRemark()
                                            + "[/REMARK]"
                                            + "[/PAID_VISIBILITY_DATA]";

                                    final_xml = final_xml + onXML;

                                }

                                final String sos_xml = "[DATA]" + final_xml
                                        + "[/DATA]";

                                request = new SoapObject(
                                        CommonString1.NAMESPACE,
                                        CommonString1.METHOD_UPLOAD_XML);
                                request.addProperty("XMLDATA", sos_xml);
                                request.addProperty("KEYS", "MT_PAID_VISIBILITY_DATA");
                                request.addProperty("USERNAME", username);
                                request.addProperty("MID", mid);

                                envelope = new SoapSerializationEnvelope(
                                        SoapEnvelope.VER11);
                                envelope.dotNet = true;
                                envelope.setOutputSoapObject(request);

                                androidHttpTransport = new HttpTransportSE(
                                        CommonString1.URL);

                                androidHttpTransport.call(
                                        CommonString1.SOAP_ACTION + CommonString1.METHOD_UPLOAD_XML,
                                        envelope);
                                result = (Object) envelope.getResponse();


                                if (!result.toString().equalsIgnoreCase(
                                        CommonString1.KEY_SUCCESS)) {

                                    // return "PAID_VISIBILITY_DATA";
                                }

                            }


                            data.value = 80;
                            data.name = "PAID_VISIBILITY_DATA";

                            publishProgress(data);


/////// FREE_VISIBILITY Data

                            final_xml = "";
                            onXML = "";

                            freeVisibilityList = database.getFreeVisibilityData(coverageBeanlist.get(i).getStoreId());

                            if (freeVisibilityList.size() > 0) {

                                for (int j = 0; j < freeVisibilityList.size(); j++) {

                                    onXML = "[FREE_VISIBILITY_DATA][MID]"
                                            + mid
                                            + "[/MID]"
                                            + "[CREATED_BY]"
                                            + username
                                            + "[/CREATED_BY]"
                                            + "[DISPLAY_CD]"
                                            + freeVisibilityList.get(j).getDisplay_cd()
                                            + "[/DISPLAY_CD]"
                                            + "[COMPANY_CD]"
                                            + freeVisibilityList.get(j).getCompany_cd()
                                            + "[/COMPANY_CD]"
                                            + "[CATEGORY_CD]"
                                            + freeVisibilityList.get(j).getCategory_cd()
                                            + "[/CATEGORY_CD]"
                                            + "[QUANTITY]"
                                            + freeVisibilityList.get(j).getQuantity()
                                            + "[/QUANTITY]"
                                            + "[IMAGE]"
                                            + freeVisibilityList.get(j).getImage()
                                            + "[/IMAGE]"
                                            + "[/FREE_VISIBILITY_DATA]";

                                    final_xml = final_xml + onXML;

                                }

                                final String sos_xml = "[DATA]" + final_xml
                                        + "[/DATA]";

                                request = new SoapObject(
                                        CommonString1.NAMESPACE,
                                        CommonString1.METHOD_UPLOAD_XML);
                                request.addProperty("XMLDATA", sos_xml);
                                request.addProperty("KEYS", "MT_FREE_VISIBILITY_DATA_NEW");
                                request.addProperty("USERNAME", username);
                                request.addProperty("MID", mid);

                                envelope = new SoapSerializationEnvelope(
                                        SoapEnvelope.VER11);
                                envelope.dotNet = true;
                                envelope.setOutputSoapObject(request);

                                androidHttpTransport = new HttpTransportSE(
                                        CommonString1.URL);

                                androidHttpTransport.call(
                                        CommonString1.SOAP_ACTION + CommonString1.METHOD_UPLOAD_XML,
                                        envelope);
                                result = (Object) envelope.getResponse();


                                if (!result.toString().equalsIgnoreCase(
                                        CommonString1.KEY_SUCCESS)) {

                                    //return "FREE_VISIBILITY_DATA";
                                }

                            }


                            data.value = 80;
                            data.name = "FREE_VISIBILITY_DATA";

                            publishProgress(data);


                            //Promotion Data

                            final_xml = "";
                            onXML = "";

                            mappingPromotionList = database.getPromotionInsertedData(coverageBeanlist.get(i).getStoreId());

                            if (mappingPromotionList.size() > 0) {

                                for (int j = 0; j < mappingPromotionList.size(); j++) {

                                    int k = 0;

                                    if (mappingPromotionList.get(j).isExist()) {
                                        k = 1;
                                    }

                                    onXML = "[PROMOTION_DATA][MID]"
                                            + mid
                                            + "[/MID]"
                                            + "[CREATED_BY]"
                                            + username
                                            + "[/CREATED_BY]"
                                            + "[ID]"
                                            + mappingPromotionList.get(j).getID().get(0)
                                            + "[/ID]"
                                            + "[IS_EXIST]"
                                            + k
                                            + "[/IS_EXIST]"
                                            + "[IMAGE]"
                                            + mappingPromotionList.get(j).getImage()
                                            + "[/IMAGE]"
                                            + "[REASON_CD]"
                                            + mappingPromotionList.get(j).getReason_cd()
                                            + "[/REASON_CD]"
                                            + "[/PROMOTION_DATA]";

                                    final_xml = final_xml + onXML;

                                }

                                final String sos_xml = "[DATA]" + final_xml
                                        + "[/DATA]";

                                request = new SoapObject(
                                        CommonString1.NAMESPACE,
                                        CommonString1.METHOD_UPLOAD_XML);
                                request.addProperty("XMLDATA", sos_xml);
                                request.addProperty("KEYS", "MT_PROMOTION_DATA");
                                request.addProperty("USERNAME", username);
                                request.addProperty("MID", mid);

                                envelope = new SoapSerializationEnvelope(
                                        SoapEnvelope.VER11);
                                envelope.dotNet = true;
                                envelope.setOutputSoapObject(request);

                                androidHttpTransport = new HttpTransportSE(
                                        CommonString1.URL);

                                androidHttpTransport.call(
                                        CommonString1.SOAP_ACTION + CommonString1.METHOD_UPLOAD_XML,
                                        envelope);
                                result = (Object) envelope.getResponse();


                                if (!result.toString().equalsIgnoreCase(
                                        CommonString1.KEY_SUCCESS)) {

                                    // return "MT_PROMOTION_DATA";
                                }

                            }

                            // SET COVERAGE STATUS

                            final_xml = "";
                            onXML = "";

                            onXML = "[COVERAGE_STATUS][STORE_ID]"
                                    + coverageBeanlist.get(i).getStoreId()
                                    + "[/STORE_ID]"
                                    + "[VISIT_DATE]"
                                    + coverageBeanlist.get(i).getVisitDate()
                                    + "[/VISIT_DATE]"
                                    + "[USER_ID]"
                                    + coverageBeanlist.get(i).getUserId()
                                    + "[/USER_ID]"
                                    + "[STATUS]"
                                    + CommonString1.KEY_D
                                    + "[/STATUS]"
                                    + "[/COVERAGE_STATUS]";

                            final_xml = final_xml + onXML;

                            String sos_xml = "[DATA]" + final_xml + "[/DATA]";

                            SoapObject request1 = new SoapObject(CommonString1.NAMESPACE, CommonString1.MEHTOD_UPLOAD_COVERAGE_STATUS);
                            request1.addProperty("onXML", sos_xml);
                            SoapSerializationEnvelope envelope1 = new SoapSerializationEnvelope(
                                    SoapEnvelope.VER11);
                            envelope1.dotNet = true;
                            envelope1.setOutputSoapObject(request1);
                            HttpTransportSE androidHttpTransport1 = new HttpTransportSE(CommonString1.URL);
                            androidHttpTransport1.call(CommonString1.SOAP_ACTION + CommonString1.MEHTOD_UPLOAD_COVERAGE_STATUS, envelope1);

                            Object result1 = (Object) envelope1.getResponse();

                            if (result1.toString().equalsIgnoreCase(
                                    CommonString1.KEY_SUCCESS)) {

                                database.open();
                                database.updateCoverageStatus(coverageBeanlist.get(i)
                                        .getMID(), CommonString1.KEY_D);
                                database.updateStoreStatusOnLeave(coverageBeanlist.get(i)
                                        .getStoreId(), coverageBeanlist.get(i)
                                        .getVisitDate(), CommonString1.KEY_D);

                            }
                            // SET COVERAGE STATUS

                            data.value = factor * (i + 1);
                            data.value = 80;
                            data.name = "Images";

                            publishProgress(data);
                        }
                    }
                }


                //region Upload image with retrofit 23-05-2017
                uploadImgCount = 0;
                totalImag = 0;
                File f = new File(CommonString1.FILE_PATH);
                totalImag = f.listFiles().length;
                if (totalImag > 0) {
                    UploadImageWithRetrofit uploadRetro = new UploadImageWithRetrofit(context);
                    uploadImgCount = uploadRetro.UploadImagesAndData();
                    if (uploadImgCount == -1) {
                        throw new IOException();
                    } else if (uploadImgCount == -2) {
                        throw new Exception();
                    }
                }


                if (is95uploaded(uploadImgCount, totalImag)) {

                    coverageBeanlist = database.getCoverageData(prev_date);
                    for (int i = 0; i < coverageBeanlist.size(); i++) {

                        if (coverageBeanlist.get(i).getStatus().equalsIgnoreCase(CommonString1.KEY_D)) {

                            String final_xml = "";
                            String onXML = "";

                            onXML = "[COVERAGE_STATUS][STORE_ID]"
                                    + coverageBeanlist.get(i).getStoreId()
                                    + "[/STORE_ID]"
                                    + "[VISIT_DATE]"
                                    + coverageBeanlist.get(i).getVisitDate()
                                    + "[/VISIT_DATE]"
                                    + "[USER_ID]"
                                    + coverageBeanlist.get(i).getUserId()
                                    + "[/USER_ID]"
                                    + "[STATUS]"
                                    + CommonString1.KEY_U
                                    + "[/STATUS]"
                                    + "[/COVERAGE_STATUS]";

                            final_xml = final_xml + onXML;

                            final String sos_xml2 = "[DATA]" + final_xml
                                    + "[/DATA]";

                            SoapObject request1 = new SoapObject(
                                    CommonString1.NAMESPACE,
                                    CommonString1.MEHTOD_UPLOAD_COVERAGE_STATUS);
                            request1.addProperty("onXML", sos_xml2);
                            SoapSerializationEnvelope envelope1 = new SoapSerializationEnvelope(
                                    SoapEnvelope.VER11);
                            envelope1.dotNet = true;
                            envelope1.setOutputSoapObject(request1);

                            HttpTransportSE androidHttpTransport1 = new HttpTransportSE(
                                    CommonString1.URL);

                            androidHttpTransport1.call(
                                    CommonString1.SOAP_ACTION + CommonString1.MEHTOD_UPLOAD_COVERAGE_STATUS,
                                    envelope1);
                            Object result1 = (Object) envelope1.getResponse();


                            if (result1.toString().equalsIgnoreCase(
                                    CommonString1.KEY_SUCCESS)) {

                                database.open();

                                database.updateCoverageStatus(coverageBeanlist.get(i)
                                        .getMID(), CommonString1.KEY_U);
                                database.updateStoreStatusOnLeave(coverageBeanlist.get(i)
                                        .getStoreId(), coverageBeanlist.get(i)
                                        .getVisitDate(), CommonString1.KEY_U);

                                database.deleteSpecificStoreData(coverageBeanlist.get(i).getStoreId());
                            }


                            if (!result1
                                    .toString()
                                    .equalsIgnoreCase(
                                            CommonString1.KEY_SUCCESS)) {

                                // return "COVERAGE_STATUS";
                            }

                            data.value = factor * (i + 1);
                            data.name = "COVERAGE_STATUS";

                            publishProgress(data);

                        }
                    }
                } else {
                    allNotUploaded = true;
                }
            } catch (MalformedURLException e) {
                up_success_flag = false;
                final AlertMessage message = new AlertMessage(
                        CheckoutNUpload.this,
                        AlertMessage.MESSAGE_EXCEPTION, "download", e);
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        message.showMessage();
                    }
                });

            } catch (IOException e) {
                up_success_flag = false;

                final AlertMessage message = new AlertMessage(
                        CheckoutNUpload.this,
                        AlertMessage.MESSAGE_SOCKETEXCEPTION, "socket", e);

                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        message.showMessage();

                    }
                });
            } catch (Exception e) {

                up_success_flag = false;

                final AlertMessage message = new AlertMessage(
                        CheckoutNUpload.this,
                        AlertMessage.MESSAGE_EXCEPTION, "download", e);

                e.getMessage();
                e.printStackTrace();
                e.getCause();
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        message.showMessage();
                    }
                });
            }
            if (up_success_flag) {
                return CommonString1.KEY_SUCCESS;
            } else {
                return CommonString1.KEY_FAILURE;
            }
        }

        boolean is95uploaded(int upload, int total) {
            boolean result = true;
            if (total != 0) {
                int percent = ((upload / total) * 100);
                if (percent < 95) {
                    result = false;
                }
            }
            return result;
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

            if (result.contains(CommonString1.KEY_SUCCESS)) {

                AlertMessage message = new AlertMessage(CheckoutNUpload.this, AlertMessage.MESSAGE_UPLOAD_DATA, "success", null);
                message.showMessage();

                if (allNotUploaded) {
                    Toast.makeText(getApplicationContext(), "All images not uploaded", Toast.LENGTH_LONG).show();
                }

            } else if (!result.contains(CommonString1.KEY_FAILURE)) {

                AlertMessage message = new AlertMessage(
                        CheckoutNUpload.this, "Error in uploading :" + result, "success", null);
                message.showMessage();
            }
        }
    }

}

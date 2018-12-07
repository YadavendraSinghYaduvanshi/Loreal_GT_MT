package com.cpm.upload;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cpm.Constants.AlertandMessages;
import com.cpm.Constants.CommonString1;
import com.cpm.GetterSetter.DisplayMasterGetterSetter;
import com.cpm.GetterSetter.FreeVisibilityGetterSetter;
import com.cpm.GetterSetter.MappingPromotion2GetterSetter;
import com.cpm.GetterSetter.ShareShelfInsertHeaderDataGetterSetter;
import com.cpm.GetterSetter.StoreProfileGetterSetter;
import com.cpm.Loreal_GT.MainMenuActivity;
import com.cpm.capitalfoods.R;
import com.cpm.database.GSKDatabase;
import com.cpm.delegates.CoverageBean;
import com.cpm.message.AlertMessage;
import com.cpm.xmlGetterSetter.BrandGetterSetter;
import com.cpm.xmlGetterSetter.COMPETITORGetterSetter;
import com.cpm.xmlGetterSetter.ChecklistGetterSetter;
import com.cpm.xmlGetterSetter.POSMDATAGetterSetter;
import com.cpm.xmlGetterSetter.StockNewGetterSetter;
import com.cpm.xmlGetterSetter.StoreSignAgeGetterSetter;
import com.cpm.xmlGetterSetter.WindowSKUEntryGetterSetter;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

@SuppressWarnings("deprecation")
public class UploadDataActivityWithoutWait extends Activity {

    private Dialog dialog;
    private ProgressBar pb;
    private TextView percentage, message;
    String app_ver;
    private String visit_date, username;
    private SharedPreferences preferences;
    private GSKDatabase database;
    private int factor;
    String datacheck = "";
    String[] words;
    String validity;
    int mid, uploadImgCount = 0, totalImag = 0;
    Data data;
    private ArrayList<CoverageBean> coverageBeanlist = new ArrayList<CoverageBean>();
    private ArrayList<StoreSignAgeGetterSetter> SignAgeData = new ArrayList<StoreSignAgeGetterSetter>();
    private ArrayList<WindowSKUEntryGetterSetter> window1 = new ArrayList<WindowSKUEntryGetterSetter>();
    private ArrayList<ChecklistGetterSetter> check1 = new ArrayList<ChecklistGetterSetter>();
    private ArrayList<BrandGetterSetter> stockavail2List = new ArrayList<BrandGetterSetter>();
    private ArrayList<DisplayMasterGetterSetter> paidVisibilityList = new ArrayList<DisplayMasterGetterSetter>();
    private ArrayList<FreeVisibilityGetterSetter> freeVisibilityList = new ArrayList<FreeVisibilityGetterSetter>();
    private ArrayList<ShareShelfInsertHeaderDataGetterSetter> shareshelfheaderList = new ArrayList<ShareShelfInsertHeaderDataGetterSetter>();
    private ArrayList<POSMDATAGetterSetter> POSMdata = new ArrayList<POSMDATAGetterSetter>();
    private ArrayList<COMPETITORGetterSetter> COMPETITORDATA = new ArrayList<COMPETITORGetterSetter>();
    private ArrayList<StockNewGetterSetter> stockData = new ArrayList<StockNewGetterSetter>();
    ArrayList<MappingPromotion2GetterSetter> mappingPromotionList = new ArrayList<>();
    String Path;
    boolean up_success_flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        visit_date = preferences.getString(CommonString1.KEY_DATE, null);
        username = preferences.getString(CommonString1.KEY_USERNAME, null);
        app_ver = preferences.getString(CommonString1.KEY_VERSION, "");
        database = new GSKDatabase(this);
        database.open();
        Path = CommonString1.FILE_PATH;
        new UploadTask(this).execute();
    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub

        Intent i = new Intent(this, MainMenuActivity.class);
        startActivity(i);
        UploadDataActivityWithoutWait.this.finish();
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
                database.open();
                coverageBeanlist = database.getCoverageData(visit_date);
                if (coverageBeanlist.size() > 0) {

                    if (coverageBeanlist.size() == 1) {
                        factor = 50;
                    } else {

                        factor = 100 / (coverageBeanlist.size());
                    }
                }

                for (int i = 0; i < coverageBeanlist.size(); i++) {
                    if (!coverageBeanlist.get(i).getStatus().equalsIgnoreCase(CommonString1.KEY_U)
                            && !coverageBeanlist.get(i).getStatus().equalsIgnoreCase(CommonString1.KEY_D)) {

                        //region COVERAGE
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
                                + coverageBeanlist.get(i).getInTime()
                                + "[/IN_TIME][OUT_TIME]"
                                + coverageBeanlist.get(i).getOutTime()
                                + "[/OUT_TIME][UPLOAD_STATUS]"
                                + "N"
                                + "[/UPLOAD_STATUS][USER_ID]" + username
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
                            database.open();
                            database.updateCoverageStatus(coverageBeanlist
                                    .get(i).getMID(), CommonString1.KEY_P);
                            database.updateStoreStatusOnLeave(
                                    coverageBeanlist.get(i).getStoreId(),
                                    coverageBeanlist.get(i).getVisitDate(),
                                    CommonString1.KEY_P);
                        } else {
                            //return CommonString1.METHOD_UPLOAD_DR_STORE_COVERAGE;
                            continue;

                        }
                        mid = Integer.parseInt((words[1]));
                        data.value = 10;
                        data.name = "Uploading";
                        publishProgress(data);
                        //endregion


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


                        //------------------------------new----------------------
                        String final_xml = "";
                        String final_xml_checklist = "", onXML_checklist = "";

                        //region window1 DATA
                        onXML = "";
                        database.open();
                        window1 = database.getwindowdat(coverageBeanlist.get(i).getStoreId());
                        if (window1.size() > 0) {

                            for (int j = 0; j < window1.size(); j++) {

                                final_xml_checklist = "";
                                onXML_checklist = "";
                                database.open();
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

                            request = new SoapObject(CommonString1.NAMESPACE, CommonString1.METHOD_UPLOAD_XML);
                            request.addProperty("XMLDATA", sos_xml);
                            request.addProperty("KEYS", "WINDOW_AND_CHECKLIST_DATA_NEW");
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
                                // return "WINDOW_DATA";
                            }
                        }
                        data.value = 15;
                        data.name = "WINDOW_DATA";
                        publishProgress(data);
                        //endregion

                        //region stock present data
                        final_xml = "";
                        onXML = "";
                        database.open();
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

                            request = new SoapObject(CommonString1.NAMESPACE, CommonString1.METHOD_UPLOAD_XML);
                            request.addProperty("XMLDATA", sos_xml);
                            request.addProperty("KEYS", "STOCK_DATA_PRESENT_NEW");
                            request.addProperty("USERNAME", username);
                            request.addProperty("MID", mid);
                            envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                            envelope.dotNet = true;
                            envelope.setOutputSoapObject(request);
                            androidHttpTransport = new HttpTransportSE(CommonString1.URL);
                            androidHttpTransport.call(CommonString1.SOAP_ACTION + CommonString1.METHOD_UPLOAD_XML, envelope);

                            result = (Object) envelope.getResponse();

                            if (!result.toString().equalsIgnoreCase(CommonString1.KEY_SUCCESS)) {
                                // return "STOCK_DATA_PRESENT";
                            }
                        }
                        data.value = 20;
                        data.name = "STOCK_DATA_PRESENT";
                        publishProgress(data);
                        //endregion

                        //region uploading SIGNAGE_DATA
                        final_xml = "";
                        onXML = "";
                        database.open();
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

                            request = new SoapObject(CommonString1.NAMESPACE, CommonString1.METHOD_UPLOAD_XML);
                            request.addProperty("XMLDATA", sos_xml);
                            request.addProperty("KEYS", "SIGNAGE_DATA");
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
                                //return "SIGNAGE_DATA";
                            }

                        }
                        data.value = 30;
                        data.name = "SIGNAGE_DATA";
                        publishProgress(data);
                        //endregion

                        //region uploading POSM_DATA data
                        final_xml = "";
                        onXML = "";
                        database.open();
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

                            request = new SoapObject(CommonString1.NAMESPACE, CommonString1.METHOD_UPLOAD_XML);
                            request.addProperty("XMLDATA", sos_xml);
                            request.addProperty("KEYS", "POSM_DATA_NEW");
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
                                //return "POSM_DATA";
                            }
                        }
                        data.value = 45;
                        data.name = "POSM_DATA";
                        publishProgress(data);
                        //endregion

                        //region COMPETITOR DATA
                        final_xml = "";
                        onXML = "";
                        database.open();
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

                            final String sos_xml = "[DATA]" + final_xml + "[/DATA]";

                            request = new SoapObject(CommonString1.NAMESPACE, CommonString1.METHOD_UPLOAD_XML);
                            request.addProperty("XMLDATA", sos_xml);
                            request.addProperty("KEYS", "COMPETITOR_DATA");
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
                                // return "COMPETITOR_DATA";
                            }
                        }
                        data.value = 50;
                        data.name = "COMPETITOR_DATA";
                        publishProgress(data);
                        //endregion

                        //region Share shelf Data
                        final_xml = "";
                        onXML = "";
                        database.open();
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

                            request = new SoapObject(CommonString1.NAMESPACE, CommonString1.METHOD_UPLOAD_XML);
                            request.addProperty("XMLDATA", sos_xml);
                            request.addProperty("KEYS", "MT_SHARESHELF_DATA");
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
                                // return "SHARESHELF_DATA";
                            }

                        }
                        data.value = 80;
                        data.name = "SHARESHELF_DATA";
                        publishProgress(data);
                        //endregion

                        //region STOCK AVAILABILITY FOR CHANNEL 2 Data
                        final_xml = "";
                        onXML = "";
                        database.open();
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

                            final String sos_xml = "[DATA]" + final_xml + "[/DATA]";
                            request = new SoapObject(CommonString1.NAMESPACE, CommonString1.METHOD_UPLOAD_XML);
                            request.addProperty("XMLDATA", sos_xml);
                            request.addProperty("KEYS", "MT_STOCKAVAILABILITYFORCHANNEL2_DATA");
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
                                // return "STOCKAVAILABILITYFORCHANNEL2_DATA";
                            }
                        }
                        data.value = 80;
                        data.name = "STOCKAVAILABILITYFORCHANNEL2_DATA";
                        publishProgress(data);
                        //endregion

                        //region PAID_VISIBILITY Data
                        final_xml = "";
                        onXML = "";
                        database.open();
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
                            request = new SoapObject(CommonString1.NAMESPACE, CommonString1.METHOD_UPLOAD_XML);
                            request.addProperty("XMLDATA", sos_xml);
                            request.addProperty("KEYS", "MT_PAID_VISIBILITY_DATA");
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
                                // return "PAID_VISIBILITY_DATA";
                            }
                        }
                        data.value = 80;
                        data.name = "PAID_VISIBILITY_DATA";
                        publishProgress(data);
                        //endregion

                        //region FREE_VISIBILITY Data
                        final_xml = "";
                        onXML = "";
                        database.open();
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

                            final String sos_xml = "[DATA]" + final_xml + "[/DATA]";
                            request = new SoapObject(CommonString1.NAMESPACE, CommonString1.METHOD_UPLOAD_XML);
                            request.addProperty("XMLDATA", sos_xml);
                            request.addProperty("KEYS", "MT_FREE_VISIBILITY_DATA_NEW");
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
                                //return "FREE_VISIBILITY_DATA";
                            }
                        }
                        data.value = 80;
                        data.name = "FREE_VISIBILITY_DATA";
                        publishProgress(data);
                        //endregion

                        //region Promotion Data
                        final_xml = "";
                        onXML = "";
                        database.open();
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

                            final String sos_xml = "[DATA]" + final_xml + "[/DATA]";
                            request = new SoapObject(CommonString1.NAMESPACE, CommonString1.METHOD_UPLOAD_XML);
                            request.addProperty("XMLDATA", sos_xml);
                            request.addProperty("KEYS", "MT_PROMOTION_DATA");
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
                                // return "MT_PROMOTION_DATA";
                            }
                        }
                        data.value = 85;
                        data.name = "MT_PROMOTION_DATA";
                        publishProgress(data);
                        //endregion

                        //region COVERAGE STATUS D
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
                        final String sos_xml = "[DATA]" + final_xml + "[/DATA]";

                        SoapObject request1 = new SoapObject(CommonString1.NAMESPACE, CommonString1.MEHTOD_UPLOAD_COVERAGE_STATUS);
                        request1.addProperty("onXML", sos_xml);
                        SoapSerializationEnvelope envelope1 = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                        envelope1.dotNet = true;
                        envelope1.setOutputSoapObject(request1);
                        HttpTransportSE androidHttpTransport1 = new HttpTransportSE(CommonString1.URL);
                        androidHttpTransport1.call(CommonString1.SOAP_ACTION + CommonString1.MEHTOD_UPLOAD_COVERAGE_STATUS, envelope1);

                        Object result1 = (Object) envelope1.getResponse();
                        if (result1.toString().equalsIgnoreCase(
                                CommonString1.KEY_SUCCESS)) {
                            database.open();
                            database.updateCoverageStatus(coverageBeanlist.get(i).getMID(), CommonString1.KEY_D);
                            database.updateStoreStatusOnLeave(coverageBeanlist.get(i).getStoreId(), coverageBeanlist.get(i)
                                    .getVisitDate(), CommonString1.KEY_D);
                        }
                        // SET COVERAGE STATUS
                        //endregion
                    }
                }

                File f = new File(CommonString1.FILE_PATH);
                File[] files = f.listFiles();
                totalImag = files.length;
                String final_xml = "";
                if (files.length > 0) {
                    for (int i = 0; i < files.length; i++) {
                        String onXML = "";
                        onXML = "[IMAGE]"
                                + files[i].getName()
                                + "[/IMAGE]";
                        final_xml = final_xml + onXML;
                    }
                    final String sos_xml = "[DATA]" + final_xml + "[/DATA]";
                    SoapObject request = new SoapObject(CommonString1.NAMESPACE, CommonString1.METHOD_UPLOAD_XML);
                    request.addProperty("XMLDATA", sos_xml);
                    request.addProperty("KEYS", "IMAGEDATA_BEFORE_IMG_UPLOAD");
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
                } else {
                    database.open();
                    ArrayList<CoverageBean> coverageBeanlist = database.getCoverageData(visit_date);
                    loop1:
                    for (int i = 0; i < coverageBeanlist.size(); i++) {
                        if (coverageBeanlist.get(i).getStatus().equalsIgnoreCase(CommonString1.KEY_D)) {
                            final_xml = "";
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
                            final String sos_xml2 = "[DATA]" + final_xml + "[/DATA]";
                            SoapObject request1 = new SoapObject(CommonString1.NAMESPACE, CommonString1.MEHTOD_UPLOAD_COVERAGE_STATUS);
                            request1.addProperty("onXML", sos_xml2);
                            SoapSerializationEnvelope envelope1 = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                            envelope1.dotNet = true;
                            envelope1.setOutputSoapObject(request1);
                            HttpTransportSE androidHttpTransport1 = new HttpTransportSE(CommonString1.URL, 10000);
                            androidHttpTransport1.call(CommonString1.SOAP_ACTION + CommonString1.MEHTOD_UPLOAD_COVERAGE_STATUS, envelope1);

                            Object result = (Object) envelope1.getResponse();
                            if (result.toString().equalsIgnoreCase(
                                    CommonString1.KEY_SUCCESS)) {
                                database.open();
                                database.updateCoverageStatus(coverageBeanlist.get(i).getMID(), CommonString1.KEY_U);
                                database.updateStoreStatusOnLeave(coverageBeanlist.get(i).getStoreId(), coverageBeanlist.get(i).getVisitDate(), CommonString1.KEY_U);
                                database.deleteSpecificStoreData(coverageBeanlist.get(i).getStoreId());
                            }

                            if (result.toString().equalsIgnoreCase(CommonString1.KEY_SUCCESS)) {
                                up_success_flag = true;
                            } else {
                                up_success_flag = false;
                                break loop1;
                            }
                        }
                    }

                }


            } catch (MalformedURLException e) {

                up_success_flag = false;

                final AlertMessage message = new AlertMessage(
                        UploadDataActivityWithoutWait.this,
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
                        UploadDataActivityWithoutWait.this,
                        AlertMessage.MESSAGE_SOCKETEXCEPTION, "socket_upload", e);

                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        message.showMessage();

                    }
                });
            } catch (Exception e) {

                up_success_flag = false;

                final AlertMessage message = new AlertMessage(
                        UploadDataActivityWithoutWait.this,
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
            if (up_success_flag == true) {
                return CommonString1.KEY_SUCCESS;
            } else {
                return CommonString1.KEY_FAILURE;
            }

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
                //region Upload image with retrofit 23-05-2017
                //region Upload image with retrofit 21-03-2018
                ArrayList<String> fileNameList;
                if (totalImag > 0) {
                    AlertandMessages.showToastMsg(context, AlertMessage.MESSAGE_UPLOAD_DATA);
                    String[] files = new File(CommonString1.FILE_PATH).list();
                    fileNameList = new ArrayList<>();
                    fileNameList.clear();
                    for (String fileName : files) {
                        fileNameList.add(fileName);
                    }
                    UploadImageWithRetrofit_WithoutWait uploadRetro = new UploadImageWithRetrofit_WithoutWait(context, fileNameList);
                    uploadRetro.UploadImagesRecursive();
                } else {
                    AlertMessage message = new AlertMessage(
                            UploadDataActivityWithoutWait.this, AlertMessage.MESSAGE_UPLOAD_DATA, "success", null);
                    message.showMessage();
                }
                //endregion
            } else if (!result.contains(CommonString1.KEY_FAILURE)) {
                AlertMessage message = new AlertMessage(
                        UploadDataActivityWithoutWait.this, "Error in uploading :" + result, "success", null);
                message.showMessage();
            }

        }
    }

    class Data {
        int value;
        String name;
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

}

package com.cpm.download;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cpm.Constants.CommonString1;
import com.cpm.GetterSetter.CityMasterGetterSetter;
import com.cpm.GetterSetter.DisplayMasterGetterSetter;
import com.cpm.GetterSetter.EmpSalaryGetterSetter;
import com.cpm.GetterSetter.JourneyPlanPreviousGetterSetter;
import com.cpm.GetterSetter.MappingPaidVisibilityGetterSetter;
import com.cpm.GetterSetter.MappingPosmGetterSetter;
import com.cpm.GetterSetter.MappingPromotion2GetterSetter;
import com.cpm.GetterSetter.NonPOSMReasonGetterSetter;
import com.cpm.GetterSetter.NonPromotionReasonGetterSetter;
import com.cpm.GetterSetter.StateMasterGetterSetter;
import com.cpm.capitalfoods.R;
import com.cpm.database.GSKDatabase;
import com.cpm.delegates.TableBean;
import com.cpm.fragment.MainFragment;
import com.cpm.message.AlertMessage;
import com.cpm.xmlGetterSetter.AssetMasterGetterSetter;
import com.cpm.xmlGetterSetter.BrandGetterSetter;
import com.cpm.xmlGetterSetter.COMPANY_MASTERGetterSetter;
import com.cpm.xmlGetterSetter.CategoryMasterGetterSetter;
import com.cpm.xmlGetterSetter.JourneyPlanGetterSetter;
import com.cpm.xmlGetterSetter.MappingAssetGetterSetter;
import com.cpm.xmlGetterSetter.MappingAvailabilityGetterSetter;
import com.cpm.xmlGetterSetter.MappingStatusWindows;
import com.cpm.xmlGetterSetter.MappingStockGetterSetter;
import com.cpm.xmlGetterSetter.MappingWindowChecklistGetterSetter;
import com.cpm.xmlGetterSetter.NonWorkingReasonGetterSetter;
import com.cpm.xmlGetterSetter.NonWorkingSubReasonGetterSetter;
import com.cpm.xmlGetterSetter.POSMDATAGetterSetter;
import com.cpm.xmlGetterSetter.SkuMasterGetterSetter;
import com.cpm.xmlGetterSetter.SubCategoryMasterGetterSetter;
import com.cpm.xmlGetterSetter.WindowChecklistAnswerGetterSetter;
import com.cpm.xmlGetterSetter.WindowNonReasonGetterSetter;
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

public class CompleteDownloadActivity extends AppCompatActivity {
    private Dialog dialog;
    private ProgressBar pb;
    private TextView percentage, message;
    private Data data;
    int eventType;

    JourneyPlanPreviousGetterSetter journeyPlanPreviousGetterSetter;
    JourneyPlanGetterSetter jcpgettersetter;
    SkuMasterGetterSetter skumastergettersetter;
    MappingAvailabilityGetterSetter mappingavailgettersetter;
    MappingPaidVisibilityGetterSetter mappingPaidVisibilityGetterSetter;
    WindowChecklistAnswerGetterSetter windowChecklistAnswerGetterSetter;
    MappingStatusWindows mapplingstatuswindows;
    POSMDATAGetterSetter posmgettersetter;
    EmpSalaryGetterSetter empSalaryGetterSetter;
    MappingAssetGetterSetter mappingassetgettersetter;
    AssetMasterGetterSetter assetmastergettersetter;
    COMPANY_MASTERGetterSetter Companygettersetter;
    StateMasterGetterSetter stateMasterGetterSetter;
    CityMasterGetterSetter cityMasterGetterSetter;
    MappingWindowChecklistGetterSetter mappingWindowChecklistGetterSetter;
    BrandGetterSetter brandGetterSetter;
    DisplayMasterGetterSetter displayMasterGetterSetter;
    NonPromotionReasonGetterSetter nonPromotionReasonGetterSetter;
    NonPOSMReasonGetterSetter nonPOSMReasonGetterSetter;
    MappingPromotion2GetterSetter mappingPromotion2GetterSetter;
    SubCategoryMasterGetterSetter subCategoryMasterGetterSetter;
    MappingStockGetterSetter mappingStockGetterSetter;
    NonWorkingReasonGetterSetter nonworkinggettersetter;
    NonWorkingSubReasonGetterSetter nonworkingSubgettersetter;
    WindowNonReasonGetterSetter windowNonReasonGetterSetter;
    CategoryMasterGetterSetter categorygettersetter;
    MappingPosmGetterSetter mappingPosmGetterSetter;
    GSKDatabase db;
    TableBean tb;
    String _UserId;
    private SharedPreferences preferences;
    boolean asset_flag = true;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.mainpage);
        setContentView(R.layout.activity_main_menu);
        context = this;
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        _UserId = preferences.getString(CommonString1.KEY_USERNAME, "");
        tb = new TableBean();
        db = new GSKDatabase(context);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        new BackgroundTask(this).execute();
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        FragmentManager fragmentManager = getSupportFragmentManager();
        MainFragment cartfrag = new MainFragment();
        fragmentManager.beginTransaction().replace(R.id.frame_layout, cartfrag).commit();
    }

    class Data {
        int value;
        String name;
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
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.custom);
            //dialog.setTitle("Download Files");
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
            pb = (ProgressBar) dialog.findViewById(R.id.progressBar1);
            percentage = (TextView) dialog.findViewById(R.id.percentage);
            message = (TextView) dialog.findViewById(R.id.message);
        }

        @Override
        protected String doInBackground(Void... params) {
            // TODO Auto-generated method stub
            String resultHttp = "";
            try {

                data = new Data();

                //region JCP Previous Data
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(true);
                XmlPullParser xpp = factory.newPullParser();
                SoapSerializationEnvelope envelope;
                HttpTransportSE androidHttpTransport;
                SoapObject request;
                // Brand Master data
                request = new SoapObject(CommonString1.NAMESPACE, CommonString1.METHOD_NAME_UNIVERSAL_DOWNLOAD);
                request.addProperty("UserName", _UserId);
                request.addProperty("Type", "JOURNEY_PLAN_PREV");
                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);
                androidHttpTransport = new HttpTransportSE(CommonString1.URL);
                androidHttpTransport.call(CommonString1.SOAP_ACTION_UNIVERSAL, envelope);

                Object resultPrevdata = (Object) envelope.getResponse();
                if (resultPrevdata.toString() != null) {
                    xpp.setInput(new StringReader(resultPrevdata.toString()));
                    xpp.next();
                    eventType = xpp.getEventType();
                    journeyPlanPreviousGetterSetter = XMLHandlers.JCPPreviousXML(xpp, eventType);
                    String jcpPrevtable = journeyPlanPreviousGetterSetter.getTable_JOURNEY_PLAN_PREV();
                    TableBean.setJCPPrevioustable(jcpPrevtable);
                    if (journeyPlanPreviousGetterSetter.getSTORE_CD().size() > 0) {
                        resultHttp = CommonString1.KEY_SUCCESS;
                    }
                    data.value = 10;
                    data.name = "JCP Previous Data Downloading";
                    publishProgress(data);
                }
                //endregion

                //region JCP Data
                request = new SoapObject(CommonString1.NAMESPACE, CommonString1.METHOD_NAME_UNIVERSAL_DOWNLOAD);
                request.addProperty("UserName", _UserId);
                request.addProperty("Type", "JOURNEY_PLAN");
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);
                androidHttpTransport = new HttpTransportSE(CommonString1.URL);
                androidHttpTransport.call(CommonString1.SOAP_ACTION_UNIVERSAL, envelope);

                Object result = (Object) envelope.getResponse();
                if (result.toString() != null) {
                    xpp.setInput(new StringReader(result.toString()));
                    xpp.next();
                    eventType = xpp.getEventType();
                    jcpgettersetter = XMLHandlers.JCPXMLHandler(xpp, eventType);
                    String jcpTable = jcpgettersetter.getTable_journey_plan();
                    TableBean.setjcptable(jcpTable);
                    if (jcpgettersetter.getStore_cd().size() > 0) {
                        resultHttp = CommonString1.KEY_SUCCESS;
                    } else {
                        return "JOURNEY_PLAN";
                    }
                    data.value = 20;
                    data.name = "JCP Data Downloading";
                    publishProgress(data);
                }
                //endregion

                //region SKU_MASTER
                request = new SoapObject(CommonString1.NAMESPACE,
                        CommonString1.METHOD_NAME_UNIVERSAL_DOWNLOAD);
                request.addProperty("UserName", _UserId);
                request.addProperty("Type", "SKU_MASTER");
                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);
                androidHttpTransport = new HttpTransportSE(CommonString1.URL);
                androidHttpTransport.call(
                        CommonString1.SOAP_ACTION_UNIVERSAL, envelope);
                result = (Object) envelope.getResponse();

                if (result.toString() != null) {
                    xpp.setInput(new StringReader(result.toString()));
                    xpp.next();
                    eventType = xpp.getEventType();
                    skumastergettersetter = XMLHandlers.storeListXML(xpp, eventType);
                    if (skumastergettersetter.getSku_cd().size() > 0) {
                        String skutable = skumastergettersetter.getSku_master_table();
                        if (skutable != null) {
                            resultHttp = CommonString1.KEY_SUCCESS;
                            TableBean.setSkumastertable(skutable);
                        }
                    } else {
                        return "SKU_MASTER";
                    }

                    data.value = 20;
                    data.name = "SKU_MASTER Data Download";
                    publishProgress(data);
                }
                //endregion

                //region BRAND_MASTER
                request = new SoapObject(CommonString1.NAMESPACE, CommonString1.METHOD_NAME_UNIVERSAL_DOWNLOAD);
                request.addProperty("UserName", _UserId);
                request.addProperty("Type", "BRAND_MASTER");
                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);
                androidHttpTransport = new HttpTransportSE(CommonString1.URL);
                androidHttpTransport.call(CommonString1.SOAP_ACTION_UNIVERSAL, envelope);

                Object resultbrand = (Object) envelope.getResponse();
                if (resultbrand.toString() != null) {
                    xpp.setInput(new StringReader(resultbrand.toString()));
                    xpp.next();
                    eventType = xpp.getEventType();
                    brandGetterSetter = XMLHandlers.brandMasterXML(xpp, eventType);
                    if (brandGetterSetter.getBrand_cd().size() > 0) {
                        resultHttp = CommonString1.KEY_SUCCESS;
                        String brandtable = brandGetterSetter.getBrand_master_table();
                        TableBean.setBrandtable(brandtable);
                    } else {
                        return "BRAND_MASTER";
                    }
                    data.value = 30;
                    data.name = "Brand Master Data Downloading";
                    publishProgress(data);
                }
                //endregion

                //region DISPLAY_MASTER
                request = new SoapObject(CommonString1.NAMESPACE, CommonString1.METHOD_NAME_UNIVERSAL_DOWNLOAD);
                request.addProperty("UserName", _UserId);
                request.addProperty("Type", "DISPLAY_MASTER");
                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);
                androidHttpTransport = new HttpTransportSE(CommonString1.URL);
                androidHttpTransport.call(CommonString1.SOAP_ACTION_UNIVERSAL, envelope);

                Object resultdisplayMaster = (Object) envelope.getResponse();
                if (resultdisplayMaster.toString() != null) {
                    xpp.setInput(new StringReader(resultdisplayMaster.toString()));
                    xpp.next();
                    eventType = xpp.getEventType();
                    displayMasterGetterSetter = XMLHandlers.displayMasterXML(xpp, eventType);
                    if (displayMasterGetterSetter.getDISPLAY_CD().size() > 0) {
                        resultHttp = CommonString1.KEY_SUCCESS;
                        String brandtable = displayMasterGetterSetter.getTable_DisplayMaster();
                        TableBean.setDisplayMasterTable(brandtable);
                    } else {
                        return "DISPLAY_MASTER";
                    }
                    data.value = 30;
                    data.name = "DISPLAY_MASTER Data Downloading";
                    publishProgress(data);
                }
                //endregion

                //region MAPPING_PROMOTION
                request = new SoapObject(CommonString1.NAMESPACE, CommonString1.METHOD_NAME_UNIVERSAL_DOWNLOAD);
                request.addProperty("UserName", _UserId);
                request.addProperty("Type", "MAPPING_PROMOTION");
                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);
                androidHttpTransport = new HttpTransportSE(CommonString1.URL);
                androidHttpTransport.call(CommonString1.SOAP_ACTION_UNIVERSAL, envelope);

                Object resultMappingPromotion = (Object) envelope.getResponse();
                if (resultMappingPromotion.toString() != null) {
                    xpp.setInput(new StringReader(resultMappingPromotion.toString()));
                    xpp.next();
                    eventType = xpp.getEventType();
                    mappingPromotion2GetterSetter = XMLHandlers.mappingPromotionXML(xpp, eventType);
                    String brandtable = mappingPromotion2GetterSetter.getTable_MAPPING_PROMOTION();
                    TableBean.setPromotiontable(brandtable);
                    if (mappingPromotion2GetterSetter.getID().size() > 0) {
                        resultHttp = CommonString1.KEY_SUCCESS;
                    } else {
                        // return "MAPPING_PROMOTION";
                    }
                    data.value = 40;
                    data.name = "MAPPING_PROMOTION Data Downloading";
                    publishProgress(data);
                }
                //endregion

                //region NON_PROMOTION_REASON
                request = new SoapObject(CommonString1.NAMESPACE, CommonString1.METHOD_NAME_UNIVERSAL_DOWNLOAD);
                request.addProperty("UserName", _UserId);
                request.addProperty("Type", "NON_PROMOTION_REASON");
                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);
                androidHttpTransport = new HttpTransportSE(CommonString1.URL);
                androidHttpTransport.call(CommonString1.SOAP_ACTION_UNIVERSAL, envelope);

                Object resultNONpromotion = (Object) envelope.getResponse();
                if (resultNONpromotion.toString() != null) {
                    xpp.setInput(new StringReader(resultNONpromotion.toString()));
                    xpp.next();
                    eventType = xpp.getEventType();
                    nonPromotionReasonGetterSetter = XMLHandlers.nonPromotionReasonXML(xpp, eventType);
                    if (nonPromotionReasonGetterSetter.getPREASON_CD().size() > 0) {
                        resultHttp = CommonString1.KEY_SUCCESS;
                        String brandtable = nonPromotionReasonGetterSetter.getTable_NonPromotionReason();
                        TableBean.setNonPromotionReasonTable(brandtable);
                    } else {
                        return "NON_PROMOTION_REASON";
                    }
                    data.value = 50;
                    data.name = "NON_PROMOTION_REASON Data Downloading";
                    publishProgress(data);
                }
                //endregion

                //region NON_POSM_REASON
                request = new SoapObject(CommonString1.NAMESPACE, CommonString1.METHOD_NAME_UNIVERSAL_DOWNLOAD);
                request.addProperty("UserName", _UserId);
                request.addProperty("Type", "NON_POSM_REASON");
                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);
                androidHttpTransport = new HttpTransportSE(CommonString1.URL);
                androidHttpTransport.call(CommonString1.SOAP_ACTION_UNIVERSAL, envelope);

                Object resultNONPosmReason = (Object) envelope.getResponse();
                if (resultNONPosmReason.toString() != null) {
                    xpp.setInput(new StringReader(resultNONPosmReason.toString()));
                    xpp.next();
                    eventType = xpp.getEventType();
                    nonPOSMReasonGetterSetter = XMLHandlers.nonPosmReasonXML(xpp, eventType);
                    if (nonPOSMReasonGetterSetter.getPREASON_CD().size() > 0) {
                        resultHttp = CommonString1.KEY_SUCCESS;
                        String brandtable = nonPOSMReasonGetterSetter.getTable_NonPosmReason();
                        TableBean.setNonPosmReasonTable(brandtable);
                    } else {
                        return "NON_POSM_REASON";
                    }
                    data.value = 60;
                    data.name = "NON_POSM_REASON Data Downloading";
                    publishProgress(data);
                }
                //endregion

                //region MAPPING_AVAILABILITY
                request = new SoapObject(CommonString1.NAMESPACE, CommonString1.METHOD_NAME_UNIVERSAL_DOWNLOAD);
                request.addProperty("UserName", _UserId);
                request.addProperty("Type", "MAPPING_AVAILABILITY");
                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);
                androidHttpTransport = new HttpTransportSE(CommonString1.URL);
                androidHttpTransport.call(CommonString1.SOAP_ACTION_UNIVERSAL, envelope);

                Object resultwork = (Object) envelope.getResponse();
                if (resultwork.toString() != null) {
                    xpp.setInput(new StringReader(resultwork.toString()));
                    xpp.next();
                    eventType = xpp.getEventType();
                    mappingavailgettersetter = XMLHandlers.mappingAvailabiltylXML(xpp, eventType);
                    if (mappingavailgettersetter.getMapping_availability_table() != null) {
                        String mappingtable = mappingavailgettersetter.getMapping_availability_table();
                        TableBean.setMappingavailablitytable(mappingtable);
                    }
                    if (mappingavailgettersetter.getSTATE_CD().size() > 0) {
                        resultHttp = CommonString1.KEY_SUCCESS;
                    } else {
                        //return "MAPPING_CORE_SKU";
                    }
                    data.value = 65;
                    data.name = "MAPPING_AVAILABILITY Downloading";
                    publishProgress(data);
                }
                //endregion

                //region MAPPING_PAID_VISIBILITY
                request = new SoapObject(CommonString1.NAMESPACE, CommonString1.METHOD_NAME_UNIVERSAL_DOWNLOAD);
                request.addProperty("UserName", _UserId);
                request.addProperty("Type", "MAPPING_PAID_VISIBILITY");
                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);
                androidHttpTransport = new HttpTransportSE(CommonString1.URL);
                androidHttpTransport.call(CommonString1.SOAP_ACTION_UNIVERSAL, envelope);

                Object resultpaidwork = (Object) envelope.getResponse();
                if (resultpaidwork.toString() != null) {
                    xpp.setInput(new StringReader(resultpaidwork.toString()));
                    xpp.next();
                    eventType = xpp.getEventType();
                    mappingPaidVisibilityGetterSetter = XMLHandlers.mappingPaidVisibilityXML(xpp, eventType);
                    if (mappingPaidVisibilityGetterSetter.getTable_MappingPaidVisibility() != null) {
                        String mappingtable = mappingPaidVisibilityGetterSetter.getTable_MappingPaidVisibility();
                        TableBean.setMappingPaidVisibilitytable(mappingtable);
                    }
                    if (mappingPaidVisibilityGetterSetter.getSTORE_CD().size() > 0) {
                        resultHttp = CommonString1.KEY_SUCCESS;


                    } else {
                        // return "MAPPING_PAID_VISIBILITY";
                    }
                    data.value = 66;
                    data.name = "MAPPING_PAID_VISIBILITY Downloading";
                    publishProgress(data);
                }
                //endregion

                //region POSM_MASTER
                request = new SoapObject(CommonString1.NAMESPACE, CommonString1.METHOD_NAME_UNIVERSAL_DOWNLOAD);
                request.addProperty("UserName", _UserId);
                request.addProperty("Type", "POSM_MASTER");
                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);
                androidHttpTransport = new HttpTransportSE(CommonString1.URL);
                androidHttpTransport.call(CommonString1.SOAP_ACTION_UNIVERSAL, envelope);

                Object resultmapping = (Object) envelope.getResponse();
                if (resultmapping.toString() != null) {
                    xpp.setInput(new StringReader(resultmapping.toString()));
                    xpp.next();
                    eventType = xpp.getEventType();
                    posmgettersetter = XMLHandlers.mappingpromotXML(xpp, eventType);
                    if (posmgettersetter.getMapping_POSM_table() != null) {
                        String mappingtable = posmgettersetter.getMapping_POSM_table();
                        TableBean.setMappingposmtable(mappingtable);
                    }
                    if (posmgettersetter.getPOSM_CD().size() > 0) {
                        resultHttp = CommonString1.KEY_SUCCESS;
                    } else {
                        return "POSM_MASTER";
                    }
                    data.value = 70;
                    data.name = "POSM_MASTER Data Downloading";
                    publishProgress(data);
                }
                //endregion

                //region MAPPING_POSM
               /* request = new SoapObject(CommonString1.NAMESPACE, CommonString1.METHOD_NAME_UNIVERSAL_DOWNLOAD);
                request.addProperty("UserName", _UserId);
                request.addProperty("Type", "MAPPING_POSM");
                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);
                androidHttpTransport = new HttpTransportSE(CommonString1.URL);
                androidHttpTransport.call(CommonString1.SOAP_ACTION_UNIVERSAL, envelope);

                Object resultMappingPosm = (Object) envelope.getResponse();
                if (resultMappingPosm.toString() != null) {
                    xpp.setInput(new StringReader(resultMappingPosm.toString()));
                    xpp.next();
                    eventType = xpp.getEventType();
                    mappingPosmGetterSetter = XMLHandlers.mappingPOSMXML(xpp, eventType);
                    if (mappingPosmGetterSetter.getTable_MappingPosm() != null) {
                        String mappingtable = mappingPosmGetterSetter.getTable_MappingPosm();
                        TableBean.setMappingPosmtable(mappingtable);
                    }
                    if (mappingPosmGetterSetter.getSTATE_CD().size() > 0) {
                        resultHttp = CommonString1.KEY_SUCCESS;
                    } else {
                        // return "MAPPING_POSM";
                    }
                    data.value = 55;
                    data.name = "MAPPING_POSM Data Downloading";
                    publishProgress(data);
                }*/
                //endregion

                //region MAPPING_POSM_STOREWISE
                request = new SoapObject(CommonString1.NAMESPACE, CommonString1.METHOD_NAME_UNIVERSAL_DOWNLOAD);
                request.addProperty("UserName", _UserId);
                request.addProperty("Type", "MAPPING_POSM_STOREWISE");
                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);
                androidHttpTransport = new HttpTransportSE(CommonString1.URL);
                androidHttpTransport.call(CommonString1.SOAP_ACTION_UNIVERSAL, envelope);

                Object resultMappingPosm = (Object) envelope.getResponse();
                if (resultMappingPosm.toString() != null) {
                    xpp.setInput(new StringReader(resultMappingPosm.toString()));
                    xpp.next();
                    eventType = xpp.getEventType();
                    mappingPosmGetterSetter = XMLHandlers.mappingPOSMXML(xpp, eventType);
                    if (mappingPosmGetterSetter.getTable_MappingPosm() != null) {
                        String mappingtable = mappingPosmGetterSetter.getTable_MappingPosm();
                        TableBean.setMappingPosmtable(mappingtable);
                    }
                    if (mappingPosmGetterSetter.getSTORE_CD().size() > 0) {
                        resultHttp = CommonString1.KEY_SUCCESS;
                    } else {
                        // return "MAPPING_POSM";
                    }
                    data.value = 72;
                    data.name = "MAPPING_POSM_STOREWISE Data Downloading";
                    publishProgress(data);
                }
                //endregion

                //region EMP_SALARY
                request = new SoapObject(CommonString1.NAMESPACE, CommonString1.METHOD_NAME_UNIVERSAL_DOWNLOAD);
                request.addProperty("UserName", _UserId);
                request.addProperty("Type", "EMP_SALARY");
                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);
                androidHttpTransport = new HttpTransportSE(CommonString1.URL);
                androidHttpTransport.call(CommonString1.SOAP_ACTION_UNIVERSAL, envelope);

                Object resultEmpSalary = (Object) envelope.getResponse();
                if (resultEmpSalary.toString() != null) {
                    xpp.setInput(new StringReader(resultEmpSalary.toString()));
                    xpp.next();
                    eventType = xpp.getEventType();
                    empSalaryGetterSetter = XMLHandlers.empSalaryXML(xpp, eventType);
                    if (empSalaryGetterSetter.getTable_EMP_SALARY() != null) {
                        String mappingtable = empSalaryGetterSetter.getTable_EMP_SALARY();
                        TableBean.setEmpSalarytable(mappingtable);
                    }
                    if (empSalaryGetterSetter.getECODE() != null) {
                        resultHttp = CommonString1.KEY_SUCCESS;
                    } else {
                        //   return "EMP_SALARY";
                    }
                    data.value = 74;
                    data.name = "EMP_SALARY Data Downloading";
                    publishProgress(data);
                }
                //endregion

                //region WINDOW_MASTER
                request = new SoapObject(CommonString1.NAMESPACE, CommonString1.METHOD_NAME_UNIVERSAL_DOWNLOAD);
                request.addProperty("UserName", _UserId);
                request.addProperty("Type", "WINDOW_MASTER");
                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);
                androidHttpTransport = new HttpTransportSE(CommonString1.URL);
                androidHttpTransport.call(CommonString1.SOAP_ACTION_UNIVERSAL, envelope);

                Object resultnonWorking = (Object) envelope.getResponse();
                if (resultnonWorking.toString() != null) {
                    xpp.setInput(new StringReader(resultnonWorking.toString()));
                    xpp.next();
                    eventType = xpp.getEventType();
                    mapplingstatuswindows = XMLHandlers.mappingavailXML(xpp, eventType);
                    if (mapplingstatuswindows.getMapping_status_windows() != null) {
                        String mappingtable = mapplingstatuswindows.getMapping_status_windows();
                        TableBean.setMappingavailtable(mappingtable);
                    }
                    if (mapplingstatuswindows.getWINDOW_CD().size() > 0) {
                        resultHttp = CommonString1.KEY_SUCCESS;

                    } else {
                        return "WINDOW_MASTER";
                    }
                    data.value = 76;
                    data.name = "WINDOW_MASTER Downloading";
                    publishProgress(data);
                }
                //endregion

                //region WINDOW_CHECKLIST
                request = new SoapObject(CommonString1.NAMESPACE, CommonString1.METHOD_NAME_UNIVERSAL_DOWNLOAD);
                request.addProperty("UserName", _UserId);
                request.addProperty("Type", "WINDOW_CHECKLIST");
                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);
                androidHttpTransport = new HttpTransportSE(CommonString1.URL);
                androidHttpTransport.call(CommonString1.SOAP_ACTION_UNIVERSAL, envelope);

                Object resultmappingasst = (Object) envelope.getResponse();
                if (resultmappingasst.toString() != null) {
                    xpp.setInput(new StringReader(resultmappingasst.toString()));
                    xpp.next();
                    eventType = xpp.getEventType();
                    mappingassetgettersetter = XMLHandlers.mappingassetXML(xpp, eventType);
                    if (mappingassetgettersetter.getMapping_asset_table() != null) {
                        String mappingtable = mappingassetgettersetter.getMapping_asset_table();
                        TableBean.setMappingassettable(mappingtable);
                    }
                    if (mappingassetgettersetter.getCHECKLIST_CD().size() > 0) {
                        resultHttp = CommonString1.KEY_SUCCESS;
                    } else {
                        //return "MAPPING_ASSET";
                        asset_flag = false;
                    }
                    data.value = 80;
                    data.name = "WINDOW_CHECKLIST Data Downloading";
                    publishProgress(data);
                }
                //endregion

                //region WINDOW_MAPPING
                request = new SoapObject(CommonString1.NAMESPACE, CommonString1.METHOD_NAME_UNIVERSAL_DOWNLOAD);
                request.addProperty("UserName", _UserId);
                request.addProperty("Type", "WINDOW_MAPPING");
                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);
                androidHttpTransport = new HttpTransportSE(CommonString1.URL);
                androidHttpTransport.call(CommonString1.SOAP_ACTION_UNIVERSAL, envelope);

                Object resultasset = (Object) envelope.getResponse();
                if (resultasset.toString() != null) {
                    xpp.setInput(new StringReader(resultasset.toString()));
                    xpp.next();
                    eventType = xpp.getEventType();
                    assetmastergettersetter = XMLHandlers.assetMasterXML(xpp, eventType);
                    String assettable = assetmastergettersetter.getAsset_master_table();
                    TableBean.setAssetmastertable(assettable);
                    if (assetmastergettersetter.getSTATE_CD().size() > 0) {
                        resultHttp = CommonString1.KEY_SUCCESS;
                    } else {
                        // return "WINDOW_MAPPING";
                    }
                    data.value = 82;
                    data.name = "WINDOW_MAPPING Data Downloading";
                    publishProgress(data);
                }
                //endregion

                //region MAPPING_WINDOW_CHECKLIST
                request = new SoapObject(CommonString1.NAMESPACE, CommonString1.METHOD_NAME_UNIVERSAL_DOWNLOAD);
                request.addProperty("UserName", _UserId);
                request.addProperty("Type", "MAPPING_WINDOW_CHECKLIST");
                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);
                androidHttpTransport = new HttpTransportSE(CommonString1.URL);
                androidHttpTransport.call(CommonString1.SOAP_ACTION_UNIVERSAL, envelope);

                Object result_window_checklist = (Object) envelope.getResponse();
                if (result_window_checklist.toString() != null) {
                    xpp.setInput(new StringReader(result_window_checklist.toString()));
                    xpp.next();
                    eventType = xpp.getEventType();
                    mappingWindowChecklistGetterSetter = XMLHandlers.mappingWindowChecklistXML(xpp, eventType);
                    String mappingwindowChecklisttable = mappingWindowChecklistGetterSetter.getTable_mapping_window_checklist();
                    TableBean.setTable_mappingwindowchecklist(mappingwindowChecklisttable);
                    if (mappingWindowChecklistGetterSetter.getWINDOW_CD().size() > 0) {
                        resultHttp = CommonString1.KEY_SUCCESS;
                    }
                    data.value = 83;
                    data.name = "MAPPING WINDOW CHECKLIST Downloading";
                    publishProgress(data);
                }
                //endregion

                //region WINDOW_CHECKLIST_ANSWER
                request = new SoapObject(CommonString1.NAMESPACE, CommonString1.METHOD_NAME_UNIVERSAL_DOWNLOAD);
                request.addProperty("UserName", _UserId);
                request.addProperty("Type", "WINDOW_CHECKLIST_ANSWER");
                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);
                androidHttpTransport = new HttpTransportSE(CommonString1.URL);
                androidHttpTransport.call(CommonString1.SOAP_ACTION_UNIVERSAL, envelope);

                Object result_window_checklist_ans = (Object) envelope.getResponse();
                if (result_window_checklist_ans.toString() != null) {
                    xpp.setInput(new StringReader(result_window_checklist_ans.toString()));
                    xpp.next();
                    eventType = xpp.getEventType();
                    windowChecklistAnswerGetterSetter = XMLHandlers.WindowChecklistAnswerXML(xpp, eventType);
                    String window_checklist_ans_table = windowChecklistAnswerGetterSetter.getTable_window_checklist_answer();
                    TableBean.setTable_windowchecklistanswer(window_checklist_ans_table);
                    if (windowChecklistAnswerGetterSetter.getANSWER_CD().size() > 0) {
                        resultHttp = CommonString1.KEY_SUCCESS;
                    } else {
                        return "WINDOW_CHECKLIST_ANSWER";
                    }
                    data.value = 85;
                    data.name = "WINDOW CHECKLIST ANSWER Data Downloading";
                    publishProgress(data);
                }
                //endregion

                //region CATEGORY_MASTER
                request = new SoapObject(CommonString1.NAMESPACE, CommonString1.METHOD_NAME_UNIVERSAL_DOWNLOAD);
                request.addProperty("UserName", _UserId);
                request.addProperty("Type", "CATEGORY_MASTER");
                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);
                androidHttpTransport = new HttpTransportSE(CommonString1.URL);
                androidHttpTransport.call(CommonString1.SOAP_ACTION_UNIVERSAL, envelope);

                Object resultcategorymaster = (Object) envelope.getResponse();
                if (resultcategorymaster.toString() != null) {
                    xpp.setInput(new StringReader(resultcategorymaster.toString()));
                    xpp.next();
                    eventType = xpp.getEventType();
                    categorygettersetter = XMLHandlers.categoryMasterXML(xpp, eventType);
                    String categorytable = categorygettersetter.getCategory_master_table();
                    TableBean.setCategorymastertable(categorytable);
                    if (categorygettersetter.getCategory_cd().size() > 0) {
                        resultHttp = CommonString1.KEY_SUCCESS;
                    } else {
                        return "CATEGORY_MASTER";
                    }
                    data.value = 87;
                    data.name = "Category Master Downloading";
                    publishProgress(data);
                }
                //endregion

                //region COMPANY_MASTER
                request = new SoapObject(CommonString1.NAMESPACE, CommonString1.METHOD_NAME_UNIVERSAL_DOWNLOAD);
                request.addProperty("UserName", _UserId);
                request.addProperty("Type", "COMPANY_MASTER");
                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);
                androidHttpTransport = new HttpTransportSE(CommonString1.URL);
                androidHttpTransport.call(CommonString1.SOAP_ACTION_UNIVERSAL, envelope);

                Object resultCOMPANY_MASTER = (Object) envelope.getResponse();
                if (resultCOMPANY_MASTER.toString() != null) {
                    xpp.setInput(new StringReader(resultCOMPANY_MASTER.toString()));
                    xpp.next();
                    eventType = xpp.getEventType();
                    Companygettersetter = XMLHandlers.comapnyMasterXML(xpp, eventType);
                    if (Companygettersetter.getCOMPANY_CD().size() > 0) {
                        resultHttp = CommonString1.KEY_SUCCESS;
                        String table = Companygettersetter.getCompany_master_table();
                        TableBean.setCompanymastertable(table);
                    } else {
                        return "COMPANY_MASTER";
                    }
                    data.value = 90;
                    data.name = "COMPANY_MASTER Data Downloading";
                    publishProgress(data);
                }
                //endregion

                //region NON_WORKING_REASON
                request = new SoapObject(CommonString1.NAMESPACE, CommonString1.METHOD_NAME_UNIVERSAL_DOWNLOAD);
                request.addProperty("UserName", _UserId);
                request.addProperty("Type", "NON_WORKING_REASON");
                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);
                androidHttpTransport = new HttpTransportSE(CommonString1.URL);
                androidHttpTransport.call(CommonString1.SOAP_ACTION_UNIVERSAL, envelope);

                Object resultnonworking = (Object) envelope.getResponse();
                if (resultnonworking.toString() != null) {
                    xpp.setInput(new StringReader(resultnonworking.toString()));
                    xpp.next();
                    eventType = xpp.getEventType();
                    nonworkinggettersetter = XMLHandlers.nonWorkinReasonXML(xpp, eventType);
                    if (nonworkinggettersetter.getReason_cd().size() > 0) {
                        resultHttp = CommonString1.KEY_SUCCESS;
                        String nonworkingtable = nonworkinggettersetter.getNonworking_table();
                        TableBean.setNonworkingtable(nonworkingtable);
                    } else {
                        return "NON_WORKING_REASON";
                    }
                    data.value = 95;
                    data.name = "Non Working Reason Downloading";
                    publishProgress(data);
                }
                //endregion

                //region NON_WORKING_SUB_REASON
                request = new SoapObject(CommonString1.NAMESPACE, CommonString1.METHOD_NAME_UNIVERSAL_DOWNLOAD);
                request.addProperty("UserName", _UserId);
                request.addProperty("Type", "NON_WORKING_SUB_REASON");
                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);
                androidHttpTransport = new HttpTransportSE(CommonString1.URL);
                androidHttpTransport.call(CommonString1.SOAP_ACTION_UNIVERSAL, envelope);

                Object resultnonworkingsubreason = (Object) envelope.getResponse();
                if (resultnonworkingsubreason.toString() != null) {
                    xpp.setInput(new StringReader(resultnonworkingsubreason.toString()));
                    xpp.next();
                    eventType = xpp.getEventType();
                    nonworkingSubgettersetter = XMLHandlers.nonWorkinSubReasonXML(xpp, eventType);
                    if (nonworkingSubgettersetter.getSUB_REASON_CD().size() > 0) {
                        resultHttp = CommonString1.KEY_SUCCESS;
                        String nonworkingtable = nonworkingSubgettersetter.getNonworkingsubreason_table();
                        TableBean.setNonworkingsubreasontable(nonworkingtable);
                    } else {
                        return "NON_WORKING_SUB_REASON";
                    }
                    data.value = 96;
                    data.name = "Non Working Sub Reason Downloading";
                    publishProgress(data);
                }
                //endregion

                //region WINDOW_NON_REASON
                request = new SoapObject(CommonString1.NAMESPACE, CommonString1.METHOD_NAME_UNIVERSAL_DOWNLOAD);
                request.addProperty("UserName", _UserId);
                request.addProperty("Type", "WINDOW_NON_REASON");
                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);
                androidHttpTransport = new HttpTransportSE(CommonString1.URL);
                androidHttpTransport.call(CommonString1.SOAP_ACTION_UNIVERSAL, envelope);

                Object resultWindowNonReason = (Object) envelope.getResponse();
                if (resultWindowNonReason.toString() != null) {
                    xpp.setInput(new StringReader(resultWindowNonReason.toString()));
                    xpp.next();
                    eventType = xpp.getEventType();
                    windowNonReasonGetterSetter = XMLHandlers.windowNonReasonXML(xpp, eventType);
                    if (windowNonReasonGetterSetter.getWREASON_CD().size() > 0) {
                        resultHttp = CommonString1.KEY_SUCCESS;
                        String nonworkingtable = windowNonReasonGetterSetter.getWindowNonReason_table();
                        TableBean.setWindowNonReasontable(nonworkingtable);
                    } else {
                        return "NON_WORKING_SUB_REASON";
                    }
                    data.value = 97;
                    data.name = "Non Working Sub Reason Downloading";
                    publishProgress(data);
                }
                //endregion

                //region SUB_CATEGORY_MASTER
                request = new SoapObject(CommonString1.NAMESPACE, CommonString1.METHOD_NAME_UNIVERSAL_DOWNLOAD);
                request.addProperty("UserName", _UserId);
                request.addProperty("Type", "SUB_CATEGORY_MASTER");
                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);
                androidHttpTransport = new HttpTransportSE(CommonString1.URL);
                androidHttpTransport.call(CommonString1.SOAP_ACTION_UNIVERSAL, envelope);

                Object result_sub_category_master = (Object) envelope.getResponse();
                if (result_sub_category_master.toString() != null) {
                    xpp.setInput(new StringReader(result_sub_category_master.toString()));
                    xpp.next();
                    eventType = xpp.getEventType();
                    subCategoryMasterGetterSetter = XMLHandlers.subCategoryMasterXML(xpp, eventType);
                    if (subCategoryMasterGetterSetter.getSUB_CATEGORY_CD().size() > 0) {
                        resultHttp = CommonString1.KEY_SUCCESS;
                        String sub_category_table = subCategoryMasterGetterSetter.getTable_SUB_CATEGORY_MASTER();
                        TableBean.setSubCategoryMaster(sub_category_table);
                    } else {
                        return "SUB_CATEGORY_MASTER";
                    }
                    data.value = 97;
                    data.name = "Sub Category Master Data Downloading";
                    publishProgress(data);
                }
                //endregion

                //region MAPPING_STOCK
                request = new SoapObject(CommonString1.NAMESPACE, CommonString1.METHOD_NAME_UNIVERSAL_DOWNLOAD);
                request.addProperty("UserName", _UserId);
                request.addProperty("Type", "MAPPING_STOCK");
                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);
                androidHttpTransport = new HttpTransportSE(CommonString1.URL);
                androidHttpTransport.call(CommonString1.SOAP_ACTION_UNIVERSAL, envelope);

                Object result_mapping_stock = (Object) envelope.getResponse();
                if (result_mapping_stock.toString() != null) {
                    xpp.setInput(new StringReader(result_mapping_stock.toString()));
                    xpp.next();
                    eventType = xpp.getEventType();
                    mappingStockGetterSetter = XMLHandlers.mappingStockXML(xpp, eventType);
                    String mstocktable = mappingStockGetterSetter.getTable_MAPPING_STOCK();
                    TableBean.setMappingStock(mstocktable);
                    if (mappingStockGetterSetter.getSKU_CD().size() > 0) {
                        resultHttp = CommonString1.KEY_SUCCESS;
                    }
                    data.value = 98;
                    data.name = "Mapping Stock Data Downloading";
                    publishProgress(data);
                }
                //endregion

                //region STATE_MASTER
                request = new SoapObject(CommonString1.NAMESPACE, CommonString1.METHOD_NAME_UNIVERSAL_DOWNLOAD);
                request.addProperty("UserName", _UserId);
                request.addProperty("Type", "STATE_MASTER");
                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);
                androidHttpTransport = new HttpTransportSE(CommonString1.URL);
                androidHttpTransport.call(CommonString1.SOAP_ACTION_UNIVERSAL, envelope);

                Object result_STATE_MASTER = (Object) envelope.getResponse();
                if (result_STATE_MASTER.toString() != null) {
                    xpp.setInput(new StringReader(result_STATE_MASTER.toString()));
                    xpp.next();
                    eventType = xpp.getEventType();
                    stateMasterGetterSetter = XMLHandlers.stateMasterXML(xpp, eventType);
                    if (stateMasterGetterSetter.getState_cd_list().size() > 0) {
                        resultHttp = CommonString1.KEY_SUCCESS;
                        String table = stateMasterGetterSetter.getTable_STATE_MASTER();
                        TableBean.setStateMasterTable(table);
                    } else {
                        return "STATE_MASTER";
                    }
                    data.value = 92;
                    data.name = "STATE_MASTER Data Downloading";
                    publishProgress(data);
                }
                //endregion

                //region CITY_MASTER
                request = new SoapObject(CommonString1.NAMESPACE, CommonString1.METHOD_NAME_UNIVERSAL_DOWNLOAD);
                request.addProperty("UserName", _UserId);
                request.addProperty("Type", "CITY_MASTER");
                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);
                androidHttpTransport = new HttpTransportSE(CommonString1.URL);
                androidHttpTransport.call(CommonString1.SOAP_ACTION_UNIVERSAL, envelope);

                Object result_CITY_MASTER = (Object) envelope.getResponse();
                if (result_CITY_MASTER.toString() != null) {
                    xpp.setInput(new StringReader(result_CITY_MASTER.toString()));
                    xpp.next();
                    eventType = xpp.getEventType();
                    cityMasterGetterSetter = XMLHandlers.cityMasterXML(xpp, eventType);
                    if (cityMasterGetterSetter.getState_cd_list().size() > 0) {
                        resultHttp = CommonString1.KEY_SUCCESS;
                        String table = cityMasterGetterSetter.getTable_CITY_MASTER();
                        TableBean.setCityMasterTable(table);
                    } else {
                        return "result_CITY_MASTER";
                    }
                    data.value = 94;
                    data.name = "CITY_MASTER Data Downloading";
                    publishProgress(data);
                }
                //endregion


                data.value = 99;
                data.name = "Saving Data";
                publishProgress(data);

                db.open();
                db.insertJCPPreviousData(journeyPlanPreviousGetterSetter);
                db.insertJCPData(jcpgettersetter);
                db.insertSkuMasterData(skumastergettersetter);
                db.insertBrandMasterData(brandGetterSetter);
                db.insertDisplayMasterData(displayMasterGetterSetter);
                db.insertMAPPING_AvailibilityData(mappingavailgettersetter);
                db.insertMAPPING_PaidVisibilityData(mappingPaidVisibilityGetterSetter);
                db.insertPOSMata(posmgettersetter);
                db.insertCOMPANY_MASTERData(Companygettersetter);
                db.insertSUB_CATEGORY_MASTERData(subCategoryMasterGetterSetter);
                if (mappingStockGetterSetter.getSKU_CD().size() > 0) {
                    db.insertMAPPING_STOCKData(mappingStockGetterSetter);
                }
                db.insertWINDOW_MASTER(mapplingstatuswindows);
                db.insertWINDOW_CHECKLISTData(mappingassetgettersetter);
                db.insertWINDOW_MAPPINGData(assetmastergettersetter);
                db.insertCategoryMasterData(categorygettersetter);
                if (mappingWindowChecklistGetterSetter.getWINDOW_CD().size() > 0) {
                    db.insertMappingWindowChecklistData(mappingWindowChecklistGetterSetter);
                } else {
                    db.deleteMappingWindowChecklistData();
                }
                db.insertNonWorkingReasonData(nonworkinggettersetter);
                db.insertNonWorkingSubReasonData(nonworkingSubgettersetter);
                db.insertWindowNonReasonData(windowNonReasonGetterSetter);
                db.insertMappingPromotion2Data(mappingPromotion2GetterSetter);
                db.insertNonPromotionReasonData(nonPromotionReasonGetterSetter);
                if (mappingPosmGetterSetter.getSTORE_CD().size() > 0) {
                    db.insertMappingPosmData(mappingPosmGetterSetter);
                }
                db.insertEmpSalaryData(empSalaryGetterSetter);
                db.insertNonPosmReasonData(nonPOSMReasonGetterSetter);
                if (windowChecklistAnswerGetterSetter.getANSWER_CD().size() > 0) {
                    db.insertWindowChecklistAnswerData(windowChecklistAnswerGetterSetter);
                } else {
                    db.deleteWindowChecklistAnswerData();
                }
                if (stateMasterGetterSetter.getState_cd_list().size() > 0) {
                    db.insertStateMasterData(stateMasterGetterSetter);
                }
                if (cityMasterGetterSetter.getCity_cd_list().size() > 0) {
                    db.insertCityMasterData(cityMasterGetterSetter);
                }


                //	db.insertNonWorkingReasonData(nonworkinggettersetter);
				/*if(promotion_flag){
                    db.insertMappingPromotionData(mappingprormotgettersetter);
				}
				if(asset_flag){
				}
				//db.insertCompanyMasterData(companyGetterSetter);
				*/
                data.value = 100;
                data.name = "Finishing";
                publishProgress(data);

                return resultHttp;

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
                        // TODO Auto-generated method stub
                        message.showMessage();

                    }
                });

            } catch (Exception e) {
                final AlertMessage message = new AlertMessage((Activity) context, AlertMessage.MESSAGE_EXCEPTION + e, "download", e);
                e.getMessage();
                e.printStackTrace();
                e.getCause();
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
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
                AlertMessage message = new AlertMessage(CompleteDownloadActivity.this, AlertMessage.MESSAGE_DOWNLOAD, "success", null);
                message.showMessage();
            } else if (!result.equalsIgnoreCase("")) {
                AlertMessage message = new AlertMessage(CompleteDownloadActivity.this, AlertMessage.MESSAGE_JCP_FALSE + result, "success", null);
                message.showMessage();
            }
        }

    }


}

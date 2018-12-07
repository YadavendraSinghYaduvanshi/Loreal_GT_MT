package com.cpm.dailyentry;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.cpm.Constants.AlertandMessages;
import com.cpm.Constants.CommonString;
import com.cpm.Constants.CommonString1;
import com.cpm.GetterSetter.CityMasterGetterSetter;
import com.cpm.GetterSetter.KYCMasterGetterSetter;
import com.cpm.GetterSetter.StateMasterGetterSetter;
import com.cpm.GetterSetter.StoreProfileGetterSetter;
import com.cpm.capitalfoods.R;
import com.cpm.database.GSKDatabase;
import com.cpm.delegates.CoverageBean;
import com.cpm.utilities.CommonFunctions;
import com.cpm.xmlGetterSetter.JourneyPlanGetterSetter;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

public class StoreProfileActivity extends AppCompatActivity {

    EditText retailer_edt, contact_edt, contact_edt2, postalAdd_edt, gst_no_edt;
    TextView storename_txt;
    Context context;
    FloatingActionButton fab;
    Button save_btn;
    GSKDatabase database;
    private SharedPreferences preferences;
    JourneyPlanGetterSetter jcpGetSet;
    Intent in;
    ArrayAdapter kyc_adapter, city_adapter, state_adapter;
    Spinner kyc_spinner, city_spinner, state_spinner;
    ArrayList<KYCMasterGetterSetter> kycMasterList;
    ArrayList<CityMasterGetterSetter> cityMasterList = new ArrayList<>();
    ArrayList<StateMasterGetterSetter> stateMasterList;
    LinearLayout ll_gst;
    String kyc, city, state;
    int kycId, cityId, stateId;
    ImageView camera_img, address_img;
    String msg = "", _pathforcheck, _pathforcheck2, _path, username, visit_date_formatted, gstImage = "", addressImg = "";
    StoreProfileGetterSetter storeProfileSaveddata;
    boolean isProfileDataFilled = false;
    ArrayList<CoverageBean> coverage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_profile);
        declaration();
        prepareList();

        if (getIntent().getSerializableExtra(CommonString.TAG_OBJECT) != null) {
            jcpGetSet = (JourneyPlanGetterSetter) getIntent().getSerializableExtra(CommonString.TAG_OBJECT);
            database.open();
            storeProfileSaveddata = database.getStoreProfileData(jcpGetSet.getStore_cd().get(0));
            storename_txt.setText(jcpGetSet.getSTORE().get(0));

            //region Retailer Name
            if (storeProfileSaveddata != null && storeProfileSaveddata.getRetailerName() != null) {
                retailer_edt.setText(storeProfileSaveddata.getRetailerName());
            } else if (jcpGetSet.getRETAILER_NAME().get(0) != null && !jcpGetSet.getRETAILER_NAME().get(0).equalsIgnoreCase("")) {
                retailer_edt.setText(jcpGetSet.getRETAILER_NAME().get(0));
            }
            //endregion

            //region Contact Number
            if (storeProfileSaveddata != null && storeProfileSaveddata.getContactNumber() != null) {
                contact_edt.setText(storeProfileSaveddata.getContactNumber());
            } else if (jcpGetSet.getSTORE_CONTACT_NO().get(0) != null && !jcpGetSet.getSTORE_CONTACT_NO().get(0).equalsIgnoreCase("")) {
                contact_edt.setText(jcpGetSet.getSTORE_CONTACT_NO().get(0));
            }
            //endregion

            //region Contact Number 2
            if (storeProfileSaveddata != null && storeProfileSaveddata.getContactNumber2() != null) {
                contact_edt2.setText(storeProfileSaveddata.getContactNumber2());
            } else if (jcpGetSet.getSTORE_CONTACT_NO2().get(0) != null && !jcpGetSet.getSTORE_CONTACT_NO2().get(0).equalsIgnoreCase("")) {
                contact_edt2.setText(jcpGetSet.getSTORE_CONTACT_NO2().get(0));
            }
            //endregion

            //region Postal Address
            if (storeProfileSaveddata != null && storeProfileSaveddata.getPostalAddress() != null) {
                postalAdd_edt.setText(storeProfileSaveddata.getPostalAddress());
            } else if (jcpGetSet.getADDRESS().get(0) != null && !jcpGetSet.getADDRESS().get(0).equalsIgnoreCase("")) {
                postalAdd_edt.setText(jcpGetSet.getADDRESS().get(0));
            }
            //endregion

            //region state
            if (storeProfileSaveddata != null && storeProfileSaveddata.getState_cd() != 0) {
                for (int i = 0; i < stateMasterList.size(); i++) {
                    if (stateMasterList.get(i).getState_cd() == storeProfileSaveddata.getState_cd()) {
                        state_spinner.setSelection(i);
                        break;
                    }
                }
            } else if (jcpGetSet.getSTATE_CD().get(0) != null && !jcpGetSet.getSTATE_CD().get(0).equalsIgnoreCase("0")) {
                for (int i = 0; i < stateMasterList.size(); i++) {
                    if (stateMasterList.get(i).getState_cd() == Integer.parseInt(jcpGetSet.getSTATE_CD().get(0))) {
                        state_spinner.setSelection(i);
                        break;
                    }
                }
            }
            //endregion

            //region Address Image
            if (storeProfileSaveddata != null && storeProfileSaveddata.getAddressImg() != null && !storeProfileSaveddata.getAddressImg().equalsIgnoreCase("")) {
                addressImg = storeProfileSaveddata.getAddressImg();
                address_img.setBackgroundResource(R.drawable.camera_green);
            } else if (jcpGetSet.getSTORE_PROFILE_IMAGE().get(0) != null && !jcpGetSet.getSTORE_PROFILE_IMAGE().get(0).equalsIgnoreCase("")) {
                addressImg = jcpGetSet.getSTORE_PROFILE_IMAGE().get(0);
                address_img.setBackgroundResource(R.drawable.camera_green);
            } else {
                addressImg = "";
                address_img.setBackgroundResource(R.drawable.camera);
            }
            //endregion

            //region KYC ID
            if (storeProfileSaveddata != null && storeProfileSaveddata.getKycId() != 0) {
                for (int i = 0; i < kycMasterList.size(); i++) {
                    if (kycMasterList.get(i).getKycId().get(0) == storeProfileSaveddata.getKycId()) {
                        kyc_spinner.setSelection(i);
                        break;
                    }
                }
            } else if (jcpGetSet.getKYC_ID().get(0) != null && !jcpGetSet.getKYC_ID().get(0).equalsIgnoreCase("0")) {
                for (int i = 0; i < kycMasterList.size(); i++) {
                    if (kycMasterList.get(i).getKycId().get(0) == Integer.parseInt(jcpGetSet.getKYC_ID().get(0))) {
                        kyc_spinner.setSelection(i);
                        break;
                    }
                }
            }
            //endregion

            //region GST Number
            if (storeProfileSaveddata != null && storeProfileSaveddata.getGstNo() != null) {
                gst_no_edt.setText(storeProfileSaveddata.getGstNo());
            } else if (jcpGetSet.getGSTIN_NO().get(0) != null && !jcpGetSet.getGSTIN_NO().get(0).equalsIgnoreCase("")) {
                gst_no_edt.setText(jcpGetSet.getGSTIN_NO().get(0));
            }
            //endregion

            //region GST Image
            if (storeProfileSaveddata != null && storeProfileSaveddata.getGstImg() != null && !storeProfileSaveddata.getGstImg().equalsIgnoreCase("")) {
                gstImage = storeProfileSaveddata.getGstImg();
                camera_img.setBackgroundResource(R.drawable.camera_green);
            } else if (jcpGetSet.getGSTIN_IMAGE().get(0) != null && !jcpGetSet.getGSTIN_IMAGE().get(0).equalsIgnoreCase("")) {
                gstImage = jcpGetSet.getGSTIN_IMAGE().get(0);
                camera_img.setBackgroundResource(R.drawable.camera_green);
            } else {
                gstImage = "";
                camera_img.setBackgroundResource(R.drawable.camera);
            }
            //endregion

            checkForFilledData();
        }


        camera_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _pathforcheck = jcpGetSet.getStore_cd().get(0) + "_" + username.replace(".", "") + "_GST_Img-" + visit_date_formatted + "-" + CommonFunctions.getCurrentTimeHHMMSS() + ".jpg";
                _path = CommonString1.FILE_PATH + _pathforcheck;
                CommonFunctions.startAnncaCameraActivity(context, _path);
                //startCameraActivity();

            }
        });

        address_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _pathforcheck2 = jcpGetSet.getStore_cd().get(0) + "_" + username.replace(".", "") + "_Store_Profile_Img-" + visit_date_formatted + "-" + CommonFunctions.getCurrentTimeHHMMSS() + ".jpg";
                _path = CommonString1.FILE_PATH + _pathforcheck2;
                CommonFunctions.startAnncaCameraActivity(context, _path);
                //startCameraActivity();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isProfileDataFilled) {
                    boolean flag = true;
                    if (coverage.size() > 0) {
                        for (int i = 0; i < coverage.size(); i++) {
                            if (jcpGetSet.getStore_cd().get(0).equals(coverage.get(i).getStoreId())) {
                                flag = false;
                                break;
                            }
                        }
                    }
                    if (flag) {
                        Intent in = new Intent(context, StoreimageActivity.class);
                        in.putExtra(CommonString.TAG_OBJECT, jcpGetSet);
                        in.putExtra(CommonString1.KEY_STORE_CD, jcpGetSet.getStore_cd().get(0));
                        startActivity(in);
                        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                    } else {
                        if (jcpGetSet.getCHANNEL_CD().get(0).equalsIgnoreCase("1")) {
                            Intent in = new Intent(context, StoreEntry.class);
                            in.putExtra(CommonString.TAG_OBJECT, jcpGetSet);
                            in.putExtra(CommonString1.KEY_STORE_CD, jcpGetSet.getStore_cd().get(0));
                            startActivity(in);
                            overridePendingTransition(R.anim.activity_in, R.anim.activity_out);

                        } else {
                            Intent in = new Intent(context, StoreEntryForChannel2Activity.class);
                            in.putExtra(CommonString.TAG_OBJECT, jcpGetSet);
                            in.putExtra(CommonString1.KEY_STORE_CD, jcpGetSet.getStore_cd().get(0));
                            startActivity(in);
                            overridePendingTransition(R.anim.activity_in, R.anim.activity_out);

                        }
                    }
                }
            }
        });

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValid()) {

                    StoreProfileGetterSetter storeProfileGetSet = new StoreProfileGetterSetter();
                    storeProfileGetSet.setStoreid(Integer.parseInt(jcpGetSet.getStore_cd().get(0)));
                    storeProfileGetSet.setRetailerName(retailer_edt.getText().toString());
                    storeProfileGetSet.setContactNumber(contact_edt.getText().toString());
                    storeProfileGetSet.setContactNumber2(contact_edt2.getText().toString());
                    storeProfileGetSet.setPostalAddress(postalAdd_edt.getText().toString());
                    storeProfileGetSet.setCity_cd(cityId);
                    storeProfileGetSet.setState_cd(stateId);
                    storeProfileGetSet.setAddressImg(addressImg);
                    storeProfileGetSet.setKycId(kycId);
                    storeProfileGetSet.setGstNo(gst_no_edt.getText().toString());
                    storeProfileGetSet.setGstImg(gstImage);
                    database.open();
                    if (database.insertStoreProfileData(storeProfileGetSet) > 0) {
                        AlertandMessages.showToastMsg(context, "Store Profile Data Saved");
                        isProfileDataFilled = true;
                        checkProfileDataFilled(isProfileDataFilled);
                    } else {
                        isProfileDataFilled = false;
                        AlertandMessages.showToastMsg(context, "Error in saving data");
                    }
                } else {
                    isProfileDataFilled = false;
                    AlertandMessages.showToastMsg(context, msg);
                }
            }
        });


        kyc_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0 && kycMasterList.size() > 0) {
                    kycId = kycMasterList.get(position).getKycId().get(0);
                    kyc = kycMasterList.get(position).getKyc().get(0);
                    if (kyc.equalsIgnoreCase("Unregistered") || position == 0) {
                        ll_gst.setVisibility(View.GONE);
                        gst_no_edt.setText("");
                        if (!gstImage.equalsIgnoreCase("")) {
                            File file = new File(CommonString1.FILE_PATH + gstImage);
                            if (file.exists()) {
                                file.delete();
                            }
                        }
                        camera_img.setBackgroundResource(R.drawable.camera);
                        gstImage = "";
                    } else {
                        ll_gst.setVisibility(View.VISIBLE);
                    }

                } else {
                    kycId = 0;
                    kyc = "";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        city_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0 && cityMasterList.size() > 0) {
                    cityId = cityMasterList.get(position).getCity_cd();
                    city = cityMasterList.get(position).getCity();
                } else {
                    cityId = 0;
                    city = "";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        state_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0 && stateMasterList.size() > 0) {
                    stateId = stateMasterList.get(position).getState_cd();
                    state = stateMasterList.get(position).getState();
                    cityMasterList.clear();
                    city_adapter.clear();
                    database.open();
                    cityMasterList = database.getCityMasterList(stateId);
                    for (int i = 0; i < cityMasterList.size(); i++) {
                        city_adapter.add(cityMasterList.get(i).getCity());
                    }
                    city_spinner.setAdapter(city_adapter);
                    setEditDataForCitySpinner();
                } else {
                    stateId = 0;
                    state = "";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    void setEditDataForCitySpinner() {
        //region City
        if (storeProfileSaveddata != null && storeProfileSaveddata.getCity_cd() != 0) {
            for (int i = 0; i < cityMasterList.size(); i++) {
                if (cityMasterList.get(i).getCity_cd() == storeProfileSaveddata.getCity_cd()) {
                    city_spinner.setSelection(i);
                    break;
                }
            }
        } else if (jcpGetSet.getCITY_CD().get(0) != null && !jcpGetSet.getCITY_CD().get(0).equalsIgnoreCase("0")) {
            for (int i = 0; i < cityMasterList.size(); i++) {
                if (cityMasterList.get(i).getCity_cd() == Integer.parseInt(jcpGetSet.getCITY_CD().get(0))) {
                    city_spinner.setSelection(i);
                    break;
                }
            }
        }
        //endregion
    }

    void checkForFilledData() {
        boolean isValid = true;

        if (retailer_edt.getText().toString().isEmpty() || retailer_edt.getText().toString().equalsIgnoreCase("")) {
            isValid = false;
        } else if (contact_edt.getText().toString().isEmpty() || contact_edt.getText().toString().equalsIgnoreCase("")) {
            isValid = false;
        } else if (postalAdd_edt.getText().toString().isEmpty() || postalAdd_edt.getText().toString().equalsIgnoreCase("")) {
            isValid = false;
        } else if (kyc_spinner.getSelectedItemPosition() == 0) {
            isValid = false;
        } else {
            if (contact_edt.getText().toString().length() < 10) {
                isValid = false;
            }
            if (kyc_spinner.getSelectedItemPosition() != 0) {
                if (kyc_spinner.getSelectedItem().toString().equalsIgnoreCase("registered")) {
                    if (gst_no_edt.getText().toString().isEmpty() || gst_no_edt.getText().toString().equalsIgnoreCase("")) {
                        isValid = false;
                    } else if (gst_no_edt.getText().toString().length() != 15) {
                        isValid = false;
                    }
                    if (gstImage == null || gstImage.equalsIgnoreCase("")) {
                        isValid = false;
                    }
                }
            }
        }

        if (isValid) {
            isProfileDataFilled = true;
            checkProfileDataFilled(isProfileDataFilled);
        } else {
            isProfileDataFilled = false;
            checkProfileDataFilled(isProfileDataFilled);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        coverage = database.getCoverageData(jcpGetSet.getVISIT_DATE().get(0));
        checkProfileDataFilled(isProfileDataFilled);
    }

    private boolean isValid() {
        boolean isValid = true;

        if (retailer_edt.getText().toString().isEmpty() || retailer_edt.getText().toString().equalsIgnoreCase("")) {
            isValid = false;
            msg = "Please fill retailer name";

        } else if (contact_edt.getText().toString().isEmpty() || contact_edt.getText().toString().equalsIgnoreCase("")) {
            isValid = false;
            msg = "Please fill contact number";

        } else if (postalAdd_edt.getText().toString().isEmpty() || postalAdd_edt.getText().toString().equalsIgnoreCase("")) {
            isValid = false;
            msg = "Please fill postal address";
        } else if (state_spinner.getSelectedItemPosition() == 0) {
            isValid = false;
            msg = "Please select state";
        } else if (city_spinner.getSelectedItemPosition() == 0) {
            isValid = false;
            msg = "Please select city";
        } else if (addressImg == null || addressImg.equalsIgnoreCase("")) {
            isValid = false;
            msg = "Please Click Store Profile Photo";
        } else if (kyc_spinner.getSelectedItemPosition() == 0) {
            isValid = false;
            msg = "Please select KYC";
        } else {

            if (contact_edt.getText().toString().length() < 10) {
                isValid = false;
                msg = "Please fill 10 digit contact number";
            }
            if (kyc_spinner.getSelectedItemPosition() != 0) {
                if (kyc_spinner.getSelectedItem().toString().equalsIgnoreCase("registered")) {
                    if (gst_no_edt.getText().toString().isEmpty() || gst_no_edt.getText().toString().equalsIgnoreCase("")) {
                        isValid = false;
                        msg = "Please fill GST number";
                    } else if (gst_no_edt.getText().toString().length() != 15) {
                        isValid = false;
                        msg = "Please fill 15 digit GST number";
                    }
                    if (gstImage == null || gstImage.equalsIgnoreCase("")) {
                        isValid = false;
                        msg = "Please Click GST Image";
                    }
                }
            }
        }

        return isValid;
    }

    protected void startCameraActivity() {
        try {
            Log.i("MakeMachine", "startCameraActivity()");
            File file = new File(_path);
            Uri outputFileUri = Uri.fromFile(file);
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
            startActivityForResult(intent, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    void prepareList() {
        //region Kyc Spinner
        kyc_adapter = new ArrayAdapter<CharSequence>(context, android.R.layout.simple_spinner_item);
        kyc_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        kyc_spinner.setAdapter(kyc_adapter);

        database.open();
        kycMasterList = database.getKycMasterList();
        for (int i = 0; i < kycMasterList.size(); i++) {
            kyc_adapter.add(kycMasterList.get(i).getKyc().get(0));
        }
        kyc_spinner.setAdapter(kyc_adapter);
        ll_gst.setVisibility(View.GONE);
        //endregion

        //region City Spinner
        city_adapter = new ArrayAdapter<CharSequence>(context, android.R.layout.simple_spinner_item);
        city_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //endregion

        //region State Spinner
        state_adapter = new ArrayAdapter<CharSequence>(context, android.R.layout.simple_spinner_item);
        state_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        database.open();
        stateMasterList = database.getStateMasterList();
        for (int i = 0; i < stateMasterList.size(); i++) {
            state_adapter.add(stateMasterList.get(i).getState());
        }
        state_spinner.setAdapter(state_adapter);
        //endregion

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


    void declaration() {
        context = this;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        retailer_edt = (EditText) findViewById(R.id.retailer_edt);
        contact_edt = (EditText) findViewById(R.id.contact_edt);
        contact_edt2 = (EditText) findViewById(R.id.contact_edt2);
        postalAdd_edt = (EditText) findViewById(R.id.postalAdd_edt);
        city_spinner = (Spinner) findViewById(R.id.city_spn);
        state_spinner = (Spinner) findViewById(R.id.state_spn);
        gst_no_edt = (EditText) findViewById(R.id.gst_no_edt);
        storename_txt = (TextView) findViewById(R.id.storename_txt);
        kyc_spinner = (Spinner) findViewById(R.id.kyc_spinner);
        ll_gst = (LinearLayout) findViewById(R.id.ll_gst);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        camera_img = (ImageView) findViewById(R.id.camera_img);
        address_img = (ImageView) findViewById(R.id.cam_add_img);
        save_btn = (Button) findViewById(R.id.save_btn);
        in = new Intent(context, StoreimageActivity.class);
        in.putExtra(CommonString.TAG_OBJECT, (Serializable) jcpGetSet);
        database = new GSKDatabase(context);

        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        username = preferences.getString(CommonString1.KEY_USERNAME, "");
        visit_date_formatted = preferences.getString(CommonString1.KEY_YYYYMMDD_DATE, "");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i("MakeMachine", "resultCode: " + resultCode);
        switch (resultCode) {
            case 0:
                // _pathforcheck = "";
                Log.i("MakeMachine", "User cancelled");
                break;
            case -1:
                if (_pathforcheck != null && !_pathforcheck.equals("")) {
                    if (new File(CommonString1.FILE_PATH + _pathforcheck).exists()) {
                        gstImage = _pathforcheck;
                        camera_img.setImageResource(R.drawable.camera_green);
                        String metadata = CommonFunctions.setMetadataAtImages(jcpGetSet, "GST Image", username);
                        CommonFunctions.addMetadataAndTimeStampToImage(context, CommonString1.FILE_PATH + _pathforcheck, metadata);
                        _pathforcheck = "";
                    }
                } else if (_pathforcheck2 != null && !_pathforcheck2.equals("")) {
                    if (new File(CommonString1.FILE_PATH + _pathforcheck2).exists()) {
                        addressImg = _pathforcheck2;
                        address_img.setImageResource(R.drawable.camera_green);
                        String metadata = CommonFunctions.setMetadataAtImages(jcpGetSet, "Store Profile", username);
                        CommonFunctions.addMetadataAndTimeStampToImage(context, CommonString1.FILE_PATH + _pathforcheck2, metadata);
                        _pathforcheck2 = "";
                    }
                }
                break;
        }
    }

    void checkProfileDataFilled(boolean value) {
        if (value) {
            fab.setVisibility(View.VISIBLE);
        } else {
            fab.setVisibility(View.INVISIBLE);
        }
    }

}

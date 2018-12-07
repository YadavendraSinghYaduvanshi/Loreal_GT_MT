package com.cpm.dailyentry;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.cpm.Constants.AlertandMessages;
import com.cpm.Constants.CommonString;
import com.cpm.Constants.CommonString1;
import com.cpm.capitalfoods.R;
import com.cpm.database.GSKDatabase;
import com.cpm.delegates.CoverageBean;
import com.cpm.utilities.CommonFunctions;
import com.cpm.xmlGetterSetter.JourneyPlanGetterSetter;
import com.cpm.xmlGetterSetter.NonWorkingReasonGetterSetter;
import com.cpm.xmlGetterSetter.NonWorkingSubReasonGetterSetter;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class NonWorkingReason extends AppCompatActivity implements
        OnItemSelectedListener, OnClickListener {

    ArrayList<NonWorkingReasonGetterSetter> reasondata = new ArrayList<>();
    ArrayList<NonWorkingSubReasonGetterSetter> subreasondata = new ArrayList<>();
    private Spinner reasonspinner, merNotAllowedSpinner;
    private GSKDatabase database;
    String reasonname;
    String reasonid;
    String subreasonid = "0";
    String entry_allow;
    String image_allow;
    String image;
    String reason_reamrk;
    Button save;
    private ArrayAdapter<CharSequence> reason_adapter, subreason_adapter;
    protected String _path;
    protected String _pathforcheck = "";
    private String image1 = "";
    private SharedPreferences preferences;
    String _UserId, metadata_global;
    EditText text, informTo;
    AlertDialog alert;
    ImageButton camera;
    RelativeLayout reason_lay, rel_cam;
    Context context;
    ArrayList<JourneyPlanGetterSetter> jcp;
    JourneyPlanGetterSetter jcpGetSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nonworking);
        declaration();

        if (getIntent().getSerializableExtra(CommonString.TAG_OBJECT) != null) {
            jcpGetSet = (JourneyPlanGetterSetter) getIntent().getSerializableExtra(CommonString.TAG_OBJECT);
        }
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        _UserId = preferences.getString(CommonString1.KEY_USERNAME, "");
        metadata_global = preferences.getString(CommonString1.KEY_META_DATA, "");

        camera.setOnClickListener(this);
        save.setOnClickListener(this);

        //region Reason Spinner
        reasondata = database.getNonWorkingData(true);
        reason_adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item);
        for (int i = 0; i < reasondata.size(); i++) {
            reason_adapter.add(reasondata.get(i).getReason().get(0));
        }
        reasonspinner.setAdapter(reason_adapter);
        reason_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        reasonspinner.setOnItemSelectedListener(this);
        //endregion

        //region Sub Reason spinner
        subreasondata = database.getNonWorkingSubReasonData();
        subreason_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        subreason_adapter.add("Select Sub Reason");
        for (int i = 0; i < subreasondata.size(); i++) {
            subreason_adapter.add(subreasondata.get(i).getSUB_REASON().get(0));
        }
        merNotAllowedSpinner.setAdapter(subreason_adapter);
        subreason_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        merNotAllowedSpinner.setOnItemSelectedListener(this);
        //endregion
    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        finish();
        overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
        // TODO Auto-generated method stub
        switch (arg0.getId()) {
            case R.id.spinner2:
                if (position != 0) {
                    reasonname = reasondata.get(position).getReason().get(0);
                    reasonid = reasondata.get(position).getReason_cd().get(0);
                    entry_allow = reasondata.get(position).getEntry_allow().get(0);
                    image_allow = reasondata.get(position).getIMAGE_ALLOW().get(0);

                    if (image_allow.equalsIgnoreCase("1")) {
                        rel_cam.setVisibility(View.VISIBLE);
                        image = "true";
                    } else {
                        rel_cam.setVisibility(View.GONE);
                        image = "false";
                    }
                    if (reasonid.equalsIgnoreCase("7")) {
                        merNotAllowedSpinner.setVisibility(View.VISIBLE);
                    } else {
                        merNotAllowedSpinner.setVisibility(View.GONE);
                        subreasonid = "0";
                    }
                    reason_reamrk = "true";
                    if (reason_reamrk.equalsIgnoreCase("true")) {
                        reason_lay.setVisibility(View.VISIBLE);
                    } else {
                        reason_lay.setVisibility(View.GONE);
                    }
                } else {
                    reasonname = "";
                    reasonid = "0";
                }
                break;
            case R.id.merNotallowdspinner:
                if (position != 0) {
                    subreasonid = subreasondata.get(position - 1).getSUB_REASON_CD().get(0);
                } else {
                    subreasonid = "0";
                }
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    protected void startCameraActivity() {
        try {
            Log.i("MakeMachine", "startCameraActivity()");
            File file = new File(_path);
            Uri outputFileUri = Uri.fromFile(file);
            Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
            startActivityForResult(intent, 0);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i("MakeMachine", "resultCode: " + resultCode);
        switch (resultCode) {
            case 0:
                Log.i("MakeMachine", "User cancelled");
                break;
            case -1:
                if (_pathforcheck != null && !_pathforcheck.equals("")) {
                    if (new File(CommonString1.FILE_PATH + _pathforcheck).exists()) {
                        camera.setImageDrawable(getResources().getDrawable(R.drawable.camera_list_tick));
                        String metadata = CommonFunctions.getMetadataAtImagesFromPref(metadata_global, "Non Working Image");
                        CommonFunctions.addMetadataAndTimeStampToImage(context, CommonString1.FILE_PATH + _pathforcheck, metadata);
                        image1 = _pathforcheck;
                    }
                }
                break;
        }
    }

    public boolean imageAllowed() {
        boolean result = true;
        if (image.equalsIgnoreCase("true")) {
            if (image1.equalsIgnoreCase("")) {
                result = false;
            }
        }
        return result;
    }

    public boolean textAllowed() {
        return true;
    }

    public boolean isDataUploaded() {
        jcp = database.getJCPData(jcpGetSet.getVISIT_DATE().get(0));
        boolean flag = true;
        if (entry_allow.equals("0")) {
            if (jcp.size() > 0) {
                for (int i = 0; i < jcp.size(); i++) {
                    if (jcp.get(i).getUploadStatus().get(0).equalsIgnoreCase(CommonString1.KEY_U) ||
                            jcp.get(i).getUploadStatus().get(0).equalsIgnoreCase(CommonString1.KEY_D)) {
                        flag = false;
                        break;
                    }
                }
            }
        }
        return flag;
    }


    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        if (v.getId() == R.id.imgcam) {
            _pathforcheck = jcpGetSet.getStore_cd().get(0) + "NonWorking" + _UserId + "_" + jcpGetSet.getVISIT_DATE().get(0).replace("/", "") + "_" + getCurrentTime().replace(":", "") + ".jpg";
            _path = CommonString1.FILE_PATH + _pathforcheck;
            //startCameraActivity();
            CommonFunctions.startAnncaCameraActivity(context, _path);
            //CommonFunctions.startMaterialCameraActivity((Activity) context, _pathforcheck, CommonString1.FILE_PATH);
        }
        if (v.getId() == R.id.save) {
            if (validatedata()) {
                if (imageAllowed()) {
                    if (textAllowed()) {
                        if (isDataUploaded()) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(context);
                            builder.setMessage("Do you want to save the data ")
                                    .setCancelable(false)
                                    .setPositiveButton("OK",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(
                                                        DialogInterface dialog,
                                                        int id) {
                                                    alert.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                                                    if (entry_allow.equals("0")) {

                                                        database.deleteAllTables();
                                                        for (int i = 0; i < jcp.size(); i++) {

                                                            CoverageBean cdata = new CoverageBean();
                                                            cdata.setStoreId(jcp.get(i).getStore_cd().get(0));
                                                            cdata.setVisitDate(jcp.get(i).getVISIT_DATE().get(0));
                                                            cdata.setChannel_cd(jcp.get(i).getCHANNEL_CD().get(0));
                                                            cdata.setUserId(_UserId);
                                                            cdata.setInTime("00:00:00");
                                                            cdata.setOutTime("00:00:00");
                                                            cdata.setReason(reasonname);
                                                            cdata.setReasonid(reasonid);
                                                            cdata.setSub_reasonId(subreasonid);
                                                            cdata.setInformto(informTo.getText().toString());
                                                            cdata.setLatitude("0.0");
                                                            cdata.setLongitude("0.0");
                                                            cdata.setImage(image1);
                                                            cdata.setRemark(text.getText().toString().replaceAll("[&^<>{}'$]", " "));
                                                            cdata.setStatus(CommonString1.STORE_STATUS_LEAVE);

                                                            database.InsertCoverageData(cdata);
                                                            database.updateStoreStatusOnLeave(jcp.get(i).getStore_cd().get(0), jcp.get(i).getVISIT_DATE().get(0), CommonString1.STORE_STATUS_LEAVE);

                                                            SharedPreferences.Editor editor = preferences.edit();
                                                            editor.putString(CommonString1.KEY_STOREVISITED_STATUS + jcp.get(i).getStore_cd().get(0), "No");
                                                            editor.putString(CommonString1.KEY_STOREVISITED_STATUS, "");
                                                            editor.putString(CommonString1.KEY_STORE_IN_TIME, "");
                                                            editor.putString(CommonString1.KEY_LATITUDE, "");
                                                            editor.putString(CommonString1.KEY_LONGITUDE, "");
                                                            editor.commit();

                                                            finish();
                                                        }
                                                    } else {

                                                        CoverageBean cdata = new CoverageBean();
                                                        cdata.setStoreId(jcpGetSet.getStore_cd().get(0));
                                                        cdata.setVisitDate(jcpGetSet.getVISIT_DATE().get(0));
                                                        cdata.setChannel_cd(jcpGetSet.getCHANNEL_CD().get(0));
                                                        cdata.setUserId(_UserId);
                                                        cdata.setInTime("00:00:00");
                                                        cdata.setOutTime("00:00:00");
                                                        cdata.setReason(reasonname);
                                                        cdata.setReasonid(reasonid);
                                                        cdata.setSub_reasonId(subreasonid);
                                                        cdata.setInformto(informTo.getText().toString());
                                                        cdata.setLatitude("0.0");
                                                        cdata.setLongitude("0.0");
                                                        cdata.setImage(image1);

                                                        cdata.setRemark(text.getText().toString().replaceAll("[&^<>{}'$]", " "));
                                                        cdata.setStatus(CommonString1.STORE_STATUS_LEAVE);

                                                        database.InsertCoverageData(cdata);
                                                        database.updateStoreStatusOnLeave(jcpGetSet.getStore_cd().get(0), jcpGetSet.getVISIT_DATE().get(0), CommonString1.STORE_STATUS_LEAVE);

                                                        SharedPreferences.Editor editor = preferences.edit();
                                                        editor.putString(CommonString1.KEY_STOREVISITED_STATUS + jcpGetSet.getStore_cd().get(0), "No");
                                                        editor.putString(CommonString1.KEY_STOREVISITED_STATUS, "");
                                                        editor.putString(CommonString1.KEY_STORE_IN_TIME, "");
                                                        editor.putString(CommonString1.KEY_LATITUDE, "");
                                                        editor.putString(CommonString1.KEY_LONGITUDE, "");
                                                        editor.commit();

                                                        finish();
                                                    }


//												database.updateStoreStatusOnCheckout(
//														store_id, visit_date,
//														CommonString1.KEY_L);

												/*Intent intent = new Intent(
                                                        getApplicationContext(),
														DailyEntryScreen.class);
												startActivity(intent);*/
                                                    // finish();
                                                }
                                            })
                                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                        public void onClick(
                                                DialogInterface dialog,
                                                int id) {
                                            dialog.cancel();
                                        }
                                    });

                            alert = builder.create();
                            alert.show();
                        } else {
                            AlertandMessages.showToastMsg(context, "Data has been uploaded for some stores, please select another reason");
                        }
                    } else {
                        AlertandMessages.showToastMsg(context, "Please enter required remark reason");
                    }
                } else {
                    AlertandMessages.showToastMsg(context, "Please Capture Image");
                }
            } else {
                AlertandMessages.showToastMsg(context, "Please Select a Reason and Fill Inform To");
            }
        }

    }

    public boolean validatedata() {
        boolean result = false;
        if (reasonid != null && !reasonid.equalsIgnoreCase("") && !reasonid.equalsIgnoreCase("0")) {
            result = true;
        }
        if (informTo.getText().toString().equalsIgnoreCase("") || informTo.getText().toString() == null) {
            result = false;
        }
        if (reasonid.equalsIgnoreCase("7") && merNotAllowedSpinner.getSelectedItemPosition() == 0) {
            result = false;
        }
        return result;
    }

    public String getCurrentTime() {
        Calendar m_cal = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        return formatter.format(m_cal.getTime());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // NavUtils.navigateUpFromSameTask(this);
            finish();
            overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
        }
        return super.onOptionsItemSelected(item);
    }

    void declaration() {
        context = this;
        reasonspinner = (Spinner) findViewById(R.id.spinner2);
        merNotAllowedSpinner = (Spinner) findViewById(R.id.merNotallowdspinner);
        camera = (ImageButton) findViewById(R.id.imgcam);
        save = (Button) findViewById(R.id.save);
        text = (EditText) findViewById(R.id.reasontxt);
        informTo = (EditText) findViewById(R.id.edit_informto);
        reason_lay = (RelativeLayout) findViewById(R.id.layout_reason);
        rel_cam = (RelativeLayout) findViewById(R.id.relimgcam);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        database = new GSKDatabase(context);
        database.open();
    }
}

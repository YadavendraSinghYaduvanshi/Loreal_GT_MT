package com.cpm.dailyentry;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.cpm.Constants.CommonString;
import com.cpm.Constants.CommonString1;
import com.cpm.capitalfoods.R;
import com.cpm.database.GSKDatabase;
import com.cpm.delegates.CoverageBean;
import com.cpm.upload.UploadPreviousStoreDataActivity;
import com.cpm.utilities.CommonFunctions;
import com.cpm.xmlGetterSetter.JourneyPlanGetterSetter;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by ashishc on 31-05-2016.
 */
public class StoreimageActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {


    ImageView img_cam, img_clicked;
    Button btn_save;
    String _pathforcheck, _path, str;
    String store_cd, visit_date, username, intime, date, channel_cd = "0";
    private SharedPreferences preferences;
    Context context;
    AlertDialog alert;
    String img_str;
    private GSKDatabase database;
    String lat = "0.0", lon = "0.0";
    JourneyPlanGetterSetter jcpGetSet;
    GoogleApiClient mGoogleApiClient;
    String metadata;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storeimage);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context = this;

        if (getIntent().getSerializableExtra(CommonString.TAG_OBJECT) != null) {
            jcpGetSet = (JourneyPlanGetterSetter) getIntent().getSerializableExtra(CommonString.TAG_OBJECT);
        }
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        img_cam = (ImageView) findViewById(R.id.img_selfie);
        img_clicked = (ImageView) findViewById(R.id.img_cam_selfie);
        btn_save = (Button) findViewById(R.id.btn_save_selfie);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        store_cd = preferences.getString(CommonString1.KEY_STORE_CD, "");
        channel_cd = preferences.getString(CommonString1.KEY_CHANNEL_CD, "");
        visit_date = preferences.getString(CommonString1.KEY_DATE, "");
        date = preferences.getString(CommonString1.KEY_DATE, "");
        username = preferences.getString(CommonString1.KEY_USERNAME, "");
        metadata = preferences.getString(CommonString1.KEY_META_DATA, "");

        str = CommonString1.FILE_PATH;

        database = new GSKDatabase(context);
        database.open();

        img_cam.setOnClickListener(this);
        img_clicked.setOnClickListener(this);
        btn_save.setOnClickListener(this);

        // Create an instance of GoogleAPIClient.
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

        if (Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        }
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

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();

        switch (id) {

            case R.id.img_cam_selfie:

                _pathforcheck = store_cd + "Store" + "Image" + visit_date.replace("/", "") + getCurrentTime().replace(":", "") + ".jpg";
                _path = CommonString1.FILE_PATH + _pathforcheck;
                //startCameraActivity();
                CommonFunctions.startAnncaCameraActivity(context, _path);
                //CommonFunctions.startMaterialCameraActivity((Activity) context, _pathforcheck, CommonString1.FILE_PATH);

                break;

            case R.id.btn_save_selfie:

                if (img_str != null) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("Do you want to save the data ")
                            .setCancelable(false)
                            .setPositiveButton("OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(
                                                DialogInterface dialog,
                                                int id) {

                                            alert.getButton(
                                                    AlertDialog.BUTTON_POSITIVE)
                                                    .setEnabled(false);

                                            CoverageBean cdata = new CoverageBean();
                                            cdata.setStoreId(store_cd);
                                            cdata.setVisitDate(visit_date);
                                            cdata.setUserId(username);
                                            cdata.setChannel_cd(jcpGetSet.getCHANNEL_CD().get(0));
                                            cdata.setInTime("00:00:00");
                                            cdata.setOutTime("00:00:00");
                                            cdata.setReason("");
                                            cdata.setReasonid("0");
                                            cdata.setLatitude(lat);
                                            cdata.setLongitude(lon);
                                            cdata.setImage(img_str);
                                            // cdata.setImage01("");
                                            cdata.setRemark("");
                                            cdata.setStatus(CommonString1.KEY_CHECK_IN);


                                            Intent intent = new Intent(context, UploadPreviousStoreDataActivity.class);
                                            intent.putExtra(CommonString.TAG_INTENT_DATA, "createCoverage");
                                            intent.putExtra(CommonString.TAG_CHANNEL_CD, channel_cd);
                                            intent.putExtra(CommonString.TAG_OBJECT, cdata);
                                            startActivity(intent);

                                            finish();
                                        }
                                    })
                            .setNegativeButton("Cancel",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(
                                                DialogInterface dialog,
                                                int id) {
                                            dialog.cancel();
                                        }
                                    });

                    alert = builder.create();
                    alert.show();

                } else {
                    Toast.makeText(getApplicationContext(),
                            "Please click the image", Toast.LENGTH_SHORT).show();

                }
                ;

                break;

        }

    }

    protected void startCameraActivity() {

        try {
            Log.i("MakeMachine", "startCameraActivity()");
            File file = new File(_path);
            Uri outputFileUri = Uri.fromFile(file);

            Intent intent = new Intent(
                    MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);

            startActivityForResult(intent, 0);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Log.i("MakeMachine", "resultCode: " + resultCode);
        switch (resultCode) {
            case 0:
                Log.i("MakeMachine", "User cancelled");
                break;

            case -1:

                if (_pathforcheck != null && !_pathforcheck.equals("")) {
                    if (new File(str + _pathforcheck).exists()) {
                        // Decode the filepath with BitmapFactory followed by the position
                        //Bitmap bmp = BitmapFactory.decodeFile(str + _pathforcheck);

                        String metadata = CommonFunctions.setMetadataAtImages(jcpGetSet, "Store Image", username);
                        Bitmap bmp = CommonFunctions.addMetadataAndTimeStampToImage(context, _path, metadata);

                        //Bitmap bmp = convertBitmap(str + _pathforcheck);
                        // Set the decoded bitmap into ImageView
                        img_cam.setImageBitmap(bmp);

                        img_clicked.setVisibility(View.GONE);
                        img_cam.setVisibility(View.VISIBLE);

                        //Set Clicked image to Imageview
                        img_str = _pathforcheck;
                        _pathforcheck = "";
                    }
                }

                break;
        }


        super.onActivityResult(requestCode, resultCode, data);
    }

 /*   public static Bitmap convertBitmap(String path) {
        Bitmap bitmap = null;
        BitmapFactory.Options ourOptions = new BitmapFactory.Options();
        ourOptions.inDither = false;
        ourOptions.inPurgeable = true;
        ourOptions.inInputShareable = true;
        ourOptions.inTempStorage = new byte[32 * 1024];
        File file = new File(path);
        FileInputStream fs = null;
        try {
            fs = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            if (fs != null) {
                bitmap = BitmapFactory.decodeFileDescriptor(fs.getFD(), null, ourOptions);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fs != null) {
                try {
                    fs.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bitmap;
    }*/


    public String getCurrentTime() {

        Calendar m_cal = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        return formatter.format(m_cal.getTime());

    }

    @Override
    public void onConnected(Bundle bundle) {

        Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {
            lat = String.valueOf(mLastLocation.getLatitude());
            lon = String.valueOf(mLastLocation.getLongitude());
        }


    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }


}

package com.cpm.geotag;



/*
public class LocationActivity 

{}*/


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.cpm.Constants.CommonString1;
import com.cpm.Constants.TMSCommonString;
import com.cpm.GetterSetter.Storenamebean;
import com.cpm.capitalfoods.R;
import com.cpm.database.GSKDatabase;
import com.cpm.utilities.CommonFunctions;
import com.cpm.xmlGetterSetter.JourneyPlanGetterSetter;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.File;
import java.util.ArrayList;


public class LocationActivity extends AppCompatActivity implements OnMapReadyCallback, ConnectionCallbacks, OnConnectionFailedListener, LocationListener {

    private static final String TAG = "LocationActivity";
    protected static final String PHOTO_TAKEN = "photo_taken";
    LocationManager locationManager;
    Geocoder geocoder;
    protected Button _buttonsave;
    public Camera camera;
    File file;
    protected ImageView _image;
    //MapController mapController;
    //GeoPoint point;
    protected boolean _taken;
    Button capture_1;
    RelativeLayout maplayout;
    public String text;
    public View view;
    GeotaggingBeans data = new GeotaggingBeans();
    private LocationManager locmanager = null;
    protected String diskpath = "";
    protected String _path;
    boolean enabled;
    protected String _pathforcheck = "";
    public static ArrayList<Storenamebean> storedetails = new ArrayList<>();
    public static ArrayList<JourneyPlanGetterSetter> journeyPlanList = new ArrayList<>();

    //String storename;
    String storeid;
    String status;
    //String storeaddress = "";
    Context context;
    protected int resultCode;
    int abc;
    private GoogleMap mMap;
    double lat = 0.0;
    double longitude = 0.0;
    private GoogleApiClient mGoogleApiClient;
    LocationRequest mLocationRequest;
    String store_cd;
    String visit_date;
    String username, metadata_global;
    Marker currLocationMarker;
    LatLng latLng;
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 1000;
    private static int UPDATE_INTERVAL = 1000; // 10 sec
    private static int FATEST_INTERVAL = 500; // 5 sec
    private static int DISPLACEMENT = 10; // 10 meters
    Location mLastLocation;
    private SharedPreferences preferences = null;
    String currentdate;
    boolean from_storeList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gpslocationscreen);
        context = this;
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        currentdate = preferences.getString(TMSCommonString.KEY_ISD_DATE, "");

        storeid = preferences.getString(CommonString1.KEY_STORE_CD, null);
        visit_date = preferences.getString(CommonString1.KEY_DATE, null);
        username = preferences.getString(CommonString1.KEY_USERNAME, null);
        metadata_global = preferences.getString(CommonString1.KEY_META_DATA, "");
        _image = (ImageView) findViewById(R.id.image);

        _buttonsave = (Button) findViewById(R.id.savedetails);
        capture_1 = (Button) findViewById(R.id.StoreFront);
        maplayout = (RelativeLayout) findViewById(R.id.maplayout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapview);
        mapFragment.getMapAsync(this);
        from_storeList = getIntent().getBooleanExtra(CommonString1.KEY_FROM_STORELIST, true);

        GSKDatabase data1 = new GSKDatabase(getApplicationContext());
        data1.open();
        if (from_storeList) {
            maplayout.setVisibility(View.GONE);
            storeid = getIntent().getStringExtra(CommonString1.KEY_STORE_CD);
            journeyPlanList = data1.getJCPData(visit_date);
        } else {
            maplayout.setVisibility(View.VISIBLE);
            storeid = getIntent().getStringExtra(CommonString1.KEY_STORE_CD);
        }

        storedetails = new ArrayList<>();
        locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
        geocoder = new Geocoder(context);

        if (checkPlayServices()) {
            // Building the GoogleApi client
            buildGoogleApiClient();
            createLocationRequest();
        }

        _pathforcheck = storeid + "_" + visit_date.replace("/", "_") + "GeoTag.jpg";

        _buttonsave.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub

                if (_pathforcheck != null) {

                    if (ImageUploadActivity.CheckGeotagImage(_pathforcheck)) {
                        status = "Y";
                        GSKDatabase data = new GSKDatabase(getApplicationContext());
                        data.open();
                        //data.updateGeoTagStatus(storeid, status, lat, longitude);
                        data.updateOutTime(status, store_cd, visit_date);
                        data.InsertStoregeotagging(storeid, lat, longitude, _pathforcheck, status);
                        data.close();

                        if (isNetworkOnline() == true) {
                            Intent intent2 = new Intent(context, UploadGeotaggingActivity.class);
                            startActivity(intent2);
                            LocationActivity.this.finish();
                        }
                    } else {

                        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                        builder.setMessage("Please take Store Front image")
                                .setCancelable(false)
                                .setPositiveButton("OK",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(
                                                    DialogInterface dialog, int id) {
                                                dialog.cancel();
                                            }
                                        });
                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setMessage("Please take Store Front image")
                            .setCancelable(false)
                            .setPositiveButton("OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(
                                                DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            }


        });

        capture_1.setOnClickListener(new ButtonClickHandler());
        locmanager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        enabled = locmanager.isProviderEnabled(LocationManager.GPS_PROVIDER);


        if (!enabled) {

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                    LocationActivity.this);
            // Setting Dialog Title
            alertDialog.setTitle("GPS IS DISABLED...");
            // Setting Dialog Message
            alertDialog.setMessage("Click ok to enable GPS.");
            // Setting Positive "Yes" Button
            alertDialog.setPositiveButton("YES",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            Intent intent = new Intent(
                                    Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            startActivity(intent);
                        }
                    });

            // Setting Negative "NO" Button
            alertDialog.setNegativeButton("NO",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Write your code here to invoke NO event
                            dialog.cancel();
                        }
                    });
            // Showing Alert Message
            alertDialog.show();

        }


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


    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        if (ImageUploadActivity.CheckGeotagImage(_pathforcheck)) {
            capture_1.setBackgroundResource(R.drawable.camera_done);
        }
    }


    protected void startCameraActivity() {
        Log.i("MakeMachine", "startCameraActivity()");
        file = new File(diskpath);
        Uri outputFileUri = Uri.fromFile(file);

        Intent intent = new Intent(
                MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
        startActivityForResult(intent, 0);

    }

    public boolean isNetworkOnline() {
        boolean status = false;
        try {
            ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getNetworkInfo(0);
            if (netInfo != null
                    && netInfo.getState() == NetworkInfo.State.CONNECTED) {
                status = true;
            } else {
                netInfo = cm.getNetworkInfo(1);
                if (netInfo != null
                        && netInfo.getState() == NetworkInfo.State.CONNECTED)
                    status = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return status;

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i("MakeMachine", "resultCode: " + resultCode);
        switch (resultCode) {
            case 0:
                Log.i("MakeMachine", "User cancelled");
                break;

            case -1:
                onPhotoTaken();
                if (ImageUploadActivity.CheckGeotagImage(_pathforcheck)) {
                    capture_1.setBackgroundResource(R.drawable.camera_done);
                    String metadata = CommonFunctions.getMetadataAtImagesFromPref("Store Id : " + storeid + " | User Id : " + username + "", "GeoTag Image");
                    CommonFunctions.addMetadataAndTimeStampToImage(context, diskpath, metadata);
                }
                break;
        }
    }

    protected void onPhotoTaken() {

        Log.i("MakeMachine", "onPhotoTaken");
        _taken = true;

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 8;
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.i("MakeMachine", "onRestoreInstanceState()");
        if (savedInstanceState.getBoolean(PHOTO_TAKEN)) {
            onPhotoTaken();
            if (ImageUploadActivity.CheckGeotagImage(_pathforcheck)) {

                capture_1.setBackgroundResource(R.drawable.camera);

            }
        }
        if (ImageUploadActivity.CheckGeotagImage(_pathforcheck)) {

            capture_1.setBackgroundResource(R.drawable.camera);

        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(PHOTO_TAKEN, _taken);
    }


    public class ButtonClickHandler implements OnClickListener {
        public void onClick(View view) {


            if (lat != 0.0 && longitude != 0.0) {

                if (view.getId() == R.id.StoreFront) {
                    diskpath = "";
                    diskpath = CommonString1.FILE_PATH + storeid + "_" + visit_date.replace("/", "_") + "GeoTag.jpg";
                    _path = storeid + "_" + visit_date.replace("/", "_") + "GeoTag.jpg";
                    abc = 3;
                    CommonFunctions.startAnncaCameraActivity(context, diskpath);
                    //startCameraActivity();
                    //CommonFunctions.startMaterialCameraActivity((Activity) context, _path, CommonString1.FILE_PATH);

                }

            } else if (lat == 0.0 && longitude == 0.0) {
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        view.getContext());

                builder.setMessage("Wait For Geo Location")
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int id) {
                                        dialog.cancel();
                                    }
                                });
                AlertDialog alert = builder.create();
                alert.show();

            } else {
                if (view.getId() == R.id.StoreFront) {
                    diskpath = "";
                    diskpath = CommonString1.FILE_PATH + storeid + "_" + visit_date.replace("/", "_") + "GeoTag.jpg";
                    _path = storeid + "_" + visit_date.replace("/", "_") + "GeoTag.jpg";
                    abc = 3;
                    //startCameraActivity();
                    CommonFunctions.startAnncaCameraActivity(context, diskpath);
                    //CommonFunctions.startMaterialCameraActivity((Activity) context, _path, CommonString1.FILE_PATH);
                }
            }

        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // TODO Auto-generated method stub
        mMap = googleMap;
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getMaxZoomLevel();
        mMap.getMinZoomLevel();
        mMap.getUiSettings();
        mMap.animateCamera(CameraUpdateFactory.zoomIn());
        mMap.animateCamera(CameraUpdateFactory.zoomOut());
        mMap.animateCamera(CameraUpdateFactory.zoomTo(20));
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult arg0) {
        // TODO Auto-generated method stub
    }


    public void onConnected(Bundle bundle) {


        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {
            //place marker at current position
            if (from_storeList) {
                if (journeyPlanList.size() > 0) {

                    for (int i = 0; i < journeyPlanList.size(); i++) {
                        if (journeyPlanList.get(i).getStore_cd().get(0).equalsIgnoreCase(storeid)) {
                            latLng = new LatLng(Double.parseDouble(journeyPlanList.get(i).getLATTITUDE().get(0)), Double.parseDouble(journeyPlanList.get(i).getLONGITUDE().get(0)));
                            break;
                        }
                    }
                }
            } else {
                latLng = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
                lat = mLastLocation.getLatitude();
                longitude = mLastLocation.getLongitude();
            }
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLng);
            markerOptions.title("Current Position");
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
            currLocationMarker = mMap.addMarker(markerOptions);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));

        }

        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(5000); //5 seconds
        mLocationRequest.setFastestInterval(3000); //3 seconds
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
    }


    @Override
    public void onConnectionSuspended(int arg0) {
        // TODO Auto-generated method stub

    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(LocationServices.API).build();
    }


    @Override
    protected void onPause() {
        super.onPause();
        stopLocationUpdates();
    }

    protected void stopLocationUpdates() {
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }

    }

    @Override
    public void onLocationChanged(Location location) {
        // TODO Auto-generated method stub

        mLastLocation = location;

    }

    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil
                .isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Toast.makeText(getApplicationContext(),
                        "This device is not supported.", Toast.LENGTH_LONG)
                        .show();
                finish();
            }
            return false;
        }
        return true;
    }


    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(UPDATE_INTERVAL);
        mLocationRequest.setFastestInterval(FATEST_INTERVAL);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setSmallestDisplacement(DISPLACEMENT);
    }

    protected void onStart() {
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }
        super.onStart();
    }

    protected void onStop() {
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
        super.onStop();
    }


    public void onBackPressed() {
        // TODO Auto-generated method stub
        LocationActivity.this.finish();
    }


}
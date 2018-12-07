package com.cpm.dailyentry;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.cpm.capitalfoods.R;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by ashishc on 05-09-2016.
 */
public class GeoTagActivity extends AppCompatActivity implements OnMapReadyCallback,ConnectionCallbacks, OnConnectionFailedListener, LocationListener {
    private GoogleApiClient mGoogleApiClient;
    LocationManager locationManager;
    Geocoder geocoder;
    Location mLastLocation;
    LocationRequest mLocationRequest;
    boolean enabled;

    Marker currLocationMarker;
    LatLng latLng;
    private static int UPDATE_INTERVAL = 1000; // 10 sec
    private static int FATEST_INTERVAL = 500; // 5 sec
    private static int DISPLACEMENT = 10; // 10 meters
    private LocationManager locmanager = null;
    private GoogleMap  mMap;
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 1000;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.geotaglayout);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapview);
        mapFragment.getMapAsync(this);

        locationManager = (LocationManager) this
                .getSystemService(LOCATION_SERVICE);
        geocoder = new Geocoder(this);
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);


        if (checkPlayServices()) {

            // Building the GoogleApi client
            buildGoogleApiClient();

            createLocationRequest();
        }

        locmanager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        enabled = locmanager.isProviderEnabled(LocationManager.GPS_PROVIDER);


        if (!enabled) {

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                    GeoTagActivity.this);

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


    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(LocationServices.API).build();
    }

    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(UPDATE_INTERVAL);
        mLocationRequest.setFastestInterval(FATEST_INTERVAL);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setSmallestDisplacement(DISPLACEMENT);
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



        // buildGoogleApiClient();

        // mGoogleApiClient.connect();




    }
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult arg0) {
        // TODO Auto-generated method stub

    }

    public void onConnected(Bundle bundle) {



        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        if (mLastLocation != null) {
            //place marker at current position
            //mGoogleMap.clear();
            latLng = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLng);

            markerOptions.title("Position");
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
            currLocationMarker = mMap.addMarker(markerOptions);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));

        }

        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(5000); //5 seconds
        mLocationRequest.setFastestInterval(3000); //3 seconds
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        //mLocationRequest.setSmallestDisplacement(0.1F); //1/10 meter

        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);




    }
    @Override
    public void onConnectionSuspended(int arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    protected void onPause() {
        super.onPause();
        stopLocationUpdates();
    }

    protected void stopLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(
                mGoogleApiClient, this);
    }

    protected void startLocationUpdates() {

        LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient, mLocationRequest, this);

    }

    @Override
    public void onLocationChanged(Location location) {
        // TODO Auto-generated method stub

        mLastLocation = location;

    }
}

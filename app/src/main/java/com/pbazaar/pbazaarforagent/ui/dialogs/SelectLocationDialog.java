package com.pbazaar.pbazaarforagent.ui.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.pbazaar.pbazaarforagent.R;
import com.pbazaar.pbazaarforagent.helper.Constants;

import butterknife.ButterKnife;

/**
 * Created by supto on 11/11/17.
 */

public class SelectLocationDialog extends DialogFragment implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    public static final String TAG = SelectLocationDialog.class.getSimpleName();
    private static final int MY_PERMISSIONS_REQUEST_CODE = 420;
    private GoogleMap map;
    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    private Location lastLocation;

    public SelectLocationDialog() {
    }

    public static SelectLocationDialog newInstance() {
        SelectLocationDialog dialog = new SelectLocationDialog();
        return dialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.dialog_select_location, null);

        ButterKnife.bind(this, view);


        SupportMapFragment mapFragment = (SupportMapFragment) getActivity().getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        //initialize googleApiClient and location request
        if (googleApiClient == null) {
            googleApiClient = new GoogleApiClient.Builder(getActivity())
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
        locationRequest = new LocationRequest();
        locationRequest.setInterval(1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        requestToEnableLocationIfDisabled();


        AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .setView(view)
                .setCancelable(false)
                .create();

        return alertDialog;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
    }

    public void onLocationChanged(Location location) {
        // if there is a location then zoom the map there
        // and remove the listener

        if (location != null) {
            Log.d(TAG, "Location found");
            LatLng currentLatLng = new LatLng(location.getLatitude(), location.getLongitude());

            map.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, Constants.MAP_ZOOM_FACTOR));
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this);
        }
    }

    private void requestForLocationAndShowInMap() {
        //check uses permission
        if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            requestPermission();

            return;
        }

        //get the last known location
        lastLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);

        // if there is a location then zoom the map there
        if (lastLocation != null) {
            LatLng currentLatLng = new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude());
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, Constants.MAP_ZOOM_FACTOR));
        } else {
            // if last location not found then
            // initialize location change listener
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, SelectLocationDialog.this);
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        googleApiClient.connect();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, SelectLocationDialog.this);
        googleApiClient.disconnect();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CODE: {

                boolean status = true;
                Log.d(TAG, "statussize : " + grantResults.length);
                for (int reult : grantResults) {

                    Log.d(TAG, "status: " + reult);

                    if (reult != PackageManager.PERMISSION_GRANTED) {
                        status = false;
                        break;
                    }
                }

                if (status) {

                    Log.d(TAG, "permisiion granted");
                    requestForLocationAndShowInMap();

                }
            }
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        requestForLocationAndShowInMap();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private void requestToEnableLocationIfDisabled() {
        // request for location service enabling
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(LocationRequest.create());

        builder.setAlwaysShow(true);

        PendingResult<LocationSettingsResult> result =
                LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());
        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(LocationSettingsResult result) {
                final Status status = result.getStatus();
                final LocationSettingsStates state = result.getLocationSettingsStates();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        // All location settings are satisfied.

                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        // Location settings are not satisfied. But could be fixed by showing the user
                        // a dialog.
                        try {
                            // Show the dialog by calling startResolutionForResult(),
                            // and check the result in onActivityResult().
                            status.startResolutionForResult(
                                    getActivity(), 1000);

                        } catch (IntentSender.SendIntentException e) {
                            // Ignore the error.
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        // Location settings are not satisfied. However, we have no way to fix the
                        // settings so we won't show the dialog.

                        break;
                }
            }
        });
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (getActivity().checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED ||
                    getActivity().checkSelfPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION)
                            != PackageManager.PERMISSION_GRANTED) {

                requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION
                                , android.Manifest.permission.ACCESS_COARSE_LOCATION}
                        , MY_PERMISSIONS_REQUEST_CODE);
            }
        }
    }
}

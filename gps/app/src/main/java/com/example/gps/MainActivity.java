package com.example.gps;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final long minupdotedistance = 100; //in meter
    private static final long minupdatetime = 1000; //in millisecond

    private LocationManager locationManager;

    private Button buncurloc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buncurloc = findViewById(R.id.buncurloc);


        Log.d("imp", "IMO");

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

//        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
//                != PackageManager.PERMISSION_GRANTED &&
//                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
//                        !=PackageManager.PERMISSION_GRANTED) {
//            return;
//        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                minupdatetime,
                minupdotedistance,
                new MyLocationListner()
        );

        buncurloc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("imp", "HELLO");
                // Toast.makeText(MainActivity.this, "HELLO", Toast.LENGTH_SHORT).show();
                showCurrentLocation();
            }
        });
    }


    public Location get() {

        List<String> providers = locationManager.getProviders(true);
        Location bestLocation = null;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return bestLocation;
        }

        for (String provider : providers) {


            Location l = locationManager.getLastKnownLocation(provider);


            if (l == null) {
                continue;
            }
            if (bestLocation == null
                    || l.getAccuracy() < bestLocation.getAccuracy()) {

                bestLocation = l;
            }
        }
        if (bestLocation == null) {
            return null;
        }
        return bestLocation;
    }



    protected void showCurrentLocation(){

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
            return;
        }

        Location location = get();

        if(location != null){
            String message = String.format("Current Location \n Longitude : %1$s \n Latitude : %2$s",
                    location.getLongitude(),location.getLatitude());

            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }

    }


    public class MyLocationListner implements LocationListener {
        @Override
        public void onLocationChanged(@NonNull Location location) {

            String message = String.format("Change to New Location \n Longitude : %1$s \n Latitude : %2$s", location.getLongitude(), location.getLatitude());

            Toast.makeText(MainActivity.this,message, Toast.LENGTH_SHORT).show();

        }

        public void onStatusChanged(String s, int i, Bundle b){
            Toast.makeText(MainActivity.this,"GPS Provider Status", Toast.LENGTH_SHORT).show();
        }

        public void onProviderDisabled(String s){
            Toast.makeText(MainActivity.this, "GPS Turned Off", Toast.LENGTH_SHORT).show();
        }

        public void onProviderEnabled(String s){
            Toast.makeText(MainActivity.this, "GPS Turned On", Toast.LENGTH_SHORT).show();
        }

    }

}
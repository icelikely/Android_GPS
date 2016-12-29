package com.android_gps;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private LocationManager locationManager;
    private TextView textview1;
    private LocationListener myLocationListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LocationManager locationManager=(LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location location = null;
        try {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                    1000, 0, locationListener);
            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        } catch (Exception e) {
            e.printStackTrace();
        }
        double latitude = location.getLatitude();     //经度
        double longitude = location.getLongitude(); //纬度
        double altitude =  location.getAltitude();     //海拔
        TextView textView = (TextView) findViewById(R.id.textview1);
        textView.setText(latitude+":"+longitude);


    }
    private final LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location) { //当坐标改变时触发此函数，如果Provider传进相同的坐标，它就不会被触发
            // log it when the location changes
            if (location != null) {
                System.out.println( "Location changed : Lat: "
                        + location.getLatitude() + " Lng: "
                        + location.getLongitude());
            }
        }
        public void onProviderDisabled(String provider) {
            // Provider被disable时触发此函数，比如GPS被关闭
        }
        public void onProviderEnabled(String provider) {
            //  Provider被enable时触发此函数，比如GPS被打开
        }
        public void onStatusChanged(String provider, int status, Bundle extras) {
            // Provider的转态在可用、暂时不可用和无服务三个状态直接切换时触发此函数
        }
    };
}




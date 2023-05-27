package com.example.test;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.PointF;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.geometry.LatLngBounds;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.NaverMapOptions;
import com.naver.maps.map.NaverMapSdk;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.OverlayImage;

import java.io.IOException;
import java.util.List;

public class Maps extends AppCompatActivity implements OnMapReadyCallback {

    private static NaverMap naverMap;
    public static double cur_lat;
    public static double cur_lon;
    public static Marker dymark;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        dymark = new Marker();

        //cur_lat과 cur_lon에 각각 위도 경도 값을 할당해줌
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location loc_current = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        cur_lat = loc_current.getLatitude();
        cur_lon = loc_current.getLongitude();




        FragmentManager fm = getSupportFragmentManager();
        MapFragment mapFragment = (MapFragment) fm.findFragmentById(R.id.map);
        if (mapFragment == null) {
            mapFragment = MapFragment.newInstance();
            fm.beginTransaction().add(R.id.map, mapFragment).commit();
        }

        mapFragment.getMapAsync(this);





        //지도 생성
//        NaverMapSdk.getInstance(this).setClient(new NaverMapSdk.NaverCloudPlatformClient("1d5wouggea"));
//        MapView mapView = (MapView) findViewById(R.id.map);
//        mapView.onCreate(savedInstanceState);
//        mapView.getMapAsync(this);
//        Marker marker = new Marker();
//        marker.setPosition(new LatLng(cur_lat,cur_lon));
//        marker.setMap(naverMap);
//
//    }
//
//    @Override
//    public void onMapReady(@NonNull NaverMap naverMap) {
//        Maps.naverMap = naverMap;
//
//        Log.d("a",""+cur_lat+"\n"+cur_lon);
//        CameraPosition cameraPosition = new CameraPosition(
//                new LatLng(cur_lat,cur_lon),8
//        );
//        naverMap.setCameraPosition(cameraPosition);
//
    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {



        Geocoder geocoder = new Geocoder(this);
        Marker marker = new Marker();
        marker.setPosition(new LatLng(cur_lat, cur_lon));
        marker.setIcon(OverlayImage.fromResource(R.drawable.good_job));
        marker.setWidth(50);
        marker.setHeight(50);
        marker.setMap(naverMap);


        Log.d("a",""+cur_lat+"\n"+cur_lon);
        CameraPosition startposition = new CameraPosition(
                new LatLng(cur_lat,cur_lon),12
        );
        naverMap.setCameraPosition(startposition);
        naverMap.setOnMapClickListener(new NaverMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull PointF pointF, @NonNull LatLng latLng) {
                Log.d("b",""+pointF + latLng.latitude+latLng.longitude);
                try {
                    List<Address> citylist = geocoder.getFromLocation(latLng.latitude,latLng.longitude,10);
                    dymark.setMap(null);
                    dymark.setPosition(new LatLng(latLng.latitude, latLng.longitude));
                    dymark.setIcon(OverlayImage.fromResource(R.drawable.good_job));
                    dymark.setWidth(50);
                    dymark.setHeight(50);
                    dymark.setMap(naverMap);

                    Log.d("tag",citylist.get(0).toString());

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        //지도 범위 제한
        naverMap.setExtent(new LatLngBounds(new LatLng(31.43, 122.37), new LatLng(44.35, 132)));
        //지도 줌 레벨 제한
        naverMap.setMinZoom(5.0);
        naverMap.setMaxZoom(18.0);
//        naverMap.addOnCameraChangeListener(new NaverMap.OnCameraChangeListener() {
//            @Override
//            public void onCameraChange(int i, boolean b) {
//                Log.d("navermap", "카메라 변경"+i+b);
//            }
//        });
        naverMap.addOnCameraIdleListener(new NaverMap.OnCameraIdleListener() {
            @Override
            public void onCameraIdle() {
                CameraPosition cameraPosition = naverMap.getCameraPosition();
                dymark.setMap(null);
                dymark.setPosition(new LatLng(
                        cameraPosition.target.latitude,
                        cameraPosition.target.longitude));
                dymark.setIcon(OverlayImage.fromResource(R.drawable.good_job));
                dymark.setWidth(50);
                dymark.setHeight(50);
                dymark.setMap(naverMap);
                double a = cameraPosition.zoom;
                Log.d("test","zoom level is : "+a);

                Log.d("navermap",cameraPosition.target.latitude + cameraPosition.target.longitude+"");
                try {
                    List<Address> citylist = geocoder.getFromLocation(cameraPosition.target.latitude,cameraPosition.target.longitude,10);
                    Log.d("geoloder",citylist.get(0).toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        naverMap.setOnSymbolClickListener(symbol -> {
            if ("부산대학교 밀양캠퍼스".equals(symbol.getCaption())) {
                Toast.makeText(this, "생자대 클릭", Toast.LENGTH_SHORT).show();
                // 이벤트 소비, OnMapClick 이벤트는 발생하지 않음
                return true;
            }
            // 이벤트 전파, OnMapClick 이벤트가 발생함
            return false;
        });



    }
}
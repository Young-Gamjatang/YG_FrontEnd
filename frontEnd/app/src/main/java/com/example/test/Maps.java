package com.example.test;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.graphics.PointF;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test.Retrofitmanager.BadHygieneRestaurantModel;
import com.example.test.Retrofitmanager.QualityRestaurantModel;
import com.example.test.Retrofitmanager.RetrofitInstance;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.geometry.LatLngBounds;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.NaverMapOptions;
import com.naver.maps.map.NaverMapSdk;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.Projection;
import com.naver.maps.map.Symbol;
import com.naver.maps.map.UiSettings;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.Overlay;
import com.naver.maps.map.overlay.OverlayImage;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Maps extends AppCompatActivity implements OnMapReadyCallback , Overlay.OnClickListener {

    private static NaverMap naverMap;
    public static double cur_lat;
    public static double cur_lon;
    public static Marker dymark;
    public static Marker[] goodmarkers;
    public static Marker[] badmarkers;
    public static String lastlocation;
    Call<List<QualityRestaurantModel>> call;
    Call<List<BadHygieneRestaurantModel>> badcall;
    List<QualityRestaurantModel> result =new ArrayList<>();
    List<BadHygieneRestaurantModel> badresult = new ArrayList<>();
    public static String inputText;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        dymark = new Marker();
        EditText editText = findViewById(R.id.edittext_serach);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // 텍스트 변경 이전에 수행할 작업
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // 텍스트 변경 중에 수행할 작업
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // 텍스트 변경 이후에 수행할 작업
                inputText = editText.getText().toString();

            }
        });


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


        TextView searchbtn = findViewById(R.id.searchbtn);


        ConstraintLayout marker_detail = findViewById(R.id.marker_deatil);
        ConstraintLayout upso_detail = findViewById(R.id.upso_deatil);
        //startposition에 cur_lat , cur_lon
        CameraPosition startposition = new CameraPosition(
                new LatLng(cur_lat,cur_lon),15
        );
        CameraPosition cameraPosition = naverMap.getCameraPosition();
        final int[] status = {0};

        Geocoder geocoder = new Geocoder(this);
        Marker marker = new Marker();

        marker.setPosition(new LatLng(cur_lat, cur_lon));
        marker.setIcon(OverlayImage.fromResource(R.drawable.good_job));
        marker.setWidth(50);
        marker.setHeight(50);
        marker.setMap(naverMap);

        //현재 위치로 버튼
        TextView mylocationbtn = findViewById(R.id.mylocationbtn);
        mylocationbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                naverMap.setCameraPosition(startposition);
            }
        });


        List<Address> citylist = null;
        try {
            citylist = geocoder.getFromLocation(cameraPosition.target.latitude,cameraPosition.target.longitude,10);
        } catch (IOException e) {
            e.printStackTrace();
        }
        lastlocation = citylist.get(0).getSubLocality();
        Log.d("yeye",""+citylist.get(0).getSubLocality());
        badcall = RetrofitInstance.getApiService().getwrongcggcode(citylist.get(0).getSubLocality());
        badcall.enqueue(new Callback<List<BadHygieneRestaurantModel>>() {
            @Override
            public void onResponse(Call<List<BadHygieneRestaurantModel>> call, Response<List<BadHygieneRestaurantModel>> response) {
                badresult = response.body();
                for(int i = 0 ; i < badresult.size(); i++)
                {
                    Marker marker = new Marker();
                    marker.setPosition(new LatLng(badresult.get(i).getLatitude(), badresult.get(i).getLongitude()));
                    marker.setIcon(OverlayImage.fromResource(R.drawable.mark_warning));
                    marker.setWidth(45);
                    marker.setHeight(66);
                    marker.setTag("warning");
                    marker.setCaptionTextSize(0);
                    marker.setOnClickListener(Maps.this);
                    marker.setMap(naverMap);
                }


            }

            @Override
            public void onFailure(Call<List<BadHygieneRestaurantModel>> call, Throwable t) {

            }
        });
        call = RetrofitInstance.getApiService().getCggcode(citylist.get(0).getSubLocality());
        /**citylist.get(0).getSubLocality()**/
        call.enqueue(new Callback<List<QualityRestaurantModel>>() {
            @Override
            public void onResponse(Call<List<QualityRestaurantModel>> call, Response<List<QualityRestaurantModel>> response) {

                result = response.body();

                for(int i = 0 ; i < result.size(); i++)
                {
                    Marker marker = new Marker();
                    marker.setPosition(new LatLng(result.get(i).getLatitude(), result.get(i).getLongitude()));
                    marker.setIcon(OverlayImage.fromResource(R.drawable.mark_mobum));
                    marker.setWidth(45);
                    marker.setHeight(66);
                    marker.setTag(result.get(i).getUpsoName()+"/"+result.get(i).getMainfood()+"/"+result.get(i).getSiteAddrRd());
                    marker.setCaptionText(result.get(i).getUpsoName());
                    marker.setCaptionTextSize(0);
                    marker.setOnClickListener(Maps.this);
                    marker.setMap(naverMap);
                }


            }

            @Override
            public void onFailure(Call<List<QualityRestaurantModel>> call, Throwable t) {

            }
        });




        Log.d("a",""+cur_lat+"\n"+cur_lon);

        naverMap.setCameraPosition(startposition);
        naverMap.setOnMapClickListener(new NaverMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull PointF pointF, @NonNull LatLng latLng) {

//                    if(marker_detail.getVisibility()==View.GONE)
//                    {
//                        marker_detail.setVisibility(View.VISIBLE);
//                        upso_detail.setVisibility(View.GONE);
//
//                    }
                }
        });
        //지도 범위 제한
        naverMap.setExtent(new LatLngBounds(new LatLng(37.413294, 126.734086), new LatLng(37.715133, 127.269311)));
        //지도 줌 레벨 제한
        naverMap.setMinZoom(15.0);
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
                List<Address> citylist = null;
                try {
                    citylist = geocoder.getFromLocation(cameraPosition.target.latitude,cameraPosition.target.longitude,10);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if(!Objects.equals(lastlocation, citylist.get(0).getSubLocality()))
                {
                    lastlocation = citylist.get(0).getSubLocality();
                    badcall = RetrofitInstance.getApiService().getwrongcggcode(citylist.get(0).getSubLocality());
                    badcall.enqueue(new Callback<List<BadHygieneRestaurantModel>>() {
                        @Override
                        public void onResponse(Call<List<BadHygieneRestaurantModel>> call, Response<List<BadHygieneRestaurantModel>> response) {
                            badresult = response.body();

                            if(badresult != null) {
                                for (int i = 0; i < badresult.size(); i++) {
                                    Marker marker = new Marker();

                                    marker.setPosition(new LatLng(badresult.get(i).getLatitude(), badresult.get(i).getLongitude()));
                                    marker.setIcon(OverlayImage.fromResource(R.drawable.mark_warning));
                                    marker.setWidth(45);
                                    marker.setHeight(66);
                                    marker.setCaptionText(badresult.get(i).getUpsoNm());
                                    marker.setCaptionTextSize(0);
                                    marker.setTag("warning");
                                    marker.setOnClickListener(Maps.this);
                                    marker.setMap(naverMap);
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<List<BadHygieneRestaurantModel>> call, Throwable t) {

                        }
                    });
                    call = RetrofitInstance.getApiService().getCggcode(citylist.get(0).getSubLocality());
                    call.enqueue(new Callback<List<QualityRestaurantModel>>() {
                                     @Override
                                     public void onResponse(Call<List<QualityRestaurantModel>> call, Response<List<QualityRestaurantModel>> response) {
                                         result = response.body();

                                         if(result != null) {
                                             for (int i = status[0]; i < status[0] + result.size(); i++) {
                                                 Marker marker = new Marker();
                                                 marker.setPosition(new LatLng(result.get(i).getLatitude(), result.get(i).getLongitude()));
                                                 marker.setIcon(OverlayImage.fromResource(R.drawable.mark_mobum));
                                                 marker.setWidth(45);
                                                 marker.setHeight(66);
                                                 marker.setTag(result.get(i).getUpsoName()+"/"+result.get(i).getMainfood()+"/"+result.get(i).getSiteAddrRd());
                                                 marker.setCaptionText(result.get(i).getUpsoName());
                                                 marker.setCaptionTextSize(0);
                                                 marker.setOnClickListener(Maps.this);
                                                 marker.setMap(naverMap);
                                             }
                                         }


                                     }

                                     @Override
                                     public void onFailure(Call<List<QualityRestaurantModel>> call, Throwable t) {

                                     }
                                 });
                }

                dymark.setMap(null);
                dymark.setPosition(new LatLng(
                        cameraPosition.target.latitude,
                        cameraPosition.target.longitude));
                dymark.setIcon(OverlayImage.fromResource(R.drawable.good_job));
                dymark.setWidth(50);
                dymark.setHeight(50);
                dymark.setMap(naverMap);

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
        //카메라 이동 감지 및 위도 경도 측정

        naverMap.setOnSymbolClickListener(new NaverMap.OnSymbolClickListener() {
            @Override
            public boolean onSymbolClick(@NonNull Symbol symbol) {
                Log.d("asdf",""+symbol.getPosition().longitude+symbol.getCaption());
                return false;
            }
        });

        naverMap.setContentPadding(0,DPtoPX(Maps.this,130),0,DPtoPX(Maps.this,200));
        // 입력 완료된 문자열을 이용하여 원하는 작업 수행
        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("tt",inputText);
                call = RetrofitInstance.getApiService().searchres(inputText);
                call.enqueue(new Callback<List<QualityRestaurantModel>>() {
                    @Override
                    public void onResponse(Call<List<QualityRestaurantModel>> call, Response<List<QualityRestaurantModel>> response) {
                        result = response.body();
                        Log.d("오예", result.get(0).toString());
                        CameraPosition startposition = new CameraPosition(
                                new LatLng(result.get(0).getLatitude(),result.get(0).getLongitude()),15
                        );
                        naverMap.setCameraPosition(startposition);


                        Marker marker;
                        marker = new Marker();
                        marker.setPosition(new LatLng(result.get(0).getLatitude(), result.get(0).getLongitude()));
                        marker.setIcon(OverlayImage.fromResource(R.drawable.mark_mobum));
                        marker.setWidth(45);
                        marker.setHeight(66);
                        marker.setTag(result.get(0).getUpsoName()+"/"+result.get(0).getMainfood()+"/"+result.get(0).getSiteAddrRd());
                        marker.setCaptionText(result.get(0).getUpsoName());
                        marker.setCaptionTextSize(0);
                        marker.setOnClickListener(Maps.this);
                        marker.setMap(naverMap);
                    }

                    @Override
                    public void onFailure(Call<List<QualityRestaurantModel>> call, Throwable t) {
                        // 요청 실패 시 수행할 작업
                    }
                });
            }
        });




    }
    public static int DPtoPX(Context context, int dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return Math.round((float) dp * density);
    }

    @Override
    public boolean onClick(@NonNull Overlay overlay) {
        if (overlay instanceof Marker)
        {
            ConstraintLayout marker_detail = findViewById(R.id.marker_deatil);
            ConstraintLayout upso_detail = findViewById(R.id.upso_deatil);
            TextView markimg = findViewById(R.id.markimg);
            TextView text_title = findViewById(R.id.text_title);
            TextView text_address = findViewById(R.id.text_address);
            TextView text_category = findViewById(R.id.text_category);
            TextView text_mainfood = findViewById(R.id.text_mainfood);
            TextView text_punish = findViewById(R.id.punish_why);


            marker_detail.setVisibility(View.GONE);
            upso_detail.setVisibility(View.VISIBLE);




            if(((Marker) overlay).getIcon().equals(OverlayImage.fromResource(R.drawable.mark_mobum)))
            {
                text_title.setText("");
                text_mainfood.setText("");
                text_address.setText("");
                text_punish.setText("");

                markimg.setBackground(getDrawable(R.drawable.mark_mobum));


                String temp = overlay.getTag().toString();
                String[] detail = temp.split("/");
                if(!detail[0].equals("null"))
                {
                    text_title.setText(detail[0]);
                }
                if(!detail[1].equals("null"))
                {
                    text_mainfood.setText(detail[1]);
                }
                if(!detail[2].equals("null"))
                {
                    text_address.setText(detail[2]);
                }

                Log.d("aaaaaaa",""+detail[0]+"{{"+detail[1]+"{{"+detail[2]);



                text_category.setText("모범업소");


            }
            if(((Marker) overlay).getIcon().equals(OverlayImage.fromResource(R.drawable.mark_warning)))
            {

                text_title.setText("");
                text_mainfood.setText("");
                text_address.setText("");
                text_punish.setText("");

                markimg.setBackground(getDrawable(R.drawable.mark_warning));
                String temp = overlay.getTag().toString();
                String[] detail = temp.split("/");
                if(!detail[0].equals("null"))
                {
                    text_title.setText(detail[0]);
                }
                if(!detail[1].equals("null"))
                {
                    text_mainfood.setText(detail[1]);
                }
                if(!detail[2].equals("null"))
                {
                    text_address.setText(detail[2]);
                }

                Log.d("aaaaaaa",""+detail[0]+"{{"+detail[1]+"{{"+detail[2]);

                Log.d("bbbbbb",((Marker) overlay).getCaptionText());
            }
        }
        return false;
    }
}
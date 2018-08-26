package com.caniwalk.idu.caniwalk;

import android.Manifest;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.ViewGroup;

import com.caniwalk.idu.caniwalk.gps.GPS;

import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Create Date: 2018-08-20
 * Created by: kil kyungwan
 * 기초 다음 api 연동 맵 액티비티 create
 * ------------------------
 * Edit Date: 2018-08-21
 * Edited by: Kil kyungwan
 * 이전의 잘못된 다음 target package를 현재의 패키지로 변경.
 * -------------------------
 * Edit Date:2018-08-26
 * Edited by: Kil kyungwan
 * 맵 검색을 위한 edit text 추가.(V)
 * GPS 권한 얻기/얻지 못하면 강제 종료 시키기(X)->권한은 받지만, 현재 위도 경도를 받지 못한다.
 * 맵 시작을 GPS 롤 통해 현재 위치를 가져오도록 한다.(X)->권한은 받지만, 현재 위도 경도를 받지 못한다.
 * 부적절한 네이밍 변경.(V)
 * <p>
 * 최상단에 그림.
 */
public class MainActivity extends AppCompatActivity {
    final String TAG = "MainActivity";
    private final int PERMISSIONS_ACCESS_FINE_LOCATION = 1000;
    private final int PERMISSIONS_ACCESS_COARSE_LOCATION = 1001;

    boolean isPermission = false;
    GPS gps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (!isPermission) {
            callPermission();
        }
        gps = new GPS(MainActivity.this);
        if (gps.isGetLocation()) {
            mapView();
        } else {
            gps.showSettingsAlert();
        }
        Log.d(TAG,""+gps.isGetLocation()+","+gps.getLatitude()+","+gps.getLongitude());
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    private void mapView() {
        MapView mapView = new MapView(this);

        ViewGroup mapViewContainer = (ViewGroup) findViewById(R.id.map_layout);
        mapViewContainer.addView(mapView);
        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(gps.getLatitude(), gps.getLongitude()), true);

    }

    private void callPermission() {
        // Check the SDK version and whether the permission is already granted or not.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_ACCESS_FINE_LOCATION);

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    PERMISSIONS_ACCESS_COARSE_LOCATION);
        } else {
            isPermission = true;
        }
    }


}

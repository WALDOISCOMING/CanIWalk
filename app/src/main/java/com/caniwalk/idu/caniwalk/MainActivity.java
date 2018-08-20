package com.caniwalk.idu.caniwalk;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.ViewGroup;

import net.daum.mf.map.api.MapView;

import java.security.MessageDigest;
/**
 Create Date: 2018-08-20
 Created by: kil kyungwan
 기초 다음 api 연동 맵 액티비티 create
 ------------------------
 Edit Date: 2018-08-21
 Edited by: Kil kyungwan
 이전의 잘못된 다음 target package를 현재의 패키지로 변경.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView();
    }

    private void mapView() {
        MapView mapView = new MapView(this);

        ViewGroup mapViewContainer = (ViewGroup) findViewById(R.id.map);
        mapViewContainer.addView(mapView);

    }

}

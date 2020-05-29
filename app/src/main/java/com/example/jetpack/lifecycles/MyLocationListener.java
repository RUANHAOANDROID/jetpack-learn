package com.example.jetpack.lifecycles;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import com.tbruyelle.rxpermissions2.RxPermissions;

import static androidx.lifecycle.Lifecycle.State.STARTED;

/**
 * Date: 2019-12-18
 * Author: 锅得铁
 * #
 */
public class MyLocationListener implements LifecycleObserver {
    private Context context;
    private LocationManager mLocationManager = null;
    private LocationProvider mProvider;
    private LocationListener callback;
    private boolean enabled = false;
    private Lifecycle lifecycle;
    RxPermissions rxPermissions;

    public MyLocationListener(FragmentActivity context, Lifecycle lifecycle, LocationListener callback) {
        this.lifecycle = lifecycle;
        this.context = context;
        this.callback = callback;
        rxPermissions = new RxPermissions(context);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void start() {
        if (!enabled) {
            // connect
            gpsStart("GPS", 5, 50, callback);
        }
    }

    public void enable() {
        enabled = true;
        if (lifecycle.getCurrentState().isAtLeast(STARTED)) {
            // connect if not connected
            gpsStart("GPS", 5, 50, callback);
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    void stop() {
        // disconnect if connected
        gpsStop(callback);
    }

    @SuppressLint("CheckResult")
    private synchronized void gpsStart(String provider, long minTime, float minDistance,
                                       LocationListener mLocationListener) {
        rxPermissions
                .request(Manifest.permission.ACCESS_FINE_LOCATION)
                .subscribe(granted -> {
                    if (granted) { // Always true pre-M
                        // I can control the camera now
                        mLocationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
                        mProvider = mLocationManager.getProvider(LocationManager.GPS_PROVIDER);
                        if (!enabled) {
                            enabled =true;
                            mLocationManager.requestLocationUpdates(mProvider.getName(), minTime, minDistance, mLocationListener);
                        }
                    } else {
                        // Oups permission denied
                    }
                });

    }

    private synchronized void gpsStop(LocationListener listener) {
        if (enabled) {
            enabled =false;
            if (mLocationManager != null) {
                mLocationManager.removeUpdates(listener);
            }
        }

    }
}

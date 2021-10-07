package com.fury.carsanj;

import android.app.Application;

import com.backtory.java.internal.BacktoryClient;
import com.backtory.java.internal.Config;

import io.realm.Realm;

/**
 * Created by fury on 10/28/2017.
 */
public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // Initializing backtory
        BacktoryClient.Android.init(Config.newBuilder().
                // Enabling User Services
                        initAuth("59f393d4e4b0edcca34582f5",
                        "59f393d4e4b0416729e79333").
                        initObjectStorage("59f393d4e4b03ffa03354def").
                        initConnectivity("59f39bd2e4b03ffa033585b1").
                        initFileStorage("59f39db2e4b03ffa033592d3").
                // Finilizing sdk
                        build(), this);

        //SETUP CONFIG
        Realm.init(this);

    }

}

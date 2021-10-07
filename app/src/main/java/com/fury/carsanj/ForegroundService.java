package com.fury.carsanj;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.location.Location;
import android.os.IBinder;
import android.preference.PreferenceManager;

import com.backtory.java.internal.BacktoryCallBack;
import com.backtory.java.internal.BacktoryObject;
import com.backtory.java.internal.BacktoryQuery;
import com.backtory.java.internal.BacktoryResponse;
import com.backtory.java.internal.BacktoryUser;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ForegroundService extends Service {
    BroadcastReceiver mReciever;
    SharedPreferences one_play_preferences;
    Editor one_play_editor;
    int EThours,ETMi;
    ScheduledThreadPoolExecutor mDialogDaemon_time;

    Context context;

    /* renamed from: shirazwebdevelopers.ehsankarimi.instaautodownloader.ForegroundService.1 */
    class C15431 extends BroadcastReceiver {
        C15431() {
        }

        public void onReceive(Context context, Intent intent) {
            if (!intent.getAction().equals("com.marothiatechs.foregroundservice.action.startforeground") && intent.getAction().equals("com.marothiatechs.foregroundservice.action.stopforeground")) {
                Editor edit1 = PreferenceManager.getDefaultSharedPreferences(ForegroundService.this.getApplicationContext()).edit();
                edit1.putBoolean("isEnabled", false);
                edit1.commit();
                Intent uiIntent2 = new Intent("ACTION_WIDGET_UPDATE_FROM_ACTIVITY");
                uiIntent2.putExtra("isEnabled", false);
                ForegroundService.this.sendBroadcast(uiIntent2);
                ForegroundService.this.doStopSelf();
            }
        }
    }

    public ForegroundService() {
    }

    public void onCreate() {
        super.onCreate();

        one_play_preferences = getApplicationContext().getSharedPreferences("PROJECT_NAME", Context.MODE_PRIVATE);
        one_play_editor = one_play_preferences.edit();

        this.mReciever = new C15431();
        IntentFilter filter = new IntentFilter("com.marothiatechs.foregroundservice.action.startforeground");
        filter.addAction("com.marothiatechs.foregroundservice.action.stopforeground");
        registerReceiver(this.mReciever, filter);

        final JalaliCalendar jalaliCalendar = new JalaliCalendar();
// java.util.Date dt = jalaliCalendar.getGregorianDate("1392/5/4");
        final String jDate = jalaliCalendar.getJalaliDate(new Date());
        // Get current user from backtory
        final BacktoryUser currentUser = BacktoryUser.getCurrentUser();
        final String firstname = currentUser.getFirstName() + " " + currentUser.getLastName();
        one_play_editor.putString("firstnameLast", firstname);
        one_play_editor.putString("jDate", jDate);
        one_play_editor.apply();

        BacktoryQuery todoQuery = new BacktoryQuery("User");
        todoQuery.whereEqualTo("user", currentUser.getUsername());
        todoQuery.findInBackground(new BacktoryCallBack<List<BacktoryObject>>() {
            @Override
            public void onResponse(BacktoryResponse<List<BacktoryObject>> backtoryResponse) {
                if (backtoryResponse.isSuccessful()) {
                    List<BacktoryObject> todoNotes = backtoryResponse.body();
                    // for (BacktoryObject todo : todoNotes)
                    //   todo.getString("content")... 

                    for (BacktoryObject todo : todoNotes){
                        EThours = todo.getInt("eHours");
                        ETMi = todo.getInt("eMinutes");
                    }

                } else {
                }
            }
        });



        if (mDialogDaemon_time != null) {
            try {
                mDialogDaemon_time.shutdown();
                mDialogDaemon_time = null;
            } catch (Exception e) {
            }
        }
        try {
            mDialogDaemon_time = new ScheduledThreadPoolExecutor(1);
        } catch (Exception e) {
        }
        try {
            mDialogDaemon_time.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {

                    Calendar rightNow = Calendar.getInstance();
                    int date = rightNow.get(Calendar.HOUR_OF_DAY);
                    int date2 = rightNow.get(Calendar.MINUTE);
                    if (date < EThours){
                        int save = one_play_preferences.getInt("saveLocation", 0);

                        final JalaliCalendar jalaliCalendar = new JalaliCalendar();
// java.util.Date dt = jalaliCalendar.getGregorianDate("1392/5/4");
                        final String jDate = jalaliCalendar.getJalaliDate(new Date());

                        if (save == 0){

                            MyLocation.LocationResult locationResult = new MyLocation.LocationResult(){
                                @Override
                                public void gotLocation(Location location){
                                    //Got the location!
                                    String getLongitude = String.valueOf(location.getLongitude());
                                    String getLatitude = String.valueOf(location.getLatitude());
                                    long time= System.currentTimeMillis();
                                    String s1 = new SimpleDateFormat("HH:mm:ss").format(new Date(time));

                                    one_play_editor.putString("Longitude_Location_1", getLongitude);
                                    one_play_editor.putString("Latitude_Location_1", getLatitude);
                                    one_play_editor.putString("Hour_Location_1", s1);
                                    one_play_editor.putString("Date_Location_1", jDate);
                                    one_play_editor.putInt("saveLocation", 1);
                                    one_play_editor.apply();
                                }
                            };
                            MyLocation myLocation = new MyLocation();
                            myLocation.getLocation(ForegroundService.this, locationResult);

                        }else if (save == 1){

                            MyLocation.LocationResult locationResult = new MyLocation.LocationResult(){
                                @Override
                                public void gotLocation(Location location){
                                    //Got the location!
                                    String getLongitude = String.valueOf(location.getLongitude());
                                    String getLatitude = String.valueOf(location.getLatitude());
                                    long time= System.currentTimeMillis();
                                    String s1 = new SimpleDateFormat("HH:mm:ss").format(new Date(time));

                                    one_play_editor.putString("Longitude_Location_2", getLongitude);
                                    one_play_editor.putString("Latitude_Location_2", getLatitude);
                                    one_play_editor.putString("Hour_Location_2", s1);
                                    one_play_editor.putString("Date_Location_2", jDate);
                                    one_play_editor.putInt("saveLocation", 2);
                                    one_play_editor.apply();
                                }
                            };
                            MyLocation myLocation = new MyLocation();
                            myLocation.getLocation(ForegroundService.this, locationResult);

                        }else if (save == 2){

                            MyLocation.LocationResult locationResult = new MyLocation.LocationResult(){
                                @Override
                                public void gotLocation(Location location){
                                    //Got the location!
                                    String getLongitude = String.valueOf(location.getLongitude());
                                    String getLatitude = String.valueOf(location.getLatitude());
                                    long time= System.currentTimeMillis();
                                    String s1 = new SimpleDateFormat("HH:mm:ss").format(new Date(time));

                                    one_play_editor.putString("Longitude_Location_3", getLongitude);
                                    one_play_editor.putString("Latitude_Location_3", getLatitude);
                                    one_play_editor.putString("Hour_Location_3", s1);
                                    one_play_editor.putString("Date_Location_3", jDate);
                                    one_play_editor.putInt("saveLocation", 3);
                                    one_play_editor.apply();
                                }
                            };
                            MyLocation myLocation = new MyLocation();
                            myLocation.getLocation(ForegroundService.this, locationResult);

                        }else if (save == 3){

                            MyLocation.LocationResult locationResult = new MyLocation.LocationResult(){
                                @Override
                                public void gotLocation(Location location){
                                    //Got the location!
                                    String getLongitude = String.valueOf(location.getLongitude());
                                    String getLatitude = String.valueOf(location.getLatitude());
                                    long time= System.currentTimeMillis();
                                    String s1 = new SimpleDateFormat("HH:mm:ss").format(new Date(time));

                                    one_play_editor.putString("Longitude_Location_4", getLongitude);
                                    one_play_editor.putString("Latitude_Location_4", getLatitude);
                                    one_play_editor.putString("Hour_Location_4", s1);
                                    one_play_editor.putString("Date_Location_4", jDate);
                                    one_play_editor.putInt("saveLocation", 4);
                                    one_play_editor.apply();
                                }
                            };
                            MyLocation myLocation = new MyLocation();
                            myLocation.getLocation(ForegroundService.this, locationResult);

                        }else if (save == 4){

                            MyLocation.LocationResult locationResult = new MyLocation.LocationResult(){
                                @Override
                                public void gotLocation(Location location){
                                    //Got the location!
                                    String getLongitude = String.valueOf(location.getLongitude());
                                    String getLatitude = String.valueOf(location.getLatitude());
                                    long time= System.currentTimeMillis();
                                    String s1 = new SimpleDateFormat("HH:mm:ss").format(new Date(time));

                                    one_play_editor.putString("Longitude_Location_5", getLongitude);
                                    one_play_editor.putString("Latitude_Location_5", getLatitude);
                                    one_play_editor.putString("Hour_Location_5", s1);
                                    one_play_editor.putString("Date_Location_5", jDate);
                                    one_play_editor.putInt("saveLocation", 5);
                                    one_play_editor.apply();
                                }
                            };
                            MyLocation myLocation = new MyLocation();
                            myLocation.getLocation(ForegroundService.this, locationResult);

                        }else if (save == 5){

                            MyLocation.LocationResult locationResult = new MyLocation.LocationResult(){
                                @Override
                                public void gotLocation(Location location){
                                    //Got the location!
                                    String getLongitude = String.valueOf(location.getLongitude());
                                    String getLatitude = String.valueOf(location.getLatitude());
                                    long time= System.currentTimeMillis();
                                    String s1 = new SimpleDateFormat("HH:mm:ss").format(new Date(time));

                                    one_play_editor.putString("Longitude_Location_6", getLongitude);
                                    one_play_editor.putString("Latitude_Location_6", getLatitude);
                                    one_play_editor.putString("Hour_Location_6", s1);
                                    one_play_editor.putString("Date_Location_6", jDate);
                                    one_play_editor.putInt("saveLocation", 6);
                                    one_play_editor.apply();
                                }
                            };
                            MyLocation myLocation = new MyLocation();
                            myLocation.getLocation(ForegroundService.this, locationResult);

                        }else if (save == 6){

                            MyLocation.LocationResult locationResult = new MyLocation.LocationResult(){
                                @Override
                                public void gotLocation(Location location){
                                    //Got the location!
                                    String getLongitude = String.valueOf(location.getLongitude());
                                    String getLatitude = String.valueOf(location.getLatitude());
                                    long time= System.currentTimeMillis();
                                    String s1 = new SimpleDateFormat("HH:mm:ss").format(new Date(time));

                                    one_play_editor.putString("Longitude_Location_7", getLongitude);
                                    one_play_editor.putString("Latitude_Location_7", getLatitude);
                                    one_play_editor.putString("Hour_Location_7", s1);
                                    one_play_editor.putString("Date_Location_7", jDate);
                                    one_play_editor.putInt("saveLocation", 7);
                                    one_play_editor.apply();
                                }
                            };
                            MyLocation myLocation = new MyLocation();
                            myLocation.getLocation(ForegroundService.this, locationResult);

                        }else if (save == 7){

                            MyLocation.LocationResult locationResult = new MyLocation.LocationResult(){
                                @Override
                                public void gotLocation(Location location){
                                    //Got the location!
                                    String getLongitude = String.valueOf(location.getLongitude());
                                    String getLatitude = String.valueOf(location.getLatitude());
                                    long time= System.currentTimeMillis();
                                    String s1 = new SimpleDateFormat("HH:mm:ss").format(new Date(time));

                                    one_play_editor.putString("Longitude_Location_8", getLongitude);
                                    one_play_editor.putString("Latitude_Location_8", getLatitude);
                                    one_play_editor.putString("Hour_Location_8", s1);
                                    one_play_editor.putString("Date_Location_8", jDate);
                                    one_play_editor.putInt("saveLocation", 8);
                                    one_play_editor.apply();
                                }
                            };
                            MyLocation myLocation = new MyLocation();
                            myLocation.getLocation(ForegroundService.this, locationResult);

                        }else if (save == 8){

                            MyLocation.LocationResult locationResult = new MyLocation.LocationResult(){
                                @Override
                                public void gotLocation(Location location){
                                    //Got the location!
                                    String getLongitude = String.valueOf(location.getLongitude());
                                    String getLatitude = String.valueOf(location.getLatitude());
                                    long time= System.currentTimeMillis();
                                    String s1 = new SimpleDateFormat("HH:mm:ss").format(new Date(time));

                                    one_play_editor.putString("Longitude_Location_9", getLongitude);
                                    one_play_editor.putString("Latitude_Location_9", getLatitude);
                                    one_play_editor.putString("Hour_Location_9", s1);
                                    one_play_editor.putString("Date_Location_9", jDate);
                                    one_play_editor.putInt("saveLocation", 9);
                                    one_play_editor.apply();
                                }
                            };
                            MyLocation myLocation = new MyLocation();
                            myLocation.getLocation(ForegroundService.this, locationResult);

                        }else if (save == 9){

                            MyLocation.LocationResult locationResult = new MyLocation.LocationResult(){
                                @Override
                                public void gotLocation(Location location){
                                    //Got the location!
                                    String getLongitude = String.valueOf(location.getLongitude());
                                    String getLatitude = String.valueOf(location.getLatitude());
                                    long time= System.currentTimeMillis();
                                    String s1 = new SimpleDateFormat("HH:mm:ss").format(new Date(time));

                                    one_play_editor.putString("Longitude_Location_10", getLongitude);
                                    one_play_editor.putString("Latitude_Location_10", getLatitude);
                                    one_play_editor.putString("Hour_Location_10", s1);
                                    one_play_editor.putString("Date_Location_10", jDate);
                                    one_play_editor.putInt("saveLocation", 10);
                                    one_play_editor.apply();
                                }
                            };
                            MyLocation myLocation = new MyLocation();
                            myLocation.getLocation(ForegroundService.this, locationResult);

                        }else if (save == 10){

                            MyLocation.LocationResult locationResult = new MyLocation.LocationResult(){
                                @Override
                                public void gotLocation(Location location){
                                    //Got the location!
                                    String getLongitude = String.valueOf(location.getLongitude());
                                    String getLatitude = String.valueOf(location.getLatitude());
                                    long time= System.currentTimeMillis();
                                    String s1 = new SimpleDateFormat("HH:mm:ss").format(new Date(time));

                                    one_play_editor.putString("Longitude_Location_11", getLongitude);
                                    one_play_editor.putString("Latitude_Location_11", getLatitude);
                                    one_play_editor.putString("Hour_Location_11", s1);
                                    one_play_editor.putString("Date_Location_11", jDate);
                                    one_play_editor.putInt("saveLocation", 11);
                                    one_play_editor.apply();
                                }
                            };
                            MyLocation myLocation = new MyLocation();
                            myLocation.getLocation(ForegroundService.this, locationResult);

                        }else if (save == 11){

                            MyLocation.LocationResult locationResult = new MyLocation.LocationResult(){
                                @Override
                                public void gotLocation(Location location){
                                    //Got the location!
                                    String getLongitude = String.valueOf(location.getLongitude());
                                    String getLatitude = String.valueOf(location.getLatitude());
                                    long time= System.currentTimeMillis();
                                    String s1 = new SimpleDateFormat("HH:mm:ss").format(new Date(time));

                                    one_play_editor.putString("Longitude_Location_12", getLongitude);
                                    one_play_editor.putString("Latitude_Location_12", getLatitude);
                                    one_play_editor.putString("Hour_Location_12", s1);
                                    one_play_editor.putString("Date_Location_12", jDate);
                                    one_play_editor.putInt("saveLocation", 12);
                                    one_play_editor.apply();
                                }
                            };
                            MyLocation myLocation = new MyLocation();
                            myLocation.getLocation(ForegroundService.this, locationResult);

                        }else if (save == 12){

                            MyLocation.LocationResult locationResult = new MyLocation.LocationResult(){
                                @Override
                                public void gotLocation(Location location){
                                    //Got the location!
                                    String getLongitude = String.valueOf(location.getLongitude());
                                    String getLatitude = String.valueOf(location.getLatitude());
                                    long time= System.currentTimeMillis();
                                    String s1 = new SimpleDateFormat("HH:mm:ss").format(new Date(time));

                                    one_play_editor.putString("Longitude_Location_13", getLongitude);
                                    one_play_editor.putString("Latitude_Location_13", getLatitude);
                                    one_play_editor.putString("Hour_Location_13", s1);
                                    one_play_editor.putString("Date_Location_13", jDate);
                                    one_play_editor.putInt("saveLocation", 13);
                                    one_play_editor.apply();
                                }
                            };
                            MyLocation myLocation = new MyLocation();
                            myLocation.getLocation(ForegroundService.this, locationResult);

                        }else if (save == 13){

                            MyLocation.LocationResult locationResult = new MyLocation.LocationResult(){
                                @Override
                                public void gotLocation(Location location){
                                    //Got the location!
                                    String getLongitude = String.valueOf(location.getLongitude());
                                    String getLatitude = String.valueOf(location.getLatitude());
                                    long time= System.currentTimeMillis();
                                    String s1 = new SimpleDateFormat("HH:mm:ss").format(new Date(time));

                                    one_play_editor.putString("Longitude_Location_14", getLongitude);
                                    one_play_editor.putString("Latitude_Location_14", getLatitude);
                                    one_play_editor.putString("Hour_Location_14", s1);
                                    one_play_editor.putString("Date_Location_14", jDate);
                                    one_play_editor.putInt("saveLocation", 14);
                                    one_play_editor.apply();
                                }
                            };
                            MyLocation myLocation = new MyLocation();
                            myLocation.getLocation(ForegroundService.this, locationResult);

                        }else if (save == 14){

                            MyLocation.LocationResult locationResult = new MyLocation.LocationResult(){
                                @Override
                                public void gotLocation(Location location){
                                    //Got the location!
                                    String getLongitude = String.valueOf(location.getLongitude());
                                    String getLatitude = String.valueOf(location.getLatitude());
                                    long time= System.currentTimeMillis();
                                    String s1 = new SimpleDateFormat("HH:mm:ss").format(new Date(time));

                                    one_play_editor.putString("Longitude_Location_15", getLongitude);
                                    one_play_editor.putString("Latitude_Location_15", getLatitude);
                                    one_play_editor.putString("Hour_Location_15", s1);
                                    one_play_editor.putString("Date_Location_15", jDate);
                                    one_play_editor.putInt("saveLocation", 15);
                                    one_play_editor.apply();
                                }
                            };
                            MyLocation myLocation = new MyLocation();
                            myLocation.getLocation(ForegroundService.this, locationResult);

                        }else if (save == 15){

                            MyLocation.LocationResult locationResult = new MyLocation.LocationResult(){
                                @Override
                                public void gotLocation(Location location){
                                    //Got the location!
                                    String getLongitude = String.valueOf(location.getLongitude());
                                    String getLatitude = String.valueOf(location.getLatitude());
                                    long time= System.currentTimeMillis();
                                    String s1 = new SimpleDateFormat("HH:mm:ss").format(new Date(time));

                                    one_play_editor.putString("Longitude_Location_16", getLongitude);
                                    one_play_editor.putString("Latitude_Location_16", getLatitude);
                                    one_play_editor.putString("Hour_Location_16", s1);
                                    one_play_editor.putString("Date_Location_16", jDate);
                                    one_play_editor.putInt("saveLocation", 16);
                                    one_play_editor.apply();
                                }
                            };
                            MyLocation myLocation = new MyLocation();
                            myLocation.getLocation(ForegroundService.this, locationResult);

                        }else if (save == 16){

                            MyLocation.LocationResult locationResult = new MyLocation.LocationResult(){
                                @Override
                                public void gotLocation(Location location){
                                    //Got the location!
                                    String getLongitude = String.valueOf(location.getLongitude());
                                    String getLatitude = String.valueOf(location.getLatitude());
                                    long time= System.currentTimeMillis();
                                    String s1 = new SimpleDateFormat("HH:mm:ss").format(new Date(time));

                                    one_play_editor.putString("Longitude_Location_17", getLongitude);
                                    one_play_editor.putString("Latitude_Location_17", getLatitude);
                                    one_play_editor.putString("Hour_Location_17", s1);
                                    one_play_editor.putString("Date_Location_17", jDate);
                                    one_play_editor.putInt("saveLocation", 17);
                                    one_play_editor.apply();
                                }
                            };
                            MyLocation myLocation = new MyLocation();
                            myLocation.getLocation(ForegroundService.this, locationResult);

                        }else if (save == 17){

                            MyLocation.LocationResult locationResult = new MyLocation.LocationResult(){
                                @Override
                                public void gotLocation(Location location){
                                    //Got the location!
                                    String getLongitude = String.valueOf(location.getLongitude());
                                    String getLatitude = String.valueOf(location.getLatitude());
                                    long time= System.currentTimeMillis();
                                    String s1 = new SimpleDateFormat("HH:mm:ss").format(new Date(time));

                                    one_play_editor.putString("Longitude_Location_18", getLongitude);
                                    one_play_editor.putString("Latitude_Location_18", getLatitude);
                                    one_play_editor.putString("Hour_Location_18", s1);
                                    one_play_editor.putString("Date_Location_18", jDate);
                                    one_play_editor.putInt("saveLocation", 18);
                                    one_play_editor.apply();
                                }
                            };
                            MyLocation myLocation = new MyLocation();
                            myLocation.getLocation(ForegroundService.this, locationResult);

                        }else if (save == 18){

                            MyLocation.LocationResult locationResult = new MyLocation.LocationResult(){
                                @Override
                                public void gotLocation(Location location){
                                    //Got the location!
                                    String getLongitude = String.valueOf(location.getLongitude());
                                    String getLatitude = String.valueOf(location.getLatitude());
                                    long time= System.currentTimeMillis();
                                    String s1 = new SimpleDateFormat("HH:mm:ss").format(new Date(time));

                                    one_play_editor.putString("Longitude_Location_19", getLongitude);
                                    one_play_editor.putString("Latitude_Location_19", getLatitude);
                                    one_play_editor.putString("Hour_Location_19", s1);
                                    one_play_editor.putString("Date_Location_19", jDate);
                                    one_play_editor.putInt("saveLocation", 19);
                                    one_play_editor.apply();
                                }
                            };
                            MyLocation myLocation = new MyLocation();
                            myLocation.getLocation(ForegroundService.this, locationResult);

                        }else if (save == 19){

                            MyLocation.LocationResult locationResult = new MyLocation.LocationResult(){
                                @Override
                                public void gotLocation(Location location){
                                    //Got the location!
                                    String getLongitude = String.valueOf(location.getLongitude());
                                    String getLatitude = String.valueOf(location.getLatitude());
                                    long time= System.currentTimeMillis();
                                    String s1 = new SimpleDateFormat("HH:mm:ss").format(new Date(time));

                                    one_play_editor.putString("Longitude_Location_20", getLongitude);
                                    one_play_editor.putString("Latitude_Location_20", getLatitude);
                                    one_play_editor.putString("Hour_Location_20", s1);
                                    one_play_editor.putString("Date_Location_20", jDate);
                                    one_play_editor.putInt("saveLocation", 20);
                                    one_play_editor.apply();
                                }
                            };
                            MyLocation myLocation = new MyLocation();
                            myLocation.getLocation(ForegroundService.this, locationResult);

                        }else if (save == 20){

                            MyLocation.LocationResult locationResult = new MyLocation.LocationResult(){
                                @Override
                                public void gotLocation(Location location){
                                    //Got the location!
                                    String getLongitude = String.valueOf(location.getLongitude());
                                    String getLatitude = String.valueOf(location.getLatitude());
                                    long time= System.currentTimeMillis();
                                    String s1 = new SimpleDateFormat("HH:mm:ss").format(new Date(time));

                                    one_play_editor.putString("Longitude_Location_21", getLongitude);
                                    one_play_editor.putString("Latitude_Location_21", getLatitude);
                                    one_play_editor.putString("Hour_Location_21", s1);
                                    one_play_editor.putString("Date_Location_21", jDate);
                                    one_play_editor.putInt("saveLocation", 21);
                                    one_play_editor.apply();
                                }
                            };
                            MyLocation myLocation = new MyLocation();
                            myLocation.getLocation(ForegroundService.this, locationResult);

                        }else if (save == 21){

                            MyLocation.LocationResult locationResult = new MyLocation.LocationResult(){
                                @Override
                                public void gotLocation(Location location){
                                    //Got the location!
                                    String getLongitude = String.valueOf(location.getLongitude());
                                    String getLatitude = String.valueOf(location.getLatitude());
                                    long time= System.currentTimeMillis();
                                    String s1 = new SimpleDateFormat("HH:mm:ss").format(new Date(time));

                                    one_play_editor.putString("Longitude_Location_22", getLongitude);
                                    one_play_editor.putString("Latitude_Location_22", getLatitude);
                                    one_play_editor.putString("Hour_Location_22", s1);
                                    one_play_editor.putString("Date_Location_22", jDate);
                                    one_play_editor.putInt("saveLocation", 22);
                                    one_play_editor.apply();
                                }
                            };
                            MyLocation myLocation = new MyLocation();
                            myLocation.getLocation(ForegroundService.this, locationResult);

                        }else if (save == 22){

                            MyLocation.LocationResult locationResult = new MyLocation.LocationResult(){
                                @Override
                                public void gotLocation(Location location){
                                    //Got the location!
                                    String getLongitude = String.valueOf(location.getLongitude());
                                    String getLatitude = String.valueOf(location.getLatitude());
                                    long time= System.currentTimeMillis();
                                    String s1 = new SimpleDateFormat("HH:mm:ss").format(new Date(time));

                                    one_play_editor.putString("Longitude_Location_23", getLongitude);
                                    one_play_editor.putString("Latitude_Location_23", getLatitude);
                                    one_play_editor.putString("Hour_Location_23", s1);
                                    one_play_editor.putString("Date_Location_23", jDate);
                                    one_play_editor.putInt("saveLocation", 23);
                                    one_play_editor.apply();
                                }
                            };
                            MyLocation myLocation = new MyLocation();
                            myLocation.getLocation(ForegroundService.this, locationResult);

                        }else if (save == 23){

                            MyLocation.LocationResult locationResult = new MyLocation.LocationResult(){
                                @Override
                                public void gotLocation(Location location){
                                    //Got the location!
                                    String getLongitude = String.valueOf(location.getLongitude());
                                    String getLatitude = String.valueOf(location.getLatitude());
                                    long time= System.currentTimeMillis();
                                    String s1 = new SimpleDateFormat("HH:mm:ss").format(new Date(time));

                                    one_play_editor.putString("Longitude_Location_24", getLongitude);
                                    one_play_editor.putString("Latitude_Location_24", getLatitude);
                                    one_play_editor.putString("Hour_Location_24", s1);
                                    one_play_editor.putString("Date_Location_24", jDate);
                                    one_play_editor.putInt("saveLocation", 24);
                                    one_play_editor.apply();
                                }
                            };
                            MyLocation myLocation = new MyLocation();
                            myLocation.getLocation(ForegroundService.this, locationResult);

                        }
                    }else {
                        int save = one_play_preferences.getInt("saveLocation", 0);

                        one_play_editor.putInt("saveLocationForSend", save);
                        one_play_editor.putBoolean("saveLocationFinish", true);
                        one_play_editor.putInt("saveLocation", 0);
                        one_play_editor.apply();
                    }

                }
            }, 0L, 1800000L, TimeUnit.MILLISECONDS);

        } catch (Exception e) {
            mDialogDaemon_time.shutdown();
            mDialogDaemon_time = null;
        }



        //FirebaseCrash.log("log 1");
        System.gc();
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        return 1;
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    private void doStopSelf() {
        unregisterReceiver(this.mReciever);
        stopForeground(true);
        stopSelf();
    }

}

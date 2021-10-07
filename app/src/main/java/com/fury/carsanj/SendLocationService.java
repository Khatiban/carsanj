package com.fury.carsanj;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.backtory.java.internal.BacktoryCallBack;
import com.backtory.java.internal.BacktoryObject;
import com.backtory.java.internal.BacktoryResponse;
import com.backtory.java.internal.BacktoryUser;

import java.util.Arrays;

/**
 * Created by fury on 11/2/2017.
 */
public class SendLocationService extends IntentService {

    public SendLocationService(String name) {
        super(name);
    }

    SharedPreferences one_play_preferences;
    SharedPreferences.Editor one_play_editor;
    String ST, ET, Hour_Location_24, Date_Location_24, Latitude_Location_24,Longitude_Location_1
    ,Longitude_Location_2,        Longitude_Location_3,Longitude_Location_4,        Longitude_Location_5,Longitude_Location_6
    ,        Longitude_Location_7,Longitude_Location_8,        Longitude_Location_9,Longitude_Location_10,        Longitude_Location_11,Longitude_Location_12
    ,        Longitude_Location_13,Longitude_Location_14,        Longitude_Location_15,Longitude_Location_16,        Longitude_Location_17,Longitude_Location_18
    ,        Longitude_Location_19,Longitude_Location_20,        Longitude_Location_21,Longitude_Location_22,        Longitude_Location_23,Longitude_Location_24,
            Latitude_Location_1,Latitude_Location_2,        Latitude_Location_3,Latitude_Location_4,        Latitude_Location_5
    ,Latitude_Location_6,        Latitude_Location_7,Latitude_Location_8,        Latitude_Location_9,Latitude_Location_10,        Latitude_Location_11
    ,Latitude_Location_12,        Latitude_Location_13,Latitude_Location_14,        Latitude_Location_15,Latitude_Location_16,        Latitude_Location_17
    ,Latitude_Location_18,        Latitude_Location_19,Latitude_Location_20,        Latitude_Location_21,Latitude_Location_22,        Latitude_Location_23
            ,Date_Location_1,Date_Location_2
    ,        Date_Location_3,Date_Location_4,        Date_Location_5,Date_Location_6,        Date_Location_7,Date_Location_8,        Date_Location_9
    ,Date_Location_10,        Date_Location_11,Date_Location_12,        Date_Location_13,Date_Location_14,        Date_Location_15,Date_Location_16
    ,        Date_Location_17,Date_Location_18,        Date_Location_19,Date_Location_20,        Date_Location_21,Date_Location_22,        Date_Location_23
            ,Hour_Location_1,Hour_Location_2
    ,        Hour_Location_3,Hour_Location_4,        Hour_Location_5,Hour_Location_6,        Hour_Location_7,Hour_Location_8,        Hour_Location_9
    ,Hour_Location_10,        Hour_Location_11,Hour_Location_12,        Hour_Location_13,Hour_Location_14,        Hour_Location_15,Hour_Location_16
    ,        Hour_Location_17,Hour_Location_18,        Hour_Location_19,Hour_Location_20,        Hour_Location_21,Hour_Location_22,        Hour_Location_23;
    boolean saveLocationFinish;
    int saveLocationForSend;

    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        boolean isNetworkConnected = extras.getBoolean("isNetworkConnected");

        one_play_preferences = getApplicationContext().getSharedPreferences("PROJECT_NAME", Context.MODE_PRIVATE);
        one_play_editor = one_play_preferences.edit();
        Longitude_Location_1 = one_play_preferences.getString("Longitude_Location_1", "");
        Longitude_Location_2 = one_play_preferences.getString("Longitude_Location_2", "");
        Longitude_Location_3 = one_play_preferences.getString("Longitude_Location_3", "");
        Longitude_Location_4 = one_play_preferences.getString("Longitude_Location_4", "");
        Longitude_Location_5 = one_play_preferences.getString("Longitude_Location_5", "");
        Longitude_Location_6 = one_play_preferences.getString("Longitude_Location_6", "");
        Longitude_Location_7 = one_play_preferences.getString("Longitude_Location_7", "");
        Longitude_Location_8 = one_play_preferences.getString("Longitude_Location_8", "");
        Longitude_Location_9 = one_play_preferences.getString("Longitude_Location_9", "");
        Longitude_Location_10 = one_play_preferences.getString("Longitude_Location_10", "");
        Longitude_Location_11 = one_play_preferences.getString("Longitude_Location_11", "");
        Longitude_Location_12 = one_play_preferences.getString("Longitude_Location_12", "");
        Longitude_Location_13 = one_play_preferences.getString("Longitude_Location_13", "");
        Longitude_Location_14 = one_play_preferences.getString("Longitude_Location_14", "");
        Longitude_Location_15 = one_play_preferences.getString("Longitude_Location_15", "");
        Longitude_Location_16 = one_play_preferences.getString("Longitude_Location_16", "");
        Longitude_Location_17 = one_play_preferences.getString("Longitude_Location_17", "");
        Longitude_Location_18 = one_play_preferences.getString("Longitude_Location_18", "");
        Longitude_Location_19 = one_play_preferences.getString("Longitude_Location_19", "");
        Longitude_Location_20 = one_play_preferences.getString("Longitude_Location_20", "");
        Longitude_Location_21 = one_play_preferences.getString("Longitude_Location_21", "");
        Longitude_Location_22 = one_play_preferences.getString("Longitude_Location_22", "");
        Longitude_Location_23 = one_play_preferences.getString("Longitude_Location_23", "");
        Longitude_Location_24 = one_play_preferences.getString("Longitude_Location_24", "");
        Latitude_Location_1 = one_play_preferences.getString("Latitude_Location_1", "");
        Latitude_Location_2 = one_play_preferences.getString("Latitude_Location_2", "");
        Latitude_Location_3 = one_play_preferences.getString("Latitude_Location_3", "");
        Latitude_Location_4 = one_play_preferences.getString("Latitude_Location_4", "");
        Latitude_Location_5 = one_play_preferences.getString("Latitude_Location_5", "");
        Latitude_Location_6 = one_play_preferences.getString("Latitude_Location_6", "");
        Latitude_Location_7 = one_play_preferences.getString("Latitude_Location_7", "");
        Latitude_Location_8 = one_play_preferences.getString("Latitude_Location_8", "");
        Latitude_Location_9 = one_play_preferences.getString("Latitude_Location_9", "");
        Latitude_Location_10 = one_play_preferences.getString("Latitude_Location_10", "");
        Latitude_Location_11 = one_play_preferences.getString("Latitude_Location_11", "");
        Latitude_Location_12 = one_play_preferences.getString("Latitude_Location_12", "");
        Latitude_Location_13 = one_play_preferences.getString("Latitude_Location_13", "");
        Latitude_Location_14 = one_play_preferences.getString("Latitude_Location_14", "");
        Latitude_Location_15 = one_play_preferences.getString("Latitude_Location_15", "");
        Latitude_Location_16 = one_play_preferences.getString("Latitude_Location_16", "");
        Latitude_Location_17 = one_play_preferences.getString("Latitude_Location_17", "");
        Latitude_Location_18 = one_play_preferences.getString("Latitude_Location_18", "");
        Latitude_Location_19 = one_play_preferences.getString("Latitude_Location_19", "");
        Latitude_Location_20 = one_play_preferences.getString("Latitude_Location_20", "");
        Latitude_Location_21 = one_play_preferences.getString("Latitude_Location_21", "");
        Latitude_Location_22 = one_play_preferences.getString("Latitude_Location_22", "");
        Latitude_Location_23 = one_play_preferences.getString("Latitude_Location_23", "");
        Latitude_Location_24 = one_play_preferences.getString("Latitude_Location_24", "");
        Date_Location_1 = one_play_preferences.getString("Date_Location_1", "");
        Date_Location_2 = one_play_preferences.getString("Date_Location_2", "");
        Date_Location_3 = one_play_preferences.getString("Date_Location_3", "");
        Date_Location_4 = one_play_preferences.getString("Date_Location_4", "");
        Date_Location_5 = one_play_preferences.getString("Date_Location_5", "");
        Date_Location_6 = one_play_preferences.getString("Date_Location_6", "");
        Date_Location_7 = one_play_preferences.getString("Date_Location_7", "");
        Date_Location_8 = one_play_preferences.getString("Date_Location_8", "");
        Date_Location_9 = one_play_preferences.getString("Date_Location_9", "");
        Date_Location_10 = one_play_preferences.getString("Date_Location_10", "");
        Date_Location_11 = one_play_preferences.getString("Date_Location_11", "");
        Date_Location_12 = one_play_preferences.getString("Date_Location_12", "");
        Date_Location_13 = one_play_preferences.getString("Date_Location_13", "");
        Date_Location_14 = one_play_preferences.getString("Date_Location_14", "");
        Date_Location_15 = one_play_preferences.getString("Date_Location_15", "");
        Date_Location_16 = one_play_preferences.getString("Date_Location_16", "");
        Date_Location_17 = one_play_preferences.getString("Date_Location_17", "");
        Date_Location_18 = one_play_preferences.getString("Date_Location_18", "");
        Date_Location_19 = one_play_preferences.getString("Date_Location_19", "");
        Date_Location_20 = one_play_preferences.getString("Date_Location_20", "");
        Date_Location_21 = one_play_preferences.getString("Date_Location_21", "");
        Date_Location_22 = one_play_preferences.getString("Date_Location_22", "");
        Date_Location_23 = one_play_preferences.getString("Date_Location_23", "");
        Date_Location_24 = one_play_preferences.getString("Date_Location_24", "");
        Hour_Location_1 = one_play_preferences.getString("Hour_Location_1", "");
        Hour_Location_2 = one_play_preferences.getString("Hour_Location_2", "");
        Hour_Location_3 = one_play_preferences.getString("Hour_Location_3", "");
        Hour_Location_4 = one_play_preferences.getString("Hour_Location_4", "");
        Hour_Location_5 = one_play_preferences.getString("Hour_Location_5", "");
        Hour_Location_6 = one_play_preferences.getString("Hour_Location_6", "");
        Hour_Location_7 = one_play_preferences.getString("Hour_Location_7", "");
        Hour_Location_8 = one_play_preferences.getString("Hour_Location_8", "");
        Hour_Location_9 = one_play_preferences.getString("Hour_Location_9", "");
        Hour_Location_10 = one_play_preferences.getString("Hour_Location_10", "");
        Hour_Location_11 = one_play_preferences.getString("Hour_Location_11", "");
        Hour_Location_12 = one_play_preferences.getString("Hour_Location_12", "");
        Hour_Location_13 = one_play_preferences.getString("Hour_Location_13", "");
        Hour_Location_14 = one_play_preferences.getString("Hour_Location_14", "");
        Hour_Location_15 = one_play_preferences.getString("Hour_Location_15", "");
        Hour_Location_16 = one_play_preferences.getString("Hour_Location_16", "");
        Hour_Location_17 = one_play_preferences.getString("Hour_Location_17", "");
        Hour_Location_18 = one_play_preferences.getString("Hour_Location_18", "");
        Hour_Location_19 = one_play_preferences.getString("Hour_Location_19", "");
        Hour_Location_20 = one_play_preferences.getString("Hour_Location_20", "");
        Hour_Location_21 = one_play_preferences.getString("Hour_Location_21", "");
        Hour_Location_22 = one_play_preferences.getString("Hour_Location_22", "");
        Hour_Location_23 = one_play_preferences.getString("Hour_Location_23", "");
        Hour_Location_24 = one_play_preferences.getString("Hour_Location_24", "");
        saveLocationForSend = one_play_preferences.getInt("saveLocationForSend", 0);
        saveLocationFinish = one_play_preferences.getBoolean("saveLocationFinish", false);

        if (isNetworkConnected) {

            if (saveLocationFinish) {

                // Get current user from backtory
                final BacktoryUser currentUser = BacktoryUser.getCurrentUser();

                BacktoryObject note = new BacktoryObject("Location");

                note.put("nameUser", currentUser.getUsername());
                note.put("all", "1");

                if (saveLocationForSend == 1) {
                    note.put("Longitude", Arrays.asList(Longitude_Location_1));
                    note.put("Latitude", Arrays.asList(Latitude_Location_1));
                    note.put("Date", Arrays.asList(Date_Location_1));
                    note.put("Hour", Arrays.asList(Hour_Location_1));
                } else if (saveLocationForSend == 2) {
                    note.put("Longitude", Arrays.asList(Longitude_Location_1,Longitude_Location_2));
                    note.put("Latitude", Arrays.asList(Latitude_Location_1,Latitude_Location_2));
                    note.put("Date", Arrays.asList(Date_Location_1,Date_Location_2));
                    note.put("Hour", Arrays.asList(Hour_Location_1,Hour_Location_2));
                } else if (saveLocationForSend == 3) {
                    note.put("Longitude", Arrays.asList(Longitude_Location_1,Longitude_Location_2,Longitude_Location_3));
                    note.put("Latitude", Arrays.asList(Latitude_Location_1,Latitude_Location_2,Latitude_Location_3));
                    note.put("Date", Arrays.asList(Date_Location_1,Date_Location_2,Date_Location_3));
                    note.put("Hour", Arrays.asList(Hour_Location_1,Hour_Location_2,Hour_Location_3));
                } else if (saveLocationForSend == 4) {
                    note.put("Longitude", Arrays.asList(Longitude_Location_1,Longitude_Location_2,Longitude_Location_3,Longitude_Location_4));
                    note.put("Latitude", Arrays.asList(Latitude_Location_1,Latitude_Location_2,Latitude_Location_3,Latitude_Location_4));
                    note.put("Date", Arrays.asList(Date_Location_1,Date_Location_2,Date_Location_3,Date_Location_4));
                    note.put("Hour", Arrays.asList(Hour_Location_1,Hour_Location_2,Hour_Location_3,Hour_Location_4));
                } else if (saveLocationForSend == 5) {
                    note.put("Longitude", Arrays.asList(Longitude_Location_1,Longitude_Location_2,Longitude_Location_3,Longitude_Location_4,Longitude_Location_5));
                    note.put("Latitude", Arrays.asList(Latitude_Location_1,Latitude_Location_2,Latitude_Location_3,Latitude_Location_4,Latitude_Location_5));
                    note.put("Date", Arrays.asList(Date_Location_1,Date_Location_2,Date_Location_3,Date_Location_4,Date_Location_5));
                    note.put("Hour", Arrays.asList(Hour_Location_1,Hour_Location_2,Hour_Location_3,Hour_Location_4,Hour_Location_5));
                } else if (saveLocationForSend == 6) {
                    note.put("Longitude", Arrays.asList(Longitude_Location_1,Longitude_Location_2,Longitude_Location_3,Longitude_Location_4,Longitude_Location_5
                    ,Longitude_Location_6));
                    note.put("Latitude", Arrays.asList(Latitude_Location_1,Latitude_Location_2,Latitude_Location_3,Latitude_Location_4,Latitude_Location_5
                    ,Latitude_Location_6));
                    note.put("Date", Arrays.asList(Date_Location_1,Date_Location_2,Date_Location_3,Date_Location_4,Date_Location_5,Date_Location_6));
                    note.put("Hour", Arrays.asList(Hour_Location_1,Hour_Location_2,Hour_Location_3,Hour_Location_4,Hour_Location_5,Hour_Location_6));
                } else if (saveLocationForSend == 7) {
                    note.put("Longitude", Arrays.asList(Longitude_Location_1,Longitude_Location_2,Longitude_Location_3,Longitude_Location_4,Longitude_Location_5
                            ,Longitude_Location_6,Longitude_Location_7));
                    note.put("Latitude", Arrays.asList(Latitude_Location_1,Latitude_Location_2,Latitude_Location_3,Latitude_Location_4,Latitude_Location_5
                            ,Latitude_Location_6,Latitude_Location_7));
                    note.put("Date", Arrays.asList(Date_Location_1,Date_Location_2,Date_Location_3,Date_Location_4,Date_Location_5,Date_Location_6,Date_Location_7));
                    note.put("Hour", Arrays.asList(Hour_Location_1,Hour_Location_2,Hour_Location_3,Hour_Location_4,Hour_Location_5,Hour_Location_6,Hour_Location_7));
                } else if (saveLocationForSend == 8) {
                    note.put("Longitude", Arrays.asList(Longitude_Location_1,Longitude_Location_2,Longitude_Location_3,Longitude_Location_4,Longitude_Location_5
                            ,Longitude_Location_6,Longitude_Location_7,Longitude_Location_8));
                    note.put("Latitude", Arrays.asList(Latitude_Location_1,Latitude_Location_2,Latitude_Location_3,Latitude_Location_4,Latitude_Location_5
                            ,Latitude_Location_6,Latitude_Location_7,Latitude_Location_8));
                    note.put("Date", Arrays.asList(Date_Location_1,Date_Location_2,Date_Location_3,Date_Location_4,Date_Location_5,Date_Location_6,Date_Location_7
                    ,Date_Location_8));
                    note.put("Hour", Arrays.asList(Hour_Location_1,Hour_Location_2,Hour_Location_3,Hour_Location_4,Hour_Location_5,Hour_Location_6,Hour_Location_7
                    ,Hour_Location_8));
                } else if (saveLocationForSend == 9) {
                    note.put("Longitude", Arrays.asList(Longitude_Location_1,Longitude_Location_2,Longitude_Location_3,Longitude_Location_4,Longitude_Location_5
                            ,Longitude_Location_6,Longitude_Location_7,Longitude_Location_8,Longitude_Location_9));
                    note.put("Latitude", Arrays.asList(Latitude_Location_1,Latitude_Location_2,Latitude_Location_3,Latitude_Location_4,Latitude_Location_5
                            ,Latitude_Location_6,Latitude_Location_7,Latitude_Location_8,Latitude_Location_9));
                    note.put("Date", Arrays.asList(Date_Location_1,Date_Location_2,Date_Location_3,Date_Location_4,Date_Location_5,Date_Location_6,Date_Location_7
                            ,Date_Location_8,Date_Location_9));
                    note.put("Hour", Arrays.asList(Hour_Location_1,Hour_Location_2,Hour_Location_3,Hour_Location_4,Hour_Location_5,Hour_Location_6,Hour_Location_7
                            ,Hour_Location_8,Hour_Location_9));
                } else if (saveLocationForSend == 10) {
                    note.put("Longitude", Arrays.asList(Longitude_Location_1,Longitude_Location_2,Longitude_Location_3,Longitude_Location_4,Longitude_Location_5
                            ,Longitude_Location_6,Longitude_Location_7,Longitude_Location_8,Longitude_Location_9,Longitude_Location_10));
                    note.put("Latitude", Arrays.asList(Latitude_Location_1,Latitude_Location_2,Latitude_Location_3,Latitude_Location_4,Latitude_Location_5
                            ,Latitude_Location_6,Latitude_Location_7,Latitude_Location_8,Latitude_Location_9,Latitude_Location_10));
                    note.put("Date", Arrays.asList(Date_Location_1,Date_Location_2,Date_Location_3,Date_Location_4,Date_Location_5,Date_Location_6,Date_Location_7
                            ,Date_Location_8,Date_Location_9,Date_Location_10));
                    note.put("Hour", Arrays.asList(Hour_Location_1,Hour_Location_2,Hour_Location_3,Hour_Location_4,Hour_Location_5,Hour_Location_6,Hour_Location_7
                            ,Hour_Location_8,Hour_Location_9,Hour_Location_10));
                } else if (saveLocationForSend == 11) {
                    note.put("Longitude", Arrays.asList(Longitude_Location_1,Longitude_Location_2,Longitude_Location_3,Longitude_Location_4,Longitude_Location_5
                            ,Longitude_Location_6,Longitude_Location_7,Longitude_Location_8,Longitude_Location_9,Longitude_Location_10,Longitude_Location_11));
                    note.put("Latitude", Arrays.asList(Latitude_Location_1,Latitude_Location_2,Latitude_Location_3,Latitude_Location_4,Latitude_Location_5
                            ,Latitude_Location_6,Latitude_Location_7,Latitude_Location_8,Latitude_Location_9,Latitude_Location_10,Latitude_Location_11));
                    note.put("Date", Arrays.asList(Date_Location_1,Date_Location_2,Date_Location_3,Date_Location_4,Date_Location_5,Date_Location_6,Date_Location_7
                            ,Date_Location_8,Date_Location_9,Date_Location_10,Date_Location_11));
                    note.put("Hour", Arrays.asList(Hour_Location_1,Hour_Location_2,Hour_Location_3,Hour_Location_4,Hour_Location_5,Hour_Location_6,Hour_Location_7
                            ,Hour_Location_8,Hour_Location_9,Hour_Location_10,Hour_Location_11));
                } else if (saveLocationForSend == 12) {
                    note.put("Longitude", Arrays.asList(Longitude_Location_1,Longitude_Location_2,Longitude_Location_3,Longitude_Location_4,Longitude_Location_5
                            ,Longitude_Location_6,Longitude_Location_7,Longitude_Location_8,Longitude_Location_9,Longitude_Location_10,Longitude_Location_11
                    ,Longitude_Location_12));
                    note.put("Latitude", Arrays.asList(Latitude_Location_1,Latitude_Location_2,Latitude_Location_3,Latitude_Location_4,Latitude_Location_5
                            ,Latitude_Location_6,Latitude_Location_7,Latitude_Location_8,Latitude_Location_9,Latitude_Location_10,Latitude_Location_11
                    ,Latitude_Location_12));
                    note.put("Date", Arrays.asList(Date_Location_1,Date_Location_2,Date_Location_3,Date_Location_4,Date_Location_5,Date_Location_6,Date_Location_7
                            ,Date_Location_8,Date_Location_9,Date_Location_10,Date_Location_11,Date_Location_12));
                    note.put("Hour", Arrays.asList(Hour_Location_1,Hour_Location_2,Hour_Location_3,Hour_Location_4,Hour_Location_5,Hour_Location_6,Hour_Location_7
                            ,Hour_Location_8,Hour_Location_9,Hour_Location_10,Hour_Location_11,Hour_Location_12));
                } else if (saveLocationForSend == 13) {
                    note.put("Longitude", Arrays.asList(Longitude_Location_1,Longitude_Location_2,Longitude_Location_3,Longitude_Location_4,Longitude_Location_5
                            ,Longitude_Location_6,Longitude_Location_7,Longitude_Location_8,Longitude_Location_9,Longitude_Location_10,Longitude_Location_11
                            ,Longitude_Location_12,Longitude_Location_13));
                    note.put("Latitude", Arrays.asList(Latitude_Location_1,Latitude_Location_2,Latitude_Location_3,Latitude_Location_4,Latitude_Location_5
                            ,Latitude_Location_6,Latitude_Location_7,Latitude_Location_8,Latitude_Location_9,Latitude_Location_10,Latitude_Location_11
                            ,Latitude_Location_12,Latitude_Location_13));
                    note.put("Date", Arrays.asList(Date_Location_1,Date_Location_2,Date_Location_3,Date_Location_4,Date_Location_5,Date_Location_6,Date_Location_7
                            ,Date_Location_8,Date_Location_9,Date_Location_10,Date_Location_11,Date_Location_12,Date_Location_13));
                    note.put("Hour", Arrays.asList(Hour_Location_1,Hour_Location_2,Hour_Location_3,Hour_Location_4,Hour_Location_5,Hour_Location_6,Hour_Location_7
                            ,Hour_Location_8,Hour_Location_9,Hour_Location_10,Hour_Location_11,Hour_Location_12,Hour_Location_13));
                } else if (saveLocationForSend == 14) {
                    note.put("Longitude", Arrays.asList(Longitude_Location_1,Longitude_Location_2,Longitude_Location_3,Longitude_Location_4,Longitude_Location_5
                            ,Longitude_Location_6,Longitude_Location_7,Longitude_Location_8,Longitude_Location_9,Longitude_Location_10,Longitude_Location_11
                            ,Longitude_Location_12,Longitude_Location_13,Longitude_Location_14));
                    note.put("Latitude", Arrays.asList(Latitude_Location_1,Latitude_Location_2,Latitude_Location_3,Latitude_Location_4,Latitude_Location_5
                            ,Latitude_Location_6,Latitude_Location_7,Latitude_Location_8,Latitude_Location_9,Latitude_Location_10,Latitude_Location_11
                            ,Latitude_Location_12,Latitude_Location_13,Latitude_Location_14));
                    note.put("Date", Arrays.asList(Date_Location_1,Date_Location_2,Date_Location_3,Date_Location_4,Date_Location_5,Date_Location_6,Date_Location_7
                            ,Date_Location_8,Date_Location_9,Date_Location_10,Date_Location_11,Date_Location_12,Date_Location_13,Date_Location_14));
                    note.put("Hour", Arrays.asList(Hour_Location_1,Hour_Location_2,Hour_Location_3,Hour_Location_4,Hour_Location_5,Hour_Location_6,Hour_Location_7
                            ,Hour_Location_8,Hour_Location_9,Hour_Location_10,Hour_Location_11,Hour_Location_12,Hour_Location_13,Hour_Location_14));
                } else if (saveLocationForSend == 15) {
                    note.put("Longitude", Arrays.asList(Longitude_Location_1,Longitude_Location_2,Longitude_Location_3,Longitude_Location_4,Longitude_Location_5
                            ,Longitude_Location_6,Longitude_Location_7,Longitude_Location_8,Longitude_Location_9,Longitude_Location_10,Longitude_Location_11
                            ,Longitude_Location_12,Longitude_Location_13,Longitude_Location_14,Longitude_Location_15));
                    note.put("Latitude", Arrays.asList(Latitude_Location_1,Latitude_Location_2,Latitude_Location_3,Latitude_Location_4,Latitude_Location_5
                            ,Latitude_Location_6,Latitude_Location_7,Latitude_Location_8,Latitude_Location_9,Latitude_Location_10,Latitude_Location_11
                            ,Latitude_Location_12,Latitude_Location_13,Latitude_Location_14,Latitude_Location_15));
                    note.put("Date", Arrays.asList(Date_Location_1,Date_Location_2,Date_Location_3,Date_Location_4,Date_Location_5,Date_Location_6,Date_Location_7
                            ,Date_Location_8,Date_Location_9,Date_Location_10,Date_Location_11,Date_Location_12,Date_Location_13,Date_Location_14
                    ,Date_Location_15));
                    note.put("Hour", Arrays.asList(Hour_Location_1,Hour_Location_2,Hour_Location_3,Hour_Location_4,Hour_Location_5,Hour_Location_6,Hour_Location_7
                            ,Hour_Location_8,Hour_Location_9,Hour_Location_10,Hour_Location_11,Hour_Location_12,Hour_Location_13,Hour_Location_14
                    ,Hour_Location_15));
                } else if (saveLocationForSend == 16) {
                    note.put("Longitude", Arrays.asList(Longitude_Location_1,Longitude_Location_2,Longitude_Location_3,Longitude_Location_4,Longitude_Location_5
                            ,Longitude_Location_6,Longitude_Location_7,Longitude_Location_8,Longitude_Location_9,Longitude_Location_10,Longitude_Location_11
                            ,Longitude_Location_12,Longitude_Location_13,Longitude_Location_14,Longitude_Location_15,Longitude_Location_16));
                    note.put("Latitude", Arrays.asList(Latitude_Location_1,Latitude_Location_2,Latitude_Location_3,Latitude_Location_4,Latitude_Location_5
                            ,Latitude_Location_6,Latitude_Location_7,Latitude_Location_8,Latitude_Location_9,Latitude_Location_10,Latitude_Location_11
                            ,Latitude_Location_12,Latitude_Location_13,Latitude_Location_14,Latitude_Location_15,Latitude_Location_16));
                    note.put("Date", Arrays.asList(Date_Location_1,Date_Location_2,Date_Location_3,Date_Location_4,Date_Location_5,Date_Location_6,Date_Location_7
                            ,Date_Location_8,Date_Location_9,Date_Location_10,Date_Location_11,Date_Location_12,Date_Location_13,Date_Location_14
                            ,Date_Location_15,Date_Location_16));
                    note.put("Hour", Arrays.asList(Hour_Location_1,Hour_Location_2,Hour_Location_3,Hour_Location_4,Hour_Location_5,Hour_Location_6,Hour_Location_7
                            ,Hour_Location_8,Hour_Location_9,Hour_Location_10,Hour_Location_11,Hour_Location_12,Hour_Location_13,Hour_Location_14
                            ,Hour_Location_15,Hour_Location_16));
                } else if (saveLocationForSend == 17) {
                    note.put("Longitude", Arrays.asList(Longitude_Location_1,Longitude_Location_2,Longitude_Location_3,Longitude_Location_4,Longitude_Location_5
                            ,Longitude_Location_6,Longitude_Location_7,Longitude_Location_8,Longitude_Location_9,Longitude_Location_10,Longitude_Location_11
                            ,Longitude_Location_12,Longitude_Location_13,Longitude_Location_14,Longitude_Location_15,Longitude_Location_16
                    ,Longitude_Location_17));
                    note.put("Latitude", Arrays.asList(Latitude_Location_1,Latitude_Location_2,Latitude_Location_3,Latitude_Location_4,Latitude_Location_5
                            ,Latitude_Location_6,Latitude_Location_7,Latitude_Location_8,Latitude_Location_9,Latitude_Location_10,Latitude_Location_11
                            ,Latitude_Location_12,Latitude_Location_13,Latitude_Location_14,Latitude_Location_15,Latitude_Location_16
                    ,Latitude_Location_17));
                    note.put("Date", Arrays.asList(Date_Location_1,Date_Location_2,Date_Location_3,Date_Location_4,Date_Location_5,Date_Location_6,Date_Location_7
                            ,Date_Location_8,Date_Location_9,Date_Location_10,Date_Location_11,Date_Location_12,Date_Location_13,Date_Location_14
                            ,Date_Location_15,Date_Location_16,Date_Location_17));
                    note.put("Hour", Arrays.asList(Hour_Location_1,Hour_Location_2,Hour_Location_3,Hour_Location_4,Hour_Location_5,Hour_Location_6,Hour_Location_7
                            ,Hour_Location_8,Hour_Location_9,Hour_Location_10,Hour_Location_11,Hour_Location_12,Hour_Location_13,Hour_Location_14
                            ,Hour_Location_15,Hour_Location_16,Hour_Location_17));
                } else if (saveLocationForSend == 18) {
                    note.put("Longitude", Arrays.asList(Longitude_Location_1,Longitude_Location_2,Longitude_Location_3,Longitude_Location_4,Longitude_Location_5
                            ,Longitude_Location_6,Longitude_Location_7,Longitude_Location_8,Longitude_Location_9,Longitude_Location_10,Longitude_Location_11
                            ,Longitude_Location_12,Longitude_Location_13,Longitude_Location_14,Longitude_Location_15,Longitude_Location_16
                            ,Longitude_Location_17,Longitude_Location_18));
                    note.put("Latitude", Arrays.asList(Latitude_Location_1,Latitude_Location_2,Latitude_Location_3,Latitude_Location_4,Latitude_Location_5
                            ,Latitude_Location_6,Latitude_Location_7,Latitude_Location_8,Latitude_Location_9,Latitude_Location_10,Latitude_Location_11
                            ,Latitude_Location_12,Latitude_Location_13,Latitude_Location_14,Latitude_Location_15,Latitude_Location_16
                            ,Latitude_Location_17,Latitude_Location_18));
                    note.put("Date", Arrays.asList(Date_Location_1,Date_Location_2,Date_Location_3,Date_Location_4,Date_Location_5,Date_Location_6,Date_Location_7
                            ,Date_Location_8,Date_Location_9,Date_Location_10,Date_Location_11,Date_Location_12,Date_Location_13,Date_Location_14
                            ,Date_Location_15,Date_Location_16,Date_Location_17,Date_Location_18));
                    note.put("Hour", Arrays.asList(Hour_Location_1,Hour_Location_2,Hour_Location_3,Hour_Location_4,Hour_Location_5,Hour_Location_6,Hour_Location_7
                            ,Hour_Location_8,Hour_Location_9,Hour_Location_10,Hour_Location_11,Hour_Location_12,Hour_Location_13,Hour_Location_14
                            ,Hour_Location_15,Hour_Location_16,Hour_Location_17,Hour_Location_18));
                } else if (saveLocationForSend == 19) {
                    note.put("Longitude", Arrays.asList(Longitude_Location_1,Longitude_Location_2,Longitude_Location_3,Longitude_Location_4,Longitude_Location_5
                            ,Longitude_Location_6,Longitude_Location_7,Longitude_Location_8,Longitude_Location_9,Longitude_Location_10,Longitude_Location_11
                            ,Longitude_Location_12,Longitude_Location_13,Longitude_Location_14,Longitude_Location_15,Longitude_Location_16
                            ,Longitude_Location_17,Longitude_Location_18,Longitude_Location_19));
                    note.put("Latitude", Arrays.asList(Latitude_Location_1,Latitude_Location_2,Latitude_Location_3,Latitude_Location_4,Latitude_Location_5
                            ,Latitude_Location_6,Latitude_Location_7,Latitude_Location_8,Latitude_Location_9,Latitude_Location_10,Latitude_Location_11
                            ,Latitude_Location_12,Latitude_Location_13,Latitude_Location_14,Latitude_Location_15,Latitude_Location_16
                            ,Latitude_Location_17,Latitude_Location_18,Latitude_Location_19));
                    note.put("Date", Arrays.asList(Date_Location_1,Date_Location_2,Date_Location_3,Date_Location_4,Date_Location_5,Date_Location_6,Date_Location_7
                            ,Date_Location_8,Date_Location_9,Date_Location_10,Date_Location_11,Date_Location_12,Date_Location_13,Date_Location_14
                            ,Date_Location_15,Date_Location_16,Date_Location_17,Date_Location_18,Date_Location_19));
                    note.put("Hour", Arrays.asList(Hour_Location_1,Hour_Location_2,Hour_Location_3,Hour_Location_4,Hour_Location_5,Hour_Location_6,Hour_Location_7
                            ,Hour_Location_8,Hour_Location_9,Hour_Location_10,Hour_Location_11,Hour_Location_12,Hour_Location_13,Hour_Location_14
                            ,Hour_Location_15,Hour_Location_16,Hour_Location_17,Hour_Location_18,Hour_Location_19));
                } else if (saveLocationForSend == 20) {
                    note.put("Longitude", Arrays.asList(Longitude_Location_1,Longitude_Location_2,Longitude_Location_3,Longitude_Location_4,Longitude_Location_5
                            ,Longitude_Location_6,Longitude_Location_7,Longitude_Location_8,Longitude_Location_9,Longitude_Location_10,Longitude_Location_11
                            ,Longitude_Location_12,Longitude_Location_13,Longitude_Location_14,Longitude_Location_15,Longitude_Location_16
                            ,Longitude_Location_17,Longitude_Location_18,Longitude_Location_19,Longitude_Location_20));
                    note.put("Latitude", Arrays.asList(Latitude_Location_1,Latitude_Location_2,Latitude_Location_3,Latitude_Location_4,Latitude_Location_5
                            ,Latitude_Location_6,Latitude_Location_7,Latitude_Location_8,Latitude_Location_9,Latitude_Location_10,Latitude_Location_11
                            ,Latitude_Location_12,Latitude_Location_13,Latitude_Location_14,Latitude_Location_15,Latitude_Location_16
                            ,Latitude_Location_17,Latitude_Location_18,Latitude_Location_19,Latitude_Location_20));
                    note.put("Date", Arrays.asList(Date_Location_1,Date_Location_2,Date_Location_3,Date_Location_4,Date_Location_5,Date_Location_6,Date_Location_7
                            ,Date_Location_8,Date_Location_9,Date_Location_10,Date_Location_11,Date_Location_12,Date_Location_13,Date_Location_14
                            ,Date_Location_15,Date_Location_16,Date_Location_17,Date_Location_18,Date_Location_19,Date_Location_20));
                    note.put("Hour", Arrays.asList(Hour_Location_1,Hour_Location_2,Hour_Location_3,Hour_Location_4,Hour_Location_5,Hour_Location_6,Hour_Location_7
                            ,Hour_Location_8,Hour_Location_9,Hour_Location_10,Hour_Location_11,Hour_Location_12,Hour_Location_13,Hour_Location_14
                            ,Hour_Location_15,Hour_Location_16,Hour_Location_17,Hour_Location_18,Hour_Location_19,Hour_Location_20));
                } else if (saveLocationForSend == 21) {
                    note.put("Longitude", Arrays.asList(Longitude_Location_1,Longitude_Location_2,Longitude_Location_3,Longitude_Location_4,Longitude_Location_5
                            ,Longitude_Location_6,Longitude_Location_7,Longitude_Location_8,Longitude_Location_9,Longitude_Location_10,Longitude_Location_11
                            ,Longitude_Location_12,Longitude_Location_13,Longitude_Location_14,Longitude_Location_15,Longitude_Location_16
                            ,Longitude_Location_17,Longitude_Location_18,Longitude_Location_19,Longitude_Location_20,Longitude_Location_21));
                    note.put("Latitude", Arrays.asList(Latitude_Location_1,Latitude_Location_2,Latitude_Location_3,Latitude_Location_4,Latitude_Location_5
                            ,Latitude_Location_6,Latitude_Location_7,Latitude_Location_8,Latitude_Location_9,Latitude_Location_10,Latitude_Location_11
                            ,Latitude_Location_12,Latitude_Location_13,Latitude_Location_14,Latitude_Location_15,Latitude_Location_16
                            ,Latitude_Location_17,Latitude_Location_18,Latitude_Location_19,Latitude_Location_20,Latitude_Location_21));
                    note.put("Date", Arrays.asList(Date_Location_1,Date_Location_2,Date_Location_3,Date_Location_4,Date_Location_5,Date_Location_6,Date_Location_7
                            ,Date_Location_8,Date_Location_9,Date_Location_10,Date_Location_11,Date_Location_12,Date_Location_13,Date_Location_14
                            ,Date_Location_15,Date_Location_16,Date_Location_17,Date_Location_18,Date_Location_19,Date_Location_20,Date_Location_21));
                    note.put("Hour", Arrays.asList(Hour_Location_1,Hour_Location_2,Hour_Location_3,Hour_Location_4,Hour_Location_5,Hour_Location_6,Hour_Location_7
                            ,Hour_Location_8,Hour_Location_9,Hour_Location_10,Hour_Location_11,Hour_Location_12,Hour_Location_13,Hour_Location_14
                            ,Hour_Location_15,Hour_Location_16,Hour_Location_17,Hour_Location_18,Hour_Location_19,Hour_Location_20,Hour_Location_21));
                } else if (saveLocationForSend == 22) {
                    note.put("Longitude", Arrays.asList(Longitude_Location_1,Longitude_Location_2,Longitude_Location_3,Longitude_Location_4,Longitude_Location_5
                            ,Longitude_Location_6,Longitude_Location_7,Longitude_Location_8,Longitude_Location_9,Longitude_Location_10,Longitude_Location_11
                            ,Longitude_Location_12,Longitude_Location_13,Longitude_Location_14,Longitude_Location_15,Longitude_Location_16
                            ,Longitude_Location_17,Longitude_Location_18,Longitude_Location_19,Longitude_Location_20,Longitude_Location_21
                    ,Longitude_Location_22));
                    note.put("Latitude", Arrays.asList(Latitude_Location_1,Latitude_Location_2,Latitude_Location_3,Latitude_Location_4,Latitude_Location_5
                            ,Latitude_Location_6,Latitude_Location_7,Latitude_Location_8,Latitude_Location_9,Latitude_Location_10,Latitude_Location_11
                            ,Latitude_Location_12,Latitude_Location_13,Latitude_Location_14,Latitude_Location_15,Latitude_Location_16
                            ,Latitude_Location_17,Latitude_Location_18,Latitude_Location_19,Latitude_Location_20,Latitude_Location_21
                    ,Latitude_Location_22));
                    note.put("Date", Arrays.asList(Date_Location_1,Date_Location_2,Date_Location_3,Date_Location_4,Date_Location_5,Date_Location_6,Date_Location_7
                            ,Date_Location_8,Date_Location_9,Date_Location_10,Date_Location_11,Date_Location_12,Date_Location_13,Date_Location_14
                            ,Date_Location_15,Date_Location_16,Date_Location_17,Date_Location_18,Date_Location_19,Date_Location_20,Date_Location_21
                    ,Date_Location_22));
                    note.put("Hour", Arrays.asList(Hour_Location_1,Hour_Location_2,Hour_Location_3,Hour_Location_4,Hour_Location_5,Hour_Location_6,Hour_Location_7
                            ,Hour_Location_8,Hour_Location_9,Hour_Location_10,Hour_Location_11,Hour_Location_12,Hour_Location_13,Hour_Location_14
                            ,Hour_Location_15,Hour_Location_16,Hour_Location_17,Hour_Location_18,Hour_Location_19,Hour_Location_20,Hour_Location_21
                    ,Hour_Location_22));
                } else if (saveLocationForSend == 23) {
                    note.put("Longitude", Arrays.asList(Longitude_Location_1,Longitude_Location_2,Longitude_Location_3,Longitude_Location_4,Longitude_Location_5
                            ,Longitude_Location_6,Longitude_Location_7,Longitude_Location_8,Longitude_Location_9,Longitude_Location_10,Longitude_Location_11
                            ,Longitude_Location_12,Longitude_Location_13,Longitude_Location_14,Longitude_Location_15,Longitude_Location_16
                            ,Longitude_Location_17,Longitude_Location_18,Longitude_Location_19,Longitude_Location_20,Longitude_Location_21
                            ,Longitude_Location_22,Longitude_Location_23));
                    note.put("Latitude", Arrays.asList(Latitude_Location_1,Latitude_Location_2,Latitude_Location_3,Latitude_Location_4,Latitude_Location_5
                            ,Latitude_Location_6,Latitude_Location_7,Latitude_Location_8,Latitude_Location_9,Latitude_Location_10,Latitude_Location_11
                            ,Latitude_Location_12,Latitude_Location_13,Latitude_Location_14,Latitude_Location_15,Latitude_Location_16
                            ,Latitude_Location_17,Latitude_Location_18,Latitude_Location_19,Latitude_Location_20,Latitude_Location_21
                            ,Latitude_Location_22,Latitude_Location_23));
                    note.put("Date", Arrays.asList(Date_Location_1,Date_Location_2,Date_Location_3,Date_Location_4,Date_Location_5,Date_Location_6,Date_Location_7
                            ,Date_Location_8,Date_Location_9,Date_Location_10,Date_Location_11,Date_Location_12,Date_Location_13,Date_Location_14
                            ,Date_Location_15,Date_Location_16,Date_Location_17,Date_Location_18,Date_Location_19,Date_Location_20,Date_Location_21
                            ,Date_Location_22,Date_Location_23));
                    note.put("Hour", Arrays.asList(Hour_Location_1,Hour_Location_2,Hour_Location_3,Hour_Location_4,Hour_Location_5,Hour_Location_6,Hour_Location_7
                            ,Hour_Location_8,Hour_Location_9,Hour_Location_10,Hour_Location_11,Hour_Location_12,Hour_Location_13,Hour_Location_14
                            ,Hour_Location_15,Hour_Location_16,Hour_Location_17,Hour_Location_18,Hour_Location_19,Hour_Location_20,Hour_Location_21
                            ,Hour_Location_22,Hour_Location_23));
                } else if (saveLocationForSend == 24) {
                    note.put("Longitude", Arrays.asList(Longitude_Location_1,Longitude_Location_2,Longitude_Location_3,Longitude_Location_4,Longitude_Location_5
                            ,Longitude_Location_6,Longitude_Location_7,Longitude_Location_8,Longitude_Location_9,Longitude_Location_10,Longitude_Location_11
                            ,Longitude_Location_12,Longitude_Location_13,Longitude_Location_14,Longitude_Location_15,Longitude_Location_16
                            ,Longitude_Location_17,Longitude_Location_18,Longitude_Location_19,Longitude_Location_20,Longitude_Location_21
                            ,Longitude_Location_22,Longitude_Location_23,Longitude_Location_24));
                    note.put("Latitude", Arrays.asList(Latitude_Location_1,Latitude_Location_2,Latitude_Location_3,Latitude_Location_4,Latitude_Location_5
                            ,Latitude_Location_6,Latitude_Location_7,Latitude_Location_8,Latitude_Location_9,Latitude_Location_10,Latitude_Location_11
                            ,Latitude_Location_12,Latitude_Location_13,Latitude_Location_14,Latitude_Location_15,Latitude_Location_16
                            ,Latitude_Location_17,Latitude_Location_18,Latitude_Location_19,Latitude_Location_20,Latitude_Location_21
                            ,Latitude_Location_22,Latitude_Location_23,Latitude_Location_24));
                    note.put("Date", Arrays.asList(Date_Location_1,Date_Location_2,Date_Location_3,Date_Location_4,Date_Location_5,Date_Location_6,Date_Location_7
                            ,Date_Location_8,Date_Location_9,Date_Location_10,Date_Location_11,Date_Location_12,Date_Location_13,Date_Location_14
                            ,Date_Location_15,Date_Location_16,Date_Location_17,Date_Location_18,Date_Location_19,Date_Location_20,Date_Location_21
                            ,Date_Location_22,Date_Location_23,Date_Location_24));
                    note.put("Hour", Arrays.asList(Hour_Location_1,Hour_Location_2,Hour_Location_3,Hour_Location_4,Hour_Location_5,Hour_Location_6,Hour_Location_7
                            ,Hour_Location_8,Hour_Location_9,Hour_Location_10,Hour_Location_11,Hour_Location_12,Hour_Location_13,Hour_Location_14
                            ,Hour_Location_15,Hour_Location_16,Hour_Location_17,Hour_Location_18,Hour_Location_19,Hour_Location_20,Hour_Location_21
                            ,Hour_Location_22,Hour_Location_23,Hour_Location_24));
                }

                note.saveInBackground(new BacktoryCallBack<Void>() {
                    @Override
                    public void onResponse(BacktoryResponse<Void> response) {
                        if (response.isSuccessful()) {
                            // successful save. good place to show a toast
                        } else {
                            // see response.message() to know the cause of error
                        }
                    }
                });

            }
        }

    }
}

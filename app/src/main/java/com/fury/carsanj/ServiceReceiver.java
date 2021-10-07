package com.fury.carsanj;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

import com.backtory.java.internal.BacktoryCallBack;
import com.backtory.java.internal.BacktoryObject;
import com.backtory.java.internal.BacktoryResponse;
import com.backtory.java.internal.BacktoryUser;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * Created by fury on 11/3/2017.
 */
public class ServiceReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, final Intent intent) {

        TelephonyManager telephony = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        telephony.listen(new PhoneStateListener(){
            @Override
            public void onCallStateChanged(int state2, String incomingNumber) {
                super.onCallStateChanged(state2, incomingNumber);

                if (incomingNumber == null){
                    incomingNumber = "0123456";
                }else if (Objects.equals(incomingNumber, "")){
                    incomingNumber = "0123456";
                }

                // Get current user from backtory
                final BacktoryUser currentUser = BacktoryUser.getCurrentUser();

                final JalaliCalendar jalaliCalendar = new JalaliCalendar();
// java.util.Date dt = jalaliCalendar.getGregorianDate("1392/5/4");
                final String jDate = jalaliCalendar.getJalaliDate(new Date());

                long time= System.currentTimeMillis();
                String s1 = new SimpleDateFormat("HH:mm:ss").format(new Date(time));

                BacktoryObject note = new BacktoryObject("NumberCall");

                Calendar calendar = Calendar.getInstance();
                Date date = calendar.getTime();
                SimpleDateFormat format = new SimpleDateFormat("yyyy MM dd");
                String dateOutput = format.format(date);

                note.put("nameUser", currentUser.getFirstName() + " " + currentUser.getLastName());
                note.put("Number", incomingNumber);
                note.put("Date", jDate);
                note.put("Date2", dateOutput);
                note.put("Time", s1);
                note.put("all", "1");

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
        },PhoneStateListener.LISTEN_CALL_STATE);
    }
}

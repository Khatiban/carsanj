package com.fury.carsanj;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.backtory.java.internal.BacktoryCallBack;
import com.backtory.java.internal.BacktoryObject;
import com.backtory.java.internal.BacktoryResponse;
import com.backtory.java.internal.BacktoryUser;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by fury on 11/5/2017.
 */
public class NewFreeDay extends Activity {

    EditText new_moz,new_time,new_toz,new_time3,new_time2;
    TextView new_send;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newmd);

        new_moz = (EditText)findViewById(R.id.new_moz);
        new_time = (EditText)findViewById(R.id.new_time);
        new_toz = (EditText)findViewById(R.id.new_toz);
        new_time2 = (EditText)findViewById(R.id.new_time2);
        new_time3 = (EditText)findViewById(R.id.new_time3);
        new_send = (TextView) findViewById(R.id.new_send);
        back = (ImageView) findViewById(R.id.back);

        final JalaliCalendar jalaliCalendar = new JalaliCalendar();
// java.util.Date dt = jalaliCalendar.getGregorianDate("1392/5/4");
        final String jDate = jalaliCalendar.getJalaliDate(new Date());

        // Get current user from backtory
        final BacktoryUser currentUser = BacktoryUser.getCurrentUser();

        new_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n1 = String.valueOf(new_moz.getText());
                if (n1 != null){
                    String n2 = String.valueOf(new_time.getText());
                    if (n2 != null){
                            long time= System.currentTimeMillis();

                            String s1 = new SimpleDateFormat("HH:mm:ss").format(new Date(time));

                            Calendar calendar = Calendar.getInstance();
                            Date date = calendar.getTime();
                            SimpleDateFormat format = new SimpleDateFormat("yyyy MM dd");
                            String dateOutput = format.format(date);

                            BacktoryObject note = new BacktoryObject("FreeDay");
                            note.put("user", currentUser.getFirstName() + " " + currentUser.getLastName());
                            note.put("Data", jDate);
                            note.put("Data2", dateOutput);
                            note.put("time", s1);
                            note.put("Mozu", new_moz.getText().toString());
                            note.put("TimeMo", new_time.getText().toString());
                            note.put("DateStart", new_time3.getText().toString());
                            note.put("DateEnd", new_time2.getText().toString());
                            note.put("Tozih", new_toz.getText().toString());
                            note.put("all", "all");
                            note.put("Visible", 3);

                            note.saveInBackground(new BacktoryCallBack<Void>() {
                                @Override
                                public void onResponse(BacktoryResponse<Void> response) {
                                    if (response.isSuccessful()) {
                                        // successful save. good place to show a toast
                                        Toast.makeText(NewFreeDay.this, "با موفقیت ارسال شد", Toast.LENGTH_LONG).show();
                                    } else {
                                        // see response.message() to know the cause of error
                                        Toast.makeText(NewFreeDay.this, "متاسفانه ارسال نشد!", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });


                    }else {
                        Toast.makeText(NewFreeDay.this, "لطفا تاریخ روز مرخصی خود را وارد کنید", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(NewFreeDay.this, "لطفا موضوع را وارد کنید", Toast.LENGTH_SHORT).show();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(NewFreeDay.this,FreeDay.class);
                startActivity(back);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent back = new Intent(this,FreeDay.class);
        startActivity(back);
        finish();

    }
}

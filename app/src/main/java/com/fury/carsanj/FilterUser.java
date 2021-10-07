package com.fury.carsanj;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.backtory.java.internal.BacktoryCallBack;
import com.backtory.java.internal.BacktoryObject;
import com.backtory.java.internal.BacktoryQuery;
import com.backtory.java.internal.BacktoryResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by fury on 11/15/2017.
 */
public class FilterUser extends Activity {

    TextView texuser,texFactory,texSub,texTime;
    String s1;
    int s11,s2;
    long all;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filter);

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.gray));
        }


        ImageView filter = (ImageView)findViewById(R.id.filter);
        ImageView back = (ImageView)findViewById(R.id.back);
        texuser = (TextView) findViewById(R.id.texuser);
        texFactory = (TextView) findViewById(R.id.texFactory);
        texSub = (TextView) findViewById(R.id.texSub);
        texTime = (TextView) findViewById(R.id.texTime);

        texuser.setVisibility(View.GONE);
        texFactory.setVisibility(View.GONE);
        texSub.setVisibility(View.GONE);
        texTime.setVisibility(View.GONE);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                (new DialogFilter(FilterUser.this)).showDialog();
            }
        });

    }

    public void filter(boolean a, final boolean b, boolean c, boolean d, String aa, final long bb, String cc, String dd){

        s2 = 0;
        texuser.setVisibility(View.VISIBLE);
        texFactory.setVisibility(View.VISIBLE);
        texSub.setVisibility(View.VISIBLE);
        texTime.setVisibility(View.VISIBLE);

        BacktoryQuery todoQuery = new BacktoryQuery("Goxrsh");
        if (a){
            todoQuery.whereEqualTo("nameUser", aa);
            texuser.setText("نام کاربری : " + aa);
        }else {
            texuser.setText("نام کاربری : " );
        }
        if (c){
            todoQuery.whereEqualTo("work", cc);
            texFactory.setText("شرکت : " + cc);
        }else {
            texFactory.setText("شرکت : " );
        }
        if (d){
            todoQuery.whereEqualTo("sub", dd);
            texSub.setText("موضوع : " + dd);
        }else {
            texSub.setText("موضوع : " );
        }
        todoQuery.findInBackground(new BacktoryCallBack<List<BacktoryObject>>() {
            @Override
            public void onResponse(BacktoryResponse<List<BacktoryObject>> backtoryResponse) {
                if (backtoryResponse.isSuccessful()) {
                    List<BacktoryObject> todoNotes = backtoryResponse.body();
                    // for (BacktoryObject todo : todoNotes)
                    //   todo.getString("content")... 

                    for (BacktoryObject todo : todoNotes) {
                        int allwork = todo.getInt("allwork");
                        String Date = todo.getString("Data2");

                        if (b){
                            long timeInMilliseconds = 0;
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd");
                            try {
                                java.util.Date mDate = sdf.parse(Date);
                                timeInMilliseconds = mDate.getTime();
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                            if (timeInMilliseconds > bb) {

                                all = allwork;
                                s1 = new SimpleDateFormat("HH").format(new Date(all));
                                s11 = Integer.parseInt(s1);
                                s2 = s2 + s11;

                            }
                        }else {

                            all = allwork;
                            s1 = new SimpleDateFormat("HH").format(new Date(all));
                            s11 = Integer.parseInt(s1);
                            s2 = s2 + s11;

                        }
                    }

                    texTime.setText("ساعات کاری (ساعت) : " + s2);

                }
            }
        });

    }

}

package com.fury.carsanj;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.backtory.java.internal.BacktoryCallBack;
import com.backtory.java.internal.BacktoryObject;
import com.backtory.java.internal.BacktoryQuery;
import com.backtory.java.internal.BacktoryResponse;
import com.backtory.java.internal.BacktoryUser;
import com.backtory.java.internal.LoginResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Created by fury on 10/29/2017.
 */
public class PageUser extends Activity {

    SharedPreferences one_play_preferences;
    SharedPreferences.Editor one_play_editor;
    int checkUser;
    String firstname, lastname, startTime, endTime, ST, ET;
    int se;
    public static final String bc = "com.fury.carsanj.android.action.broadcast";

    DrawerLayout drawerlayout;

    RelativeLayout rl3;
    TextView textwork;
    ImageView imageStartWork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.root_1);

        one_play_preferences = getApplicationContext().getSharedPreferences("PROJECT_NAME", android.content.Context.MODE_PRIVATE);
        one_play_editor = one_play_preferences.edit();
        checkUser = one_play_preferences.getInt("checkUser", 0);
        se = one_play_preferences.getInt("se", 0);

        if (checkUser == 0) {
            Intent p = new Intent(PageUser.this, LoginActivity.class);
            startActivity(p);
            finish();
        } else if (checkUser == 1) {
            Intent p = new Intent(PageUser.this, SuperAdmin.class);
            startActivity(p);
            finish();
        } else if (checkUser == 2) {
            Intent p = new Intent(PageUser.this, Admin.class);
            startActivity(p);
            finish();
        }

        final JalaliCalendar jalaliCalendar = new JalaliCalendar();
// java.util.Date dt = jalaliCalendar.getGregorianDate("1392/5/4");
        final String jDate = jalaliCalendar.getJalaliDate(new Date());

        // Get current user from backtory
        final BacktoryUser currentUser = BacktoryUser.getCurrentUser();

// Check if currentUser is not empty
        if (currentUser != null) {
            // Log the result to output
            firstname = currentUser.getFirstName();
            lastname = currentUser.getLastName();

        } else {
            // No user is logged-in
            one_play_editor.putInt("checkUser", 0);
            one_play_editor.apply();
            Intent p = new Intent(PageUser.this, LoginResponse.class);
            startActivity(p);
            finish();
        }

        TextView textDate = (TextView) findViewById(R.id.textDate);
        TextView textName = (TextView) findViewById(R.id.textName);
        TextView lin_menu_sp = (TextView) findViewById(R.id.lin_menu_sp);//m hours
        TextView lin_menu_d = (TextView) findViewById(R.id.lin_menu_d);//m day
        TextView lin_menu_dm = (TextView) findViewById(R.id.lin_menu_dm);//support
        TextView lin_menu_2 = (TextView) findViewById(R.id.lin_menu_2);//support
        TextView lin_menu_3 = (TextView) findViewById(R.id.lin_menu_3);//support
        textwork = (TextView) findViewById(R.id.textwork);//support
        //sliding view
        drawerlayout = (DrawerLayout) findViewById(R.id.root_page_1);
        rl3 = (RelativeLayout) findViewById(R.id.rl3);
        imageStartWork = (ImageView) findViewById(R.id.imageStartWork);
        final ImageView menu = (ImageView) findViewById(R.id.menu);
        textDate.setText(jDate);
        textName.setText(firstname + " " + lastname);


        if (se == 1) {
            rl3.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            imageStartWork.setImageResource(R.drawable.endbtn);
            textwork.setText("پایان کار");
        } else if (se == 0) {
            rl3.setBackgroundColor(getResources().getColor(R.color.text));
            imageStartWork.setImageResource(R.drawable.startbtn);
            textwork.setText("شروع کار");
        }

        Intent startIntent = new Intent(PageUser.this, ForegroundService.class);
        startIntent.setAction("com.marothiatechs.foregroundservice.action.startforeground");
        startService(startIntent);

        imageStartWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                se = one_play_preferences.getInt("se", 0);
                if (se == 0) {

                    BacktoryQuery todoQuery = new BacktoryQuery("User");
                    todoQuery.whereEqualTo("user", currentUser.getFirstName() + " " + currentUser.getLastName());
                    todoQuery.setLimit(1);
                    todoQuery.findInBackground(new BacktoryCallBack<List<BacktoryObject>>() {
                        @Override
                        public void onResponse(BacktoryResponse<List<BacktoryObject>> backtoryResponse) {
                            if (backtoryResponse.isSuccessful()) {
                                List<BacktoryObject> todoNotes = backtoryResponse.body();
                                // for (BacktoryObject todo : todoNotes)
                                //   todo.getString("content")... 

                                try {
                                    for (BacktoryObject todo : todoNotes) {
                                        ST = todo.getString("StartTime");
                                        ET = todo.getString("EndTime");
                                    }
                                }catch (Exception e){
                                    Toast.makeText(PageUser.this,"error 2329", Toast.LENGTH_SHORT).show();
                                }


                                new MyTask().execute(ST,ET);

                            } else {
                                Toast.makeText(PageUser.this,"error 2309", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                } else if (se == 1) {

                    rl3.setBackgroundColor(getResources().getColor(R.color.text));
                    textwork.setText("شروع کار");
                    imageStartWork.setImageResource(R.drawable.startbtn);
                    textwork.setTextColor(getResources().getColor(R.color.text));
                    startTime = one_play_preferences.getString("timeworkstart", "");
                    long time2 = one_play_preferences.getLong("longtime", 0);
                    long time = System.currentTimeMillis();
                    String s1 = new SimpleDateFormat("HH:mm:ss").format(new Date(time));
                    endTime = s1;

                    long timeall = time - time2;

                    Log.e("timeall", String.valueOf(timeall));

                    int timeall2 = (int) timeall;
                    startTime = startTime + " تا " + endTime;
                    s1 = jDate + " " + s1;

                    (new DialogUserGozarsh(PageUser.this, jDate, s1, startTime, timeall2, currentUser.getFirstName() + " " + currentUser.getLastName())).showDialog();

                    one_play_editor.putInt("se", 0);
                    one_play_editor.apply();
                }
            }
        });


        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    if (drawerlayout.isDrawerOpen(Gravity.RIGHT)) {
                        drawerlayout.closeDrawer(Gravity.RIGHT);
                    } else {
                        drawerlayout.openDrawer(Gravity.RIGHT);
                    }

                } catch (Exception e) {
                    //FirebaseCrash.report(new Exception("10"));
                }
            }
        });

        lin_menu_sp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent h = new Intent(PageUser.this, FreeHours.class);
                startActivity(h);
            }
        });

        lin_menu_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent h = new Intent(PageUser.this, FreeDay.class);
                startActivity(h);
            }
        });

        lin_menu_dm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent h = new Intent(PageUser.this, SelectChat.class);
                startActivity(h);
            }
        });


        lin_menu_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                (new DialogEmail(PageUser.this)).showDialog();
            }
        });
        lin_menu_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                (new DialogPass(PageUser.this)).showDialog();
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    private class MyTask extends AsyncTask<String, Integer, String> {

        ProgressDialog dialog;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialog = new ProgressDialog(PageUser.this);
            dialog.setMessage("لطفا صبر کنید");
            dialog.show();

        }

        @Override
        protected String doInBackground(String... strings) {

            String mString1 = strings[0];
            String mString2 = strings[1];

            final long time = System.currentTimeMillis();

            Calendar c = Calendar.getInstance();
            SimpleDateFormat sdf5 = new SimpleDateFormat("HH:mm:ss");
            String strDate = sdf5.format(c.getTime());

            Log.e("timeInMi5", String.valueOf(strDate));

            long timeInMi = 0;
            long timeInMil = 0;
            long timeInMil2 = 0;
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                Date mdate2 = sdf.parse(strDate);
                timeInMi = mdate2.getTime();
                Log.e("timeInMi", String.valueOf(timeInMi));
            } catch (ParseException e) {
                e.printStackTrace();
            }try {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                Date mdate2 = sdf.parse(mString2);
                timeInMil2 = mdate2.getTime();
                Log.e("timeInMil2", String.valueOf(timeInMil2));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                Date mdate = sdf.parse(mString1);
                timeInMil = mdate.getTime();
                Log.e("timeInMil", String.valueOf(timeInMil));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (timeInMil <= timeInMi) {
                if (timeInMil2 >= timeInMi) {

                    /*int seconds = (int) (milliseconds / 1000) % 60 ;
                    int minutes = (int) ((milliseconds / (1000*60)) % 60);
                    int hours   = (int) ((milliseconds / (1000*60*60)) % 24);*/
                    String s1 = null;
                    try {
                        s1 = new SimpleDateFormat("HH:mm:ss").format(new Date(time));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        one_play_editor.putInt("se", 1);
                        one_play_editor.putString("timeworkstart", s1);
                        one_play_editor.putLong("longtime", time);
                        one_play_editor.apply();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //Toast.makeText(PageUser.this, "ok", Toast.LENGTH_LONG).show();
                    String s = "1";
                    return s;
                } else {
                    return "2";
                }
            } else {
                return "2";
            }


        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if (Objects.equals(s, "1")){
                try {
                    rl3.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    textwork.setText("پایان کار");
                    textwork.setTextColor(getResources().getColor(R.color.colorPrimary));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    imageStartWork.setImageResource(R.drawable.endbtn);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                dialog.dismiss();
            }else {
                Toast.makeText(PageUser.this, "خطا، زمان کار نمی باشد!", Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }


        }
    }

    public class MyTask2 extends AsyncTask<String, Integer, String> {

        ProgressDialog dialog;
        BacktoryObject note;
        final long time = System.currentTimeMillis();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialog = new ProgressDialog(PageUser.this);
            dialog.setMessage("لطفا صبر کنید");
            dialog.show();

        }

        @Override
        protected String doInBackground(String... strings) {

            String mString1 = strings[0];
            String mString2 = strings[1];
            String mString3 = strings[2];
            String mString4 = strings[3];
            String mString5 = strings[4];

            String StringMO = one_play_preferences.getString("StringMO", "");
            String Stringfactory = one_play_preferences.getString("Stringfactory", "");

            Calendar calendar = Calendar.getInstance();
                    Date date = calendar.getTime();
                    SimpleDateFormat format = new SimpleDateFormat("yyyy MM dd");
                    String dateOutput = format.format(date);

            note = new BacktoryObject("Goxrsh");
            note.put("nameUser", mString1);
            note.put("date", mString2);//
            note.put("Data2", dateOutput);
            note.put("timenow", mString3);//
            note.put("timework", startTime);//
            note.put("allwork", mString4);
            note.put("work", Stringfactory);//
            note.put("sub", StringMO);//
            note.put("text", mString5);//


            String s = "1";
            return s;


        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            dialog.dismiss();

            if (Objects.equals(s, "1")){

                note.saveInBackground(new BacktoryCallBack<Void>() {
                    @Override
                    public void onResponse(BacktoryResponse<Void> response) {
                        if (response.isSuccessful()) {
                            // successful save. good place to show a toast
                            Toast.makeText(PageUser.this, "با موفقیت ثبت شد", Toast.LENGTH_LONG).show();
                        } else {
                            // see response.message() to know the cause of error
                            Toast.makeText(PageUser.this, "متاسفانه ارسال نشد!", Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }else {
                Toast.makeText(PageUser.this, "خطا", Toast.LENGTH_LONG).show();
            }


        }
    }

}

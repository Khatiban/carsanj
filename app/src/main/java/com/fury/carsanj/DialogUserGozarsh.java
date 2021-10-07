package com.fury.carsanj;

import android.app.Dialog;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.backtory.java.internal.BacktoryCallBack;
import com.backtory.java.internal.BacktoryObject;
import com.backtory.java.internal.BacktoryQuery;
import com.backtory.java.internal.BacktoryResponse;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by fury on 9/21/2017.
 */
public class DialogUserGozarsh {

    private Dialog mDialog;
    private PageUser mDialogUniversalInfoActivity;
    TextView cancel, send,text_mo,text_factory;
    String jalaliCalendar,s1,startTime,nameUser,id;
    int timeall2;
    EditText sub;
    ScheduledThreadPoolExecutor mDialogDaemon_time;

    public DialogUserGozarsh(PageUser var1, String jalaliCalendar,String s1,String startTime,int timeall2,String nameUser) {
        this.mDialogUniversalInfoActivity = var1;
        this.jalaliCalendar = jalaliCalendar;
        this.s1 = s1;
        this.startTime = startTime;
        this.timeall2 = timeall2;
        this.nameUser = nameUser;
    }

    private void initDialogButtons() {
        this.cancel.setOnClickListener(new OnClickListener() {
            public void onClick(View var1) {
                DialogUserGozarsh.this.mDialog.dismiss();
            }
        });
        this.send.setOnClickListener(new OnClickListener() {
            public void onClick(View var1) {
                if (app_net.getInstance(mDialogUniversalInfoActivity).isOnline()) {

                    // str = str.replace("X", "");

                    //new PageUser.MyTask2().execute(nameUser,jalaliCalendar,s1,timeall2,sub.getText().toString());

                    String StringMO = mDialogUniversalInfoActivity.one_play_preferences.getString("StringMO", "");
                    String Stringfactory = mDialogUniversalInfoActivity.one_play_preferences.getString("Stringfactory", "");
                    Calendar calendar = Calendar.getInstance();
                    Date date = calendar.getTime();
                    SimpleDateFormat format = new SimpleDateFormat("yyyy MM dd");
                    String dateOutput = format.format(date);

                    BacktoryObject note = new BacktoryObject("Goxrsh");
                    note.put("nameUser", nameUser);
                    note.put("date", jalaliCalendar);//
                    note.put("Data2", dateOutput);
                    note.put("timenow", s1);//
                    note.put("timework", startTime);//
                    note.put("allwork", timeall2);
                    note.put("work", Stringfactory);//
                    note.put("sub", StringMO);//
                    note.put("text", sub.getText().toString());//
                    note.saveInBackground(new BacktoryCallBack<Void>() {
                        @Override
                        public void onResponse(BacktoryResponse<Void> response) {
                            if (response.isSuccessful()) {
                                // successful save. good place to show a toast
                                Toast.makeText(mDialogUniversalInfoActivity, "با موفقیت ثبت شد", Toast.LENGTH_LONG).show();
                                DialogUserGozarsh.this.mDialog.dismiss();
                            } else {
                                // see response.message() to know the cause of error
                                Toast.makeText(mDialogUniversalInfoActivity, "متاسفانه ارسال نشد!", Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                } else {
                    Toast.makeText(mDialogUniversalInfoActivity, "خطا در دسترسی اینترنت!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    public void dismissDialog() {
        this.mDialog.dismiss();
    }

    public void showDialog() {
        if (this.mDialog == null) {
            this.mDialog = new Dialog(this.mDialogUniversalInfoActivity, R.style.CustomDialogTheme);
        }
        this.mDialog.setContentView(R.layout.dialog_user_endwork);
        this.mDialog.setCancelable(false);
        this.mDialog.show();

        send = (TextView) mDialog.findViewById(R.id.send);
        cancel = (TextView) mDialog.findViewById(R.id.cancel);
        text_mo = (TextView) mDialog.findViewById(R.id.text_mo);
        text_factory = (TextView) mDialog.findViewById(R.id.text_factory);
        sub = (EditText) mDialog.findViewById(R.id.sub);


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
                    mDialogUniversalInfoActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            boolean go = mDialogUniversalInfoActivity.one_play_preferences.getBoolean("go_mo", false);
                            boolean go_factory = mDialogUniversalInfoActivity.one_play_preferences.getBoolean("go_factory", false);
                            String StringMO = mDialogUniversalInfoActivity.one_play_preferences.getString("StringMO", "");
                            String Stringfactory = mDialogUniversalInfoActivity.one_play_preferences.getString("Stringfactory", "");
                            if (go){
                                text_mo.setText(StringMO);
                                mDialogUniversalInfoActivity.one_play_editor.putBoolean("go_mo", false);
                                mDialogUniversalInfoActivity.one_play_editor.apply();
                            }
                            if (go_factory){
                                text_factory.setText(Stringfactory);
                                mDialogUniversalInfoActivity.one_play_editor.putBoolean("go_factory", false);
                                mDialogUniversalInfoActivity.one_play_editor.apply();
                            }

                        }
                    });
                }
            }, 0L, 1000L, TimeUnit.MILLISECONDS);

        } catch (Exception e) {
            mDialogDaemon_time.shutdown();
            mDialogDaemon_time = null;
        }



        text_factory.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View view) {

                BacktoryQuery todoQuery = new BacktoryQuery("About");

                todoQuery.whereEqualTo("name", "factory");

                todoQuery.findInBackground(new BacktoryCallBack<List<BacktoryObject>>() {
                    @Override
                    public void onResponse(BacktoryResponse<List<BacktoryObject>> backtoryResponse) {
                        if (backtoryResponse.isSuccessful()) {
                            List<BacktoryObject> todoNotes = backtoryResponse.body();
                            // for (BacktoryObject todo : todoNotes)
                            //   todo.getString("content")... 

                            PopupMenu menu = new PopupMenu(mDialogUniversalInfoActivity, view);
                            for (BacktoryObject todo : todoNotes) {
                                String SM = todo.getString("detail");
                                menu.getMenu().add(SM);
                            }

                            menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                                @Override
                                public boolean onMenuItemClick(MenuItem item) {

                                    String factor = item.getTitle().toString();

                                    mDialogUniversalInfoActivity.one_play_editor.putBoolean("go_factory", true);
                                    mDialogUniversalInfoActivity.one_play_editor.putString("Stringfactory", factor);
                                    mDialogUniversalInfoActivity.one_play_editor.apply();

                                    return true;
                                }

                            });
                            menu.show(); //showing popup menu
                        }
                    }
                });
            }
        });

        text_mo.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View view) {
                BacktoryQuery todoQuery = new BacktoryQuery("About");

                todoQuery.whereEqualTo("name", "mo");

                todoQuery.findInBackground(new BacktoryCallBack<List<BacktoryObject>>() {
                    @Override
                    public void onResponse(BacktoryResponse<List<BacktoryObject>> backtoryResponse) {
                        if (backtoryResponse.isSuccessful()) {
                            List<BacktoryObject> todoNotes = backtoryResponse.body();
                            // for (BacktoryObject todo : todoNotes)
                            //   todo.getString("content")... 

                            PopupMenu menu = new PopupMenu(mDialogUniversalInfoActivity, view);
                            for (BacktoryObject todo : todoNotes) {
                                String SM = todo.getString("detail");
                                menu.getMenu().add(SM);
                            }

                            menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                                @Override
                                public boolean onMenuItemClick(MenuItem item) {

                                    String factor = item.getTitle().toString();

                                    mDialogUniversalInfoActivity.one_play_editor.putBoolean("go_mo", true);
                                    mDialogUniversalInfoActivity.one_play_editor.putString("StringMO", factor);
                                    mDialogUniversalInfoActivity.one_play_editor.apply();

                                    return true;
                                }

                            });
                            menu.show(); //showing popup menu
                        }
                    }
                });


            }
        });


        initDialogButtons();
    }

}

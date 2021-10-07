package com.fury.carsanj;

import android.app.Dialog;
import android.support.v7.widget.PopupMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.backtory.java.internal.BacktoryCallBack;
import com.backtory.java.internal.BacktoryObject;
import com.backtory.java.internal.BacktoryQuery;
import com.backtory.java.internal.BacktoryResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by fury on 9/21/2017.
 */
public class DialogFilter {

    private Dialog mDialog;
    private FilterUser mDialogUniversalInfoActivity;
    TextView cancel, send,text_Name,text_Time,text_Fac,text_Work;
    String Name,Factory,Work;
    long timeInMilliseconds2;
    boolean n,f,w,t;

    public DialogFilter(FilterUser var1) {
        this.mDialogUniversalInfoActivity = var1;
    }

    private void initDialogButtons() {
        this.cancel.setOnClickListener(new OnClickListener() {
            public void onClick(View var1) {
                DialogFilter.this.mDialog.dismiss();
            }
        });

        text_Name.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View view) {
                //handle comment
                BacktoryQuery todoQuery = new BacktoryQuery("IDUser");
                todoQuery.whereEqualTo("all", "1");
                todoQuery.findInBackground(new BacktoryCallBack<List<BacktoryObject>>() {
                    @Override
                    public void onResponse(BacktoryResponse<List<BacktoryObject>> backtoryResponse) {
                        if (backtoryResponse.isSuccessful()) {

                            List<BacktoryObject> todoNotes = backtoryResponse.body();
                            // for (BacktoryObject todo : todoNotes)
                            //   todo.getString("content")... 

                            PopupMenu menu = new PopupMenu(mDialogUniversalInfoActivity, view);
                            for (BacktoryObject todo : todoNotes) {
                                String SM = todo.getString("UserName");
                                menu.getMenu().add(SM);
                            }

                            menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                                @Override
                                public boolean onMenuItemClick(MenuItem item) {

                                    n = true;
                                    Name = item.getTitle().toString();
                                    text_Name.setText(Name);

                                    return true;
                                }

                            });

                            menu.show(); //showing popup menu
                        }
                    }
                });
            }
        });

        text_Time.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                //handle share
                PopupMenu menu = new PopupMenu(mDialogUniversalInfoActivity, view);
                menu.getMenu().add(Menu.NONE, 1, 1, "دیروز");
                menu.getMenu().add(Menu.NONE, 2, 2, "دو روز پیش");
                menu.getMenu().add(Menu.NONE, 3, 3, "هفته پیش");
                menu.getMenu().add(Menu.NONE, 4, 4, "یک ماه پیش");
                menu.getMenu().add(Menu.NONE, 5, 5, "سه ماه پیش");
                menu.getMenu().add(Menu.NONE, 6, 6, "شش ماه پیش");
                menu.getMenu().add(Menu.NONE, 7, 7, "یک سال پیش");

                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        t = true;
                        int i = item.getItemId();
                        if (i == 1) {
                            //handle share
                            Calendar calendar = Calendar.getInstance();
                            calendar.add(Calendar.DAY_OF_MONTH, -1);
                            Date date = calendar.getTime();
                            SimpleDateFormat format = new SimpleDateFormat("yyyy MM dd");
                            String dateOutput = format.format(date);
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd");
                            try {
                                Date mDate = sdf.parse(dateOutput);
                                timeInMilliseconds2 = mDate.getTime();
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            return true;
                        } else if (i == 2) {
                            //handle comment
                            Calendar calendar = Calendar.getInstance();
                            calendar.add(Calendar.DAY_OF_MONTH, -2);
                            Date date = calendar.getTime();
                            SimpleDateFormat format = new SimpleDateFormat("yyyy MM dd");
                            String dateOutput = format.format(date);
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd");
                            try {
                                Date mDate = sdf.parse(dateOutput);
                                timeInMilliseconds2 = mDate.getTime();
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            return true;
                        } else if (i == 3) {
                            //handle comment
                            Calendar calendar = Calendar.getInstance();
                            calendar.add(Calendar.WEEK_OF_MONTH, -1);
                            Date date = calendar.getTime();
                            SimpleDateFormat format = new SimpleDateFormat("yyyy MM dd");
                            String dateOutput = format.format(date);
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd");
                            try {
                                Date mDate = sdf.parse(dateOutput);
                                timeInMilliseconds2 = mDate.getTime();
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            return true;
                        } else if (i == 4) {
                            //handle comment
                            Calendar calendar = Calendar.getInstance();
                            calendar.add(Calendar.MONTH, -1);
                            Date date = calendar.getTime();
                            SimpleDateFormat format = new SimpleDateFormat("yyyy MM dd");
                            String dateOutput = format.format(date);
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd");
                            try {
                                Date mDate = sdf.parse(dateOutput);
                                timeInMilliseconds2 = mDate.getTime();
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            return true;
                        } else if (i == 5) {
                            //handle comment
                            Calendar calendar = Calendar.getInstance();
                            calendar.add(Calendar.MONTH, -3);
                            Date date = calendar.getTime();
                            SimpleDateFormat format = new SimpleDateFormat("yyyy MM dd");
                            String dateOutput = format.format(date);
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd");
                            try {
                                Date mDate = sdf.parse(dateOutput);
                                timeInMilliseconds2 = mDate.getTime();
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            return true;
                        } else if (i == 6) {
                            //handle comment
                            Calendar calendar = Calendar.getInstance();
                            calendar.add(Calendar.MONTH, -6);
                            Date date = calendar.getTime();
                            SimpleDateFormat format = new SimpleDateFormat("yyyy MM dd");
                            String dateOutput = format.format(date);
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd");
                            try {
                                Date mDate = sdf.parse(dateOutput);
                                timeInMilliseconds2 = mDate.getTime();
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            return true;
                        } else if (i == 7) {
                            //handle comment
                            Calendar calendar = Calendar.getInstance();
                            calendar.add(Calendar.MONTH, -12);
                            Date date = calendar.getTime();
                            SimpleDateFormat format = new SimpleDateFormat("yyyy MM dd");
                            String dateOutput = format.format(date);
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd");
                            try {
                                Date mDate = sdf.parse(dateOutput);
                                timeInMilliseconds2 = mDate.getTime();
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            return true;
                        } else {
                            return false;
                        }
                    }

                });
                menu.show(); //showing popup menu

            }
        });

        text_Fac.setOnClickListener(new OnClickListener() {
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

                                    f = true;
                                    Factory = item.getTitle().toString();
                                    text_Fac.setText(Factory);

                                    return true;
                                }

                            });
                            menu.show(); //showing popup menu
                        }
                    }
                    });
            }
        });

        text_Work.setOnClickListener(new OnClickListener() {
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

                                    w = true;
                                    Work = item.getTitle().toString();
                                    text_Work.setText(Work);

                                    return true;
                                }

                            });
                            menu.show(); //showing popup menu
                        }
                    }
                });
            }
        });

        this.send.setOnClickListener(new OnClickListener() {
            public void onClick(View var1) {
                if (app_net.getInstance(mDialogUniversalInfoActivity).isOnline()) {

                    mDialogUniversalInfoActivity.filter(n,t,f,w,Name,timeInMilliseconds2,Factory,Work);
                    DialogFilter.this.mDialog.dismiss();

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
        this.mDialog.setContentView(R.layout.dialog_filter);
        this.mDialog.setCancelable(false);
        this.mDialog.show();

        w = false;
        f = false;
        t = false;
        n = false;

        send = (TextView) mDialog.findViewById(R.id.send);
        cancel = (TextView) mDialog.findViewById(R.id.cancel);
        text_Name = (TextView) mDialog.findViewById(R.id.text_Name);
        text_Time = (TextView) mDialog.findViewById(R.id.text_Time);
        text_Fac = (TextView) mDialog.findViewById(R.id.text_Fac);
        text_Work = (TextView) mDialog.findViewById(R.id.text_work);

        initDialogButtons();
    }

}

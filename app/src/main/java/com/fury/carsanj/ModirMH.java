package com.fury.carsanj;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.backtory.java.internal.BacktoryCallBack;
import com.backtory.java.internal.BacktoryObject;
import com.backtory.java.internal.BacktoryQuery;
import com.backtory.java.internal.BacktoryResponse;
import com.fury.carsanj.date.Data5;
import com.fury.carsanj.date.MoviesAdapter5;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by fury on 11/9/2017.
 */
public class ModirMH extends AppCompatActivity {

    boolean ti, nam;
    String nameuser;
    long timeInMilliseconds2;
    private RecyclerView recyclerView;
    ProgressDialog pDialog;
    static public Activity act;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mmh);

        act = this;


        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this,R.color.gray));
        }

        ImageView back = (ImageView)findViewById(R.id.back);
        ImageView filter = (ImageView) findViewById(R.id.filter);
        recyclerView = (RecyclerView) findViewById(R.id.rvAnimals);


        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                PopupMenu menu = new PopupMenu(ModirMH.this, view);
                menu.getMenu().add(Menu.NONE, 1, 1, "زمان");
                menu.getMenu().add(Menu.NONE, 2, 2, "کاربر");
                //registering popup with OnMenuItemClickListener
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        int i = item.getItemId();
                        if (i == 1) {
                            //handle share
                            PopupMenu menu = new PopupMenu(ModirMH.this, view);
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
                                            onFileterTime(timeInMilliseconds2);
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
                                            onFileterTime(timeInMilliseconds2);
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
                                            onFileterTime(timeInMilliseconds2);
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
                                            onFileterTime(timeInMilliseconds2);
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
                                            onFileterTime(timeInMilliseconds2);
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
                                            onFileterTime(timeInMilliseconds2);
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
                                            onFileterTime(timeInMilliseconds2);
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
                            return true;
                        } else if (i == 2) {
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

                                        PopupMenu menu = new PopupMenu(ModirMH.this, view);
                                        for (BacktoryObject todo : todoNotes) {
                                            String SM = todo.getString("UserName");
                                            menu.getMenu().add(SM);
                                        }

                                        menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                                            @Override
                                            public boolean onMenuItemClick(MenuItem item) {

                                                BacktoryQuery todoQuery = new BacktoryQuery("FreeHours");
                                                todoQuery.whereEqualTo("all", "all");
                                                todoQuery.whereEqualTo("user", item.getTitle().toString());
                                                nameuser = item.getTitle().toString();
                                                todoQuery.findInBackground(new BacktoryCallBack<List<BacktoryObject>>() {
                                                    @Override
                                                    public void onResponse(BacktoryResponse<List<BacktoryObject>> backtoryResponse) {
                                                        if (backtoryResponse.isSuccessful()) {
                                                            List<BacktoryObject> todoNotes = backtoryResponse.body();
                                                            // for (BacktoryObject todo : todoNotes)
                                                            //   todo.getString("content")... 

                                                            List<Data5> data = new ArrayList<>();
                                                            for (BacktoryObject todo : todoNotes) {
                                                                String user = todo.getString("user");
                                                                String time = todo.getString("time");
                                                                String TimeMo = todo.getString("TimeMo");
                                                                String Tozih = todo.getString("Tozih");
                                                                String SM = todo.getString("Mozu");
                                                                String SD = todo.getString("Data");
                                                                String Date = todo.getString("Data2");
                                                                int IV = todo.getInt("Visible");
                                                                String vis = null;
                                                                if (IV == 1){
                                                                    vis = "تایید شد";
                                                                }else if (IV == 2){
                                                                    vis = "رد شد";
                                                                }else if (IV == 3){
                                                                    vis = "در حال بررسی";
                                                                }

                                                                if (ti) {
                                                                    long timeInMilliseconds = 0;
                                                                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd");
                                                                    try {
                                                                        Date mDate = sdf.parse(Date);
                                                                        timeInMilliseconds = mDate.getTime();
                                                                    } catch (ParseException e) {
                                                                        e.printStackTrace();
                                                                    }

                                                                    if (timeInMilliseconds > timeInMilliseconds2) {
                                                                        data.add(new Data5(SM,SD,vis,user,time,TimeMo,Tozih,IV));
                                                                    }
                                                                } else {
                                                                    data.add(new Data5(SM,SD,vis,user,time,TimeMo,Tozih,IV));
                                                                }
                                                            }

                                                            MoviesAdapter5 adapter = new MoviesAdapter5(data, getApplication());
                                                            recyclerView.setAdapter(adapter);
                                                            recyclerView.setLayoutManager(new LinearLayoutManager(ModirMH.this));
                                                            recyclerView.scrollToPosition(adapter.getItemCount()-1);

                                                            RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
                                                            itemAnimator.setAddDuration(1000);
                                                            itemAnimator.setRemoveDuration(1000);
                                                            recyclerView.setItemAnimator(itemAnimator);

                                                        } else {
                                                        }
                                                    }
                                                });

                                                return true;
                                            }

                                        });

                                        menu.show(); //showing popup menu

                                    }
                                }
                            });
                            return true;
                        } else {
                            return false;
                        }
                    }
                });

                menu.show(); //showing popup menu

            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        BacktoryQuery todoQuery = new BacktoryQuery("FreeHours");
        todoQuery.whereEqualTo("all", "all");
        final ProgressDialog dialog = new ProgressDialog(ModirMH.this);
        dialog.setMessage("لطفا صبر کنید");
        dialog.show();
        todoQuery.findInBackground(new BacktoryCallBack<List<BacktoryObject>>() {
            @Override
            public void onResponse(BacktoryResponse<List<BacktoryObject>> backtoryResponse) {
                if (backtoryResponse.isSuccessful()) {
                    dialog.dismiss();
                    List<BacktoryObject> todoNotes = backtoryResponse.body();
                    // for (BacktoryObject todo : todoNotes)
                    //   todo.getString("content")... 

                    List<Data5> data = new ArrayList<>();
                    for (BacktoryObject todo : todoNotes){
                        String user = todo.getString("user");
                        String time = todo.getString("time");
                        String TimeMo = todo.getString("TimeMo");
                        String Tozih = todo.getString("Tozih");
                        String SM = todo.getString("Mozu");
                        String SD = todo.getString("Data");
                        int IV = todo.getInt("Visible");
                        String vis = null;
                        if (IV == 1){
                            vis = "تایید شد";
                        }else if (IV == 2){
                            vis = "رد شد";
                        }else if (IV == 3){
                            vis = "در حال بررسی";
                        }

                        data.add(new Data5(SM,SD,vis,user,time,TimeMo,Tozih,IV));
                    }

                    MoviesAdapter5 adapter = new MoviesAdapter5(data, getApplication());
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(ModirMH.this));
                    recyclerView.scrollToPosition(adapter.getItemCount()-1);

                    RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
                    itemAnimator.setAddDuration(1000);
                    itemAnimator.setRemoveDuration(1000);
                    recyclerView.setItemAnimator(itemAnimator);

                } else {
                    dialog.dismiss();
                }
            }
        });


    }


    public void onFileterTime(final long a) {

        BacktoryQuery todoQuery = new BacktoryQuery("FreeDay");
        todoQuery.whereEqualTo("all", "all");
        if (nam) {
            todoQuery.whereEqualTo("user", nameuser);
        }
        final ProgressDialog dialog = new ProgressDialog(ModirMH.this);
        dialog.setMessage("لطفا صبر کنید");
        dialog.show();
        todoQuery.findInBackground(new BacktoryCallBack<List<BacktoryObject>>() {
            @Override
            public void onResponse(BacktoryResponse<List<BacktoryObject>> backtoryResponse) {
                if (backtoryResponse.isSuccessful()) {
                    dialog.dismiss();
                    List<BacktoryObject> todoNotes = backtoryResponse.body();
                    // for (BacktoryObject todo : todoNotes)
                    //   todo.getString("content")... 

                    List<Data5> data = new ArrayList<>();
                    for (BacktoryObject todo : todoNotes) {
                        String user = todo.getString("user");
                        String time = todo.getString("time");
                        String TimeMo = todo.getString("TimeMo");
                        String Tozih = todo.getString("Tozih");
                        String SM = todo.getString("Mozu");
                        String SD = todo.getString("Data");
                        String Date = todo.getString("Data2");
                        int IV = todo.getInt("Visible");
                        String vis = null;
                        if (IV == 1){
                            vis = "تایید شد";
                        }else if (IV == 2){
                            vis = "رد شد";
                        }else if (IV == 3){
                            vis = "در حال بررسی";
                        }

                        long timeInMilliseconds = 0;
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd");
                        try {
                            Date mDate = sdf.parse(Date);
                            timeInMilliseconds = mDate.getTime();
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        if (timeInMilliseconds > a) {

                            data.add(new Data5(SM,SD,vis,user,time,TimeMo,Tozih,IV));
                        }

                    }

                    MoviesAdapter5 adapter = new MoviesAdapter5(data, getApplication());
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(ModirMH.this));
                    recyclerView.scrollToPosition(adapter.getItemCount()-1);

                    RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
                    itemAnimator.setAddDuration(1000);
                    itemAnimator.setRemoveDuration(1000);
                    recyclerView.setItemAnimator(itemAnimator);

                } else {
                    dialog.dismiss();
                }
            }
        });


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();

    }

}

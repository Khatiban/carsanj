package com.fury.carsanj;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
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
import com.fury.carsanj.date.Data2;
import com.fury.carsanj.date.MoviesAdapter2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by fury on 11/13/2017.
 */
public class ModirG extends AppCompatActivity {

    private RecyclerView recyclerView;
    ProgressDialog pDialog;
    String nameuser, factor, mozo;
    long timeInMilliseconds2;
    boolean ti, f, m;
    // XML node keys
    static final String KEY_Name = "Name";
    static final String KEY_moz = "moz";
    static final String KEY_Factory = "Factory";
    static final String KEY_Time = "Time";
    static final String KEY_Date = "Date";
    static final String KEY_Toz = "Toz";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mgoz2);

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.gray));
        }

        final Intent i = this.getIntent();

        Bundle extras = i.getExtras();
        if (extras != null) {
            nameuser = extras.getString("Username");
        }

        ti = false;
        f = false;

        ImageView back = (ImageView) findViewById(R.id.back);
        ImageView filter = (ImageView) findViewById(R.id.filter);
        recyclerView = (RecyclerView) findViewById(R.id.rvAnimals);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        BacktoryQuery todoQuery = new BacktoryQuery("Goxrsh");
        todoQuery.whereEqualTo("nameUser", nameuser);
        final ProgressDialog dialog = new ProgressDialog(ModirG.this);
        dialog.setMessage("لطفا صبر کنید");
        dialog.show();
        todoQuery.findInBackground(new BacktoryCallBack<List<BacktoryObject>>() {
            @Override
            public void onResponse(BacktoryResponse<List<BacktoryObject>> backtoryResponse) {
                dialog.dismiss();
                if (backtoryResponse.isSuccessful()) {
                    List<BacktoryObject> todoNotes = backtoryResponse.body();
                    // for (BacktoryObject todo : todoNotes)
                    //   todo.getString("content")... 

                    List<Data2> data = new ArrayList<>();
                    for (BacktoryObject todo : todoNotes) {
                        String user = todo.getString("nameUser");
                        String time = todo.getString("timenow");
                        String TimeMo = todo.getString("timework");
                        String Tozih = todo.getString("text");
                        String SM = todo.getString("sub");
                        //String SD = todo.getString("date");
                        String Fa = todo.getString("work");

                        data.add(new Data2( "نام : " + user, "موضوع : "  + SM, "شرکت : " + Fa, "زمان کار : "  + TimeMo, "توضیحات : " + Tozih, time));
                    }


                    MoviesAdapter2 adapter = new MoviesAdapter2(data, getApplication());
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(ModirG.this));
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


        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                PopupMenu menu = new PopupMenu(ModirG.this, view);
                menu.getMenu().add(Menu.NONE, 1, 1, "زمان");
                menu.getMenu().add(Menu.NONE, 2, 2, "شرکت");
                menu.getMenu().add(Menu.NONE, 3, 3, "موضوع");
                //registering popup with OnMenuItemClickListener
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        int i = item.getItemId();
                        if (i == 1) {

                            //handle share
                            PopupMenu menu = new PopupMenu(ModirG.this, view);
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
                            BacktoryQuery todoQuery = new BacktoryQuery("About");

                            todoQuery.whereEqualTo("name", "factory");

                            todoQuery.findInBackground(new BacktoryCallBack<List<BacktoryObject>>() {
                                @Override
                                public void onResponse(BacktoryResponse<List<BacktoryObject>> backtoryResponse) {
                                    if (backtoryResponse.isSuccessful()) {
                                        List<BacktoryObject> todoNotes = backtoryResponse.body();
                                        // for (BacktoryObject todo : todoNotes)
                                        //   todo.getString("content")... 

                                        PopupMenu menu = new PopupMenu(ModirG.this, view);
                                        for (BacktoryObject todo : todoNotes) {
                                            String SM = todo.getString("detail");
                                            menu.getMenu().add(SM);
                                        }

                                        menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                                            @Override
                                            public boolean onMenuItemClick(MenuItem item) {

                                                f = true;
                                                factor = item.getTitle().toString();

                                                BacktoryQuery todoQuery = new BacktoryQuery("Goxrsh");
                                                todoQuery.whereEqualTo("nameUser", nameuser);
                                                if (m) {
                                                    todoQuery.whereEqualTo("sub", mozo);
                                                }
                                                todoQuery.whereEqualTo("work", item.getTitle().toString());
                                                todoQuery.findInBackground(new BacktoryCallBack<List<BacktoryObject>>() {
                                                    @Override
                                                    public void onResponse(BacktoryResponse<List<BacktoryObject>> backtoryResponse) {
                                                        if (backtoryResponse.isSuccessful()) {
                                                            List<BacktoryObject> todoNotes = backtoryResponse.body();
                                                            // for (BacktoryObject todo : todoNotes)
                                                            //   todo.getString("content")... 

                                                            List<Data2> data = new ArrayList<>();
                                                            for (BacktoryObject todo : todoNotes) {
                                                                String user = todo.getString("nameUser");
                                                                String time = todo.getString("timenow");
                                                                String TimeMo = todo.getString("timework");
                                                                String Tozih = todo.getString("text");
                                                                String SM = todo.getString("sub");
                                                                //String SD = todo.getString("date");
                                                                String Fa = todo.getString("work");
                                                                String Date = todo.getString("Data2");

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
                                                                        data.add(new Data2( "نام : " + user, "موضوع : "  + SM, "شرکت : " + Fa, "زمان کار : "  + TimeMo, "توضیحات : " + Tozih, time));


                                                                    }
                                                                } else {
                                                                    data.add(new Data2( "نام : " + user, "موضوع : "  + SM, "شرکت : " + Fa, "زمان کار : "  + TimeMo, "توضیحات : " + Tozih, time));
                                                                }

                                                            }
                                                            MoviesAdapter2 adapter = new MoviesAdapter2(data, getApplication());
                                                            recyclerView.setAdapter(adapter);
                                                            recyclerView.setLayoutManager(new LinearLayoutManager(ModirG.this));
                                                            recyclerView.scrollToPosition(adapter.getItemCount()-1);

                                                            RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
                                                            itemAnimator.setAddDuration(1000);
                                                            itemAnimator.setRemoveDuration(1000);
                                                            recyclerView.setItemAnimator(itemAnimator);
                                                            adapter.notifyDataSetChanged();
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
                        } else if (i == 3) {

                            BacktoryQuery todoQuery = new BacktoryQuery("About");

                            todoQuery.whereEqualTo("name", "mo");

                            todoQuery.findInBackground(new BacktoryCallBack<List<BacktoryObject>>() {
                                @Override
                                public void onResponse(BacktoryResponse<List<BacktoryObject>> backtoryResponse) {
                                    if (backtoryResponse.isSuccessful()) {
                                        List<BacktoryObject> todoNotes = backtoryResponse.body();
                                        // for (BacktoryObject todo : todoNotes)
                                        //   todo.getString("content")... 

                                        PopupMenu menu = new PopupMenu(ModirG.this, view);
                                        for (BacktoryObject todo : todoNotes) {
                                            String SM = todo.getString("detail");
                                            menu.getMenu().add(SM);
                                        }

                                        menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                                            @Override
                                            public boolean onMenuItemClick(MenuItem item) {

                                                m = true;
                                                mozo = item.getTitle().toString();

                                                BacktoryQuery todoQuery = new BacktoryQuery("Goxrsh");
                                                todoQuery.whereEqualTo("nameUser", nameuser);
                                                if (f) {
                                                    todoQuery.whereEqualTo("work", factor);
                                                }
                                                todoQuery.whereEqualTo("sub", item.getTitle().toString());
                                                todoQuery.findInBackground(new BacktoryCallBack<List<BacktoryObject>>() {
                                                    @Override
                                                    public void onResponse(BacktoryResponse<List<BacktoryObject>> backtoryResponse) {
                                                        if (backtoryResponse.isSuccessful()) {
                                                            List<BacktoryObject> todoNotes = backtoryResponse.body();
                                                            // for (BacktoryObject todo : todoNotes)
                                                            //   todo.getString("content")... 

                                                            List<Data2> data = new ArrayList<>();
                                                            for (BacktoryObject todo : todoNotes) {
                                                                String user = todo.getString("nameUser");
                                                                String time = todo.getString("timenow");
                                                                String TimeMo = todo.getString("timework");
                                                                String Tozih = todo.getString("text");
                                                                String SM = todo.getString("sub");
                                                                //String SD = todo.getString("date");
                                                                String Fa = todo.getString("work");
                                                                String Date = todo.getString("Data2");

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
                                                                        data.add(new Data2( "نام : " + user, "موضوع : "  + SM, "شرکت : " + Fa, "زمان کار : "  + TimeMo, "توضیحات : " + Tozih, time));
                                                                    }
                                                                } else {
                                                                    data.add(new Data2( "نام : " + user, "موضوع : "  + SM, "شرکت : " + Fa, "زمان کار : "  + TimeMo, "توضیحات : " + Tozih, time));
                                                                }

                                                            }
                                                            MoviesAdapter2 adapter = new MoviesAdapter2(data, getApplication());
                                                            recyclerView.setAdapter(adapter);
                                                            recyclerView.setLayoutManager(new LinearLayoutManager(ModirG.this));
                                                            recyclerView.scrollToPosition(adapter.getItemCount()-1);

                                                            RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
                                                            itemAnimator.setAddDuration(1000);
                                                            itemAnimator.setRemoveDuration(1000);
                                                            recyclerView.setItemAnimator(itemAnimator);
                                                            adapter.notifyDataSetChanged();

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


    }


    public void onFileterTime(final long a) {

        ti = true;

        BacktoryQuery todoQuery = new BacktoryQuery("Goxrsh");
        todoQuery.whereEqualTo("nameUser", nameuser);
        if (f) {
            todoQuery.whereEqualTo("work", factor);
        }
        final ProgressDialog dialog = new ProgressDialog(ModirG.this);
        dialog.setMessage("لطفا صبر کنید");
        dialog.show();
        todoQuery.findInBackground(new BacktoryCallBack<List<BacktoryObject>>() {
            @Override
            public void onResponse(BacktoryResponse<List<BacktoryObject>> backtoryResponse) {
                dialog.dismiss();
                if (backtoryResponse.isSuccessful()) {
                    List<BacktoryObject> todoNotes = backtoryResponse.body();
                    // for (BacktoryObject todo : todoNotes)
                    //   todo.getString("content")... 

                    List<Data2> data = new ArrayList<>();
                    for (BacktoryObject todo : todoNotes) {
                        String user = todo.getString("nameUser");
                        String time = todo.getString("timenow");
                        String TimeMo = todo.getString("timework");
                        String Tozih = todo.getString("text");
                        String SM = todo.getString("sub");
                        //String SD = todo.getString("date");
                        String Fa = todo.getString("work");
                        String Date = todo.getString("Data2");

                        long timeInMilliseconds = 0;
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd");
                        try {
                            Date mDate = sdf.parse(Date);
                            timeInMilliseconds = mDate.getTime();
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        if (timeInMilliseconds > a) {
                            data.add(new Data2( "نام : " + user, "موضوع : "  + SM, "شرکت : " + Fa, "زمان کار : "  + TimeMo, "توضیحات : " + Tozih, time));
                        }

                    }
                    MoviesAdapter2 adapter = new MoviesAdapter2(data, getApplication());
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(ModirG.this));
                    recyclerView.scrollToPosition(adapter.getItemCount()-1);

                    RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
                    itemAnimator.setAddDuration(1000);
                    itemAnimator.setRemoveDuration(1000);
                    recyclerView.setItemAnimator(itemAnimator);
                    adapter.notifyDataSetChanged();

                } else {
                    dialog.dismiss();
                }
            }
        });

    }

}


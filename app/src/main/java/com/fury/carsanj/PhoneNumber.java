package com.fury.carsanj;

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
import com.fury.carsanj.date.Data6;
import com.fury.carsanj.date.MoviesAdapter6;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by fury on 11/15/2017.
 */
public class PhoneNumber extends AppCompatActivity {

    boolean ti, nam, ph;
    String nameuser,phone;
    long timeInMilliseconds2;
    private RecyclerView recyclerView;
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phone);

        ti = false;
        nam = false;
        ph = false;


        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.gray));
        }

        ImageView filter = (ImageView)findViewById(R.id.filter);
        ImageView back = (ImageView)findViewById(R.id.back);
        recyclerView = (RecyclerView) findViewById(R.id.rvAnimals);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                PopupMenu menu = new PopupMenu(PhoneNumber.this, view);
                menu.getMenu().add(Menu.NONE, 1, 1, "زمان");
                menu.getMenu().add(Menu.NONE, 2, 2, "کاربر");
                menu.getMenu().add(Menu.NONE, 3, 3, "شماره");
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        int i = item.getItemId();
                        if (i == 1) {
                            //handle share
                            PopupMenu menu = new PopupMenu(PhoneNumber.this, view);
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
                        }else if (i == 2) {
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

                                        PopupMenu menu = new PopupMenu(PhoneNumber.this, view);
                                        for (BacktoryObject todo : todoNotes) {
                                            String SM = todo.getString("UserName");
                                            menu.getMenu().add(SM);
                                        }

                                        menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                                            @Override
                                            public boolean onMenuItemClick(MenuItem item) {

                                                nam = true;
                                                BacktoryQuery todoQuery = new BacktoryQuery("NumberCall");
                                                todoQuery.whereEqualTo("all", "1");
                                                todoQuery.whereEqualTo("nameUser", item.getTitle().toString());
                                                if (ph){
                                                    todoQuery.whereEqualTo("Number", phone);
                                                }
                                                nameuser = item.getTitle().toString();
                                                todoQuery.findInBackground(new BacktoryCallBack<List<BacktoryObject>>() {
                                                    @Override
                                                    public void onResponse(BacktoryResponse<List<BacktoryObject>> backtoryResponse) {
                                                        if (backtoryResponse.isSuccessful()) {
                                                            List<BacktoryObject> todoNotes = backtoryResponse.body();
                                                            // for (BacktoryObject todo : todoNotes)
                                                            //   todo.getString("content")... 

                                                            List<Data6> data = new ArrayList<>();
                                                            for (BacktoryObject todo : todoNotes) {
                                                                String user = todo.getString("nameUser");
                                                                String Number = todo.getString("Number");
                                                                String Date = todo.getString("Date");
                                                                String Time = todo.getString("Time");
                                                                String Date2 = todo.getString("Data2");

                                                                if (ti) {
                                                                    long timeInMilliseconds = 0;
                                                                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd");
                                                                    try {
                                                                        Date mDate = sdf.parse(Date2);
                                                                        timeInMilliseconds = mDate.getTime();
                                                                    } catch (ParseException e) {
                                                                        e.printStackTrace();
                                                                    }

                                                                    if (timeInMilliseconds > timeInMilliseconds2) {

                                                                        data.add(new Data6(user,Date + " " + Time,Number));
                                                                    }
                                                                } else {
                                                                    data.add(new Data6(user,Date + " " + Time,Number));
                                                                }
                                                            }

                                                            MoviesAdapter6 adapter = new MoviesAdapter6(data, getApplication());
                                                            adapter.notifyDataSetChanged();
                                                            recyclerView.setAdapter(adapter);
                                                            recyclerView.setLayoutManager(new LinearLayoutManager(PhoneNumber.this));
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

                                    }else {
                                    }
                                }
                            });
                            return true;
                        } else if (i == 3) {

                            (new DialogNumber(PhoneNumber.this)).showDialog();

                            return true;
                        } else {
                            return false;
                        }


                    }
                });

                menu.show(); //showing popup menu
            }
        });


        BacktoryQuery todoQuery = new BacktoryQuery("NumberCall");
        todoQuery.whereEqualTo("all", "1");
        final ProgressDialog dialog = new ProgressDialog(PhoneNumber.this);
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

                    List<Data6> data = new ArrayList<>();
                    for (BacktoryObject todo : todoNotes){
                        String SM = todo.getString("nameUser");
                        String Number = todo.getString("Number");
                        String Date = todo.getString("Date");
                        String Time = todo.getString("Time");

                        data.add(new Data6(SM,Date + " " + Time,Number));
                    }

                    MoviesAdapter6 adapter = new MoviesAdapter6(data, getApplication());
                    adapter.notifyDataSetChanged();
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(PhoneNumber.this));
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


    public void number(String s){

        ph = true;
        phone = s;
        BacktoryQuery todoQuery = new BacktoryQuery("NumberCall");
        todoQuery.whereEqualTo("all", "1");
        todoQuery.whereEqualTo("Number", s);
        if (nam) {
            todoQuery.whereEqualTo("nameUser", nameuser);
        }
        final ProgressDialog dialog = new ProgressDialog(PhoneNumber.this);
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

                    List<Data6> data = new ArrayList<>();
                    for (BacktoryObject todo : todoNotes) {
                        String nameUser = todo.getString("nameUser");
                        String Number = todo.getString("Number");
                        String Date = todo.getString("Date");
                        String Time = todo.getString("Time");
                        String Date2 = todo.getString("Date2");

                        if (ti) {
                            long timeInMilliseconds = 0;
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd");
                            try {
                                Date mDate = sdf.parse(Date2);
                                timeInMilliseconds = mDate.getTime();
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                            if (timeInMilliseconds > timeInMilliseconds2) {
                                data.add(new Data6(nameUser,Date + " " + Time,Number));
                            }
                        } else {
                            data.add(new Data6(nameUser,Date + " " + Time,Number));
                        }
                    }

                    MoviesAdapter6 adapter = new MoviesAdapter6(data, getApplication());
                    adapter.notifyDataSetChanged();
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(PhoneNumber.this));
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

        ti = true;
        BacktoryQuery todoQuery = new BacktoryQuery("NumberCall");
        todoQuery.whereEqualTo("all", "1");
        if (nam) {
            todoQuery.whereEqualTo("nameUser", nameuser);
        }
        if (ph) {
            todoQuery.whereEqualTo("Number", phone);
        }
        final ProgressDialog dialog = new ProgressDialog(PhoneNumber.this);
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

                    List<Data6> data = new ArrayList<>();
                    for (BacktoryObject todo : todoNotes) {
                        String nameUser = todo.getString("nameUser");
                        String Number = todo.getString("Number");
                        String Date = todo.getString("Date");
                        String Time = todo.getString("Time");
                        String Date2 = todo.getString("Date2");

                        long timeInMilliseconds = 0;
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd");
                        try {
                            Date mDate = sdf.parse(Date2);
                            timeInMilliseconds = mDate.getTime();
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        if (timeInMilliseconds > a) {
                            data.add(new Data6(nameUser,Date + " " + Time,Number));
                        }
                    }


                    MoviesAdapter6 adapter = new MoviesAdapter6(data, getApplication());
                    adapter.notifyDataSetChanged();
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(PhoneNumber.this));
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

}

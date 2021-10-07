package com.fury.carsanj;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.backtory.java.internal.BacktoryCallBack;
import com.backtory.java.internal.BacktoryObject;
import com.backtory.java.internal.BacktoryQuery;
import com.backtory.java.internal.BacktoryResponse;
import com.backtory.java.internal.BacktoryUser;

import java.util.List;

/**
 * Created by fury on 11/13/2017.
 */
public class Setting extends Activity {

    boolean d1, d2;
    EditText etaztime, ettatime, namesub, namework;
    TextView ettimesend, worksend, btnDelete, Delete, subsend, btnDelete2, Delete2, setuser,tx6,tx8,tx7;

    SharedPreferences one_play_preferences;
    SharedPreferences.Editor one_play_editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);

        d1 = false;
        d2 = false;

        one_play_preferences = getApplicationContext().getSharedPreferences("PROJECT_NAME", android.content.Context.MODE_PRIVATE);
        one_play_editor = one_play_preferences.edit();

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.gray));
        }

        ImageView back = (ImageView) findViewById(R.id.back);
        etaztime = (EditText) findViewById(R.id.etaztime);
        ettatime = (EditText) findViewById(R.id.ettatime);
        namesub = (EditText) findViewById(R.id.namesub);
        namework = (EditText) findViewById(R.id.namework);
        ettimesend = (TextView) findViewById(R.id.ettimesend);
        worksend = (TextView) findViewById(R.id.worksend);
        btnDelete = (TextView) findViewById(R.id.btnDelete);
        Delete = (TextView) findViewById(R.id.Delete);
        subsend = (TextView) findViewById(R.id.subsend);
        btnDelete2 = (TextView) findViewById(R.id.btnDelete2);
        Delete2 = (TextView) findViewById(R.id.Delete2);
        setuser = (TextView) findViewById(R.id.setuser);
        tx6 = (TextView) findViewById(R.id.tx6);
        tx8 = (TextView) findViewById(R.id.tx8);
        tx7 = (TextView) findViewById(R.id.tx7);


        Delete2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (d2) {
                    BacktoryQuery todoQuery = new BacktoryQuery("About");

                    todoQuery.whereEqualTo("name", "mo");
                    todoQuery.whereEqualTo("detail", btnDelete2.getText().toString());
                    todoQuery.findInBackground(new BacktoryCallBack<List<BacktoryObject>>() {
                        @Override
                        public void onResponse(BacktoryResponse<List<BacktoryObject>> backtoryResponse) {
                            if (backtoryResponse.isSuccessful()) {
                                List<BacktoryObject> todoNotes = backtoryResponse.body();
                                // for (BacktoryObject todo : todoNotes)
                                //   todo.getString("content")... 

                                for (BacktoryObject todo : todoNotes) {
                                    todo.deleteInBackground(new BacktoryCallBack<Void>() {
                                        @Override
                                        public void onResponse(BacktoryResponse<Void> backtoryResponse) {
                                            if (backtoryResponse.isSuccessful()) {
                                                // object is updated on server
                                                Toast.makeText(Setting.this, "با موفقیت انجام شد", Toast.LENGTH_SHORT).show();
                                            } else {
                                                // see response.message() to know the cause of error
                                                Toast.makeText(Setting.this, "مشکلی در ارسال پیش آمد", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                }

                            }
                        }

                    });
                } else {
                    Toast.makeText(Setting.this, "خطا ابتدا یک مورد را انتخاب کنید!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        btnDelete2.setOnClickListener(new View.OnClickListener() {
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

                            PopupMenu menu = new PopupMenu(Setting.this, view);
                            for (BacktoryObject todo : todoNotes) {
                                String SM = todo.getString("detail");
                                menu.getMenu().add(SM);
                            }
                            menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                                @Override
                                public boolean onMenuItemClick(MenuItem item) {

                                    d2 = true;
                                    btnDelete2.setText(item.getTitle().toString());

                                    return true;
                                }

                            });
                            menu.show(); //showing popup menu
                        }

                    }
                });
            }
        });


        subsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BacktoryObject note = new BacktoryObject("About");
                note.put("name", "mo");
                note.put("detail", namesub.getText().toString());
                note.saveInBackground(new BacktoryCallBack<Void>() {
                    @Override
                    public void onResponse(BacktoryResponse<Void> backtoryResponse) {
                        if (backtoryResponse.isSuccessful()) {
                            // object is updated on server
                            Toast.makeText(Setting.this, "با موفقیت انجام شد", Toast.LENGTH_SHORT).show();
                        } else {
                            // see response.message() to know the cause of error
                            Toast.makeText(Setting.this, "مشکلی در ارسال پیش آمد", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });


        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (d1) {
                    BacktoryQuery todoQuery = new BacktoryQuery("About");

                    todoQuery.whereEqualTo("name", "factory");
                    todoQuery.whereEqualTo("detail", btnDelete.getText().toString());
                    todoQuery.findInBackground(new BacktoryCallBack<List<BacktoryObject>>() {
                        @Override
                        public void onResponse(BacktoryResponse<List<BacktoryObject>> backtoryResponse) {
                            if (backtoryResponse.isSuccessful()) {
                                List<BacktoryObject> todoNotes = backtoryResponse.body();
                                // for (BacktoryObject todo : todoNotes)
                                //   todo.getString("content")... 

                                for (BacktoryObject todo : todoNotes) {
                                    todo.deleteInBackground(new BacktoryCallBack<Void>() {
                                        @Override
                                        public void onResponse(BacktoryResponse<Void> backtoryResponse) {
                                            if (backtoryResponse.isSuccessful()) {
                                                // object is updated on server
                                                Toast.makeText(Setting.this, "با موفقیت انجام شد", Toast.LENGTH_SHORT).show();
                                            } else {
                                                // see response.message() to know the cause of error
                                                Toast.makeText(Setting.this, "مشکلی در ارسال پیش آمد", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                }

                            }
                        }

                    });
                } else {
                    Toast.makeText(Setting.this, "خطا ابتدا یک مورد را انتخاب کنید!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
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

                            PopupMenu menu = new PopupMenu(Setting.this, view);
                            for (BacktoryObject todo : todoNotes) {
                                String SM = todo.getString("detail");
                                menu.getMenu().add(SM);
                            }
                            menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                                @Override
                                public boolean onMenuItemClick(MenuItem item) {

                                    d1 = true;
                                    btnDelete.setText(item.getTitle().toString());

                                    return true;
                                }

                            });
                            menu.show(); //showing popup menu
                        }

                    }
                });
            }
        });


        worksend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BacktoryObject note = new BacktoryObject("About");
                note.put("name", "factory");
                note.put("detail", namework.getText().toString());
                note.saveInBackground(new BacktoryCallBack<Void>() {
                    @Override
                    public void onResponse(BacktoryResponse<Void> backtoryResponse) {
                        if (backtoryResponse.isSuccessful()) {
                            // object is updated on server
                            Toast.makeText(Setting.this, "با موفقیت انجام شد", Toast.LENGTH_SHORT).show();
                        } else {
                            // see response.message() to know the cause of error
                            Toast.makeText(Setting.this, "مشکلی در ارسال پیش آمد", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });


        ettimesend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                BacktoryQuery todoQuery = new BacktoryQuery("User");
                todoQuery.whereEqualTo("user", setuser.getText().toString());
                todoQuery.setLimit(1);
                todoQuery.findInBackground(new BacktoryCallBack<List<BacktoryObject>>() {
                    @Override
                    public void onResponse(BacktoryResponse<List<BacktoryObject>> backtoryResponse) {

                        if (backtoryResponse.isSuccessful()) {
                            List<BacktoryObject> todoNotes = backtoryResponse.body();
                            for (BacktoryObject todo : todoNotes) {
                                    todo.put("StartTime", etaztime.getText().toString());
                                    todo.put("EndTime", ettatime.getText().toString());
                                    todo.saveInBackground(new BacktoryCallBack<Void>() {
                                        @Override
                                        public void onResponse(BacktoryResponse<Void> backtoryResponse) {
                                            if (backtoryResponse.isSuccessful()) {
                                                // object is updated on server
                                                Toast.makeText(Setting.this, "با موفقیت انجام شد", Toast.LENGTH_SHORT).show();
                                            } else {
                                                // see response.message() to know the cause of error
                                                Toast.makeText(Setting.this, "مشکلی در ارسال پیش آمد", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                            }
                        }

                    }
                });

            }
        });


        setuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                final BacktoryQuery todoQuery = new BacktoryQuery("IDUser");
                todoQuery.whereEqualTo("all", "1");
                todoQuery.findInBackground(new BacktoryCallBack<List<BacktoryObject>>() {
                    @Override
                    public void onResponse(BacktoryResponse<List<BacktoryObject>> backtoryResponse) {
                        if (backtoryResponse.isSuccessful()) {
                            List<BacktoryObject> todoNotes = backtoryResponse.body();
                            // for (BacktoryObject todo : todoNotes)
                            //   todo.getString("content")... 

                            PopupMenu menu = new PopupMenu(Setting.this, view);
                            for (BacktoryObject todo : todoNotes) {
                                String SM = todo.getString("UserName");
                                menu.getMenu().add(SM);
                            }

                            menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                                @Override
                                public boolean onMenuItemClick(MenuItem item) {

                                    setuser.setText(item.getTitle().toString());

                                    return true;
                                }

                            });

                            menu.show(); //showing popup menu

                        }
                    }
                });

            }
        });


        tx6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                BacktoryUser.logoutInBackground();
                one_play_editor.putInt("checkUser", 0);
                one_play_editor.apply();
                Intent ne = new Intent(Setting.this,LoginActivity.class);
                startActivity(ne);
                finish();

            }
        });
        tx7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                (new DialogPass2(Setting.this)).showDialog();

            }
        });
        tx8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                (new DialogEmail2(Setting.this)).showDialog();

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


}

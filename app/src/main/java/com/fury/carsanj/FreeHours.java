package com.fury.carsanj;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.backtory.java.internal.BacktoryCallBack;
import com.backtory.java.internal.BacktoryObject;
import com.backtory.java.internal.BacktoryQuery;
import com.backtory.java.internal.BacktoryResponse;
import com.backtory.java.internal.BacktoryUser;
import com.fury.carsanj.date.Data1;
import com.fury.carsanj.date.MoviesAdapter1;
import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fury on 11/5/2017.
 */
public class FreeHours extends AppCompatActivity {

    private RecyclerView recyclerView;
    ProgressDialog pDialog;

    /**
     * Called when the activity is first created.
     */
    // XML node keys
    static final String KEY_VISIBLE = "visible";
    static final String KEY_DATE = "Date";
    static final String KEY_MOZ = "moz";
    static final String KEY_V = "v";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mhours);

        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        recyclerView = (RecyclerView) findViewById(R.id.rvAnimals);
        ImageView back = (ImageView)findViewById(R.id.back);

        fab.show();
        //fab.hide();
        fab.attachToRecyclerView(recyclerView);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent start = new Intent(FreeHours.this,NewFreeHours.class);
                startActivity(start);
                finish();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // Get current user from backtory
        final BacktoryUser currentUser = BacktoryUser.getCurrentUser();

        BacktoryQuery todoQuery = new BacktoryQuery("FreeHours");
        todoQuery.whereEqualTo("user", currentUser.getFirstName() + " " + currentUser.getLastName());
        final ProgressDialog dialog = new ProgressDialog(FreeHours.this);
        dialog.setMessage("لطفا صبر کنید");
        dialog.show();
        todoQuery.findInBackground(new BacktoryCallBack<List<BacktoryObject>>() {
            @Override
            public void onResponse(BacktoryResponse<List<BacktoryObject>> backtoryResponse) {
                if (backtoryResponse.isSuccessful()) {
                    List<BacktoryObject> todoNotes = backtoryResponse.body();
                    // for (BacktoryObject todo : todoNotes)
                    //   todo.getString("content")... 

                    List<Data1> data = new ArrayList<>();
                    for (BacktoryObject todo : todoNotes){
                        String SM = todo.getString("Mozu");
                        String vis = null;
                        String SD = todo.getString("Data");
                        int IV = todo.getInt("Visible");
                        if (IV == 1){
                            vis = "تایید شد";
                        }else if (IV == 2){
                            vis = "رد شد";
                        }else if (IV == 3){
                            vis = "در حال بررسی";
                        }

                        data.add(new Data1(SM,SD,vis,IV));

                    }


                    MoviesAdapter1 adapter = new MoviesAdapter1(data, getApplication());
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(FreeHours.this));
                    recyclerView.scrollToPosition(adapter.getItemCount()-1);

                    RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
                    itemAnimator.setAddDuration(1000);
                    itemAnimator.setRemoveDuration(1000);
                    recyclerView.setItemAnimator(itemAnimator);
                    dialog.dismiss();

                } else {
                    dialog.dismiss();
                }
            }
        });


    }
}

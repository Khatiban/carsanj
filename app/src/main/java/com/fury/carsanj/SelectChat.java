package com.fury.carsanj;

import android.app.Activity;
import android.app.ProgressDialog;
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
import com.fury.carsanj.date.Data7;
import com.fury.carsanj.date.MoviesAdapter7;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fury on 11/5/2017.
 */
public class SelectChat extends AppCompatActivity {

    private RecyclerView recyclerView;
    ProgressDialog pDialog;
    /**
     * Called when the activity is first created.
     */

    static public Activity act;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);

        act = this;

        ImageView back = (ImageView)findViewById(R.id.back);
        recyclerView = (RecyclerView) findViewById(R.id.rvAnimals);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // Get current user from backtory
        final BacktoryUser currentUser = BacktoryUser.getCurrentUser();

        BacktoryQuery todoQuery = new BacktoryQuery("IDUser");
        todoQuery.whereEqualTo("all", "1");
        todoQuery.findInBackground(new BacktoryCallBack<List<BacktoryObject>>() {
            @Override
            public void onResponse(BacktoryResponse<List<BacktoryObject>> backtoryResponse) {
                if (backtoryResponse.isSuccessful()) {
                    List<BacktoryObject> todoNotes = backtoryResponse.body();
                    // for (BacktoryObject todo : todoNotes)
                    //   todo.getString("content")... 

                    List<Data7> data = new ArrayList<>();
                    for (BacktoryObject todo : todoNotes){
                        String SM = todo.getString("UserName");
                        String SD = todo.getString("iduser");

                        data.add(new Data7(SM,SD));
                    }

                    MoviesAdapter7 adapter = new MoviesAdapter7(data, getApplication());
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(SelectChat.this));

                    RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
                    itemAnimator.setAddDuration(1000);
                    itemAnimator.setRemoveDuration(1000);
                    recyclerView.setItemAnimator(itemAnimator);

                } else {
                }
            }
        });

    }

}

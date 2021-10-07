package com.fury.carsanj.date;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.backtory.java.internal.BacktoryCallBack;
import com.backtory.java.internal.BacktoryObject;
import com.backtory.java.internal.BacktoryQuery;
import com.backtory.java.internal.BacktoryResponse;
import com.fury.carsanj.ModirMD;
import com.fury.carsanj.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by fury on 11/23/2017.
 */
public class MoviesAdapter4 extends RecyclerView.Adapter<View_Holder4> implements View.OnClickListener {

    List<Data4> list = Collections.emptyList();
    Context context;
    int s;

    public MoviesAdapter4(List<Data4> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public View_Holder4 onCreateViewHolder(ViewGroup parent, int viewType) {
        //Inflate the layout, initialize the View Holder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);
        View_Holder4 holder = new View_Holder4(v);
        return holder;

    }

    @Override
    public void onBindViewHolder(View_Holder4 holder, final int position) {

        //Use the provided View Holder on the onCreateViewHolder method to populate the current row on the RecyclerView
        holder.Name.setText(list.get(position).title);
        holder.Name2.setText(list.get(position).title2);
        holder.Name3.setText(list.get(position).title3);
        holder.Name4.setText(list.get(position).title4);
        holder.Name5.setText(list.get(position).title5);
        holder.Name6.setText(list.get(position).title6);
        holder.Name7.setText(list.get(position).title7);

        if (list.get(position).title8 == 1){
            holder.Name8.setImageResource(R.drawable.bg_list1);
        }else if (list.get(position).title8 == 2){
            holder.Name8.setImageResource(R.drawable.bg_list3);
        }else if (list.get(position).title8 == 3){
            holder.Name8.setImageResource(R.drawable.bg_list2);
        }

        holder.Name8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                AlertDialog.Builder builder = new AlertDialog.Builder(ModirMD.act);
                builder.setTitle(list.get(position).title4);
                builder.setMessage("تاریخ ارسال درخواست : " + list.get(position).title2 + " " + list.get(position).title5 + "\n" + "موضوع : " + list.get(position).title + "\n" + "تعداد روز مرخصی : " + " " + list.get(position).title6 + "\n" + "از تاریخ : " + list.get(position).title9 + "تا تاریخ : " + list.get(position).title10 + "\n" + "توضیحات : " + list.get(position).title7);
                builder.setPositiveButton("تایید", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        BacktoryQuery todoQuery = new BacktoryQuery("FreeDay");
                        todoQuery.whereEqualTo("user", list.get(position).title4);
                        todoQuery.whereEqualTo("Data", list.get(position).title2);
                        todoQuery.whereEqualTo("time", list.get(position).title5);
                        todoQuery.setLimit(1);
                        todoQuery.findInBackground(new BacktoryCallBack<List<BacktoryObject>>() {
                            @Override
                            public void onResponse(BacktoryResponse<List<BacktoryObject>> backtoryResponse) {
                                if (backtoryResponse.isSuccessful()) {
                                    List<BacktoryObject> todoNotes = backtoryResponse.body();
                                    for (BacktoryObject todo : todoNotes) {
                                        todo.put("Visible", 1);
                                        todo.saveInBackground(new BacktoryCallBack<Void>() {
                                            @Override
                                            public void onResponse(BacktoryResponse<Void> backtoryResponse) {
                                                if (backtoryResponse.isSuccessful()) {
                                                    // object is updated on server
                                                    Toast.makeText(ModirMD.act, "با موفقیت انجام شد", Toast.LENGTH_SHORT).show();
                                                } else {
                                                    // see response.message() to know the cause of error
                                                    Toast.makeText(ModirMD.act, "مشکلی در ارسال پیش آمد", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                                    }
                                }
                            }
                        });
                    }
                });
                builder.setNegativeButton("رد", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        BacktoryQuery todoQuery = new BacktoryQuery("FreeDay");
                        todoQuery.whereEqualTo("user", list.get(position).title4);
                        todoQuery.whereEqualTo("Data", list.get(position).title2);
                        todoQuery.whereEqualTo("time", list.get(position).title5);
                        todoQuery.setLimit(1);
                        todoQuery.findInBackground(new BacktoryCallBack<List<BacktoryObject>>() {
                            @Override
                            public void onResponse(BacktoryResponse<List<BacktoryObject>> backtoryResponse) {
                                if (backtoryResponse.isSuccessful()) {
                                    List<BacktoryObject> todoNotes = backtoryResponse.body();
                                    for (BacktoryObject todo : todoNotes) {
                                        todo.put("Visible", 2);
                                        todo.saveInBackground(new BacktoryCallBack<Void>() {
                                            @Override
                                            public void onResponse(BacktoryResponse<Void> backtoryResponse) {
                                                if (backtoryResponse.isSuccessful()) {
                                                    // object is updated on server
                                                    Toast.makeText(ModirMD.act, "با موفقیت انجام شد", Toast.LENGTH_SHORT).show();
                                                } else {
                                                    // see response.message() to know the cause of error
                                                    Toast.makeText(ModirMD.act, "مشکلی در ارسال پیش آمد", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                                    }
                                }
                            }
                        });
                    }
                });
                builder.show();

            }
        });

        s = position;
        //animate(holder);

    }

    @Override
    public int getItemCount() {
        //returns the number of elements the RecyclerView will display
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    // Insert a new item to the RecyclerView on a predefined position
    public void insert(int position, Data4 data) {
        list.add(position, data);
        notifyItemInserted(position);
    }

    // Remove a RecyclerView item containing a specified Data object
    public void remove(Data4 data) {
        int position = list.indexOf(data);
        list.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public void onClick(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(list.get(s).title4);
        builder.setMessage("تاریخ ارسال درخواست : " + list.get(s).title2 + " " + list.get(s).title5 + "\n" + "موضوع : " + list.get(s).title + "\n" + "درخواست مرخصی در تاریخ " + " " + list.get(s).title6 + "\n" + "توضیحات : " + list.get(s).title7);
        builder.setPositiveButton("تایید", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                BacktoryQuery todoQuery = new BacktoryQuery("FreeDay");
                todoQuery.whereEqualTo("user", list.get(s).title4);
                todoQuery.whereEqualTo("Data", list.get(s).title2);
                todoQuery.whereEqualTo("time", list.get(s).title5);
                todoQuery.setLimit(1);
                todoQuery.findInBackground(new BacktoryCallBack<List<BacktoryObject>>() {
                    @Override
                    public void onResponse(BacktoryResponse<List<BacktoryObject>> backtoryResponse) {
                        if (backtoryResponse.isSuccessful()) {
                            List<BacktoryObject> todoNotes = backtoryResponse.body();
                            for (BacktoryObject todo : todoNotes) {
                                todo.put("Visible", 1);
                                todo.saveInBackground(new BacktoryCallBack<Void>() {
                                    @Override
                                    public void onResponse(BacktoryResponse<Void> backtoryResponse) {
                                        if (backtoryResponse.isSuccessful()) {
                                            // object is updated on server
                                            Toast.makeText(context, "با موفقیت انجام شد", Toast.LENGTH_SHORT).show();
                                        } else {
                                            // see response.message() to know the cause of error
                                            Toast.makeText(context, "مشکلی در ارسال پیش آمد", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                        }
                    }
                });
            }
        });
        builder.setNegativeButton("رد", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                BacktoryQuery todoQuery = new BacktoryQuery("FreeDay");
                todoQuery.whereEqualTo("user", list.get(s).title4);
                todoQuery.whereEqualTo("Data", list.get(s).title2);
                todoQuery.whereEqualTo("time", list.get(s).title5);
                todoQuery.setLimit(1);
                todoQuery.findInBackground(new BacktoryCallBack<List<BacktoryObject>>() {
                    @Override
                    public void onResponse(BacktoryResponse<List<BacktoryObject>> backtoryResponse) {
                        if (backtoryResponse.isSuccessful()) {
                            List<BacktoryObject> todoNotes = backtoryResponse.body();
                            for (BacktoryObject todo : todoNotes) {
                                todo.put("Visible", 2);
                                todo.saveInBackground(new BacktoryCallBack<Void>() {
                                    @Override
                                    public void onResponse(BacktoryResponse<Void> backtoryResponse) {
                                        if (backtoryResponse.isSuccessful()) {
                                            // object is updated on server
                                            Toast.makeText(context, "با موفقیت انجام شد", Toast.LENGTH_SHORT).show();
                                        } else {
                                            // see response.message() to know the cause of error
                                            Toast.makeText(context, "مشکلی در ارسال پیش آمد", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                        }
                    }
                });
            }
        });


    }
}

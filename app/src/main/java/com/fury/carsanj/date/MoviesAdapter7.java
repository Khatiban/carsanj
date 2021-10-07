package com.fury.carsanj.date;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fury.carsanj.ChatNew;
import com.fury.carsanj.R;
import com.fury.carsanj.SelectChat;

import java.util.Collections;
import java.util.List;

/**
 * Created by fury on 11/23/2017.
 */
public class MoviesAdapter7 extends RecyclerView.Adapter<View_Holder7> implements View.OnClickListener {

    List<Data7> list = Collections.emptyList();
    Context context;
    int s;

    public MoviesAdapter7(List<Data7> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public View_Holder7 onCreateViewHolder(ViewGroup parent, int viewType) {
        //Inflate the layout, initialize the View Holder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.userchat, parent, false);

        /*v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startChat = new Intent(context,ChatNew.class);
                startChat.putExtra("ID",list.get(view).title2);
                startChat.putExtra("Username",list.get(view).title);
                context.startActivity(startChat);
            }
        });*/

        View_Holder7 holder = new View_Holder7(v);
        return holder;

    }

    @Override
    public void onBindViewHolder(View_Holder7 holder, final int position) {

        //Use the provided View Holder on the onCreateViewHolder method to populate the current row on the RecyclerView
        holder.Name.setText(list.get(position).title);
        holder.Name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startChat = new Intent(SelectChat.act,ChatNew.class);
                startChat.putExtra("ID",list.get(position).title2);
                startChat.putExtra("Username",list.get(position).title);
                SelectChat.act.startActivity(startChat);
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
    public void insert(int position, Data7 data) {
        list.add(position, data);
        notifyItemInserted(position);
    }

    // Remove a RecyclerView item containing a specified Data object
    public void remove(Data7 data) {
        int position = list.indexOf(data);
        list.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public void onClick(View view) {
        Intent startChat = new Intent(context,ChatNew.class);
        startChat.putExtra("ID",list.get(s).title2);
        startChat.putExtra("Username",list.get(s).title);
        context.startActivity(startChat);
    }

}

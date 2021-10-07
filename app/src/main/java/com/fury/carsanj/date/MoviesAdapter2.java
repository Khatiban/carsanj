package com.fury.carsanj.date;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fury.carsanj.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by fury on 11/23/2017.
 */
public class MoviesAdapter2 extends RecyclerView.Adapter<View_Holder2> {

    List<Data2> list = Collections.emptyList();
    Context context;

    public MoviesAdapter2(List<Data2> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public View_Holder2 onCreateViewHolder(ViewGroup parent, int viewType) {
        //Inflate the layout, initialize the View Holder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardgoz, parent, false);
        View_Holder2 holder = new View_Holder2(v);
        return holder;

    }

    @Override
    public void onBindViewHolder(View_Holder2 holder, int position) {

        //Use the provided View Holder on the onCreateViewHolder method to populate the current row on the RecyclerView
        holder.Name.setText(list.get(position).title);
        holder.moz.setText(list.get(position).description);
        holder.Factory.setText(list.get(position).description2);
        holder.Time.setText(list.get(position).description3);
        holder.Toz.setText(list.get(position).description4);
        holder.Date.setText(list.get(position).description5);

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
    public void insert(int position, Data2 data) {
        list.add(position, data);
        notifyItemInserted(position);
    }

    // Remove a RecyclerView item containing a specified Data object
    public void remove(Data2 data) {
        int position = list.indexOf(data);
        list.remove(position);
        notifyItemRemoved(position);
    }

}

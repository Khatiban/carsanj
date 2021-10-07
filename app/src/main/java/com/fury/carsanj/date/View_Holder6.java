package com.fury.carsanj.date;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.fury.carsanj.R;

/**
 * Created by fury on 11/23/2017.
 */

public class View_Holder6 extends RecyclerView.ViewHolder {

    TextView Name;
    TextView Name2;
    TextView Name3;

    View_Holder6(View itemView) {
        super(itemView);

        Name = (TextView) itemView.findViewById(R.id.username);
        Name2 = (TextView) itemView.findViewById(R.id.idtime);
        Name3 = (TextView) itemView.findViewById(R.id.userid);
    }

}

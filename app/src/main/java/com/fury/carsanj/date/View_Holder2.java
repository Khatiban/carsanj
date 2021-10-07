package com.fury.carsanj.date;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.fury.carsanj.R;

/**
 * Created by fury on 11/23/2017.
 */

public class View_Holder2 extends RecyclerView.ViewHolder {

    TextView Name;
    TextView moz;
    TextView Factory;
    TextView Time;
    TextView Toz;
    TextView Date;

    View_Holder2(View itemView) {
        super(itemView);

        Name = (TextView) itemView.findViewById(R.id.usertext);
        moz = (TextView) itemView.findViewById(R.id.usertext2);
        Factory = (TextView) itemView.findViewById(R.id.usertext3);
        Time = (TextView) itemView.findViewById(R.id.usertext4);
        Toz = (TextView) itemView.findViewById(R.id.usertext6);
        Date = (TextView) itemView.findViewById(R.id.userdate);
    }

}

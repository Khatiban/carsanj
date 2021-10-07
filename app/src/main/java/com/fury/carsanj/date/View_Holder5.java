package com.fury.carsanj.date;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fury.carsanj.R;

/**
 * Created by fury on 11/23/2017.
 */

public class View_Holder5 extends RecyclerView.ViewHolder {

    TextView Name;
    TextView Name2;
    TextView Name3;
    TextView Name4;
    TextView Name5;
    TextView Name6;
    TextView Name7;
    ImageView Name8;

    View_Holder5(View itemView) {
        super(itemView);

        Name = (TextView) itemView.findViewById(R.id.moz);
        Name2 = (TextView) itemView.findViewById(R.id.Date);
        Name3 = (TextView) itemView.findViewById(R.id.visible);
        Name4 = (TextView) itemView.findViewById(R.id.visible1);
        Name5 = (TextView) itemView.findViewById(R.id.visible2);
        Name6 = (TextView) itemView.findViewById(R.id.visible3);
        Name7 = (TextView) itemView.findViewById(R.id.visible4);
        Name8 = (ImageView) itemView.findViewById(R.id.imagecard);
    }

}

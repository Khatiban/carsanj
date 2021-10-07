package com.fury.carsanj.date;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fury.carsanj.R;

/**
 * Created by fury on 11/23/2017.
 */

public class View_Holder extends RecyclerView.ViewHolder {

    TextView title;
    TextView description;
    TextView description2;
    ImageView imageView;

    View_Holder(View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.moz);
        description = (TextView) itemView.findViewById(R.id.Date);
        description2 = (TextView) itemView.findViewById(R.id.visible);
        imageView = (ImageView) itemView.findViewById(R.id.imagecard);
    }

}

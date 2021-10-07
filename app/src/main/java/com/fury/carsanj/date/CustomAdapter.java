package com.fury.carsanj.date;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fury.carsanj.R;

import java.util.ArrayList;

/**
 * Created by fury on 9/14/2017.
 */
public class CustomAdapter extends BaseAdapter {

    Context c;
    ArrayList<Spacecraft> spacecrafts;

    int bak,u;

    public CustomAdapter(Context c,ArrayList<Spacecraft> spacecrafts){
        this.c = c;
        this.spacecrafts = spacecrafts;
    }

    @Override
    public int getCount() {
        return spacecrafts.size();
    }

    @Override
    public Object getItem(int i) {
        return spacecrafts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null){
            view = LayoutInflater.from(c).inflate(R.layout.cardchatfile,viewGroup,false);
        }

        LinearLayout imagecard = (LinearLayout)view.findViewById(R.id.imagecard);
        final TextView usertext = (TextView) view.findViewById(R.id.usertext);
        final TextView dlfile = (TextView) view.findViewById(R.id.dlfile);
        final TextView userdate1 = (TextView) view.findViewById(R.id.userdate1);
        final TextView userdate = (TextView) view.findViewById(R.id.userdate);
        Spacecraft s = (Spacecraft)this.getItem(i);

        bak = s.getBak();
        u = s.getW();

        if (u == 0){
            final int sdk = Build.VERSION.SDK_INT;
            if (sdk < Build.VERSION_CODES.JELLY_BEAN){
                imagecard.setBackgroundDrawable(c.getResources().getDrawable(R.drawable.cbm));
            }else {
                imagecard.setBackground(c.getResources().getDrawable(R.drawable.cbm));
            }
        }else if (u == 1){
            final int sdk = Build.VERSION.SDK_INT;
            if (sdk < Build.VERSION_CODES.JELLY_BEAN){
                imagecard.setBackgroundDrawable(c.getResources().getDrawable(R.drawable.cbf));
            }else {
                imagecard.setBackground(c.getResources().getDrawable(R.drawable.cbf));
            }
        }

        if (bak == 0){
            dlfile.setVisibility(View.GONE);
            userdate1.setVisibility(View.GONE);
            userdate.setVisibility(View.VISIBLE);
            usertext.setVisibility(View.VISIBLE);
            usertext.setText(s.getText());
            userdate.setText(s.getDate());
        }else if (bak == 1){
            usertext.setVisibility(View.GONE);
            dlfile.setVisibility(View.VISIBLE);
            userdate.setVisibility(View.VISIBLE);
            userdate1.setVisibility(View.VISIBLE);
            userdate.setText(s.getDate());
            usertext.setText(s.getText());
        }

        dlfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent download = new Intent(Intent.ACTION_VIEW);
                download.setData(Uri.parse(usertext.getText().toString()));
                c.startActivity(download);
            }
        });

        return view;
    }
}

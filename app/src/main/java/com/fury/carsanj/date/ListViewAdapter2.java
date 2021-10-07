package com.fury.carsanj.date;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fury.carsanj.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by fury on 11/18/2017.
 */
public class ListViewAdapter2 extends BaseAdapter {

    private Activity activity;
    private ArrayList<HashMap<String, String>> data2;
    private static LayoutInflater inflater = null;

    public ListViewAdapter2(Activity a, ArrayList<HashMap<String, String>> d2) {
        activity = a;
        data2 = d2;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return data2.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (convertView == null)
            vi = inflater.inflate(R.layout.cardgoz, null);

        TextView Name = (TextView) vi.findViewById(R.id.usertext);
        TextView moz = (TextView) vi.findViewById(R.id.usertext2);
        TextView Factory = (TextView) vi.findViewById(R.id.usertext3);
        TextView Time = (TextView) vi.findViewById(R.id.usertext4);
        TextView Toz = (TextView) vi.findViewById(R.id.usertext6);
        TextView Date = (TextView) vi.findViewById(R.id.userdate);

        HashMap<String, String> item2 = new HashMap<String, String>();
        item2 = data2.get(position);

        //Setting all values in listview

        Name.setText(item2.get("Name"));
        moz.setText(item2.get("moz"));
        Factory.setText(item2.get("Factory"));
        Time.setText(item2.get("Time"));
        Toz.setText(item2.get("Toz"));
        Date.setText(item2.get("Date"));

        return vi;
    }
}

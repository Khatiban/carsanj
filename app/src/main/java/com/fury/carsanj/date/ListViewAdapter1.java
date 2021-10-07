package com.fury.carsanj.date;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fury.carsanj.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * Created by fury on 11/18/2017.
 */
public class ListViewAdapter1 extends BaseAdapter {

    private Activity activity;
    private ArrayList<HashMap<String, String>> data2;
    private static LayoutInflater inflater = null;

    public ListViewAdapter1(Activity a, ArrayList<HashMap<String, String>> d2) {
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
            vi = inflater.inflate(R.layout.card, null);

        ImageView imagecard = (ImageView) vi.findViewById(R.id.imagecard);
        TextView moz = (TextView) vi.findViewById(R.id.moz);
        TextView Date = (TextView) vi.findViewById(R.id.Date);
        TextView visible = (TextView) vi.findViewById(R.id.visible);

        HashMap<String, String> item2 = new HashMap<String, String>();
        item2 = data2.get(position);

        //Setting all values in listview

        if (Objects.equals(item2.get("v"), "1")){
            imagecard.setImageResource(R.drawable.bg_list1);
        }else if (Objects.equals(item2.get("v"), "2")){
            imagecard.setImageResource(R.drawable.bg_list3);
        }else if (Objects.equals(item2.get("v"), "3")){
            imagecard.setImageResource(R.drawable.bg_list2);
        }

        moz.setText(item2.get("moz"));
        Date.setText(item2.get("Date"));
        visible.setText(item2.get("visible"));
        return vi;
    }
}

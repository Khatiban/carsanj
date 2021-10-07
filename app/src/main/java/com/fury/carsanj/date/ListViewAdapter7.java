package com.fury.carsanj.date;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fury.carsanj.ChatNew;
import com.fury.carsanj.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by fury on 11/18/2017.
 */
public class ListViewAdapter7 extends BaseAdapter {

    private Activity activity;
    private ArrayList<HashMap<String, String>> data2;
    private static LayoutInflater inflater = null;

    public ListViewAdapter7(Activity a, ArrayList<HashMap<String, String>> d2) {
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
            vi = inflater.inflate(R.layout.userchat, null);

        final TextView userid = (TextView) vi.findViewById(R.id.userid);
        final TextView id = (TextView) vi.findViewById(R.id.id);
        ImageView imagecard = (ImageView) vi.findViewById(R.id.imagecard);

        HashMap<String, String> item2 = new HashMap<String, String>();
        item2 = data2.get(position);

        //Setting all values in listview

        userid.setText(item2.get("Name"));
        id.setText(item2.get("Name2"));

        imagecard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startChat = new Intent(activity,ChatNew.class);
                startChat.putExtra("ID",id.getText().toString());
                startChat.putExtra("Username",userid.getText().toString());
                activity.startActivity(startChat);
            }
        });

        return vi;
    }
}

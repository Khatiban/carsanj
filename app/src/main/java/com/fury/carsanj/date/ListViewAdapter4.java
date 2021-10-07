package com.fury.carsanj.date;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.backtory.java.internal.BacktoryCallBack;
import com.backtory.java.internal.BacktoryObject;
import com.backtory.java.internal.BacktoryQuery;
import com.backtory.java.internal.BacktoryResponse;
import com.fury.carsanj.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * Created by fury on 11/18/2017.
 */
public class ListViewAdapter4 extends BaseAdapter {

    private Activity activity;
    private ArrayList<HashMap<String, String>> data2;
    private static LayoutInflater inflater = null;

    public ListViewAdapter4(Activity a, ArrayList<HashMap<String, String>> d2) {
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
        final TextView moz = (TextView) vi.findViewById(R.id.moz);
        final TextView Date = (TextView) vi.findViewById(R.id.Date);
        TextView visible = (TextView) vi.findViewById(R.id.visible);
        final TextView visible1 = (TextView) vi.findViewById(R.id.visible1);
        final TextView visible2 = (TextView) vi.findViewById(R.id.visible2);
        final TextView visible3 = (TextView) vi.findViewById(R.id.visible3);
        final TextView visible4 = (TextView) vi.findViewById(R.id.visible4);

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
        visible1.setText(item2.get("visible1"));
        visible2.setText(item2.get("visible2"));
        visible3.setText(item2.get("visible3"));
        visible4.setText(item2.get("visible4"));

        imagecard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setTitle(visible1.getText().toString());
                builder.setMessage("تاریخ ارسال درخواست : " + Date.getText().toString() + " " + visible2.getText().toString() + "\n" + "موضوع : " + moz.getText().toString() + "\n" + "درخواست مرخصی در تاریخ " + " " + visible3.getText().toString() + "\n" + "توضیحات : " + visible4.getText().toString());
                builder.setPositiveButton("تایید", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        BacktoryQuery todoQuery = new BacktoryQuery("FreeDay");
                        todoQuery.whereEqualTo("user", visible1.getText().toString());
                        todoQuery.whereEqualTo("Data", Date.getText().toString());
                        todoQuery.whereEqualTo("time", visible2.getText().toString());
                        todoQuery.setLimit(1);
                        todoQuery.findInBackground(new BacktoryCallBack<List<BacktoryObject>>() {
                            @Override
                            public void onResponse(BacktoryResponse<List<BacktoryObject>> backtoryResponse) {
                                if (backtoryResponse.isSuccessful()) {
                                    List<BacktoryObject> todoNotes = backtoryResponse.body();
                                    for (BacktoryObject todo : todoNotes) {
                                        todo.put("Visible", 1);
                                        todo.saveInBackground(new BacktoryCallBack<Void>() {
                                            @Override
                                            public void onResponse(BacktoryResponse<Void> backtoryResponse) {
                                                if (backtoryResponse.isSuccessful()) {
                                                    // object is updated on server
                                                    Toast.makeText(activity, "با موفقیت انجام شد", Toast.LENGTH_SHORT).show();
                                                } else {
                                                    // see response.message() to know the cause of error
                                                    Toast.makeText(activity, "مشکلی در ارسال پیش آمد", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                                    }
                                }
                            }
                        });
                    }
                });
                builder.setNegativeButton("رد", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        BacktoryQuery todoQuery = new BacktoryQuery("FreeDay");
                        todoQuery.whereEqualTo("user", visible1.getText().toString());
                        todoQuery.whereEqualTo("Data", Date.getText().toString());
                        todoQuery.whereEqualTo("time", visible2.getText().toString());
                        todoQuery.setLimit(1);
                        todoQuery.findInBackground(new BacktoryCallBack<List<BacktoryObject>>() {
                            @Override
                            public void onResponse(BacktoryResponse<List<BacktoryObject>> backtoryResponse) {
                                if (backtoryResponse.isSuccessful()) {
                                    List<BacktoryObject> todoNotes = backtoryResponse.body();
                                    for (BacktoryObject todo : todoNotes) {
                                        todo.put("Visible", 2);
                                        todo.saveInBackground(new BacktoryCallBack<Void>() {
                                            @Override
                                            public void onResponse(BacktoryResponse<Void> backtoryResponse) {
                                                if (backtoryResponse.isSuccessful()) {
                                                    // object is updated on server
                                                    Toast.makeText(activity, "با موفقیت انجام شد", Toast.LENGTH_SHORT).show();
                                                } else {
                                                    // see response.message() to know the cause of error
                                                    Toast.makeText(activity, "مشکلی در ارسال پیش آمد", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                                    }
                                }
                            }
                        });
                    }
                });

            }
        });

        return vi;
    }
}

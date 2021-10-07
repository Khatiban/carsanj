package com.fury.carsanj;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.backtory.java.internal.BacktoryCallBack;
import com.backtory.java.internal.BacktoryObject;
import com.backtory.java.internal.BacktoryQuery;
import com.backtory.java.internal.BacktoryResponse;

import java.util.List;
import java.util.Objects;
import java.util.Vector;

/**
 * Created by fury on 11/15/2017.
 */
public class LocationUser extends AppCompatActivity {

    private LayoutInflater mInflater;
    private Vector<RowData> data;
    boolean ti, nam, ph,out;

    //GoogleMap map;

    double Longitude;
    double Latitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location);

        ti = false;
        nam = false;
        ph = false;
        out = true;

        mInflater = (LayoutInflater) getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        data = new Vector<RowData>();

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.gray));
        }

        final ListView lv = (ListView)findViewById(R.id.lv) ;

        RowData rd = new RowData("a","11/11/11",false,35.8130761,50.9966516);
        data.add(rd);
        CustomAdapter adapter = new CustomAdapter(LocationUser.this, R.layout.location, R.id.username, data);
        lv.setAdapter(adapter);
        lv.setSelection(adapter.getCount() - 1);
        lv.setTextFilterEnabled(true);

        ImageView back = (ImageView)findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        BacktoryQuery todoQuery = new BacktoryQuery("Location");
        todoQuery.whereEqualTo("all", "1");
        todoQuery.findInBackground(new BacktoryCallBack<List<BacktoryObject>>() {
            @Override
            public void onResponse(BacktoryResponse<List<BacktoryObject>> backtoryResponse) {
                if (backtoryResponse.isSuccessful()) {
                    List<BacktoryObject> todoNotes = backtoryResponse.body();
                    // for (BacktoryObject todo : todoNotes)
                    //   todo.getString("content")... 

                    for (BacktoryObject todo : todoNotes){
                        String SM = todo.getString("nameUser");
                        Longitude = Double.parseDouble(todo.getString("Longitude"));
                        Latitude = Double.parseDouble(todo.getString("Latitude"));
                        String Date = todo.getString("Date");
                        String Hour = todo.getString("Hour");

                        String str = String.valueOf(Latitude);
                        String str2 = String.valueOf(Longitude);
                        String first4char = str.substring(0,6);
                        String first4char2 = str2.substring(0,6);

                        if (!Objects.equals(first4char, "35.813")){
                            out = false;
                        }
                        if (!Objects.equals(first4char2, "50.996")){
                            out = false;
                        }
                        RowData rd = new RowData(SM,Date + " " + Hour,out,Longitude,Latitude);
                        data.add(rd);
                    }

                    CustomAdapter adapter = new CustomAdapter(LocationUser.this, R.layout.location, R.id.username, data);
                    lv.setAdapter(adapter);
                    lv.setTextFilterEnabled(true);

                } else {
                }
            }
        });

        /**((SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map)).getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;

                Latitude = 35.813800;
                Longitude = 50.996800;

                LatLng latLng = new LatLng(Latitude,Longitude);

                //35.813,50.996 location workshop
                String str = String.valueOf(Latitude);
                String str2 = String.valueOf(Longitude);
                String first4char = str.substring(0,6);
                String first4char2 = str2.substring(0,6);

                if (!Objects.equals(first4char, "35.813")){
                    out = true;
                }
                if (!Objects.equals(first4char2, "50.996")){
                    out = true;
                }

                if(out){
                    map.addMarker(new MarkerOptions().position(latLng).title("خارج از دفتر").icon(BitmapDescriptorFactory.fromResource(R.drawable.loc2)));
                }else {
                    map.addMarker(new MarkerOptions().position(latLng).title("دفتر").icon(BitmapDescriptorFactory.fromResource(R.drawable.loc1)));
                }


                map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,17));

                map.getUiSettings().setZoomControlsEnabled(true);
                map.getUiSettings().setMyLocationButtonEnabled(true);

            }
        });*/

    }

    public void onListItemClick(ListView parent, View v, int position, long id) {
        CustomAdapter adapter = (CustomAdapter) parent.getAdapter();
        RowData row = adapter.getItem(position);

        // Create a Uri from an intent string. Use the result to create an Intent.
        Uri gmmIntentUri = Uri.parse("google.streetview:cbll=" + row.Longitude + "," + row.Latitude);

// Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
// Make the Intent explicit by setting the Google Maps package
        mapIntent.setPackage("com.google.android.apps.maps");
        try {
            startActivity(mapIntent);
        } catch (Exception e) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/@"+ row.Longitude +"," + row.Latitude +",19.11z?hl=en"));
            startActivity(browserIntent);
        }
    }

    /**
     * Data type used for custom adapter. Single item of the adapter.
     */
    private class RowData {
        protected String mName;
        protected double Longitude;
        protected double Latitude;
        protected String mDate;
        protected boolean m;

        RowData(String item,String i3,boolean i4,double Lo, double La) {
            mName = item;
            mDate = i3;
            Longitude = Lo;
            Latitude = La;
            m = i4;

        }

        @Override
        public String toString() {
            return mName + " " + mDate ;
        }
    }

    private class CustomAdapter extends ArrayAdapter<RowData> {

        public CustomAdapter(Context context, int resource,
                             int textViewResourceId, List<RowData> objects) {
            super(context, resource, textViewResourceId, objects);

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;

            //widgets displayed by each item in your list
            ImageView imagecard = null;
            TextView moz = null;
            TextView date = null;

            //data from your adapter
            final RowData rowData = getItem(position);


            //we want to reuse already constructed row views...
            if (null == convertView) {
                convertView = mInflater.inflate(R.layout.locationlist, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            }
            //
            holder = (ViewHolder) convertView.getTag();
            moz = holder.getMoz();
            moz.setText(rowData.mName);
            date = holder.getDate();
            date.setText(rowData.mDate);

            if (rowData.m){
                imagecard = holder.getimagecard();
                imagecard.setImageResource(R.drawable.di);
            }else {
                imagecard = holder.getimagecard();
                imagecard.setImageResource(R.drawable.bg_list3);
            }

            imagecard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/@"+ rowData.Longitude +"," + rowData.Latitude +",19.11z?hl=en"));
                        startActivity(browserIntent);

                }
            });

            return convertView;
        }
    }

    /**
     * Wrapper for row data.
     */
    private class ViewHolder {
        private View mRow;
        private TextView moz = null;
        private TextView date = null;
        private ImageView m = null;

        public ViewHolder(View row) {
            mRow = row;
        }

        public TextView getMoz() {
            if (null == moz) {
                moz = (TextView) mRow.findViewById(R.id.username);
            }
            return moz;
        }

        public TextView getDate() {
            if (null == date) {
                date = (TextView) mRow.findViewById(R.id.idtime);
            }
            return date;
        }

        public ImageView getimagecard() {
            if (null == m) {
                m = (ImageView) mRow.findViewById(R.id.imagecard);
            }
            return m;
        }
    }

}

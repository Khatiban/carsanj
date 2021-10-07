package com.fury.carsanj;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by fury on 11/9/2017.
 */
public class Admin extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin);

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this,R.color.gray));
        }

        TextView mhm = (TextView)findViewById(R.id.mhm);
        TextView mdm = (TextView)findViewById(R.id.mdm);
        TextView goz = (TextView)findViewById(R.id.goz);
        TextView mes = (TextView)findViewById(R.id.mes);
        TextView set = (TextView)findViewById(R.id.set);
        TextView goz2 = (TextView)findViewById(R.id.goz2);

        mhm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent p = new Intent(Admin.this,ModirMH.class);
                startActivity(p);
            }
        });

        mdm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent p = new Intent(Admin.this,ModirMD.class);
                startActivity(p);
            }
        });

        goz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent p = new Intent(Admin.this,ModirGOZARSH.class);
                startActivity(p);
            }
        });

        goz2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent p = new Intent(Admin.this,FilterUser.class);
                startActivity(p);
            }
        });

        mes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent p = new Intent(Admin.this,SelectChat.class);
                startActivity(p);
            }
        });

        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent p = new Intent(Admin.this,Setting.class);
                startActivity(p);
            }
        });

    }

}

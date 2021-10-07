package com.fury.carsanj;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by fury on 11/14/2017.
 */
public class SuperAdmin extends Activity {


    DrawerLayout drawerlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.root_2);


        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this,R.color.gray));
        }

        TextView mhm = (TextView)findViewById(R.id.mhm);
        TextView mdm = (TextView)findViewById(R.id.mdm);
        TextView mes = (TextView)findViewById(R.id.mes);
        TextView lin_menu_4 = (TextView)findViewById(R.id.lin_menu_4);
        TextView lin_menu_3 = (TextView)findViewById(R.id.lin_menu_3);
        TextView lin_menu_2 = (TextView)findViewById(R.id.lin_menu_2);
        TextView lin_menu_1 = (TextView)findViewById(R.id.lin_menu_1);
        ImageView filter = (ImageView)findViewById(R.id.filter);
        ImageView setting = (ImageView)findViewById(R.id.setting);
        //sliding view
        drawerlayout = (DrawerLayout) findViewById(R.id.root_page_1);


        mhm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent p = new Intent(SuperAdmin.this,LocationUser.class);
                startActivity(p);
            }
        });

        mdm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent p = new Intent(SuperAdmin.this,PhoneNumber.class);
                startActivity(p);

            }
        });

        mes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent p = new Intent(SuperAdmin.this,SelectChat.class);
                startActivity(p);
            }
        });

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    if (drawerlayout.isDrawerOpen(Gravity.RIGHT)) {
                        drawerlayout.closeDrawer(Gravity.RIGHT);
                    } else {
                        drawerlayout.openDrawer(Gravity.RIGHT);
                    }

                } catch (Exception e) {
                    //FirebaseCrash.report(new Exception("10"));
                }
            }
        });

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent p = new Intent(SuperAdmin.this,Setting.class);
                startActivity(p);
            }
        });

        lin_menu_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent p = new Intent(SuperAdmin.this,ModirGOZARSH.class);
                startActivity(p);
            }
        });

        lin_menu_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent p = new Intent(SuperAdmin.this,ModirMD.class);
                startActivity(p);
            }
        });

        lin_menu_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent p = new Intent(SuperAdmin.this,ModirMH.class);
                startActivity(p);
            }
        });

        lin_menu_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent p = new Intent(SuperAdmin.this,FilterUser.class);
                startActivity(p);
            }
        });

    }
}

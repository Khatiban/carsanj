package com.fury.carsanj;

import android.app.Dialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by fury on 9/21/2017.
 */
public class DialogNumber {

    private Dialog mDialog;
    private PhoneNumber mDialogUniversalInfoActivity;
    TextView cancel, send;
    int timeall2;
    EditText sub;

    public DialogNumber(PhoneNumber var1) {
        this.mDialogUniversalInfoActivity = var1;
    }

    private void initDialogButtons() {
        this.cancel.setOnClickListener(new OnClickListener() {
            public void onClick(View var1) {
                DialogNumber.this.mDialog.dismiss();
            }
        });
        this.send.setOnClickListener(new OnClickListener() {
            public void onClick(View var1) {
                if (app_net.getInstance(mDialogUniversalInfoActivity).isOnline()) {

                    mDialogUniversalInfoActivity.number(sub.getText().toString());
                    DialogNumber.this.mDialog.dismiss();

                } else {
                    Toast.makeText(mDialogUniversalInfoActivity, "خطا در دسترسی اینترنت!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    public void dismissDialog() {
        this.mDialog.dismiss();
    }

    public void showDialog() {
        if (this.mDialog == null) {
            this.mDialog = new Dialog(this.mDialogUniversalInfoActivity, R.style.CustomDialogTheme);
        }
        this.mDialog.setContentView(R.layout.dialog_number);
        this.mDialog.setCancelable(false);
        this.mDialog.show();

        send = (TextView) mDialog.findViewById(R.id.send);
        cancel = (TextView) mDialog.findViewById(R.id.cancel);
        sub = (EditText) mDialog.findViewById(R.id.text_mo);

        initDialogButtons();
    }

}

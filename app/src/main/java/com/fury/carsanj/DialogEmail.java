package com.fury.carsanj;

import android.app.Dialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.backtory.java.internal.BacktoryCallBack;
import com.backtory.java.internal.BacktoryResponse;
import com.backtory.java.internal.BacktoryUser;

/**
 * Created by fury on 9/21/2017.
 */
public class DialogEmail {

    private Dialog mDialog;
    private PageUser mDialogUniversalInfoActivity;
    TextView cancel, send;
    int timeall2;
    EditText sub;

    public DialogEmail(PageUser var1) {
        this.mDialogUniversalInfoActivity = var1;
    }

    private void initDialogButtons() {
        this.cancel.setOnClickListener(new OnClickListener() {
            public void onClick(View var1) {
                DialogEmail.this.mDialog.dismiss();
            }
        });
        this.send.setOnClickListener(new OnClickListener() {
            public void onClick(View var1) {
                if (app_net.getInstance(mDialogUniversalInfoActivity).isOnline()) {

                    BacktoryUser backtoryUser = BacktoryUser.getCurrentUser();

                    backtoryUser.setEmail(sub.getText().toString());

                    backtoryUser.updateInBackground(new BacktoryCallBack<BacktoryUser>() {
                        @Override
                        public void onResponse(BacktoryResponse<BacktoryUser> backtoryResponse) {

                            if (backtoryResponse.isSuccessful()){
                                Toast.makeText(mDialogUniversalInfoActivity, "با موفقیت تغییر کرد", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(mDialogUniversalInfoActivity, "متاسفانه ثبت نشد", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

                    DialogEmail.this.mDialog.dismiss();

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
        this.mDialog.setContentView(R.layout.dialog_email);
        this.mDialog.setCancelable(false);
        this.mDialog.show();

        send = (TextView) mDialog.findViewById(R.id.send);
        cancel = (TextView) mDialog.findViewById(R.id.cancel);
        sub = (EditText) mDialog.findViewById(R.id.text_mo);

        initDialogButtons();
    }

}

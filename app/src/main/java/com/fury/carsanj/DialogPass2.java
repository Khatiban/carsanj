package com.fury.carsanj;

import android.app.Dialog;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.backtory.java.HttpStatusCode;
import com.backtory.java.internal.BacktoryCallBack;
import com.backtory.java.internal.BacktoryResponse;
import com.backtory.java.internal.BacktoryUser;

/**
 * Created by fury on 9/21/2017.
 */
public class DialogPass2 {

    private Dialog mDialog;
    private Setting mDialogUniversalInfoActivity;
    TextView cancel, send;
    int timeall2;
    EditText sub,sub2;

    public DialogPass2(Setting var1) {
        this.mDialogUniversalInfoActivity = var1;
    }

    private void initDialogButtons() {
        this.cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View var1) {
                DialogPass2.this.mDialog.dismiss();
            }
        });
        this.send.setOnClickListener(new View.OnClickListener() {
            public void onClick(View var1) {
                if (app_net.getInstance(mDialogUniversalInfoActivity).isOnline()) {

                    BacktoryUser.getCurrentUser().changePasswordInBackground(sub.getText().toString(), sub2.getText().toString(), new BacktoryCallBack<Void>() {
                        @Override
                        public void onResponse(BacktoryResponse<Void> backtoryResponse) {
                            if (backtoryResponse.isSuccessful()){
                                Toast.makeText(mDialogUniversalInfoActivity, "رمز عبور تغییر کرد", Toast.LENGTH_LONG).show();
                            }else if (backtoryResponse.code() == HttpStatusCode.Forbidden.code()){
                                Toast.makeText(mDialogUniversalInfoActivity, "رمز قدیمی اشتباه است!", Toast.LENGTH_LONG).show();
                            }else {
                                Toast.makeText(mDialogUniversalInfoActivity, "خطا در دسترسی اینترنت!", Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                    DialogPass2.this.mDialog.dismiss();

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
        this.mDialog.setContentView(R.layout.dialog_pass);
        this.mDialog.setCancelable(true);
        this.mDialog.show();

        send = (TextView) mDialog.findViewById(R.id.send);
        cancel = (TextView) mDialog.findViewById(R.id.cancel);
        sub = (EditText) mDialog.findViewById(R.id.text_mo);
        sub2 = (EditText) mDialog.findViewById(R.id.text_mo2);

        initDialogButtons();
    }

}

package com.fury.carsanj;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.backtory.java.internal.BacktoryCallBack;
import com.backtory.java.internal.BacktoryObject;
import com.backtory.java.internal.BacktoryQuery;
import com.backtory.java.internal.BacktoryResponse;

import java.util.List;

/**
 * Created by fury on 9/21/2017.
 */
public class DialogList {

    private Dialog mDialog;
    private PageUser mDialogUniversalInfoActivity;
    TextView dialog_universal_info_title, send;
    RadioButton radioButton,radioButton2,radioButton3,radioButton4,radioButton5,radioButton6,radioButton7,radioButton8,radioButton9
            ,radioButton10,radioButton11,radioButton12,radioButton13,radioButton14,radioButton15,radioButton16,radioButton17
            ,radioButton18,radioButton19,radioButton20;
    boolean tru = false;
    int s;

    public DialogList(PageUser var1,int s) {
        this.mDialogUniversalInfoActivity = var1;
        this.s = s;
    }

    private void initDialogButtons() {
        this.send.setOnClickListener(new OnClickListener() {
            public void onClick(View var1) {
                if (app_net.getInstance(mDialogUniversalInfoActivity).isOnline()) {
                    if (tru){
                        if (s == 1){
                            mDialogUniversalInfoActivity.one_play_editor.putBoolean("go_mo", true);
                            mDialogUniversalInfoActivity.one_play_editor.apply();
                        }else {
                            mDialogUniversalInfoActivity.one_play_editor.putBoolean("go_factory", true);
                            mDialogUniversalInfoActivity.one_play_editor.apply();
                        }
                        DialogList.this.mDialog.dismiss();
                    }else {
                        Toast.makeText(mDialogUniversalInfoActivity, "خطا! لطفا یک مورد را انتخاب کنید", Toast.LENGTH_LONG).show();
                    }
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
        this.mDialog.setContentView(R.layout.dialog_list);
        this.mDialog.setCancelable(false);
        this.mDialog.show();

        dialog_universal_info_title = (TextView) mDialog.findViewById(R.id.dialog_universal_info_title);
        send = (TextView) mDialog.findViewById(R.id.send);
        RadioGroup rGroup = (RadioGroup)mDialog.findViewById(R.id.radioGroup1);
        final LinearLayout numberpicker_container3 = (LinearLayout)mDialog.findViewById(R.id.numberpicker_container3);

        if ( s == 1 ){
            dialog_universal_info_title.setText("موضوع");
        }else {
            dialog_universal_info_title.setText("نام شرکت");
        }

        rGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                // This will get the radiobutton that has changed in its check state
                RadioButton checkedRadioButton = (RadioButton)group.findViewById(checkedId);
                // This puts the value (true/false) into the variable
                boolean isChecked = checkedRadioButton.isChecked();
                // If the radiobutton that has changed in check state is now checked...
                if (isChecked)
                {
                    // Changes the textview's text to "Checked: example radiobutton text"
                    tru = true;
                    if (s == 1){
                        mDialogUniversalInfoActivity.one_play_editor.putString("StringMO", String.valueOf(checkedRadioButton.getText()));
                        mDialogUniversalInfoActivity.one_play_editor.apply();
                    }else {
                        mDialogUniversalInfoActivity.one_play_editor.putString("Stringfactory", String.valueOf(checkedRadioButton.getText()));
                        mDialogUniversalInfoActivity.one_play_editor.apply();
                    }

                }
            }
        });

        radioButton = (RadioButton) mDialog.findViewById(R.id.radioButton);
        radioButton2 = (RadioButton) mDialog.findViewById(R.id.radioButton2 );
        radioButton3 = (RadioButton) mDialog.findViewById(R.id.radioButton3 );
        radioButton4 = (RadioButton) mDialog.findViewById(R.id.radioButton4 );
        radioButton5 = (RadioButton) mDialog.findViewById(R.id.radioButton5 );
        radioButton6 = (RadioButton) mDialog.findViewById(R.id.radioButton6 );
        radioButton7 = (RadioButton) mDialog.findViewById(R.id.radioButton7 );
        radioButton8 = (RadioButton) mDialog.findViewById(R.id.radioButton8 );
        radioButton9 = (RadioButton) mDialog.findViewById(R.id.radioButton9 );
        radioButton10 = (RadioButton) mDialog.findViewById(R.id.radioButton10);
        radioButton11 = (RadioButton) mDialog.findViewById(R.id.radioButton11);
        radioButton12 = (RadioButton) mDialog.findViewById(R.id.radioButton12);
        radioButton13 = (RadioButton) mDialog.findViewById(R.id.radioButton13);
        radioButton14 = (RadioButton) mDialog.findViewById(R.id.radioButton14);
        radioButton15 = (RadioButton) mDialog.findViewById(R.id.radioButton15);
        radioButton16 = (RadioButton) mDialog.findViewById(R.id.radioButton16);
        radioButton17 = (RadioButton) mDialog.findViewById(R.id.radioButton17);
        radioButton18 = (RadioButton) mDialog.findViewById(R.id.radioButton18);
        radioButton19 = (RadioButton) mDialog.findViewById(R.id.radioButton19);
        radioButton20 = (RadioButton) mDialog.findViewById(R.id.radioButton20);

        BacktoryQuery todoQuery = new BacktoryQuery("About");
        if (s == 1){
            todoQuery.whereEqualTo("name", "mo");
        }else {
            todoQuery.whereEqualTo("name", "factory");
        }
        final ProgressDialog dialog = new ProgressDialog(mDialogUniversalInfoActivity);
        dialog.setMessage("لطفا صبر کنید");
        dialog.show();
        todoQuery.findInBackground(new BacktoryCallBack<List<BacktoryObject>>() {
            @Override
            public void onResponse(BacktoryResponse<List<BacktoryObject>> backtoryResponse) {

                if (backtoryResponse.isSuccessful()) {
                    List<BacktoryObject> todoNotes = backtoryResponse.body();
                    // for (BacktoryObject todo : todoNotes)
                    //   todo.getString("content")... 

                    StringBuilder csvList = new StringBuilder();
                    for (BacktoryObject todo : todoNotes){
                        String SM = todo.getString("detail");
                        csvList.append(SM);
                        csvList.append(",");
                    }
                    String csvList2 = csvList.toString();
                    String[] items = csvList2.split(",");

                    if (items[0] != null){
                        radioButton.setText(items[0]);
                    }else {
                        radioButton.setVisibility(View.GONE);
                    }
                    if (items[1] != null){
                        radioButton2.setText(items[1]);
                    }else {
                        radioButton2.setVisibility(View.GONE);
                    }
                    if (items[2] != null){
                        radioButton3.setText(items[2]);
                    }else {
                        radioButton3.setVisibility(View.GONE);
                    }
                    if (items[3] != null){
                        radioButton4.setText(items[3]);
                    }else {
                        radioButton4.setVisibility(View.GONE);
                    }
                    if (items[4] != null){
                        radioButton5.setText(items[4]);
                    }else {
                        radioButton5.setVisibility(View.GONE);
                    }
                    if (items[5] != null){
                        radioButton6.setText(items[5]);
                    }else {
                        radioButton6.setVisibility(View.GONE);
                    }
                    if (items[6] != null){
                        radioButton7.setText(items[6]);
                    }else {
                        radioButton7.setVisibility(View.GONE);
                    }
                    if (items[7] != null){
                        radioButton8.setText(items[7]);
                    }else {
                        radioButton8.setVisibility(View.GONE);
                    }
                    if (items[8] != null){
                        radioButton9.setText(items[8]);
                    }else {
                        radioButton9.setVisibility(View.GONE);
                    }
                    if (items[9] != null){
                        radioButton10.setText(items[9]);
                    }else {
                        radioButton10.setVisibility(View.GONE);
                    }
                    if (items[10] != null){
                        radioButton11.setText(items[10]);
                    }else {
                        radioButton11.setVisibility(View.GONE);
                        numberpicker_container3.setVisibility(View.GONE);
                    }
                    if (items[11] != null){
                        radioButton12.setText(items[11]);
                    }else {
                        radioButton12.setVisibility(View.GONE);
                    }
                    if (items[12] != null){
                        radioButton13.setText(items[12]);
                    }else {
                        radioButton13.setVisibility(View.GONE);
                    }
                    if (items[13] != null){
                        radioButton14.setText(items[13]);
                    }else {
                        radioButton14.setVisibility(View.GONE);
                    }
                    if (items[14] != null){
                        radioButton15.setText(items[14]);
                    }else {
                        radioButton15.setVisibility(View.GONE);
                    }
                    if (items[15] != null){
                        radioButton16.setText(items[15]);
                    }else {
                        radioButton16.setVisibility(View.GONE);
                    }
                    if (items[16] != null){
                        radioButton17.setText(items[16]);
                    }else {
                        radioButton17.setVisibility(View.GONE);
                    }
                    if (items[17] != null){
                        radioButton18.setText(items[17]);
                    }else {
                        radioButton18.setVisibility(View.GONE);
                    }
                    if (items[18] != null){
                        radioButton19.setText(items[18]);
                    }else {
                        radioButton19.setVisibility(View.GONE);
                    }
                    if (items[19] != null){
                        radioButton20.setText(items[19]);
                    }else {
                        radioButton20.setVisibility(View.GONE);
                    }

                    dialog.dismiss();
                } else {
                    Toast.makeText(mDialogUniversalInfoActivity, backtoryResponse.message(), Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                }
            }
        });

        initDialogButtons();
    }

}

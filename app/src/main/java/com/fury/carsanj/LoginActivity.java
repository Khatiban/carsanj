package com.fury.carsanj;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.backtory.java.HttpStatusCode;
import com.backtory.java.internal.BacktoryCallBack;
import com.backtory.java.internal.BacktoryResponse;
import com.backtory.java.internal.BacktoryUser;
import com.backtory.java.internal.LoginResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    private EditText mPasswordView;
    private EditText mUserView;
    private TextView fore;
    private View mProgressView;

    SharedPreferences one_play_preferences;
    SharedPreferences.Editor one_play_editor;

    public static final int MULTIPLE_PERMISSIONS = 10; // code you want.

    String[] permissions= new String[]{
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.WRITE_CONTACTS,
            android.Manifest.permission.READ_PHONE_STATE,
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.ACCESS_FINE_LOCATION};
    int checkUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        one_play_preferences = getApplicationContext().getSharedPreferences("PROJECT_NAME", android.content.Context.MODE_PRIVATE);
        one_play_editor = one_play_preferences.edit();
        checkUser = one_play_preferences.getInt("checkUser", 0);

        mPasswordView = (EditText) findViewById(R.id.ET_pass);
        mUserView = (EditText) findViewById(R.id.ET_user);
        fore = (TextView) findViewById(R.id.fore);

        checkPermissions();
        //  permissions  granted.

        if (checkUser == 3){
            Intent p = new Intent(LoginActivity.this,PageUser.class);
            startActivity(p);
            finish();
        }else if (checkUser == 1){
            Intent p = new Intent(LoginActivity.this,SuperAdmin.class);
            startActivity(p);
            finish();
        }else if (checkUser == 2){
            Intent p = new Intent(LoginActivity.this,Admin.class);
            startActivity(p);
            finish();
        }

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mProgressView = findViewById(R.id.login_progress);

        fore.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                (new DialogForget(LoginActivity.this)).showDialog();
            }
        });

    }


    private  boolean checkPermissions() {
        int result;
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String p:permissions) {
            result = ContextCompat.checkSelfPermission(LoginActivity.this,p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),MULTIPLE_PERMISSIONS );
            return false;
        }
        return true;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MULTIPLE_PERMISSIONS:{
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    // permissions granted.
                } else {
                    // permissions list of don't granted permission
                }
                return;
            }
        }
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {

        // Store values at the time of the login attempt.
        String email = mUserView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mUserView.setError(getString(R.string.error_field_required));
            focusView = mUserView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            // Pass user info to login
            BacktoryUser.loginInBackground(email, password,
                    new BacktoryCallBack<LoginResponse>() {

                        // Login operation done (fail or success), handling it:
                        @Override
                        public void onResponse(BacktoryResponse<LoginResponse> response) {
                            // Checking result of operation
                            if (response.isSuccessful()) {
                                // Login successfull
                                BacktoryUser currentUser = BacktoryUser.getCurrentUser();
                                String username = currentUser.getUsername();
                                if (Objects.equals(username, "admin")){
                                    one_play_editor.putInt("checkUser", 2);
                                    one_play_editor.apply();
                                }else if (Objects.equals(username, "superadmin")){
                                    one_play_editor.putInt("checkUser", 1);
                                    one_play_editor.apply();
                                }else {
                                    one_play_editor.putInt("checkUser", 3);
                                    one_play_editor.apply();
                                }
                                showProgress(false);
                                Intent p = new Intent(LoginActivity.this,PageUser.class);
                                startActivity(p);
                            } else if (response.code() == HttpStatusCode.Unauthorized.code()) {
                                // Username 'mohx' with password '123456' is wrong
                                Toast.makeText(LoginActivity.this, "نام کاربری یا رمز عبور اشتباه می باشد", Toast.LENGTH_SHORT).show();
                                showProgress(false);
                            } else {
                                // Operation generally failed, maybe internet connection issue
                                Toast.makeText(LoginActivity.this, "دسترسی به اینترنت موجود نیست!", Toast.LENGTH_SHORT).show();
                                showProgress(false);
                            }
                        }

                    });
        }
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
        }
    }

}


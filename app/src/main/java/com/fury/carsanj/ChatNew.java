package com.fury.carsanj;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.backtory.java.internal.BacktoryCallBack;
import com.backtory.java.internal.BacktoryFile;
import com.backtory.java.internal.BacktoryResponse;
import com.backtory.java.internal.BacktoryUser;
import com.backtory.java.realtime.android.BacktoryRealtimeAndroidApi;
import com.backtory.java.realtime.core.listeners.ChatListener;
import com.backtory.java.realtime.core.listeners.RealtimeSdkListener;
import com.backtory.java.realtime.core.models.ConnectResponse;
import com.backtory.java.realtime.core.models.connectivity.chat.ChatInvitationMessage;
import com.backtory.java.realtime.core.models.connectivity.chat.SimpleChatMessage;
import com.backtory.java.realtime.core.models.connectivity.chat.UserAddedMessage;
import com.backtory.java.realtime.core.models.connectivity.chat.UserJoinedMessage;
import com.backtory.java.realtime.core.models.connectivity.chat.UserLeftMessage;
import com.backtory.java.realtime.core.models.connectivity.chat.UserRemovedMessage;
import com.backtory.java.realtime.core.models.exception.ExceptionMessage;
import com.fury.carsanj.date.CustomAdapter;
import com.fury.carsanj.date.RealmHelper;
import com.fury.carsanj.date.Spacecraft;
import com.melnykov.fab.FloatingActionButton;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmConfiguration;

import static android.os.Environment.isExternalStorageRemovable;

/**
 * Created by fury on 11/8/2017.
 */
public class ChatNew extends Activity implements ChatListener, RealtimeSdkListener {

    ImageView back;
    TextView name;
    EditText textms;
    ListView lv;
    String code, nameuser;
    FloatingActionButton send, file;
    private static final int READ_REQUEST_CODE = 46; // or any int you like :)
    private static final String DISK_CACHE_SUBDIR = "tempFiles";
    Realm realm;
    RealmChangeListener realmChangeListener;
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basschat);

        Intent i = this.getIntent();

        Bundle extras = i.getExtras();
        if (extras != null) {
            code = extras.getString("ID");
            nameuser = extras.getString("Username");
        }

        RealmConfiguration config = new RealmConfiguration.Builder().name(code + ".realm").build();
        realm = Realm.getInstance(config);
        final RealmHelper realmHelper = new RealmHelper(realm);
        realmHelper.retrievefromDB();

        back = (ImageView) findViewById(R.id.back);
        name = (TextView) findViewById(R.id.name);
        send = (FloatingActionButton) findViewById(R.id.send);
        file = (FloatingActionButton) findViewById(R.id.file);
        textms = (EditText) findViewById(R.id.textms);
        lv = (ListView) findViewById(R.id.lv);

        name.setText(nameuser);

        // 1. Get Realtime api
        BacktoryRealtimeAndroidApi backtoryApi = BacktoryRealtimeAndroidApi.getInstance();

        // Set required listener
        backtoryApi.setRealtimeSdkListener(this);

        // Set chat listener
        backtoryApi.setChatListener(this);

// 2. Try to connect
        backtoryApi.connectAsync(new BacktoryCallBack<ConnectResponse>() {
            @Override
            public void onResponse(BacktoryResponse<ConnectResponse> response) {
                // 3. Check if connected Successful
                if (response.isSuccessful()) {
                }
            }
        });


        adapter = new CustomAdapter(this, realmHelper.refresh());
        lv.setAdapter(adapter);

        realmChangeListener = new RealmChangeListener() {
            @Override
            public void onChange(Object o) {
                adapter = new CustomAdapter(ChatNew.this, realmHelper.refresh());
                lv.setAdapter(adapter);
            }
        };
        realm.addChangeListener(realmChangeListener);


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (textms.getText() != null) {

// 1. Get Realtime api
                    BacktoryRealtimeAndroidApi backtoryApi = BacktoryRealtimeAndroidApi.getInstance();

// 2. Send message to him/her
                    backtoryApi.sendChatToUserAsync(code, textms.getText().toString(),
                            new BacktoryCallBack<Void>() {
                                @Override
                                public void onResponse(BacktoryResponse<Void> response) {
                                    // 3. Check if message is sent to server successfully
                                    if (response.isSuccessful()) {
                                        Log.d("TAG", "Message is sent to Ali.");
                                    } else {
                                        Log.d("TAG", "Failed to send message to ali with error: "
                                                + response.message());
                                    }
                                }
                            });
                } else {
                    Toast.makeText(ChatNew.this, "هیچ متنی برای ارسال وجود ندارد!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ACTION_OPEN_DOCUMENT is the intent to choose a file via the system's file
                // browser.
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);

                // Filter to only show results that can be "opened", such as a
                // file (as opposed to a list of contacts or timezones)
                intent.addCategory(Intent.CATEGORY_OPENABLE);

                // Filter to show only images, using the image MIME data type.
                // If one wanted to search for ogg vorbis files, the type would be "audio/ogg".
                // To search for all documents available via installed storage providers,
                // it would be "*/*".
                intent.setType("*/*");

                startActivityForResult(intent, READ_REQUEST_CODE);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            // only one file selected http://stackoverflow.com/questions/21071098/select-multiple-images-from-android-how-to-get-uris#comment62968158_26336150
            uploadSingle(data.getData().toString());

        }

    }

    public void uploadSingle(String address) {

        new BacktoryFile().beginUpload(createFileFromContentUri(address), "ChatFile", true)
                .commitInBackground(new BacktoryCallBack<String>() {
                    @Override
                    public void onResponse(BacktoryResponse<String> response) {
                        if (response.isSuccessful()) {
                            String filePathOnServer = response.body();
                            // 1. Get Realtime api
                            BacktoryRealtimeAndroidApi backtoryApi = BacktoryRealtimeAndroidApi.getInstance();

// 2. Send message to him/her
                            backtoryApi.sendChatToUserAsync(code, "217218fury" + filePathOnServer,
                                    new BacktoryCallBack<Void>() {
                                        @Override
                                        public void onResponse(BacktoryResponse<Void> response) {
                                            // 3. Check if message is sent to server successfully
                                            if (response.isSuccessful()) {
                                                Toast.makeText(ChatNew.this, "ok send", Toast.LENGTH_SHORT).show();
                                                Log.d("TAG", "Message is sent to Ali.");
                                            } else {
                                                Log.d("TAG", "Failed to send message to ali with error: "
                                                        + response.message());
                                            }
                                        }
                                    });
                        } else {
                            Toast.makeText(ChatNew.this, "متاسفانه فایل ارسال نشد!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }


    File createFileFromContentUri(String fileAbsoluteAddress) {
        Uri contentUri = Uri.parse(fileAbsoluteAddress);
        try {
            File cacheDir = getDiskCacheDir(ChatNew.this, DISK_CACHE_SUBDIR);
            if (!cacheDir.exists()) {
                if (!cacheDir.mkdirs()) {
                    throw new IllegalStateException("failed to create new directory in cached dir '" + cacheDir.getPath() + "'");
                }
            }
            String fileName = getFileName(getContentResolver(), contentUri);
            File file = new File(cacheDir/*context.getCacheDir()*/, fileName);
            InputStream ins = getDocumentInputStream(getContentResolver(), contentUri);
            createFileFromInputStream(ins, file);
            return file;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    static String getFileName(ContentResolver contentResolver, Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = contentResolver.query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }

    private static InputStream getDocumentInputStream(ContentResolver contentResolver, Uri contentUri) throws FileNotFoundException {
        InputStream ins;
        ins = contentResolver.openInputStream(contentUri);
        return ins;
    }

    static void createFileFromInputStream(InputStream ins, File file) throws IOException {
        FileOutputStream output = new FileOutputStream(file);
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];
        int len = 0;
        while ((len = ins.read(buffer)) != -1) {
            output.write(buffer, 0, len);
        }
    }

    private static File getDiskCacheDir(Context context, String uniqueName) {
        // Check if media is mounted or storage is built-in, if so, try and use external cache dir
        // otherwise use internal cache dir
        final String cachePath =
                Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) ||
                        !isExternalStorageRemovable() ? context.getExternalCacheDir().getPath() :
                        context.getCacheDir().getPath();

        return new File(cachePath + File.separator + uniqueName);
    }

    @Override
    public void onPushMessage(SimpleChatMessage simpleChatMessage) {

    }

    @Override
    public void onChatMessage(SimpleChatMessage simpleChatMessage) {
        String from = simpleChatMessage.getSenderId();
        String message = simpleChatMessage.getMessage();
        long date = simpleChatMessage.getDate();

        // Get current user from backtory
        final BacktoryUser currentUser = BacktoryUser.getCurrentUser();
        if (Objects.equals(from, code)) {
            String endst;
            String text2 = null;
            try{
                String text = message;
                String add_1 = text + ")";
                int endindex = add_1.indexOf(")");
                text2 = add_1.substring(10, endindex);
                endst = message.substring(1, 10);
            }catch (Exception e){
                endst = "11";
                Toast.makeText(ChatNew.this, "error 2001", Toast.LENGTH_LONG).show();
            }

            if (Objects.equals(endst, "217218fur")) {
                ///text2 ; link Download

                Spacecraft spacecraft = new Spacecraft();
                spacecraft.setDate(String.valueOf(date));
                spacecraft.setText(text2);
                spacecraft.setBak(1);


                if (Objects.equals(from, currentUser.getUserId())) {
                    spacecraft.setW(0);
                } else {
                    spacecraft.setW(1);
                }

                RealmHelper realmHelper = new RealmHelper(realm);
                realmHelper.save(spacecraft);

            } else {
                //message text user
                //date time send text
                Spacecraft spacecraft = new Spacecraft();
                String dateString = new SimpleDateFormat("MM/dd/yyyy").format(new Date(date));
                spacecraft.setDate(String.valueOf(dateString));
                spacecraft.setText(message);
                spacecraft.setBak(0);

                if (Objects.equals(from, currentUser.getUserId())) {
                    spacecraft.setW(0);
                } else {
                    spacecraft.setW(1);
                }

                RealmHelper realmHelper = new RealmHelper(realm);
                realmHelper.save(spacecraft);

            }

        } else if (Objects.equals(from, currentUser.getUserId())) {
            String endst;
            String text2 = null;
            try{
                String text = message;
                String add_1 = text + ")";
                int endindex = add_1.indexOf(")");
                text2 = add_1.substring(10, endindex);
                endst = message.substring(1, 10);
            }catch (Exception e){
                endst = "11";
                Toast.makeText(ChatNew.this, "error 2001", Toast.LENGTH_LONG).show();
            }

            if (Objects.equals(endst, "217218fur")) {
                ///text2 ; link Download

                Spacecraft spacecraft = new Spacecraft();
                spacecraft.setDate(String.valueOf(date));
                spacecraft.setText(text2);
                spacecraft.setBak(1);

                if (Objects.equals(from, currentUser.getUserId())) {
                    spacecraft.setW(0);
                } else {
                    spacecraft.setW(1);
                }

                RealmHelper realmHelper = new RealmHelper(realm);
                realmHelper.save(spacecraft);

            } else {
                //message text user
                //date time send text
                Spacecraft spacecraft = new Spacecraft();
                String dateString = new SimpleDateFormat("MM/dd/yyyy").format(new Date(date));
                spacecraft.setDate(String.valueOf(dateString));
                spacecraft.setText(message);
                spacecraft.setBak(0);

                if (Objects.equals(from, currentUser.getUserId())) {
                    spacecraft.setW(0);
                } else {
                    spacecraft.setW(1);
                }

                RealmHelper realmHelper = new RealmHelper(realm);
                realmHelper.save(spacecraft);

            }

        } else {

            RealmConfiguration config = new RealmConfiguration.Builder().name(from + ".realm").build();
            Realm realm2 = Realm.getInstance(config);

            String text2 = null;
            String endst = null;
            try {
                String text = message;
                String add_1 = text + ")";
                int endindex = add_1.indexOf(")");
                text2 = add_1.substring(10, endindex);
                endst = message.substring(1, 10);
            }catch (Exception e){

            }

            if (Objects.equals(endst, "217218fury")) {
                ///text2 ; link Download

                Spacecraft spacecraft = new Spacecraft();
                String dateString = new SimpleDateFormat("MM/dd/yyyy").format(new Date(date));
                spacecraft.setDate(String.valueOf(dateString));
                spacecraft.setText(text2);
                spacecraft.setBak(1);

                if (Objects.equals(from, currentUser.getUserId())) {
                    spacecraft.setW(0);
                } else {
                    spacecraft.setW(1);
                }

                RealmHelper realmHelper = new RealmHelper(realm2);
                realmHelper.save(spacecraft);

            } else {
                //message text user
                //date time send text
                Spacecraft spacecraft = new Spacecraft();
                String dateString = new SimpleDateFormat("MM/dd/yyyy").format(new Date(date));
                spacecraft.setDate(String.valueOf(dateString));
                spacecraft.setText(message);
                spacecraft.setBak(0);

                if (Objects.equals(from, currentUser.getUserId())) {
                    spacecraft.setW(0);
                } else {
                    spacecraft.setW(1);
                }

                RealmHelper realmHelper = new RealmHelper(realm2);
                realmHelper.save(spacecraft);

            }

            Toast.makeText(ChatNew.this, "کاربر دیگری برایتان پیامی ارسال کردن", Toast.LENGTH_LONG).show();

        }

    }

    @Override
    public void onGroupPushMessage(SimpleChatMessage simpleChatMessage) {

    }

    @Override
    public void onGroupChatMessage(SimpleChatMessage simpleChatMessage) {

    }

    @Override
    public void onChatInvitationMessage(ChatInvitationMessage chatInvitationMessage) {

    }

    @Override
    public void onChatGroupUserAddedMessage(UserAddedMessage userAddedMessage) {

    }

    @Override
    public void onChatGroupUserJoinedMessage(UserJoinedMessage userJoinedMessage) {

    }

    @Override
    public void onChatGroupUserLeftMessage(UserLeftMessage userLeftMessage) {

    }

    @Override
    public void onChatGroupUserRemovedMessage(UserRemovedMessage userRemovedMessage) {

    }

    @Override
    public void onDisconnect() {

    }

    @Override
    public void onException(ExceptionMessage exceptionMessage) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.removeChangeListener(realmChangeListener);
        realm.close();

        // 1. Get Realtime api
        BacktoryRealtimeAndroidApi backtoryApi = BacktoryRealtimeAndroidApi.getInstance();

        // 2. Try to disconnect
        backtoryApi.disconnectAsync(new BacktoryCallBack<Void>() {
            @Override
            public void onResponse(BacktoryResponse<Void> response) {
                // 3. Check if disconnect was successful
                if (response.isSuccessful()) {
                    Log.d("TAG", "Disconnect successfully.");
                } else {
                    Log.d("TAG", "Disconnect failed with code: " + response.code()
                            + " and message " + response.message());
                }
            }
        });

    }
}

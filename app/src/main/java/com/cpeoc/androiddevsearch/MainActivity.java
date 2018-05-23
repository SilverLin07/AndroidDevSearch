package com.cpeoc.androiddevsearch;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cpeoc.androiddevsearch.fruit.Apple;
import com.cpeoc.androiddevsearch.fruit.Fruit;
import com.cpeoc.androiddevsearch.net.NetUtil;
import com.cpeoc.androiddevsearch.net.OkHttp3NetUtil;
import com.cpeoc.androiddevsearch.net.RetrofitNetUtil;
import com.cpeoc.androiddevsearch.util.FileUtil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;

import static okhttp3.internal.Util.closeQuietly;

public class MainActivity extends AppCompatActivity {
    public static final int MY_PERMISSIONS_REQUEST_STORAGE = 1;
    private NetUtil mNetUtil;
    private TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvContent = (TextView) findViewById(R.id.tv_content);
//        execute();
//        PECSTest();
        requestPermission();
    }

    public void requestPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_CONTACTS)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_STORAGE);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            writeFile();
            readFile();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_STORAGE:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    writeFile();
                } else {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT);
                }
                break;
        }
    }

    public void writeFile() {
        BufferedSink bufferedSink = null;
        FileUtil fileUtil = new FileUtil(this);
        try {
            String savePath = fileUtil.mkdir("file");
//            File file = new File(Environment.getExternalStorageDirectory() + "/test.txt");
//            File file = new File(getApplicationContext().getFileStreamPath("test.txt").getPath());
            File file = new File(savePath + "/test.txt");
//            bufferedSink = Okio.buffer(Okio.sink(file));
//            bufferedSink.writeString("Hello world", Charset.defaultCharset());
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write("Good to see you, My friend");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FileUtil.SdCardNotExistedException e) {
            e.printStackTrace();
        } finally {
            closeQuietly(bufferedSink);
        }
    }

    public void readFile() {
        BufferedSource bufferedSource = null;
        FileUtil fileUtil = new FileUtil(this);
        try {
            String savePath = fileUtil.mkdir("file");
            File file = new File(savePath + "/test.txt");
//            bufferedSource = Okio.buffer(Okio.source(file));
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
//            Log.e("str", bufferedSource.readString(Charset.defaultCharset()));
            Log.e("str", bufferedReader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FileUtil.SdCardNotExistedException e) {
            e.printStackTrace();
        } finally {
            closeQuietly(bufferedSource);
        }
    }

    public void execute() {
//        exeWithRetrofit();
        exeWithOkHttp3(tvContent);
        mNetUtil.init();
        mNetUtil.run();
    }

    public void exeWithOkHttp3(View view) {
        mNetUtil = new OkHttp3NetUtil(view);
    }

    public void exeWithRetrofit() {
        mNetUtil = new RetrofitNetUtil();
    }

    public void PECSTest() {
//        try {
//            Class clazz = Class.forName("com.cpeoc.androiddevsearch.fruit.Apple");
////        Fruit f = (Food) clazz.newInstance();
//            Plate<?> plate = new Plate<>(clazz.newInstance());
//            Log.e("? Name", ((Food) plate.getItem()).getName());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        Plate<? extends Fruit> plate1 = new Plate<>(new Fruit());
//        Log.e("extends Name", plate1.getItem().getName());
//
//        Plate<? super Fruit> plate2 = new Plate<>(new Food());
//        plate2.setItem(new Apple());
//        Log.e("super Name", ((Food)plate2.getItem()).getName());

        List<Fruit> fruits = new ArrayList<>();
        fruits.add(new Apple());
        fruits.add(new Fruit());
        List<? extends Fruit> list1 = new ArrayList<>(fruits);
        for (int i = 0; i < list1.size(); i++) {
            Log.e("name", list1.get(i).getName());
        }
    }
}

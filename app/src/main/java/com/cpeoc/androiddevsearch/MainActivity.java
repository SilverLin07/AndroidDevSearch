package com.cpeoc.androiddevsearch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.cpeoc.androiddevsearch.fruit.Apple;
import com.cpeoc.androiddevsearch.fruit.Fruit;
import com.cpeoc.androiddevsearch.net.NetUtil;
import com.cpeoc.androiddevsearch.net.OkHttp3NetUtil;
import com.cpeoc.androiddevsearch.net.RetrofitNetUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private NetUtil mNetUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

//        execute();
//        PECSTest();
        startRNActivity();
    }

    public void execute() {
        exeWithRetrofit();
        mNetUtil.init();
        mNetUtil.run();
    }

    public void exeWithOkHttp3() {
        mNetUtil = new OkHttp3NetUtil();
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

    public void startRNActivity() {
        startActivity(new Intent(this, RNShowActivity.class));
    }
}

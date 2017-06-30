package com.xcommander.dispatchtouchevent;

import android.util.Log;

/**
 * Created by xulinchao on 2017/6/29.
 */

public class Cat {
    public static final String TAG = "xulinchao";
    public static int count;
    public int age;
    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    static {
        count = 0;

    }

    public Cat() {
        age = count++;
        Log.e(TAG, "cat: create");

    }

    public void run(String xx) {
        Log.e(Cat.TAG, "run xx = "+xx);
    }

    private void runff(String k,int x) {
        Log.e(Cat.TAG, " runff k = "+k+" x = "+x);
    }

}

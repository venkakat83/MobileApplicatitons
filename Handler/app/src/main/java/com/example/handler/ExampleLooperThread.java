package com.example.handler;

import android.os.Handler;
import android.os.Looper;

public class ExampleLooperThread extends Thread {

    Looper looper;
    Handler handler;

    @Override
    public void run() {

        looper.prepare();

        looper = Looper.myLooper();

        handler = new ExampleHandler();


        looper.loop();
    }
}

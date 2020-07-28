package com.example.handler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    ExampleLooperThread looperThread = new ExampleLooperThread();

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void start(View view) {

        looperThread.start();


        // To run the handler anonymously use the following.

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//                for (int i = 0; i < 20 ; i++) {
//
//                    Log.d(TAG, "run: " + i);
//                    SystemClock.sleep(1000);
//
//                }
//
//            }
//        }, 3000);

//        new Handler().postDelayed(()->{
//
//            System.out.println("hi");
//
//        }, 3000);


    }

    public void stop(View view) {

        looperThread.looper.quitSafely();

    }


    public void taskA(View view) {

        Message message = Message.obtain();

        message.what = 1;

        looperThread.handler.sendMessage(message);



//        handler = looperThread.handler;
//
//        handler.post(new Runnable() {
//            @Override
//            public void run() {
//
//                for (int i = 0; i <5 ; i++) {
//
//                    Log.d(TAG, "run: " + i);
//                    SystemClock.sleep(1000);
//
//                }
//
//            }
//        });
    }
}

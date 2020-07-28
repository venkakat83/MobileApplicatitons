package com.example.handler;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

public class ExampleHandler extends Handler {

    private static final String TAG = "ExampleHandler";


    @Override
    public void handleMessage(@NonNull Message msg) {

        Log.d(TAG, "handleMessage: " + msg.what);

    }
}

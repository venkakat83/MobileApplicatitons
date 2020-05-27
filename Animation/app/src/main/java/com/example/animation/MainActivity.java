package com.example.animation;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView textView1;
    TextView textView2;
    TextView textView3;
    int screenWidth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView1 = findViewById(R.id.text);
        textView2 = findViewById(R.id.text2);
        textView3 = findViewById(R.id.text3);
        DisplayMetrics displaymetrics = new DisplayMetrics();

        this.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        screenWidth = displaymetrics.widthPixels;
    }

    @Override
    protected void onStart() {
        super.onStart();

        final ValueAnimator fadeAnim = ObjectAnimator.ofFloat(textView1, "x", screenWidth*0.9f);
        fadeAnim.setRepeatCount(1);
        fadeAnim.setDuration(2500);
        fadeAnim.start();

        ValueAnimator fadeAnim1 = ObjectAnimator.ofFloat(textView2, "x", screenWidth*0.6f);
        fadeAnim1.setRepeatCount(1);
        fadeAnim1.setDuration(2500);
        fadeAnim1.start();

        ValueAnimator fadeAnim2 = ObjectAnimator.ofFloat(textView3, "x", screenWidth*0.3f);
        fadeAnim2.setRepeatCount(1);
        fadeAnim2.setDuration(2500);
        fadeAnim2.start();

        fadeAnim.addListener(new AnimatorListenerAdapter(){

            @Override
            public void onAnimationEnd(Animator animation, boolean isReverse) {

                ObjectAnimator.ofFloat(textView1, "alpha", 1f, 0f).setDuration(1000).start();
                //textView1.setVisibility(TextView.INVISIBLE);

            }
        });

        fadeAnim1.addListener(new AnimatorListenerAdapter(){

            @Override
            public void onAnimationEnd(Animator animation, boolean isReverse) {

                ObjectAnimator.ofFloat(textView2, "alpha", 1f, 0f).setDuration(1000).start();
                //textView2.setVisibility(TextView.INVISIBLE);

            }
        });

        fadeAnim2.addListener(new AnimatorListenerAdapter(){

            @Override
            public void onAnimationEnd(Animator animation, boolean isReverse) {

                ObjectAnimator.ofFloat(textView3, "alpha", 1f, 0f).setDuration(1000).start();
                //textView3.setVisibility(TextView.INVISIBLE);
            }
        });


    }
}

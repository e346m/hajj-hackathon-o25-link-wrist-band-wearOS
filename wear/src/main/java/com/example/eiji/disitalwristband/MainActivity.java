package com.example.eiji.disitalwristband;

import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.drawable.TransitionDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.common.GoogleApiAvailability;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static final Random RANDOM = new Random();
    public View root;
    public Button button;
    public ValueAnimator anim;

    public Handler mHandler = new Handler();
    public Runnable restartAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GoogleApiAvailability.getInstance().makeGooglePlayServicesAvailable(this);
        MyFirebaseInstanceIDService instance = new MyFirebaseInstanceIDService();
        instance.onTokenRefresh();
        setContentView(R.layout.activity_main);
        root = findViewById(R.id.root);
        button = (Button) findViewById(R.id.button1);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                boolean flag = RANDOM.nextBoolean();
                if(flag) {
                    anim.end();
                    int red = RANDOM.nextInt(255);
                    int blue = RANDOM.nextInt(255);
                    int green = RANDOM.nextInt(255);

                    int color = Color.rgb(red, green, blue);
                    root.setBackgroundColor(color);
                    button.setText("Yes, they already departured. Their wrist band color is the same as the background color.");
                    button.setEnabled(false);
                    restartAnimation = new Runnable() {
                        public void run() {
                            startAnimation(anim);
                            mHandler.removeCallbacks(restartAnimation);
                            mHandler.postDelayed(restartAnimation, 5000);
                        }
                    };

                    mHandler.postDelayed(restartAnimation, 5000);
                }else{
                    button.setText("They are still around here. Please look around to find them");
                }
            }
        });
        anim = ValueAnimator.ofFloat(0, 1);
        startAnimation(anim);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int red = RANDOM.nextInt(255);
                int blue = RANDOM.nextInt(255);
                int green = RANDOM.nextInt(255);

                int color = Color.rgb(red, green, blue);
                root.setBackgroundColor(color);
            }
        });
    }

      public void startAnimation(ValueAnimator anim){
          anim.setDuration(2000);

          final float[] hsv;
          int runColor;
          int hue = 0;
          hsv = new float[3]; // Transition color
          hsv[1] = 1;
          hsv[2] = 1;
          anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

              @Override
              public void onAnimationUpdate(ValueAnimator animation) {

                  hsv[0] = 360 * animation.getAnimatedFraction();
                  root.setBackgroundColor(Color.HSVToColor(hsv));
              }
          });

          anim.setRepeatCount(Animation.INFINITE);

          button.setText("check if your group already started to move");
          button.setEnabled(true);

          anim.start();
      }
}

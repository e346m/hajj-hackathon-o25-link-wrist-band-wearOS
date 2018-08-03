package com.example.eiji.disitalwristband;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GoogleApiAvailability.getInstance().makeGooglePlayServicesAvailable(this);
        Log.d("DEBUG", "token?");
        MyFirebaseInstanceIDService instance = new MyFirebaseInstanceIDService();
        instance.onTokenRefresh();
        setContentView(R.layout.activity_main);
        root = findViewById(R.id.root);
        button = (Button) findViewById(R.id.button1);
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
}

package com.kinvn.rxjava2demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.kinvn.rxjava2demo.R;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //SampleExampleActivity
        findViewById(R.id.button_simple).setOnClickListener(view -> startActivity(SimpleExampleActivity.class));
        //MapExampleActivity
        findViewById(R.id.button_map).setOnClickListener(view -> startActivity(MapExampleActivity.class));
        //ZipExampleActivity
        findViewById(R.id.button_zip).setOnClickListener(view -> startActivity(ZipExampleActivity.class));
        //DisposableObserverExampleActivity
        findViewById(R.id.button_dispose).setOnClickListener(view -> startActivity(DisposableObserverExampleActivity.class));
        //TakeExampleActivity
        findViewById(R.id.button_take).setOnClickListener(view -> startActivity(TakeExampleActivity.class));
        //TimerExampleActivity
        findViewById(R.id.button_timer).setOnClickListener(view -> startActivity(TimerExampleActivity.class));
    }

    private void startActivity(Class<? extends Activity> clz) {
        Intent intent = new Intent(this, clz);
        startActivity(intent);
    }
}
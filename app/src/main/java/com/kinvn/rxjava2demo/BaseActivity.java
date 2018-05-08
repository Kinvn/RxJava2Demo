package com.kinvn.rxjava2demo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by Kinvn on 2018/4/27.
 * <p>
 * Email:kinvn123@gmail.com
 */

@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onStart() {
        super.onStart();
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getClass().getSimpleName());
        }
    }

    protected void showToast(String content) {
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }
}

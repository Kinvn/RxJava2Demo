package com.kinvn.rxjava2demo;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by Kinvn on 2018/4/27.
 * <p>
 * Email:kinvn123@gmail.com
 */

public class BaseActivity extends AppCompatActivity {
    protected void showToast(String content) {
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }
}

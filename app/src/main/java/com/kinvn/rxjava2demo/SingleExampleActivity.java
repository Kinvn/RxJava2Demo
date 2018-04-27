package com.kinvn.rxjava2demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.kinvn.rxjava2demo.R;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

/**
 * Created by Kinvn on 2018/4/27.
 * <p>
 * Email:kinvn123@gmail.com
 */

public class SingleExampleActivity extends BaseActivity {
    private static final String TAG = "SingleExampleActivity";
    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_example);
        textView = findViewById(R.id.textView);
        findViewById(R.id.button).setOnClickListener(view -> Single.just("Single").subscribe(getObserver()));
    }

    private SingleObserver<String> getObserver() {
        return new SingleObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe: " + d.isDisposed());
            }

            @Override
            public void onSuccess(String s) {
                textView.append(s + "\n");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage() );
            }
        };
    }
}

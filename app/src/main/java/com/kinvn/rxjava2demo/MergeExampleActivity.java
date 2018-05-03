package com.kinvn.rxjava2demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import com.example.kinvn.rxjava2demo.R;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Kinvn on 2018/5/3.
 */

public class MergeExampleActivity extends BaseActivity {
    private static final String TAG = "MergeExampleActivity";
    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merge_example);
        textView = findViewById(R.id.textView);
        String[] arrayA = {"A", "B", "C", "D"};
        String[] arrayB = {"1", "2", "3"};
        findViewById(R.id.button).setOnClickListener(view ->
                Observable.merge(Observable.fromArray(arrayA), Observable.fromArray(arrayB))
                        .subscribe(getObserver()));
    }

    private Observer<String> getObserver() {
        return new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
            }

            @Override
            public void onNext(String s) {
                textView.append(s + "\n");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                showToast("onComplete");
            }
        };
    }
}


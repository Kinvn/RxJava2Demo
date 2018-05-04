package com.kinvn.rxjava2demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import com.example.kinvn.rxjava2demo.R;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.BehaviorSubject;

/**
 * Created by Kinvn on 2018/5/4.
 * <p>
 * Email:kinvn123@gmail.com
 */

public class BehaviorSubjectActivity extends BaseActivity {
    private static final String TAG = "BehaviorSubjectActivity";
    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_behavior_subject_example);
        textView = findViewById(R.id.textView);
        findViewById(R.id.button).setOnClickListener(view -> {
            BehaviorSubject<String> source = BehaviorSubject.create();
            source.subscribe(getFirstObserver());
            source.onNext("A");
            source.onNext("B");
            source.onNext("C");
            source.subscribe(getSecondObserver());
            source.onNext("D");
            source.onComplete();
        });
    }

    private Observer<String> getFirstObserver() {
        return new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "FirstSubscribe");
            }

            @Override
            public void onNext(String s) {
                textView.append("First: " + s + "\n");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                showToast("FirstComplete");
            }
        };
    }

    private Observer<String> getSecondObserver() {
        return new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "SecondSubscribe");
            }

            @Override
            public void onNext(String s) {
                textView.append("Second: " + s + "\n");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                showToast("SecondComplete");
            }
        };
    }
}

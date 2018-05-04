package com.kinvn.rxjava2demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import com.example.kinvn.rxjava2demo.R;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.ReplaySubject;

/**
 * Created by Kinvn on 2018/5/4.
 * <p>
 * Email:kinvn123@gmail.com
 */

public class ReplaySubjectActivity extends BaseActivity {
    private static final String TAG = "ReplaySubjectActivity";
    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replay_subject_example);
        textView = findViewById(R.id.textView);
        ReplaySubject<String> source = ReplaySubject.create();
        findViewById(R.id.button).setOnClickListener(view -> {
            source.subscribe(getFirstObserver());
            source.onNext("A");
            source.onNext("B");
            source.onNext("C");
            source.onNext("D");
            source.subscribe(getSecondObserver());
        });
    }

    private Observer<String> getFirstObserver() {
        return new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "FirstObserverSubscribe");
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "FirstObserver: " + s);
                textView.append("FirstObserver: " + s + "\n");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                showToast("FirstObserverComplete");
            }
        };
    }

    private Observer<String> getSecondObserver() {
        return new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "SecondObserverSubscribe");
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "SecondObserver: " + s);
                textView.append("SecondObserver: " + s + "\n");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                showToast("SecondObserverComplete");
            }
        };
    }
}

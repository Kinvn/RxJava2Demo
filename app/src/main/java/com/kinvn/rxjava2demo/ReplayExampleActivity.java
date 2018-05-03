package com.kinvn.rxjava2demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import com.example.kinvn.rxjava2demo.R;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by Kinvn on 2018/5/3.
 */

public class ReplayExampleActivity extends BaseActivity {
    private static final String TAG = "ReplayExampleActivity";
    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replay_example);
        textView = findViewById(R.id.textView);
        findViewById(R.id.button).setOnClickListener(view -> {
            PublishSubject<Integer> source = PublishSubject.create();
            ConnectableObservable<Integer> observable = source.replay(3);
            observable.connect();
            observable.subscribe(getFirstObserver());
            source.onNext(1);
            source.onNext(2);
            source.onNext(3);
            source.onNext(4);
            source.onComplete();
            observable.subscribe(getSecondeObserver());
        });
    }

    private Observer<Integer> getFirstObserver() {
        return new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                textView.append("First Observer Subscribe\n");
            }

            @Override
            public void onNext(Integer integer) {
                textView.append("FirstObserver: ");
                textView.append(integer + "\n");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                showToast("First Observer Complete");
            }
        };
    }

    private Observer<Integer> getSecondeObserver() {
        return new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                textView.append("Second Observer Subscribe\n");
            }

            @Override
            public void onNext(Integer integer) {
                textView.append("SecondObserver: ");
                textView.append(integer + "\n");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage() );
            }

            @Override
            public void onComplete() {
                showToast("Second Observer Complete");
            }
        };
    }
}

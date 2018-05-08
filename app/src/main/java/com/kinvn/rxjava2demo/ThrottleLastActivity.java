package com.kinvn.rxjava2demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import com.example.kinvn.rxjava2demo.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Kinvn on 2018/5/8.
 * <p>
 * Email:kinvn123@gmail.com
 */

public class ThrottleLastActivity extends BaseActivity {
    private static final String TAG = "ThrottleLastActivity";
    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_throttle_last_example);
        textView = findViewById(R.id.textView);
        findViewById(R.id.button).setOnClickListener(view ->
                getObservable()
                        .throttleLast(500, TimeUnit.MILLISECONDS)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(getObserver()));
    }

    private Observable<String> getObservable() {
        return Observable.create(emitter -> {
            emitter.onNext("A");
            Thread.sleep(510);
            emitter.onNext("B1");
            emitter.onNext("B2");
            Thread.sleep(600);
            emitter.onNext("C");
            Thread.sleep(300);
            emitter.onNext("D");
            Thread.sleep(1000);
            emitter.onNext("E");
            emitter.onComplete();
        });
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

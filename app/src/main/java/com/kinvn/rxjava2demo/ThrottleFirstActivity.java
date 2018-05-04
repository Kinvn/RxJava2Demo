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
 * Created by Kinvn on 2018/5/4.
 * <p>
 * Email:kinvn123@gmail.com
 */

public class ThrottleFirstActivity extends BaseActivity {
    private static final String TAG = "ThrottleFirstActivity";
    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_throttle_first_example);
        textView = findViewById(R.id.textView);
        findViewById(R.id.button).setOnClickListener(view -> {
            getObservable()
                    .throttleFirst(500, TimeUnit.MILLISECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(getObserver());
        });
    }

    private Observable<String> getObservable() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("A");
                Thread.sleep(505);
                emitter.onNext("B");
                emitter.onNext("C");
                Thread.sleep(100);
                emitter.onNext("D");
                Thread.sleep(90);
                emitter.onNext("E");
                emitter.onComplete();
            }
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

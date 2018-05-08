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
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Kinvn on 2018/5/8.
 * <p>
 * Email:kinvn123@gmail.com
 */

public class WindowExampleActivity extends BaseActivity {
    private static final String TAG = "WindowExampleActivity";
    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window_example);
        textView = findViewById(R.id.textView);
        findViewById(R.id.button).setOnClickListener(view ->
                Observable.interval(1000,TimeUnit.MILLISECONDS)
                        .take(12)
                        .window(3, TimeUnit.SECONDS)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(getObserver()));
    }

    private Observer<Observable<Long>> getObserver() {
        return new Observer<Observable<Long>>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
            }

            @Override
            public void onNext(Observable<Long> stringObservable) {
                textView.append("onNext" + "\n");
                stringObservable
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(l -> textView.append(l + "\n"));
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

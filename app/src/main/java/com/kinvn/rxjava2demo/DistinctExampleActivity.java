package com.kinvn.rxjava2demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import com.example.kinvn.rxjava2demo.R;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Kinvn on 2018/5/4.
 * <p>
 * Email:kinvn123@gmail.com
 */

public class DistinctExampleActivity extends BaseActivity {
    private static final String TAG = "DistinctExampleActivity";
    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distinct_example);
        textView = findViewById(R.id.textView);
        findViewById(R.id.button).setOnClickListener(view ->
                getObservable()
                        .distinct()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(getObserver()));
    }

    private Observable<Integer> getObservable() {
        Integer[] integers = new Integer[]{1, 2, 2, 3, 3, 3, 4, 4};
        return Observable.fromArray(integers);
    }

    private Observer<Integer> getObserver() {
        return new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                textView.append(integer + "\n");
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

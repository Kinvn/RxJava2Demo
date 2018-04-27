package com.kinvn.rxjava2demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kinvn.rxjava2demo.R;

import io.reactivex.Flowable;
import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Kinvn on 2018/4/27.
 * <p>
 * Email:kinvn123@gmail.com
 */

public class FlowableExampleActivity extends BaseActivity{
    private static final String TAG = "FlowableExampleActivity";
    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flowable_example);
        textView = findViewById(R.id.textView);
        findViewById(R.id.button).setOnClickListener(view -> Flowable.just(1,2,3,4)
                .reduce(50, (integer, integer2) -> integer + integer2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver()));
    }

    private SingleObserver<Integer> getObserver() {
        return new SingleObserver<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
            }

            @Override
            public void onSuccess(Integer integer) {
                textView.setText("" + integer);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage() );
            }
        };
    }
}

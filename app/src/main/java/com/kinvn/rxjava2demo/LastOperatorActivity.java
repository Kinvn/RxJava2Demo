package com.kinvn.rxjava2demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import com.example.kinvn.rxjava2demo.R;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

/**
 * Created by Kinvn on 2018/5/4.
 * <p>
 * Email:kinvn123@gmail.com
 */

public class LastOperatorActivity extends BaseActivity {
    private static final String TAG = "LastOperatorActivity";
    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_operator_example);
        textView = findViewById(R.id.textView);
        findViewById(R.id.button).setOnClickListener(view ->
                getObservable()
                        .last("A")
                        .subscribe(getObserver()));
    }

    private Observable<String> getObservable() {
        return Observable.fromArray("A", "B", "C", "D", "E");
    }


    private SingleObserver<String> getObserver() {
        return new SingleObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
            }

            @Override
            public void onSuccess(String s) {
                textView.append(s);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage() );
            }
        };
    }
}

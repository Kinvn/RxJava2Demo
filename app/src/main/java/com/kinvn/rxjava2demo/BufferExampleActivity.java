package com.kinvn.rxjava2demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kinvn.rxjava2demo.R;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Kinvn on 2018/4/27.
 * <p>
 * Email:kinvn123@gmail.com
 */

public class BufferExampleActivity extends BaseActivity {
    private static final String TAG = "BufferExampleActivity";
    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buffer_example);
        textView = findViewById(R.id.textView);
        findViewById(R.id.button).setOnClickListener(view ->
                Observable.just("A", "B", "C", "D", "E")
                        .buffer(3, 1)
                        .subscribe(getObserver()));
    }

    private Observer<List<String>> getObserver() {
        return new Observer<List<String>>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
            }

            @Override
            public void onNext(List<String> strings) {
                textView.append("size:" + strings.size() + "\n");
                for (String s : strings) {
                    textView.append(s + "\n");
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage() );
            }

            @Override
            public void onComplete() {
                showToast("onComplete");
            }
        };
    }
}

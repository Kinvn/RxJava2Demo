package com.kinvn.rxjava2demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kinvn.rxjava2demo.R;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Kinvn on 2018/4/26.
 * <p>
 * Email:kinvn123@gmail.com
 */

public class SimpleExampleActivity extends BaseActivity {
    private static final String TAG = "SimpleExampleActivity";
    private TextView textView;
    private EditText editText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_example);
        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.edit_text);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(view -> getObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver()));
    }

    private Observable<String> getObservable() {
        return Observable.just(editText.getText().toString());
    }

    private Observer<String> getObserver() {
        return new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
            }

            @Override
            public void onNext(String s) {
                textView.append("\n" + s);
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

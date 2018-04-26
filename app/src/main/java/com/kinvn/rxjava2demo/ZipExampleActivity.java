package com.kinvn.rxjava2demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
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

public class ZipExampleActivity extends AppCompatActivity {
    private EditText editText1, editText2;
    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zip_example);
        editText1 = findViewById(R.id.edit_text_1);
        editText2 = findViewById(R.id.edit_text_2);
        textView = findViewById(R.id.textView);
        findViewById(R.id.button).setOnClickListener(view -> {
            Observable.zip(getFirstObservable(), getSecondObservable(),
                    (s1, s2) -> s1 + ", " + s2)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(getObserver());
        });
    }

    private Observable<String> getFirstObservable() {
        return Observable.create(e -> {
            if (!e.isDisposed()) {
                e.onNext(editText1.getText().toString());
                e.onNext(editText1.getText().toString());
                e.onNext(editText1.getText().toString());
            }
        });
    }

    private Observable<String> getSecondObservable() {
        return Observable.create(e -> {
            if (!e.isDisposed()) {
                e.onNext(editText2.getText().toString());
                e.onNext(editText2.getText().toString());
                e.onComplete();
            }
        });
    }

    private Observer<String> getObserver() {
        return new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                textView.append("\n" + s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Toast.makeText(ZipExampleActivity.this, "Completed!", Toast.LENGTH_SHORT).show();
            }
        };
    }
}

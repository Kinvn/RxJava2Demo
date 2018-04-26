package com.kinvn.rxjava2demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

public class MapExampleActivity extends AppCompatActivity {
    private static final String TAG = "MapExampleActivity";
    private EditText editText;
    private TextView textView;
    private int mSum;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_example);
        editText = findViewById(R.id.edit_text);
        textView = findViewById(R.id.textView);
        findViewById(R.id.button).setOnClickListener(view -> getObservable()
                .map(Integer::parseInt)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver()));
    }

    private Observable<String> getObservable() {
        return Observable.just(editText.getText().toString());
    }

    private Observer<Integer> getObserver() {
        return new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                mSum += integer;
                textView.setText("Sum is: " + mSum);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Toast.makeText(MapExampleActivity.this, "Completed!", Toast.LENGTH_SHORT).show();
            }
        };
    }
}

package com.kinvn.rxjava2demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.TextView;

import com.example.kinvn.rxjava2demo.R;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;

/**
 * Created by Kinvn on 2018/5/4.
 */

public class DeferExampleActivity extends BaseActivity {
    private static final String TAG = "DeferExampleActivity";
    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_defer_example);
        textView = findViewById(R.id.textView);
        findViewById(R.id.button).setOnClickListener(view ->{
            Car car = new Car();
            Observable<String> observable = car.getBrandObservable();
            car.setBrand("BMW");
            observable.subscribe(s -> textView.append(s));
        });
    }

    class Car {
        private String mBrand;

        String getBrand() {
            return TextUtils.isEmpty(mBrand) ? "" : mBrand;
        }

        void setBrand(String brand) {
            mBrand = brand;
        }

        Observable<String> getBrandObservable() {
            return Observable.defer(() -> Observable.just(mBrand));
        }
    }
}

package com.kinvn.rxjava2demo;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kinvn.rxjava2demo.R;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Kinvn on 2018/4/26.
 * <p>
 * Email:kinvn123@gmail.com
 */

public class DisposableObserverExampleActivity extends BaseActivity {
    private static final String TAG = "DisposableObserver";
    private CompositeDisposable disposables = new CompositeDisposable();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disposable_example);
        TextView textView = findViewById(R.id.textView);
        findViewById(R.id.button).setOnClickListener(
                view -> disposables.add(getObservable()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableObserver<String>() {
                            @Override
                            public void onNext(String s) {
                                textView.append(s + "\n");
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e(TAG, "onError:" + e.getMessage());
                            }

                            @Override
                            public void onComplete() {
                                showToast("onComplete");
                            }
                        })));
    }

    private Observable<String> getObservable() {
        return Observable.defer(() -> {
            SystemClock.sleep(2000);
            return Observable.just("A", "B", "C", "D", "E");
        });
    }
}

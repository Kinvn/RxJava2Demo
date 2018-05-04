package com.kinvn.rxjava2demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.kinvn.rxjava2demo.R;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //SampleExampleActivity
        findViewById(R.id.button_simple).setOnClickListener(view -> startActivity(SimpleExampleActivity.class));
        //MapExampleActivity
        findViewById(R.id.button_map).setOnClickListener(view -> startActivity(MapExampleActivity.class));
        //ZipExampleActivity
        findViewById(R.id.button_zip).setOnClickListener(view -> startActivity(ZipExampleActivity.class));
        //DisposableObserverExampleActivity
        findViewById(R.id.button_dispose).setOnClickListener(view -> startActivity(DisposableObserverExampleActivity.class));
        //TakeExampleActivity
        findViewById(R.id.button_take).setOnClickListener(view -> startActivity(TakeExampleActivity.class));
        //TimerExampleActivity
        findViewById(R.id.button_timer).setOnClickListener(view -> startActivity(TimerExampleActivity.class));
        //IntervalExampleActivity
        findViewById(R.id.button_interval).setOnClickListener(view -> startActivity(IntervalExampleActivity.class));
        //SingleExampleActivity
        findViewById(R.id.button_single).setOnClickListener(view -> startActivity(SingleExampleActivity.class));
        //CompletableExampleActivity
        findViewById(R.id.button_completable).setOnClickListener(view -> startActivity(CompletableExampleActivity.class));
        //FlowableExampleActivity
        findViewById(R.id.button_flowable).setOnClickListener(view -> startActivity(FlowableExampleActivity.class));
        //ReduceExampleActivity
        findViewById(R.id.button_reduce).setOnClickListener(view -> startActivity(ReduceExampleActivity.class));
        //BufferExampleActivity
        findViewById(R.id.button_buffer).setOnClickListener(view -> startActivity(BufferExampleActivity.class));
        //FilterExampleActivity
        findViewById(R.id.button_filter).setOnClickListener(view -> startActivity(FilterExampleActivity.class));
        //SkipExampleActivity
        findViewById(R.id.button_skip).setOnClickListener(view -> startActivity(SkipExampleActivity.class));
        //ScanExampleActivity
        findViewById(R.id.button_scan).setOnClickListener(view -> startActivity(ScanExampleActivity.class));
        //ReplayExampleActivity
        findViewById(R.id.button_replay).setOnClickListener(view -> startActivity(ReplayExampleActivity.class));
        //ContactExampleActivity
        findViewById(R.id.button_contact).setOnClickListener(view -> startActivity(ContactExampleActivity.class));
        //MergeExampleActivity
        findViewById(R.id.button_merge).setOnClickListener(view -> startActivity(MergeExampleActivity.class));
        //DeferExampleActivity
        findViewById(R.id.button_defer).setOnClickListener(view -> startActivity(DeferExampleActivity.class));
        //DistinctExampleActivity
        findViewById(R.id.button_distinct).setOnClickListener(view -> startActivity(DistinctExampleActivity.class));
        //LastOperatorExampleActivity
        findViewById(R.id.button_last).setOnClickListener(view -> startActivity(LastOperatorActivity.class));
        //ReplaySubjectExampleActivity
        findViewById(R.id.button_replay_subject).setOnClickListener(view -> startActivity(ReplaySubjectActivity.class));
        //PublishSubjectExampleActivity
        findViewById(R.id.button_publish_subject).setOnClickListener(view -> startActivity(PublishSubjectActivity.class));
        //BehaviorSubjectExampleActivity
        findViewById(R.id.button_behavior_subject).setOnClickListener(view -> startActivity(BehaviorSubjectActivity.class));
    }

    private void startActivity(Class<? extends Activity> clz) {
        Intent intent = new Intent(this, clz);
        startActivity(intent);
    }
}

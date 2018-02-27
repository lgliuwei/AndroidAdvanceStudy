package cn.codingblock.androidadvancestudy.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import cn.codingblock.androidadvancestudy.R;
import cn.codingblock.libutils.view.ViewUtils;

public class SingleTaskActivity extends AppCompatActivity {

    private final static String TAG = SingleTaskActivity.class.getSimpleName();

    private Button btn_start_test1;
    private Button btn_start_self;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_task);
        Log.i(TAG, "onCreate: ");

        btn_start_test1 = ViewUtils.find(this, R.id.btn_start_test1);
        btn_start_self = ViewUtils.find(this, R.id.btn_start_self);
        btn_start_test1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SingleTaskActivity.this, Test1Activity.class));
            }
        });

        btn_start_self.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(SingleTaskActivity.this, SingleTaskActivity.class));
            }
        });
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i(TAG, "onNewIntent: ");
        if (intent != null) {
            Log.i(TAG, "onNewIntent: intent=" + intent);
            Log.i(TAG, "onNewIntent: intent=" + intent.getStringExtra("info"));
        }

        Intent getIntent = getIntent();
        Log.i(TAG, "onNewIntent: getIntent = " + getIntent);
        if (getIntent != null) {
            Log.i(TAG, "onNewIntent: getIntent = " + getIntent.getStringExtra("info"));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
    }
}

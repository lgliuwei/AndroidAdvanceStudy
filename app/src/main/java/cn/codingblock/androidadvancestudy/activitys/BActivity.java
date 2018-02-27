package cn.codingblock.androidadvancestudy.activitys;

import android.app.Activity;
import android.content.res.Configuration;
import android.nfc.Tag;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.LoginFilter;
import android.util.Log;

import cn.codingblock.androidadvancestudy.R;

public class BActivity extends Activity {

    String TAG = BActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate:");
        if (savedInstanceState != null) {
            Log.i(TAG, "onCreate: Bundle=" + savedInstanceState);
            Log.i(TAG, "onCreate: Bundle=" + savedInstanceState.get("info"));
        }
        setContentView(R.layout.activity_b);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.i(TAG, "onConfigurationChanged: " + newConfig.getLayoutDirection());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("info", "activity state");
        Log.i(TAG, "onSaveInstanceState: Bundle=" + outState.get("info"));
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG, "onRestoreInstanceState: Bundle=" + savedInstanceState);
        Log.i(TAG, "onRestoreInstanceState: Bundle=" + savedInstanceState.get("info"));
    }
}

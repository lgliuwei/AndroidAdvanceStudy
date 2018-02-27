package cn.codingblock.optimize;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;

import cn.codingblock.libutils.view.ViewUtils;
import cn.codingblock.optimize.animator.AnimatorTestActivity;
import cn.codingblock.optimize.anr.AnrTestActivity;
import cn.codingblock.optimize.memory_leak.MemoryLeakTestActivity;
import cn.codingblock.optimize.uncaught_exception.ThrowExceptionActivity;

public class MainActivity extends Activity implements View.OnClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    private Button btn_show;
    private ViewStub vs_import;
    private Button btn_animator_test;
    private Button btn_anr_test;
    private Button btn_memory_leak_test;
    private Button btn_exception_handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_show = (Button) findViewById(R.id.btn_show);
        vs_import = (ViewStub) findViewById(R.id.vs_import);
        btn_animator_test = ViewUtils.find(this, R.id.btn_animator_test);
        btn_anr_test = ViewUtils.find(this, R.id.btn_anr_test);
        btn_memory_leak_test = ViewUtils.find(this, R.id.btn_memory_leak_test);
        btn_exception_handler = ViewUtils.find(this, R.id.btn_exception_handler);
        btn_show.setOnClickListener(this);
        btn_animator_test.setOnClickListener(this);
        btn_anr_test.setOnClickListener(this);
        btn_memory_leak_test.setOnClickListener(this);
        btn_exception_handler.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_show:
                // viewstub的两种加载方式
//                vs_import.inflate();
//                vs_import.setVisibility(View.VISIBLE);
                Log.i(TAG, "onClick: " + this + " | " + getApplicationContext());
                break;

            case R.id.btn_animator_test:
                startActivity(new Intent(getApplicationContext(), AnimatorTestActivity.class));
                break;

            case R.id.btn_anr_test:
                startActivity(new Intent(getApplicationContext(), AnrTestActivity.class));
                break;

            case R.id.btn_memory_leak_test:
                startActivity(new Intent(getApplicationContext(), MemoryLeakTestActivity.class));
                break;

            case R.id.btn_exception_handler:
                startActivity(new Intent(getApplicationContext(), ThrowExceptionActivity.class));
                break;
        }
    }
}

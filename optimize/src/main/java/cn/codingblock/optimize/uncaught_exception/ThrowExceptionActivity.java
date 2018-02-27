package cn.codingblock.optimize.uncaught_exception;

import android.support.v4.widget.SearchViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cn.codingblock.libutils.view.ViewUtils;
import cn.codingblock.optimize.R;

public class ThrowExceptionActivity extends AppCompatActivity {

    private Button btn_exception;
    private Button btn_subthread_exception;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_throw_exception);
        Thread.setDefaultUncaughtExceptionHandler(MyUncaughtExceptionHandler.getInstance());

        btn_exception = ViewUtils.find(this, R.id.btn_exception);
        btn_subthread_exception = ViewUtils.find(this, R.id.btn_subthread_exception);

        btn_exception.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                throw new RuntimeException("按钮点击事件运行时异常");
            }
        });

        btn_subthread_exception.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        throw new ArrayIndexOutOfBoundsException("子线程抛出数组越界异常");
                    }
                }).start();
            }
        });
    }
}

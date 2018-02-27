package cn.codingblock.optimize.memory_leak;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.codingblock.optimize.R;

public class MemoryLeakTestActivity extends AppCompatActivity {

    private static Context sContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_leak_test);
        sContext = this;
        Singleton.getInstance(this);
    }
}

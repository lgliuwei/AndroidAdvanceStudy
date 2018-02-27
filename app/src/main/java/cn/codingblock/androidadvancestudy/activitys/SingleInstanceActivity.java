package cn.codingblock.androidadvancestudy.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.codingblock.androidadvancestudy.R;

public class SingleInstanceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instance);
    }
}

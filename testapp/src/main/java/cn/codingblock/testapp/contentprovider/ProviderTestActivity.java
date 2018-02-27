package cn.codingblock.testapp.contentprovider;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import cn.codingblock.testapp.R;

public class ProviderTestActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_start_user_info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_test);
        btn_start_user_info = (Button) findViewById(R.id.btn_start_user_info);
        btn_start_user_info.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_start_user_info:
                startActivity(new Intent(getApplicationContext(), UserInfoResolverActivity.class));
                break;
        }
    }
}

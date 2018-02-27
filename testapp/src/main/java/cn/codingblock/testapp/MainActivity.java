package cn.codingblock.testapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cn.codingblock.testapp.contentprovider.ProviderTestActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btn_start_provider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_start_provider = (Button) findViewById(R.id.btn_start_provider);
        btn_start_provider.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_start_provider:
                startActivity(new Intent(getApplicationContext(), ProviderTestActivity.class));
                break;
        }
    }
}

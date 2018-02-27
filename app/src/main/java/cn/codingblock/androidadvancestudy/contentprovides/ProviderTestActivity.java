package cn.codingblock.androidadvancestudy.contentprovides;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cn.codingblock.androidadvancestudy.R;
import cn.codingblock.androidadvancestudy.contentprovides.system_provider.ContactProviderActivity;
import cn.codingblock.libutils.view.ViewUtils;

public class ProviderTestActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_start_user_info;
    Button btn_start_contact_provider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_test);
        btn_start_user_info = ViewUtils.find(this, R.id.btn_start_user_info);
        btn_start_contact_provider = ViewUtils.find(this, R.id.btn_start_contact_provider);
        btn_start_user_info.setOnClickListener(this);
        btn_start_contact_provider.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_start_user_info:
                startActivity(new Intent(getApplicationContext(), UserInfoResolverActivity.class));
                break;
            case R.id.btn_start_contact_provider:
                startActivity(new Intent(getApplicationContext(), ContactProviderActivity.class));
                break;
        }
    }
}

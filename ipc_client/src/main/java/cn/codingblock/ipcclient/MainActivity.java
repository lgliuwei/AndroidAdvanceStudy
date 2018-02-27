package cn.codingblock.ipcclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cn.codingblock.ipcclient.aidl.ContactMangerActivity;
import cn.codingblock.ipcclient.messenger.MessengerActivity;
import cn.codingblock.ipcclient.socket.TCPClientActivity;
import cn.codingblock.libutils.view.ViewUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewUtils.findAndOnClick(this, R.id.btn_messenger_activity, mOnClickListener);
        ViewUtils.findAndOnClick(this, R.id.btn_tcp_client_activity, mOnClickListener);
        ViewUtils.findAndOnClick(this, R.id.btn_test_aidl, mOnClickListener);
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_messenger_activity:
                    startActivity(new Intent(getApplicationContext(), MessengerActivity.class));
                    break;
                case R.id.btn_tcp_client_activity:
                    startActivity(new Intent(getApplicationContext(), TCPClientActivity.class));
                    break;
                case R.id.btn_test_aidl:
                    startActivity(new Intent(getApplicationContext(), ContactMangerActivity.class));
                    break;
            }
        }
    };
}

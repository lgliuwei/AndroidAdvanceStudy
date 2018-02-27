package cn.codingblock.ipc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cn.codingblock.ipc.aidl.contact.ContactManagerService;
import cn.codingblock.ipc.messager.MessengerActivity;
import cn.codingblock.ipc.socket.TCPSocketService;
import cn.codingblock.libutils.view.ViewUtils;

public class MainActivity extends AppCompatActivity {

    Context mContext;
    Activity mActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = getApplicationContext();
        mActivity = this;

        ViewUtils.findAndOnClick(this, R.id.btn_messenger_activity, mOnClickListener);
        ViewUtils.findAndOnClick(this, R.id.btn_start_tcp_socket_service, mOnClickListener);
        ViewUtils.findAndOnClick(this, R.id.btn_start_contact_manager_service, mOnClickListener);

    }

   private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_messenger_activity:
                    startActivity(new Intent(mActivity, MessengerActivity.class));
                    break;
                case R.id.btn_start_tcp_socket_service:
                    startService(new Intent(mActivity, TCPSocketService.class));
                    break;
                case R.id.btn_start_contact_manager_service:
                    startService(new Intent(mActivity, ContactManagerService.class));
                    break;
            }
        }
    };

    private IBinder.DeathRecipient deathRecipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            // ...
        }
    };
}

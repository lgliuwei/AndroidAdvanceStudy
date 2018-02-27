package cn.codingblock.ipcclient.messenger;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import cn.codingblock.ipcclient.AppConstants;
import cn.codingblock.ipcclient.R;
import cn.codingblock.libutils.view.ViewUtils;

/**
 *
 */
public class MessengerActivity extends AppCompatActivity {

    private final static String TAG = MessengerActivity.class.getSimpleName();

    Button btn_bind_messenger;
    Intent intent;
    Messenger messenger;

    private class MessengerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case AppConstants.MSG_FROM_SERVICE:
                    Log.i(TAG, "handleMessage: MSG_FROM_SERVICE:" + msg.getData().getString("msg"));
                    break;
                default:super.handleMessage(msg);
            }
        }
    }
    private Messenger clientMessenger = new Messenger(new MessengerHandler());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);
        btn_bind_messenger = ViewUtils.findAndOnClick(this, R.id.btn_bind_messenger, mOnClickListener);

        intent = new Intent();
        intent.setComponent(new ComponentName("cn.codingblock.ipc", "cn.codingblock.ipc.messager.MessengerService"));
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_bind_messenger:
                    // 绑定Service
                    bindService(intent, connection, BIND_AUTO_CREATE);
                    break;
            }
        }
    };

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // 链接messenger成功，利用service创建一个Messenger
            messenger = new Messenger(service);

            // 向服务端发送一条消息
            Message message = Message.obtain(null, AppConstants.MSG_FROM_CLIENT);
            Bundle bundle = new Bundle();
            bundle.putString("msg", "client bind messenger succeed!(from ipc_client)");
            message.setData(bundle);

            // 关键点，告诉服务器接收回复的messenger
            message.replyTo = clientMessenger;

            try {
                messenger.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            messenger = null;
        }
    };

    @Override
    protected void onDestroy() {
        unbindService(connection);
        super.onDestroy();
    }
}

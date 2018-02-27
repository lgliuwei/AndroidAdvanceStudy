package cn.codingblock.ipc.messager;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import cn.codingblock.ipc.AppConstants;

/**
 * Created by liuwei on 18/1/29.
 */
public class MessengerService extends Service {

    private static final String TAG = MessengerHandler.class.getSimpleName();

    private class MessengerHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case AppConstants.MSG_FROM_CLIENT:
                    // 接收客户端发来的消息
                    Log.i(TAG, "handleMessage: MSG_FROM_CLIENT：" + msg.getData().getString("msg"));

                    // 服务端收到消息后，给客户端发送回应
                    Messenger client = msg.replyTo;
                    Message replyMsg = Message.obtain(null, AppConstants.MSG_FROM_SERVICE);
                    Bundle bundle = new Bundle();
                    bundle.putString("msg", "ok，I will reply you soon！");
                    replyMsg.setData(bundle);
                    try {
                        client.send(replyMsg);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                default: super.handleMessage(msg);
            }
        }
    }

    private Messenger messenger = new Messenger(new MessengerHandler());

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }
}

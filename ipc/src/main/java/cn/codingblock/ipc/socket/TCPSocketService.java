package cn.codingblock.ipc.socket;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by liuwei on 18/2/5.
 */

public class TCPSocketService extends Service {

    private static final String TAG = TCPSocketService.class.getSimpleName();

    private boolean mIsServiceDestroyed = false;
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: 正在启动ServerSocket...");
        new Thread(new TcpServer()).start();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        mIsServiceDestroyed = true;
        super.onDestroy();
    }

    private class TcpServer implements Runnable {
        @Override
        public void run() {
            ServerSocket serverSocket;

            try {
                // 监听本地端口
                serverSocket = new ServerSocket(8088);
                Log.i(TAG, "run: 8088 started");
            } catch (IOException e) {
                Log.i(TAG, "run: 8088 failed");
                e.printStackTrace();
                return;
            }

            // 接收客户端请求
            while (!mIsServiceDestroyed) {
                try {
                    final Socket client = serverSocket.accept();

                    Log.i(TAG, "run: 接收客户端请求：client = " + client);

                    // 回应客户端
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            responeClient(client);
                        }
                    }).start();
                    
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    private void responeClient(Socket client) {
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            // 用于接收客户端消息
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));

            // 用于想客户端发送消息
            out = new PrintWriter(new OutputStreamWriter(client.getOutputStream()), true);

            // 向客户端发送消息
            out.println("欢迎交流！");

            while (!mIsServiceDestroyed) {
                // 读取客户端发来的消息
                Log.i(TAG, "run: in.readLine()");
                String msgFromClient = in.readLine();
                Log.i(TAG, "responeClient: msg from client:" + msgFromClient);

                if (msgFromClient == null) {
                    // 当客户端断开连接时，realLine()就会返回null，在此时跳出循环。
                    break;
                }

                // 向客户端回应消息
                out.println("已经收到你发来的消息：【" + msgFromClient + "】，请放心！");
            }

            Log.i(TAG, "responeClient: client quit！");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
                if (client != null) {
                    client.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

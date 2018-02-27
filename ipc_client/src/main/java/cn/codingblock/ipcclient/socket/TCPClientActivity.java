package cn.codingblock.ipcclient.socket;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import cn.codingblock.ipcclient.R;

/**
 * @author CodingBlock
 * @博客地址 http://www.cnblogs.com/codingblock/
 */
public class TCPClientActivity extends AppCompatActivity {
    private final static String TAG = TCPClientActivity.class.getSimpleName();

    private Socket mClientSocket;
    private PrintWriter mPrintWriter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tcp_client);

        new Thread(new Runnable() {
            @Override
            public void run() {
                // 循环连接服务端Socket
                Socket socket = null;
                while (socket == null) {
                    try {
                        // 指定服务端Socket地址和端口号，初始化Socket
                        socket = new Socket("localhost", 8088);
                        mClientSocket = socket;

                        mPrintWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                        Log.i(TAG, "onCreate: 连接服务端Socket成功!");
                    } catch (IOException e) {
                        e.printStackTrace();
                        SystemClock.sleep(1000); // 如果连接失败了，就等1s重试
                        Log.i(TAG, "onCreate: 连接服务端Socket失败，正在重试...");
                    }
                }

                // 连接成功后向服务端发送一条测试消息
                mPrintWriter.println("你好，服务端，我是客户端");

                // 成功后就去循环读取服务端发送过来的消息
                try {
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    while (!TCPClientActivity.this.isFinishing()) {
                        Log.i(TAG, "run: in.readLine()");
                        String msgFromServer = in.readLine();
                        Log.i(TAG, "onCreate: msg from server：" + msgFromServer);
                    }

                    // 循环结束，关闭相关流，关闭socket
                    Log.i(TAG, "onCreate: 客户端退出!");
                    in.close();
                    mPrintWriter.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 退出时关闭socket连接
        if (mClientSocket != null) {
            try {
                mClientSocket.shutdownInput();
                mClientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

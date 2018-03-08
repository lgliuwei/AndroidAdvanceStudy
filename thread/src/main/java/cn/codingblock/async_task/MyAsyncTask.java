package cn.codingblock.async_task;

import android.os.AsyncTask;
import android.os.SystemClock;
import android.util.Log;

/**
 * Created by liuwei on 18/2/28.
 */
public class MyAsyncTask extends AsyncTask<String, Integer, String> {

    private final static String TAG = MyAsyncTask.class.getSimpleName();

    private int taskSize = 10;
    private int taskId;

    public MyAsyncTask(int taskId) {
        this.taskId = taskId;
    }

    /**
     * 在异步任务执行之前调用
     * 执行在主线程中
     */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.i(TAG, "taskId= " + taskId + ": onPreExecute: ");
    }

    /**
     * 执行异步任务
     * 执行在线程池中
     * @param params
     * @return
     */
    @Override
    protected String doInBackground(String... params) {
        Log.i(TAG, "taskId= " + taskId + ": doInBackground: ");
        int i;
        for (i = 0; i < taskSize; i++) {
            SystemClock.sleep(1000);
            int progress = (int)((i / (float)taskSize) * 100);
            publishProgress(progress);

            if (isCancelled()) {
                break;
            }
        }
        return "taskId= " + taskId + ": 执行结果：" + (i / (float)taskSize) * 100 + "%";
    }

    /**
     * 当异步任务被取消时执行此方法，此时将不会再调用onPostExecute方法
     */
    @Override
    protected void onCancelled() {
        super.onCancelled();
        Log.i(TAG, "taskId= " + taskId + ": onCancelled: ");
    }

    @Override
    protected void onCancelled(String s) {
        super.onCancelled(s);
        Log.i(TAG, "taskId= " + taskId + ": onCancelled: result=" + s);
    }

    /**
     * 当异步任务执行进度改变时执行此方法
     * 执行在主线程中
     * @param values
     */
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        Log.i(TAG, "taskId= " + taskId + ": onProgressUpdate: 执行进度：" + values[0] + "%");
    }

    /**
     * 当异步任务执行完成后执行此方法
     * 执行在主线程中
     * @param s
     */
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.i(TAG, "taskId= " + taskId + ": onPostExecute: result=" + s);
    }
}

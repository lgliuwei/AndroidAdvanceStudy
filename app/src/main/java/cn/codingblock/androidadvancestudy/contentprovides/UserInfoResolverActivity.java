package cn.codingblock.androidadvancestudy.contentprovides;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import cn.codingblock.androidadvancestudy.R;

/**
 * 忽略此类，使用testapp Module下面的此类测试对应的provider
 */
public class UserInfoResolverActivity extends AppCompatActivity implements View.OnClickListener {

    private final static String TAG = UserInfoResolverActivity.class.getSimpleName();

    private ContentResolver contentResolver;
    private Uri uri = Uri.parse("content://cn.codingblock.androidadvancestudy.contentprovides.UserInfoProvider/");

    Button btn_insert;
    Button btn_query;
    Button btn_update;
    Button btn_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_resolver);
        // 获取ContentResolver对象
        contentResolver = getContentResolver();

        btn_insert = (Button) findViewById(R.id.btn_insert);
        btn_query = (Button) findViewById(R.id.btn_query);
        btn_update = (Button) findViewById(R.id.btn_update);
        btn_delete = (Button) findViewById(R.id.btn_delete);

        btn_insert.setOnClickListener(this);
        btn_query.setOnClickListener(this);
        btn_update.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_insert:
                insert();
                break;

            case R.id.btn_query:
                query();
                break;

            case R.id.btn_update:
                update();
                break;

            case R.id.btn_delete:
                delete();
                break;
        }
    }

    public void insert() {
        ContentValues values = new ContentValues(1);
        values.put("1", "添加数据1");
        Uri tempUri = contentResolver.insert(uri, values);
        Log.i(TAG, "insert: tempUri = " + tempUri);
    }

    public void query() {
        Cursor cursor = contentResolver.query(uri, null, "where", null, null);
        Log.i(TAG, "query: cursor = " + cursor);
    }

    public void update() {
        ContentValues values = new ContentValues(1);
        values.put("1", "第一条更新数据");
        int n = contentResolver.update(uri, values, "where", null);
        Log.i(TAG, "update: n = " + n);
    }

    public void delete() {
        int n = contentResolver.delete(uri, "where", null);
        Log.i(TAG, "delete: n = " + n);
    }




}

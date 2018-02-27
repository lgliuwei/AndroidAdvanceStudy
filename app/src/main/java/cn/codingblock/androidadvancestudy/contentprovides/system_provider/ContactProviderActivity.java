package cn.codingblock.androidadvancestudy.contentprovides.system_provider;

import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cn.codingblock.androidadvancestudy.R;
import cn.codingblock.libutils.view.ViewUtils;

public class ContactProviderActivity extends AppCompatActivity implements View.OnClickListener {

    private final static String TAG = ContactProviderActivity.class.getSimpleName();

    Button btn_insert;
    Button btn_query;
    Button btn_update;
    Button btn_delete;
    TextView tv_show;
    EditText et_name;
    EditText et_phone;
    String showContact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_provider);
        btn_insert = ViewUtils.find(this, R.id.btn_insert);
        btn_query = ViewUtils.find(this, R.id.btn_query);
        btn_update = ViewUtils.find(this, R.id.btn_update);
        btn_delete = ViewUtils.find(this, R.id.btn_delete);
        tv_show = ViewUtils.find(this, R.id.tv_show);

        et_name = ViewUtils.find(this, R.id.et_name);
        et_phone = ViewUtils.find(this, R.id.et_phone);

        btn_insert.setOnClickListener(this);
        btn_query.setOnClickListener(this);
        btn_update.setOnClickListener(this);
        btn_delete.setOnClickListener(this);

        tv_show.setMovementMethod(ScrollingMovementMethod.getInstance());
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

        String name = et_name.getText().toString();
        String phone = et_phone.getText().toString();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(phone)) {
            Toast.makeText(getApplicationContext(), "不能为空", Toast.LENGTH_LONG).show();
            return;
        }

        ContentValues values = new ContentValues();

        // 往插入一个空值以便获取id
        Uri rawContactUri = getContentResolver().insert(ContactsContract.RawContacts.CONTENT_URI, values);
        long rawContactId = ContentUris.parseId(rawContactUri);

        // ------插入联系人姓名------
        // 设置id
        values.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId);
        // 设置内容类型
        values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
        // 设置名字
        values.put(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME, name);
        // 插入姓名
        getContentResolver().insert(ContactsContract.Data.CONTENT_URI, values);
        values.clear();

        // ------插入联系人电话------
        values.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId);
        values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
        values.put(ContactsContract.CommonDataKinds.Phone.NUMBER, phone);
        values.put(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE);
        getContentResolver().insert(ContactsContract.Data.CONTENT_URI, values);

        Toast.makeText(getApplicationContext(), "添加成功", Toast.LENGTH_LONG).show();
        query();
    }

    public void query() {
        showContact = "";
        // 获取联系人的Cursor集合
        Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        while (cursor.moveToNext()) {
            // 获取联系人id
            String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            // 获取联系人姓名
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

            // 获取当前联系人id下的所有电话号码
            Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + id, null, null);
            showContact += "--id="+ id + " | name=" + name + "--\n";
            Log.i(TAG, "onCreate: ------id="+ id + " | name=" + name + "------");
            while (phones.moveToNext()) {
                String phone = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                showContact += "phone = " + phone + "\n";
                Log.i(TAG, "onCreate: phone = " + phone);
            }
            phones.close();
        }
        cursor.close();
        tv_show.setText(showContact);
    }

    public void update() {
    }

    public void delete() {
    }
}

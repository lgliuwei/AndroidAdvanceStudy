package cn.codingblock.ipcclient.aidl;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.SystemClock;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import cn.codingblock.ipcclient.R;
import cn.codingblock.libaidl.contacts.Contact;
import cn.codingblock.libaidl.contacts.IContactsManager;
import cn.codingblock.libutils.permission.PermissionManager;
import cn.codingblock.libutils.view.ViewUtils;

/**
 * Created by liuwei on 18/2/8.
 */
public class ContactMangerActivity extends AppCompatActivity {
    private static final String TAG = ContactMangerActivity.class.getSimpleName();

    private IContactsManager mIContactsManager;
    private EditText et_contact_name;
    private EditText et_contact_phone_number;
    private EditText et_contact_address;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_manger);

        ViewUtils.findAndOnClick(this, R.id.btn_add_contact, mOnClickListener);
        ViewUtils.findAndOnClick(this, R.id.btn_get_phone_number, mOnClickListener);
        ViewUtils.findAndOnClick(this, R.id.btn_get_name, mOnClickListener);
        ViewUtils.findAndOnClick(this, R.id.btn_get_contact, mOnClickListener);
        ViewUtils.findAndOnClick(this, R.id.btn_get_list, mOnClickListener);

        et_contact_name = ViewUtils.find(this, R.id.et_contact_name);
        et_contact_phone_number = ViewUtils.find(this, R.id.et_contact_phone_number);
        et_contact_address = ViewUtils.find(this, R.id.et_contact_address);

        intent = new Intent();
        intent.setComponent(new ComponentName("cn.codingblock.ipc", "cn.codingblock.ipc.aidl.contact.ContactManagerService"));
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);

    }

    private ServiceConnection serviceConnection = new ServiceConnection(){
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mIContactsManager = IContactsManager.Stub.asInterface(service);
            Log.i(TAG, "onServiceConnected: mIContactsManager=" + mIContactsManager);
            try {
                // 给service设置死亡代理
                service.linkToDeath(mDeathRecipient, 0);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mIContactsManager = null;
            Log.i(TAG, "onServiceDisconnected: ");
        }
    };

    private IBinder.DeathRecipient mDeathRecipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            // 当binder挂掉后就会执行此方法
            if (mIContactsManager == null) {
                return;
            }
            // 首先移除之前绑定的死亡代理
            mIContactsManager.asBinder().unlinkToDeath(mDeathRecipient, 0);
            mIContactsManager = null;

            // 然后重新绑定远程服务
            bindService(intent, serviceConnection, BIND_AUTO_CREATE);
        }
    };

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_add_contact:

                    Contact contact = new Contact(getEtContactPhoneNumber(), getEtContactName(), getEtContactAddress());
                    try {
                        mIContactsManager.addContact(contact);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }

                    break;

                case R.id.btn_get_phone_number:
                    String name = getEtContactName();

                    try {
                        Log.i(TAG, "onClick: " + name + "的电话：" + mIContactsManager.getPhoneNumber(name));
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }

                    break;

                case R.id.btn_get_name:

                    int number = getEtContactPhoneNumber();

                    try {
                        Log.i(TAG, "onClick: " + number + " 对应的名称：" + mIContactsManager.getName(number));
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }

                    break;

                case R.id.btn_get_contact:

                    int number1 = getEtContactPhoneNumber();

                    try {
                        Contact contact1 = mIContactsManager.getContact(number1);

                        System.out.println(contact1);

                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }

                    break;

                case R.id.btn_get_list:

                    try {
                        List<Contact> contacts = mIContactsManager.getContactList();
                        Log.i(TAG, "onClick: contacts的类型：" + contacts.getClass());
                        System.out.println(contacts);

                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }

                    break;
            }
        }
    };

    private String getEtContactName() {
        String str = et_contact_name.getText().toString();
        if (TextUtils.isEmpty(str)) {
            Toast.makeText(this, "请输入联系人名称", Toast.LENGTH_SHORT).show();
            return null;
        }
        return str;
    }

    private int getEtContactPhoneNumber() {
        String str = et_contact_phone_number.getText().toString();
        if (TextUtils.isEmpty(str)) {
            Toast.makeText(this, "请输入联系人电话", Toast.LENGTH_SHORT).show();
            return 0;
        }
        return Integer.valueOf(str);
    }

    private String getEtContactAddress() {
        String str = et_contact_address.getText().toString();
        if (TextUtils.isEmpty(str)) {
            Toast.makeText(this, "请输入联系人地址", Toast.LENGTH_SHORT).show();
            return null;
        }
        return str;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(serviceConnection);
    }
}

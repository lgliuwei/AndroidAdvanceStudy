package cn.codingblock.ipc.aidl.contact;

import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import java.security.Permission;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import cn.codingblock.libaidl.contacts.Contact;
import cn.codingblock.libaidl.contacts.IContactsManager;

/**
 * Created by liuwei on 18/2/8.
 */
public class ContactManagerService extends Service {

    private final static String TAG = ContactManagerService.class.getSimpleName();

    /**
     * 其实这里虽然使用了CopyOnWriteArrayList，在客户端获取这个list时也会在binder中自动转换成ArrayList
     */
    private CopyOnWriteArrayList<Contact> contacts = new CopyOnWriteArrayList<>();
//    private LinkedList<Contact> contacts = new LinkedList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: ContactManagerService started...");
        contacts.add(new Contact(110, "报警电话", "派出所"));
        contacts.add(new Contact(119, "火警电话", "消防局"));
        contacts.add(new Contact(112, "故障电话", "保障局"));

        System.out.println("现有的联系人：" + contacts);

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        if (checkCallingOrSelfPermission("cn.codingblock.permission.ACCESS_CONTACT_MANAGER") == PackageManager.PERMISSION_DENIED) {
            Log.i(TAG, "onBind: 权限校验失败，拒绝绑定...");
            return null;
        }
        Log.i(TAG, "onBind: 权限校验成功！");
        return new ContactManagerBinder();
    }

    private class ContactManagerBinder extends IContactsManager.Stub{

        /**
         * 根据号码返回手机号
         * @param name
         * @return
         * @throws RemoteException
         */
        @Override
        public int getPhoneNumber(String name) throws RemoteException {
            if (!TextUtils.isEmpty(name)) {
                for (Contact contact:contacts) {
                    if (contact.name.equals(name)){
                        return contact.phoneNumber;
                    }
                }
            }
            return 0;
        }

        /**
         * 根据号码返回名称
         * @param phoneNumber
         * @return
         * @throws RemoteException
         */
        @Override
        public String getName(int phoneNumber) throws RemoteException {
            for (Contact contact:contacts) {
                if (contact.phoneNumber == phoneNumber){
                    return contact.name;
                }
            }
            return null;
        }

        /**
         * 根据号码返回联系人对象
         * @param phoneNumber
         * @return
         * @throws RemoteException
         */
        @Override
        public Contact getContact(int phoneNumber) throws RemoteException {
            for (Contact contact:contacts) {
                if (contact.phoneNumber == phoneNumber) {
                    return contact;
                }
            }
            return null;
        }

        /**
         * 获取联系人集合
         * @return
         * @throws RemoteException
         */
        @Override
        public List<Contact> getContactList() throws RemoteException {
            Log.i(TAG, "getContactList: contacts的类型：" + contacts.getClass());
            return contacts;
        }

        /**
         * 添加联系人
         * @param contact
         * @return
         * @throws RemoteException
         */
        @Override
        public boolean addContact(Contact contact) throws RemoteException {
            if (contact != null) {
                return contacts.add(contact);
            }
            return false;
        }

        @Override
        public void testTagIn(Contact contact) throws RemoteException {

        }

        @Override
        public void testTagOut(Contact contact) throws RemoteException {

        }

        @Override
        public void testTagInOut(Contact contact) throws RemoteException {

        }
    }
}

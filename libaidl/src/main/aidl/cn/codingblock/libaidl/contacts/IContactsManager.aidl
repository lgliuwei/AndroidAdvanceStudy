package cn.codingblock.libaidl.contacts;

import cn.codingblock.libaidl.contacts.Contact;

interface IContactsManager {
    int getPhoneNumber(in String name);
    String getName(int phoneNumber);
    Contact getContact(int phoneNumber);
    List<Contact> getContactList();
    boolean addContact(in Contact contact);

    void testTagIn(in Contact contact);
    void testTagOut(out Contact contact);
    void testTagInOut(inout Contact contact);
}
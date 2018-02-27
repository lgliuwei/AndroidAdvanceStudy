package cn.codingblock.libaidl.contacts;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by liuwei on 18/2/8.
 */
public class Contact implements Parcelable {
    public int phoneNumber;
    public String name;
    public String address;

    public Contact(int phoneNumber, String name, String address) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.address = address;
    }

    public Contact() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(phoneNumber);
        dest.writeString(name);
        dest.writeString(address);
    }

    public void readFromParcel(Parcel parcel) {
        phoneNumber = parcel.readInt();
        name = parcel.readString();
        address = parcel.readString();
    }

    public final static Creator<Contact> CREATOR = new Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel source) {
            return new Contact(source);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };


    public Contact(Parcel parcel) {
        phoneNumber = parcel.readInt();
        name = parcel.readString();
        address = parcel.readString();
    }

    @Override
    public String toString() {
        return "Contact{" +
                "phoneNumber=" + phoneNumber +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}

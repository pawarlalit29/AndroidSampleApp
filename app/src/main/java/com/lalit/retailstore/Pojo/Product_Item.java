package com.lalit.retailstore.Pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by lalit on 31/7/15.
 */
public class Product_Item implements Parcelable{

    private String str_pid,str_imgurl,str_title,str_decs,str_category,str_price;
    private boolean flag_state;

    public Product_Item() {
    }

    public String getStr_imgurl() {
        return str_imgurl;
    }

    public void setStr_imgurl(String str_imgurl) {
        this.str_imgurl = str_imgurl;
    }

    public String getStr_title() {
        return str_title;
    }

    public void setStr_title(String str_title) {
        this.str_title = str_title;
    }

    public String getStr_decs() {
        return str_decs;
    }

    public void setStr_decs(String str_decs) {
        this.str_decs = str_decs;
    }

    public String getStr_pid() {
        return str_pid;
    }

    public void setStr_pid(String str_pid) {
        this.str_pid = str_pid;
    }

    public String getStr_category() {
        return str_category;
    }

    public void setStr_category(String str_category) {
        this.str_category = str_category;
    }

    public boolean isFlag_state() {
        return flag_state;
    }

    public void setFlag_state(boolean flag_state) {
        this.flag_state = flag_state;
    }

    public String getStr_price() {
        return str_price;
    }

    public void setStr_price(String str_price) {
        this.str_price = str_price;
    }

    public Product_Item (Parcel parcel) {
        this.str_pid = parcel.readString();
        this.str_title = parcel.readString();
        this.str_decs = parcel.readString();
        this.str_category = parcel.readString();
        this.str_imgurl = parcel.readString();
        this.str_price = parcel.readString();
        this.flag_state = parcel.readByte() != 0;     //myBoolean == true if byte != 0

    }

    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        // TODO Auto-generated method stub

        parcel.writeString(str_pid);
        parcel.writeString(str_title);
        parcel.writeString(str_decs);
        parcel.writeString(str_category);
        parcel.writeString(str_imgurl);
        parcel.writeString(str_price);
        parcel.writeByte((byte) (flag_state ? 1 : 0));     //if myBoolean == true, byte == 1

    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        @Override
        public Product_Item createFromParcel(Parcel parcel) {
            return new Product_Item(parcel);
        }

        @Override
        public Object[] newArray(int i) {
            return new Product_Item[i];
        }
    };

}

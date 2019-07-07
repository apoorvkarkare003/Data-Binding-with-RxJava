package com.test.tc.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;

import com.test.tc.BR;

public class Counter extends BaseObservable implements Parcelable {

    private String tenthChar;
    private String everyTenthChar;
    private String uniqueChar;

    public Counter() {

    }

    public Counter(Parcel in) {
        tenthChar = in.readString();
        everyTenthChar = in.readString();
        uniqueChar = in.readString();
    }

    public static final Creator<Counter> CREATOR = new Creator<Counter>() {
        @Override
        public Counter createFromParcel(Parcel in) {
            return new Counter(in);
        }

        @Override
        public Counter[] newArray(int size) {
            return new Counter[size];
        }
    };

    @Bindable
    public String getTenthChar() {
        return tenthChar;
    }

    public void setTenthChar(String tenthChar) {
        this.tenthChar = tenthChar;
        notifyPropertyChanged(BR.tenthChar);
    }

    @Bindable
    public String getEveryTenthChar() {
        return everyTenthChar;
    }

    public void setEveryTenthChar(String everyTenthChar) {
        this.everyTenthChar = everyTenthChar;
        notifyPropertyChanged(BR.everyTenthChar);
    }

    @Bindable
    public String getUniqueChar() {
        return uniqueChar;
    }

    public void setUniqueChar(String uniqueChar) {
        this.uniqueChar = uniqueChar;
        notifyPropertyChanged(BR.uniqueChar);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(tenthChar);
        parcel.writeString(everyTenthChar);
        parcel.writeString(uniqueChar);
    }
}

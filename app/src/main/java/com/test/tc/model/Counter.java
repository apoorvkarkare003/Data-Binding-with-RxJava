package com.test.tc.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.test.tc.BR;

public class Counter extends BaseObservable {

    private String tenthChar;
    private String everyTenthChar;
    private String uniqueChar;

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

}

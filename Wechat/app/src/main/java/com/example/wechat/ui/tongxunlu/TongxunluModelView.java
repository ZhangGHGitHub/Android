package com.example.wechat.ui.tongxunlu;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TongxunluModelView extends ViewModel {
    private MutableLiveData<String> mText;

    public TongxunluModelView() {
        mText = new MutableLiveData<>();
        mText.setValue("这是联系人页面");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
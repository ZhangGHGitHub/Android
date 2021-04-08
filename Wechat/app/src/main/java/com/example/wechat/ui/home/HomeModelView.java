package com.example.wechat.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeModelView extends ViewModel {
    private MutableLiveData<String> mText;

    public HomeModelView() {
        mText = new MutableLiveData<>();
        mText.setValue("这是聊天主页面");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
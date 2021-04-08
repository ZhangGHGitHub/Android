package com.example.wechat.ui.personal;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PersonalModelView extends ViewModel {
    private MutableLiveData<String> mText;

    public PersonalModelView() {
        mText = new MutableLiveData<>();
        mText.setValue("这是个人信息页面");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
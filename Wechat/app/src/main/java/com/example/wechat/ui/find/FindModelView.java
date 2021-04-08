package com.example.wechat.ui.find;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FindModelView extends ViewModel {
    private MutableLiveData<String> mText;

    public FindModelView() {
        mText = new MutableLiveData<>();
        mText.setValue("这是发现页面");
    }

    public LiveData<String> getText() {
        return mText;
    }
}

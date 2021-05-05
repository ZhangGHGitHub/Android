package com.example.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.fragment.databinding.DialogLoginBinding;

public class LoginDialog extends DialogFragment {
    private DialogLoginBinding binding;
    //定义接口对象
    private OnLoginInputListener listener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DialogLoginBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        //登录按钮的事件监听处理
        binding.btnLogin.setOnClickListener(v ->{
            String username = binding.etUsername.getText().toString().trim();
            String password = binding.etPassword.getText().toString();
            //触发MainActivity的确定按钮事件
            listener.onDialogPositiveClick(username,password);
            //隐藏登录对话框
            LoginDialog.this.dismiss();
        });
        //取消按钮的事件监听处理
        binding.btnCancel.setOnClickListener(v ->{
            //触发事件
            listener.onDialogNegativeClick(LoginDialog.this);
            //隐藏对话框
            LoginDialog.this.dismiss();
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        //将对话框的宽度设为MATH_PARENT
        final WindowManager.LayoutParams params = requireDialog().getWindow().getAttributes();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        requireDialog().getWindow().setAttributes(params);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.listener = (OnLoginInputListener) context;
    }

    //登陆信息的监听回调接口
    public interface OnLoginInputListener{
        public void onDialogPositiveClick(String name, String password);
        public void onDialogNegativeClick(DialogFragment dialogFragment);
    }

}

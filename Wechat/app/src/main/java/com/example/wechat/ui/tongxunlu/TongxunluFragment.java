package com.example.wechat.ui.tongxunlu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.wechat.R;

public class TongxunluFragment extends Fragment {
    private TongxunluModelView tongxunluModelView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        tongxunluModelView=
                new ViewModelProvider(this).get(TongxunluModelView.class);
        View root = inflater.inflate(R.layout.fragment_tongxunlu, container, false);
        final TextView textView = root.findViewById(R.id.text_tongxunlu);
        tongxunluModelView.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}

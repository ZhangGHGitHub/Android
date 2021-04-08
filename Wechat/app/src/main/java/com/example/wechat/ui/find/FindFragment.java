package com.example.wechat.ui.find;

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

public class FindFragment extends Fragment {
    private FindModelView findModelView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        findModelView =
                new ViewModelProvider(this).get(FindModelView.class);
        View root = inflater.inflate(R.layout.fragment_find, container, false);
        final TextView textView = root.findViewById(R.id.text_find);
        findModelView.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}

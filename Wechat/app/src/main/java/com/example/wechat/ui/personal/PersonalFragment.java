package com.example.wechat.ui.personal;

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

public class PersonalFragment extends Fragment {
    private PersonalModelView personalModelView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        personalModelView=
                new ViewModelProvider(this).get(PersonalModelView.class);
        View root = inflater.inflate(R.layout.fragment_personal, container, false);
        final TextView textView = root.findViewById(R.id.text_personal);
        personalModelView.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}

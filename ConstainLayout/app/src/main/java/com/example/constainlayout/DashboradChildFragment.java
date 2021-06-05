package com.example.constainlayout;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.constainlayout.databinding.FragmentDashboradChildBinding;

import java.text.MessageFormat;

public class DashboradChildFragment extends Fragment {

    private static final String ARGUMENT_POSITION = "argument_postion";
    private FragmentDashboradChildBinding binding;
    public DashboradChildFragment() {
    }

    public static DashboradChildFragment newInstance(int position) {
        DashboradChildFragment fragment = new DashboradChildFragment();
        Bundle args = new Bundle();
        args.putInt(ARGUMENT_POSITION,position);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDashboradChildBinding.inflate(inflater, container, false);
        return  binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            int position = getArguments().getInt(ARGUMENT_POSITION);
            binding.tvDashboard.setText(MessageFormat.format
                    ("{0}{1}",getString(R.string.title_dashboard),position));
        }
    }
}
package com.example.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.fragment.databinding.FragmentContentBinding;
public class ContentFragment extends Fragment {
    private static final String ARG_PARAM = "param";
    private FragmentContentBinding binding;
    private String mParam;
    public ContentFragment() {
        // Required empty public constructor
    }
    public static ContentFragment newInstance(String param) {
        ContentFragment fragment = new ContentFragment();
        //使用Bundle对象传递数据
        Bundle args = new Bundle();
        args.putString(ARG_PARAM, param);
        //传递Bundle对象
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam = getArguments().getString(ARG_PARAM);
        }
//          接收Activity传递的数据
//        Bundle bundle = getArguments();
//        if (bundle != null) {
//            mParam = bundle.getString(ARG_PARAM);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentContentBinding.inflate(inflater, container,false );
        View view = binding.getRoot();
        if (mParam != null) {
            binding.tvContent.setText(mParam);
        }
        binding.tvContent.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ContentFragment.this.listener.OnItemSelected("fragment->Activity传递数据");

                Bundle bundle = new Bundle();
                bundle.putString("title_name", "内容标题");
                getParentFragmentManager().setFragmentResult("title", bundle);
            }
        });
        binding.tvContent.setOnLongClickListener(v ->{
            final DeleteDialog deleteDialog = new DeleteDialog();
            deleteDialog.show(getParentFragmentManager(), "删除");
            return  true;
        });

        return view;
    }
    //定义接口对象
    private OnItemSelectedListener listener;
    //捕获接口实现
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            this.listener = (OnItemSelectedListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() +
                    "must implement OnHeadlineSelectedListener");
        }
    }
    //接口对象的set()方法
    public void setOnItemSelectedListener(OnItemSelectedListener callback) {
        this.listener = callback;
    }
    //定义接口
    public interface OnItemSelectedListener{
        void OnItemSelected(String content);
    }


}
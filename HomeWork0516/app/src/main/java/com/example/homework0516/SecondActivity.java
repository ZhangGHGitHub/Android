package com.example.homework0516;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.SeekBar;

import com.example.homework0516.databinding.ActivitySecondBinding;
import com.google.android.material.snackbar.Snackbar;

public class SecondActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener{
    private ActivitySecondBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        /**
         *
         * （2）：设置圆角
         * app:cardCornerRadius="10dp"
         * 动态设置：cardview.setRadius(10)
         *
         * （3）：设置内部的padding
         * app:contentPadding="10dp"
         * app:contentPaddingBottom="10dp"
         * app:contentPaddingLeft="10dp"
         * app:contentPaddingRight="10dp"
         * app:contentPaddingTop="10dp"
         * 动态：cardview.setContentPadding(5,5,5,5);
         *
         *（4）：设置阴影的大小
         * app:cardElevation="10dp"
         * 动态：cardview.setCardElevation(10);
         *
         *
         * */

        binding.cardCornerRadius.setOnSeekBarChangeListener(this);
        binding.cardElevation.setOnSeekBarChangeListener(this);
        binding.cardContentPadding.setOnSeekBarChangeListener(this);
    }


        /**
         * 在进度变更时触发。第三个参数为true表示用户拖动，为false表示代码设置进度
         * @param seekBar
         * @param progress
         * @param fromUser
         */
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            switch (seekBar.getId()) {
                case R.id.cardCornerRadius:
                    binding.cardView.setRadius(progress);
                    break;
                case R.id.cardContentPadding:
                    //应该是ContentPadding
                    binding.cardView.setContentPadding(progress,progress,progress,progress);
                    break;
                case R.id.cardElevation:
                    binding.cardView.setElevation(progress);
                    break;

            }
        }
        /**
         * 当开始拖动进度时触发
         * @param seekBar
         */
        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }
        /**
         * 在停止拖动进度时触发
         * @param seekBar
         */
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }



}


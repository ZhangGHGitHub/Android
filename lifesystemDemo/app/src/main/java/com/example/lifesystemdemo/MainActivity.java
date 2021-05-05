package com.example.lifesystemdemo;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;

import com.example.lifesystemdemo.databinding.ActivityMainBinding;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityMainBinding binding;
    private String messageContent;
    private Editable messageNumber;
    private Editable callNumber;
    private String browerPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


//          message_content = null;
//         callNumber ;
        binding.btnJump.setOnClickListener(this);
        binding.btnCall.setOnClickListener(this);
        binding.btnMessage.setOnClickListener(this);
        binding.btnBrowser.setOnClickListener(this);
        binding.btnShare.setOnClickListener(this);
    }



    @Override

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_jump:
                jump();
                break;
            case R.id.btn_call:
                callNumber = binding.calNumber.getText();
                call();
                break;
            case R.id.btn_message:
                messageNumber = binding.calNumber.getText();
                messageContent = String.valueOf(binding.calNumberMessage.getText());
                message();
                break;
            case R.id.btn_browser:
                browerPage = String.valueOf(binding.browserPage.getText());
                brower();
                break;
            case R.id.btn_share:
                share("分享内容");
                break;

        }
    }

    private ActivityResultLauncher<String> launcher = registerForActivityResult(
            new ResultContract(),
            new ActivityResultCallback<String>() {
                @Override
                public void onActivityResult(String result) {
                    binding.retDate.setText("返回的数据为：" + result);
                }
            }
    );
    //页面跳转
    private void jump() {
        Intent intent = new Intent();
        intent.setAction("com.example.implicit.intent.START");
        intent.addCategory("android.intent.category.DEFAULT");

        //第一种
        User user = new User("张三", 19);
        intent.putExtra("User", user);

        //intent.putExtra("姓名", "张三");
        //第二种
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(80);
        arrayList.add(90);
        arrayList.add(100);
        intent.putIntegerArrayListExtra("list", arrayList);
        //第三种
        final Bundle bundle = new Bundle();
        bundle.putString("班级", "软件2058");
        bundle.putIntegerArrayList("分数", arrayList);
        bundle.putSerializable("User", user);
        intent.putExtras(bundle);

        intent.putExtra("data", "Activity传递字符串");
        launcher.launch("传递数值");
        startActivity(intent);
    }

    //打电话
    private void call() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + (callNumber)));
        startActivity(intent);
    }

    //发短信
    private void message() {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("smsto:" + messageNumber));

        intent.putExtra("sms_body", messageContent);
        startActivity(intent);
    }

    //浏览网页
    private void brower() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://" + browerPage));
        startActivity(intent);
    }

    //分享
    private void share(String shareText) {
        final String txtMimeType = "text/plain";
        final String picMimeType = "image/*";
        final String chooser = "分享列表";
        ShareCompat.IntentBuilder.from(this)
                .setText(shareText)
                .setChooserTitle(chooser)
                .setType(txtMimeType)
                .setType(picMimeType)
                .startChooser();


    }

    class ResultContract extends ActivityResultContract<String, String> {
        @NonNull
        @Override
        public Intent createIntent(@NonNull Context context, String input) {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra("data", input);
            return intent;
        }

        @Override
        public String parseResult(int resultCode, @Nullable Intent intent) {
            if (resultCode == Activity.RESULT_OK && intent != null) {
                return intent.getStringExtra("data");
            }
            return null;
        }
    }
}


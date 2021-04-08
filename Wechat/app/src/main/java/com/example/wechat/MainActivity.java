package com.example.wechat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.app.ActionBar;
import android.app.Activity;
import android.content.ClipData;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewConfiguration;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wechat.ui.find.FindFragment;
import com.example.wechat.ui.home.HomeFragment;
import com.example.wechat.ui.personal.PersonalFragment;
import com.example.wechat.ui.tongxunlu.TongxunluFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.nav_view);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_tongxunlu,R.id.navigation_find, R.id.navigation_personal)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

//        navView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
//            @Override
//            public void onNavigationItemReselected(@NonNull MenuItem item) {
//                Log.e("ee","Reselected Item:"+item.getTitle());
//            }
//        });
//
//        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                Log.e("ee","Selected Item:"+item.getTitle());
//                onTabSelected(item.getItemId());
//                return true;
//            }
//        });
//        onTabSelected(R.id.text_home);


//        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navView.setOnNavigationItemSelectedListener(item -> {
            final int itemId=item.getItemId();
            if(itemId==R.id.WechatMessage){

                if (item.isChecked()){
                    return true;
                }
            item.setChecked(true);
            navController.navigate(R.id.navigation_home);
            }
            else if (itemId==R.id.Tongxunlu){
                if (item.isChecked()){
                    return true;
                }
                item.setChecked(true);
                navController.navigate(R.id.navigation_tongxunlu);
            }
            else if (itemId==R.id.Faxian){
                if (item.isChecked()){
                    return true;
                }
                item.setChecked(true);
                navController.navigate(R.id.navigation_find);
            }
            else if (itemId==R.id.Wo){
                if (item.isChecked()){
                    return true;
                }
                item.setChecked(true);
                navController.navigate(R.id.navigation_personal);
            }
            return false;
        });

//        setMenuOverflowAlways();
//        getActionBar().setDisplayShowHomeEnabled(true);//设置ActionBar应用图标不显示
        ActionBar actionBar = getActionBar();
        getSupportActionBar().setDisplayUseLogoEnabled(true);
//        actionBar.setDisplayShowHomeEnabled(true);


    }
//    private void onTabSelected(int id){
//        FragmentManager manager = getSupportFragmentManager();
//        FragmentTransaction transaction = manager.beginTransaction();
//        Fragment fragment = null;
//        switch (id){
//            case R.id.text_home:
//                fragment = new HomeFragment();
//                break;
//            case R.id.text_find:
//                fragment = new FindFragment();
//                break;
//            case R.id.text_personal:
//                fragment = new PersonalFragment();
//                break;
//            case R.id.text_tongxunlu:
//                fragment = new TongxunluFragment();
//                break;
//        }
//        transaction.replace(R.id.container,fragment);
//        transaction.commit();
//    }


//    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
//            = new BottomNavigationView.OnNavigationItemSelectedListener() {
//
//        @Override
//        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//            switch (item.getItemId()) {
//                case R.id.navigation_home:
//                    mTextMessage.setText(R.string.title_home);
//                    return true;
//                case R.id.navigation_find:
//                    mTextMessage.setText(R.string.title_find);
//                    return true;
//                case R.id.navigation_tongxunlu:
//                    mTextMessage.setText(R.string.title_tongxunlu);
//                    return true;
//                    case R.id.navigation_personal:
//                    mTextMessage.setText(R.string.title_personal);
//                    return true;
//            }
//            return false;
//        }
//    };

//    MenuItem teamChat=findViewById(R.id.teamChat);
    //初始化Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

//        menu.add(Menu.NONE,Menu.FIRST+7,8,"新增").setIcon(android.R.drawable.ic_input_add);//手动添加menu菜单
        getMenuInflater().inflate(R.menu.top_menu, menu);
        if(menu instanceof MenuBuilder){
            MenuBuilder m=(MenuBuilder)menu;
            m.setOptionalIconsVisible(true);
        }
        return true;

    }
    //添加Menu的点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search:
//   Toast.makeText(this, "检索按钮", Toast.LENGTH_SHORT).show();
                break;
            case R.id.addFriends:
//   Toast.makeText(this, "添加朋友", Toast.LENGTH_SHORT).show();
                break;
            case R.id.teamChat:
//   Toast.makeText(this, "群聊", Toast.LENGTH_SHORT).show();
                break;
            case R.id.look:
//   Toast.makeText(this, "扫一扫", Toast.LENGTH_SHORT).show();
                break;
            case R.id.getMoneny:
//   Toast.makeText(this, "收款", Toast.LENGTH_SHORT).show();
                break;
            case R.id.help:
//   Toast.makeText(this, "帮助与反馈", Toast.LENGTH_SHORT).show();
                break;
//            case Menu.FIRST+7:
//   Toast.makeText(this, "新增", Toast.LENGTH_SHORT).show();
//                break;
        }
        Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }

    //设置menu菜单的第一个图标显示在标题右上角---使用反射机制来完成
//    public void setMenuOverflowAlways(){
//        try {
//            ViewConfiguration config = ViewConfiguration.get(this);
//            Field field = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
//            field.setAccessible(true);
//            field.setBoolean(config, false);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    //设置每个Menu显示为左图标右标题
//    @Override
//    public boolean onMenuOpened(int featureId, Menu menu) {
//        if (featureId == Window.FEATURE_ACTION_BAR && menu != null) {
//            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
//                try {
//                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
//                    m.setAccessible(true);
//                    m.invoke(menu, true);
//                } catch (Exception e) {
//                }
//            }
//        }
//        return super.onMenuOpened(featureId, menu);
//    }
}
package com.example.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.fragment.databinding.ActivityMainBinding;
public class MainActivity extends AppCompatActivity implements ContentFragment.OnItemSelectedListener,
LoginDialog.OnLoginInputListener
{
    private ActivityMainBinding binding;
    private SparseArray<Fragment> fragments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragment();
        replaceFragment(R.id.fragment_title,fragments.get(R.id.fragment_title));
        replaceFragment(R.id.fragment_content,fragments.get(R.id.fragment_content));
    }
    public void initFragment(){
        fragments = new SparseArray<>();
        fragments.put(R.id.fragment_title, TitleFragment.newInstance());
        fragments.put(R.id.fragment_content, ContentFragment.newInstance("内容Fragment"));

    }

    private void replaceFragment(int containerId, Fragment fragment) {
        final FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .replace(containerId, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void OnItemSelected(String content) {
        Toast.makeText(this,content,Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.login_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        //处理菜单项的选择
        Toast.makeText(MainActivity.this,item.getTitle(), Toast.LENGTH_LONG).show();
        if (item.getItemId() == R.id.login_item) {
            final LoginDialog loginDialog = new LoginDialog();
            loginDialog.show(getSupportFragmentManager(),"登录");
        }
        return super.onOptionsItemSelected(item);
    }
    //onOption
    @Override
    public void onDialogPositiveClick(String name, String password) {
        Toast.makeText(MainActivity.this,name + ", " + password ,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialogFragment) {
        Toast.makeText(MainActivity.this,"取消", Toast.LENGTH_LONG).show();
    }
}
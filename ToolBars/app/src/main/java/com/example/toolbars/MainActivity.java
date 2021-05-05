package com.example.toolbars;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.toolbars.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private EditText emplist = null;
    String number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        LinearLayout root = binding.getRoot();
        setContentView(root);
        emplist=(EditText) super.findViewById(R.id.edit_text);
        registerForContextMenu(emplist);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.item_clear) {
            Toast.makeText(this, "清空", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.item_copy) {
            Toast.makeText(this, "拷贝", Toast.LENGTH_SHORT).show();
        } else if(item.getItemId() == R.id.item_paste)
            Toast.makeText(this, "粘贴", Toast.LENGTH_SHORT).show();
        return super.onContextItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId()==R.id.edit_text){
            getMenuInflater().inflate(R.menu.context_menu,menu);
        }
    }

    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        mode.getMenuInflater().inflate(R.menu.context_menu, menu );
        return true;
    }

//    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
//        if(item.getItemId() == R.id.edit_text ||
//            item.)
//    }


}
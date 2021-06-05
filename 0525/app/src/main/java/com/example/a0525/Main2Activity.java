package com.example.a0525;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentUris;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.a0525.databinding.ActivityMainBinding;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private static final String TAG = "MainActivity";
    private static final String FILE_NAME = "flower.jpg";
    private static final int REQUEST_PERMISSIONS = 0;
    private static final int REQUEST_READ = 1;
    private static final int REQUEST_WRITE = 2;
    private static final int REQUEST_PHOTO = 3;
    private static final int SELECT_PHOTO = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        initPermission();
    }


    private void init() {
        //从SD卡读取图片
        binding.btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readPictures();
            }
        });
        //将图片存入SD卡
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writePicture();
            }
        });
        //点击图片，从相册选择图片
        binding.ivPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAlbum();
            }
        });
    }

    //作用域存储，需要借助MediaStore API获取图片的Uri
    private void readPictures() {
        //读取SD卡上的文件
        Cursor cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, null, null, MediaStore.MediaColumns.DATE_ADDED + "desc");
        if (cursor != null) {
            if (cursor.moveToNext()) {
                long id = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.MediaColumns._ID));
                Uri uri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id);
                try {
                    loadPicture(uri);
                    Toast.makeText(Main2Activity.this, "读取成功", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "获取或解析图片失败，请检查权限", Toast.LENGTH_SHORT).show();
                }
            }
            cursor.close();
        }

    }

    //图片写入SD卡，针对Android 11的存储变更，使用MediaStore API项共享存储写入媒体文件，不需要申请权限
    private void writePicture() {
        String displayName = System.currentTimeMillis() + ".png";
        //获取外部存储的Pictures目录，创建存储文件
        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        if (!path.exists() && !path.mkdir()) {
            Log.e(TAG, "图片目录不能创建");
            return;
        }
        File file = new File(path, displayName);
        try {
            if (file.createNewFile()) {
                //获取ImageView的Bitmp图片对象
                BitmapDrawable drawable = (BitmapDrawable) binding.ivPic.getDrawable();
                Bitmap bitmap = drawable.getBitmap();
                FileOutputStream fos = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                fos.flush();
                fos.close();
                Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "保存失败", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    //打开相册
    private void openAlbum() {
        final Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, SELECT_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_OK && requestCode == SELECT_PHOTO && data != null) {
            final Uri uri = data.getData();
            if (uri != null) {
                try {
                    loadPicture(uri);
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "解析图片失败，请检查权限", Toast.LENGTH_SHORT).show();

                }
            } else {
                Toast.makeText(this, "获取图片失败，请检查权限", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //显示图片
    private void loadPicture(Uri uri) throws IOException {
        ParcelFileDescriptor fileDescriptor = getContentResolver().openFileDescriptor(uri, "r");
        if (fileDescriptor != null) {
            final Bitmap bitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor.getFileDescriptor());
            fileDescriptor.close();
            binding.ivPic.setImageBitmap(bitmap);
        }
    }

    //permission数组存放所有需要的申请权限
    String[] permissions = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
    //被禁止的权限集合
    List<String> deniedPermissions = new ArrayList<>();
    //权限判断和申请
    private void initPermission(){
        //清空以及允许的没有通过的权限
        deniedPermissions.clear();
        //逐个判断是否还有未通过的权限
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) ==
                    PackageManager.PERMISSION_DENIED) {
                //添加还未授予的权限到deniedPermissions中
                deniedPermissions.add(permission);
            }
        }
        //申请权限
        if (deniedPermissions.size() > 0) {
            //有权限没有通过，需要申请
            ActivityCompat.requestPermissions(this, permissions, REQUEST_PERMISSIONS);
        }else {
            //权限全部通过，初始化程序
            init();
        }
    }
    /**
     * 请求权限后回调的方法
     *
     * */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean isDismiss = false; //有权限没有通过
        if (REQUEST_PERMISSIONS == requestCode) {
            for (int grantResult : grantResults) {
                if (grantResult == PackageManager.PERMISSION_DENIED) {
                    isDismiss = true;
                    break;
                }
            }
        }
        if (isDismiss) {
            //有权限未被允许
            showPermissionDialog();
        } else {
            //权限全部通过后初始化
            init();
        }
    }

    /**
     * 不在提示权限时的展示对话框
     * */
    AlertDialog permissionDialog = null;
    String pakeName = "com.example.a0525";
    private void showPermissionDialog() {
        if (permissionDialog == null) {
            permissionDialog = new AlertDialog.Builder(this)
                    .setMessage("已禁用权限，请手动授予")
                    .setPositiveButton("设置", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            permissionDialog.cancel();
                            Uri pacageURI = Uri.parse("package:" + pakeName);
                            Intent intent = new Intent(Settings.ACTION_SHOW_WORK_POLICY_INFO, pacageURI);
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //关闭页面或者其他操作
                            permissionDialog.cancel();
                            Main2Activity.this.fileList();
                        }
                    })
                    .create();

        }
        permissionDialog.show();
    }

}
package com.example.constainlayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import com.example.constainlayout.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
private ActivityMainBinding binding;
    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.navigation_home:
                            binding.viewPager.setCurrentItem(0);
                            return true;
                        case R.id.navigation_dashiboard:
                            binding.viewPager.setCurrentItem(1);
                            return true;
                        case R.id.navigation_notifications:
                            binding.viewPager.setCurrentItem(2);
                            return true;
                    }
                    return false;
                }
            };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //???????????????ToolBar
        setSupportActionBar(binding.toolbar);
        //ViewPage???????????????
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        binding.viewPager.setAdapter(adapter);
        //??????Fragment??????????????????????????????????????????
        binding.viewPager.setOffscreenPageLimit(adapter.getCount() -1);

        binding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                binding.navigation.getMenu().getItem(position).setChecked(true);
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        
        //??????????????????
        binding.navigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        //?????????Toolbar?????????
        initTitle();
        //??????NavigationView
        bindNaviagtionDrawer();
        binding.floatActionButton.setOnClickListener(v -> onFabClicked(binding.coordinatorLayout));

    }

    //FloatingActionButton???????????????
    public void onFabClicked(View view) {
        showSnackbar();
    }
    private void bindNaviagtionDrawer() {
        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, binding.drawerLayout, binding.toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        binding.navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                showSnackbar(item.getTitle().toString());
                binding.drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    private void showSnackbar(String title) {
        Snackbar.make(binding.drawerLayout, "????????????"+title, Snackbar.LENGTH_SHORT).show();
    }
    private void showSnackbar() {
        Snackbar snack = Snackbar.make(binding.coordinatorLayout, "???????????? Custom Snackbar", Snackbar.LENGTH_INDEFINITE);
        //??????Action?????????
        snack.setAction("OK", v -> snack.dismiss());
        //?????????????????????
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) snack.getView().getLayoutParams();

        params.setAnchorId(R.id.navigation);
        params.anchorGravity = Gravity.TOP;
        params.gravity = Gravity.TOP;
        snack.getView().setLayoutParams(params);
        //??????
        snack.show();
    }

    private void initTitle() {
        binding.toolbar.post(() ->binding.toolbar.setTitle(binding.navigation.getMenu().getItem(0).getTitle()));
    }
    private static class MyFragmentPagerAdapter extends FragmentPagerAdapter{
        public MyFragmentPagerAdapter(@NonNull FragmentManager fm) {
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }
        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return HomeFragment.newInstance();
                case 1:
                    return DashboardFragment.newInstance();
                case 2:
                    return NotificationsFragment.newInstance();
            }
            return HomeFragment.newInstance();
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

}
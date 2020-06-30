package com.example.bawei6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.account.AccountFragment;
import com.example.bawei6.adapter.FragmentViewPagerAdapter;
import com.example.finalce.view.FinalceFragment;
import com.example.home.view.HomeFragment;
import com.example.more.MoreFragment;
import com.example.wiget.TitleBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bnvBottombar;
    private ViewPager vpMain;
    private ArrayList<Fragment> fragments;
    private TitleBar tbMain;
    private DrawerLayout dlMain;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
        initData();
    }

    private void initData() {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new FinalceFragment());
        fragments.add(new AccountFragment());
        fragments.add(new MoreFragment());
        FragmentViewPagerAdapter adapter = new FragmentViewPagerAdapter(getSupportFragmentManager(), fragments);
        vpMain.setAdapter(adapter);
    }

    private void initEvent() {
        //底部菜单切换
        bnvBottombar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_bottom_bar_home:
                        vpMain.setCurrentItem(0);
                        setLeftRight(true, true);
                        return true;
                    case R.id.menu_bottom_bar_account:
                        vpMain.setCurrentItem(2);
                        setLeftRight(false, false);
                        return true;

                    case R.id.menu_bottom_bar_finalce:
                        vpMain.setCurrentItem(1);
                        setLeftRight(false, false);

                        return true;
                    case R.id.menu_bottom_bar_more:

                        vpMain.setCurrentItem(3);
                        setLeftRight(false, false);

                        return true;
                }
                return false;
            }
        });

        vpMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                bnvBottombar.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tbMain.setTitleBarClickListener(new TitleBar.TitleBarClickListener() {
            @Override
            public void leftClick(View view) {
                dlMain.openDrawer(Gravity.LEFT);
            }

            @Override
            public void rightClick(View view) {
                Toast.makeText(MainActivity.this, "right click", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setLeftRight(boolean left, boolean right) {
        tbMain.setLeftVisible(left);
        tbMain.setRightVisible(right);
    }

    private void initView() {
        dlMain = findViewById(R.id.dl_main);
        bnvBottombar = findViewById(R.id.bnv_bottombar);
        vpMain = findViewById(R.id.vp_main);
        tbMain = findViewById(R.id.tb_main);

        dlMain.setScrimColor(Color.TRANSPARENT);

    }
}

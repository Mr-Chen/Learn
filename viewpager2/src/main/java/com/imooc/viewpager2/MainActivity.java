package com.imooc.viewpager2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 mViewPager2;
    private BottomNavigationView mNavigationView;

    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager2 = findViewById(R.id.vp);

        mNavigationView = findViewById(R.id.nav_view);


        fragments.add(BlankFragment.newInstance(0));
        fragments.add(BlankFragment.newInstance(1));
        fragments.add(BlankFragment.newInstance(2));
        fragments.add(BlankFragment.newInstance(3));
        MyViewPagerAdapter adapter = new MyViewPagerAdapter(getSupportFragmentManager(),
                getLifecycle(),fragments);
        mViewPager2.setAdapter(adapter);
        mViewPager2.setUserInputEnabled(false);

        //保留原icon颜色
        mNavigationView.setItemIconTintList(null);


       mViewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
           @Override
           public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
               super.onPageScrolled(position, positionOffset, positionOffsetPixels);
           }

           @Override
           public void onPageSelected(int position) {
               super.onPageSelected(position);
               switch (position) {
                   case 0:
                       mNavigationView.setSelectedItemId(R.id.tab_one);
                       break;
                   case 1:
                       mNavigationView.setSelectedItemId(R.id.tab_two);
                       break;
                   case 2:
                       mNavigationView.setSelectedItemId(R.id.tab_three);
                       break;
                   case 3:
                       mNavigationView.setSelectedItemId(R.id.tab_four);
                       break;
               }

           }

           @Override
           public void onPageScrollStateChanged(int state) {
               super.onPageScrollStateChanged(state);
           }
       });

       mNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
           @Override
           public boolean onNavigationItemSelected(@NonNull MenuItem item) {
               switch (item.getItemId()) {
                   case R.id.tab_one:
                       mViewPager2.setCurrentItem(0,false);
                       break;
                   case R.id.tab_two:
                       mViewPager2.setCurrentItem(1,false);
                       break;
                   case R.id.tab_three:
                       mViewPager2.setCurrentItem(2,false);
                       break;
                   case R.id.tab_four:
                       mViewPager2.setCurrentItem(3,false);
                       break;
               }
               return true;
           }
       });
    }
}
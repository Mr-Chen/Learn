package com.imooc.mvp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.gyf.immersionbar.ImmersionBar;
import com.imooc.mvp.ui.base.BaseActivity;
import com.imooc.mvp.ui.base.BaseFragment;
import com.imooc.mvp.ui.cart.CartFragment;
import com.imooc.mvp.ui.home.HomeFragment;
import com.imooc.mvp.ui.mine.MineFragment;

public class MainActivity extends BaseActivity implements NavigationBarView.OnItemSelectedListener {

    private int latestFragmentIndex = 0;

    private BottomNavigationView bottomView;
    private BaseFragment[] fragments = {new HomeFragment(), new CartFragment(), new MineFragment()};


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {


        bottomView = find(R.id.bottom_nav);
        bottomView.setItemIconTintList(null);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, fragments[0])
                .commit();

        bottomView.setOnItemSelectedListener(this);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                switchFragment(0);
                break;
            case R.id.cart:
                switchFragment(1);
                break;
            case R.id.mine:
                switchFragment(2);
                break;
        }
        return true;
    }

    private void switchFragment(int to) {
        if (latestFragmentIndex == to) {
            return;
        }

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (!fragments[to].isAdded()) {
            transaction.add(R.id.container, fragments[to]);
        } else {
            transaction.show(fragments[to]);
        }
        transaction.hide(fragments[latestFragmentIndex]);
        transaction.commit();

        latestFragmentIndex = to;
    }
}
package cn.fulldroid.lib.example.tab;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

import cn.fulldroid.lib.example.BaseActivity;
import cn.fulldroid.lib.example.R;
import cn.fulldroid.lib.example.fragment.Tab1Fragment;
import cn.fulldroid.lib.tab.SlidingTabLayout;
import cn.fulldroid.lib.tab.listener.OnTabSelectListener;
import cn.fulldroid.lib.tab.widget.MsgView;

/**
 * Created by MDZZ on 2017/3/22.
 */

@ContentView(R.layout.activity_top_tab)
public class TopTabActivity extends BaseActivity implements OnTabSelectListener {

    private Context mContext = this;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private final String[] mTitles = {"头条", "推荐", "体育", "娱乐", "视频", "科技", "汽车"};
    private MyPagerAdapter mAdapter;

    @ViewInject(R.id.viewPager)
    private ViewPager viewPager;

    @ViewInject(R.id.slidingTabLayout)
    private SlidingTabLayout slidingTabLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        for (String title : mTitles) {
            mFragments.add(new Tab1Fragment());
        }


        mAdapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mAdapter);

        slidingTabLayout.setViewPager(viewPager);
        slidingTabLayout.setOnTabSelectListener(this);

        slidingTabLayout.showDot(4);

        slidingTabLayout.showMsg(3, 5);
        slidingTabLayout.setMsgMargin(3, 0, 10);
        MsgView rtv_2_3 = slidingTabLayout.getMsgView(3);
        if (rtv_2_3 != null) {
            rtv_2_3.setBackgroundColor(Color.parseColor("#6D8FB0"));
        }

        slidingTabLayout.showMsg(5, 5);
        slidingTabLayout.setMsgMargin(5, 0, 10);


    }

    @Override
    public void onTabSelect(int position) {
        Toast.makeText(mContext, "onTabSelect&position--->" + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTabReselect(int position) {
        Toast.makeText(mContext, "onTabReselect&position--->" + position, Toast.LENGTH_SHORT).show();
    }


    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }
}

package cn.fulldroid.lib.example.tab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

import cn.fulldroid.lib.example.BaseActivity;
import cn.fulldroid.lib.example.R;
import cn.fulldroid.lib.example.entity.TabEntity;
import cn.fulldroid.lib.example.fragment.Examp1Fragment;
import cn.fulldroid.lib.example.fragment.Examp2Fragment;
import cn.fulldroid.lib.example.fragment.Examp3Fragment;
import cn.fulldroid.lib.example.fragment.Examp4Fragment;
import cn.fulldroid.lib.statusbar.StatusBarHelper;
import cn.fulldroid.lib.tab.CommonTabLayout;
import cn.fulldroid.lib.tab.listener.CustomTabEntity;
import cn.fulldroid.lib.tab.listener.OnTabSelectListener;

/**
 * Created by MDZZ on 2017/3/22.
 */

@ContentView(R.layout.activity_bottom_tab)
public class BottomTabActivity extends BaseActivity {

    @ViewInject(R.id.viewPager)
    private ViewPager viewPager;

    @ViewInject(R.id.tabLayout)
    private CommonTabLayout tabLayout;

    private ArrayList<Fragment> mFragments = new ArrayList<>();

    private String[] mTitles = {"首页", "消息", "联系人", "更多"};

    private int[] mIconUnselectIds = {
            R.drawable.tab_home_unselect, R.drawable.tab_speech_unselect,
            R.drawable.tab_contact_unselect, R.drawable.tab_more_unselect
    };

    private int[] mIconSelectIds = {
            R.drawable.tab_home_select, R.drawable.tab_speech_select,
            R.drawable.tab_contact_select, R.drawable.tab_more_select
    };

    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置状态栏颜色，支持miui6，Flyme4，android6.0以上
        StatusBarHelper.setStatusBarDark(this);

        mFragments.add(new Examp1Fragment());
        mFragments.add(new Examp2Fragment());
        mFragments.add(new Examp3Fragment());
        mFragments.add(new Examp4Fragment());

        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }

        initView();

    }

    private void initView() {
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        tabLayout.setTabData(mTabEntities);
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
                if (position == 0) {
                    tabLayout.showMsg(0, 10);
//                    UnreadMsgUtils.show(mTabLayout_2.getMsgView(0), mRandom.nextInt(100) + 1);
                }
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        viewPager.setCurrentItem(0);

        //两位数
        tabLayout.showMsg(0, 55);
        tabLayout.setMsgMargin(0, -5, 5);

        //三位数
        tabLayout.showMsg(1, 100);
        tabLayout.setMsgMargin(1, -5, 5);

        //设置未读消息红点
        tabLayout.showDot(2);

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

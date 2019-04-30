package com.hansysu.plan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity
        implements
        View.OnClickListener {

    private ViewPager mViewPager;

    private List<Fragment> mList;
    private Fragment mOne;
    private Fragment mTwo;
    private Fragment mThree;
    private Fragment mFour;

    private ImageButton progress;
    private ImageButton complete;
    private ImageButton statistics;
    private ImageButton add;
    private TextView title;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = (ViewPager) findViewById(R.id.fragment);
        //加载Fragment
        mList = new ArrayList<>();
        mOne = new MainFragment();
        mTwo = new CompletePlan();
        mThree = new StatisFrag();
        mList.add(mTwo);
        mList.add(mOne);
        mList.add(mThree);
//        mList.add(mFour);

        progress = (ImageButton) findViewById(R.id.progress);
        complete = (ImageButton) findViewById(R.id.complete);
        statistics = (ImageButton) findViewById(R.id.statistics);
        title = (TextView)findViewById(R.id.title);
        add = (ImageButton) findViewById(R.id.add);
        progress.setOnClickListener(this);
        complete.setOnClickListener(this);
        statistics.setOnClickListener(this);
        add.setOnClickListener(this);
        //设置到ViewPager中
        mViewPager.setAdapter(new ContentsPagerAdapter(
                getSupportFragmentManager()));
        mViewPager.setCurrentItem(1);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    void changeToPage(int position){

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.progress :
                mViewPager.setCurrentItem(1);
                title.setText("进行中");
                break;
            case R.id.complete :
                mViewPager.setCurrentItem(0);
                title.setText("已完成");
                break;
            case R.id.statistics :
                title.setText("统计");
                mViewPager.setCurrentItem(2);
                break;
            case R.id.add :
                Intent intent=new Intent(MainActivity.this, AddPlanActivity.class);
                startActivity(intent);
                break;
        }
    }


    class ContentsPagerAdapter extends FragmentStatePagerAdapter {

        public ContentsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mList.get(position);
        }

        @Override
        public int getCount() {
            return mList.size();
        }
    }
}
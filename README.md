# TabView
使用时关注TabSelectView与TabFrgmAdapter，将TabSelectView、TabFrgmAdapter、ViewPager三者关联起来使用
UI满足不了的情况，参照TabSelectView创建BindPagerAdapterView子类即可。
设置OnPageChangeListener与调用setCurrentItem时，需使用BindPagerAdapterView（即TabSelectView）来设置调用相应方法。



用法：
公司工程代码的使用不方便贴，所以写了一个比较粗糙的使用类，及很丑的效果图，忽略这种细节就好。


![](https://raw.githubusercontent.com/yeerik/TabView/master/sample/img/20150816174018172.jpg)




package com.example.tabtest;

import com.idroid.tab.TabFrgmAdapter;
import com.idroid.tab.TabSelectView;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new TabFrgmAdapter(getSupportFragmentManager(), AFrgm.class, BFrgm.class, CFrgm.class));
        TabSelectView mTabsView = (TabSelectView) findViewById(R.id.tabs);

        mTabsView.setViewPager(viewPager);
        final String[] strs = new String[] { "首页", "第二页", "我" };
        final int[] images = new int[] { R.drawable.image_slt, R.drawable.image_slt, R.drawable.image_slt };

        mTabsView.setAdapter(new BaseAdapter() {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                convertView = View.inflate(parent.getContext(), R.layout.tab_item, null);
                ImageView image = (ImageView) convertView.findViewById(R.id.item_image);
                image.setImageResource(images[position]);
                TextView textView = (TextView) convertView.findViewById(R.id.item_text);
                textView.setText(strs[position]);
                return convertView;
            }

            @Override
            public long getItemId(int position) {
                // TODO Auto-generated method stub
                return 0;
            }

            @Override
            public Object getItem(int position) {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public int getCount() {
                // TODO Auto-generated method stub
                return strs.length;
            }
        });

        mTabsView.setCurrentItem(0);
    }

}

package com.example.tabtest;

import com.idroid.tab.TabFrgmAdapter; import com.idroid.tab.TabSelectView;

import android.os.Bundle; import android.support.v4.app.FragmentActivity; import android.support.v4.view.ViewPager; import android.view.View; import android.view.ViewGroup; import android.view.Window; import android.widget.BaseAdapter; import android.widget.ImageView; import android.widget.TextView;

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
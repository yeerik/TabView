package com.idroid.tab;

import android.support.v4.view.ViewPager;

public interface ITabView extends ViewPager.OnPageChangeListener {
	void setViewPager(ViewPager viewPager);

	void setCurrentItem(int item);

	void setOnPageChangeListener(ViewPager.OnPageChangeListener listener);
}

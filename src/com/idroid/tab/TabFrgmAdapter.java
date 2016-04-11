package com.idroid.tab;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class TabFrgmAdapter extends FragmentStatePagerAdapter {

	/**
	 * 按从左到右的顺序加入frgm
	 */
	public TabFrgmAdapter(FragmentManager fm, Class<? extends Fragment>... cls) {
		super(fm);
		mFrgmList = new ArrayList<Fragment>();
		for (Class<? extends Fragment> clazz : cls) {
			Fragment frgm = newInstance(clazz);
			mFrgmList.add(frgm);
		}
	}

	private Fragment newInstance(Class<? extends Fragment> clazz) {
		try {
			return clazz.newInstance();
		} catch (Exception e) {
		}
		return null;
	}

	private List<Fragment> mFrgmList;

	@Override
	public int getCount() {
		return mFrgmList.size();
	}

	@Override
	public Fragment getItem(int position) {
		return mFrgmList.get(position);
	}

}

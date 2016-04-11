package com.idroid.tab;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


public abstract class BindPagerAdapterView extends BasicAdapterView implements ITabView {
    private View mOldSelView;
    private ViewPager.OnPageChangeListener mOnPageChangeL;
    private ViewPager mViewPager;

    public BindPagerAdapterView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {
        if (mOnPageChangeL != null) {
            mOnPageChangeL.onPageSelected(arg0);
        }
    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
        if (mOnPageChangeL != null) {
            mOnPageChangeL.onPageScrolled(arg0, arg1, arg2);
        }
    }

    @Override
    public void onPageSelected(int arg0) {
        if (mOnPageChangeL != null) {
            mOnPageChangeL.onPageSelected(arg0);
        }
    }

    @Override
    public void setViewPager(ViewPager view) {
        if (mViewPager == view || view == null)
            return;

        if (mViewPager != null) {
            mViewPager.setOnPageChangeListener(null);
        }

        mViewPager = view;
        mViewPager.setOnPageChangeListener(this);

    }

    @Override
    public void setCurrentItem(int item) {
        if (item >= getChildCount() || mViewPager == null)
            return;
        View v = getChildAt(item);
        if (mOldSelView != null) {
            mOldSelView.setSelected(false);
            mOldSelView.setPressed(false);
        }
        v.setSelected(true);
        v.setPressed(true);
        mOldSelView = v;
        mViewPager.setCurrentItem(item, false);
    }

    @Override
    public void setOnPageChangeListener(OnPageChangeListener listener) {
        mOnPageChangeL = listener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                int pos = getTouchPos(event);
                setCurrentItem(pos);
                View v = getChildAt(pos);
                performItemClick(v, pos, 0);
                break;
            case MotionEvent.ACTION_UP:

                break;
            default:
                break;
        }
        return false;
    }

    /**
     * 获利点击的位置,因自定义view的计算不同，需子类来实现
     */
    public abstract int getTouchPos(MotionEvent event);

}

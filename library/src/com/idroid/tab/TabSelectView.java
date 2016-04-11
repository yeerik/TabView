package com.idroid.tab;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


public class TabSelectView extends BindPagerAdapterView {

	public TabSelectView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		widthMeasureSpec = MeasureSpec.makeMeasureSpec(widthSize, MeasureSpec.EXACTLY);
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		int heightSize = MeasureSpec.getSize(heightMeasureSpec);
		int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

		if (modeHeight == MeasureSpec.EXACTLY) {
			setMeasuredDimension(widthSize, heightSize);
			return;
		}

		int childHeight = 0;
		int count = getChildCount();
		for (int i = 0; i < count; i++) {
			View child = getChildAt(i);
			measureChild(child, widthMeasureSpec, heightMeasureSpec);
			childHeight = Math.max(childHeight, child.getMeasuredHeight());
		}
		setMeasuredDimension(widthSize, childHeight);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		int maxWidth = getMeasuredWidth();
		int maxHeight = getMeasuredHeight();
		int count = getChildCount();
		final int tabWidth = maxWidth / count;

		for (int i = 0; i < getChildCount(); i++) {
			View child = getChildAt(i);
			int childWidth = child.getMeasuredWidth();
			int left = i * tabWidth + (tabWidth - childWidth) / 2;
			int right = left + childWidth;
			child.layout(left, 0, right, maxHeight);
		}
	}

	@Override
	public int getTouchPos(MotionEvent event) {
		int maxWidth = getMeasuredWidth();
		int count = getChildCount();
		final int tabWidth = maxWidth / count;
		int x = (int) event.getX();
		for (int i = 0; i < count; i++) {
			if (x <= tabWidth * (i + 1)) {
				return i;
			}
		}
		return 0;
	}

}

package com.idroid.tab;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;

public abstract class BasicAdapterView extends AdapterView<ListAdapter> {

	private ListAdapter mAdapter;
	private AdapterDataSetObserver mDataSetObserver;

	public BasicAdapterView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public ListAdapter getAdapter() {
		return mAdapter;
	}

	@Override
	public void setAdapter(ListAdapter adapter) {
		if (mAdapter != null && mDataSetObserver != null) {
			mAdapter.unregisterDataSetObserver(mDataSetObserver);
		}
		mAdapter = adapter;
		if (mAdapter != null) {
			mDataSetObserver = new AdapterDataSetObserver();
			mAdapter.registerDataSetObserver(mDataSetObserver);
			makeView();
		} else {
			removeAllViewsInLayout();
			invalidate();
		}
	}

	private void makeView() {
		removeAllViewsInLayout();
		int count = mAdapter.getCount();
		for (int i = 0; i < count; i++) {
			View child = mAdapter.getView(i, null, this);
			LayoutParams params = child.getLayoutParams();
			if (params == null)
				params = generateDefaultLayoutParams();
			addViewInLayout(child, i, params);
		}
	}

	private class AdapterDataSetObserver extends DataSetObserver {

		@Override
		public void onChanged() {
			super.onChanged();
			makeView();
			requestLayout();
		}
	}

	@Override
	public View getSelectedView() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSelection(int position) {
		// TODO Auto-generated method stub

	}

}

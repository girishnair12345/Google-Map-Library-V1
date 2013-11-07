package com.trigyn.library.googlemaps;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.Toast;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class DialogItemizedOverlay extends ItemizedOverlay<OverlayItem> {

	private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
	private Context mContext;
	private OnOverlayPinClicked mOnOverlayPinClicked;

	public DialogItemizedOverlay(Drawable defaultMaker, Object Activity) {
		super(boundCenterBottom(defaultMaker));
		mContext = (Context) Activity;
		if (Activity instanceof OnOverlayPinClicked)
			mOnOverlayPinClicked = (OnOverlayPinClicked) Activity;
		else
			mOnOverlayPinClicked = null;
	}

	public void addOverlay(OverlayItem overlay) {
		mOverlays.add(overlay);
		populate();
	}

	@Override
	protected OverlayItem createItem(int i) {
		return mOverlays.get(i);
	}

	@Override
	public int size() {
		return mOverlays.size();
	}

	@Override
	protected boolean onTap(int index) {
		OverlayItem item = mOverlays.get(index);
		AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
		dialog.setTitle(item.getTitle());
		dialog.setMessage(item.getSnippet());
		dialog.show();
		if (mOnOverlayPinClicked != null)
			mOnOverlayPinClicked.onPinClicked(mOverlays.get(index), index);
		return true;
	}
}

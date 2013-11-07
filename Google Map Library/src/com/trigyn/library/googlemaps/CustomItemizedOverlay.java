package com.trigyn.library.googlemaps;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.Toast;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class CustomItemizedOverlay extends ItemizedOverlay<OverlayItem>{

	private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();  
	private OnOverlayPinClicked mOnOverlayPinClicked;
	
	public CustomItemizedOverlay(Drawable defaultMaker,Object Activity){
		super(boundCenterBottom(defaultMaker));
		if(Activity instanceof OnOverlayPinClicked)
			mOnOverlayPinClicked = (OnOverlayPinClicked) Activity;
		else
			mOnOverlayPinClicked = null;
	}
	
	public void addOverlay(OverlayItem overlay){
		mOverlays.add(overlay);
		populate();
	}
	
	@Override
	protected OverlayItem createItem(int i){
		return mOverlays.get(i);
	}
	
	@Override
	public int size(){
		return mOverlays.size();
	}
	
	@Override
	protected boolean onTap(int index){
		//Show no dialog
		if(mOnOverlayPinClicked != null)
		mOnOverlayPinClicked.onPinClicked(mOverlays.get(index), index);
		return true;
	}
}

package com.trigyn.library.googlemaps;


import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener;
import android.view.ViewConfiguration;
import android.widget.Toast;

public class CustomGoogleMap extends com.google.android.maps.MapView  {

	private static final String LOG_TAG = "Google Maps";
	private long lastTouchTime = -1;
	private ScaleGestureDetector mScaleGestureDetector;
	private boolean doubleTap = true;
	private boolean pinchToZoom = true;
	public boolean isPinchToZoom() {
		return pinchToZoom;
	}


	public void setPinchToZoom(boolean pinchToZoom) {
		this.pinchToZoom = pinchToZoom;
	}


	public boolean isDoubleTap() {
		return doubleTap;
	}


	public void setDoubleTap(boolean doubleTap) {
		this.doubleTap = doubleTap;
	}


	public CustomGoogleMap(Context context, AttributeSet attrs) {
		super(context, attrs);
		mScaleGestureDetector = new ScaleGestureDetector(context,new MySimpleOnScaleGestureListener());
	}

	
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		if(pinchToZoom)
		mScaleGestureDetector.onTouchEvent(ev);
		return super.onTouchEvent(ev);
	}


	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		if(doubleTap){
			 if (ev.getAction() == MotionEvent.ACTION_DOWN) {
			      long thisTime = System.currentTimeMillis();
			      if (thisTime - lastTouchTime < ViewConfiguration.getDoubleTapTimeout()) {
			        // Double tap
			        this.getController().zoomInFixing((int) ev.getX(), (int) ev.getY());
			        lastTouchTime = -1;
			      } else {
			        // Too slow 
			        lastTouchTime = thisTime;
			      }
			    }
		}
	    return super.onInterceptTouchEvent(ev);
	}
	
	private class MySimpleOnScaleGestureListener extends SimpleOnScaleGestureListener{

		//for returns true and false
		//Whether or not the detector should consider this event as handled
		@Override
		public boolean onScale(ScaleGestureDetector detector){
			CustomGoogleMap.this.getController().zoomInFixing((int) detector.getFocusX(), (int) detector.getFocusY());
			return true;
		}
	}
}

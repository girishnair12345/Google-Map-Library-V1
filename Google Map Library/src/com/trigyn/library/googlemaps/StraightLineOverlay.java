package com.trigyn.library.googlemaps;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;

class StraightLineOverlay extends Overlay{
	private Projection mProjection;
	GeoPoint[] gp;
	Point[] mPoints;
	Paint paint;
	
	public StraightLineOverlay(Paint paint, MapView mMapView,GeoPoint...geoPoints){
		mProjection = mMapView.getProjection();
		gp = geoPoints;
		mPoints = new Point[geoPoints.length];
		this.paint = paint;
	}
	
	public void draw(Canvas mCanvas, MapView mMapView, boolean Shadow){
		super.draw(mCanvas, mMapView, Shadow);
		
		Paint mPaint;
		if(this.paint == null){
			mPaint = new Paint();
			mPaint.setDither(true);
			mPaint.setColor(Color.RED);
			mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
			mPaint.setStrokeJoin(Paint.Join.ROUND);
			mPaint.setStrokeCap(Paint.Cap.ROUND);
			mPaint.setStrokeWidth(2.0f);
		}else{
			mPaint = this.paint;
		}
		
		
		Path mPath = new Path();
		
		for(int x=0; x < gp.length; x++){
			mPoints[x] = new Point();
			mProjection.toPixels(gp[x], mPoints[x]);
		}
		for(int x=gp.length-1; x > 0; x--){
			mPath.moveTo(mPoints[x].x, mPoints[x].y);
			mPath.lineTo(mPoints[x-1].x, mPoints[x-1].y);
		}
	
	    
	    mCanvas.drawPath(mPath, mPaint);
	}
	
}

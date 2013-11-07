package com.example.google.maps.library.sample;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.OverlayItem;
import com.trigyn.library.googlemaps.CustomGoogleMap;
import com.trigyn.library.googlemaps.CustomItemizedOverlay;
import com.trigyn.library.googlemaps.GoogleMapLibrary;
import com.trigyn.library.googlemaps.OnOverlayPinClicked;
import com.trigyn.library.googlemaps.RoutePathData;
import com.trigyn.library.googlemaps.deletethis;

public class MainActivity extends MapActivity implements OnOverlayPinClicked,com.trigyn.library.googlemaps.onRoutePathLoaded{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn1 = (Button) findViewById(R.id.btnZoom);
        
        
        CustomGoogleMap mMap = (CustomGoogleMap) findViewById(R.id.mapview);
        final GoogleMapLibrary mMyMap = 
        		new GoogleMapLibrary(mMap,getApplicationContext());
        mMyMap.enableDoubleTapZoom(true);
        mMyMap.enablePinchToZoom(true);
        mMyMap.setBuiltInZoomControls(true);
        
        btn1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
		        //To add pins
		       CustomItemizedOverlay itemizedoverlay = new CustomItemizedOverlay(getResources()
						.getDrawable(R.drawable.ic_launcher),this);

		        mMyMap.addPins(itemizedoverlay, deletethis.gp1, true);
		        
			}
		});
        
//        //To add pins
//       CustomItemizedOverlay itemizedoverlay = new CustomItemizedOverlay(getResources()
//				.getDrawable(R.drawable.ic_launcher),this);
//    
//        float lat = 46.399041f;
//		float lng = 99.154358f;
//        GeoPoint point = new GeoPoint((int)(lat * 1E6), (int)(lng * 1E6));
//        mMyMap.addPins(itemizedoverlay, point, true);
//        mMyMap.addPins(itemizedoverlay, 46.405670f, 98.04f, true);
//       
//        //To add pins with dialog box 
//        DialogItemizedOverlay Dialogitemizedoverlay = new DialogItemizedOverlay(getResources()
//				.getDrawable(R.drawable.ic_launcher), this);
//        float lat1 = 46.939012f;
//		float lng2 = 99.354858f;
//        GeoPoint point1 = new GeoPoint((int)(lat1 * 1E6), (int)(lng2 * 1E6));
//        mMyMap.addPins(Dialogitemizedoverlay,"Title 1","Message 1", point1, true);
//        mMyMap.addPins(Dialogitemizedoverlay, "Title 2","Message 2",46.263443f, 99.338379f, true);
        
//        //Draw straight line
//        GeoPoint gp1 = getGeoPointFromLatLong(10.217625f,5.888672f);
//	    GeoPoint gp2 = getGeoPointFromLatLong(10.649811f,6.921387f);
//	    GeoPoint gp3 = getGeoPointFromLatLong(10.649811f,6.921387f);
//	    GeoPoint gp4 = getGeoPointFromLatLong(9.925566f,6.987305f);
//	    GeoPoint gp5 = getGeoPointFromLatLong(9.112945f,5.734863f);
//	    GeoPoint gp6 = getGeoPointFromLatLong(10.055403f,5.449219f); 
//	    GeoPoint gp7 = getGeoPointFromLatLong(9.351513f,6.712646f);
//	    Paint mPaint;
//	    mPaint = new Paint();
//		mPaint.setDither(true);
//		mPaint.setColor(Color.BLUE);
//		mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
//		mPaint.setStrokeJoin(Paint.Join.ROUND);
//		mPaint.setStrokeCap(Paint.Cap.ROUND);
//		mPaint.setStrokeWidth(2.0f);
//       mMyMap.drawStraightLine(mPaint,gp1,gp2,gp3,gp4,gp5,gp6,gp7);
        GeoPoint gp1 = new GeoPoint(35952967, -83929158);
        GeoPoint gp2 = new GeoPoint(45956567, -83925450);
 //       Log.d("Points", "35952967 " + 35952967/1e6 + " -83929158 " + -83929158/1e6);
 //       Log.d("Points", "35952967 " + 35952967/1e6 + " -83925450 " + -83925450/1e6);
       //mMyMap.showRoutePath(gp1, gp2, mMap.getMaxZoomLevel() - 5);
       mMyMap.getRoutePath(null,this,46.952967f,-83.929158f,35.952967f,-83.92545f,false);
//        mMyMap.showRoutePath(4.434044f,14.370117f,
//        		3.853293f,11.491699f,mMap.getMaxZoomLevel() - 5);
//        mMyMap.showRoutePath((float)(35952967/1e6),(float)(-83929158/1e6),
//        		(float)(35952967/1e6),(float)(-83925450/1e6),mMap.getMaxZoomLevel() - 5);
        
    }

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onPinClicked(OverlayItem mOverlayItem, int index) {
		Toast.makeText(getApplicationContext(), "Pin Clicked" + index, Toast.LENGTH_LONG).show();
	}

	@Override
	public void RoutePathLoaded(RoutePathData mRoutePathData) {
		if(mRoutePathData!= null)
			Toast.makeText(getApplicationContext(), "Got it", Toast.LENGTH_SHORT).show();
		else
			Toast.makeText(getApplicationContext(), "Got it but is null", Toast.LENGTH_SHORT).show();
	}
}

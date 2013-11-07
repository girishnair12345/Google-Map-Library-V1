package com.trigyn.library.googlemaps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class GoogleMapLibrary {

	private CustomGoogleMap mMapView;
	private CustomItemizedOverlay itemizedoverlay;
	private List<Overlay> mapOverlays;
	private CustomItemizedOverlay mMyItemizedOverlayDialog;
	private Context mContext;
	boolean routeIsDisplayed = false;
	RouteSegmentOverlay route;
	int numberRoutePoints;	
	GeoPoint routePoints [];   // Dimension will be set in class RouteLoader below
	int routeGrade []; 
	private String LOG_TAG = "Google Maps";
	private onRoutePathLoaded mRoutePathLoader;
	private boolean inGetRoutePath,drawforGetRoutePath;
	private Paint PaintForRoutePath = null;
	
	public GoogleMapLibrary(CustomGoogleMap mMapView, Context mContext){
		this.mMapView = mMapView;	
		this.mContext = mContext;
	}
	
	public void enableDoubleTapZoom(boolean val){
		mMapView.setDoubleTap(val);
	}
	
	public void enablePinchToZoom(boolean val){
		mMapView.setPinchToZoom(val);
	}
	
	public void setBuiltInZoomControls(boolean val){
		mMapView.setBuiltInZoomControls(val);
	}
	
	public void addPins(CustomItemizedOverlay mMyItemizedOverlay, GeoPoint mGeoPoint,boolean ZoomToPoint){
		OverlayItem item = new OverlayItem(mGeoPoint, "", "");
		if(mMyItemizedOverlay == null)
			throw new RuntimeException("CustomItemizedOverlay Is NULL. Please initialize it");
		mMyItemizedOverlay.addOverlay(item);
		mMapView.getOverlays().add(mMyItemizedOverlay);
		mMapView.postInvalidate();
		
		if(ZoomToPoint)
			mMapView.getController().animateTo(mGeoPoint);
		
	}
	
	public void addPins(CustomItemizedOverlay mMyItemizedOverlay, float latitude, float longitude,boolean ZoomToPoint){
		GeoPoint mGeoPoint = getGeoPointFromLatLong(latitude, longitude);
		OverlayItem item = new OverlayItem(mGeoPoint, "", "");
		if(mMyItemizedOverlay == null)
			throw new RuntimeException("CustomItemizedOverlay Is NULL. Please Initialize It");
		mMyItemizedOverlay.addOverlay(item);
		mMapView.getOverlays().add(mMyItemizedOverlay);
		mMapView.postInvalidate();
		
		if(ZoomToPoint)
			mMapView.getController().animateTo(mGeoPoint);	
	}
	
	public void addPins(DialogItemizedOverlay mDialogItemizedOverlay,String DialogTitle, String DialogMessage,GeoPoint mGeoPoint,boolean ZoomToPoint){
		OverlayItem item = new OverlayItem(mGeoPoint, DialogTitle, DialogMessage);
		if(mDialogItemizedOverlay == null)
			throw new RuntimeException("DialogItemizedOverlay Is NULL. Please Initialize It");
		mDialogItemizedOverlay.addOverlay(item);
		mMapView.getOverlays().add(mDialogItemizedOverlay);
		mMapView.postInvalidate();
		
		if(ZoomToPoint)
			mMapView.getController().animateTo(mGeoPoint);
		
	}

	public void addPins(DialogItemizedOverlay mDialogItemizedOverlay, String DialogTitle, String DialogMessage,float latitude, float longitude,boolean ZoomToPoint){
		GeoPoint mGeoPoint = getGeoPointFromLatLong(latitude, longitude);
		OverlayItem item = new OverlayItem(mGeoPoint,DialogTitle, DialogMessage);
		if(mDialogItemizedOverlay == null)
			throw new RuntimeException("DialogItemizedOverlay Is NULL. Please Initialize It");
		mDialogItemizedOverlay.addOverlay(item);
		mMapView.getOverlays().add(mDialogItemizedOverlay);
		mMapView.postInvalidate();
		
		if(ZoomToPoint)
			mMapView.getController().animateTo(mGeoPoint);	
	}
	
	private static GeoPoint getGeoPointFromLatLong(float latitude, float longitude){
		return new GeoPoint((int)(latitude * 1E6), (int)(longitude * 1E6));
	}
	
	public void drawStraightLine(Paint mPaint, GeoPoint...geoPoints){
		mMapView.getController().zoomToSpan(geoPoints[0].getLatitudeE6(), geoPoints[0].getLongitudeE6());
		mMapView.getController().animateTo(geoPoints[0]);
		mMapView.getOverlays().add(new StraightLineOverlay(mPaint,mMapView,geoPoints));
		mMapView.postInvalidate();
	}
	
	public void drawStraightLine(GeoPoint...geoPoints){
	
		mMapView.getController().zoomToSpan(geoPoints[0].getLatitudeE6(), geoPoints[0].getLongitudeE6());
		mMapView.getController().animateTo(geoPoints[0]);
		mMapView.getOverlays().add(new StraightLineOverlay(null,mMapView,geoPoints));
		mMapView.postInvalidate();
	}
	
	public void showRoutePath(Paint mPaint,float latitude1, float longitude1, float latitude2, float longitude2){
		inGetRoutePath = false;
		Log.i(LOG_TAG, "Loading started");
		//String URL = "http://maps.googleapis.com/maps/api/directions/json?origin=46.952967,-83.929158&destination=35.952967,-83.92545&sensor=false";
		String URL = "http://maps.googleapis.com/maps/api/directions/json?origin="+latitude1+","+longitude1+"&destination="+latitude2+","+longitude2+"&sensor=false";
		new DrawRouteTask().execute(URL);
		PaintForRoutePath = mPaint;
	}
	
	public void getRoutePath(Paint mPaint,Activity activity, float latitude1, float longitude1, float latitude2, float longitude2,boolean drawRoutePath){
		inGetRoutePath = true;
		drawforGetRoutePath = drawRoutePath;
		mRoutePathLoader =(onRoutePathLoaded) activity;
		
		Log.i(LOG_TAG, "Loading started");
		//String URL = "http://maps.googleapis.com/maps/api/directions/json?origin=46.952967,-83.929158&destination=35.952967,-83.92545&sensor=false";
		String URL = "http://maps.googleapis.com/maps/api/directions/json?origin="+latitude1+","+longitude1+"&destination="+latitude2+","+longitude2+"&sensor=false";
		new DrawRouteTask().execute(URL);
		PaintForRoutePath = mPaint;
	}
	

	private class DrawRouteTask extends AsyncTask<String, Void, String> {

		InputStream is = null;
		JSONObject mJsonData = null;
		String json = "";
		String LOG_TAG = "Google Maps";
		RoutePathData rpd;
		@Override
		protected String doInBackground(String... URL) {
			
			// Making HTTP request
			try {
				DefaultHttpClient httpClient = new DefaultHttpClient();
				HttpPost httpPost = new HttpPost(URL[0]);
				HttpResponse httpResponse = httpClient.execute(httpPost);
				HttpEntity httpEntity = httpResponse.getEntity();
				is = httpEntity.getContent();
				// Log.i(LOG_TAG, "Connection Made Successfully");
			} catch (UnsupportedEncodingException e) {
				Log.e(LOG_TAG, "UnsupportedEncodingException - " + e.getMessage());
				e.printStackTrace();
				return "Failed";
			} catch (ClientProtocolException e) {
				Log.e(LOG_TAG, "ClientProtocolException - " + e.getMessage());
				e.printStackTrace();
				return "Failed";
			} catch (IOException e) {
				Log.e(LOG_TAG, "IOException - " + e.getMessage());
				e.printStackTrace();
				return "Failed";
			}

			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(
						is, "iso-8859-1"), 8);

				StringBuilder sb = new StringBuilder();
				String line = null;
				while ((line = reader.readLine()) != null) {
					sb.append(line + "\n");
				}
				is.close();
				json = sb.toString();
				// Log.i(LOG_TAG, "JSON :=> " +sb.toString());

			} catch (Exception e) {
				Log.e(LOG_TAG, "Error converting result " + e.toString());
				return "Failed";
			}

			// try parse the string to a JSON object
			try {
				mJsonData = new JSONObject(json);
			} catch (JSONException e) {
				Log.e(LOG_TAG, "Error parsing data " + e.toString());
				return "Failed";
			}
			
			
			try {
				rpd = new RoutePathData();
				rpd.setStatus(mJsonData.getString("status"));
				JSONObject Json = mJsonData.getJSONArray("routes").getJSONObject(0);
				
				JSONObject Bounds = Json.getJSONObject("bounds");
				rpd.setBounds_northeast_latitude(Bounds.getJSONObject("northeast").getString("lat"));
				rpd.setBounds_northeast_longitude(Bounds.getJSONObject("northeast").getString("lng"));
				rpd.setBounds_southwest_latitude(Bounds.getJSONObject("southwest").getString("lat"));
				rpd.setBounds_southwestt_longitude(Bounds.getJSONObject("southwest").getString("lng"));

				//Copyrights
				rpd.setCopyrights(Json.getString("copyrights"));
				
				//legs
				JSONArray legs = Json.getJSONArray("legs");
				rpd.setDistance_text(legs.getJSONObject(0).getJSONObject("distance").getString("text"));
				rpd.setDistance_value(legs.getJSONObject(0).getJSONObject("distance").getString("value"));
				rpd.setDuration_text(legs.getJSONObject(0).getJSONObject("duration").getString("text"));
				rpd.setDuration_value(legs.getJSONObject(0).getJSONObject("duration").getString("value"));
				
				// Start Location
				rpd.setStart_address(legs.getJSONObject(0).getString("start_address"));
				rpd.setStart_location_latitude(legs.getJSONObject(0).getJSONObject("start_location").getString("lat"));
				rpd.setStart_location_longitude(legs.getJSONObject(0).getJSONObject("start_location").getString("lng"));

				// End Location
				rpd.setEnd_address(legs.getJSONObject(0).getString("end_address"));
				rpd.setEnd_location_latitude(legs.getJSONObject(0).getJSONObject("end_location").getString("lat"));
				rpd.setEnd_location_longitude(legs.getJSONObject(0).getJSONObject("end_location").getString("lng"));
				
				// Steps to reach the location
				JSONArray StepsArray = legs.getJSONObject(0).getJSONArray("steps");
				steps[] sp = new steps[StepsArray.length()];
				for (int i = 0; i < sp.length; i++) {
					sp[i] = new steps();
					sp[i].setDistance_text(StepsArray.getJSONObject(0)
							.getJSONObject("distance").getString("text"));
					sp[i].setDistance_value(StepsArray.getJSONObject(0)
							.getJSONObject("distance").getString("value"));
					sp[i].setDuration_text(StepsArray.getJSONObject(0)
							.getJSONObject("duration").getString("text"));
					sp[i].setDuration_value(StepsArray.getJSONObject(0)
							.getJSONObject("duration").getString("value"));
					sp[i].setStart_location_latitude(StepsArray.getJSONObject(0)
							.getJSONObject("start_location").getString("lat"));
					sp[i].setStart_location_longitude(StepsArray.getJSONObject(0)
							.getJSONObject("start_location").getString("lng"));
					sp[i].setEnd_location_latitude(StepsArray.getJSONObject(0)
							.getJSONObject("end_location").getString("lat"));
					sp[i].setEnd_location_longitude(StepsArray.getJSONObject(0)
							.getJSONObject("end_location").getString("lng"));
					sp[i].setHtml_instructions(StepsArray.getJSONObject(0)
							.getString("html_instructions"));
					sp[i].setTravel_mode(StepsArray.getJSONObject(0).getString(
							"travel_mode"));
					sp[i].setPolyline(StepsArray.getJSONObject(0)
							.getJSONObject("polyline").getString("points"));
					List<GeoPoint> lgp = Utils.decodePoly(sp[i].getPolyline());
					GeoPoint[] gp =  new GeoPoint[lgp.size()];
					int x = 0;
					for(GeoPoint g : lgp)
					{
						gp[x] = g;
						x++;
					}
					sp[i].setGeopoint(gp);
				}
				
				//Log.i(LOG_TAG, "Json" + Json.getString("copyrights"));
				
				rpd.setOverview_polyline(Json.getJSONObject("overview_polyline").getString("points"));
				
				List<GeoPoint> overview_polyline_geoPoints = Utils.decodePoly(rpd.getOverview_polyline());
				GeoPoint[] gp =  new GeoPoint[overview_polyline_geoPoints.size()];
				int x = 0;
				for(GeoPoint g : overview_polyline_geoPoints)
				{
					gp[x] = g;
					x++;
				}
				
				//Overview polyline in geopoints
				rpd.setOverview_polyline_GEOPOINTS(gp);
				
				// Summary
				rpd.setSummary(Json.getString("summary"));
				
			} catch (JSONException e) {
				e.printStackTrace();
				return "Failed";
			}
			
			return "Done Successfully";
		}

		@Override
		protected void onPostExecute(String result) {
			if(!inGetRoutePath)
				if(PaintForRoutePath == null)
					drawStraightLine(rpd.getOverview_polyline_GEOPOINTS());
				else
					drawStraightLine(PaintForRoutePath,rpd.getOverview_polyline_GEOPOINTS());
			else
				if(inGetRoutePath && drawforGetRoutePath)
					if(PaintForRoutePath == null)
						drawStraightLine(rpd.getOverview_polyline_GEOPOINTS());
					else
						drawStraightLine(PaintForRoutePath,rpd.getOverview_polyline_GEOPOINTS());
				
			if(mRoutePathLoader != null)
				mRoutePathLoader.RoutePathLoaded(rpd);
			super.onPostExecute(result);
		}
		
	}
}


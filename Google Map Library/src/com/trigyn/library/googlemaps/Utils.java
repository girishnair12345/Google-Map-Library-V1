package com.trigyn.library.googlemaps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.google.android.maps.GeoPoint;

public class Utils {

	static InputStream is = null;
	static JSONObject jObj = null;
	static String json = "";
	private static String LOG_TAG = "Google Maps";

	public static List<GeoPoint> decodePoly(String encoded) {

		List<GeoPoint> poly = new ArrayList<GeoPoint>();
		int index = 0, len = encoded.length();
		int lat = 0, lng = 0;

		while (index < len) {
			int b, shift = 0, result = 0;
			do {
				b = encoded.charAt(index++) - 63;
				result |= (b & 0x1f) << shift;
				shift += 5;
			} while (b >= 0x20);
			int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
			lat += dlat;

			shift = 0;
			result = 0;
			do {
				b = encoded.charAt(index++) - 63;
				result |= (b & 0x1f) << shift;
				shift += 5;
			} while (b >= 0x20);
			int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
			lng += dlng;

			GeoPoint p = new GeoPoint((int) (((double) lat / 1E5) * 1E6),
					(int) (((double) lng / 1E5) * 1E6));
			poly.add(p);
		}

		return poly;
	}

	public static JSONObject getJSONFromUrl(String url) {
		
		// Making HTTP request
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(url);
			HttpResponse httpResponse = httpClient.execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();
			is = httpEntity.getContent();
			// Log.i(LOG_TAG, "Connection Made Successfully");
		} catch (UnsupportedEncodingException e) {
			Log.e(LOG_TAG, "UnsupportedEncodingException - " + e.getMessage());
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			Log.e(LOG_TAG, "ClientProtocolException - " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			Log.e(LOG_TAG, "IOException - " + e.getMessage());
			e.printStackTrace();
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
		}

		// try parse the string to a JSON object
		try {
			jObj = new JSONObject(json);
		} catch (JSONException e) {
			Log.e(LOG_TAG, "Error parsing data " + e.toString());
		}

		// return JSON String
		return jObj;
	}

}

package com.trigyn.library.googlemaps;

import com.google.android.maps.GeoPoint;

/**
 * Sub-steps of this step. Specified for non-transit 
 * sections of transit routes.
 */
public class steps {
	
	
	private String distance_text;
	private String distance_value;
	private String duration_text;
	private String duration_value;
	private String start_location_latitude;
	private String start_location_longitude;
	private String end_location_latitude;
	private String end_location_longitude;
	private String html_instructions;
	private String polyline;
	private String travel_mode;
	private GeoPoint[] geopoint;
	
	



	/**
	 * The distance covered by this step. 
	 * A string representation of the distance value, using the 
	 * UnitSystem specified in the request.
	 */
	public String getDistance_text() {
		return distance_text;
	}

	/**
	 * The distance covered by this step. 
	 * A string representation of the distance value, using the 
	 * UnitSystem specified in the request.
	 */
	public void setDistance_text(String distance_text) {
		this.distance_text = distance_text;
	}

	/**
	 * The distance covered by this step. 
	 * The distance in meters.
	 */
	public String getDistance_value() {
		return distance_value;
	}

	/**
	 * The distance covered by this step. 
	 * The distance in meters.
	 */
	public void setDistance_value(String distance_value) {
		this.distance_value = distance_value;
	}
	
	/**
	 * The typical time required to perform this step.A string 
	 * representation of the duration value.
	 */
	public String getDuration_text() {
		return duration_text;
	}
	
	/**
	 * The typical time required to perform this step.A string 
	 * representation of the duration value.
	 */
	public void setDuration_text(String duration_text) {
		this.duration_text = duration_text;
	}
	
	/**
	 * The typical time required to perform this step.The duration 
	 * in seconds
	 */
	public String getDuration_value() {
		return duration_value;
	}
	
	/**
	 * The typical time required to perform this step.The duration 
	 * in seconds
	 */
	public void setDuration_value(String duration_value) {
		this.duration_value = duration_value;
	}

	/**
	 * The starting location of this step.
	 */
	public String getStart_location_latitude() {
		return start_location_latitude;
	}

	/**
	 * The starting location of this step.
	 */
	public void setStart_location_latitude(String start_location_latitude) {
		this.start_location_latitude = start_location_latitude;
	}
	
	/**
	 * The starting location of this step.
	 */
	public String getStart_location_longitude() {
		return start_location_longitude;
	}
	
	/**
	 * The starting location of this step.
	 */
	public void setStart_location_longitude(String start_location_longitude) {
		this.start_location_longitude = start_location_longitude;
	}
	
	/**
	 * The ending location of this step.
	 */
	public String getEnd_location_latitude() {
		return end_location_latitude;
	}
	
	/**
	 * The ending location of this step.
	 */
	public void setEnd_location_latitude(String end_location_latitude) {
		this.end_location_latitude = end_location_latitude;
	}
	
	/**
	 * The ending location of this step.
	 */
	public String getEnd_location_longitude() {
		return end_location_longitude;
	}
	
	/**
	 * The ending location of this step.
	 */
	public void setEnd_location_longitude(String end_location_longitude) {
		this.end_location_longitude = end_location_longitude;
	}
	
	
	/**
	 * Instructions for this steps in HTML
	 */
	public String getHtml_instructions() {
		return html_instructions;
	}
	
	
	/**
	 * Instructions for this steps in HTML
	 */
	public void setHtml_instructions(String html_instructions) {
		this.html_instructions = html_instructions;
	}
	
	/**
	 * The polyline details of step taken
	 */	
	public String getPolyline() {
		return polyline;
	}
	
	/**
	 * The polyline details of step taken
	 */	
	public void setPolyline(String polyline) {
		this.polyline = polyline;
	}
	
	/**
	 *The valid  travel_mode modes
	 *Eg: BICYCLING, DRIVING, TRANSIT & WALKING
	 */
	public String getTravel_mode() {
		return travel_mode;
	}
	
	/**
	 *The valid  travel_mode modes
	 *Eg: BICYCLING, DRIVING, TRANSIT & WALKING
	 */
	public void setTravel_mode(String travel_mode) {
		this.travel_mode = travel_mode;
	}

	/**
	 * The GeoPoint of this point
	 */	
	public GeoPoint[] getGeopoint() {
		return geopoint;
	}

	/**
	 * The GeoPoint of this point
	 */	
	public void setGeopoint(GeoPoint[] geopoint) {
		this.geopoint = geopoint;
	}
}

package com.trigyn.library.googlemaps;

import com.google.android.maps.GeoPoint;

public 	class RoutePathData {
	
	
	private String status;
	
	private String bounds_northeast_latitude;
	private String bounds_northeast_longitude;
	private String bounds_southwest_latitude;
	private String bounds_southwestt_longitude;
	
	private String copyrights;
	
	private String distance_text;
	private String distance_value;
	private String duration_text;
	private String duration_value;
	private String start_address;
	private String start_location_latitude;
	private String start_location_longitude;
	private String end_address;
	private String end_location_latitude;
	private String end_location_longitude;
	private steps[] steps;
	
	private String overview_polyline;
	private GeoPoint[] overview_polyline_GEOPOINTS;
	private String summary;
	
	/**
	 * The status returned by the Geocoder on the completion of a call to geocode().
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * The status returned by the Geocoder on the completion of a call to geocode().
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * Constructs a rectangle from the points at its south-west 
	 * and north-east corners.
	 * 
	 * Creates a LatLng object representing a geographic point.
	 * 
	 * Latitude is specified in degrees within the range [-90, 90].
	 * 
	 * Longitude is specified in degrees within the range [-180, 180].
	 */
	public String getBounds_northeast_latitude() {
		return bounds_northeast_latitude;
	}
	
	/**
	 * Constructs a rectangle from the points at its south-west 
	 * and north-east corners.
	 * 
	 * Creates a LatLng object representing a geographic point.
	 * 
	 * Latitude is specified in degrees within the range [-90, 90].
	 * 
	 * Longitude is specified in degrees within the range [-180, 180].
	 */
	public void setBounds_northeast_latitude(String bounds_northeast_latitude) {
		this.bounds_northeast_latitude = bounds_northeast_latitude;
	}
	
	/**
	 * Constructs a rectangle from the points at its south-west 
	 * and north-east corners.
	 * 
	 * Creates a LatLng object representing a geographic point.
	 * 
	 * Latitude is specified in degrees within the range [-90, 90].
	 * 
	 * Longitude is specified in degrees within the range [-180, 180].
	 */
	public String getBounds_northeast_longitude() {
		return bounds_northeast_longitude;
	}
	
	/**
	 * Constructs a rectangle from the points at its south-west 
	 * and north-east corners.
	 * 
	 * Creates a LatLng object representing a geographic point.
	 * 
	 * Latitude is specified in degrees within the range [-90, 90].
	 * 
	 * Longitude is specified in degrees within the range [-180, 180].
	 */
	public void setBounds_northeast_longitude(String bounds_northeast_longitude) {
		this.bounds_northeast_longitude = bounds_northeast_longitude;
	}
	
	/**
	 * Constructs a rectangle from the points at its south-west 
	 * and north-east corners.
	 * 
	 * Creates a LatLng object representing a geographic point.
	 * 
	 * Latitude is specified in degrees within the range [-90, 90].
	 * 
	 * Longitude is specified in degrees within the range [-180, 180].
	 */
	public String getBounds_southwest_latitude() {
		return bounds_southwest_latitude;
	}
	
	/**
	 * Constructs a rectangle from the points at its south-west 
	 * and north-east corners.
	 * 
	 * Creates a LatLng object representing a geographic point.
	 * 
	 * Latitude is specified in degrees within the range [-90, 90].
	 * 
	 * Longitude is specified in degrees within the range [-180, 180].
	 */
	public void setBounds_southwest_latitude(String bounds_southwest_latitude) {
		this.bounds_southwest_latitude = bounds_southwest_latitude;
	}
	
	/**
	 * Constructs a rectangle from the points at its south-west 
	 * and north-east corners.
	 * 
	 * Creates a LatLng object representing a geographic point.
	 * 
	 * Latitude is specified in degrees within the range [-90, 90].
	 * 
	 * Longitude is specified in degrees within the range [-180, 180].
	 */
	public String getBounds_southwestt_longitude() {
		return bounds_southwestt_longitude;
	}
	
	/**
	 * Constructs a rectangle from the points at its south-west 
	 * and north-east corners.
	 * 
	 * Creates a LatLng object representing a geographic point.
	 * 
	 * Latitude is specified in degrees within the range [-90, 90].
	 * 
	 * Longitude is specified in degrees within the range [-180, 180].
	 */
	public void setBounds_southwestt_longitude(String bounds_southwestt_longitude) {
		this.bounds_southwestt_longitude = bounds_southwestt_longitude;
	}
	
	/**
	 * Copyrights text to be displayed for this route.
	 */
	public String getCopyrights() {
		return copyrights;
	}
	
	/**
	 * Copyrights text to be displayed for this route.
	 */
	public void setCopyrights(String copyrights) {
		this.copyrights = copyrights;
	}
	
	/**
	 * The total distance covered by this leg. This property may 
	 * be undefined as the distance may be unknown.
	 * A string representation of the distance value, using 
	 * the UnitSystem specified in the request.
	 */
	public String getDistance_text() {
		return distance_text;
	}
	
	/**
	 * The total distance covered by this leg. This property may 
	 * be undefined as the distance may be unknown.
	 * A string representation of the distance value, using 
	 * the UnitSystem specified in the request.
	 */
	public void setDistance_text(String distance_text) {
		this.distance_text = distance_text;
	}
	
	/**
	 * The total distance covered by this leg. This property may 
	 * be undefined as the distance may be unknown.
	 * The distance in meters.
	 */
	public String getDistance_value() {
		return distance_value;
	}
	
	/**
	 * The total distance covered by this leg. This property may 
	 * be undefined as the distance may be unknown.
	 * The distance in meters.
	 */
	public void setDistance_value(String distance_value) {
		this.distance_value = distance_value;
	}
	
	/**
	 * The total duration of this leg.
	 * A string representation of the duration value.
	 */
	public String getDuration_text() {
		return duration_text;
	}
	
	/**
	 * The total duration of this leg.
	 * A string representation of the duration value.
	 */
	public void setDuration_text(String duration_text) {
		this.duration_text = duration_text;
	}
	
	/**
	 * The total duration of this leg.
	 * The duration in seconds.
	 */
	public String getDuration_value() {
		return duration_value;
	}
	
	/**
	 * The total duration of this leg.
	 * The duration in seconds.
	 */
	public void setDuration_value(String duration_value) {
		this.duration_value = duration_value;
	}
	
	/**
	 * The address of the origin of this leg.
	 */
	public String getStart_address() {
		return start_address;
	}
	
	/**
	 * The address of the origin of this leg.
	 */
	public void setStart_address(String start_address) {
		this.start_address = start_address;
	}
	
	/**
	 * The latitude of start location.
	 */
	public String getStart_location_latitude() {
		return start_location_latitude;
	}
	
	/**
	 * The latitude of start location.
	 */
	public void setStart_location_latitude(String start_location_latitude) {
		this.start_location_latitude = start_location_latitude;
	}
	
	/**
	 * The longitude of start location.
	 */
	public String getStart_location_longitude() {
		return start_location_longitude;
	}
	
	/**
	 * The longitude of start location.
	 */
	public void setStart_location_longitude(String start_location_longitude) {
		this.start_location_longitude = start_location_longitude;
	}
	
	/**
	 * The address of the destination of this leg.
	 */
	public String getEnd_address() {
		return end_address;
	}
	
	/**
	 * The address of the destination of this leg.
	 */
	public void setEnd_address(String end_address) {
		this.end_address = end_address;
	}
	
	/**
	 * The latitude of end location.
	 */
	public String getEnd_location_latitude() {
		return end_location_latitude;
	}
	
	/**
	 * The latitude of end location.
	 */
	public void setEnd_location_latitude(String end_location_latitude) {
		this.end_location_latitude = end_location_latitude;
	}
	
	/**
	 * The latitude of end longitude.
	 */
	public String getEnd_location_longitude() {
		return end_location_longitude;
	}
	
	/**
	 * The latitude of end longitude.
	 */
	public void setEnd_location_longitude(String end_location_longitude) {
		this.end_location_longitude = end_location_longitude;
	}
	
	/**
	 * An array of DirectionsSteps, each of which contains information 
	 * about the individual steps in this leg.
	 */
	public steps[] getSteps() {
		return steps;
	}
	
	/**
	 * An array of DirectionsSteps, each of which contains information 
	 * about the individual steps in this leg.
	 */
	public void setSteps(steps[] steps) {
		this.steps = steps;
	}
	
	/**
	 *The overview_polyline for the entire route path
	 */
	public String getOverview_polyline() {
		return overview_polyline;
	}
	
	/**
	 *The overview_polyline for the entire route path
	 */
	public void setOverview_polyline(String overview_polyline) {
		this.overview_polyline = overview_polyline;
	}
	
	/**
	 *The overview_polyline for the entire route path which 
	 *returns GeoPoint array
	 */
	public GeoPoint[] getOverview_polyline_GEOPOINTS() {
		return overview_polyline_GEOPOINTS;
	}
	
	/**
	 *The overview_polyline for the entire route path in
	 *GeoPoints
	 */
	public void setOverview_polyline_GEOPOINTS(
			GeoPoint[] overview_polyline_GEOPOINTS) {
		this.overview_polyline_GEOPOINTS = overview_polyline_GEOPOINTS;
	}
	
	/**
	 * The summary of the road taken
	 */
	public String getSummary() {
		return summary;
	}
	
	/**
	 * The summary of the road taken
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}

}


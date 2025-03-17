package com.example.appv6;

import android.os.AsyncTask;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class GetNearbyPlaces extends AsyncTask<Object, String, String> {
    String googlePlacesData;
    GoogleMap mMap;
    String url;

    @Override
    protected String doInBackground(Object... objects) {
        mMap = (GoogleMap) objects[0];
        url = (String) objects[1];

        DownloadUrl downloadUrl = new DownloadUrl();
        try {
            googlePlacesData = downloadUrl.readUrl(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return googlePlacesData;
    }

    @Override
    protected void onPostExecute(String s) {
        List<HashMap<String, String>> nearbyPlacesList = null;
        DataParser parser = new DataParser();
        nearbyPlacesList = parser.parse(s);

        // Check if the list is not null or empty
        if (nearbyPlacesList != null && !nearbyPlacesList.isEmpty()) {
            showNearbyPlaces(nearbyPlacesList); // Call this to display markers
        } else {
            // Handle the case where no places are found
            // For example, show a Toast message
        }
    }

    private void showNearbyPlaces(List<HashMap<String, String>> nearbyPlaceList) {
        // Move the camera to the first location
        if (!nearbyPlaceList.isEmpty()) {
            HashMap<String, String> firstPlace = nearbyPlaceList.get(0);
            double lat = Double.parseDouble(firstPlace.get("lat"));
            double lng = Double.parseDouble(firstPlace.get("lng"));
            LatLng latLng = new LatLng(lat, lng);

            // Move the camera to the first location and zoom in
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(12)); // Adjust zoom level as needed
        }

        // Add markers for all nearby places
        for (int i = 0; i < nearbyPlaceList.size(); i++) {
            MarkerOptions markerOptions = new MarkerOptions();
            HashMap<String, String> googlePlace = nearbyPlaceList.get(i);

            String placeName = googlePlace.get("place_name");
            String vicinity = googlePlace.get("vicinity");
            double lat = Double.parseDouble(googlePlace.get("lat"));
            double lng = Double.parseDouble(googlePlace.get("lng"));

            LatLng latLng = new LatLng(lat, lng);
            markerOptions.position(latLng);
            markerOptions.title(placeName + " , " + vicinity);
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET));

            mMap.addMarker(markerOptions);
        }
    }
}
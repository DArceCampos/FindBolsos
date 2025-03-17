package com.example.appv6;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataParser {

    // Extracts a single place from a JSONObject
    private HashMap<String, String> getPlace(JSONObject googlePlaceJson) {
        HashMap<String, String> googlePlaceMap = new HashMap<>();
        String placeName = ""; // Use empty string as default
        String vicinity = "";  // Use empty string as default
        String latitude = "";
        String longitude = "";

        try {
            // Extract place name
            if (!googlePlaceJson.isNull("name")) {
                placeName = googlePlaceJson.getString("name");
            }

            // Extract vicinity
            if (!googlePlaceJson.isNull("vicinity")) {
                vicinity = googlePlaceJson.getString("vicinity");
            }

            // Extract latitude and longitude
            JSONObject geometry = googlePlaceJson.getJSONObject("geometry");
            JSONObject location = geometry.getJSONObject("location");
            latitude = location.getString("lat");
            longitude = location.getString("lng");

            // Add data to the map
            googlePlaceMap.put("place_name", placeName);
            googlePlaceMap.put("vicinity", vicinity);
            googlePlaceMap.put("lat", latitude);
            googlePlaceMap.put("lng", longitude);

        } catch (JSONException e) {
            e.printStackTrace(); // Log the error instead of crashing
        }

        return googlePlaceMap;
    }

    // Extracts a list of places from a JSONArray
    private List<HashMap<String, String>> getPlaces(JSONArray jsonArray) {
        List<HashMap<String, String>> placesList = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject placeJson = jsonArray.getJSONObject(i);
                HashMap<String, String> placeMap = getPlace(placeJson);
                placesList.add(placeMap);
            } catch (JSONException e) {
                e.printStackTrace(); // Log the error instead of crashing
            }
        }

        return placesList;
    }

    // Parses the JSON response from the Google Places API
    public List<HashMap<String, String>> parse(String jsonData) {
        JSONArray jsonArray = null;

        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            jsonArray = jsonObject.getJSONArray("results");
        } catch (JSONException e) {
            e.printStackTrace(); // Log the error instead of crashing
        }

        // Return an empty list if parsing fails
        if (jsonArray == null) {
            return new ArrayList<>();
        }

        return getPlaces(jsonArray);
    }
}
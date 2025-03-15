package com.example.appv6;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class WazeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Search for "backpack shops near me"
        String searchQuery = "backpack shops near me";
        redirectToWaze(searchQuery);
    }

    private void redirectToWaze(String query) {
        try {
            // Create a URI for the search query
            String url = "https://waze.com/ul?q=" + Uri.encode(query);
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        } catch (ActivityNotFoundException ex) {
            // If Waze is not installed, open the Google Play Store to install Waze
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.waze"));
            startActivity(intent);
        }
    }
}
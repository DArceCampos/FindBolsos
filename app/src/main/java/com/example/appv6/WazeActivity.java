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

        double latitude = 9.8562;
        double longitude = -83.9109;

        String searchQuery = "tienda de bolsos";
        redirectToWaze(searchQuery, latitude, longitude, 10000);
    }

    private void redirectToWaze(String query, double latitude, double longitude, int radius) {
        try {
            String url = "https://waze.com/ul?q=" + Uri.encode(query) +
                    "&ll=" + latitude + "," + longitude +
                    "&radius=" + radius;
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        } catch (ActivityNotFoundException ex) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.waze"));
            startActivity(intent);
        }
    }
}
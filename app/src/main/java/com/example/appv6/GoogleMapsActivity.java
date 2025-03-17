package com.example.appv6;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class GoogleMapsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String searchQuery = "bolsos";
        redirectToMaps(searchQuery);
    }

    private void redirectToMaps(String query) {
        try {
            int radius = 10000;

            String url = "https://www.google.com/maps/search/" + Uri.encode(query) + "/@?radius=" + radius;
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        } catch (ActivityNotFoundException ex) {
            int radius = 10000;
            String url = "https://www.google.com/maps/search/?api=1&query=" + Uri.encode(query) + "&radius=" + radius;
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        }
    }
}
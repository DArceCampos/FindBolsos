package com.example.appv6;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class WazeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Coordinates for the store (example)
        String lat = "9.934739";  // Latitude
        String lon = "-84.087502"; // Longitude

        // Waze URI
        String uri = "https://waze.com/ul?ll=" + lat + "," + lon + "&navigate=yes";

        // Create the intent
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        intent.setPackage("com.waze"); // Ensure Waze is used

        // Check if Waze is installed
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            // If Waze is not installed, show a message
            Toast.makeText(this, "Waze is not installed", Toast.LENGTH_SHORT).show();
        }

        // Finish activity so it doesn't stay open
        finish();
    }
}

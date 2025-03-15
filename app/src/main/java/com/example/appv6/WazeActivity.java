package com.example.appv6;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;
import android.content.pm.ResolveInfo;

public class WazeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Coordinates for the store (example)
        String lat = "9.934739";  // Latitude
        String lon = "-84.087502"; // Longitude

        // Waze URI (using waze:// scheme)
        String uri = "waze://?ll=" + lat + "," + lon + "&navigate=yes";

        // Create the intent
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        intent.setPackage("com.waze"); // Ensure Waze is used

        // Debug: Log available activities
        List<ResolveInfo> activities = getPackageManager().queryIntentActivities(intent, 0);
        for (ResolveInfo info : activities) {
            Log.d("WazeActivity", "Available activity: " + info.activityInfo.packageName);
        }

        // Check if Waze is installed
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            // If Waze is not installed, show a message
            Toast.makeText(this, "Waze is not installed", Toast.LENGTH_SHORT).show();
        }
    }
}
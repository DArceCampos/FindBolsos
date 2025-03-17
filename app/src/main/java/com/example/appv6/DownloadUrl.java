package com.example.appv6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadUrl {

    public String readUrl(String myUrl) throws IOException {
        String data = "";
        InputStream inputStream = null;
        HttpURLConnection urlConnection = null;

        try {
            URL url = new URL(myUrl);
            urlConnection = (HttpURLConnection) url.openConnection();

            // Set timeouts to avoid hanging indefinitely
            urlConnection.setConnectTimeout(15000); // 15 seconds
            urlConnection.setReadTimeout(15000);    // 15 seconds

            // Connect to the server
            urlConnection.connect();

            // Check if the response code is OK (200)
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                inputStream = urlConnection.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder sb = new StringBuilder();

                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }

                data = sb.toString();
                br.close();
            } else {
                // Handle HTTP errors (e.g., 404, 500)
                throw new IOException("HTTP error code: " + urlConnection.getResponseCode());
            }

        } finally {
            // Close resources safely
            if (inputStream != null) {
                inputStream.close();
            }
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        return data;
    }
}
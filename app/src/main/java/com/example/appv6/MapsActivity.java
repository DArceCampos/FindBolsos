package com.example.appv6;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.appv6.databinding.ActivityMapsBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.PlaceLikelihood;
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest;
import com.google.android.libraries.places.api.net.FindCurrentPlaceResponse;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;

import java.util.Arrays;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private final int FINE_PERMISSION_CODE=1;
    private GoogleMap mMap;
    Location currentLocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    private ActivityMapsBinding binding;
    private PlacesClient placesClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        getLastLocation();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        Places.initialize(getApplicationContext(), "AIzaSyBia8i4Pnz0snGSEgWE9gn4B4u9BMc590Q");
        placesClient = Places.createClient(this);

    }
    private void getLastLocation() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, FINE_PERMISSION_CODE);
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    currentLocation = location;

                    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                            .findFragmentById(R.id.map);
                    mapFragment.getMapAsync(MapsActivity.this);
                }
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng currentLatLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
        mMap.addMarker(new MarkerOptions().position(currentLatLng).title("Ubicación Actual"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 12));

        // Buscar tiendas de bolsos cercanas
        findNearbyBagsStores(currentLatLng);
    }

    private void findNearbyBagsStores(LatLng location) {
        // Definir los tipos de lugares que queremos buscar (en este caso, tiendas)
        List<Place.Field> placeFields = Arrays.asList(Place.Field.NAME, Place.Field.LAT_LNG);

        // Crear una solicitud para buscar lugares cercanos
        FindCurrentPlaceRequest request = FindCurrentPlaceRequest.builder(placeFields).build();

        // Verificar permisos de ubicación
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, FINE_PERMISSION_CODE);
            return;
        }

        // Realizar la solicitud
        Task<FindCurrentPlaceResponse> placeResponse = placesClient.findCurrentPlace(request);
        placeResponse.addOnSuccessListener(new OnSuccessListener<FindCurrentPlaceResponse>() {
            @Override
            public void onSuccess(FindCurrentPlaceResponse response) {
                for (PlaceLikelihood placeLikelihood : response.getPlaceLikelihoods()) {
                    Place place = placeLikelihood.getPlace();
                    if (place.getName() != null && (place.getName().toLowerCase().contains("bolsos")||place.getName().toLowerCase().contains("store")||place.getName().toLowerCase().contains("tienda")||place.getName().toLowerCase().contains("mochila"))) {
                        LatLng placeLatLng = place.getLatLng();
                        mMap.addMarker(new MarkerOptions().position(placeLatLng).title(place.getName()));
                    }
                }
            }
        }).addOnFailureListener(e -> {
            Toast.makeText(MapsActivity.this, "Error al buscar tiendas cercanas: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == FINE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation();
            } else {
                Toast.makeText(this, "El acceso a su ubicación ha sido denegado", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
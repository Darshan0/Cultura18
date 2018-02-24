package in.co.cultura.cultura18.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Locale;

import in.co.cultura.cultura18.R;

public class LocationActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    public static final CameraPosition CMRIT =
            new CameraPosition.Builder().target(new LatLng(12.9661011, 77.712195)).zoom(18.0f).bearing(0.0f).tilt(25.0f).build();
    public static final CameraPosition CURRENT =
            new CameraPosition.Builder().target(new LatLng(12.957048d, 77.598928d))
                    .zoom(12.0f)
                    .bearing(0.0f)
                    .tilt(25.0f)
                    .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.SplashTheme);
        if (Build.VERSION.SDK_INT >= 21) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        setContentView(R.layout.activity_location);
        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.location_map);
        supportMapFragment.getMapAsync(this);
        ImageView takeMeThere = findViewById(R.id.location_button);
        takeMeThere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(String.format(Locale.ENGLISH, "http://maps.google.com/maps?daddr=%f,%f (%s)", new Object[]{Double.valueOf(12.9661011d), Double.valueOf(77.712195d), "Cultura 2K18"})));
                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                startActivity(intent);
            }
        });
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(1);
        mMap.getUiSettings().setMapToolbarEnabled(false);
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(CURRENT));
        mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                mMap.moveCamera(CameraUpdateFactory.newCameraPosition(CURRENT));
                mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
                    @Override
                    public void onMapLoaded() {
                        goToCmrit();
                    }
                });
            }
        });
        // We will provide our own zoom controls.
        mMap.getUiSettings().setZoomControlsEnabled(false);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.addMarker(new MarkerOptions().position(CMRIT.target).visible(true));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(12.957048d, 77.598928d), 10));
    }

    public void goToCmrit() {
        if (this.mMap != null) {
            this.mMap.animateCamera(CameraUpdateFactory.newCameraPosition(CMRIT), 3000, null);
        }
    }
}

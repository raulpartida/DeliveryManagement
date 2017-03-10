package com.example.rulop_000.deliverym.Vista;

import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;
import com.directions.route.Route;
import com.directions.route.RouteException;
import com.directions.route.Routing;
import com.directions.route.RoutingListener;
import com.example.rulop_000.deliverym.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, RoutingListener {

    private GoogleMap mMap;
    private ProgressDialog progressDialog;
    private List<Polyline> polylines;
    LatLng start ;//new LatLng(18.015365, -77.499382);
    LatLng waypoint;//new LatLng(18.01455, -77.499333);
    LatLng waypoint2;
    LatLng end ;// LatLng(18.012590, -77.500659);
    private static final int[] COLORS = new int[]{R.color.ruta1,R.color.ruta2,R.color.ruta3,R.color.ruta4,R.color.ruta5,R.color.ruta6,R.color.ruta7,R.color.ruta8,R.color.ruta9,R.color.ruta10};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_menu);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        polylines = new ArrayList<>();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
   /* @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng ceti = new LatLng(20.7022041, -103.389256);
        LatLng andares = new LatLng(20.709899, -103.412772);
        LatLng galerias = new LatLng(20.677261, -103.430849);
        LatLng colomos  = new LatLng(20.708045, -103.393376);
        mMap.addMarker(new MarkerOptions().position(ceti).title("Marker in CETI"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ceti));
        mMap.addMarker(new MarkerOptions().position(andares).title("Andares"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(andares));
        mMap.addMarker(new MarkerOptions().position(galerias).title("Galerias"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(galerias));
        mMap.addMarker(new MarkerOptions().position(colomos).title("Colomos"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(colomos));
    }*/
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng ceti = new LatLng(20.7022041, -103.389256);
        LatLng andares = new LatLng(20.709899, -103.412772);
        LatLng galerias = new LatLng(20.677261, -103.430849);
        LatLng uag  = new LatLng(20.698613,-103.416117);

        start = ceti;//new LatLng(18.015365, -77.499382);
        waypoint= andares;//new LatLng(18.01455, -77.499333);
        waypoint2=uag;
        end = galerias;// LatLng(18.012590, -77.500659);

        Routing routing = new Routing.Builder()
                .travelMode(Routing.TravelMode.DRIVING)
                .withListener(this)
                .waypoints(start, andares, uag, end)
                .optimize(true)
                .build();

        routing.execute();

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(start)
                .zoom(12).build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

    }

    @Override
    public void onRoutingFailure(RouteException e) {

    }

    @Override
    public void onRoutingStart() {

    }

    @Override
    public void onRoutingSuccess(ArrayList<Route> route, int i) {

        if(polylines.size()>0) {
            for (Polyline poly : polylines) {
                poly.remove();
            }
        }

        polylines = new ArrayList<>();
        PolylineOptions polyOptions = new PolylineOptions();

        polyOptions.width(5);
        polyOptions.addAll(route.get(0).getPoints());
        Polyline polyline = mMap.addPolyline(polyOptions);
        polyline.setColor(R.color.ruta4);
        polylines.add(polyline);

        Toast.makeText(getApplicationContext(),"Tiempo estimado de la ruta:" + route.get(0).getDurationText(),Toast.LENGTH_SHORT).show();

        // Start marker
        MarkerOptions options = new MarkerOptions();
        options.position(start);
        options.icon(BitmapDescriptorFactory.fromResource(R.drawable.start_blue));
        mMap.addMarker(options.position(start).title("CETI Colomos"));


        options = new MarkerOptions();
        options.position(waypoint);
        mMap.addMarker(options.position(waypoint).title("Andares"));


        options = new MarkerOptions();
        options.position(waypoint2);
        mMap.addMarker(options.position(waypoint2).title("UAG"));


        // End marker
        options = new MarkerOptions();
        options.position(end);
        options.icon(BitmapDescriptorFactory.fromResource(R.drawable.end_green));
        mMap.addMarker(options.position(end).title("Galerias"));
    }

    @Override
    public void onRoutingCancelled() {

    }
}

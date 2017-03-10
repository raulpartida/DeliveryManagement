package com.example.rulop_000.deliverym.Vista;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.rulop_000.deliverym.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //----------------------------MAPA----------------------------------------------
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       // if (id == R.id.action_settings) {
       //     return true;
       // }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
       /* if (id == R.id.nav_ruta) {
            Intent intent = new Intent(MenuActivity.this,  MapsActivity.class);
            startActivity(intent);
        }*/
        if (id == R.id.nav_direcciones) {
            Intent intent = new Intent(MenuActivity.this, DireccionesActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_registro_entregas) {
            Intent intent = new Intent(MenuActivity.this, EntregasActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_perfil) {
            Intent intent = new Intent(MenuActivity.this, PerfilActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_cliente) {
            Intent intent = new Intent(MenuActivity.this, AddClienteActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_configuracion) {
            Intent intent = new Intent(MenuActivity.this, CambioPalabraActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_sesion) {SharedPreferences preferences;
            preferences = getSharedPreferences("user",MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear().commit();
            Intent intent = new Intent(MenuActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng ceti = new LatLng(20.7022041, -103.389256);
        LatLng andares = new LatLng(20.709899, -103.412772);
        LatLng galerias = new LatLng(20.677261, -103.430849);
        LatLng colomos = new LatLng(20.708045, -103.393376);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);//habilita la localizacion del celular
        mMap.moveCamera(CameraUpdateFactory.zoomTo(12));// con esto se inicializa el Zoom que tendra el mapa
        mMap.addMarker(new MarkerOptions().position(ceti).title("Marker in CETI"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ceti));
        mMap.addMarker(new MarkerOptions().position(andares).title("Andares"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(andares));
        mMap.addMarker(new MarkerOptions().position(galerias).title("Galerias"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(galerias));
        mMap.addMarker(new MarkerOptions().position(colomos).title("Colomos"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(colomos));


    }
}

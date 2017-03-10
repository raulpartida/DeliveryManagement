package com.example.rulop_000.deliverym.Vista;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rulop_000.deliverym.Controlador.LoginWebService;
import com.example.rulop_000.deliverym.Entidad.LoginRepartidor;
import com.example.rulop_000.deliverym.Entidad.PerfilRepartidor;
import com.example.rulop_000.deliverym.R;

public class PerfilActivity extends AppCompatActivity {

     private TextView tvId,tvNombre, tvRuta,tvCarro;
    private PerfilRepartidor perfilRepartidor;
    private LoginRepartidor logrep;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        tvId=(TextView)findViewById(R.id.tvIdChofer);
        tvNombre=(TextView)findViewById(R.id.tvChofer);
        tvRuta=(TextView)findViewById(R.id.tvIDRuta);
        tvCarro=(TextView)findViewById(R.id.tvCamion);

        SharedPreferences preferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        tvId.setText(String.valueOf(preferences.getInt("id",0)));
        tvNombre.setText(preferences.getString("repartidor","ERROR"));
        tvRuta.setText(String.valueOf(preferences.getInt("id_ruta",0)));
        tvCarro.setText(preferences.getString("id_vehiculo","ERROR"));
    }

}

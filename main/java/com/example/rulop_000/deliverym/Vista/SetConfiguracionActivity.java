package com.example.rulop_000.deliverym.Vista;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.example.rulop_000.deliverym.Entidad.OlvidoContrasena;
import com.example.rulop_000.deliverym.R;

public class SetConfiguracionActivity extends AppCompatActivity  {
    private EditText etNuevaP, etNuevaP2,etContra,etId;
    private Button btnAceptarCambio;
    private OlvidoContrasena olvidoContrasena = new OlvidoContrasena();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    }


}

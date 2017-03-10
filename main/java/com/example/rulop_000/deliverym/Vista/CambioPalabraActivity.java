package com.example.rulop_000.deliverym.Vista;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rulop_000.deliverym.Controlador.CambiarPalabraWebService;
import com.example.rulop_000.deliverym.Entidad.OlvidoContrasena;
import com.example.rulop_000.deliverym.R;

public class CambioPalabraActivity extends AppCompatActivity implements  View.OnClickListener{

    private EditText etContra,etPalabra1,etPalabra2;
    private Button btnCambiarPalabra,btnContra,btnAcerca;
    private OlvidoContrasena olvidoContrasena = new OlvidoContrasena();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambio_palabra);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        etContra=(EditText)findViewById(R.id.etContrasena);
        etPalabra1=(EditText)findViewById(R.id.etNewWord1);
        etPalabra2=(EditText)findViewById(R.id.etNewWord2);
        btnCambiarPalabra=(Button)findViewById(R.id.btnAceptarCambioPalabra);
        btnContra=(Button)findViewById(R.id.btnContraseña);
        btnAcerca=(Button)findViewById(R.id.btnAcerca);

        btnCambiarPalabra.setOnClickListener(this);
        btnContra.setOnClickListener(this);
        btnAcerca.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAceptarCambioPalabra:
                if (!etContra.getText().toString().isEmpty() && !etPalabra1.getText().toString().isEmpty() && !etPalabra2.getText().toString().isEmpty()) {
                    if (etPalabra2.getText().toString().equals(etPalabra1.getText().toString())) {
                        if (etPalabra1.getText().length() <= 15 && etPalabra1.getText().length() >= 4) {
                            SharedPreferences preferences = getSharedPreferences("user", Context.MODE_PRIVATE);
                            olvidoContrasena.setId(Integer.valueOf(preferences.getInt("id", 0)));
                            olvidoContrasena.setPassword(etContra.getText().toString());
                            olvidoContrasena.setPalabra(etPalabra1.getText().toString());
                            CambiarPalabra cambiarPalabra = new CambiarPalabra();
                            cambiarPalabra.execute();
                        } else {
                            Toast.makeText(CambioPalabraActivity.this, "El mínimo de la palabra clave es de 4 caracteres", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(CambioPalabraActivity.this, "Las palabras no coinciden", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(CambioPalabraActivity.this, "Faltan llenar campos", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btnContraseña:
                Intent intent= new Intent(CambioPalabraActivity.this,ConfiguracionActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.btnAcerca:
                Intent intent2= new Intent(CambioPalabraActivity.this,SetConfiguracionActivity.class);
                startActivity(intent2);
                break;
        }
    }

    private  class CambiarPalabra extends AsyncTask <Void,Object,OlvidoContrasena>{
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(CambioPalabraActivity.this);
            progressDialog.setMessage("Espera");
            progressDialog.setCancelable(false);
            progressDialog.show();
            super.onPreExecute();
        }

        @Override
        protected OlvidoContrasena doInBackground(Void... params) {
            CambiarPalabraWebService personaWebService = new CambiarPalabraWebService();
            OlvidoContrasena empleado = personaWebService.setPalabra(olvidoContrasena);
            return empleado;
        }
        @Override
        protected void onProgressUpdate(Object... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(OlvidoContrasena olvidoContrasena) {
            super.onPostExecute(olvidoContrasena);

            if (progressDialog.isShowing()){
                progressDialog.dismiss();
            }
                Toast.makeText(CambioPalabraActivity.this,"Palabra clave cambiada",Toast.LENGTH_SHORT).show();
                finish();
        }
    }
}

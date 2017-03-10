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

import com.example.rulop_000.deliverym.Controlador.OlvidoContrasenaWebService;
import com.example.rulop_000.deliverym.Entidad.OlvidoContrasena;
import com.example.rulop_000.deliverym.R;

public class ConfiguracionActivity extends AppCompatActivity implements  View.OnClickListener{

    private EditText etNuevaContraseña1, etNuevaContraseña2,etPalabra;
    private Button btnAceptarCambio,btnAcerca;
    private OlvidoContrasena olvidoContrasena = new OlvidoContrasena();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        etPalabra=(EditText)findViewById(R.id.etPalabra);
        etNuevaContraseña1 = (EditText) findViewById(R.id.etNewPassword1);
        etNuevaContraseña2 = (EditText) findViewById(R.id.etNewPassword2);
        btnAceptarCambio = (Button) findViewById(R.id.btnAceptarCambioContraseña);
        btnAcerca=(Button) findViewById(R.id.btnAcerca);

        btnAceptarCambio.setOnClickListener( this );
        btnAcerca.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(ConfiguracionActivity.this,SetConfiguracionActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(!etPalabra.getText().toString().isEmpty()
                && !etNuevaContraseña1.getText().toString().isEmpty()
                && !etNuevaContraseña2.getText().toString().isEmpty()){
            if (etNuevaContraseña1.getText().length()==10) {
                if (etNuevaContraseña1.getText().toString().equals(etNuevaContraseña2.getText().toString())){
                    SharedPreferences preferences = getSharedPreferences("user", Context.MODE_PRIVATE);
                    olvidoContrasena.setId(Integer.valueOf(preferences.getInt("id",0)));
                    olvidoContrasena.setPalabra(etPalabra.getText().toString());
                    olvidoContrasena.setPassword(etNuevaContraseña1.getText().toString());
                    CambiarPassword cambiarPassword= new CambiarPassword();
                    cambiarPassword.execute();
                }
                else {
                    Toast.makeText(ConfiguracionActivity.this,"Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Toast.makeText(ConfiguracionActivity.this,"El tamaño de la contraseña tiene que ser de 10 caracteres", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(ConfiguracionActivity.this,"Falta llenar campos", Toast.LENGTH_SHORT).show();
        }
    }

    private class CambiarPassword extends AsyncTask<Void,Object,OlvidoContrasena>{
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(ConfiguracionActivity.this);
            progressDialog.setMessage("Espera");
            progressDialog.setCancelable(false);
            progressDialog.show();
            super.onPreExecute();
        }

        @Override
        protected OlvidoContrasena doInBackground(Void... params) {
            OlvidoContrasenaWebService personaWebService = new OlvidoContrasenaWebService();
            OlvidoContrasena empleado= personaWebService.setContra(olvidoContrasena);
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
                Toast.makeText(ConfiguracionActivity.this,"Contraseña cambiada",Toast.LENGTH_SHORT).show();
                finish();
        }
    }
}

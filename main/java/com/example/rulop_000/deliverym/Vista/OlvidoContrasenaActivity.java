package com.example.rulop_000.deliverym.Vista;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rulop_000.deliverym.Controlador.OlvidoContrasenaWebService;
import com.example.rulop_000.deliverym.Entidad.OlvidoContrasena;
import com.example.rulop_000.deliverym.R;

public class OlvidoContrasenaActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText etNuevaContraseña1, etNuevaContraseña2,etPalabra,etId;
    private Button btnAceptarCambio;
    private OlvidoContrasena olvidoContrasena = new OlvidoContrasena();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_olvido_contrasena);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        etId=(EditText)findViewById(R.id.etContrasena);
        etPalabra=(EditText)findViewById(R.id.etPalabra);
        etNuevaContraseña1 = (EditText) findViewById(R.id.etNewPassword1);
        etNuevaContraseña2 = (EditText) findViewById(R.id.etNewPassword2);
        btnAceptarCambio = (Button) findViewById(R.id.btnAceptarCambioContraseña);
        btnAceptarCambio.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(!etId.getText().toString().isEmpty()&&!etPalabra.getText().toString().isEmpty()
                && !etNuevaContraseña1.getText().toString().isEmpty()
                && !etNuevaContraseña2.getText().toString().isEmpty()){
            if (etNuevaContraseña1.getText().length()==10) {
                if (etNuevaContraseña1.getText().toString().equals(etNuevaContraseña2.getText().toString())){
                    olvidoContrasena.setId(Integer.valueOf(etId.getText().toString()));
                    olvidoContrasena.setPalabra(etPalabra.getText().toString());
                    olvidoContrasena.setPassword(etNuevaContraseña1.getText().toString());
                    CambiarPassword cambiarPassword= new CambiarPassword();
                    cambiarPassword.execute();
                }
                else {
                    Toast.makeText(OlvidoContrasenaActivity.this,"Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Toast.makeText(OlvidoContrasenaActivity.this,"El tamaño de la contraseña tiene que ser de 10 caracteres", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(OlvidoContrasenaActivity.this,"Falta llenar campos", Toast.LENGTH_SHORT).show();
        }
    }
    private class CambiarPassword extends AsyncTask<Void,Object,OlvidoContrasena> {
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(OlvidoContrasenaActivity.this);
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
            Toast.makeText(OlvidoContrasenaActivity.this,"Contraseña cambiada",Toast.LENGTH_SHORT).show();
            Intent i = new Intent(OlvidoContrasenaActivity.this, LoginActivity.class);
            startActivity(i);
            finish();
        }
    }
}

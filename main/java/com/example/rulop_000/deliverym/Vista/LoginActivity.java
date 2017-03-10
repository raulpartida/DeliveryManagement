package com.example.rulop_000.deliverym.Vista;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rulop_000.deliverym.Controlador.LoginWebService;
import com.example.rulop_000.deliverym.Entidad.LoginRepartidor;
import com.example.rulop_000.deliverym.Entidad.OlvidoContrasena;
import com.example.rulop_000.deliverym.Entidad.PerfilRepartidor;
import com.example.rulop_000.deliverym.R;

public class LoginActivity extends AppCompatActivity {
    private EditText etU,etPassword;
    private Button btnSesion,btnRecuperarC;
    private LoginRepartidor logRep;
    private SharedPreferences preferences;
    private int empleado = 0;
    private String contra = "";
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION = 1 ;
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

//        ActivityCompat.requestPermissions(LoginActivity.this,
//                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
//                MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION);
//        ActivityCompat.requestPermissions(LoginActivity.this,
//               new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
//                MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);

        logRep = new LoginRepartidor(0,"null");
        etU=(EditText)findViewById(R.id.etUsuario);
        etPassword=(EditText)findViewById(R.id.etPassword);
        btnSesion=(Button)findViewById(R.id.btnLogin);
        btnRecuperarC=(Button) findViewById(R.id.btnRecuperar);
        btnSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logRep= new LoginRepartidor();
                if(etU.getText().toString().isEmpty() || etPassword.getText().toString().isEmpty()){
                    Toast.makeText(getBaseContext(), "Ingresa todos los campos", Toast.LENGTH_LONG).show();
                }
                else {
                    logRep.setId(Integer.valueOf(etU.getText().toString()));
                    logRep.setContrasena(etPassword.getText().toString());
                    Login login = new Login();
                    login.execute();

                    etU.setText("");
                    etPassword.setText("");
                    etU.setHint("Usuario");
                    etPassword.setHint("Contraseña");
                }
            }
        });
        btnRecuperarC.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, OlvidoContrasenaActivity.class);
                startActivity(intent);
            }
        } );
        preferences = getSharedPreferences("user", MODE_PRIVATE);
        logRep.setId(preferences.getInt("id", 0));
        logRep.setContrasena(preferences.getString("password", "null"));


        if (logRep.getId() != 0 && !logRep.getContrasena().equals("null")) {
            Login login = new Login();
            login.execute();
        }
    }
    //-------------------------Clase LOGIN---------------------------------

    private class Login extends AsyncTask<Void, Object, PerfilRepartidor> {

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(LoginActivity.this);
            progressDialog.setMessage("Espere un momento...");
            progressDialog.setCancelable(false);
            progressDialog.show();

        }

        @Override
        protected PerfilRepartidor doInBackground(Void... params) {

           LoginWebService logweb= new LoginWebService();
           PerfilRepartidor logrep1 = logweb.getRepartidor(logRep);
           return logrep1;
        }

        @Override
        protected void onPostExecute(PerfilRepartidor logRep) {
            super.onPostExecute(logRep);
            if (progressDialog.isShowing()){
                progressDialog.dismiss();
            }
            //Toast.makeText(getBaseContext(),String.valueOf(logRep.getId_empleado()), Toast.LENGTH_LONG).show();
            if (logRep.getId_empleado()!=0) {

                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("id", logRep.getId_empleado());
                editor.putString("repartidor", logRep.getNombre()+" "+logRep.getAmaterno()+" "+logRep.getApaterno());
                editor.putInt("id_ruta", logRep.getId_ruta());
                editor.putString("id_vehiculo", logRep.getId_vehiculo());
                editor.commit();

                Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(getBaseContext(), "Usuario incorrecto", Toast.LENGTH_LONG).show();
            }
        }

    }

}

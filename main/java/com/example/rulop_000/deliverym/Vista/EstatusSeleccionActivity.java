package com.example.rulop_000.deliverym.Vista;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.rulop_000.deliverym.Controlador.GuardarEstatusWebService;
import com.example.rulop_000.deliverym.Entidad.GuardarEstatus;
import com.example.rulop_000.deliverym.R;

public class EstatusSeleccionActivity extends AppCompatActivity {

    private ListView lvMercancia;
    private GuardarEstatus guardarEstatus;
    private RadioButton rbC,rbI,rbM,rbD;
    private Button btnFin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estatus_seleccion);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Bundle bundle = getIntent().getExtras();
        guardarEstatus = new GuardarEstatus("",0);
        guardarEstatus.setId_pedido(Integer.valueOf(bundle.getString("id_pedido")));

        rbC=(RadioButton)findViewById(R.id.rb1);
        rbI=(RadioButton)findViewById(R.id.rb2);
        rbM=(RadioButton)findViewById(R.id.rb3);
        rbD=(RadioButton)findViewById(R.id.rb4);
        btnFin = (Button)findViewById(R.id.btnFinalizar);

        btnFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rbC.isChecked()==true){
                    guardarEstatus.setEstatus("Completado");
                    ObtenerEstado obtenerEstado=new ObtenerEstado();
                    obtenerEstado.execute();
                }else if(rbI.isChecked()==true){
                    guardarEstatus.setEstatus("Incompleto");
                    ObtenerEstado obtenerEstado=new ObtenerEstado();
                    obtenerEstado.execute();
                }else if(rbM.isChecked()==true){
                    guardarEstatus.setEstatus("Mercancia Dañada");
                    ObtenerEstado obtenerEstado=new ObtenerEstado();
                    obtenerEstado.execute();
                }else if(rbD.isChecked()==true){
                    guardarEstatus.setEstatus("Cliente no disponible");
                    ObtenerEstado obtenerEstado=new ObtenerEstado();
                    obtenerEstado.execute();
                }
                else{
                    Toast.makeText(EstatusSeleccionActivity.this,"Selecciona un estatus",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private class ObtenerEstado extends AsyncTask<Void,Object,GuardarEstatus> {
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(EstatusSeleccionActivity.this);
            progressDialog.setMessage("Espera");
            progressDialog.setCancelable(false);
            progressDialog.show();
            super.onPreExecute();
        }
        @Override
        protected GuardarEstatus doInBackground(Void... params) {
            GuardarEstatusWebService guardarEstatusWebService = new GuardarEstatusWebService();
            GuardarEstatus estatus= guardarEstatusWebService.guardarEstatus(guardarEstatus);
            return estatus;
        }
        @Override
        protected void onProgressUpdate(Object... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(GuardarEstatus guardarEstatus) {
            super.onPostExecute(guardarEstatus);
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            Toast.makeText(EstatusSeleccionActivity.this,"Estatus guardado",Toast.LENGTH_SHORT).show();
            Intent i = new Intent(EstatusSeleccionActivity.this, EntregasActivity.class);
            startActivity(i);
            finish();
        }
    }
}

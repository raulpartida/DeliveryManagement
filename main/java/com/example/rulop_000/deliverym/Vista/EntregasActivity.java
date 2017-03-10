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
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.rulop_000.deliverym.Controlador.EstatusEntregasWebService;
import com.example.rulop_000.deliverym.Controlador.EstatusListAdapter;
import com.example.rulop_000.deliverym.Entidad.EstatusEntregas;
import com.example.rulop_000.deliverym.Entidad.PedidosWS;
import com.example.rulop_000.deliverym.R;

import java.util.ArrayList;

public class EntregasActivity extends AppCompatActivity {

    private ListView lvEstadoEntregas;
    private PedidosWS solicitud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entregas);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
          ObtenerEstatus obtenerEstatus=new ObtenerEstatus();
          obtenerEstatus.execute();

        lvEstadoEntregas=(ListView)findViewById(R.id.lvEstadoEntregas);


    }
    private class ObtenerEstatus extends AsyncTask<Void,Object,ArrayList<EstatusEntregas>> {
        private ProgressDialog progressDialog;


        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(EntregasActivity.this);
            progressDialog.setMessage("Espera");
            progressDialog.setCancelable(false);
            progressDialog.show();
            super.onPreExecute();
        }
        @Override
        protected ArrayList<EstatusEntregas> doInBackground(Void... params) {
            EstatusEntregasWebService estatusEntregasWebService= new EstatusEntregasWebService();
            SharedPreferences preferences = getSharedPreferences("user", Context.MODE_PRIVATE);
            ArrayList<EstatusEntregas> estatus =estatusEntregasWebService.getEstatus(new PedidosWS(preferences.getInt("id",0)));
            return estatus;
        }
        @Override
        protected void onProgressUpdate(Object... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(final ArrayList<EstatusEntregas> estatus) {
            super.onPostExecute(estatus);
            final ArrayList<EstatusEntregas> estatusObtenidos = new ArrayList<>();
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            if(!estatus.isEmpty())
                if (estatus.get(0).getNentrega()!=0){
                    for (int i=0;i<estatus.size();i++){
                        estatusObtenidos.add(estatus.get(i));
                    }

                    EstatusListAdapter estatusListAdapter= new EstatusListAdapter(getBaseContext(),estatusObtenidos);
                    lvEstadoEntregas.setAdapter(estatusListAdapter);
                    lvEstadoEntregas.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener(){
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent estadoIntent= new Intent(EntregasActivity.this, EstatusSeleccionActivity.class);
                            estadoIntent.putExtra("id_pedido",String.valueOf(estatus.get(position).getNentrega()));
                            startActivity(estadoIntent);
                            finish();
                        }
                    });
                }
                else{
                    Toast.makeText(EntregasActivity.this,"Error de conexion",Toast.LENGTH_SHORT).show();
                }

        }
    }
}

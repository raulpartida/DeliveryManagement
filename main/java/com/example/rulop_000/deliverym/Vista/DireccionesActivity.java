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
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.rulop_000.deliverym.Controlador.DireccionesListAdapter;
import com.example.rulop_000.deliverym.Controlador.PedidosDireccionesWebService;
import com.example.rulop_000.deliverym.Entidad.Pedidos;
import com.example.rulop_000.deliverym.Entidad.PedidosWS;
import com.example.rulop_000.deliverym.R;

import java.util.ArrayList;

public class DireccionesActivity extends AppCompatActivity{

    private ListView lvEntrega;
    private Button btnDetalles;
    private PedidosWS solicitud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direcciones);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ObtenerDirecciones obtenerDirecciones = new ObtenerDirecciones();
        obtenerDirecciones.execute();
        lvEntrega=(ListView)findViewById(R.id.lvEntregas);
    }

    private class ObtenerDirecciones extends AsyncTask<Void,Object,ArrayList<Pedidos>>{
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(DireccionesActivity.this);
            progressDialog.setMessage("Espera");
            progressDialog.setCancelable(false);
            progressDialog.show();
            super.onPreExecute();
        }

        @Override
        protected ArrayList<Pedidos> doInBackground(Void... params) {
            PedidosDireccionesWebService pedidosDireccionesWebService = new PedidosDireccionesWebService();
            SharedPreferences preferences = getSharedPreferences("user", Context.MODE_PRIVATE);
            ArrayList<Pedidos> pedidos = pedidosDireccionesWebService.getPedidos(new PedidosWS(preferences.getInt("id",0)));
             return pedidos;
        }
        @Override
        protected void onProgressUpdate(Object... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(final ArrayList<Pedidos> pedidos) {
            super.onPostExecute(pedidos);
            final ArrayList<Pedidos> pedidosObtenidos = new ArrayList<>();
            if (progressDialog.isShowing()){
                progressDialog.dismiss();
            }
            if(!pedidos.isEmpty())
            if (pedidos.get(0).getNentrega()!=0){
                for (int i=0;i<pedidos.size();i++){
                    pedidosObtenidos.add(pedidos.get(i));
                }
                DireccionesListAdapter direccionesListAdapter= new DireccionesListAdapter(getBaseContext(),pedidosObtenidos);
                lvEntrega.setAdapter(direccionesListAdapter);
                lvEntrega.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent mercaIntent= new Intent(DireccionesActivity.this, MercanciaActivity.class);
                        mercaIntent.putExtra("id_pedido",String.valueOf(pedidos.get(position).getNentrega()));//en el 0 se coloca el numero de pedido que se obtuvo del web service o directo del TextView
                        mercaIntent.putExtra("comentario",pedidos.get(position).getComentarios());
                        startActivity(mercaIntent);
                    }
                });
            }
            else{
                Toast.makeText(DireccionesActivity.this,"Error de conexion",Toast.LENGTH_SHORT).show();
            }
        }
    }
}


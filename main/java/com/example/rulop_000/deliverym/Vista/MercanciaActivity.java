package com.example.rulop_000.deliverym.Vista;

import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rulop_000.deliverym.Controlador.MercanciaListAdapter;
import com.example.rulop_000.deliverym.Controlador.MercanciaWebService;
import com.example.rulop_000.deliverym.Entidad.Mercancia;
import com.example.rulop_000.deliverym.Entidad.MercanciaWS;
import com.example.rulop_000.deliverym.R;

import java.util.ArrayList;

public class MercanciaActivity extends AppCompatActivity {

    private ListView lvMercancia;
    private MercanciaWS solicitada;
    private TextView  tvcomentario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        tvcomentario=(TextView)findViewById(R.id.tvComentario);

        Bundle bundle = getIntent().getExtras();
        solicitada = new MercanciaWS(Integer.valueOf(bundle.getString("id_pedido")));
        tvcomentario.setText("Comentarios: \n"+bundle.getString("comentario"));

        ObtenerMercancia obtenerMercancia= new ObtenerMercancia();
        obtenerMercancia.execute();
        lvMercancia=(ListView)findViewById(R.id.lvMercancia);

    }

    private class ObtenerMercancia extends AsyncTask<Void,Object,ArrayList<Mercancia>> {
        private ProgressDialog progressDialog;


        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(MercanciaActivity.this);
            progressDialog.setMessage("Espera");
            progressDialog.setCancelable(false);
            progressDialog.show();
            super.onPreExecute();
        }
        @Override
        protected ArrayList<Mercancia> doInBackground(Void... params) {
            MercanciaWebService mercanciaWebService = new MercanciaWebService();
            ArrayList<Mercancia> mercancias= mercanciaWebService.getMercancia(solicitada);
            return mercancias;
        }
        @Override
        protected void onProgressUpdate(Object... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(ArrayList<Mercancia> mercancias) {
            super.onPostExecute(mercancias);
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            if(!mercancias.isEmpty())
            if (mercancias.get(0).getEstado()==1){
                MercanciaListAdapter mercanciaListAdapter= new MercanciaListAdapter(getBaseContext(),mercancias);
                lvMercancia.setAdapter(mercanciaListAdapter);
                registerForContextMenu(lvMercancia);
            }
            else{
                Toast.makeText(MercanciaActivity.this,"Error de conexion",Toast.LENGTH_SHORT).show();
            }
        }
    }
}

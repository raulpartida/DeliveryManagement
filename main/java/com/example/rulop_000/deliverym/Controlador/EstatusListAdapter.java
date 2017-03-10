package com.example.rulop_000.deliverym.Controlador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.rulop_000.deliverym.Entidad.EstatusEntregas;
import com.example.rulop_000.deliverym.R;

import java.util.ArrayList;

/**
 * Created by rulop_000 on 31/07/2016.
 */
public class EstatusListAdapter extends ArrayAdapter<EstatusEntregas> {
    ArrayList<EstatusEntregas> estatusEntregasArrayList;

    public EstatusListAdapter(Context context, ArrayList<EstatusEntregas> estatus) {
        super(context, 0, estatus);
        this.estatusEntregasArrayList = estatus;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v = inflater.inflate(R.layout.listview_entregas, parent, false);

        TextView tvNumero=(TextView) v.findViewById(R.id.tvNumeroEntregaRuta);
        TextView tvNombre=(TextView) v.findViewById(R.id.tvNombreClienteEntrega);


        EstatusEntregas estatusEntregas= estatusEntregasArrayList.get(position);

        tvNumero.setText(String.valueOf(estatusEntregas.getNentrega()));
        tvNombre.setText(estatusEntregas.getNombre_negocio()+estatusEntregas.getNombre_cliente()+estatusEntregas.getApaterno()+estatusEntregas.getAmaterno());
       return v;
    }
}
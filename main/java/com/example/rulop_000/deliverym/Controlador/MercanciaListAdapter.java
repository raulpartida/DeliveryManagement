package com.example.rulop_000.deliverym.Controlador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.rulop_000.deliverym.Entidad.Mercancia;
import com.example.rulop_000.deliverym.R;

import java.util.ArrayList;

/**
 * Created by rulop_000 on 31/07/2016.
 */
public class MercanciaListAdapter extends ArrayAdapter<Mercancia> {
    ArrayList<Mercancia> mercanciaArrayAdapter;

    public MercanciaListAdapter(Context context, ArrayList<Mercancia> mercancia) {
        super(context, 0, mercancia);
        this.mercanciaArrayAdapter = mercancia;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v = inflater.inflate(R.layout.listview_mercancia, parent, false);

        TextView tvNombre=(TextView)v.findViewById(R.id.tvNombreMercacnia);
        TextView tvMarca=(TextView)v.findViewById(R.id.tvMarcaMercacnia);
        TextView tvCantidad=(TextView)v.findViewById(R.id.tvCantidadMercacnia);

        Mercancia mercancia = mercanciaArrayAdapter.get(position);

        tvNombre.setText(mercancia.getNombre());
        tvMarca.setText(mercancia.getMercancia());
        tvCantidad.setText(mercancia.getCantidad());
        return v;
    }
}

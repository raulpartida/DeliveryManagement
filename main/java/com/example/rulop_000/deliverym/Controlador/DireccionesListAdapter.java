package com.example.rulop_000.deliverym.Controlador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.rulop_000.deliverym.Entidad.Pedidos;
import com.example.rulop_000.deliverym.R;

import java.util.ArrayList;

/**
 * Created by rulop_000 on 31/07/2016.
 */
public class DireccionesListAdapter extends ArrayAdapter<Pedidos>  {

     ArrayList<Pedidos> pedidosArrayAdapter;

    public DireccionesListAdapter(Context context, ArrayList<Pedidos> pedido) {
        super(context,0,pedido);
        this.pedidosArrayAdapter=pedido;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v = inflater.inflate(R.layout.listview_direcciones, parent, false);


        TextView tvNumeroPedido=(TextView)v.findViewById(R.id.tvNumeroEntrega);
        TextView tvDireccion=(TextView)v.findViewById(R.id.tvDireccionCliente);
        TextView tvNombreCliente=(TextView)v.findViewById(R.id.tvNombreCliente);
        TextView tvEstado=(TextView)v.findViewById(R.id.tvEstatus);

        Pedidos pedidos = pedidosArrayAdapter.get(position);

            tvNumeroPedido.setText(String.valueOf(pedidos.getNentrega()));
            tvDireccion.setText(pedidos.getDireccion() + "," + pedidos.getColonia());
            tvNombreCliente.setText(pedidos.getNombre_negocio() + "" + pedidos.getNombre_cliente() + " " + pedidos.getApaterno() + " " + pedidos.getAmaterno());
            tvEstado.setText(pedidos.getEstatus());

        return v;
    }

}

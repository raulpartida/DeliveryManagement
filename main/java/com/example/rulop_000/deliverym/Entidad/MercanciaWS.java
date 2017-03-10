package com.example.rulop_000.deliverym.Entidad;

import java.io.Serializable;

/**
 * Created by rulop_000 on 31/07/2016.
 */
public class MercanciaWS implements Serializable{
    private int id_pedido;

    public MercanciaWS() {
    }

    public MercanciaWS(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }
}

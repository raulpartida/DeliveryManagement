package com.example.rulop_000.deliverym.Entidad;

/**
 * Created by rulop_000 on 31/07/2016.
 */
public class GuardarEstatus {
    private String estatus;
    private int id_pedido;

    public GuardarEstatus() {
    }

    public GuardarEstatus(String estatus, int id_pedido) {
        this.estatus = estatus;
        this.id_pedido = id_pedido;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }
}

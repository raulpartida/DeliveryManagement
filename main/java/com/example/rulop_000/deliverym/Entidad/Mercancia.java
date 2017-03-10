package com.example.rulop_000.deliverym.Entidad;

import java.io.Serializable;

/**
 * Created by rulop_000 on 31/07/2016.
 */
public class Mercancia implements Serializable {
    private String nombre,mercancia,cantidad;
    private int estado;
    public Mercancia() {
    }

    public Mercancia(String nombre, String cantidad, String mercancia) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.mercancia = mercancia;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getMercancia() {
        return mercancia;
    }

    public void setMercancia(String mercancia) {
        this.mercancia = mercancia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
